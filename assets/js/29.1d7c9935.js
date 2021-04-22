(window.webpackJsonp=window.webpackJsonp||[]).push([[29],{399:function(t,e,s){"use strict";s.r(e);var a=s(45),n=Object(a.a)({},(function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"range-todo"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#range-todo"}},[t._v("#")]),t._v(" Range(TODO)")]),t._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),s("p",[t._v("This function is used to detect range anomaly of time series. According to upper bound and lower bound parameters, the function judges if a input value is beyond range, aka range anomaly, and a new time series of anomaly will be output.")]),t._v(" "),s("p",[s("strong",[t._v("Name:")]),t._v(" RANGE")]),t._v(" "),s("p",[s("strong",[t._v("Input Series:")]),t._v(" Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),t._v(" "),s("ul",[s("li",[s("code",[t._v("lower_bound")]),t._v(":lower bound of range anomaly detection.")]),t._v(" "),s("li",[s("code",[t._v("upper_bound")]),t._v(":upper bound of range anomaly detection.")])]),t._v(" "),s("p",[s("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is DOUBLE.")]),t._v(" "),s("p",[s("strong",[t._v("Note:")]),t._v(" Only when upper_bound is larger than lower_bound, the anomaly detection will be performed. Otherwise, nothing will be output.")]),t._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),s("h3",{attrs:{id:"assigning-lower-and-upper-bound"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#assigning-lower-and-upper-bound"}},[t._v("#")]),t._v(" Assigning Lower and Upper Bound")]),t._v(" "),s("p",[t._v("Input series:")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:02.000+08:00|          100.0|\n|2020-01-01T00:00:03.000+08:00|          101.0|\n|2020-01-01T00:00:04.000+08:00|          102.0|\n|2020-01-01T00:00:06.000+08:00|          104.0|\n|2020-01-01T00:00:08.000+08:00|          126.0|\n|2020-01-01T00:00:10.000+08:00|          108.0|\n|2020-01-01T00:00:14.000+08:00|          112.0|\n|2020-01-01T00:00:15.000+08:00|          113.0|\n|2020-01-01T00:00:16.000+08:00|          114.0|\n|2020-01-01T00:00:18.000+08:00|          116.0|\n|2020-01-01T00:00:20.000+08:00|          118.0|\n|2020-01-01T00:00:22.000+08:00|          120.0|\n|2020-01-01T00:00:26.000+08:00|          124.0|\n|2020-01-01T00:00:28.000+08:00|          126.0|\n|2020-01-01T00:00:30.000+08:00|            NaN|\n+-----------------------------+---------------+\n")])])]),s("p",[t._v("SQL for query:")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" range"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v('"lower_bound"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v('"101.0"')]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v('"upper_bound"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v('"125.0"')]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1 "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("where")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("time")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("<=")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("2020")]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("01")]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("01")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("00")]),t._v(":"),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("00")]),t._v(":"),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("30")]),t._v("\n")])])]),s("p",[t._v("Output series:")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v('+-----------------------------+------------------------------------------------------------------+\n|Time                         |range(root.test.d1.s1,"lower_bound"="101.0","upper_bound"="125.0")|\n+-----------------------------+------------------------------------------------------------------+\n|2020-01-01T00:00:02.000+08:00|                                                             100.0|\n|2020-01-01T00:00:28.000+08:00|                                                             126.0|\n+-----------------------------+------------------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);e.default=n.exports}}]);