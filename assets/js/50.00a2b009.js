(window.webpackJsonp=window.webpackJsonp||[]).push([[50],{425:function(e,t,s){"use strict";s.r(t);var n=s(45),a=Object(n.a)({},(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[s("h1",{attrs:{id:"completeness"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#completeness"}},[e._v("#")]),e._v(" Completeness")]),e._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[e._v("#")]),e._v(" Usage")]),e._v(" "),s("p",[e._v("This function is used to calculate the completeness of time series. The input series are divided into several continuous and non overlapping windows. The timestamp of the first data point and the completeness of each window will be output.")]),e._v(" "),s("p",[s("strong",[e._v("Name:")]),e._v(" COMPLETENESS")]),e._v(" "),s("p",[s("strong",[e._v("Input Series:")]),e._v(" Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),e._v(" "),s("p",[s("strong",[e._v("Parameters:")])]),e._v(" "),s("ul",[s("li",[s("code",[e._v("window")]),e._v(": The size of each window. It is a positive integer or a positive number with an unit. The former is the number of data points in each window. The number of data points in the last window may be less than it. The latter is the time of the window. The unit is 'ms' for millisecond, 's' for second, 'm' for minute, 'h' for hour and 'd' for day. By default, all input data belongs to the same window.")]),e._v(" "),s("li",[s("code",[e._v("downtime")]),e._v(": Whether the downtime exception is considered in the calculation of completeness. It is 'true' or 'false' (default). When considering the downtime exception, long-term missing data will be considered as downtime exception without any influence on completeness.")])]),e._v(" "),s("p",[s("strong",[e._v("Output Series:")]),e._v(" Output a single series. The type is DOUBLE. The range of each value is [0,1].")]),e._v(" "),s("p",[s("strong",[e._v("Note:")]),e._v(" Only when the number of data points in the window exceeds 10, the calculation will be performed. Otherwise, the window will be ignored and nothing will be output.")]),e._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[e._v("#")]),e._v(" Examples")]),e._v(" "),s("h3",{attrs:{id:"default-parameters"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#default-parameters"}},[e._v("#")]),e._v(" Default Parameters")]),e._v(" "),s("p",[e._v("With default parameters, this function will regard all input data as the same window.")]),e._v(" "),s("p",[e._v("Input series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:02.000+08:00|          100.0|\n|2020-01-01T00:00:03.000+08:00|          101.0|\n|2020-01-01T00:00:04.000+08:00|          102.0|\n|2020-01-01T00:00:06.000+08:00|          104.0|\n|2020-01-01T00:00:08.000+08:00|          126.0|\n|2020-01-01T00:00:10.000+08:00|          108.0|\n|2020-01-01T00:00:14.000+08:00|          112.0|\n|2020-01-01T00:00:15.000+08:00|          113.0|\n|2020-01-01T00:00:16.000+08:00|          114.0|\n|2020-01-01T00:00:18.000+08:00|          116.0|\n|2020-01-01T00:00:20.000+08:00|          118.0|\n|2020-01-01T00:00:22.000+08:00|          120.0|\n|2020-01-01T00:00:26.000+08:00|          124.0|\n|2020-01-01T00:00:28.000+08:00|          126.0|\n|2020-01-01T00:00:30.000+08:00|            NaN|\n+-----------------------------+---------------+\n")])])]),s("p",[e._v("SQL for query:")]),e._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" completeness"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d1 "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("where")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("time")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("<=")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("2020")]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("00")]),e._v(":"),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("00")]),e._v(":"),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("30")]),e._v("\n")])])]),s("p",[e._v("Output series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+-----------------------------+\n|                         Time|completeness(root.test.d1.s1)|\n+-----------------------------+-----------------------------+\n|2020-01-01T00:00:02.000+08:00|                        0.875|\n+-----------------------------+-----------------------------+\n")])])]),s("h3",{attrs:{id:"specific-window-size"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#specific-window-size"}},[e._v("#")]),e._v(" Specific Window Size")]),e._v(" "),s("p",[e._v("When the window size is given, this function will divide the input data as multiple windows.")]),e._v(" "),s("p",[e._v("Input series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:02.000+08:00|          100.0|\n|2020-01-01T00:00:03.000+08:00|          101.0|\n|2020-01-01T00:00:04.000+08:00|          102.0|\n|2020-01-01T00:00:06.000+08:00|          104.0|\n|2020-01-01T00:00:08.000+08:00|          126.0|\n|2020-01-01T00:00:10.000+08:00|          108.0|\n|2020-01-01T00:00:14.000+08:00|          112.0|\n|2020-01-01T00:00:15.000+08:00|          113.0|\n|2020-01-01T00:00:16.000+08:00|          114.0|\n|2020-01-01T00:00:18.000+08:00|          116.0|\n|2020-01-01T00:00:20.000+08:00|          118.0|\n|2020-01-01T00:00:22.000+08:00|          120.0|\n|2020-01-01T00:00:26.000+08:00|          124.0|\n|2020-01-01T00:00:28.000+08:00|          126.0|\n|2020-01-01T00:00:30.000+08:00|            NaN|\n|2020-01-01T00:00:32.000+08:00|          130.0|\n|2020-01-01T00:00:34.000+08:00|          132.0|\n|2020-01-01T00:00:36.000+08:00|          134.0|\n|2020-01-01T00:00:38.000+08:00|          136.0|\n|2020-01-01T00:00:40.000+08:00|          138.0|\n|2020-01-01T00:00:42.000+08:00|          140.0|\n|2020-01-01T00:00:44.000+08:00|          142.0|\n|2020-01-01T00:00:46.000+08:00|          144.0|\n|2020-01-01T00:00:48.000+08:00|          146.0|\n|2020-01-01T00:00:50.000+08:00|          148.0|\n|2020-01-01T00:00:52.000+08:00|          150.0|\n|2020-01-01T00:00:54.000+08:00|          152.0|\n|2020-01-01T00:00:56.000+08:00|          154.0|\n|2020-01-01T00:00:58.000+08:00|          156.0|\n|2020-01-01T00:01:00.000+08:00|          158.0|\n+-----------------------------+---------------+\n")])])]),s("p",[e._v("SQL for query:")]),e._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" completeness"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(",")]),s("span",{pre:!0,attrs:{class:"token string"}},[e._v('"window"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[e._v('"15"')]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d1 "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("where")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("time")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("<=")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("2020")]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("00")]),e._v(":"),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),e._v(":"),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("00")]),e._v("\n")])])]),s("p",[e._v("Output series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v('+-----------------------------+--------------------------------------------+\n|                         Time|completeness(root.test.d1.s1, "window"="15")|\n+-----------------------------+--------------------------------------------+\n|2020-01-01T00:00:02.000+08:00|                                       0.875|\n|2020-01-01T00:00:32.000+08:00|                                         1.0|\n+-----------------------------+--------------------------------------------+\n')])])])])}),[],!1,null,null,null);t.default=a.exports}}]);