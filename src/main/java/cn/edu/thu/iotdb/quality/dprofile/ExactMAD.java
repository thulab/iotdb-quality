package cn.edu.thu.iotdb.quality.dprofile;

import cn.edu.thu.iotdb.quality.Util;
import org.apache.iotdb.db.query.udf.api.access.RowIterator;

import java.util.ArrayList;
import java.util.Arrays;

public class ExactMAD {
    public static double mad(RowIterator iterator) throws Exception {
        ArrayList<Double> nums = new ArrayList<>();
        while(iterator.hasNextRow()){
            Double value = Util.getValueAsDouble(iterator.next());
            if(value != null && !Double.isNaN(value)){
                nums.add(value);
            }
        }

        return mad(Util.toDoubleArray(nums));
    }

    public static double mad(RowIterator iterator, int size) throws Exception {
        double[] nums = new double[size];
        int index = 0;
        while(iterator.hasNextRow()){
            Double value = Util.getValueAsDouble(iterator.next());
            if(value != null && !Double.isNaN(value)){
                nums[index++] = value;
            }
        }
        return mad(nums);
    }

    private static double mad(double[] nums) {
        int rank = (int) Math.floor(0.5 * (nums.length - 1));
        Arrays.sort(nums);
        double median = nums[rank];
        for(int i = 0; i < nums.length; ++i){
            nums[i] = Math.abs(nums[i] - median);
        }
        Arrays.sort(nums);
        return nums[rank];
    }
}