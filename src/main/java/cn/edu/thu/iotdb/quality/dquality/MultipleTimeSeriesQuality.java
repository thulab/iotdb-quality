package cn.edu.thu.iotdb.quality.dquality;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.util.Scanner;

public class MultipleTimeSeriesQuality {
    public static final int WINDOWSIZE = 10;
    private boolean downtime = true;//是否考虑停机异常
    private int cnt = 0;//数据点总数
    private int inaccurateNum = 0;

    public MultipleTimeSeriesQuality(RowIterator dataIterator, int colCnt, String constraints) throws Exception {
        // avoid warnings
        System.setProperty("nashorn.args","--no-deprecation-warning");

        // script engine
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("js");

        while (dataIterator.hasNextRow()) {
            Row row = dataIterator.next();
            cnt++;
            String temp_constraints = constraints;
            boolean isFinite = true;
            for (int i = 0; i < colCnt; i++) {
                double v = Util.getValueAsDouble(row, i);
                if (Double.isFinite(v)) {
                    temp_constraints = temp_constraints.replace("$" + (i+1), String.valueOf(v));
                } else {
                    isFinite = false;
                    break;
                }
            }
            if (!isFinite || !String.valueOf(scriptEngine.eval(temp_constraints)).equals("true")) {
                inaccurateNum++;
            }
        }
    }

    public MultipleTimeSeriesQuality(String filename, int colCnt, String constraints) throws Exception {
        // avoid warnings
        System.setProperty("nashorn.args","--no-deprecation-warning");

        // script engine
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("js");

        Scanner sc = new Scanner(new File(filename));
        sc.useDelimiter("\\s*(,|\\r|\\n)\\s*");//设置分隔符，以逗号或回车分隔，前后可以有若干个空白符
        sc.nextLine();
        while (sc.hasNext()) {
            cnt++;
            sc.next();
            String temp_constraints = constraints;
            boolean isFinite = true;
            for (int i = 0; i < colCnt; i++) {
                if (isFinite && sc.hasNextDouble()) {
                    double v = sc.nextDouble();
                    System.out.println(v);
                    temp_constraints = temp_constraints.replace("$" + (i+1), String.valueOf(v));
                } else {
                    isFinite = false;
                    sc.next();
                }
            }

            if (!isFinite || !String.valueOf(scriptEngine.eval(temp_constraints)).equals("true")) {
                inaccurateNum++;
            }
        }
    }

    public double getAccuracy() {
        return 1 - inaccurateNum * 1.0 / cnt;
    }

    public static void main(String[] args) throws Exception {
        long stime = System.currentTimeMillis();
        MultipleTimeSeriesQuality tsq = new MultipleTimeSeriesQuality("temp_accuracy2.csv", 3, "$1 + $2 == $3 + -1 + 1");
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.printf("执行时长：%d 毫秒.\n", (etime - stime));
        System.out.println(tsq.getAccuracy());
    }

    /**
     * @return the downtime
     */
    public boolean isDowntime() {
        return downtime;
    }

    /**
     * @param downtime the downtime to set
     */
    public void setDowntime(boolean downtime) {
        this.downtime = downtime;
    }
}
