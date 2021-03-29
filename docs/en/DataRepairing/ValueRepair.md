# ValueRepair

## Usage
This function is used to repair the value of the time series.
Currently, two methods are supported: 
**Screen** is a method based on speed threshold, which makes all speeds meet the threshold requirements under the premise of minimum changes; 
**LsGreedy** is a method based on speed change likelihood, which models speed changes as Gaussian distribution, and uses a greedy algorithm to maximize the likelihood.


**Name:** VALUEREPAIR

**Input Series:** Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.

**Parameters:**

+ `method`: The method used to repair, which is 'Screen' or 'LsGreedy'. By default, Screen is used.
+ `minSpeed`: This parameter is only valid with Screen. It is the speed threshold. Speeds below it will be regarded as outliers. By default, it is the median minus 3 times of median absolute deviation.
+ `maxSpeed`: This parameter is only valid with Screen. It is the speed threshold. Speeds above it will be regarded as outliers. By default, it is the median plus 3 times of median absolute deviation.
+ `center`: This parameter is only valid with LsGreedy. It is the center of the Gaussian distribution of speed changes. By default, it is 0. 
+ `sigma`: This parameter is only valid with LsGreedy. It is the standard deviation of the Gaussian distribution of speed changes. By default, it is the median absolute deviation. 

**Output Series:** Output a single series. The type is the same as the input. This series is the input after repairing.

**Note:** `NaN` will be filled with linear interpolation before repairing.

## Examples

### Repair with Screen
### Repair with LsGreedy