(window.webpackJsonp=window.webpackJsonp||[]).push([[279],{650:function(s,t,a){"use strict";a.r(t);var e=a(45),n=Object(e.a)({},(function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("ContentSlotsDistributor",{attrs:{"slot-key":s.$parent.slotKey}},[a("h1",{attrs:{id:"consecutivesequences"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#consecutivesequences"}},[s._v("#")]),s._v(" ConsecutiveSequences")]),s._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[s._v("#")]),s._v(" 函数简介")]),s._v(" "),a("p",[s._v("本函数用于在多维严格等间隔数据中发现局部最长连续子序列。")]),s._v(" "),a("p",[s._v("严格等间隔数据是指数据的时间间隔是严格相等的，允许存在数据缺失（包括行缺失和值缺失），但不允许存在数据冗余和时间戳偏移。")]),s._v(" "),a("p",[s._v("连续子序列是指严格按照标准时间间隔等距排布，不存在任何数据缺失的子序列。如果某个连续子序列不是任何连续子序列的真子序列，那么它是局部最长的。")]),s._v(" "),a("p",[a("strong",[s._v("函数名：")]),s._v(" CONSECUTIVESEQUENCES")]),s._v(" "),a("p",[a("strong",[s._v("输入序列：")]),s._v(" 支持多个输入序列，类型可以是任意的，但要满足严格等间隔的要求。")]),s._v(" "),a("p",[a("strong",[s._v("参数：")])]),s._v(" "),a("ul",[a("li",[a("code",[s._v("gap")]),s._v("：标准时间间隔，是一个有单位的正数。目前支持五种单位，分别是'ms'（毫秒）、's'（秒）、'm'（分钟）、'h'（小时）和'd'（天）。在缺省情况下，函数会利用众数估计标准时间间隔。")])]),s._v(" "),a("p",[a("strong",[s._v("输出序列：")]),s._v(" 输出单个序列，类型为INT32。输出序列中的每一个数据点对应一个局部最长连续子序列，时间戳为子序列的起始时刻，值为子序列包含的数据点个数。")]),s._v(" "),a("p",[a("strong",[s._v("提示：")]),s._v(" 对于不符合要求的输入，本函数不对输出做任何保证。")]),s._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[s._v("#")]),s._v(" 使用示例")]),s._v(" "),a("h3",{attrs:{id:"手动指定标准时间间隔"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#手动指定标准时间间隔"}},[s._v("#")]),s._v(" 手动指定标准时间间隔")]),s._v(" "),a("p",[s._v("本函数可以通过"),a("code",[s._v("gap")]),s._v("参数手动指定标准时间间隔。需要注意的是，错误的参数设置会导致输出产生严重错误。")]),s._v(" "),a("p",[s._v("输入序列：")]),s._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[s._v("+-----------------------------+---------------+---------------+\n|                         Time|root.test.d1.s1|root.test.d1.s2|\n+-----------------------------+---------------+---------------+\n|2020-01-01T00:00:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:05:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:10:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:20:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:25:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:30:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:35:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:40:00.000+08:00|            1.0|           null|\n|2020-01-01T00:45:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:50:00.000+08:00|            1.0|            1.0|\n+-----------------------------+---------------+---------------+\n")])])]),a("p",[s._v("用于查询的SQL语句：")]),s._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("select")]),s._v(" consecutivesequences"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("(")]),s._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),s._v("s2"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'gap'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'5m'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(")")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("from")]),s._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("d1\n")])])]),a("p",[s._v("输出序列：")]),s._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[s._v('+-----------------------------+------------------------------------------------------------------+\n|                         Time|consecutivesequences(root.test.d1.s1, root.test.d1.s2, "gap"="5m")|\n+-----------------------------+------------------------------------------------------------------+\n|2020-01-01T00:00:00.000+08:00|                                                                 3|\n|2020-01-01T00:20:00.000+08:00|                                                                 4|\n|2020-01-01T00:45:00.000+08:00|                                                                 2|\n+-----------------------------+------------------------------------------------------------------+\n')])])]),a("h3",{attrs:{id:"自动估计标准时间间隔"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#自动估计标准时间间隔"}},[s._v("#")]),s._v(" 自动估计标准时间间隔")]),s._v(" "),a("p",[s._v("当"),a("code",[s._v("gap")]),s._v("参数缺省时，本函数可以利用众数估计标准时间间隔，得到同样的结果。因此，这种用法更受推荐。")]),s._v(" "),a("p",[s._v("输入序列同上，用于查询的SQL语句如下：")]),s._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("select")]),s._v(" consecutivesequences"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("(")]),s._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),s._v("s2"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(")")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("from")]),s._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("d1\n")])])]),a("p",[s._v("输出序列：")]),s._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[s._v("+-----------------------------+------------------------------------------------------+\n|                         Time|consecutivesequences(root.test.d1.s1, root.test.d1.s2)|\n+-----------------------------+------------------------------------------------------+\n|2020-01-01T00:00:00.000+08:00|                                                     3|\n|2020-01-01T00:20:00.000+08:00|                                                     4|\n|2020-01-01T00:45:00.000+08:00|                                                     2|\n+-----------------------------+------------------------------------------------------+\n")])])])])}),[],!1,null,null,null);t.default=n.exports}}]);