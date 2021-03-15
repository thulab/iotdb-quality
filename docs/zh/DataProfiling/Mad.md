# Mad

## 函数简介

本函数用于计算单列数值型数据的近似绝对中位差，绝对中位差为所有数值与其中位数绝对偏移量的中位数，

如有数据集{1,3,3,5,5,6,7,8,9}，其中位数为5，所有数值与中位数的偏移量的绝对值为{0,0,1,2,2,2,3,4,4}，其中位数为2，故而原数据集的绝对中位差为2。

**函数名：** MAD

**输入序列：** 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE

**参数：**

+ `error`：近似绝对中位差的基于数值的误差百分比，如且`error`=0.01，即精确绝对中位差为a，近似绝对中位差为b，则必然有以下不等式成立：
  $$
  0.99a \leq b \leq 1.01a
  $$

**输出序列：** 近似绝对中位差
