(window.webpackJsonp=window.webpackJsonp||[]).push([[249],{619:function(t,s,a){"use strict";a.r(s);var e=a(45),n=Object(e.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"period"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#period"}},[t._v("#")]),t._v(" Period")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数用于计算单列数值型数据的周期。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" PERIOD")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE")]),t._v(" "),a("p",[a("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为INT32，序列仅包含一个时间戳为0、值为周期的数据点。")]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d3.s1|\n+-----------------------------+---------------+\n|1970-01-01T08:00:00.001+08:00|            1.0|\n|1970-01-01T08:00:00.002+08:00|            2.0|\n|1970-01-01T08:00:00.003+08:00|            3.0|\n|1970-01-01T08:00:00.004+08:00|            1.0|\n|1970-01-01T08:00:00.005+08:00|            2.0|\n|1970-01-01T08:00:00.006+08:00|            3.0|\n|1970-01-01T08:00:00.007+08:00|            1.0|\n|1970-01-01T08:00:00.008+08:00|            2.0|\n|1970-01-01T08:00:00.009+08:00|            3.0|\n+-----------------------------+---------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" period"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d3\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+-----------------------+\n|                         Time|period(root.test.d3.s1)|\n+-----------------------------+-----------------------+\n|1970-01-01T08:00:00.000+08:00|                      3|\n+-----------------------------+-----------------------+\n")])])]),a("h3",{attrs:{id:"zeppelin示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#zeppelin示例"}},[t._v("#")]),t._v(" Zeppelin示例")]),t._v(" "),a("p",[t._v("链接: "),a("a",{attrs:{href:"http://101.6.15.213:18181/#/notebook/2GEJBUSZ9",target:"_blank",rel:"noopener noreferrer"}},[t._v("http://101.6.15.213:18181/#/notebook/2GEJBUSZ9"),a("OutboundLink")],1)])])}),[],!1,null,null,null);s.default=n.exports}}]);