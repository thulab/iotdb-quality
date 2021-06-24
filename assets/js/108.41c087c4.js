(window.webpackJsonp=window.webpackJsonp||[]).push([[108],{478:function(t,a,s){"use strict";s.r(a);var n=s(45),e=Object(n.a)({},(function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"valuefill"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#valuefill"}},[t._v("#")]),t._v(" ValueFill")]),t._v(" "),s("h2",{attrs:{id:"函数简介"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),s("p",[s("strong",[t._v("函数名：")]),t._v(" FILL")]),t._v(" "),s("p",[s("strong",[t._v("输入序列：")]),t._v(" 单列时序数据，类型为INT32 / INT64 / FLOAT / DOUBLE")]),t._v(" "),s("p",[s("strong",[t._v("参数：")])]),t._v(" "),s("ul",[s("li",[s("code",[t._v("method")]),t._v(': “ffill"指使用前值填补方法；“linear"指使用线性填补方法；缺省情况下使用“ffill”。')])]),t._v(" "),s("p",[s("strong",[t._v("输出序列：")]),t._v(" 即修复后的单维序列。")]),t._v(" "),s("h2",{attrs:{id:"使用示例"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),s("h3",{attrs:{id:"使用ffill方法进行修复"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#使用ffill方法进行修复"}},[t._v("#")]),t._v(" 使用ffill方法进行修复")]),t._v(" "),s("p",[t._v("当"),s("code",[t._v("method")]),t._v("缺省或取值为'ffill'时，本函数将使用ffill方法进行数值修复。")]),t._v(" "),s("p",[t._v("输入序列：")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d2.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:02.000+08:00|            NaN|\n|2020-01-01T00:00:03.000+08:00|          101.0|\n|2020-01-01T00:00:04.000+08:00|          102.0|\n|2020-01-01T00:00:06.000+08:00|          104.0|\n|2020-01-01T00:00:08.000+08:00|          126.0|\n|2020-01-01T00:00:10.000+08:00|          108.0|\n|2020-01-01T00:00:14.000+08:00|            NaN|\n|2020-01-01T00:00:15.000+08:00|          113.0|\n|2020-01-01T00:00:16.000+08:00|          114.0|\n|2020-01-01T00:00:18.000+08:00|          116.0|\n|2020-01-01T00:00:20.000+08:00|            NaN|\n|2020-01-01T00:00:22.000+08:00|            NaN|\n|2020-01-01T00:00:26.000+08:00|          124.0|\n|2020-01-01T00:00:28.000+08:00|          126.0|\n|2020-01-01T00:00:30.000+08:00|          128.0|\n+-----------------------------+---------------+\n")])])]),s("p",[t._v("用于查询的SQL语句：")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" valuefill"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d2\n")])])]),s("p",[t._v("输出序列：")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+-----------------------+\n|                         Time|valuefill(root.test.d2)|\n+-----------------------------+-----------------------+\n|2020-01-01T00:00:02.000+08:00|                    NaN|\n|2020-01-01T00:00:03.000+08:00|                  101.0|\n|2020-01-01T00:00:04.000+08:00|                  102.0|\n|2020-01-01T00:00:06.000+08:00|                  104.0|\n|2020-01-01T00:00:08.000+08:00|                  126.0|\n|2020-01-01T00:00:10.000+08:00|                  108.0|\n|2020-01-01T00:00:14.000+08:00|                  110.5|\n|2020-01-01T00:00:15.000+08:00|                  113.0|\n|2020-01-01T00:00:16.000+08:00|                  114.0|\n|2020-01-01T00:00:18.000+08:00|                  116.0|\n|2020-01-01T00:00:20.000+08:00|                  116.0|\n|2020-01-01T00:00:22.000+08:00|                  116.0|\n|2020-01-01T00:00:26.000+08:00|                  124.0|\n|2020-01-01T00:00:28.000+08:00|                  126.0|\n|2020-01-01T00:00:30.000+08:00|                  128.0|\n+-----------------------------+-----------------------+\n")])])]),s("h3",{attrs:{id:"使用linear方法进行填补"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#使用linear方法进行填补"}},[t._v("#")]),t._v(" 使用linear方法进行填补")]),t._v(" "),s("p",[t._v("当"),s("code",[t._v("method")]),t._v("取值为'linear'时，本函数将使用Linear方法进行数值修复。")]),t._v(" "),s("p",[t._v("输入序列同上，用于查询的SQL语句如下：")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" valuefill"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'method'")]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'linaer'")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d2\n")])])]),s("p",[t._v("输出序列：")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v('+-----------------------------+-----------------------------------------+\n|                         Time|valuefill(root.test.d2,"method"="linear")|\n+-----------------------------+-----------------------------------------+\n|2020-01-01T00:00:02.000+08:00|                                      NaN|\n|2020-01-01T00:00:03.000+08:00|                                    101.0|\n|2020-01-01T00:00:04.000+08:00|                                    102.0|\n|2020-01-01T00:00:06.000+08:00|                                    104.0|\n|2020-01-01T00:00:08.000+08:00|                                    126.0|\n|2020-01-01T00:00:10.000+08:00|                                    108.0|\n|2020-01-01T00:00:14.000+08:00|                                    108.0|\n|2020-01-01T00:00:15.000+08:00|                                    113.0|\n|2020-01-01T00:00:16.000+08:00|                                    114.0|\n|2020-01-01T00:00:18.000+08:00|                                    116.0|\n|2020-01-01T00:00:20.000+08:00|                                    118.7|\n|2020-01-01T00:00:22.000+08:00|                                    121.3|\n|2020-01-01T00:00:26.000+08:00|                                    124.0|\n|2020-01-01T00:00:28.000+08:00|                                    126.0|\n|2020-01-01T00:00:30.000+08:00|                                    128.0|\n+-----------------------------+-----------------------------------------+\n')])])])])}),[],!1,null,null,null);a.default=e.exports}}]);