package cn.edu.thu.iotdb.quality;

import org.apache.commons.math3.analysis.function.Abs;

import java.util.Arrays;

public class LinearRegression {
    double[] x,y,e,yhead;
    int n;
    double sumx,sumy,sumx2,xbar,ybar,xxbar,yybar,xybar;
    double beta1,beta0,df,rss,ssr,R2,svar,svar1,svar0;
    public LinearRegression(double[] a, double[] b) {
        x=a.clone();
        y=b.clone();
        n=x.length;
        e=new double[n];
        yhead=new double[n];
        double sumx= Arrays.stream(x).sum();
        double sumy= Arrays.stream(y).sum();
        double sumx2 = 0.0;
        for(int i=0;i<n;i++){
            sumx2+=x[i]*x[i];
        }
        double xbar = sumx / n;
        double ybar = sumy / n;
        // second pass: compute summary statistics
        double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            yybar += (y[i] - ybar) * (y[i] - ybar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        double beta1 = xybar / xxbar;
        double beta0 = ybar - beta1 * xbar;
        // analyze results
        int df = n - 2;
        double rss = 0.0;      // residual sum of squares
        double ssr = 0.0;      // regression sum of squares
        for (int i = 0; i < n; i++) {
            yhead[i] = beta1*x[i] + beta0;
            e[i]=yhead[i]-x[i];
            rss += (yhead[i] - y[i]) * (yhead[i] - y[i]);
            ssr += (yhead[i] - ybar) * (yhead[i] - ybar);
        }
        double R2    = ssr / yybar;
        double svar  = rss / df;
        double svar1 = svar / xxbar;
        double svar0 = svar/n + xbar*xbar*svar1;
    }
    public double getMSE(){
        return rss/n;
    }
    public double getMAbsE(){//mean abs error
        double sumAbsE=0.0;
        for(int i=0;i<n;i++){
            sumAbsE+=Math.abs(e[i]);
        }
        return sumAbsE/n;
    }
    public double[] getYhead(){
        return yhead;
    }
}


