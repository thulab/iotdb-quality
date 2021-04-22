# Fill(TODO)

## 函数简介

**函数名：** FILL

**输入序列：** 支持多维输入序列，类型为INT32 / INT64 / FLOAT / DOUBLE

**参数：**

+ `method`: “mean"指使用均值方法；“median”使用中值填补；"previous"指使用前值方法；“MICE"使用multivariate imputation of chained equation方法填补；”ARIMA“使用回归滑动平均方法（默认）；"KNN"使用K近邻方法；“EM”使用期望最大化方法；
+ `regression`: 当method指定为mice时使用，“lr”/"linear"表示线性回归，“rf"指随机森林；其他方式待完成中

**输出序列：** 即修复后的多维序列。
