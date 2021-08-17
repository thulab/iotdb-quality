package cn.edu.thu.iotdb.quality;

import org.apache.commons.math3.analysis.function.Max;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class Queue {
    static int MaxLen;
    private int front;
    private int rear;
    TSDataType datatype;
    long[] timeQueueList;
    double[] doubleValueQueueList;
    float[] floatValueQueueList;
    int[] intValueQueueList;
    long[] longValueQueueList;
    double sum;
    public Queue(int len, TSDataType type) {
        MaxLen=len;
        timeQueueList=new long[MaxLen];
        datatype=type;
        sum=0.0d;
        switch(datatype){
            case INT32:
                intValueQueueList=new int[MaxLen];
                //longValueQueueList=new long[1];
                //floatValueQueueList=new float[1];
                //doubleValueQueueList=new double[1];
                break;
            case INT64:
                //intValueQueueList=new int[1];
                longValueQueueList=new long[MaxLen];
                //floatValueQueueList=new float[1];
                //doubleValueQueueList=new double[1];
                break;
            case FLOAT:
                //intValueQueueList=new int[1];
                //longValueQueueList=new long[1];
                floatValueQueueList=new float[MaxLen];
                //doubleValueQueueList=new double[1];
                break;
            case DOUBLE:
                //intValueQueueList=new int[1];
                //longValueQueueList=new long[1];
                //floatValueQueueList=new float[1];
                doubleValueQueueList=new double[MaxLen];
                break;
        }
        front=0;
        rear=0;

    }
    public boolean isEmpty() {
        return front == rear;
    }
    public boolean isFull(){
        return ((rear + 1) % MaxLen) == front;
    }
    public long queueFrontTime() throws Exception {
        if(!isEmpty()){
            return timeQueueList[(front+1)%MaxLen];
        }
        else {
            throw new Exception();
        }
    }
    public double queueFrontValue() throws Exception {
        if(!isEmpty()){
            switch(datatype){
                case INT32:
                    return intValueQueueList[(front+1)%MaxLen];
                case INT64:
                    return (double) longValueQueueList[(front+1)%MaxLen];
                case FLOAT:
                    return floatValueQueueList[(front+1)%MaxLen];
                case DOUBLE:
                    return doubleValueQueueList[(front+1)%MaxLen];
            }
            return 0;
        }
        else {
            throw new Exception();
        }
    }
    public long queueithTime(int i) throws Exception{
        int index=(front+i+1)%MaxLen;
        if(rear>=front&&(index>rear||index<=front)){
            System.out.println("ERROR01 Out of index");
            throw new Exception();
        }
        if(rear<front&&index>rear&&index<=front){
            System.out.println("ERROR02 Out of index");
            throw new Exception();
        }
        return timeQueueList[index];
    }
    public Object queueithValue(int i) throws Exception{
        int index=(front+i+1)%MaxLen;
        if(rear>=front&&(index>rear||index<=front)){
            System.out.println("ERROR01 Out of index");
            throw new Exception();
        }
        if(rear<front&&index>rear&&index<=front){
            System.out.println("ERROR02 Out of index");
            throw new Exception();
        }
        switch(datatype){
            case INT32:
                return intValueQueueList[index];
            case INT64:
                return longValueQueueList[index];
            case FLOAT:
                return floatValueQueueList[index];
            case DOUBLE:
                return doubleValueQueueList[index];
        }
        return 0;
    }
    public void push(long timestamp, Object value) {
        if(!isFull()){
            rear=(rear+1)%MaxLen;
            this.timeQueueList[rear]=timestamp;
            switch(datatype){
                case INT32:
                    intValueQueueList[rear]= Integer.parseInt(value.toString());
                    sum+=Integer.parseInt(value.toString());
                    break;
                case INT64:
                    longValueQueueList[rear]= Long.parseLong(value.toString());
                    sum+=Long.parseLong(value.toString());
                    break;
                case FLOAT:
                    floatValueQueueList[rear] = Float.parseFloat(value.toString());
                    sum+=Float.parseFloat(value.toString());
                    break;
                case DOUBLE:
                    doubleValueQueueList[rear] = Double.parseDouble(value.toString());
                    sum+=Double.parseDouble(value.toString());
                    break;
            }
        }
        else{
            System.out.println("ERROR:Queue is Full");
        }
    }
    public void pop() throws Exception {
        if(!isEmpty()){
            sum-=queueFrontValue();
            front=(front+1)%MaxLen;
        } else {
            System.out.println("ERROR:Queue is Empty");
        }
    }
    public int getRear(){
        return rear;
    }
    public int getFront(){
        return front;
    }
    public int getMaxLen(){
        return  MaxLen;
    }
    public double getSum() {return sum;}
    public int getLength(){
        if(rear>=front){
            return rear-front;
        }
        else{
            return MaxLen-(front-rear)+1;
        }
    }
}

