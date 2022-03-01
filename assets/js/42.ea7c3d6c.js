(window.webpackJsonp=window.webpackJsonp||[]).push([[42],{410:function(e,t,s){"use strict";s.r(t);var n=s(45),a=Object(n.a)({},(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[s("h1",{attrs:{id:"percentile"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#percentile"}},[e._v("#")]),e._v(" Percentile")]),e._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[e._v("#")]),e._v(" Usage")]),e._v(" "),s("p",[e._v("The function is used to compute the exact or approximate percentile of a numeric time series. A percentile is value of element in the certain rank of the sorted series.")]),e._v(" "),s("p",[s("strong",[e._v("Name:")]),e._v(" PERCENTILE")]),e._v(" "),s("p",[s("strong",[e._v("Input Series:")]),e._v(" Only support a single input series. The data type is INT32 / INT64 / FLOAT / DOUBLE.")]),e._v(" "),s("p",[s("strong",[e._v("Parameter:")])]),e._v(" "),s("ul",[s("li",[s("code",[e._v("rank")]),e._v(": The rank percentage of the percentile. It should be (0,1] and the default value is 0.5. For instance, a percentile with "),s("code",[e._v("rank")]),e._v("=0.5 is the median.")]),e._v(" "),s("li",[s("code",[e._v("error")]),e._v(": The rank error of the approximate percentile. It should be within [0,1) and the default value is 0. For instance, a 0.5-percentile with "),s("code",[e._v("error")]),e._v("=0.01 is the value of the element with rank percentage 0.49~0.51. With "),s("code",[e._v("error")]),e._v("=0, the output is the exact percentile.")])]),e._v(" "),s("p",[s("strong",[e._v("Output Series:")]),e._v(" Output a single series. The type is DOUBLE. There is only one data point in the series, whose timestamp is 0 and value is the percentile.")]),e._v(" "),s("p",[s("strong",[e._v("Note:")]),e._v(" Missing points, null points and "),s("code",[e._v("NaN")]),e._v(" in the input series will be ignored.")]),e._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[e._v("#")]),e._v(" Examples")]),e._v(" "),s("p",[e._v("Input series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+------------+\n|                         Time|root.test.s0|\n+-----------------------------+------------+\n|2021-03-17T10:32:17.054+08:00|   0.5319929|\n|2021-03-17T10:32:18.054+08:00|   0.9304316|\n|2021-03-17T10:32:19.054+08:00|  -1.4800133|\n|2021-03-17T10:32:20.054+08:00|   0.6114087|\n|2021-03-17T10:32:21.054+08:00|   2.5163336|\n|2021-03-17T10:32:22.054+08:00|  -1.0845392|\n|2021-03-17T10:32:23.054+08:00|   1.0562582|\n|2021-03-17T10:32:24.054+08:00|   1.3867859|\n|2021-03-17T10:32:25.054+08:00| -0.45429882|\n|2021-03-17T10:32:26.054+08:00|   1.0353678|\n|2021-03-17T10:32:27.054+08:00|   0.7307929|\n|2021-03-17T10:32:28.054+08:00|   2.3167255|\n|2021-03-17T10:32:29.054+08:00|    2.342443|\n|2021-03-17T10:32:30.054+08:00|   1.5809103|\n|2021-03-17T10:32:31.054+08:00|   1.4829416|\n|2021-03-17T10:32:32.054+08:00|   1.5800357|\n|2021-03-17T10:32:33.054+08:00|   0.7124368|\n|2021-03-17T10:32:34.054+08:00| -0.78597564|\n|2021-03-17T10:32:35.054+08:00|   1.2058644|\n|2021-03-17T10:32:36.054+08:00|   1.4215064|\n|2021-03-17T10:32:37.054+08:00|   1.2808295|\n|2021-03-17T10:32:38.054+08:00|  -0.6173715|\n|2021-03-17T10:32:39.054+08:00|  0.06644377|\n|2021-03-17T10:32:40.054+08:00|    2.349338|\n|2021-03-17T10:32:41.054+08:00|   1.7335888|\n|2021-03-17T10:32:42.054+08:00|   1.5872132|\n............\nTotal line number = 10000\n")])])]),s("p",[e._v("SQL for query:")]),e._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" percentile"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s0"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(",")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token string"}},[e._v('"rank"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[e._v('"0.2"')]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(",")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token string"}},[e._v('"error"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[e._v('"0.01"')]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test\n")])])]),s("p",[e._v("Output series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v('+-----------------------------+------------------------------------------------------+\n|                         Time|percentile(root.test.s0, "rank"="0.2", "error"="0.01")|\n+-----------------------------+------------------------------------------------------+\n|1970-01-01T08:00:00.000+08:00|                                    0.1801469624042511|\n+-----------------------------+------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);t.default=a.exports}}]);