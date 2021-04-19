(window.webpackJsonp=window.webpackJsonp||[]).push([[68],{438:function(t,e,s){"use strict";s.r(e);var a=s(45),n=Object(a.a)({},(function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"mode"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#mode"}},[t._v("#")]),t._v(" Mode")]),t._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),s("p",[t._v("This function is used to calculate the mode of time series, that is, the value that occurs most frequently.")]),t._v(" "),s("p",[s("strong",[t._v("Name:")]),t._v(" MODE")]),t._v(" "),s("p",[s("strong",[t._v("Input Series:")]),t._v(" Only support a single input series. The type is arbitrary.")]),t._v(" "),s("p",[s("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is the same as the input. There is only one data point in the series, whose timestamp is 0 and value is the mode.")]),t._v(" "),s("p",[s("strong",[t._v("Note:")])]),t._v(" "),s("ul",[s("li",[t._v("If there are multiple values with the most occurrences, the one that appears first will be output.")]),t._v(" "),s("li",[t._v("Missing points and null points in the input series will be ignored, but "),s("code",[t._v("NaN")]),t._v(" will not.")])]),t._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),s("p",[t._v("Input series:")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d2.s2|\n+-----------------------------+---------------+\n|1970-01-01T08:00:00.001+08:00|          Hello|\n|1970-01-01T08:00:00.002+08:00|          hello|\n|1970-01-01T08:00:00.003+08:00|          Hello|\n|1970-01-01T08:00:00.004+08:00|          World|\n|1970-01-01T08:00:00.005+08:00|          World|\n|1970-01-01T08:00:01.600+08:00|          World|\n|1970-01-15T09:37:34.451+08:00|          Hello|\n|1970-01-15T09:37:34.452+08:00|          hello|\n|1970-01-15T09:37:34.453+08:00|          Hello|\n|1970-01-15T09:37:34.454+08:00|          World|\n|1970-01-15T09:37:34.455+08:00|          World|\n+-----------------------------+---------------+\n")])])]),s("p",[t._v("SQL for query:")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("mode")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s2"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d2\n")])])]),s("p",[t._v("Output series:")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+---------------------+\n|                         Time|mode(root.test.d2.s2)|\n+-----------------------------+---------------------+\n|1970-01-01T08:00:00.000+08:00|                World|\n+-----------------------------+---------------------+\n")])])])])}),[],!1,null,null,null);e.default=n.exports}}]);