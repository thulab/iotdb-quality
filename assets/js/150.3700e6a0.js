(window.webpackJsonp=window.webpackJsonp||[]).push([[150],{524:function(t,s,a){"use strict";a.r(s);var n=a(45),e=Object(n.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"histogram"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#histogram"}},[t._v("#")]),t._v(" Histogram")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数用于计算单列数值型数据的分布直方图。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" HISTOGRAM")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。")]),t._v(" "),a("p",[a("strong",[t._v("参数：")])]),t._v(" "),a("ul",[a("li",[a("code",[t._v("start")]),t._v("：表示所求数据范围的下限，默认值为-Double.MAX_VALUE。")]),t._v(" "),a("li",[a("code",[t._v("end")]),t._v("：表示所求数据范围的上限，默认值为Double.MAX_VALUE，"),a("code",[t._v("start")]),t._v("的值必须小于或等于"),a("code",[t._v("end")]),t._v("。")]),t._v(" "),a("li",[a("code",[t._v("count")]),t._v(": 表示直方图分桶的数量，默认值为1，其值必须为正整数。")])]),t._v(" "),a("p",[a("strong",[t._v("输出序列：")]),t._v(" 直方图分桶的值，其中第i个桶（从1开始计数）表示的数据范围下界为$start+ (i-1)\\cdot\\frac{end-start}{count}$，数据范围上界为$start+ i \\cdot \\frac{end-start}{count}$。")]),t._v(" "),a("p",[a("strong",[t._v("提示：")])]),t._v(" "),a("ul",[a("li",[t._v("如果某个数据点的数值小于"),a("code",[t._v("start")]),t._v("，它会被放入第1个桶；如果某个数据点的数值大于"),a("code",[t._v("end")]),t._v("，它会被放入最后1个桶。")]),t._v(" "),a("li",[t._v("数据中的空值、缺失值和"),a("code",[t._v("NaN")]),t._v("将会被忽略。")])]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:00.000+08:00|            1.0|\n|2020-01-01T00:00:01.000+08:00|            2.0|\n|2020-01-01T00:00:02.000+08:00|            3.0|\n|2020-01-01T00:00:03.000+08:00|            4.0|\n|2020-01-01T00:00:04.000+08:00|            5.0|\n|2020-01-01T00:00:05.000+08:00|            6.0|\n|2020-01-01T00:00:06.000+08:00|            7.0|\n|2020-01-01T00:00:07.000+08:00|            8.0|\n|2020-01-01T00:00:08.000+08:00|            9.0|\n|2020-01-01T00:00:09.000+08:00|           10.0|\n|2020-01-01T00:00:10.000+08:00|           11.0|\n|2020-01-01T00:00:11.000+08:00|           12.0|\n|2020-01-01T00:00:12.000+08:00|           13.0|\n|2020-01-01T00:00:13.000+08:00|           14.0|\n|2020-01-01T00:00:14.000+08:00|           15.0|\n|2020-01-01T00:00:15.000+08:00|           16.0|\n|2020-01-01T00:00:16.000+08:00|           17.0|\n|2020-01-01T00:00:17.000+08:00|           18.0|\n|2020-01-01T00:00:18.000+08:00|           19.0|\n|2020-01-01T00:00:19.000+08:00|           20.0|\n+-----------------------------+---------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" histogram"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"start"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"1"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"end"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"20"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"count"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"10"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v('+-----------------------------+-----------------------------------------------------------------+\n|                         Time|histogram(root.test.d1.s1, "start"="1", "end"="20", "count"="10")|\n+-----------------------------+-----------------------------------------------------------------+\n|1970-01-01T08:00:00.000+08:00|                                                                2|\n|1970-01-01T08:00:00.001+08:00|                                                                2|\n|1970-01-01T08:00:00.002+08:00|                                                                2|\n|1970-01-01T08:00:00.003+08:00|                                                                2|\n|1970-01-01T08:00:00.004+08:00|                                                                2|\n|1970-01-01T08:00:00.005+08:00|                                                                2|\n|1970-01-01T08:00:00.006+08:00|                                                                2|\n|1970-01-01T08:00:00.007+08:00|                                                                2|\n|1970-01-01T08:00:00.008+08:00|                                                                2|\n|1970-01-01T08:00:00.009+08:00|                                                                2|\n+-----------------------------+-----------------------------------------------------------------+\n')])])]),a("h3",{attrs:{id:"zeppelin示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#zeppelin示例"}},[t._v("#")]),t._v(" Zeppelin示例")]),t._v(" "),a("p",[t._v("链接: "),a("a",{attrs:{href:"http://101.6.15.213:18181/#/notebook/2GC1HE97R",target:"_blank",rel:"noopener noreferrer"}},[t._v("http://101.6.15.213:18181/#/notebook/2GC1HE97R"),a("OutboundLink")],1)])])}),[],!1,null,null,null);s.default=e.exports}}]);