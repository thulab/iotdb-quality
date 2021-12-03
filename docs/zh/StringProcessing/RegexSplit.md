# RegexSplit

## 函数简介

本函数用于使用给定的正则表达式切分文本，并返回指定的项。

**函数名：** REGEXSPLIT

**输入序列：** 仅支持单个输入序列，类型为 TEXT。

**参数：**

+ `regex`: 用于分割文本的正则表达式，支持所有Java正则表达式语法，比如`['"]`将会匹配任意的英文引号`'`和`"`。
+ `index`: 输出结果在切分后数组中的序号，需要是大于等于-1的整数，默认值为-1表示返回切分后数组的长度，其它非负整数即表示返回数组中对应位置的切分结果（数组的秩从0开始计数）。

**输出序列：** 输出单个序列，在`index`为-1时输出数据类型为INT32，否则为TEXT。

**提示：** 如果`index`超出了切分后结果数组的秩范围，例如使用`,`切分`0,1,2`时输入`index`为3，则该数据点没有输出结果。

## 使用示例


输入序列：

```
+-----------------------------+---------------+
|                         Time|root.test.d1.s1|
+-----------------------------+---------------+
|2021-01-01T00:00:01.000+08:00|      A,B,A+,B-|
|2021-01-01T00:00:02.000+08:00|      A,A+,A,B+|
|2021-01-01T00:00:03.000+08:00|         B+,B,B|
|2021-01-01T00:00:04.000+08:00|      A+,A,A+,A|
|2021-01-01T00:00:05.000+08:00|       A,B-,B,B|
+-----------------------------+---------------+
```

用于查询的SQL语句：

```sql
select regexsplit(s1, "regex"=",", "index"="-1") from root.test.d1
```

输出序列：

```
+-----------------------------+------------------------------------------------------+
|                         Time|regexsplit(root.test.d1.s1, "regex"=",", "index"="-1")|
+-----------------------------+------------------------------------------------------+
|2021-01-01T00:00:01.000+08:00|                                                     4|
|2021-01-01T00:00:02.000+08:00|                                                     4|
|2021-01-01T00:00:03.000+08:00|                                                     3|
|2021-01-01T00:00:04.000+08:00|                                                     4|
|2021-01-01T00:00:05.000+08:00|                                                     4|
+-----------------------------+------------------------------------------------------+
```

另一个查询的SQL语句：

```sql
select regexsplit(s1, "regex"=",", "index"="3") from root.test.d1
```

输出序列：

```
+-----------------------------+-----------------------------------------------------+
|                         Time|regexsplit(root.test.d1.s1, "regex"=",", "index"="3")|
+-----------------------------+-----------------------------------------------------+
|2021-01-01T00:00:01.000+08:00|                                                   B-|
|2021-01-01T00:00:02.000+08:00|                                                   B+|
|2021-01-01T00:00:04.000+08:00|                                                    A|
|2021-01-01T00:00:05.000+08:00|                                                    B|
+-----------------------------+-----------------------------------------------------+
```