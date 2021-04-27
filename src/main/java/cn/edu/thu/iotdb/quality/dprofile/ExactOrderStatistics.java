package cn.edu.thu.iotdb.quality.dprofile;

import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ExactOrderStatistics {
    public static double getMedian(FloatArrayList nums) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return kthSmallest(nums.toArray(), (nums.size() >> 1) + 1, true);
        }
    }

    public static double getMad(FloatArrayList nums) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            float[] arr = nums.toArray();
            float median = kthSmallest(arr, (arr.length >> 1) + 1, true);
            for(int i = 0; i < arr.length; ++i){
                arr[i] = Math.abs(arr[i] - median);
            }
            return kthSmallest(arr, (arr.length >> 1) + 1, true);
        }
    }

    public static double getPercentile(FloatArrayList nums, double phi) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return kthSmallest(nums.toArray(), (int)Math.ceil(nums.size() * phi), false);
        }
    }

    public static double getMedian(DoubleArrayList nums) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return kthSmallest(nums.toArray(), (nums.size() >> 1) + 1, true);
        }
    }

    public static double getMad(DoubleArrayList nums) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            double[] arr = nums.toArray();
            final double median = kthSmallest(arr, (arr.length >> 1) + 1, true);
            return kthSmallest(Arrays.stream(arr).map(x->Math.abs(x - median)).toArray(), (arr.length >> 1) + 1, true);
        }
    }

    public static double getPercentile(DoubleArrayList nums, double phi) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return kthSmallest(nums.toArray(), (int)Math.ceil(nums.size() * phi), false);
        }
    }

    public static double getMedian(IntArrayList nums) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return kthSmallest(nums.toArray(), (nums.size() >> 1) + 1, true);
        }
    }

    public static double getMad(IntArrayList nums) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            int[] arr = nums.toArray();
            final double median = kthSmallest(arr, (arr.length >> 1) + 1, true);
            return kthSmallest(Arrays.stream(arr).mapToDouble(x->Math.abs(x - median)).toArray(), (arr.length >> 1) + 1, true);
        }
    }

    public static double getPercentile(IntArrayList nums, double phi) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return kthSmallest(nums.toArray(), (int)Math.ceil(nums.size() * phi), false);
        }
    }

    public static double getMedian(LongArrayList nums) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return kthSmallest(nums.toArray(), (nums.size() >> 1) + 1, true);
        }
    }

    public static double getMad(LongArrayList nums) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            long[] arr = nums.toArray();
            final double median = kthSmallest(arr, (arr.length >> 1) + 1, true);
            return kthSmallest(Arrays.stream(arr).mapToDouble(x->Math.abs(x - median)).toArray(), (arr.length >> 1) + 1, true);
        }
    }

    public static double getPercentile(LongArrayList nums, double phi) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return kthSmallest(nums.toArray(), (int)Math.ceil(nums.size() * phi), false);
        }
    }

    private static double kthSmallest(double[] num, int k, boolean even){
        int partition = kthSmallest(num, 0, num.length - 1, k);
        double median = num[partition];
        if(even && (num.length & 1) == 0){
            partition = kthSmallest(num, 0, partition - 1, k - 1);
            median = (median + num[partition]) / 2;
        }
        return median;
    }

    private static int kthSmallest(double[] num, int low,int high, int k){
        int partition = partition(num, low, high);

        while(partition != k - 1){
            if(partition < k - 1){
                low = partition + 1;
            }else{
                high = partition - 1;
            }
            partition = partition(num, low, high);
        }

        return partition;
    }


    private static int partition(double[] num, int low, int high){
        double pivot = num[high];
        int pivotloc = low;
        for (int i = low; i <= high; i++) {
            if (num[i] < pivot) {
                double temp = num[i];
                num[i] = num[pivotloc];
                num[pivotloc] = temp;
                pivotloc++;
            }
        }
        double temp = num[high];
        num[high] = num[pivotloc];
        num[pivotloc] = temp;
        return pivotloc;
    }

    private static float kthSmallest(float[] num, int k, boolean even){
        int partition = kthSmallest(num, 0, num.length - 1, k);
        float median = num[partition];
        if(even && (num.length & 1) == 0){
            partition = kthSmallest(num, 0, partition - 1, k - 1);
            median = (median + num[partition]) / 2;
        }
        return median;
    }

    private static int kthSmallest(float[] num, int low,int high, int k){
        int partition = partition(num, low, high);

        while(partition != k - 1){
            if(partition < k - 1){
                low = partition + 1;
            }else{
                high = partition - 1;
            }
            partition = partition(num, low, high);
        }

        return partition;
    }


    private static int partition(float[] num, int low, int high){
        float pivot = num[high];
        int pivotloc = low;
        for (int i = low; i <= high; i++) {
            if (num[i] < pivot) {
                float temp = num[i];
                num[i] = num[pivotloc];
                num[pivotloc] = temp;
                pivotloc++;
            }
        }
        float temp = num[high];
        num[high] = num[pivotloc];
        num[pivotloc] = temp;
        return pivotloc;
    }

    private static double kthSmallest(int[] num, int k, boolean even){
        int partition = kthSmallest(num, 0, num.length - 1, k);
        double median = num[partition];
        if(even && (num.length & 1) == 0){
            partition = kthSmallest(num, 0, partition - 1, k - 1);
            median = (median + num[partition]) / 2;
        }
        return median;
    }

    private static int kthSmallest(int[] num, int low,int high, int k){
        int partition = partition(num, low, high);

        while(partition != k - 1){
            if(partition < k - 1){
                low = partition + 1;
            }else{
                high = partition - 1;
            }
            partition = partition(num, low, high);
        }

        return partition;
    }


    private static int partition(int[] num, int low, int high){
        int pivot = num[high];
        int pivotloc = low;
        for (int i = low; i <= high; i++) {
            if (num[i] < pivot) {
                int temp = num[i];
                num[i] = num[pivotloc];
                num[pivotloc] = temp;
                pivotloc++;
            }
        }
        int temp = num[high];
        num[high] = num[pivotloc];
        num[pivotloc] = temp;
        return pivotloc;
    }

    private static double kthSmallest(long[] num, int k, boolean even){
        int partition = kthSmallest(num, 0, num.length - 1, k);
        double median = num[partition];
        if(even && (num.length & 1) == 0){
            partition = kthSmallest(num, 0, partition - 1, k - 1);
            median = (median + num[partition]) / 2;
        }
        return median;
    }

    private static int kthSmallest(long[] num, int low,int high, int k){
        int partition = partition(num, low, high);

        while(partition != k - 1){
            if(partition < k - 1){
                low = partition + 1;
            }else{
                high = partition - 1;
            }
            partition = partition(num, low, high);
        }

        return partition;
    }


    private static int partition(long[] num, int low, int high){
        long pivot = num[high];
        int pivotloc = low;
        for (int i = low; i <= high; i++) {
            if (num[i] < pivot) {
                long temp = num[i];
                num[i] = num[pivotloc];
                num[pivotloc] = temp;
                pivotloc++;
            }
        }
        long temp = num[high];
        num[high] = num[pivotloc];
        num[pivotloc] = temp;
        return pivotloc;
    }
}