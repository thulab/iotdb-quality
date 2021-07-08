package cn.edu.thu.iotdb.quality.dprofile;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import cn.edu.thu.iotdb.quality.LinearRegression;
import cn.edu.thu.iotdb.quality.Util;
import java.util.ArrayList;
import java.util.Arrays;


public class Segment implements UDTF {

    private double calculate_error(double[] x1, double[] x2){
        double[] x= ArrayUtils.addAll(x1,x2);
        int l=x.length;
        double[] y=new double[l];
        for(int i=0;i<l;i++){
            y[i]=(double) i;
        }
        LinearRegression linearFit= new LinearRegression(x,y);
        return linearFit.getMAbsE();
    }

    private ArrayList<double[]> bottom_up(double[] value, double max_error){
        ArrayList<double[]> seg_ts=new ArrayList<>();
        if(value.length<=2){
            ArrayList<double[]> ret=new ArrayList<>();
            ret.add(value);
            return ret;
        }
        for(int i=0;i<value.length;i+=2){
            seg_ts.set(i/2, Arrays.copyOfRange(value,i,i+2));
        }
        double[] merge_cost = new double[seg_ts.size()-1];
        for (int i=0;i<seg_ts.size()-1;i++){
            merge_cost[i]=calculate_error(seg_ts.get(i), seg_ts.get(i + 1));
        }
        while(Arrays.stream(merge_cost).min().getAsDouble()<max_error){
            int index=ArrayUtils.indexOf(merge_cost,Arrays.stream(merge_cost).min().getAsDouble());
            seg_ts.set(index, ArrayUtils.addAll(seg_ts.get(index),seg_ts.get(index+1)));
            seg_ts.remove(index+1);
            ArrayUtils.remove(merge_cost,index);
            if(seg_ts.size()==1){
                break;
            }
            if(index+1<seg_ts.size()){
                merge_cost[index]=calculate_error(seg_ts.get(index), seg_ts.get(index + 1));
                merge_cost[index-1]=calculate_error(seg_ts.get(index - 1), seg_ts.get(index));
            }
        }
        return  seg_ts;
    }

    private double[] best_line(double max_error, double[] input_df, double[] w, int start_idx, double upper_bound){
        double error=0.0;
        int idx=start_idx+w.length;
        double[] S_prev=w.clone();
        if(idx>=input_df.length){
            return  S_prev;
        }
        while (error<=max_error){
            double[] S=ArrayUtils.addAll(S_prev,input_df[idx]);
            idx++;
            double[] times=new double[S.length];
            for(int i=0;i<times.length;i++){
                times[i]= i;
            }
            error= new LinearRegression(times,S).getMAbsE();
            if (error<=max_error){
                S_prev=S.clone();
            }
            if(S_prev.length>upper_bound||input_df[idx]==0){
                break;
            }
        }
        return  S_prev;
    }

    private double[] approximated_segment(double[] in_seq){
        if(in_seq.length<=2){

            return in_seq;
        }
        double[] times=new double[in_seq.length];
        for(int i=0;i<times.length;i++){
            times[i]= i;
        }
        double[] approximated_values=new LinearRegression(times,in_seq).getYhead();

        double[] new_seg=new double[5];
        new_seg[0]=approximated_values[0];
        new_seg[1]=approximated_values[approximated_values.length-2];

        return  new_seg;
    }

    private ArrayList<double[]> swab(double[] input_df, double max_error, int seg_num, int in_window_size){
        int cur_nr=0;
        int window_size=in_window_size;
        int w_nr=cur_nr+window_size;
        int tot_size= input_df.length;
        double[] w=Arrays.copyOfRange(input_df,cur_nr,w_nr);
        int lower_bound=w_nr/2;
        int upper_bound=2*w_nr;
        ArrayList<double[]> seg_ts=new ArrayList<>();
        boolean last_run=false;
        while(true){
            ArrayList<double[]> T=bottom_up(w,max_error);
            seg_ts.add(T.get(0));
            if(cur_nr>=tot_size||last_run){
                if(T.size()>1){
                    seg_ts.add(approximated_segment(T.get(1)));
                }
                break;
            }
            cur_nr+=T.get(0).length-1;
            w_nr=cur_nr+window_size;
            if(input_df.length<=w_nr){
                w_nr=-1;
                last_run=true;
                w=Arrays.copyOfRange(input_df,cur_nr,input_df.length);
            }
            else{
                w=Arrays.copyOfRange(input_df,cur_nr,w_nr);
            }
            Arrays.sort(w);
            w=best_line(max_error,input_df,w,cur_nr,upper_bound);
            if(w.length>upper_bound){
                w=Arrays.copyOfRange(w,0,upper_bound);
            }
        }
        return seg_ts;
    }

    private ArrayList<double[]> swab_alg(double[] df, long[] timestamp, double max_error, int windowsize, double thr_steady, double thr_steep){
        double error_bound=(Arrays.stream(timestamp).max().getAsLong()-Arrays.stream(timestamp).min().getAsLong())*max_error;
        ArrayList<double[]> res_df1=swab(df,error_bound,10,windowsize);
        return res_df1;
    }

    private int window_size;
    private double max_error;
    private String method;
    private ArrayList<Long> timestamp=new ArrayList<>();
    private ArrayList<Double> value=new ArrayList<>();
    @Override
    public void validate(UDFParameterValidator validator) throws Exception {
        validator.validateInputSeriesDataType(0,
                TSDataType.INT32,
                TSDataType.INT64,
                TSDataType.FLOAT,
                TSDataType.DOUBLE);
    }

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        this.window_size=udfParameters.getIntOrDefault("window",10);
        this.max_error=udfParameters.getDoubleOrDefault("error",0.1);
        this.method=udfParameters.getStringOrDefault("method","swab");
        this.method=this.method.toLowerCase();
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        timestamp.add(row.getTime());
        value.add(Util.getValueAsDouble(row));

    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        long[] ts = timestamp.stream().mapToLong(Long::valueOf).toArray();
        double[] v=value.stream().mapToDouble(Double::valueOf).toArray();
        ArrayList<double[]> seg=swab_alg(v,ts,max_error,window_size,0.1,1.75);
        int index=0;
        for (double[] doubles : seg) {
            collector.putDouble(ts[index], doubles[0]);
            index += doubles.length;
        }
    }
}
