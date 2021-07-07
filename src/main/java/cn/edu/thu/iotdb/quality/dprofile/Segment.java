package cn.edu.thu.iotdb.quality.dprofile;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import cn.edu.thu.iotdb.quality.LinearRegression;

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

    private ArrayList<double[]> approximated_segment(double[] in_seq){
        if(in_seq.length<=2){
            ArrayList<double[]> ret=new ArrayList<>();
            ret.add(in_seq);

            return ret;
        }
        double[] times=new double[in_seq.length];
        for(int i=0;i<times.length;i++){
            times[i]= i;
        }
        double[] approximated_values=new LinearRegression(times,in_seq).getYhead();

        double[] new_seg=new double[5];
        new_seg[0]=approximated_values[0];
        new_seg[1]=approximated_values[approximated_values.length-2];

    }

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
                .setOutputDataType(udfParameters.getDataType(0));


    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {



    }

    @Override
    public void terminate(PointCollector collector) throws Exception {

    }


}
