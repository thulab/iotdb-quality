(window.webpackJsonp=window.webpackJsonp||[]).push([[120],{490:function(t,s,e){"use strict";e.r(s);var a=e(45),n=Object(a.a)({},(function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[e("h1",{attrs:{id:"stddev"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#stddev"}},[t._v("#")]),t._v(" Stddev")]),t._v(" "),e("h2",{attrs:{id:"usage"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),e("p",[t._v("This function is used to calculate the population standard deviation.")]),t._v(" "),e("p",[e("strong",[t._v("Name:")]),t._v(" STDDEV")]),t._v(" "),e("p",[e("strong",[t._v("Input Series:")]),t._v(" Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),t._v(" "),e("p",[e("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is DOUBLE. There is only one data point in the series, whose timestamp is 0 and value is the population standard deviation.")]),t._v(" "),e("p",[e("strong",[t._v("Note:")]),t._v(" Missing points, null points and "),e("code",[t._v("NaN")]),t._v(" in the input series will be ignored.")]),t._v(" "),e("h2",{attrs:{id:"examples"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),e("p",[t._v("Input series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:00.000+08:00|            1.0|\n|2020-01-01T00:00:01.000+08:00|            2.0|\n|2020-01-01T00:00:02.000+08:00|            3.0|\n|2020-01-01T00:00:03.000+08:00|            4.0|\n|2020-01-01T00:00:04.000+08:00|            5.0|\n|2020-01-01T00:00:05.000+08:00|            6.0|\n|2020-01-01T00:00:06.000+08:00|            7.0|\n|2020-01-01T00:00:07.000+08:00|            8.0|\n|2020-01-01T00:00:08.000+08:00|            9.0|\n|2020-01-01T00:00:09.000+08:00|           10.0|\n|2020-01-01T00:00:10.000+08:00|           11.0|\n|2020-01-01T00:00:11.000+08:00|           12.0|\n|2020-01-01T00:00:12.000+08:00|           13.0|\n|2020-01-01T00:00:13.000+08:00|           14.0|\n|2020-01-01T00:00:14.000+08:00|           15.0|\n|2020-01-01T00:00:15.000+08:00|           16.0|\n|2020-01-01T00:00:16.000+08:00|           17.0|\n|2020-01-01T00:00:17.000+08:00|           18.0|\n|2020-01-01T00:00:18.000+08:00|           19.0|\n|2020-01-01T00:00:19.000+08:00|           20.0|\n+-----------------------------+---------------+\n")])])]),e("p",[t._v("SQL for query:")]),t._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" stddev"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),e("p",[t._v("Output series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+-----------------------+\n|                         Time|stddev(root.test.d1.s1)|\n+-----------------------------+-----------------------+\n|1970-01-01T08:00:00.000+08:00|     5.7662812973353965|\n+-----------------------------+-----------------------+\n")])])])])}),[],!1,null,null,null);s.default=n.exports}}]);