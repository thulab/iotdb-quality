/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import java.util.Arrays;
import java.util.Random;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.Row;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.RowByRowAccessStrategy;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;

/**
 * 使用蓄水池采样算法进行数据采样的UDTF
 *
 * @author Wang Haoyu
 */
public class UDTFSample implements UDTF {

    private int k;//采样数

    //下列变量在蓄水池采样法中使用
    private Pair<Long, Object>[] samples;//采样得到的样本
    private int num = 0;//计数器，记录当前一共有多少个元素
    private final Random random = new Random();

    @Override
    public void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception {
        //参数验证
        this.k = parameters.getIntOrDefault("k", 1);
        if (this.k <= 0) {
            throw new Exception("k should be a positive integer.");
        }
        //算法设置
        String method = parameters.getStringOrDefault("method", "reservoir");
        if ("isometric".equalsIgnoreCase(method)) {
            configurations.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
                    .setOutputDataType(parameters.getDataType(0));
        } else if ("reservoir".equalsIgnoreCase(method)) {
            configurations.setAccessStrategy(new RowByRowAccessStrategy())
                    .setOutputDataType(parameters.getDataType(0));
            this.samples = new Pair[this.k];
        } else {
            throw new Exception("Illegal sampling method.");
        }

    }

    @Override
    public void transform(Row row, PointCollector collector) throws Exception {
        //蓄水池采样法
        Object v = Util.getValueAsObject(row);
        Long t = row.getTime();
        if (v != null) {
            if (this.num < this.k) {
                this.samples[this.num] = Pair.of(t, v);
                this.num++;
            } else {
                this.num++;
                int x = random.nextInt(num);
                if (x < this.k) {
                    this.samples[x] = Pair.of(t, v);
                }
            }
        }
    }

    @Override
    public void transform(RowWindow rowWindow, PointCollector collector) throws Exception {
        //等距采样法
        int n = rowWindow.windowSize();
        if (this.k < n) {
            for (long i = 0; i < this.k; i++) {
                long j = Math.floorDiv(i * (long) n, (long) k);//防止中间数据超过int类型范围
                Row row = rowWindow.getRow((int) j);
                Util.putValue(collector, row.getTime(), Util.getValueAsObject(row));
            }
        } else {//采样数大于等于输入序列长度，输出所有元素
            RowIterator iterator = rowWindow.getRowIterator();
            while (iterator.hasNextRow()) {
                Row row = iterator.next();
                Util.putValue(collector, row.getTime(), Util.getValueAsObject(row));
            }
        }
    }

    @Override
    public void terminate(PointCollector pc) throws Exception {
        if (samples != null) {//仅用于蓄水池采样
            int m = Math.min(num, k);
            Arrays.sort(samples, 0, m);
            for (int i = 0; i < m; i++) {
                Pair<Long, Object> p = samples[i];
                Util.putValue(pc, p.getLeft(), p.getRight());
            }
        }
    }

}
