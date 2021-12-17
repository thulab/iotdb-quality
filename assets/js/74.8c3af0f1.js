(window.webpackJsonp=window.webpackJsonp||[]).push([[74],{444:function(t,s,e){"use strict";e.r(s);var a=e(45),n=Object(a.a)({},(function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[e("h1",{attrs:{id:"consecutivewindows"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#consecutivewindows"}},[t._v("#")]),t._v(" ConsecutiveWindows")]),t._v(" "),e("h2",{attrs:{id:"usage"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),e("p",[t._v("This function is used to find consecutive windows of specified length in strictly equispaced multidimensional data.")]),t._v(" "),e("p",[t._v("Strictly equispaced data is the data whose time intervals are strictly equal. Missing data, including missing rows and missing values, is allowed in it, while data redundancy and timestamp drift is not allowed.")]),t._v(" "),e("p",[t._v("Consecutive window is the subsequence that is strictly equispaced with the standard time interval without any missing data.")]),t._v(" "),e("p",[e("strong",[t._v("Name:")]),t._v(" CONSECUTIVEWINDOWS")]),t._v(" "),e("p",[e("strong",[t._v("Input Series:")]),t._v(" Support multiple input series. The type is arbitrary but the data is strictly equispaced.")]),t._v(" "),e("p",[e("strong",[t._v("Parameters:")])]),t._v(" "),e("ul",[e("li",[e("code",[t._v("gap")]),t._v(": The standard time interval which is a positive number with an unit. The unit is 'ms' for millisecond, 's' for second, 'm' for minute, 'h' for hour and 'd' for day. By default, it will be estimated by the mode of time intervals.")]),t._v(" "),e("li",[e("code",[t._v("length")]),t._v(": The length of the window which is a positive number with an unit. The unit is 'ms' for millisecond, 's' for second, 'm' for minute, 'h' for hour and 'd' for day. This parameter cannot be lacked.")])]),t._v(" "),e("p",[e("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is INT32. Each data point in the output series corresponds to a consecutive window. The output timestamp is the starting timestamp of the window and the output value is the number of data points in the window.")]),t._v(" "),e("p",[e("strong",[t._v("Note:")]),t._v(" For input series that is not strictly equispaced, there is no guarantee on the output.")]),t._v(" "),e("h2",{attrs:{id:"examples"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),e("p",[t._v("Input series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+---------------+---------------+\n|                         Time|root.test.d1.s1|root.test.d1.s2|\n+-----------------------------+---------------+---------------+\n|2020-01-01T00:00:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:05:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:10:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:20:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:25:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:30:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:35:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:40:00.000+08:00|            1.0|           null|\n|2020-01-01T00:45:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:50:00.000+08:00|            1.0|            1.0|\n+-----------------------------+---------------+---------------+\n")])])]),e("p",[t._v("SQL for query:")]),t._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" consecutivewindows"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("s2"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v("'length'")]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v("'10m'")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),e("p",[t._v("Output series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v('+-----------------------------+--------------------------------------------------------------------+\n|                         Time|consecutivewindows(root.test.d1.s1, root.test.d1.s2, "length"="10m")|\n+-----------------------------+--------------------------------------------------------------------+\n|2020-01-01T00:00:00.000+08:00|                                                                   3|\n|2020-01-01T00:20:00.000+08:00|                                                                   3|\n|2020-01-01T00:25:00.000+08:00|                                                                   3|\n+-----------------------------+--------------------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=n.exports}}]);