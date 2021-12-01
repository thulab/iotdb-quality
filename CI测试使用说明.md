# CI测试脚本使用说明

## 1.Dockerfile使用

1.将需要执行测试的文件使用ADD命令添加至Docker镜像中。

2.使用RUN命令执行Docker镜像中的命令行操作，不要使用多个RUN语句。

3.使用EXPOSE命令确定使用端口。

4.使用ENV命令编辑环境变量。

5.ENTRYPOINGT命令自动启动iotdb，无需修改。

## 2.测试脚本test.sh使用

1.确定Docker镜像和容器的别名，需与其他人的使用的名称区分。

2.使用bash执行测试脚本。可以预先将java文件放入Docker镜像的方式执行java脚本。

3.测试完成后删除Docker容器及镜像。

