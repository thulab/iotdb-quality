package cn.edu.thu.iotdb.quality.frequency;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import java.util.ArrayList;
import java.util.Arrays;

// 输入必须是2的整数次幂个点，视为等距采样
public class UDTFWavelet implements UDTF {
    /**
     * The number of coefficients.
     */
    private int ncof;
    /**
     * Centering.
     */
    private int ioff;
    private int joff;
    /**
     * Wavelet coefficients.
     */
    private double[] cc;
    private double[] cr;

    /**
     * Workspace.
     */
    private double[] workspace = new double[1024];

    private ArrayList<Long> timestamp;
    private ArrayList<Double> value;

    public static boolean isPower2(int x) {
        return x > 0 && (x & (x - 1)) == 0;
    }
    /**
     * Log of base 2.
     * @param x a real number.
     * @return the value <code>log2(x)</code>.
     */
    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    /**
     * Applies the wavelet filter to a signal vector a[0, n-1].
     * @param a the signal vector.
     * @param n the length of vector.
     */
    void forward(Double[] a, int n) {
        if (n < ncof) {
            return;
        }

        if (n > workspace.length) {
            workspace = new double[n];
        } else {
            Arrays.fill(workspace, 0, n, 0.0);
        }

        int nmod = ncof * n;
        int n1 = n - 1;
        int nh = n >> 1;

        for (int ii = 0, i = 0; i < n; i += 2, ii++) {
            int ni = i + 1 + nmod + ioff;
            int nj = i + 1 + nmod + joff;
            for (int k = 0; k < ncof; k++) {
                int jf = n1 & (ni + k + 1);
                int jr = n1 & (nj + k + 1);
                workspace[ii] += cc[k] * a[jf];
                workspace[ii + nh] += cr[k] * a[jr];
            }
        }

        System.arraycopy(workspace, 0, a, 0, n);
    }

    /**
     * Applies the inverse wavelet filter to a signal vector a[0, n-1].
     * @param a the signal vector.
     * @param n the length of vector.
     */
    void backward(double[] a, int n) {
        if (n < ncof) {
            return;
        }

        if (n > workspace.length) {
            workspace = new double[n];
        } else {
            Arrays.fill(workspace, 0, n, 0.0);
        }

        int nmod = ncof * n;
        int n1 = n - 1;
        int nh = n >> 1;

        for (int ii = 0, i = 0; i < n; i += 2, ii++) {
            double ai = a[ii];
            double ai1 = a[ii + nh];
            int ni = i + 1 + nmod + ioff;
            int nj = i + 1 + nmod + joff;
            for (int k = 0; k < ncof; k++) {
                int jf = n1 & (ni + k + 1);
                int jr = n1 & (nj + k + 1);
                workspace[jf] += cc[k] * ai;
                workspace[jr] += cr[k] * ai1;
            }
        }

        System.arraycopy(workspace, 0, a, 0, n);
    }

    /**
     * Discrete wavelet transform.
     * @param a the signal vector.
     */
    public void waveletTransform(Double[] a) {
        int n = a.length;

        if (!isPower2(n)) {
            throw new IllegalArgumentException("The data vector size is not a power of 2.");
        }

        if (n < ncof) {
            throw new IllegalArgumentException("The data vector size is less than wavelet coefficient size.");
        }

        for (int nn = n; nn >= ncof; nn >>= 1) {
            forward(a, nn);
        }
    }

    /**
     * Inverse discrete wavelet transform.
     * @param a the signal vector.
     */
    public void inverse(double[] a) {
        int n = a.length;

        if (!isPower2(n)) {
            throw new IllegalArgumentException("The data vector size is not a power of 2.");
        }

        if (n < ncof) {
            throw new IllegalArgumentException("The data vector size is less than wavelet coefficient size.");
        }

        int start = n >> (int) Math.floor(log2(n/(ncof-1.)));
        for (int nn = start; nn <= n; nn <<= 1) {
            backward(a, nn);
        }
    }

    /**
     * Create a wavelet with given coefficients.
     */
    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        configurations.setAccessStrategy(new RowByRowAccessStrategy())
                .setOutputDataType(TSDataType.DOUBLE);
        String s= parameters.getString("coef");
        String[] coefString=s.split(",");
        ncof=coefString.length;
        ioff = joff = -(ncof >> 1);
        cc=new double[ncof];
        cr=new double[ncof];
        double sig=-1.0;
        for (int i=0;i<ncof;i++){
            cc[i]=Double.parseDouble(coefString[i]);
            cr[ncof-1-i]=sig*cc[i];
            sig=-sig;
        }
    }
    @Override
    public void transform(Row row, PointCollector pointCollector) throws Exception{
        timestamp.add(row.getTime());
        value.add(Util.getValueAsDouble(row));
    }
    @Override
    public void terminate(PointCollector pointCollector) throws Exception{
        Double[] r=value.toArray(new Double[0]);
        waveletTransform(r);
        for(int i=0;i<ncof;i++){
            pointCollector.putDouble(timestamp.get(i),r[i]);
        }
    }
}

