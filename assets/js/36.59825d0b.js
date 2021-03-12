(window.webpackJsonp=window.webpackJsonp||[]).push([[36],{416:function(s,t,e){"use strict";e.r(t);var a=e(45),n=Object(a.a)({},(function(){var s=this,t=s.$createElement,e=s._self._c||t;return e("ContentSlotsDistributor",{attrs:{"slot-key":s.$parent.slotKey}},[e("h1",{attrs:{id:"timeliness"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#timeliness"}},[s._v("#")]),s._v(" Timeliness")]),s._v(" "),e("h2",{attrs:{id:"usage"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[s._v("#")]),s._v(" Usage")]),s._v(" "),e("p",[s._v("This function is used to calculate the timeliness of time series. The input series are divided into several continuous and non overlapping windows. The timestamp of the first data point and the timeliness of each window will be output.")]),s._v(" "),e("p",[e("strong",[s._v("Name:")]),s._v(" TIMELINESS")]),s._v(" "),e("p",[e("strong",[s._v("Input Series:")]),s._v(" Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),s._v(" "),e("p",[e("strong",[s._v("Parameters:")])]),s._v(" "),e("ul",[e("li",[e("code",[s._v("window")]),s._v(": The number of data points in each window. The number of data points in the last window may be less than it. By default, all input data belongs to the same window.")])]),s._v(" "),e("p",[e("strong",[s._v("Output Series:")]),s._v(" Output a single series. The type is DOUBLE. The range of each value is [0,1].")]),s._v(" "),e("p",[e("strong",[s._v("Note:")]),s._v(" Only when the number of data points in the window exceeds 10, the calculation will be performed. Otherwise, the window will be ignored and nothing will be output.")]),s._v(" "),e("h2",{attrs:{id:"examples"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[s._v("#")]),s._v(" Examples")]),s._v(" "),e("h3",{attrs:{id:"default-parameters"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#default-parameters"}},[s._v("#")]),s._v(" Default Parameters")]),s._v(" "),e("p",[s._v("With default parameters, this function will regard all input data as the same window.")]),s._v(" "),e("p",[s._v("Input series:")]),s._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[s._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:02.000+08:00|          100.0|\n|2020-01-01T00:00:03.000+08:00|          101.0|\n|2020-01-01T00:00:04.000+08:00|          102.0|\n|2020-01-01T00:00:06.000+08:00|          104.0|\n|2020-01-01T00:00:08.000+08:00|          126.0|\n|2020-01-01T00:00:10.000+08:00|          108.0|\n|2020-01-01T00:00:14.000+08:00|          112.0|\n|2020-01-01T00:00:15.000+08:00|          113.0|\n|2020-01-01T00:00:16.000+08:00|          114.0|\n|2020-01-01T00:00:18.000+08:00|          116.0|\n|2020-01-01T00:00:20.000+08:00|          118.0|\n|2020-01-01T00:00:22.000+08:00|          120.0|\n|2020-01-01T00:00:26.000+08:00|          124.0|\n|2020-01-01T00:00:28.000+08:00|          126.0|\n|2020-01-01T00:00:30.000+08:00|            NaN|\n+-----------------------------+---------------+\n")])])]),e("p",[s._v("SQL for query:")]),s._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("select")]),s._v(" timeliness"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("(")]),s._v("s1"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(")")]),s._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("from")]),s._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("test"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("d1 "),e("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("where")]),s._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("time")]),s._v(" "),e("span",{pre:!0,attrs:{class:"token operator"}},[s._v("<=")]),s._v(" "),e("span",{pre:!0,attrs:{class:"token number"}},[s._v("2020")]),e("span",{pre:!0,attrs:{class:"token operator"}},[s._v("-")]),e("span",{pre:!0,attrs:{class:"token number"}},[s._v("01")]),e("span",{pre:!0,attrs:{class:"token operator"}},[s._v("-")]),e("span",{pre:!0,attrs:{class:"token number"}},[s._v("01")]),s._v(" "),e("span",{pre:!0,attrs:{class:"token number"}},[s._v("00")]),s._v(":"),e("span",{pre:!0,attrs:{class:"token number"}},[s._v("00")]),s._v(":"),e("span",{pre:!0,attrs:{class:"token number"}},[s._v("30")]),s._v("\n")])])]),e("p",[s._v("Output series:")]),s._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[s._v("+-----------------------------+---------------------------+\n|                         Time|timeliness(root.test.d1.s1)|\n+-----------------------------+---------------------------+\n|2020-01-01T00:00:02.000+08:00|         0.9333333333333333|\n+-----------------------------+---------------------------+\n")])])]),e("h3",{attrs:{id:"specific-window-size"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#specific-window-size"}},[s._v("#")]),s._v(" Specific Window Size")]),s._v(" "),e("p",[s._v("When the window size is given, this function will divide the input data as multiple windows.")]),s._v(" "),e("p",[s._v("Input series:")]),s._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[s._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:02.000+08:00|          100.0|\n|2020-01-01T00:00:03.000+08:00|          101.0|\n|2020-01-01T00:00:04.000+08:00|          102.0|\n|2020-01-01T00:00:06.000+08:00|          104.0|\n|2020-01-01T00:00:08.000+08:00|          126.0|\n|2020-01-01T00:00:10.000+08:00|          108.0|\n|2020-01-01T00:00:14.000+08:00|          112.0|\n|2020-01-01T00:00:15.000+08:00|          113.0|\n|2020-01-01T00:00:16.000+08:00|          114.0|\n|2020-01-01T00:00:18.000+08:00|          116.0|\n|2020-01-01T00:00:20.000+08:00|          118.0|\n|2020-01-01T00:00:22.000+08:00|          120.0|\n|2020-01-01T00:00:26.000+08:00|          124.0|\n|2020-01-01T00:00:28.000+08:00|          126.0|\n|2020-01-01T00:00:30.000+08:00|            NaN|\n|2020-01-01T00:00:32.000+08:00|          130.0|\n|2020-01-01T00:00:34.000+08:00|          132.0|\n|2020-01-01T00:00:36.000+08:00|          134.0|\n|2020-01-01T00:00:38.000+08:00|          136.0|\n|2020-01-01T00:00:40.000+08:00|          138.0|\n|2020-01-01T00:00:42.000+08:00|          140.0|\n|2020-01-01T00:00:44.000+08:00|          142.0|\n|2020-01-01T00:00:46.000+08:00|          144.0|\n|2020-01-01T00:00:48.000+08:00|          146.0|\n|2020-01-01T00:00:50.000+08:00|          148.0|\n|2020-01-01T00:00:52.000+08:00|          150.0|\n|2020-01-01T00:00:54.000+08:00|          152.0|\n|2020-01-01T00:00:56.000+08:00|          154.0|\n|2020-01-01T00:00:58.000+08:00|          156.0|\n|2020-01-01T00:01:00.000+08:00|          158.0|\n+-----------------------------+---------------+\n")])])]),e("p",[s._v("SQL for query:")]),s._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("select")]),s._v(" timeliness"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("(")]),s._v("s1"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),e("span",{pre:!0,attrs:{class:"token string"}},[s._v('"window"')]),e("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),e("span",{pre:!0,attrs:{class:"token string"}},[s._v('"15"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(")")]),s._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("from")]),s._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("test"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("d1 "),e("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("where")]),s._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("time")]),s._v(" "),e("span",{pre:!0,attrs:{class:"token operator"}},[s._v("<=")]),s._v(" "),e("span",{pre:!0,attrs:{class:"token number"}},[s._v("2020")]),e("span",{pre:!0,attrs:{class:"token operator"}},[s._v("-")]),e("span",{pre:!0,attrs:{class:"token number"}},[s._v("01")]),e("span",{pre:!0,attrs:{class:"token operator"}},[s._v("-")]),e("span",{pre:!0,attrs:{class:"token number"}},[s._v("01")]),s._v(" "),e("span",{pre:!0,attrs:{class:"token number"}},[s._v("00")]),s._v(":"),e("span",{pre:!0,attrs:{class:"token number"}},[s._v("01")]),s._v(":"),e("span",{pre:!0,attrs:{class:"token number"}},[s._v("00")]),s._v("\n")])])]),e("p",[s._v("Output series:")]),s._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[s._v('+-----------------------------+------------------------------------------+\n|                         Time|timeliness(root.test.d1.s1, "window"="15")|\n+-----------------------------+------------------------------------------+\n|2020-01-01T00:00:02.000+08:00|                        0.9333333333333333|\n|2020-01-01T00:00:32.000+08:00|                                       1.0|\n+-----------------------------+------------------------------------------+\n')])])])])}),[],!1,null,null,null);t.default=n.exports}}]);