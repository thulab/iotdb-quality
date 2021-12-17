(window.webpackJsonp=window.webpackJsonp||[]).push([[10],{379:function(t,s,e){"use strict";e.r(s);var a=e(45),n=Object(a.a)({},(function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[e("h1",{attrs:{id:"adwin"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#adwin"}},[t._v("#")]),t._v(" ADWIN")]),t._v(" "),e("h2",{attrs:{id:"usage"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),e("p",[t._v("This function is used to detect possible concept drift of time series. According to delta parameter, the function judges if concept drift occurs at an input value using ADWIN algorithm, and a new time series of detected concept drifts will be output. For detail, see")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("Learning from Time-Changing Data with Adaptive Windowing, A Bifetet al., 2005\n")])])]),e("p",[e("strong",[t._v("Name:")]),t._v(" ADWIN")]),t._v(" "),e("p",[e("strong",[t._v("Input Series:")]),t._v(" Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),t._v(" "),e("ul",[e("li",[e("code",[t._v("delta")]),t._v(":a threshold to distinguish concept drifts. See the δ defined in the paper. Default value is 0.01.")]),t._v(" "),e("li",[e("code",[t._v("windowsize")]),t._v(":the size of window to detect. Should be larger than 2")])]),t._v(" "),e("p",[e("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is INT32. 1 for anomaly, and 0 for others.")]),t._v(" "),e("h2",{attrs:{id:"examples"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),e("h3",{attrs:{id:"assigning-parameters"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#assigning-parameters"}},[t._v("#")]),t._v(" Assigning parameters")]),t._v(" "),e("p",[t._v("Input series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+------------+\n|                         Time|root.test.s1|\n+-----------------------------+------------+\n|1970-01-01T08:00:00.000+08:00|         5.0|\n|1970-01-01T08:00:00.100+08:00|         5.0|\n|1970-01-01T08:00:00.200+08:00|         5.0|\n|1970-01-01T08:00:00.300+08:00|         5.0|\n|1970-01-01T08:00:00.400+08:00|         5.0|\n|1970-01-01T08:00:00.500+08:00|         5.0|\n|1970-01-01T08:00:00.600+08:00|         5.0|\n|1970-01-01T08:00:00.700+08:00|         5.0|\n|1970-01-01T08:00:00.800+08:00|         5.0|\n|1970-01-01T08:00:00.900+08:00|         5.0|\n|1970-01-01T08:00:01.000+08:00|         5.0|\n|1970-01-01T08:00:01.100+08:00|         5.0|\n|1970-01-01T08:00:01.200+08:00|         5.0|\n|1970-01-01T08:00:01.300+08:00|         5.0|\n|1970-01-01T08:00:01.400+08:00|         5.0|\n|1970-01-01T08:00:01.500+08:00|         5.0|\n|1970-01-01T08:00:01.600+08:00|         5.0|\n|1970-01-01T08:00:01.700+08:00|         5.0|\n|1970-01-01T08:00:01.800+08:00|         5.0|\n|1970-01-01T08:00:01.900+08:00|         5.0|\n|1970-01-01T08:00:02.000+08:00|        10.0|\n|1970-01-01T08:00:02.100+08:00|        10.0|\n|1970-01-01T08:00:02.200+08:00|        10.0|\n|1970-01-01T08:00:02.300+08:00|        10.0|\n|1970-01-01T08:00:02.400+08:00|        10.0|\n|1970-01-01T08:00:02.500+08:00|        10.0|\n|1970-01-01T08:00:02.600+08:00|        10.0|\n|1970-01-01T08:00:02.700+08:00|        10.0|\n|1970-01-01T08:00:02.800+08:00|        10.0|\n|1970-01-01T08:00:02.900+08:00|        10.0|\n|1970-01-01T08:00:03.000+08:00|        10.0|\n|1970-01-01T08:00:03.100+08:00|        10.0|\n|1970-01-01T08:00:03.200+08:00|        10.0|\n|1970-01-01T08:00:03.300+08:00|        10.0|\n|1970-01-01T08:00:03.400+08:00|        10.0|\n|1970-01-01T08:00:03.500+08:00|        10.0|\n|1970-01-01T08:00:03.600+08:00|        10.0|\n|1970-01-01T08:00:03.700+08:00|        10.0|\n|1970-01-01T08:00:03.800+08:00|        10.0|\n|1970-01-01T08:00:03.900+08:00|        10.0|\n+-----------------------------+------------+\n")])])]),e("p",[t._v("SQL for query:")]),t._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" adwin"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"windowsize"')]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"30"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"delta"')]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"0.01"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test\n")])])]),e("p",[t._v("Output series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v('+-----------------------------+------------------------------------------------------+\n|                         Time|adwin(root.test.s1, "windowsize"="30", "delta"="0.01")|\n+-----------------------------+------------------------------------------------------+\n|1970-01-01T08:00:02.100+08:00|                                                     1|\n+-----------------------------+------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=n.exports}}]);