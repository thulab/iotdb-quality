# Filter(TODO)

## Usage
This function is used to filter the input series by a rational transfer function, which is 
$$Y(z)=\frac{b(0)+b(1)z^{-1}+\cdots+b(n_b)z^{-n_b}}{a(0)+a(1)z^{-1}+\cdots+a(n_a)z^{-n_a}}X(z)$$

**Name:** FILTER

**Input:** Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.

**Parameters:** 

+ `numerator`: The numerator coefficients of the transfer function $a(0)..a(n_a)$. Coefficients are separated by commas.
+ `denominator`: The denominator coefficients of the transfer function $b(0)..b(n_b)$. Coefficients are separated by commas. 

**Output:** Output a single series. The type is DOUBLE. It is the filtered series whose length and timestamps are the same as the input.

**Note:** `NaN` in the input series will be ignored. 

## Examples