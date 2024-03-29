package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.commons.math3.linear.*;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;
import org.apache.commons.math3.stat.StatUtils;
import java.util.ArrayList;
import java.util.Arrays;

/*
    使用Yule-Walker法计算偏自相关函数
    改编自python库statsmodels.tsa.stattools.pacf_yw
 */

public class UDTFPartialAutoCorrelation implements UDTF {
    ArrayList<Double> value=new ArrayList<>();
    ArrayList<Long> timestamp=new ArrayList<>();
    int lag;
    int n=0;

    private double yuleWalker(double[] x, int order, String method){
        double adj_needed=method.equalsIgnoreCase("adjusted")?1:0;
        double[] r = new double[order+1];
        double squaresumx=0d;
        for (double v : x) {
            squaresumx += v * v;
        }
        r[0] = squaresumx / n;
        for (int k=1;k<order+1;k++){
            double[] t1 = Arrays.copyOfRange(x,0,n-k);
            double[] t2 = Arrays.copyOfRange(x, k, n);
            double crossmultiplysum=0d;
            for (int i=0;i<Math.min(t1.length,t2.length);i++){
                crossmultiplysum+=t1[i]*t2[i];
            }
            r[k] = crossmultiplysum / (n - k * adj_needed);
        }
        //R is a toeplitz matrix
        double[][] R = new double[r.length-1][r.length-1];
        for(int i=0;i<r.length-1;i++){
            for(int j=0;j<r.length-1;j++){
                R[i][j]=r[Math.abs(i-j)];
            }
        }
        RealMatrix a = new Array2DRowRealMatrix(R,true);
        RealVector b = new ArrayRealVector(Arrays.copyOfRange(r,1,r.length),true);
        DecompositionSolver solver = new LUDecomposition(a).getSolver();
        RealVector rho = solver.solve(b);
        /*
        sigmasq = r[0] - (r[1:]*rho).sum()
        sigma = np.sqrt(sigmasq) if not np.isnan(sigmasq) and sigmasq > 0 else np.nan
        */
        return rho.getEntry(rho.getDimension()-1);
    }

    @Override
    public void beforeStart(UDFParameters udfParameters, UDTFConfigurations udtfConfigurations) throws Exception {
        value.clear();
        timestamp.clear();
        udtfConfigurations.setAccessStrategy(new RowByRowAccessStrategy()).setOutputDataType(TSDataType.DOUBLE);
        lag=udfParameters.getIntOrDefault("lag",-1);
    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        value.add(Util.getValueAsDouble(row));
        timestamp.add(row.getTime());
    }

    @Override
    public void terminate(PointCollector collector) throws Exception {
        String method="adjusted";
        n=value.size();
        if(n>1){
            if(lag<0||lag>value.size()-1){
                lag= (int) Math.min(10 * Math.log10(value.size()), value.size() - 1);
            }
            double[] x= Arrays.stream(value.toArray(new Double[0])).mapToDouble(Double::valueOf).toArray();
            double xmean=StatUtils.mean(x);
            for (int i=0;i<x.length;i++){
                x[i]-=xmean;
            }
            collector.putDouble(timestamp.get(0),1.0d);
            for (int k=1;k<=lag;k++){
                collector.putDouble(timestamp.get(k),yuleWalker(x,k,method));
            }
        }
    }
}
