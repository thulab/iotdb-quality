# UDF代码质量检查列表

## 一、依赖库检查

#### 1.无违反Apache license 2.0的依赖项

http://www.apache.org/legal/resolved.html#category-x

#### 2.所有Apache IoTDB依赖版本保持在0.13.0

#### 3.数学计算工具优先使用math3

## 二、函数设计检查

#### 1.UDF设计规范

##### i.接口规范

​	a)提供数值计算功能的函数，若结果数据类型与输入数据类型一致，则写入与输入数据相同数据类型的结果；计算结果为实数的，写入DOUBLE类型；其余写入类型依函数功能设计确定。

​	b)UDF参数均提供默认值，即使用`parameters.getDoubleOrDefalut()`等函数获得用户输入参数；

​	c)UDF参数使用小写英文单词或词组。

​	存在流式计算与块式计算选择的，统一使用`"compute"="batch"|"stream"`；

​	其它计算方法选择参数使用`"method"`；

​	窗口长度参数使用`"window"`；

​	单个阈值参数使用`"threshold"`。

##### ii.命名规范

​	UDF函数名应为标准英文术语，可以带有简写或缩写。聚合函数使用UDAF为前缀，转换函数使用UDTF为前缀。

##### iii.NaN处理

​	计算结果与NaN无关的函数应忽略输入中的NaN值。

#### 2.UDF开发规范

UDF说明文档

http://iotdb.apache.org/zh/UserGuide/Master/Advanced-Features/UDF-User-Defined-Function.html

##### i.函数顺序按照UDTF调用顺序排序

1. `void validate(UDFParameterValidator validator) throws Exception`
2. `void beforeStart(UDFParameters parameters, UDTFConfigurations configurations) throws Exception`
3. `void transform(Row row, PointCollector collector) throws Exception`或者`void transform(RowWindow rowWindow, PointCollector collector) throws Exception`
4. `void terminate(PointCollector collector) throws Exception`
5. `void beforeDestroy()`

##### ii. `validate()`

​	根据用户手册和设计文档，确保该函数验证数据类型合规。验证数值类型数据时，对非数值输入抛出`util.NoNumberException`。对不合法参数使用

```
validator.validate(
	x -> (int) x > 0,
	"window has to be greater than 0.",
	validator.getParameters().getIntOrDefault("window", 10))
```

形式抛出参数异常。

##### iii. `beforeStart()`

​	除获得用户参数外，初始化所有变量。UDF如遇错误退出，会保留此前的数据，再次调用时不会新建对象，因此需避免该情况下出现错误结果。

##### iv.`transform()`

​	取数需进行类型转换时，使用`util.Util`中封装的接口。

##### v. `beforeDestroy()`

​	如函数存在构造对象，应清除释放内存。

## 三、格式检查

#### 1.注释

​	使用如下模板：

```
/**
*@ClassName     ${NAME}
*@Description   TODO
*@Author
*@Version
*/
```

#### 2.命名

​	将拼音等命名修改为英文命名。

#### 3.编辑格式

​	将统一使用spotless与sonarcloud修改和检查。

## 四、结构规范检查

#### 1.测试内容

​	将目前代码中已有的测试函数全部转移至test文件夹下对应的类。

#### 2.为UDF编写的依赖项

​	暂时统一移动至对应package下的util文件夹中。部分类可能需要规范接口。
