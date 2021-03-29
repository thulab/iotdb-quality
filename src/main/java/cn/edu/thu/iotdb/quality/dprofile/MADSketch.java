package cn.edu.thu.iotdb.quality.dprofile;

import java.io.Serializable;
import java.util.*;

import static org.apache.commons.math3.util.Precision.EPSILON;

public class MADSketch implements Serializable {
    private double alpha;
    private double gamma;
    private double multiplier;
    private int bucket_num_limit;
    private int threshold_for_compression;

    private Map<Integer, Long> positive_buckets;
    private Map<Integer, Long> negative_buckets;
    private double collapse_bound;
    private long zero_count;

    transient private double beta;
    transient private final double[] valid_range;

    private final static double MIN_POSITIVE_VALUE = 1e-6;
    private final static double COEFFICIENT = 1.5;

    public MADSketch() {
        this(EPSILON, 2048);
    }

    public MADSketch(double alpha, int bucket_num_limit){
        this.alpha = alpha;
        this.bucket_num_limit = Math.max(bucket_num_limit, 2);
        this.threshold_for_compression = (int) (bucket_num_limit * COEFFICIENT);

        this.gamma = 2 * alpha / (1 - alpha) + 1;
        this.multiplier = Math.log(Math.E) / (Math.log1p(gamma - 1));
        this.beta = 1;
        this.positive_buckets = new HashMap<>((int)(bucket_num_limit * 0.75));
        this.negative_buckets = new HashMap<>((int)(bucket_num_limit * 0.25));
        this.zero_count = 0;
        this.collapse_bound = -Double.MAX_VALUE;
        this.valid_range = new double[6];
    }

    public void insert(double v){
        if(v < collapse_bound){
            v = collapse_bound;
        }
        if(v > MIN_POSITIVE_VALUE){
            int i = (int) Math.ceil(Math.log(v) * multiplier);
            positive_buckets.put(i, positive_buckets.getOrDefault(i, 0L) + 1);
        }else if(v < -MIN_POSITIVE_VALUE){
            int i = (int) Math.ceil(Math.log(-v) * multiplier);
            negative_buckets.put(i, negative_buckets.getOrDefault(i, 0L) + 1);
        }else{
            zero_count++;
        }
        collapse(threshold_for_compression);
    }

    public void insert(double v, double[] bounds){
        if(v < bounds[0]){
            v = bounds[0];
        }else if(v > bounds[1] && v < bounds[2]){
            v = bounds[2];
        }else if(v > bounds[3] && v < bounds[4]){
            v = bounds[4];
        }else if(v > bounds[5]){
            v = bounds[5];
        }
        insert(v);
    }

    public void merge(MADSketch sketch){
        zero_count += sketch.zero_count;
        for(Map.Entry<Integer, Long> entry: sketch.positive_buckets.entrySet()){
            positive_buckets.put(entry.getKey(), positive_buckets.getOrDefault(entry.getKey(), 0L) + entry.getValue());
        }

        for(Map.Entry<Integer, Long> entry: sketch.negative_buckets.entrySet()){
            negative_buckets.put(entry.getKey(), negative_buckets.getOrDefault(entry.getKey(), 0L) + entry.getValue());
        }

        this.collapse(bucket_num_limit);
    }

    private void collapse(int limit){
        if(sketch_size() > limit){
            int exceed = sketch_size() - bucket_num_limit;
            Integer[] indices = negative_buckets.keySet().toArray(new Integer[0]);
            Arrays.sort(indices);
            long count = 0;
            for(int i = Math.max(0, indices.length - exceed); i < indices.length; ++i){
                count += negative_buckets.remove(indices[i]);
            }
            if(count > 0){
                int i = indices.length - exceed - 1;
                if(i >= 0){
                    negative_buckets.put(indices[i], negative_buckets.get(indices[i]) + count);
                    collapse_bound = -Math.pow(gamma, indices[i]);
                }else{
                    zero_count += count;
                    collapse_bound = 0;
                }
            }
            exceed -= (indices.length - Math.max(0, indices.length - exceed));
            if(exceed > 0){
                count = zero_count;
                if(zero_count > 0){
                    exceed--;
                }
                indices = positive_buckets.keySet().toArray(new Integer[0]);
                Arrays.sort(indices);
                for(int i = exceed - 1; i >= 0; --i){
                    count += positive_buckets.remove(indices[i]);
                }
                positive_buckets.put(indices[exceed], positive_buckets.get(indices[exceed]) + count);
                collapse_bound = Math.pow(gamma, indices[exceed]-1);
            }
        }
    }

    private Bucket[] union_buckets(){
        Bucket[] buckets = new Bucket[sketch_size()];
        int i = 0;
        for(Map.Entry<Integer, Long> e: positive_buckets.entrySet()){
            buckets[i++] = new Bucket(e.getKey(), Math.pow(gamma, e.getKey() - 1), Math.pow(gamma, e.getKey()), e.getValue());
        }
        for(Map.Entry<Integer, Long> e: negative_buckets.entrySet()){
            buckets[i++] = new Bucket(e.getKey(), -Math.pow(gamma, e.getKey()), -Math.pow(gamma, e.getKey() - 1), e.getValue());
        }
        if(zero_count > 0){
            buckets[i] = new Bucket(0, 0, 0, zero_count);
        }
        Arrays.sort(buckets, Comparator.comparingDouble(o -> o.lower_bound));
        return buckets;
    }

    public long total_count(){
        return positive_buckets.values().stream().mapToLong(l-> l).sum() + negative_buckets.values().stream().mapToLong(l-> l).sum() + zero_count;
    }

    private int find_p_index(Bucket[] buckets, long total_count){
        long count = 0;
        double rank = 0.5 * (total_count - 1);
        for(int i = 0; i < buckets.length; ++i){
            count += buckets[i].count;
            if(count > rank){
                return i;
            }
        }
        return -1;
    }

    private int find_q_index(int p, Bucket[] buckets, long total_count){
        int q = p;
        long count = buckets[p].count;
        double rank = 0.5 * (total_count - 1);
        int l = p - 1;
        int r = p + 1;
        while(count <= rank && l >= 0 && r < buckets.length){
            if(buckets[p].lower_bound - buckets[l].upper_bound < buckets[r].lower_bound - buckets[p].upper_bound){
                q = l--;
            }else{
                q = r++;
            }
            count += buckets[q].count;
        }

        while(count <= rank && l >= 0){
            q = l--;
            count += buckets[q].count;
        }

        while(count <= rank && r < buckets.length){
            q = r++;
            count += buckets[q].count;
        }

        double m_lower_bound = buckets[p].lower_bound + buckets[p].upper_bound - buckets[q].upper_bound;
        double m_upper_bound = buckets[p].lower_bound + buckets[p].upper_bound - buckets[q].lower_bound;
        if(p > q){
            r--;
            if(buckets[r].lower_bound <= m_lower_bound && buckets[r].upper_bound >= m_upper_bound){
                q = r;
            }
        }else if(p < q){
            l++;
            if(buckets[l].lower_bound <= m_lower_bound && buckets[l].upper_bound >= m_upper_bound){
                q = l;
            }
        }
        return q;
    }

    private void setValid_range(Bucket p, Bucket q){
        valid_range[0] = p.lower_bound;
        valid_range[1] = p.upper_bound;
        valid_range[2] = p.lower_bound + q.lower_bound - p.upper_bound;
        valid_range[3] = p.upper_bound + q.upper_bound - p.lower_bound;
        valid_range[4] = 2 * p.lower_bound - q.upper_bound;
        valid_range[5] = 2 * p.upper_bound - q.lower_bound;
        Arrays.sort(valid_range);
    }

    private double minDelta(double delta1, double delta2){
        double delta;
        if(delta1 < 0 && delta2 < 0){
            delta = 0;
        }else if(delta1 < 0){
            delta = delta2;
        }else if(delta2 < 0){
            delta = delta1;
        }else{
            delta = Math.min(delta1, delta2);
        }
        return delta;
    }

    public double getMedian(){
        Bucket[] buckets = union_buckets();
        long total_count = total_count();
        Bucket p = buckets[find_p_index(buckets, total_count)];
        if(p.lower_bound < 0){
            return 2 * p.lower_bound / (1+gamma);
        }else{
            return 2 * p.upper_bound / (1+gamma);
        }
    }

    public Mad getMad(){
        beta = 1;
        Bucket[] buckets = union_buckets();
        long total_count = total_count();
        int p_index = find_p_index(buckets, total_count);

        if(p_index == -1){
            throw new NoSuchElementException("No values in the sketch");
        }

        int q_index = find_q_index(p_index, buckets, total_count);
        Bucket p = buckets[p_index];
        Bucket q = buckets[q_index];
        if(p.lower_bound == collapse_bound || q.lower_bound == collapse_bound){
            throw new IllegalArgumentException("The sketch has been compressed too much");
        }

        if(p.lower_bound * q.lower_bound > 0){
            if(p.lower_bound == q.lower_bound){
                return new Mad(0, Double.MAX_VALUE);
            }else{
                double mad = 2 * (p.upper_bound - q.lower_bound) * (p.lower_bound - q.upper_bound) / ((gamma + 1) * Math.abs(p.lower_bound - q.lower_bound));
                double gamma_p_q = Math.max(p.upper_bound / q.upper_bound, q.upper_bound / p.upper_bound);
                double delta;
                if(Math.abs(p.lower_bound) < Math.abs(q.lower_bound)){
                    delta = minDelta(gamma_p_q / Math.pow(gamma, 2) - 1 / gamma + Math.pow(gamma, -3), 1 / (Math.pow(gamma, 3) - gamma_p_q * gamma + Math.pow(gamma, 2)));
                }else{
                    delta = minDelta(Math.pow(gamma, -2) + Math.pow(gamma, -3) - 1 / (gamma_p_q * gamma), 1 / (Math.pow(gamma, 2) / gamma_p_q + Math.pow(gamma, 3) - gamma));
                }
                beta = 1 - 2 / (1 + delta);
                setValid_range(p, q);
                return new Mad(mad, (1 + 2 / (gamma_p_q - 1)) * alpha);
            }
        }else{
            double mad = 2 * Math.max(Math.abs(p.upper_bound - q.lower_bound), Math.abs(q.upper_bound - p.lower_bound)) / (gamma + 1);
            return new Mad(mad, alpha);
        }
    }

    public int sketch_size(){
        return positive_buckets.size() + negative_buckets.size() + (zero_count == 0 ? 0 : 1);
    }

    public boolean isAppropriate(){
        return beta > 0 && beta < 1;
    }

    public double getBeta() {
        return beta;
    }

    public double[] getValid_range() {
        return valid_range;
    }

    public void show(Bucket[] buckets){
        for(Bucket bucket: buckets){
            System.out.println(bucket.index + ": " + bucket.count);
        }
    }

    private static class Bucket{
        int index;
        double lower_bound;
        double upper_bound;
        long count;

        Bucket(int index, double lower_bound, double upper_bound, long count) {
            this.index = index;
            this.lower_bound = lower_bound;
            this.upper_bound = upper_bound;
            this.count = count;
        }
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public int getBucket_num_limit() {
        return bucket_num_limit;
    }

    public void setBucket_num_limit(int bucket_num_limit) {
        this.bucket_num_limit = bucket_num_limit;
    }

    public int getThreshold_for_compression() {
        return threshold_for_compression;
    }

    public void setThreshold_for_compression(int threshold_for_compression) {
        this.threshold_for_compression = threshold_for_compression;
    }

    public Map<Integer, Long> getPositive_buckets() {
        return positive_buckets;
    }

    public void setPositive_buckets(Map<Integer, Long> positive_buckets) {
        this.positive_buckets = positive_buckets;
    }

    public Map<Integer, Long> getNegative_buckets() {
        return negative_buckets;
    }

    public void setNegative_buckets(Map<Integer, Long> negative_buckets) {
        this.negative_buckets = negative_buckets;
    }

    public double getCollapse_bound() {
        return collapse_bound;
    }

    public void setCollapse_bound(double collapse_bound) {
        this.collapse_bound = collapse_bound;
    }

    public long getZero_count() {
        return zero_count;
    }

    public void setZero_count(long zero_count) {
        this.zero_count = zero_count;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }
}