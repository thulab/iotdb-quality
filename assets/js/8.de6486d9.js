(window.webpackJsonp=window.webpackJsonp||[]).push([[8],{374:function(t,e,s){"use strict";s.r(e);var a=s(45),n=Object(a.a)({},(function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"completeness"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#completeness"}},[t._v("#")]),t._v(" Completeness")]),t._v(" "),s("h2",{attrs:{id:"函数简介"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),s("p",[t._v("This function is used to calculate the completeness of time series.")]),t._v(" "),s("ul",[s("li",[t._v("Name: COMPLETENESS")]),t._v(" "),s("li",[t._v("Type of Input Series: INT32 / INT64 / FLOAT / DOUBLE")]),t._v(" "),s("li",[t._v("Type of Output Series: DOUBLE")]),t._v(" "),s("li",[t._v("Parameters:\n"),s("ul",[s("li",[s("code",[t._v("window")]),t._v(": The number of data points in each window. The number of data points in the last window may be less than it. By default, all input data belongs to the same window.")])])]),t._v(" "),s("li",[t._v("Description: The input series are divided into several continuous and non overlapping windows. The timestamp of the first data point and the completeness of each window will be output.")])]),t._v(" "),s("h2",{attrs:{id:"function-usage-examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#function-usage-examples"}},[t._v("#")]),t._v(" Function Usage Examples")]),t._v(" "),s("h3",{attrs:{id:"example-1"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#example-1"}},[t._v("#")]),t._v(" Example 1")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("completeness"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1 "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("where")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("time")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("<=")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("2020")]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("01")]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("01")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("00")]),t._v(":"),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("00")]),t._v(":"),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("30")]),t._v("\n")])])]),s("p",[t._v("Result:")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+---------------+-----------------------------+\n|                         Time|root.test.d1.s1|completeness(root.test.d1.s1)|\n+-----------------------------+---------------+-----------------------------+\n|2020-01-01T00:00:02.000+08:00|          100.0|                        0.875|\n|2020-01-01T00:00:03.000+08:00|          101.0|                         null|\n|2020-01-01T00:00:04.000+08:00|          102.0|                         null|\n|2020-01-01T00:00:06.000+08:00|          104.0|                         null|\n|2020-01-01T00:00:08.000+08:00|          126.0|                         null|\n|2020-01-01T00:00:10.000+08:00|          108.0|                         null|\n|2020-01-01T00:00:14.000+08:00|          112.0|                         null|\n|2020-01-01T00:00:15.000+08:00|          113.0|                         null|\n|2020-01-01T00:00:16.000+08:00|          114.0|                         null|\n|2020-01-01T00:00:18.000+08:00|          116.0|                         null|\n|2020-01-01T00:00:20.000+08:00|          118.0|                         null|\n|2020-01-01T00:00:22.000+08:00|          120.0|                         null|\n|2020-01-01T00:00:26.000+08:00|          124.0|                         null|\n|2020-01-01T00:00:28.000+08:00|          126.0|                         null|\n|2020-01-01T00:00:30.000+08:00|            NaN|                         null|\n+-----------------------------+---------------+-----------------------------+\nTotal line number = 15\nIt costs 0.500s\n")])])])])}),[],!1,null,null,null);e.default=n.exports}}]);