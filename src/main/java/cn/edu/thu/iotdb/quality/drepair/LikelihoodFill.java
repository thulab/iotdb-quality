package cn.edu.thu.iotdb.quality.drepair;

import org.apache.iotdb.db.query.udf.api.access.RowIterator;

import java.util.ArrayList;
import java.util.List;

public class LikelihoodFill extends ValueFill {

    private double stopErrorRatio = 0.0001;
    private int stopIteration = Integer.MAX_VALUE;//= Integer.MAX_VALUE;

    public LikelihoodFill(RowIterator dataIterator) throws Exception {
        super(dataIterator);
        //setParameters();
    }

    public LikelihoodFill(String filename) throws Exception {
        super(filename);
        //setParameters();
    }

    @Override
    public void fill() {
        // 标记缺失点
        List<Integer> repairIndexList = new ArrayList<>();
        List<Double> repairValueList = new ArrayList<>();
        // 线性插值初始化
        int previousNotNaN = -1;
        for (int i = 0; i < n; i++) {
            if (Double.isNaN(original[i])) {
                repairIndexList.add(i);
                repaired[i] = original[i];
            } else {
                if (previousNotNaN >= 0 && previousNotNaN + 1 != i) {
                    double delta = (original[i] - original[previousNotNaN]) / (time[i] - time[previousNotNaN]);
                    for (int j = previousNotNaN + 1; j < i; j++) {
                        repaired[j] = original[previousNotNaN] + delta * (time[j] - time[previousNotNaN]);
                        repairValueList.add(repaired[j]);
                    }
                }
                repaired[i] = original[i];
                previousNotNaN = i;
            }
        }
        // 迭代式更新填充值
        double errorRatio = 1;
        int iteration = 0;
        while (errorRatio > stopErrorRatio && iteration < stopIteration) {
            errorRatio = 0.0;
            // 从上次修复值中更新当前的最优修复值
            for (int i = 0; i < repairIndexList.size(); i++) {
                int currentIndex = repairIndexList.get(i);
                // 开头和结尾的连续缺失不做修复
                if (currentIndex == 0 || Double.isNaN(repaired[currentIndex - 1]) ||
                        currentIndex == n - 1 || Double.isNaN(repaired[currentIndex + 1])) {
                    continue;
                }
                double intervalPrev1 = time[currentIndex] - time[currentIndex - 1];
                double intervalPost1 = time[currentIndex + 1] - time[currentIndex];
                // 二次函数系数 - 前一点速度变化的贡献项
                double squareAPrev = 0.0, squareBPrev = 0.0;
                if (currentIndex >= 2 && !Double.isNaN(repaired[currentIndex - 2])) {
                    double intervalPrev2 = time[currentIndex - 2] - time[currentIndex - 1];
                    squareAPrev = 1.0 / (intervalPrev1 * intervalPrev1);
                    squareBPrev = 2.0 * repaired[currentIndex - 2] / (intervalPrev2 * intervalPrev1)
                            - 2.0 * (intervalPrev2 + intervalPrev1) * repaired[currentIndex - 1] / (intervalPrev2 * intervalPrev1 * intervalPrev1);
                }
                // 二次函数系数 - 当前点速度变化的贡献项
                double squareACurr = (intervalPrev1 + intervalPost1) * (intervalPrev1 + intervalPost1) / (intervalPrev1 * intervalPrev1 * intervalPost1 * intervalPost1);
                double squareBCurr = -2.0 * (intervalPrev1 + intervalPost1) * repaired[currentIndex - 1] / (intervalPrev1 * intervalPrev1 * intervalPost1)
                        - 2.0 * (intervalPrev1 + intervalPost1) * repaired[currentIndex + 1] / (intervalPrev1 * intervalPost1 * intervalPost1);
                // 二次函数系数 - 后一点速度变化的贡献项
                double squareAPost = 0.0, squareBPost = 0.0;
                if (currentIndex <= n - 3 && !Double.isNaN(repaired[currentIndex + 2])) {
                    double intervalPost2 = time[currentIndex + 2] - time[currentIndex + 1];
                    squareAPost = 1.0 / (intervalPost1 * intervalPost1);
                    squareBPost = 2.0 * repaired[currentIndex + 2] / (intervalPost1 * intervalPost2)
                            - 2.0 * (intervalPost1 + intervalPost2) * repaired[currentIndex + 1] / (intervalPost1 * intervalPost1 * intervalPost2);
                }
                // 二次函数求最小值
                repairValueList.set(i, -(squareBPrev + squareBCurr + squareBPost) / (2.0 * (squareAPrev + squareACurr + squareAPost)));
            }
            for (int i = 0; i < repairIndexList.size(); i++) {
                int currentIndex = repairIndexList.get(i);
                double previousRepair = repaired[currentIndex];
                double updatedRepair = repairValueList.get(i);
                double updatedRatio = Math.abs(updatedRepair-previousRepair)/previousRepair;
                errorRatio = Math.max(updatedRatio, errorRatio);
                repaired[currentIndex] = updatedRepair;
            }
            iteration++;
        }
    }

    public static void main(String[] args) throws Exception {
        LikelihoodFill likelihoodFill = new LikelihoodFill("temp.csv");
        likelihoodFill.fill();
        for (int i = 0; i < likelihoodFill.n; i++) {
            System.out.println(likelihoodFill.repaired[i]);
//            System.out.println(likelihoodFill.time[i] + " " + likelihoodFill.repaired[i]);
        }
    }
}
