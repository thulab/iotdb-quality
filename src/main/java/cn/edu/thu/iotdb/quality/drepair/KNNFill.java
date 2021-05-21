package cn.edu.thu.iotdb.quality.drepair;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.PrintWriter;

class SortNode implements Comparable{
    private double Distance;
    private int Index;

    public SortNode(double Distance, int Index){
        this.Distance = Distance;
        this.Index = Index;
    }

    public int getIndex(){
        return Index;
    }

    public double getDistance(){
        return  Distance;
    }

    @Override
    public int compareTo(Object o){
        SortNode s = (SortNode) o;
        if (this.Distance > s.Distance){
            return 1;
        }else{
            return -1;
        }
    }
}

public class KNNFill extends ValueFill {

    private final int nSize;
    private final int nFeature;
    private final String csvHeaders;
    private final String originalTime[];
    private final double original[][];
    private double repaired[][];
    private int K = 15;

    public KNNFill(String filename) throws Exception{
        super(filename);
        Scanner sc=new Scanner(new File(filename));
        SimpleDateFormat format = new SimpleDateFormat("y/M/d h:mm");
        sc.useDelimiter("\\s*(,|\\r|\\n)\\s*");//设置分隔符，以逗号或回车分隔，前后可以有若干个空白符
        csvHeaders=sc.nextLine();//读取csv表头
        nFeature=csvHeaders.split(",").length;//确定特征个数
        ArrayList<String> originalTimeList = new ArrayList<>();
        ArrayList<Double>[] originList = (ArrayList<Double>[])new ArrayList[nFeature];
        for (int i = 0; i < nFeature; i++)
            originList[i] = new ArrayList<Double>();
        while (sc.hasNext()){//读取数据
            String itemTime = sc.next();
            originalTimeList.add(itemTime);
            originList[0].add((double)format.parse(itemTime).getTime());
            for (int i = 1; i < nFeature; i++){
                Double v;
                try{
                    v = sc.nextDouble();
                } catch (InputMismatchException ex){ v = Double.NaN;}
                if (!Double.isFinite(v)) {//对空值的处理和特殊值的处理
                    originList[i].add(Double.NaN);
                } else {
                    originList[i].add(v);
                }
            }
        }

        //保存时间序列
        nSize = originalTimeList.size();
        originalTime = new String[nSize];
        for (int i = 0; i < nSize; i++)
            originalTime[i] = originalTimeList.get(i);
        original = new double[nSize][nFeature];
        for (int i = 0; i < nFeature; i++){
            for (int j = 0; j < nSize; j++)
                original[j][i] = originList[i].get(j);
        }

        repaired = new double[nSize][nFeature];
    }

    @Override
    public void fill(){
        //进行数据的标准化
        double FeatureCenter[] = new double[nFeature];
        double FeatureVariability[] = new double[nFeature];
        double Standardized[][] = new double[nSize][nFeature];
        for (int i = 0; i < nFeature; i++){
            int notNaNcount=0;
            double Sum=0, Sum2=0;
            for (int j = 0; j < nSize; j++)
                if (!Double.isNaN(original[j][i])){
                    notNaNcount++;
                    Sum += original[j][i];
                    Sum2 += original[j][i] * original[j][i];
                }
            //此处假设一列至少有一个未缺失的数据，这也是使用KNN填补必需的
            FeatureCenter[i] = Sum/notNaNcount;
            FeatureVariability[i] = Sum2/notNaNcount - FeatureCenter[i]*FeatureCenter[i];

            for (int j = 0; j < nSize; j++)
                Standardized[j][i] = (original[j][i] - FeatureCenter[i])/Math.sqrt(FeatureVariability[i]);
        }

        for (int i = 0; i < nSize; i++){
            //判断某行是否存在缺失值，确定是否需要计算改行与其余行的距离
            int imputeFlag = 0;
            for (int j = 0; j < nFeature; j++)
                if (Double.isNaN(Standardized[i][j]))
                    imputeFlag = 1;
            if (imputeFlag == 0)
                continue;//改行无需填补

            //计算第i行与剩余行的距离
            double Distance[] = new double[nSize];
            for (int j = 0; j < nSize; j++){
                if (i == j)
                    Distance[j] = Double.POSITIVE_INFINITY;
                else{
                    int unNaNpair = 0;
                    double totalSquredDiff = 0;
                    for (int k = 0; k < nFeature; k++)
                        if (!Double.isNaN(Standardized[i][k])&&!Double.isNaN(Standardized[j][k])) {
                            unNaNpair++;
                            totalSquredDiff+=(Standardized[i][k]-Standardized[j][k])*(Standardized[i][k]-Standardized[j][k]);
                        }
                    if (unNaNpair == 0){
                        Distance[j] = Double.POSITIVE_INFINITY;
                    }else{
                        Distance[j] = totalSquredDiff/unNaNpair;
                    }
                }
            }

            //将求得的距离排序
            ArrayList<SortNode> DistanceList = new ArrayList<>();
            for (int j = 0; j < nSize; j++)
                if (Double.isFinite(Distance[j]))
                    DistanceList.add(new SortNode(Distance[j],j));
            Collections.sort(DistanceList);

            //进行填补
            for (int j = 0; j < nFeature; j++)
                if (Double.isNaN(Standardized[i][j])){
                    int nodeFound = 0;
                    double sum = 0;
                    for (int k = 0; k < nSize; k++)
                        if (!Double.isNaN(Standardized[DistanceList.get(k).getIndex()][j])){
                            nodeFound++;
                            sum+=Standardized[DistanceList.get(k).getIndex()][j];
                            if (nodeFound == K)
                                break;//找到了K个合理的近邻
                        }
                    Standardized[i][j]= sum/nodeFound;
                }
        }

        //将填补结果保存到repaired
        for (int i = 0; i < nSize; i++)
            for (int j = 0 ; j < nFeature; j++)
                if (Double.isNaN(original[i][j]))
                    repaired[i][j] = Standardized[i][j] * Math.sqrt(FeatureVariability[j]) + FeatureCenter[j];
                else
                    repaired[i][j] = original[i][j];
    }

    @Override
    public double[] getFilled() { return repaired[0]; }

    //将填补后的数据保存至给定csv文件
//    public void outputToCSV(String filename) throws FileNotFoundException {
//        PrintWriter printWriter = new PrintWriter(new File(filename));
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(csvHeaders+"\n");
//        for (int i = 0; i < nSize; i++){
//            stringBuilder.append(originalTime[i]);
//            for (int j = 1; j < nFeature; j++) {
//                stringBuilder.append(",");
//                stringBuilder.append(repaired[i][j]);
//            }
//            stringBuilder.append("\n");
//        }
//        printWriter.write(stringBuilder.toString());
//        printWriter.close();
//    }
//
//    public static void main(String[] args) throws Exception{
//        KNNFill kNearestNeighbors = new KNearestNeighbors("beijing_history.csv");
//        kNearestNeighbors.fill();
//        kNearestNeighbors.outputToCSV("repaired.csv");
//    }
}