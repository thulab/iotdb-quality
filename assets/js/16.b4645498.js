(window.webpackJsonp=window.webpackJsonp||[]).push([[16],{389:function(t,e,s){"use strict";s.r(e);var n=s(45),a=Object(n.a)({},(function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"twosidedfilter"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#twosidedfilter"}},[t._v("#")]),t._v(" TwoSidedFilter")]),t._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),s("p",[t._v("The function is used to filter anomalies of a numeric time series based on two-sided window detection.")]),t._v(" "),s("p",[s("strong",[t._v("Name:")]),t._v(" TWOSIDEDFILTER")]),t._v(" "),s("p",[s("strong",[t._v("Input Series:")]),t._v(" Only support a single input series. The data type is  INT32 / INT64 / FLOAT / DOUBLE")]),t._v(" "),s("p",[s("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is the same as the input. It is the input without anomalies.")]),t._v(" "),s("p",[s("strong",[t._v("Parameter:")])]),t._v(" "),s("ul",[s("li",[s("p",[s("code",[t._v("len")]),t._v(": The size of the window, which is a positive integer. By default, it's 5. When "),s("code",[t._v("len")]),t._v("=3, the algorithm detects forward window and backward window with length 3 and calculates the outlierness of the current point.")])]),t._v(" "),s("li",[s("p",[s("code",[t._v("threshold")]),t._v(": The threshold of outlierness, which is a floating number in (0,1). By default, it's 0.3. The strict standard of detecting anomalies is in proportion to the threshold.")])])]),t._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),s("p",[t._v("Input series:")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+------------+\n|                         Time|root.test.s0|\n+-----------------------------+------------+\n|1970-01-01T08:00:00.000+08:00|      2002.0|\n|1970-01-01T08:00:01.000+08:00|      1946.0|\n|1970-01-01T08:00:02.000+08:00|      1958.0|\n|1970-01-01T08:00:03.000+08:00|      2012.0|\n|1970-01-01T08:00:04.000+08:00|      2051.0|\n|1970-01-01T08:00:05.000+08:00|      1898.0|\n|1970-01-01T08:00:06.000+08:00|      2014.0|\n|1970-01-01T08:00:07.000+08:00|      2052.0|\n|1970-01-01T08:00:08.000+08:00|      1935.0|\n|1970-01-01T08:00:09.000+08:00|      1901.0|\n|1970-01-01T08:00:10.000+08:00|      1972.0|\n|1970-01-01T08:00:11.000+08:00|      1969.0|\n|1970-01-01T08:00:12.000+08:00|      1984.0|\n|1970-01-01T08:00:13.000+08:00|      2018.0|\n|1970-01-01T08:00:37.000+08:00|      1484.0|\n|1970-01-01T08:00:38.000+08:00|      1055.0|\n|1970-01-01T08:00:39.000+08:00|      1050.0|\n|1970-01-01T08:01:05.000+08:00|      1023.0|\n|1970-01-01T08:01:06.000+08:00|      1056.0|\n|1970-01-01T08:01:07.000+08:00|       978.0|\n|1970-01-01T08:01:08.000+08:00|      1050.0|\n|1970-01-01T08:01:09.000+08:00|      1123.0|\n|1970-01-01T08:01:10.000+08:00|      1150.0|\n|1970-01-01T08:01:11.000+08:00|      1034.0|\n|1970-01-01T08:01:12.000+08:00|       950.0|\n|1970-01-01T08:01:13.000+08:00|      1059.0|\n+-----------------------------+------------+\n")])])]),s("p",[t._v("SQL for query:")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" TwoSidedFilter"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s0"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'len'")]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'5'")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'threshold'")]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'0.3'")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test\n")])])]),s("p",[t._v("Output series:")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+------------+\n|                         Time|root.test.s0|\n+-----------------------------+------------+\n|1970-01-01T08:00:00.000+08:00|      2002.0|\n|1970-01-01T08:00:01.000+08:00|      1946.0|\n|1970-01-01T08:00:02.000+08:00|      1958.0|\n|1970-01-01T08:00:03.000+08:00|      2012.0|\n|1970-01-01T08:00:04.000+08:00|      2051.0|\n|1970-01-01T08:00:05.000+08:00|      1898.0|\n|1970-01-01T08:00:06.000+08:00|      2014.0|\n|1970-01-01T08:00:07.000+08:00|      2052.0|\n|1970-01-01T08:00:08.000+08:00|      1935.0|\n|1970-01-01T08:00:09.000+08:00|      1901.0|\n|1970-01-01T08:00:10.000+08:00|      1972.0|\n|1970-01-01T08:00:11.000+08:00|      1969.0|\n|1970-01-01T08:00:12.000+08:00|      1984.0|\n|1970-01-01T08:00:13.000+08:00|      2018.0|\n|1970-01-01T08:01:05.000+08:00|      1023.0|\n|1970-01-01T08:01:06.000+08:00|      1056.0|\n|1970-01-01T08:01:07.000+08:00|       978.0|\n|1970-01-01T08:01:08.000+08:00|      1050.0|\n|1970-01-01T08:01:09.000+08:00|      1123.0|\n|1970-01-01T08:01:10.000+08:00|      1150.0|\n|1970-01-01T08:01:11.000+08:00|      1034.0|\n|1970-01-01T08:01:12.000+08:00|       950.0|\n|1970-01-01T08:01:13.000+08:00|      1059.0|\n+-----------------------------+------------+\n")])])])])}),[],!1,null,null,null);e.default=a.exports}}]);