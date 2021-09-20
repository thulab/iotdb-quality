(window.webpackJsonp=window.webpackJsonp||[]).push([[125],{495:function(t,n,a){"use strict";a.r(n);var s=a(45),e=Object(s.a)({},(function(){var t=this,n=t.$createElement,a=t._self._c||n;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"minmax"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#minmax"}},[t._v("#")]),t._v(" MinMax")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数将输入序列使用min-max方法进行标准化。最小值归一至0，最大值归一至1.")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" MINMAX")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。")]),t._v(" "),a("p",[a("strong",[t._v("参数：")])]),t._v(" "),a("ul",[a("li",[a("code",[t._v("method")]),t._v('：若设置为"batch"，则将数据全部读入后转换；若设置为"stream"，则需用户提供最大值及最小值进行流式计算转换。默认为"batch"。')]),t._v(" "),a("li",[a("code",[t._v("min")]),t._v("：使用流式计算时的最小值。")]),t._v(" "),a("li",[a("code",[t._v("max")]),t._v("：使用流式计算时的最大值。")])]),t._v(" "),a("p",[a("strong",[t._v("输出序列")]),t._v("：输出单个序列，类型为DOUBLE。")]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("h3",{attrs:{id:"全数据计算"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#全数据计算"}},[t._v("#")]),t._v(" 全数据计算")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+------------+\n|                         Time|root.test.s1|\n+-----------------------------+------------+\n|1970-01-01T08:00:00.100+08:00|         0.0|\n|1970-01-01T08:00:00.200+08:00|         0.0|\n|1970-01-01T08:00:00.300+08:00|         1.0|\n|1970-01-01T08:00:00.400+08:00|        -1.0|\n|1970-01-01T08:00:00.500+08:00|         0.0|\n|1970-01-01T08:00:00.600+08:00|         0.0|\n|1970-01-01T08:00:00.700+08:00|        -2.0|\n|1970-01-01T08:00:00.800+08:00|         2.0|\n|1970-01-01T08:00:00.900+08:00|         0.0|\n|1970-01-01T08:00:01.000+08:00|         0.0|\n|1970-01-01T08:00:01.100+08:00|         1.0|\n|1970-01-01T08:00:01.200+08:00|        -1.0|\n|1970-01-01T08:00:01.300+08:00|        -1.0|\n|1970-01-01T08:00:01.400+08:00|         1.0|\n|1970-01-01T08:00:01.500+08:00|         0.0|\n|1970-01-01T08:00:01.600+08:00|         0.0|\n|1970-01-01T08:00:01.700+08:00|        10.0|\n|1970-01-01T08:00:01.800+08:00|         2.0|\n|1970-01-01T08:00:01.900+08:00|        -2.0|\n|1970-01-01T08:00:02.000+08:00|         0.0|\n+-----------------------------+------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" minmax"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+--------------------+\n|                         Time|minmax(root.test.s1)|\n+-----------------------------+--------------------+\n|1970-01-01T08:00:00.100+08:00| 0.16666666666666666|\n|1970-01-01T08:00:00.200+08:00| 0.16666666666666666|\n|1970-01-01T08:00:00.300+08:00|                0.25|\n|1970-01-01T08:00:00.400+08:00| 0.08333333333333333|\n|1970-01-01T08:00:00.500+08:00| 0.16666666666666666|\n|1970-01-01T08:00:00.600+08:00| 0.16666666666666666|\n|1970-01-01T08:00:00.700+08:00|                 0.0|\n|1970-01-01T08:00:00.800+08:00|  0.3333333333333333|\n|1970-01-01T08:00:00.900+08:00| 0.16666666666666666|\n|1970-01-01T08:00:01.000+08:00| 0.16666666666666666|\n|1970-01-01T08:00:01.100+08:00|                0.25|\n|1970-01-01T08:00:01.200+08:00| 0.08333333333333333|\n|1970-01-01T08:00:01.300+08:00| 0.08333333333333333|\n|1970-01-01T08:00:01.400+08:00|                0.25|\n|1970-01-01T08:00:01.500+08:00| 0.16666666666666666|\n|1970-01-01T08:00:01.600+08:00| 0.16666666666666666|\n|1970-01-01T08:00:01.700+08:00|                 1.0|\n|1970-01-01T08:00:01.800+08:00|  0.3333333333333333|\n|1970-01-01T08:00:01.900+08:00|                 0.0|\n|1970-01-01T08:00:02.000+08:00| 0.16666666666666666|\n+-----------------------------+--------------------+\n")])])])])}),[],!1,null,null,null);n.default=e.exports}}]);