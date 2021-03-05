# 文档网站

## 脚本介绍

运行`preview.sh`可以在本地预览网站，运行`deploy.sh`可以将网站部署到远程的GitHub Pages。

## 依赖

+ Node.js
+ Vuepress

## 文档格式

- 所有的md都会被编译成html，REDEME.md编译为index.html，xx.md编译为xx.html
- md内标签必须有开头有结尾，比如\<tr>必须有\</tr>与之对应，而且是严格对应；如果文档中需要插入标签，比如List\<String>，可以加这个放入代码块中，也可以在**俩个**尖括号前加上\，如\\\<String\\\>
- 标签不能交叉嵌套，比如\<p>\<center>\</p>\</center>这是不允许的
- 文章的一级标题就是该文档对应sidebar的标题

## 贡献文档

- 修改已有文档：只需要对文档里的内容进行修改，不要修改文件名（修改文件名就需要到config.js修改）
- 增加文档：将增加的文档放在自己想放的目录，记录下目录的链接路径，到docs/.vuepress/config.js中修改



## 文档存储结构

文件的存储结构如下,zh和en应该一一对应，除了/zh/README.md在/en/中没有对应（它对应于根目录下的`README.md`）

```
docs
├─ en
|   ├─ DataQuality
|   ├─ DataProfiling
|   └─ etc.
└─ zh
    ├─ README.md
    ├─ DataQuality
    ├─ DataProfiling
    └─ etc.
```
## 修改config.js中的侧边栏

最前面的是跳转链接，children是补充该文档的跳转链接

**总的链接就是 该网站域名+指定的语言版本（/或/zh/）+sidebar的链接+children的链接**

```js
sidebar: {
    'sidebar的链接': [
        {
            title:'sidebar的标题',
            children: [
                'children的链接1',
                'children的链接2',
                'children的链接3'
            ]
        },
    ]
}
```

关于sidebar其他属性配置，见[vuepress教程](https://www.vuepress.cn/zh/theme/default-theme-config.html#%E4%BE%A7%E8%BE%B9%E6%A0%8F)。

