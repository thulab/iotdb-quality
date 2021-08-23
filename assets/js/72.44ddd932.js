(window.webpackJsonp=window.webpackJsonp||[]).push([[72],{440:function(t,s,e){"use strict";e.r(s);var a=e(45),n=Object(a.a)({},(function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[e("h1",{attrs:{id:"distinct"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#distinct"}},[t._v("#")]),t._v(" Distinct")]),t._v(" "),e("h2",{attrs:{id:"usage"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),e("p",[t._v("This function returns all unique values in time series.")]),t._v(" "),e("p",[e("strong",[t._v("Name:")]),t._v(" DISTINCT")]),t._v(" "),e("p",[e("strong",[t._v("Input Series:")]),t._v(" Only support a single input series. The type is arbitrary.")]),t._v(" "),e("p",[e("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is the same as the input.")]),t._v(" "),e("p",[e("strong",[t._v("Note:")])]),t._v(" "),e("ul",[e("li",[t._v("The timestamp of the output series is meaningless. The output order is the same as the appearing order of values.")]),t._v(" "),e("li",[t._v("Missing points and null points in the input series will be ignored, but "),e("code",[t._v("NaN")]),t._v(" will not.")])]),t._v(" "),e("h2",{attrs:{id:"examples"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),e("p",[t._v("Input series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d2.s2|\n+-----------------------------+---------------+\n|2020-01-01T08:00:00.001+08:00|          Hello|\n|2020-01-01T08:00:00.002+08:00|          hello|\n|2020-01-01T08:00:00.003+08:00|          Hello|\n|2020-01-01T08:00:00.004+08:00|          World|\n|2020-01-01T08:00:00.005+08:00|          World|\n+-----------------------------+---------------+\n")])])]),e("p",[t._v("SQL for query:")]),t._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("distinct")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s2"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d2\n")])])]),e("p",[t._v("Output series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+-------------------------+\n|                         Time|distinct(root.test.d2.s2)|\n+-----------------------------+-------------------------+\n|1970-01-01T08:00:00.001+08:00|                    Hello|\n|1970-01-01T08:00:00.002+08:00|                    hello|\n|1970-01-01T08:00:00.003+08:00|                    World|\n+-----------------------------+-------------------------+\n")])])])])}),[],!1,null,null,null);s.default=n.exports}}]);