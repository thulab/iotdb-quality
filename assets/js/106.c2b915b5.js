(window.webpackJsonp=window.webpackJsonp||[]).push([[106],{476:function(e,t,a){"use strict";a.r(t);var s=a(45),n=Object(s.a)({},(function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[a("h1",{attrs:{id:"median"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#median"}},[e._v("#")]),e._v(" Median")]),e._v(" "),a("h2",{attrs:{id:"usage"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[e._v("#")]),e._v(" Usage")]),e._v(" "),a("p",[e._v("The function is used to compute the exact or approximate median of a numeric time series. Median is the value separating the higher half from the lower half of a data sample.")]),e._v(" "),a("p",[a("strong",[e._v("Name:")]),e._v(" MEDIAN")]),e._v(" "),a("p",[a("strong",[e._v("Input Series:")]),e._v(" Only support a single input series. The data type is INT32 / INT64 / FLOAT / DOUBLE.")]),e._v(" "),a("p",[a("strong",[e._v("Parameter:")])]),e._v(" "),a("ul",[a("li",[a("code",[e._v("error")]),e._v(": The rank error of the approximate median. It should be within [0,1) and the default value is 0. For instance, a median with "),a("code",[e._v("error")]),e._v("=0.01 is the value of the element with rank percentage 0.49~0.51. With "),a("code",[e._v("error")]),e._v("=0, the output is the exact median.")])]),e._v(" "),a("p",[a("strong",[e._v("Output Series:")]),e._v(" Output a single series. The type is DOUBLE. There is only one data point in the series, whose timestamp is 0 and value is the median.")]),e._v(" "),a("h2",{attrs:{id:"examples"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[e._v("#")]),e._v(" Examples")]),e._v(" "),a("p",[e._v("Input series:")]),e._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v("+-----------------------------+------------+\n|                         Time|root.test.s0|\n+-----------------------------+------------+\n|2021-03-17T10:32:17.054+08:00|   0.5319929|\n|2021-03-17T10:32:18.054+08:00|   0.9304316|\n|2021-03-17T10:32:19.054+08:00|  -1.4800133|\n|2021-03-17T10:32:20.054+08:00|   0.6114087|\n|2021-03-17T10:32:21.054+08:00|   2.5163336|\n|2021-03-17T10:32:22.054+08:00|  -1.0845392|\n|2021-03-17T10:32:23.054+08:00|   1.0562582|\n|2021-03-17T10:32:24.054+08:00|   1.3867859|\n|2021-03-17T10:32:25.054+08:00| -0.45429882|\n|2021-03-17T10:32:26.054+08:00|   1.0353678|\n|2021-03-17T10:32:27.054+08:00|   0.7307929|\n|2021-03-17T10:32:28.054+08:00|   2.3167255|\n|2021-03-17T10:32:29.054+08:00|    2.342443|\n|2021-03-17T10:32:30.054+08:00|   1.5809103|\n|2021-03-17T10:32:31.054+08:00|   1.4829416|\n|2021-03-17T10:32:32.054+08:00|   1.5800357|\n|2021-03-17T10:32:33.054+08:00|   0.7124368|\n|2021-03-17T10:32:34.054+08:00| -0.78597564|\n|2021-03-17T10:32:35.054+08:00|   1.2058644|\n|2021-03-17T10:32:36.054+08:00|   1.4215064|\n|2021-03-17T10:32:37.054+08:00|   1.2808295|\n|2021-03-17T10:32:38.054+08:00|  -0.6173715|\n|2021-03-17T10:32:39.054+08:00|  0.06644377|\n|2021-03-17T10:32:40.054+08:00|    2.349338|\n|2021-03-17T10:32:41.054+08:00|   1.7335888|\n|2021-03-17T10:32:42.054+08:00|   1.5872132|\n............\nTotal line number = 10000\n")])])]),a("p",[e._v("SQL for query:")]),e._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" median"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s0"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(",")]),e._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[e._v('"error"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[e._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[e._v('"0.01"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test\n")])])]),a("p",[e._v("Output series:")]),e._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[e._v('+-----------------------------+------------------------------------+\n|                         Time|median(root.test.s0, "error"="0.01")|\n+-----------------------------+------------------------------------+\n|1970-01-01T08:00:00.000+08:00|                   1.021884560585022|\n+-----------------------------+------------------------------------+\n')])])])])}),[],!1,null,null,null);t.default=n.exports}}]);