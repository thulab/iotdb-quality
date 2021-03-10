# Readme

本工具用于项目的中英文文档的自动生成。

## 使用方法

1. 运行`convert.py`，将所有的markdown文档都自动转换成latex文件，中文文档存放在文件夹`output_zh`中，英文文档存放在文件夹`output_en`中；
2. 使用xelatex命令编译`UserManual_zh.tex`，可以自动引入转换得到的latex文件，生成中文文档（可以根据需要修改该文件）；同样的，编译`UserManual_en.tex`可以生成英文文档。

## 依赖环境

+ Python3
+ PyLatex库
+ Latex编译环境

## 说明

+ md2latex转换工具来自<https://github.com/sailist/MarkTex>，经过修改，解决了若干个bug，并对部分规则进行了改动。所以，请不要通过pip引入MarkTex库。
+ 本工具支持大部分markdown基础语法：
  + 支持markdown的三级标题，分别对应于section、subsection和subsubsection；
  + 支持文本加粗、倾斜、删除线、超链接等行内语法；
  + 支持表格；
  + 支持列表，包括有序列表和无序列表，但**不支持列表嵌套**；
  + 支持代码段，包括代码的高亮；
  + 支持图片、公式、引用等；
  + 为了防止转换出错，请不要使用过于复杂的语法🐶。
+ Latex模板名为ElegantBook，来自<https://github.com/ElegantLaTeX/ElegantBook/>，该模板同时提供了中英文选项。