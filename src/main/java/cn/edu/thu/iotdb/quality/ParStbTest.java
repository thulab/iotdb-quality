package cn.edu.thu.iotdb.quality;

import com.csvreader.CsvReader;
import cn.edu.thu.iotdb.quality.DataStatisticsUtils;
import java.util.ArrayList;
import java.util.Arrays;

public class ParStbTest {
    private static double k=20.0f;
    private static String method="ksigma";
    private static String path="G:\\yzhstbdata\\";
    private static int window=288*5;
    public static void main(String[] args) throws Exception {
        String[] guoshou_1={
                "guoshou_value_from2019-12-27to2020-01-21_7501.csv",
                "guoshou_value_from2019-12-27to2020-01-21_7521.csv",
                "guoshou_value_from2019-12-27to2020-01-21_7525.csv",
                "guoshou_value_from2019-12-27to2020-01-21_7533.csv",
                "guoshou_value_from2019-12-27to2020-02-27_7630.csv",
                "guoshou_value_from2019-12-27to2020-02-27_7638.csv",
                "guoshou_value_from2019-12-27to2020-02-27_7657.csv",
                "guoshou_value_from2019-12-27to2020-02-27_7693.csv"
        };
        String[] guoshou_2={
                "guoshou_value_from2019-12-27to2020-01-21_7521.csv"
        };
        String[] hangxin_1={
                "hangxin_value_from2020-04-09to2020-04-23_7586.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_964.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_1205.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_1651.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_1754.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_1890.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_2057.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_2155.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_2197.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_2296.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_2571.csv",
                //"hangxin_valuelist_from2019-11-16to2019-12-16_2711.csv",
                //"hangxin_valuelist_from2019-11-16to2019-12-16_2754.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_2794.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_3083.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_3267.csv",
                //"hangxin_valuelist_from2019-11-16to2019-12-16_3276.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_3294.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_3514.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_3523.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_3736.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_3752.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_3988.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_4588.csv",
                //"hangxin_valuelist_from2019-11-16to2019-12-16_4599.csv",
                //"hangxin_valuelist_from2019-11-16to2019-12-16_4830.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_4944.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_4950.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_4971.csv",
                //"hangxin_valuelist_from2019-11-16to2019-12-16_5016.csv",
                //"hangxin_valuelist_from2019-11-16to2019-12-16_6207.csv",
                //"hangxin_valuelist_from2019-11-16to2019-12-16_6304.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_6378.csv"
                //,"hangxin_valuelist_from2019-11-16to2019-12-16_7158.csv"
        };
        String[] hangxin_2={
                //"hangxin_valuelist_from2019-11-16to2019-12-16_2155.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_2711.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_2754.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_3276.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_4599.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_4830.csv",
                //"hangxin_valuelist_from2019-11-16to2019-12-16_4950.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_5016.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_6207.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_6304.csv",
                "hangxin_valuelist_from2019-11-16to2019-12-16_7158.csv"
        };
        String[] jiaohang={
                "jiaohang_value_from2019-09-23to2019-10-08_7569.csv"
        };
        String[] liantong={
                "liantong_data_from2018-12-19to2019-01-31_8217.csv",
                "liantong_data_from2018-12-19to2019-01-31_8328.csv",
                "liantong_data_from2018-12-19to2019-01-31_8379.csv"
        };
        String[] zhongxin_1={
                "zhongxin_data_from2019-01-01to2019-01-31_7711.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_7720.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_7752.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_7788.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_7798.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_7812.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_7822.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_7826.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_7854.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_7867.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_7912.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_7965.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_7967.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_7976.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_7980.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_8013.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_8020.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_8035.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_8040.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_8076.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_8090.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_8091.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_8103.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_8105.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_8112.csv"
        };
        String[] zhongxin_2={
                "zhongxin_data_from2019-01-01to2019-01-31_7788.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_7812.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_7965.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_7967.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_8013.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_8035.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_8040.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_8076.csv",
                //"zhongxin_data_from2019-01-01to2019-01-31_8090.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_8091.csv"
                //,"zhongxin_data_from2019-01-01to2019-01-31_8105.csv"
        };
        String[] zhongxin_3={
                "zhongxin_data_from2019-01-01to2019-01-31_7965.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_8013.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_8035.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_8040.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_8076.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_8090.csv",
                "zhongxin_data_from2019-01-01to2019-01-31_8105.csv"
        };
        for (String filename:guoshou_1) {
            CsvReader csvReader=new CsvReader(path+filename,',');
            csvReader.readHeaders();
            //ArrayList<Long> timestamp=new ArrayList<>();
            ArrayList<Double> value=new ArrayList<>();
            ArrayList<Integer> label=new ArrayList<>();
            // 读取除表头外的内容
            while (csvReader.readRecord()) {
                // 读取一整行
                String line = csvReader.getRawRecord();
                //System.out.println(line);
                //timestamp.add(Long.parseLong(line.split(",")[0]));
                value.add(Double.parseDouble(line.split(",")[1]));
                label.add(Double.parseDouble(line.split(",")[2])>0.5?1:0);
            }
            csvReader.close();
            //long[] ts=timestamp.stream().mapToLong(Long::valueOf).toArray();
            double[] v=value.stream().mapToDouble(Double::valueOf).toArray();
            int[] l=label.stream().mapToInt(Integer::valueOf).toArray();
            int length=v.length;
            int TP=0,TN=0,FP=0,FN=0;
            if(filename.toLowerCase().contains("zhongxin")){
                for(int i=length-1;i>0;i--){
                    v[i]=v[i]-v[i-1];
                }
                v[0]=0.0f;
                if(method.equalsIgnoreCase("ksigma")){
                    //for k-sigma method
                    double sigma=DataStatisticsUtils.getStandardDevition(v);
                    double median=DataStatisticsUtils.getMedian(v);
                    for(int i=0;i<length;i++){
                        if(v[i]>median+k*sigma||v[i]<median-k*sigma){//anomaly detected
                            if(l[i]==0){
                                FP++;
                            }
                            else if(l[i]==1){
                                TP++;
                            }
                        }
                        else {//anomaly not detected
                            if(l[i]==0){
                                TN++;
                            }
                            else if(l[i]==1){
                                FN++;
                            }
                        }
                    }
                }
                else if(method.equalsIgnoreCase("mad")){
                    //for k-MAD method
                    double mad=DataStatisticsUtils.getMedianAbsoluteDeviation(v);
                    double median=DataStatisticsUtils.getMedian(v);
                    for(int i=0;i<length;i++){
                        if(v[i]>median+k*mad||v[i]<median-k*mad){//anomaly detected
                            if(l[i]==0){
                                FP++;
                            }
                            else if(l[i]==1){
                                TP++;
                            }
                        }
                        else {//anomaly not detected
                            if(l[i]==0){
                                TN++;
                            }
                            else if(l[i]==1){
                                FN++;
                            }
                        }
                    }
                }
            }
            else{
                if(method.equalsIgnoreCase("ksigma")){
                    //for k-sigma method
                    for(int round=0;round*window<length;round++){
                        double[] vtemp= Arrays.copyOfRange(v,round*window,(Math.min((round + 1) * window, length)));
                        int[] ltemp= Arrays.copyOfRange(l,round*window,(Math.min((round + 1) * window, length)));
                        double sigma=DataStatisticsUtils.getStandardDevition(vtemp);
                        double median=DataStatisticsUtils.getMedian(vtemp);
                        for(int i = 0; i<(round*window+window<length?window:length-round*window); i++){
                            if(vtemp[i]>median+k*sigma||vtemp[i]<median-k*sigma){//anomaly detected
                                if(ltemp[i]==0){
                                    FP++;
                                }
                                else if(ltemp[i]==1){
                                    TP++;
                                }
                            }
                            else {//anomaly not detected
                                if(ltemp[i]==0){
                                    TN++;
                                }
                                else if(ltemp[i]==1){
                                    FN++;
                                }
                            }
                        }
                    }

                }
                else if(method.equalsIgnoreCase("mad")){
                    //for k-MAD method
                    for(int round=0;round*window<length;round++){
                        double[] vtemp= Arrays.copyOfRange(v,round*window,(Math.min((round + 1) * window, length)));
                        int[] ltemp= Arrays.copyOfRange(l,round*window,(Math.min((round + 1) * window, length)));
                        double mad=DataStatisticsUtils.getMedianAbsoluteDeviation(v);
                        double median=DataStatisticsUtils.getMedian(vtemp);
                        for(int i = 0; i<(round*window+window<length?window:length-round*window); i++){
                            if(vtemp[i]>median+k*mad||vtemp[i]<median-k*mad){//anomaly detected
                                if(ltemp[i]==0){
                                    FP++;
                                }
                                else if(ltemp[i]==1){
                                    TP++;
                                }
                            }
                            else {//anomaly not detected
                                if(ltemp[i]==0){
                                    TN++;
                                }
                                else if(ltemp[i]==1){
                                    FN++;
                                }
                            }
                        }
                    }
                }
            }
                        double precision,recall,f1;
            precision=(TP+FP)>0?TP/(double)(TP+FP):(TP+1.0)/(TP+FP+1.0);
            recall=(TP+FN)>0?TP/(double)(TP+FN):(TP+1.0)/(TP+FP+1.0);
            f1=(precision+recall)>0?2*precision*recall/(precision+recall):-1;
            System.out.println(filename+"\t"+ TP +"\t"+ FP +"\t"+ TN +"\t"+ FN +"\t"+String.format("%.2f\t%.2f\t%.2f",precision,recall,f1));
        }
    }
}
