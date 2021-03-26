package cn.edu.thu.dquality.udf.SeriesMatching;


import cn.edu.thu.dquality.NoNumberException;
import cn.edu.thu.dquality.Util;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;

public class SeriesMatching {
    private double[][] distance;
    private ArrayList<Double> X;
    private ArrayList<Double> Y;
    private double[][] dtw;
    private double minDtw;
    private double minDex;
    public ArrayList<ArrayList<Double>> listTemp;
    public ArrayList<Integer> listMatching;


    public SeriesMatching(ArrayList<ArrayList<Double>> listTemp) {
        this.X = listTemp.get(0);
        this.Y = listTemp.get(1);
        this.listMatching = new ArrayList<>();
        this.listTemp = listTemp;
    }

    public SeriesMatching(RowIterator dataIterator, int colSize) throws IOException, NoNumberException {
        ArrayList<Double> timeList = new ArrayList<>();
        this.listMatching = new ArrayList<>();
        this.listTemp =  new ArrayList<ArrayList<Double>>();
        //利用listTemp初始化两个带比较的数列
        int cnt = 0;
        while (dataIterator.hasNextRow()) {

            Row row = dataIterator.next();
            for(int index=0 ; index<colSize; index++){
                if (cnt==0){
                    ArrayList<Double> valueList = new ArrayList<Double> ();
                    listTemp.add(valueList);
                }
                Double v = Util.getValueAsDouble(row, index);
                if (Double.isFinite(v)) {
                    double t = Long.valueOf(row.getTime()).doubleValue();
                    timeList.add(t);
                    listTemp.get(index).add(v);
                }
            }
            cnt++;
        }

    }


    public void initXAndY(int colIndex) {
        int i;
        X = this.listTemp.get(colIndex);
        double dtw;
        this.minDtw = Double.MAX_VALUE;
        this.minDex = 0;
        //第一行作为模板
        for (int j = 0; j < this.listTemp.size(); j++) {
            if(j==colIndex)
                continue;
            dtw = 0;
            Y = this.listTemp.get(j);

            dtw = getDtwDist();
            if (dtw < this.minDtw) {
                this.minDtw = dtw;
                this.minDex = j;
            }


        }
        /*	System.out.print(minDex);*/
        this.listMatching.add((int) this.minDex);
        System.out.print(MessageFormat.format("匹配程度最高的序号为{0}，距离为{1}", this.minDex, this.minDtw));
        System.out.print("-------");

        for (int l = 0; l < this.listTemp.get((int) this.minDex).size(); l++) {
            System.out.print(this.listTemp.get((int) this.minDex).get(l) + "---");
        }


    }

    private double computeDistance(double x, double y) {
        return Math.sqrt((x - y) * (x - y));

    }

    private void initDistance() {//先初始化点与点之间的距离
        distance = new double[X.size()][Y.size()];
        for (int i = 0; i < X.size(); i++) {
            for (int j = 0; j < Y.size(); j++) {
                distance[i][j] = computeDistance(X.get(i), Y.get(j));

            }
        }


    }

    private void computeDtw() {//初始化dtw数组
        dtw = new double[X.size()][Y.size()];
        initDistance();
        //根据distance数组来初始化dtw数组
        dtw[0][0] = 0;
        for (int i = 0; i < X.size(); i++) {
            for (int j = 0; j < Y.size(); j++) {
                //这里要对i,j进行判定，其实就是加入边界值的考虑
                if (i > 00 && j > 0) {
                    dtw[i][j] = minDist(dtw[i][j - 1] + distance[i][j], dtw[i - 1][j] + distance[i][j], dtw[i - 1][j - 1] + 2 * distance[i][j]);
                } else if (i == 0 && j > 0) {
                    dtw[i][j] = dtw[i][j - 1] + distance[i][j];
                } else if (i > 0 && j == 0) {
                    dtw[i][j] = dtw[i - 1][j] + distance[i][j];
                } else {
                    dtw[i][j] = 0;
                }

            }
        }

    }

    public double getDtwDist() {
        computeDtw();
        return dtw[X.size() - 1][Y.size() - 1];

    }

    private double minDist(double dist1, double dist2, double dist3) {
        return (dist1 < dist2 ? (dist2 < dist3 ? dist3 : (Math.min(dist1, dist3))) : (Math.min(dist2, dist3)));

    }

    public double getMinDtw() {
        return minDtw;
    }

    public double getMinDex() {
        return minDex;
    }
}