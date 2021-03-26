(window.webpackJsonp=window.webpackJsonp||[]).push([[62],{429:function(t,s,a){"use strict";a.r(s);var e=a(45),n=Object(e.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"distinct"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#distinct"}},[t._v("#")]),t._v(" Distinct")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数可以返回输入序列中出现的所有不同的值。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" DISTINCT")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型可以是任意的")]),t._v(" "),a("p",[a("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型与输入相同。")]),t._v(" "),a("p",[a("strong",[t._v("提示：")]),t._v(" 输出序列的时间戳是无意义的，且不保证输出顺序。")]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d2.s2|\n+-----------------------------+---------------+\n|2020-01-01T08:00:00.001+08:00|          Hello|\n|2020-01-01T08:00:00.002+08:00|          hello|\n|2020-01-01T08:00:00.003+08:00|          Hello|\n|2020-01-01T08:00:00.004+08:00|          World|\n|2020-01-01T08:00:00.005+08:00|          World|\n+-----------------------------+---------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("distinct")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s2"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d2\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+-------------------------+\n|                         Time|distinct(root.test.d2.s2)|\n+-----------------------------+-------------------------+\n|1970-01-01T08:00:00.001+08:00|                    Hello|\n|1970-01-01T08:00:00.002+08:00|                    hello|\n|1970-01-01T08:00:00.003+08:00|                    World|\n+-----------------------------+-------------------------+\n")])])])])}),[],!1,null,null,null);s.default=n.exports}}]);