(window.webpackJsonp=window.webpackJsonp||[]).push([[165],{535:function(t,s,a){"use strict";a.r(s);var n=a(45),e=Object(n.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"patternsymmetric"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#patternsymmetric"}},[t._v("#")]),t._v(" PatternSymmetric")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数用于寻找序列中所有对称度小于阈值的对称子序列。对称度通过DTW计算，值越小代表序列对称性越高。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" PATTERNSYMMETRIC")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持一个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。")]),t._v(" "),a("p",[a("strong",[t._v("参数：")])]),t._v(" "),a("ul",[a("li",[a("code",[t._v("window")]),t._v("：对称子序列的长度，是一个正整数，默认值为10。")]),t._v(" "),a("li",[a("code",[t._v("threshold")]),t._v("：对称度阈值，是一个非负数，只有对称度小于等于该值的对称子序列才会被输出。在缺省情况下，所有的子序列都会被输出。")])]),t._v(" "),a("p",[a("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为DOUBLE。序列中的每一个数据点对应于一个对称子序列，时间戳为子序列的起始时刻，值为对称度。")]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s4|\n+-----------------------------+---------------+\n|2021-01-01T12:00:00.000+08:00|            1.0|\n|2021-01-01T12:00:01.000+08:00|            2.0|\n|2021-01-01T12:00:02.000+08:00|            3.0|\n|2021-01-01T12:00:03.000+08:00|            2.0|\n|2021-01-01T12:00:04.000+08:00|            1.0|\n|2021-01-01T12:00:05.000+08:00|            1.0|\n|2021-01-01T12:00:06.000+08:00|            1.0|\n|2021-01-01T12:00:07.000+08:00|            1.0|\n|2021-01-01T12:00:08.000+08:00|            2.0|\n|2021-01-01T12:00:09.000+08:00|            3.0|\n|2021-01-01T12:00:10.000+08:00|            2.0|\n|2021-01-01T12:00:11.000+08:00|            1.0|\n+-----------------------------+---------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" patternsymmetric"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s4"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'window'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'5'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'threshold'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'0'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v('+-----------------------------+----------------------------------------------------------------+\n|                         Time|patternsymmetric(root.test.d1.s4, "window"="5", "threshold"="0")|\n+-----------------------------+----------------------------------------------------------------+\n|2021-01-01T12:00:00.000+08:00|                                                             0.0|\n|2021-01-01T12:00:07.000+08:00|                                                             0.0|\n+-----------------------------+----------------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=e.exports}}]);