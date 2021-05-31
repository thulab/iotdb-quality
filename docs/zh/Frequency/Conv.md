# Conv(TODO)

## 函数简介
本函数对两个输入序列进行卷积。

**函数名：** CONV

**输入序列：** 仅支持两个输入序列，类型均为 INT32 / INT64 / FLOAT / DOUBLE

**输出序列：** 输出单个序列，类型为DOUBLE，它是两个序列卷积的结果。序列的时间戳从0开始，仅用于表示顺序。

**提示：** 输入序列中的`NaN`将被忽略。

## 使用示例