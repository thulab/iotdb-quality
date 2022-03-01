(window.webpackJsonp=window.webpackJsonp||[]).push([[93],{463:function(t,s,e){"use strict";e.r(s);var a=e(45),n=Object(a.a)({},(function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[e("h1",{attrs:{id:"dtw"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#dtw"}},[t._v("#")]),t._v(" DTW")]),t._v(" "),e("h2",{attrs:{id:"usage"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),e("p",[t._v("This function is used to calculate the DTW distance between two input series.")]),t._v(" "),e("p",[e("strong",[t._v("Name:")]),t._v(" DTW")]),t._v(" "),e("p",[e("strong",[t._v("Input Series:")]),t._v(" Only support two input series. The types are both INT32 / INT64 / FLOAT / DOUBLE.")]),t._v(" "),e("p",[e("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is DOUBLE. There is only one data point in the series, whose timestamp is 0 and value is the DTW distance.")]),t._v(" "),e("p",[e("strong",[t._v("Note:")])]),t._v(" "),e("ul",[e("li",[t._v("If a row contains missing points, null points or "),e("code",[t._v("NaN")]),t._v(", it will be ignored;")]),t._v(" "),e("li",[t._v("If all rows are ignored, "),e("code",[t._v("0")]),t._v(" will be output.")])]),t._v(" "),e("h2",{attrs:{id:"examples"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),e("p",[t._v("Input series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+---------------+---------------+\n|                         Time|root.test.d2.s1|root.test.d2.s2|\n+-----------------------------+---------------+---------------+\n|1970-01-01T08:00:00.001+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.002+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.003+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.004+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.005+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.006+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.007+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.008+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.009+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.010+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.011+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.012+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.013+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.014+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.015+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.016+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.017+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.018+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.019+08:00|            1.0|            2.0|\n|1970-01-01T08:00:00.020+08:00|            1.0|            2.0|\n+-----------------------------+---------------+---------------+\n")])])]),e("p",[t._v("SQL for query:")]),t._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" dtw"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("s2"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d2\n")])])]),e("p",[t._v("Output series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+-------------------------------------+\n|                         Time|dtw(root.test.d2.s1, root.test.d2.s2)|\n+-----------------------------+-------------------------------------+\n|1970-01-01T08:00:00.000+08:00|                                 20.0|\n+-----------------------------+-------------------------------------+\n")])])])])}),[],!1,null,null,null);s.default=n.exports}}]);