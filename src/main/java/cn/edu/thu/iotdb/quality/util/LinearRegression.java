package cn.edu.thu.iotdb.quality.util;

import org.apache.commons.math3.analysis.function.Abs;

import java.util.Arrays;

public class LinearRegression {
    double[] x,y,e,yhead;
    int n;
    double sumx,sumy,xbar,ybar,xxbar,yybar,xybar;
    double beta1,beta0,rss,ssr,R2,svar,svar1,svar0;
    public LinearRegression(double[] a, double[] b) {
        x=a.clone();
        y=b.clone();
        n=x.length;
        e=new double[n];
        yhead=new double[n];
        sumx= Arrays.stream(x).sum();
        sumy= Arrays.stream(y).sum();
        xbar = sumx / n;
        ybar = sumy / n;
        // second pass: compute summary statistics
        xxbar = 0.0; yybar = 0.0; xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            yybar += (y[i] - ybar) * (y[i] - ybar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        beta1 = xybar / xxbar;
        beta0 = ybar - beta1 * xbar;
        // analyze results
        int df = n - 2;
        rss = 0.0;      // residual sum of squares
        ssr = 0.0;      // regression sum of squares
        for (int i = 0; i < n; i++) {
            yhead[i] = beta1*x[i] + beta0;
            e[i]=yhead[i]-y[i];
            rss += (yhead[i] - y[i]) * (yhead[i] - y[i]);
            ssr += (yhead[i] - ybar) * (yhead[i] - ybar);
        }
        R2    = ssr / yybar;
        svar  = rss / df;
        svar1 = svar / xxbar;
        svar0 = svar/n + xbar*xbar*svar1;
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


