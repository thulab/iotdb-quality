# LOF

## Usage

This function is used to detect density anomaly of time series. According to k-th distance calculation parameter and local outlier factor (lof) threshold, the function judges if a set of input values is an density anomaly, and a bool mark of anomaly values will be output.

**Name:** LOF

**Input Series:** Multiple input series. The type is INT32 / INT64 / FLOAT / DOUBLE.

+ `k`:use the k-th distance to calculate lof.
+  `threshold`:sets of values of lof larger than this threshold will be recognized as outlier. Lof larger than 1 indicates density near the set of value is below nearby sets, which is likely to be an outlier.

**Output Series:** Output a single series. The type is BOOLEAN.

**Note:** Incomplete rows will be ignored. They are neither calculated nor marked as anomaly.

## Examples

### Assigning k







### Assigning k and threshold

