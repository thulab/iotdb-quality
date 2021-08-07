(window.webpackJsonp=window.webpackJsonp||[]).push([[44],{418:function(e,t,s){"use strict";s.r(t);var n=s(45),a=Object(n.a)({},(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[s("h1",{attrs:{id:"segment"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#segment"}},[e._v("#")]),e._v(" Segment")]),e._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[e._v("#")]),e._v(" Usage")]),e._v(" "),s("p",[e._v("This function is used to segment a time series into subsequences according to linear trend, and returns linear fitted values of first values in each subsequence or every data point.")]),e._v(" "),s("p",[s("strong",[e._v("Name:")]),e._v(" SEGMENT")]),e._v(" "),s("p",[s("strong",[e._v("Input Series:")]),e._v(" Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),e._v(" "),s("p",[s("strong",[e._v("Parameters:")])]),e._v(" "),s("ul",[s("li",[s("p",[s("code",[e._v("output")]),e._v(' :"all" to output all fitted points; "first" to output first fitted points in each subsequence.')])]),e._v(" "),s("li",[s("p",[s("code",[e._v("error")]),e._v(": error allowed at linear regression. It is defined as mean absolute error of a subsequence.")])])]),e._v(" "),s("p",[s("strong",[e._v("Output Series:")]),e._v(" Output a single series. The type is DOUBLE.")]),e._v(" "),s("p",[s("strong",[e._v("Note:")]),e._v(" This function treat input series as equal-interval sampled. All data are loaded, so downsample input series first if there are too many data points.")]),e._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[e._v("#")]),e._v(" Examples")]),e._v(" "),s("p",[e._v("Input series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+------------+\n|                         Time|root.test.s1|\n+-----------------------------+------------+\n|1970-01-01T08:00:00.000+08:00|         5.0|\n|1970-01-01T08:00:00.100+08:00|         0.0|\n|1970-01-01T08:00:00.200+08:00|         1.0|\n|1970-01-01T08:00:00.300+08:00|         2.0|\n|1970-01-01T08:00:00.400+08:00|         3.0|\n|1970-01-01T08:00:00.500+08:00|         4.0|\n|1970-01-01T08:00:00.600+08:00|         5.0|\n|1970-01-01T08:00:00.700+08:00|         6.0|\n|1970-01-01T08:00:00.800+08:00|         7.0|\n|1970-01-01T08:00:00.900+08:00|         8.0|\n|1970-01-01T08:00:01.000+08:00|         9.0|\n|1970-01-01T08:00:01.100+08:00|         9.1|\n|1970-01-01T08:00:01.200+08:00|         9.2|\n|1970-01-01T08:00:01.300+08:00|         9.3|\n|1970-01-01T08:00:01.400+08:00|         9.4|\n|1970-01-01T08:00:01.500+08:00|         9.5|\n|1970-01-01T08:00:01.600+08:00|         9.6|\n|1970-01-01T08:00:01.700+08:00|         9.7|\n|1970-01-01T08:00:01.800+08:00|         9.8|\n|1970-01-01T08:00:01.900+08:00|         9.9|\n|1970-01-01T08:00:02.000+08:00|        10.0|\n|1970-01-01T08:00:02.100+08:00|         8.0|\n|1970-01-01T08:00:02.200+08:00|         6.0|\n|1970-01-01T08:00:02.300+08:00|         4.0|\n|1970-01-01T08:00:02.400+08:00|         2.0|\n|1970-01-01T08:00:02.500+08:00|         0.0|\n|1970-01-01T08:00:02.600+08:00|        -2.0|\n|1970-01-01T08:00:02.700+08:00|        -4.0|\n|1970-01-01T08:00:02.800+08:00|        -6.0|\n|1970-01-01T08:00:02.900+08:00|        -8.0|\n|1970-01-01T08:00:03.000+08:00|       -10.0|\n|1970-01-01T08:00:03.100+08:00|        10.0|\n|1970-01-01T08:00:03.200+08:00|        10.0|\n|1970-01-01T08:00:03.300+08:00|        10.0|\n|1970-01-01T08:00:03.400+08:00|        10.0|\n|1970-01-01T08:00:03.500+08:00|        10.0|\n|1970-01-01T08:00:03.600+08:00|        10.0|\n|1970-01-01T08:00:03.700+08:00|        10.0|\n|1970-01-01T08:00:03.800+08:00|        10.0|\n|1970-01-01T08:00:03.900+08:00|        10.0|\n+-----------------------------+------------+\n")])])]),s("p",[e._v("SQL for query:")]),e._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" segment"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(",")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token string"}},[e._v('"error"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[e._v('"0.1"')]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test\n")])])]),s("p",[e._v("Output series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v('+-----------------------------+------------------------------------+\n|                         Time|segment(root.test.s1, "error"="0.1")|\n+-----------------------------+------------------------------------+\n|1970-01-01T08:00:00.000+08:00|                                 5.0|\n|1970-01-01T08:00:00.200+08:00|                                 1.0|\n|1970-01-01T08:00:01.000+08:00|                                 9.0|\n|1970-01-01T08:00:02.000+08:00|                                10.0|\n|1970-01-01T08:00:03.000+08:00|                               -10.0|\n|1970-01-01T08:00:03.200+08:00|                                10.0|\n+-----------------------------+------------------------------------+\n')])])])])}),[],!1,null,null,null);t.default=a.exports}}]);