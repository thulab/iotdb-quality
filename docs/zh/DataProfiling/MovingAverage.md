# MovingAverage

## 函数简介

本函数计算序列的移动平均。

**函数名：** MOVINGAVERAGE

**输入序列：** 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。

**参数：**

+ `n`：移动窗口的长度。

**输出序列**：输出单个序列，类型为DOUBLE。

## 使用示例

### 全数据计算

输入序列：

```

```

用于查询的SQL语句：

```sql
select movingaverage() from root.test
```

输出序列：

```

```

