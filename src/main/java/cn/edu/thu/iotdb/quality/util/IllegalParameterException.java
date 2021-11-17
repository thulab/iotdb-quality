package cn.edu.thu.iotdb.quality.util;

public class IllegalParameterException extends Exception{
    String parameter;
    IllegalParameterException(String parameterName){
        parameter=parameterName;
    }
    @Override
    public String toString() {
        String s = "Illegal Parameter: "+parameter;
        return s + super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
