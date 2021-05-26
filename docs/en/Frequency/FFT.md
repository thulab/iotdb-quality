# FFT(TODO)

## Usage
This function is used to calculate the fast Fourier transform (FFT) of a numerical series.

**Name:** FFT

**Input:** Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.

**Parameters:** 

+ `type`: The type of FFT, which is 'uniform' (by default) or 'nonuniform'. If the value is 'uniform', the timestamps will be ignored and all data points will be regarded as equidistant. Thus, the equidistant fast Fourier transform algorithm will be applied. If the value is 'nonuniform', the non-equidistant fast Fourier transform algorithm will be applied based on timestamps.
+ `result`: The result of FFT, which is 'real', 'imag', 'abs' or 'angle', corresponding to the real part, imaginary part, magnitude and phase angle. By default, the magnitude will be output. 

**Output:** Output a single series. The type is DOUBLE. The timestamps starting from 0 only indicate the order.

**Note:** `NaN` in the input series will be ignored. 

## Examples

