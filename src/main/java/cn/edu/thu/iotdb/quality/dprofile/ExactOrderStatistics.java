package cn.edu.thu.iotdb.quality.dprofile;

import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ExactOrderStatistics {
    public static double getMedian(FloatArrayList nums) {
        try {
            return nums.median();
        }catch (ArithmeticException e){
            throw new NoSuchElementException();
        }
    }

    public static double getMad(FloatArrayList nums) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            float[] sortedArray = nums.toSortedArray();
            int middleIndex = sortedArray.length >> 1;
            float median = sortedArray[middleIndex];
            if ((sortedArray.length & 1) == 0) {
                median = (median + sortedArray[middleIndex - 1]) / 2.0f;
            }
            for(int i = 0; i < sortedArray.length; ++i){
                sortedArray[i] = Math.abs(sortedArray[i] - median);
            }
            Arrays.sort(sortedArray);
            median = sortedArray[middleIndex];
            if ((sortedArray.length & 1) == 0) {
                median = (median + sortedArray[middleIndex - 1]) / 2.0f;
            }
            return median;
        }
    }

    public static double getPercentile(FloatArrayList nums, double phi) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            float[] sortedArray = nums.toSortedArray();
            int stop = (int)(Math.ceil(sortedArray.length * phi) - 1);
            return sortedArray[stop];
        }
    }

    public static double getMedian(DoubleArrayList nums) {
        try {
            return nums.median();
        }catch (ArithmeticException e){
            throw new NoSuchElementException();
        }
    }

    public static double getMad(DoubleArrayList nums) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            double[] sortedArray = nums.toSortedArray();
            int middleIndex = sortedArray.length >> 1;
            double median = sortedArray[middleIndex];
            if ((sortedArray.length & 1) == 0) {
                median = (median + sortedArray[middleIndex - 1]) / 2.0D;
            }
            double finalMedian = median;
            return Arrays.stream(sortedArray)
                    .map(x->Math.abs(x - finalMedian))
                    .sorted()
                    .skip((sortedArray.length - 1) >> 1)
                    .limit(2 - (sortedArray.length & 1))
                    .average()
                    .getAsDouble();
        }
    }

    public static double getPercentile(DoubleArrayList nums, double phi) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            double[] sortedArray = nums.toSortedArray();
            int stop = (int)(Math.ceil(sortedArray.length * phi) - 1);
            return sortedArray[stop];
        }
    }

    public static double getMedian(IntArrayList nums) {
        try {
            return nums.median();
        }catch (ArithmeticException e){
            throw new NoSuchElementException();
        }
    }

    public static double getMad(IntArrayList nums) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            int[] sortedArray = nums.toSortedArray();
            int middleIndex = sortedArray.length >> 1;
            double median = sortedArray[middleIndex];
            if ((sortedArray.length & 1) == 0) {
                median = (median + sortedArray[middleIndex - 1]) / 2.0D;
            }
            double finalMedian = median;
            return Arrays.stream(sortedArray)
                    .mapToDouble(x->Math.abs(x - finalMedian))
                    .sorted()
                    .skip((sortedArray.length - 1) >> 1)
                    .limit(2 - (sortedArray.length & 1))
                    .average()
                    .getAsDouble();
        }
    }

    public static double getPercentile(IntArrayList nums, double phi) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            int[] sortedArray = nums.toSortedArray();
            int stop = (int)(Math.ceil(sortedArray.length * phi) - 1);
            return sortedArray[stop];
        }
    }

    public static double getMedian(LongArrayList nums) {
        try {
            return nums.median();
        }catch (ArithmeticException e){
            throw new NoSuchElementException();
        }
    }

    public static double getMad(LongArrayList nums) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            long[] sortedArray = nums.toSortedArray();
            int middleIndex = sortedArray.length >> 1;
            double median = sortedArray[middleIndex];
            if ((sortedArray.length & 1) == 0) {
                median = (median + sortedArray[middleIndex - 1]) / 2.0D;
            }
            double finalMedian = median;
            return Arrays.stream(sortedArray)
                    .mapToDouble(x->Math.abs(x - finalMedian))
                    .sorted()
                    .skip((sortedArray.length - 1) >> 1)
                    .limit(2 - (sortedArray.length & 1))
                    .average()
                    .getAsDouble();
        }
    }

    public static double getPercentile(LongArrayList nums, double phi) {
        if (nums.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            long[] sortedArray = nums.toSortedArray();
            int stop = (int)(Math.ceil(sortedArray.length * phi) - 1);
            return sortedArray[stop];
        }
    }
}