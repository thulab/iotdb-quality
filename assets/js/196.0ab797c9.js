(window.webpackJsonp=window.webpackJsonp||[]).push([[196],{564:function(t,a,s){"use strict";s.r(a);var e=s(45),n=Object(e.a)({},(function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"timestamprepair"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#timestamprepair"}},[t._v("#")]),t._v(" TimestampRepair")]),t._v(" "),s("h2",{attrs:{id:"函数简介"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),s("p",[t._v("本函数用于时间戳修复。根据给定的标准时间间隔，采用最小化修复代价的方法，通过对数据时间戳的微调，将原本时间戳间隔不稳定的数据修复为严格等间隔的数据。在未给定标准时间间隔的情况下，本函数将使用时间间隔的中位数(median)、众数(mode)或聚类中心(cluster)来推算标准时间间隔。")]),t._v(" "),s("p",[s("strong",[t._v("函数名：")]),t._v(" TIMESTAMPREPAIR")]),t._v(" "),s("p",[s("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE")]),t._v(" "),s("p",[s("strong",[t._v("参数：")])]),t._v(" "),s("ul",[s("li",[s("code",[t._v("interval")]),t._v(": 标准时间间隔（单位是毫秒），是一个正整数。在缺省情况下，将根据指定的方法推算。")]),t._v(" "),s("li",[s("code",[t._v("method")]),t._v("：推算标准时间间隔的方法，取值为'median'、'mode'或'cluster'，仅在"),s("code",[t._v("interval")]),t._v("缺省时有效。在缺省情况下，将使用中位数方法进行推算。")])]),t._v(" "),s("p",[s("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型与输入序列相同。该序列是修复后的输入序列。")]),t._v(" "),s("h2",{attrs:{id:"使用示例"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),s("h3",{attrs:{id:"指定标准时间间隔"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#指定标准时间间隔"}},[t._v("#")]),t._v(" 指定标准时间间隔")]),t._v(" "),s("p",[t._v("在给定"),s("code",[t._v("interval")]),t._v("参数的情况下，本函数将按照指定的标准时间间隔进行修复。")]),t._v(" "),s("p",[t._v("输入序列：")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d2.s1|\n+-----------------------------+---------------+\n|2021-07-01T12:00:00.000+08:00|            1.0|\n|2021-07-01T12:00:10.000+08:00|            2.0|\n|2021-07-01T12:00:19.000+08:00|            3.0|\n|2021-07-01T12:00:30.000+08:00|            4.0|\n|2021-07-01T12:00:40.000+08:00|            5.0|\n|2021-07-01T12:00:50.000+08:00|            6.0|\n|2021-07-01T12:01:01.000+08:00|            7.0|\n|2021-07-01T12:01:11.000+08:00|            8.0|\n|2021-07-01T12:01:21.000+08:00|            9.0|\n|2021-07-01T12:01:31.000+08:00|           10.0|\n+-----------------------------+---------------+\n")])])]),s("p",[t._v("用于查询的SQL语句：")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" timestamprepair"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'interval'")]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'10000'")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d2\n")])])]),s("p",[t._v("输出序列：")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v('+-----------------------------+----------------------------------------------------+\n|                         Time|timestamprepair(root.test.d2.s1, "interval"="10000")|\n+-----------------------------+----------------------------------------------------+\n|2021-07-01T12:00:00.000+08:00|                                                 1.0|\n|2021-07-01T12:00:10.000+08:00|                                                 2.0|\n|2021-07-01T12:00:20.000+08:00|                                                 3.0|\n|2021-07-01T12:00:30.000+08:00|                                                 4.0|\n|2021-07-01T12:00:40.000+08:00|                                                 5.0|\n|2021-07-01T12:00:50.000+08:00|                                                 6.0|\n|2021-07-01T12:01:00.000+08:00|                                                 7.0|\n|2021-07-01T12:01:10.000+08:00|                                                 8.0|\n|2021-07-01T12:01:20.000+08:00|                                                 9.0|\n|2021-07-01T12:01:30.000+08:00|                                                10.0|\n+-----------------------------+----------------------------------------------------+\n')])])]),s("h3",{attrs:{id:"自动推算标准时间间隔"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#自动推算标准时间间隔"}},[t._v("#")]),t._v(" 自动推算标准时间间隔")]),t._v(" "),s("p",[t._v("如果"),s("code",[t._v("interval")]),t._v("参数没有给定，本函数将按照推算的标准时间间隔进行修复。")]),t._v(" "),s("p",[t._v("输入序列同上，用于查询的SQL语句如下：")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" timestamprepair"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d2\n")])])]),s("p",[t._v("输出序列：")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+--------------------------------+\n|                         Time|timestamprepair(root.test.d2.s1)|\n+-----------------------------+--------------------------------+\n|2021-07-01T12:00:00.000+08:00|                             1.0|\n|2021-07-01T12:00:10.000+08:00|                             2.0|\n|2021-07-01T12:00:20.000+08:00|                             3.0|\n|2021-07-01T12:00:30.000+08:00|                             4.0|\n|2021-07-01T12:00:40.000+08:00|                             5.0|\n|2021-07-01T12:00:50.000+08:00|                             6.0|\n|2021-07-01T12:01:00.000+08:00|                             7.0|\n|2021-07-01T12:01:10.000+08:00|                             8.0|\n|2021-07-01T12:01:20.000+08:00|                             9.0|\n|2021-07-01T12:01:30.000+08:00|                            10.0|\n+-----------------------------+--------------------------------+\n")])])])])}),[],!1,null,null,null);a.default=n.exports}}]);