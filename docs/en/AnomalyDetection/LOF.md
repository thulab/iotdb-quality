# LOF

## Usage

This function is used to detect density anomaly of time series. According to k-th distance calculation parameter and local outlier factor (lof) threshold, the function judges if a set of input values is an density anomaly, and a new time series of anomaly point serial number will be output.

**Name:** LOF

**Input Series:** Multiple input series. The type is INT32 / INT64 / FLOAT / DOUBLE.

+ `k`:use the k-th distance to calculate lof.
+  `threshold`:sets of values of lof larger than this threshold will be recognized as outlier. Lof larger than 1 indicates density near the set of value is below nearby sets, which is likely to be an outlier.

**Output Series:** Output a single series. The type is INT32.

**Note:** Combination of a set of values from different input series does not come from comparison of timestamps. Instead, the 1st, 2nd, 3rd... values from different series will be combined as sets of values. Ending part of longer input series will be ignored. The serial number of sets starts from 0.

## Examples

### Assigning k







### Assigning k and threshold

