# Deconv(TODO)

## Usage
This function is used to calculate the deconvolution, i.e. polynomial division.

**Name:** DECONV

**Input:** Only support two input series. The types are both INT32 / INT64 / FLOAT / DOUBLE.

**Parameters:** 

+ `result`：The result of deconvolution, which is 'quotient' or 'remainder'. By default, the quotient will be output. 

**Output:** Output a single series. The type is DOUBLE. It is the result of deconvolving the second series from the first series (dividing the first series by the second series) whose timestamps starting from 0 only indicate the order.

**Note:** `NaN` in the input series will be ignored. 

## Examples
