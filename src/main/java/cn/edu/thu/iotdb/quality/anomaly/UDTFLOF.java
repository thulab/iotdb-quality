package cn.edu.thu.iotdb.quality.anomaly;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;

public class UDTFLOF implements UDTF{
    private double threshold;
    private int k;
    private int dim;

    int Partition(Double[][] A,int left,int right){
        Double key=A[left][1];
        Double key2=A[left][0];
        while(left<right){
            while(left<right && A[right][1]>=key)
                right--;
            if(left<right) {
                A[left][0]=A[right][0];
                A[left][1]=A[right][1];
            }
            while(left<right && A[left][1]<=key)
                left++;
            if(left<right){
                A[right][0]=A[left][0];
                A[right][1]=A[left][1];
            }
        }
        A[left][0]=key2;
        A[left][1]=key;
        return left;
    }
    Double findKthNum(Double[][] A,int left,int right,int k){
        int index=Partition(A,left,right);
        if(index+1==k)
            return A[index][0];
        else if(index+1<k)
            return findKthNum(A,index+1,right,k);
        else
            return findKthNum(A,left,index-1,k);
    }
    public double getLOF(Double[][] knn, Double[] x, int length) {
        double sum = 0;
        for (int i=0;i<length;i++) {
            Double[] o=knn[i];
            sum += getLocDens(knn, o) / getLocDens(knn, x);
        }
        return sum / k;
    }
    public double getLocDens(Double[][] knn, Double[] x) {
        Double[] nnk = findKthPoint(knn, x);

        double sum = 0;
        for (Double[] o : knn) {
            sum += reachDist(o, x, nnk);
        }
        return sum / k;
    }
    public Double[] findKthPoint(Double[][] knn, Double[] x) {
        int index = 0;
        double minDist = dist(knn[0], x);
        Double[][] d =new Double[knn.length][2];
        for(int i=0;i<knn.length;i++){
            d[i][0]= (double)i;
            d[i][1]=dist(knn[i], x);
        }
        index=(int)(double)(findKthNum(d,0,knn.length-1,k+1));
        return knn[index];
    }
    public double reachDist(Double[] o, Double[] x, Double[] nnk) {
        return Math.max(dist(o, x), dist(nnk, x));
    }
    private double dist(Double[] nnk, Double[] x) {

        double sum = 0;
        for (int i = 0; i < nnk.length; i++) {
            sum += (nnk[i] - x[i]) * (nnk[i] - x[i]);
        }

        return Math.sqrt(sum);
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
        udtfConfigurations.setAccessStrategy(new SlidingSizeWindowAccessStrategy(udfParameters.getIntOrDefault("window", 10000)))
                .setOutputDataType(udfParameters.getDataType(0));
        this.k = udfParameters.getIntOrDefault("k", 3);
        this.threshold = udfParameters.getDoubleOrDefault("threshold",1);
        dim=udfParameters.getPaths().size();
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        int size=rowWindow.windowSize();
        Double[][] knn =new Double[rowWindow.windowSize()][dim];
        long[] timestamp =new long[rowWindow.windowSize()];
        int i=0;
        while(i<size){
            timestamp[i]=rowWindow.getRow(i).getTime();
            for(int j=0;j<dim;j++){
                if(!rowWindow.getRow(i).isNull(j)) {
                    knn[i][j] = Util.getValueAsDouble(rowWindow.getRow(i), j);
                }
                else{
                    i--;
                    size--;
                    break;
                }
            }
            i++;
        }
        double[] lof =new double[rowWindow.windowSize()];
        if(size>k) {
            for (int i = 0; i < size; i++) {
                lof[i] = getLOF(knn, knn[i],size);
                if (lof[i] > threshold) {
                    collector.putDouble(timestamp[i], lof[i]);
                }
            }
        }
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {

    }
}
