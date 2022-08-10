(window.webpackJsonp=window.webpackJsonp||[]).push([[231],{606:function(t,s,a){"use strict";a.r(s);var n=a(45),e=Object(n.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"dtw"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#dtw"}},[t._v("#")]),t._v(" Dtw")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数用于计算两列数值型数据的DTW距离。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" DTW")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持两个输入序列，类型均为 INT32 / INT64 / FLOAT / DOUBLE。")]),t._v(" "),a("p",[a("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为DOUBLE。序列仅包含一个时间戳为0、值为两个时间序列的DTW距离值。")]),t._v(" "),a("p",[a("strong",[t._v("提示：")])]),t._v(" "),a("ul",[a("li",[t._v("如果某行数据中包含空值、缺失值或"),a("code",[t._v("NaN")]),t._v("，该行数据将会被忽略；")]),t._v(" "),a("li",[t._v("如果数据中所有的行都被忽略，函数将会输出0。")])]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+---------------+\n|                         Time|root.test.d2.s1|root.test.d2.s2|\n+-----------------------------+---------------+---------------+\n|1970-01-01T08:00:00.001+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.002+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.003+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.004+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.005+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.006+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.007+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.008+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.009+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.010+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.011+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.012+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.013+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.014+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.015+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.016+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.017+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.018+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.019+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.020+08:00|            1.0|            2.0|\n+-----------------------------+---------------+---------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" dtw"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("s2"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d2\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+-------------------------------------+\n|                         Time|dtw(root.test.d2.s1, root.test.d2.s2)|\n+-----------------------------+-------------------------------------+\n|1970-01-01T08:00:00.000+08:00|                                 20.0|\n+-----------------------------+-------------------------------------+\n")])])])])}),[],!1,null,null,null);s.default=e.exports}}]);