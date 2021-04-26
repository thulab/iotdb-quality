package cn.edu.thu.iotdb.quality.dprofile;

public class Mad {
    public final double result;
    public final double error_bound;

    public Mad(double result, double error_bound) {
        this.result = result;
        this.error_bound = error_bound;
    }

    public String toString(){
        return result + " - " + error_bound;
    }
}