(window.webpackJsonp=window.webpackJsonp||[]).push([[118],{488:function(t,s,n){"use strict";n.r(s);var a=n(45),e=Object(a.a)({},(function(){var t=this,s=t.$createElement,n=t._self._c||s;return n("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[n("h1",{attrs:{id:"consecutivewindows"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#consecutivewindows"}},[t._v("#")]),t._v(" ConsecutiveWindows")]),t._v(" "),n("h2",{attrs:{id:"函数简介"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),n("p",[t._v("本函数用于在多维严格等间隔数据中发现指定长度的连续窗口。")]),t._v(" "),n("p",[t._v("严格等间隔数据是指数据的时间间隔是严格相等的，允许存在数据缺失（包括行缺失和值缺失），但不允许存在数据冗余和时间戳偏移。")]),t._v(" "),n("p",[t._v("连续窗口是指严格按照标准时间间隔等距排布，不存在任何数据缺失的子序列。")]),t._v(" "),n("p",[n("strong",[t._v("函数名：")]),t._v(" CONSECUTIVEWINDOWS")]),t._v(" "),n("p",[n("strong",[t._v("输入序列：")]),t._v(" 支持多个输入序列，类型可以是任意的，但要满足严格等间隔的要求。")]),t._v(" "),n("p",[n("strong",[t._v("参数：")])]),t._v(" "),n("ul",[n("li",[n("code",[t._v("gap")]),t._v("：标准时间间隔，是一个有单位的正数。目前支持五种单位，分别是'ms'（毫秒）、's'（秒）、'm'（分钟）、'h'（小时）和'd'（天）。在缺省情况下，函数会利用众数估计标准时间间隔。")]),t._v(" "),n("li",[n("code",[t._v("length")]),t._v("：序列长度，是一个有单位的正数。目前支持五种单位，分别是'ms'（毫秒）、's'（秒）、'm'（分钟）、'h'（小时）和'd'（天）。该参数不允许缺省。")])]),t._v(" "),n("p",[n("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为INT32。输出序列中的每一个数据点对应一个指定长度连续子序列，时间戳为子序列的起始时刻，值为子序列包含的数据点个数。")]),t._v(" "),n("p",[n("strong",[t._v("提示：")]),t._v(" 对于不符合要求的输入，本函数不对输出做任何保证。")]),t._v(" "),n("h2",{attrs:{id:"使用示例"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),n("p",[t._v("输入序列：")]),t._v(" "),n("div",{staticClass:"language- extra-class"},[n("pre",{pre:!0,attrs:{class:"language-text"}},[n("code",[t._v("+-----------------------------+---------------+---------------+\n|                         Time|root.test.d1.s1|root.test.d1.s2|\n+-----------------------------+---------------+---------------+\n|2020-01-01T00:00:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:05:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:10:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:20:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:25:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:30:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:35:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:40:00.000+08:00|            1.0|           null|\n|2020-01-01T00:45:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:50:00.000+08:00|            1.0|            1.0|\n+-----------------------------+---------------+---------------+\n")])])]),n("p",[t._v("用于查询的SQL语句：")]),t._v(" "),n("div",{staticClass:"language-sql extra-class"},[n("pre",{pre:!0,attrs:{class:"language-sql"}},[n("code",[n("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" consecutivewindows"),n("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),n("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("s2"),n("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),n("span",{pre:!0,attrs:{class:"token string"}},[t._v("'length'")]),n("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),n("span",{pre:!0,attrs:{class:"token string"}},[t._v("'10m'")]),n("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),n("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),n("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),n("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),n("p",[t._v("输出序列：")]),t._v(" "),n("div",{staticClass:"language- extra-class"},[n("pre",{pre:!0,attrs:{class:"language-text"}},[n("code",[t._v('+-----------------------------+--------------------------------------------------------------------+\n|                         Time|consecutivewindows(root.test.d1.s1, root.test.d1.s2, "length"="10m")|\n+-----------------------------+--------------------------------------------------------------------+\n|2020-01-01T00:00:00.000+08:00|                                                                   3|\n|2020-01-01T00:20:00.000+08:00|                                                                   3|\n|2020-01-01T00:25:00.000+08:00|                                                                   3|\n+-----------------------------+--------------------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=e.exports}}]);