(window.webpackJsonp=window.webpackJsonp||[]).push([[144],{516:function(t,s,a){"use strict";a.r(s);var e=a(45),n=Object(e.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"deconv"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#deconv"}},[t._v("#")]),t._v(" Deconv")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数对两个输入序列进行去卷积，即多项式除法运算。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" DECONV")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持两个输入序列，类型均为 INT32 / INT64 / FLOAT / DOUBLE")]),t._v(" "),a("p",[a("strong",[t._v("参数：")])]),t._v(" "),a("ul",[a("li",[a("code",[t._v("result")]),t._v("：去卷积的结果，取值为'quotient'或'remainder'，分别对应于去卷积的商和余数。在缺省情况下，输出去卷积的商。")])]),t._v(" "),a("p",[a("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为DOUBLE。它是将第二个序列从第一个序列中去卷积（第一个序列除以第二个序列）的结果。序列的时间戳从0开始，仅用于表示顺序。")]),t._v(" "),a("p",[a("strong",[t._v("提示：")]),t._v(" 输入序列中的"),a("code",[t._v("NaN")]),t._v("将被忽略。")]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("h3",{attrs:{id:"计算去卷积的商"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#计算去卷积的商"}},[t._v("#")]),t._v(" 计算去卷积的商")]),t._v(" "),a("p",[t._v("当"),a("code",[t._v("result")]),t._v("参数缺省或为'quotient'时，本函数计算去卷积的商。")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+---------------+\n|                         Time|root.test.d2.s3|root.test.d2.s2|\n+-----------------------------+---------------+---------------+\n|1970-01-01T08:00:00.000+08:00|            8.0|            7.0|\n|1970-01-01T08:00:00.001+08:00|            2.0|            2.0|\n|1970-01-01T08:00:00.002+08:00|            7.0|           null|\n|1970-01-01T08:00:00.003+08:00|            2.0|           null|\n+-----------------------------+---------------+---------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" deconv"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s3"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("s2"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d2\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+----------------------------------------+\n|                         Time|deconv(root.test.d2.s3, root.test.d2.s2)|\n+-----------------------------+----------------------------------------+\n|1970-01-01T08:00:00.000+08:00|                                     1.0|\n|1970-01-01T08:00:00.001+08:00|                                     0.0|\n|1970-01-01T08:00:00.002+08:00|                                     1.0|\n+-----------------------------+----------------------------------------+\n")])])]),a("h3",{attrs:{id:"计算去卷积的余数"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#计算去卷积的余数"}},[t._v("#")]),t._v(" 计算去卷积的余数")]),t._v(" "),a("p",[t._v("当"),a("code",[t._v("result")]),t._v("参数为'remainder'时，本函数计算去卷积的余数。输入序列同上，用于查询的SQL语句如下：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" deconv"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s3"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("s2"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'result'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'remainder'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d2\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v('+-----------------------------+--------------------------------------------------------------+\n|                         Time|deconv(root.test.d2.s3, root.test.d2.s2, "result"="remainder")|\n+-----------------------------+--------------------------------------------------------------+\n|1970-01-01T08:00:00.000+08:00|                                                           1.0|\n|1970-01-01T08:00:00.001+08:00|                                                           0.0|\n|1970-01-01T08:00:00.002+08:00|                                                           0.0|\n|1970-01-01T08:00:00.003+08:00|                                                           0.0|\n+-----------------------------+--------------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=n.exports}}]);