package cn.edu.thu.dquality.udf.SeriesMatching;

import java.util.ArrayList;
import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args) {
		ArrayList<Double> x= new ArrayList<>();
		for (int i = 1 ; i < 5 ; i++){
		    x.add(i*1.1);
		}
        ArrayList<Double> y= new ArrayList<>();
        for (int i = 1 ; i < 5 ; i++){
            y.add((double) i*11);
        }
        ArrayList<Double> z= new ArrayList<>();
        for (int i = 0 ; i < 5 ; i++){
            z.add((double) i+2);
        }
        ArrayList<ArrayList<Double>> listTemp = new ArrayList<>();
        listTemp.add(x);
        listTemp.add(y);
        listTemp.add(z);
//		DTW_Tool tool = new DTW_Tool(x,y);
        SeriesMatching tool = new SeriesMatching(listTemp);

        tool.initXAndY(0);
        tool.initXAndY(1);
        tool.initXAndY(2);
        int sumMatched = 0;
        for (int colIndex =0; colIndex< 3 ; colIndex++){
            ArrayList<Integer> listMatching = tool.listMatching;
            if (listMatching.get(listMatching.get(colIndex))==colIndex){
                System.out.println(colIndex + "matched");
                String output = colIndex+"->match->"+listMatching.get(colIndex);
                System.out.println(output);
                System.out.println(sumMatched);
                System.out.println("putString");
                listMatching.set(colIndex, -1);
                sumMatched += 1;
            }
            else
                System.out.println("not matched");
        }
        if (sumMatched == 0){
            System.out.println("not matched Column");
        }
        System.out.println(tool.getMinDex());
        System.out.println(tool.getMinDtw());
//        tool = new SeriesMatching(x, z);
//        System.out.println(tool.getDtwDist());
//        tool.initXAndY();
//        System.out.print(tool.getDtwDist());
    }
}
