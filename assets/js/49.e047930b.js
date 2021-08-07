(window.webpackJsonp=window.webpackJsonp||[]).push([[49],{420:function(e,t,s){"use strict";s.r(t);var a=s(45),n=Object(a.a)({},(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[s("h1",{attrs:{id:"timeweightedavg"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#timeweightedavg"}},[e._v("#")]),e._v(" TimeWeightedAvg")]),e._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[e._v("#")]),e._v(" Usage")]),e._v(" "),s("p",[e._v("This function is used to calculate the time-weighted average of time series.\nTime is weighted using the linearly interpolated integral of values,\nand the output equals to the area divided by the time interval using the same time "),s("code",[e._v("unit")]),e._v(".\nFor more information of the area under the curve, please refer to "),s("code",[e._v("Integral")]),e._v(" function.")]),e._v(" "),s("p",[s("strong",[e._v("Name:")]),e._v(" TIMEWEIGHTEDAVG")]),e._v(" "),s("p",[s("strong",[e._v("Input Series:")]),e._v(" Only support a single input numeric series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),e._v(" "),s("p",[s("strong",[e._v("Output Series:")]),e._v(" Output a single series. The type is DOUBLE. There is only one data point in the series, whose timestamp is 0 and value is the time-weighted average.")]),e._v(" "),s("p",[s("strong",[e._v("Note:")])]),e._v(" "),s("ul",[s("li",[s("p",[e._v("The time-weighted value equals to the integral value with any "),s("code",[e._v("unit")]),e._v(" divided by the time interval of input series.\nThe result is irrelevant to the time unit used in integral, and it's consistent with the timestamp precision of IoTDB by default.")])]),e._v(" "),s("li",[s("p",[s("code",[e._v("NaN")]),e._v(" values in the input series will be ignored. The curve or trapezoids will skip these points and use the next valid point.")])]),e._v(" "),s("li",[s("p",[e._v("If the input series is empty, the output value will be 0.0, but if there is only one data point, the value will equal to the input value.")])])]),e._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[e._v("#")]),e._v(" Examples")]),e._v(" "),s("p",[e._v("Input series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:01.000+08:00|              1|\n|2020-01-01T00:00:02.000+08:00|              2|\n|2020-01-01T00:00:03.000+08:00|              5|\n|2020-01-01T00:00:04.000+08:00|              6|\n|2020-01-01T00:00:05.000+08:00|              7|\n|2020-01-01T00:00:08.000+08:00|              8|\n|2020-01-01T00:00:09.000+08:00|            NaN|\n|2020-01-01T00:00:10.000+08:00|             10|\n+-----------------------------+---------------+\n")])])]),s("p",[e._v("SQL for query:")]),e._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" timeweightedavg"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d1 "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("where")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("time")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("<=")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("2020")]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("00")]),e._v(":"),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("00")]),e._v(":"),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("10")]),e._v("\n")])])]),s("p",[e._v("Output series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+--------------------------------+\n|                         Time|timeweightedavg(root.test.d1.s1)|\n+-----------------------------+--------------------------------+\n|1970-01-01T08:00:00.000+08:00|                            5.75|\n+-----------------------------+--------------------------------+\n")])])]),s("p",[e._v("Calculation expression:\n$$\\frac{1}{2}[(1+2) \\times 1 + (2+5) \\times 1 + (5+6) \\times 1 + (6+7) \\times 1 + (7+8) \\times 3 + (8+10) \\times 2] / 10 = 5.75$$")])])}),[],!1,null,null,null);t.default=n.exports}}]);