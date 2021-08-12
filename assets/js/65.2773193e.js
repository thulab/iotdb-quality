(window.webpackJsonp=window.webpackJsonp||[]).push([[65],{443:function(t,e,s){"use strict";s.r(e);var a=s(45),n=Object(a.a)({},(function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"consecutivesequences"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#consecutivesequences"}},[t._v("#")]),t._v(" ConsecutiveSequences")]),t._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),s("p",[t._v("This function is used to find locally longest consecutive subsequences in strictly equispaced multidimensional data.")]),t._v(" "),s("p",[t._v("Strictly equispaced data is the data whose time intervals are strictly equal. Missing data, including missing rows and missing values, is allowed in it, while data redundancy and timestamp drift is not allowed.")]),t._v(" "),s("p",[t._v("Consecutive subsequence is the subsequence that is strictly equispaced with the standard time interval without any missing data. If a consecutive subsequence is not a proper subsequence of any consecutive subsequence, it is locally longest.")]),t._v(" "),s("p",[s("strong",[t._v("Name:")]),t._v(" CONSECUTIVESEQUENCES")]),t._v(" "),s("p",[s("strong",[t._v("Input Series:")]),t._v(" Support multiple input series. The type is arbitrary but the data is strictly equispaced.")]),t._v(" "),s("p",[s("strong",[t._v("Parameters:")])]),t._v(" "),s("ul",[s("li",[s("code",[t._v("gap")]),t._v(": The standard time interval which is a positive number with an unit. The unit is 'ms' for millisecond, 's' for second, 'm' for minute, 'h' for hour and 'd' for day. By default, it will be estimated by the mode of time intervals.")])]),t._v(" "),s("p",[s("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is INT32. Each data point in the output series corresponds to a locally longest consecutive subsequence. The output timestamp is the starting timestamp of the subsequence and the output value is the number of data points in the subsequence.")]),t._v(" "),s("p",[s("strong",[t._v("Note:")]),t._v(" For input series that is not strictly equispaced, there is no guarantee on the output.")]),t._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),s("h3",{attrs:{id:"manually-specify-the-standard-time-interval"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#manually-specify-the-standard-time-interval"}},[t._v("#")]),t._v(" Manually Specify the Standard Time Interval")]),t._v(" "),s("p",[t._v("It's able to manually specify the standard time interval by the parameter "),s("code",[t._v("gap")]),t._v(". It's notable that false parameter leads to false output.")]),t._v(" "),s("p",[t._v("Input series:")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+---------------+---------------+\n|                         Time|root.test.d1.s1|root.test.d1.s2|\n+-----------------------------+---------------+---------------+\n|2020-01-01T00:00:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:05:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:10:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:20:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:25:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:30:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:35:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:40:00.000+08:00|            1.0|           null|\n|2020-01-01T00:45:00.000+08:00|            1.0|            1.0|\n|2020-01-01T00:50:00.000+08:00|            1.0|            1.0|\n+-----------------------------+---------------+---------------+\n")])])]),s("p",[t._v("SQL for query:")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" consecutivesequences"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("s2"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'gap'")]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'5m'")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),s("p",[t._v("Output series:")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v('+-----------------------------+------------------------------------------------------------------+\n|                         Time|consecutivesequences(root.test.d1.s1, root.test.d1.s2, "gap"="5m")|\n+-----------------------------+------------------------------------------------------------------+\n|2020-01-01T00:00:00.000+08:00|                                                                 3|\n|2020-01-01T00:20:00.000+08:00|                                                                 4|\n|2020-01-01T00:45:00.000+08:00|                                                                 2|\n+-----------------------------+------------------------------------------------------------------+\n')])])]),s("h3",{attrs:{id:"automatically-estimate-the-standard-time-interval"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#automatically-estimate-the-standard-time-interval"}},[t._v("#")]),t._v(" Automatically Estimate the Standard Time Interval")]),t._v(" "),s("p",[t._v("When "),s("code",[t._v("gap")]),t._v(" is default, this function estimates the standard time interval by the mode of time intervals and gets the same results. Therefore, this usage is more recommended.")]),t._v(" "),s("p",[t._v("Input series is the same as above, the SQL for query is shown below:")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" consecutivesequences"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("s2"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),s("p",[t._v("Output series:")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+------------------------------------------------------+\n|                         Time|consecutivesequences(root.test.d1.s1, root.test.d1.s2)|\n+-----------------------------+------------------------------------------------------+\n|2020-01-01T00:00:00.000+08:00|                                                     3|\n|2020-01-01T00:20:00.000+08:00|                                                     4|\n|2020-01-01T00:45:00.000+08:00|                                                     2|\n+-----------------------------+------------------------------------------------------+\n")])])])])}),[],!1,null,null,null);e.default=n.exports}}]);