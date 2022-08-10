(window.webpackJsonp=window.webpackJsonp||[]).push([[247],{616:function(t,n,s){"use strict";s.r(n);var a=s(45),e=Object(a.a)({},(function(){var t=this,n=t.$createElement,s=t._self._c||n;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"mvavg"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#mvavg"}},[t._v("#")]),t._v(" MvAvg")]),t._v(" "),s("h2",{attrs:{id:"函数简介"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),s("p",[t._v("本函数计算序列的移动平均。")]),t._v(" "),s("p",[s("strong",[t._v("函数名：")]),t._v(" MVAVG")]),t._v(" "),s("p",[s("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。")]),t._v(" "),s("p",[s("strong",[t._v("参数：")])]),t._v(" "),s("ul",[s("li",[s("code",[t._v("window")]),t._v("：移动窗口的长度。默认值为10.")])]),t._v(" "),s("p",[s("strong",[t._v("输出序列")]),t._v("：输出单个序列，类型为DOUBLE。")]),t._v(" "),s("h2",{attrs:{id:"使用示例"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),s("h3",{attrs:{id:"指定窗口长度"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#指定窗口长度"}},[t._v("#")]),t._v(" 指定窗口长度")]),t._v(" "),s("p",[t._v("输入序列：")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+------------+\n|                         Time|root.test.s1|\n+-----------------------------+------------+\n|1970-01-01T08:00:00.100+08:00|         0.0|\n|1970-01-01T08:00:00.200+08:00|         0.0|\n|1970-01-01T08:00:00.300+08:00|         1.0|\n|1970-01-01T08:00:00.400+08:00|        -1.0|\n|1970-01-01T08:00:00.500+08:00|         0.0|\n|1970-01-01T08:00:00.600+08:00|         0.0|\n|1970-01-01T08:00:00.700+08:00|        -2.0|\n|1970-01-01T08:00:00.800+08:00|         2.0|\n|1970-01-01T08:00:00.900+08:00|         0.0|\n|1970-01-01T08:00:01.000+08:00|         0.0|\n|1970-01-01T08:00:01.100+08:00|         1.0|\n|1970-01-01T08:00:01.200+08:00|        -1.0|\n|1970-01-01T08:00:01.300+08:00|        -1.0|\n|1970-01-01T08:00:01.400+08:00|         1.0|\n|1970-01-01T08:00:01.500+08:00|         0.0|\n|1970-01-01T08:00:01.600+08:00|         0.0|\n|1970-01-01T08:00:01.700+08:00|        10.0|\n|1970-01-01T08:00:01.800+08:00|         2.0|\n|1970-01-01T08:00:01.900+08:00|        -2.0|\n|1970-01-01T08:00:02.000+08:00|         0.0|\n+-----------------------------+------------+\n")])])]),s("p",[t._v("用于查询的SQL语句：")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" mvavg"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token string"}},[t._v('"window"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v('"3"')]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test\n")])])]),s("p",[t._v("输出序列：")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v('+-----------------------------+---------------------------------+\n|                         Time|mvavg(root.test.s1, "window"="3")|\n+-----------------------------+---------------------------------+\n|1970-01-01T08:00:00.300+08:00|               0.3333333333333333|\n|1970-01-01T08:00:00.400+08:00|                              0.0|\n|1970-01-01T08:00:00.500+08:00|              -0.3333333333333333|\n|1970-01-01T08:00:00.600+08:00|                              0.0|\n|1970-01-01T08:00:00.700+08:00|              -0.6666666666666666|\n|1970-01-01T08:00:00.800+08:00|                              0.0|\n|1970-01-01T08:00:00.900+08:00|               0.6666666666666666|\n|1970-01-01T08:00:01.000+08:00|                              0.0|\n|1970-01-01T08:00:01.100+08:00|               0.3333333333333333|\n|1970-01-01T08:00:01.200+08:00|                              0.0|\n|1970-01-01T08:00:01.300+08:00|              -0.6666666666666666|\n|1970-01-01T08:00:01.400+08:00|                              0.0|\n|1970-01-01T08:00:01.500+08:00|               0.3333333333333333|\n|1970-01-01T08:00:01.600+08:00|                              0.0|\n|1970-01-01T08:00:01.700+08:00|               3.3333333333333335|\n|1970-01-01T08:00:01.800+08:00|                              4.0|\n|1970-01-01T08:00:01.900+08:00|                              0.0|\n|1970-01-01T08:00:02.000+08:00|              -0.6666666666666666|\n+-----------------------------+---------------------------------+\n')])])])])}),[],!1,null,null,null);n.default=e.exports}}]);