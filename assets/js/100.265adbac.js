(window.webpackJsonp=window.webpackJsonp||[]).push([[100],{469:function(t,s,e){"use strict";e.r(s);var a=e(45),n=Object(a.a)({},(function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[e("h1",{attrs:{id:"acf"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#acf"}},[t._v("#")]),t._v(" ACF")]),t._v(" "),e("h2",{attrs:{id:"usage"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),e("p",[t._v("This function is used to calculate the auto-correlation factor of the input time series,\nwhich equals to cross correlation between the same series.\nFor more information, please refer to "),e("code",[t._v("XCorr")]),t._v(" function.")]),t._v(" "),e("p",[e("strong",[t._v("Name:")]),t._v(" ACF")]),t._v(" "),e("p",[e("strong",[t._v("Input Series:")]),t._v(" Only support a single input numeric series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),t._v(" "),e("p",[e("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is DOUBLE.\nThere are "),e("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[e("mjx-math",{staticClass:" MJX-TEX"},[e("mjx-mn",{staticClass:"mjx-n"},[e("mjx-c",{attrs:{c:"2"}})],1),e("mjx-mi",{staticClass:"mjx-i"},[e("mjx-c",{attrs:{c:"N"}})],1),e("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[e("mjx-c",{attrs:{c:"2212"}})],1),e("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[e("mjx-c",{attrs:{c:"1"}})],1)],1)],1),t._v(" data points in the series, and the values are interpreted in details in "),e("code",[t._v("XCorr")]),t._v(" function.")],1),t._v(" "),e("p",[e("strong",[t._v("Note:")])]),t._v(" "),e("ul",[e("li",[e("code",[t._v("null")]),t._v(" and "),e("code",[t._v("NaN")]),t._v(" values in the input series will be ignored and treated as 0.")])]),t._v(" "),e("h2",{attrs:{id:"examples"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),e("p",[t._v("Input series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:01.000+08:00|              1|\n|2020-01-01T00:00:02.000+08:00|           null|\n|2020-01-01T00:00:03.000+08:00|              3|\n|2020-01-01T00:00:04.000+08:00|            NaN|\n|2020-01-01T00:00:05.000+08:00|              5|\n+-----------------------------+---------------+\n")])])]),e("p",[t._v("SQL for query:")]),t._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" acf"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1 "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("where")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("time")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("<=")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token number"}},[t._v("2020")]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("-")]),e("span",{pre:!0,attrs:{class:"token number"}},[t._v("01")]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("-")]),e("span",{pre:!0,attrs:{class:"token number"}},[t._v("01")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token number"}},[t._v("00")]),t._v(":"),e("span",{pre:!0,attrs:{class:"token number"}},[t._v("00")]),t._v(":"),e("span",{pre:!0,attrs:{class:"token number"}},[t._v("05")]),t._v("\n")])])]),e("p",[t._v("Output series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+--------------------+\n|                         Time|acf(root.test.d1.s1)|\n+-----------------------------+--------------------+\n|1970-01-01T08:00:00.001+08:00|                 1.0|\n|1970-01-01T08:00:00.002+08:00|                 0.0|\n|1970-01-01T08:00:00.003+08:00|                 3.6|\n|1970-01-01T08:00:00.004+08:00|                 0.0|\n|1970-01-01T08:00:00.005+08:00|                 7.0|\n|1970-01-01T08:00:00.006+08:00|                 0.0|\n|1970-01-01T08:00:00.007+08:00|                 3.6|\n|1970-01-01T08:00:00.008+08:00|                 0.0|\n|1970-01-01T08:00:00.009+08:00|                 1.0|\n+-----------------------------+--------------------+\n")])])])])}),[],!1,null,null,null);s.default=n.exports}}]);