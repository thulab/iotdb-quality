(window.webpackJsonp=window.webpackJsonp||[]).push([[11],{379:function(e,s,t){"use strict";t.r(s);var a=t(45),n=Object(a.a)({},(function(){var e=this,s=e.$createElement,t=e._self._c||s;return t("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[t("h1",{attrs:{id:"range"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#range"}},[e._v("#")]),e._v(" Range")]),e._v(" "),t("h2",{attrs:{id:"usage"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[e._v("#")]),e._v(" Usage")]),e._v(" "),t("p",[e._v("This function is used to detect range anomaly of time series. According to upper bound and lower bound parameters, the function judges if a input value is beyond range, aka range anomaly, and a new time series of anomaly will be output.")]),e._v(" "),t("p",[t("strong",[e._v("Name:")]),e._v(" RANGE")]),e._v(" "),t("p",[t("strong",[e._v("Input Series:")]),e._v(" Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),e._v(" "),t("ul",[t("li",[t("code",[e._v("lower_bound")]),e._v(":lower bound of range anomaly detection.")]),e._v(" "),t("li",[t("code",[e._v("upper_bound")]),e._v(":upper bound of range anomaly detection.")])]),e._v(" "),t("p",[t("strong",[e._v("Output Series:")]),e._v(" Output a single series. The type is DOUBLE.")]),e._v(" "),t("p",[t("strong",[e._v("Note:")]),e._v(" Only when upper_bound is larger than lower_bound, the anomaly detection will be performed. Otherwise, nothing will be output.")]),e._v(" "),t("h2",{attrs:{id:"examples"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[e._v("#")]),e._v(" Examples")]),e._v(" "),t("h3",{attrs:{id:"assigning-lower-and-upper-bound"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#assigning-lower-and-upper-bound"}},[e._v("#")]),e._v(" Assigning Lower and Upper Bound")]),e._v(" "),t("p",[e._v("Input series:")]),e._v(" "),t("div",{staticClass:"language- extra-class"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[e._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:02.000+08:00|          100.0|\n|2020-01-01T00:00:03.000+08:00|          101.0|\n|2020-01-01T00:00:04.000+08:00|          102.0|\n|2020-01-01T00:00:06.000+08:00|          104.0|\n|2020-01-01T00:00:08.000+08:00|          126.0|\n|2020-01-01T00:00:10.000+08:00|          108.0|\n|2020-01-01T00:00:14.000+08:00|          112.0|\n|2020-01-01T00:00:15.000+08:00|          113.0|\n|2020-01-01T00:00:16.000+08:00|          114.0|\n|2020-01-01T00:00:18.000+08:00|          116.0|\n|2020-01-01T00:00:20.000+08:00|          118.0|\n|2020-01-01T00:00:22.000+08:00|          120.0|\n|2020-01-01T00:00:26.000+08:00|          124.0|\n|2020-01-01T00:00:28.000+08:00|          126.0|\n|2020-01-01T00:00:30.000+08:00|            NaN|\n+-----------------------------+---------------+\n")])])]),t("p",[e._v("SQL for query:")]),e._v(" "),t("div",{staticClass:"language-sql extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sql"}},[t("code",[t("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" range"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s1"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(",")]),t("span",{pre:!0,attrs:{class:"token string"}},[e._v('"lower_bound"')]),t("span",{pre:!0,attrs:{class:"token operator"}},[e._v("=")]),t("span",{pre:!0,attrs:{class:"token string"}},[e._v('"101.0"')]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(",")]),t("span",{pre:!0,attrs:{class:"token string"}},[e._v('"upper_bound"')]),t("span",{pre:!0,attrs:{class:"token operator"}},[e._v("=")]),t("span",{pre:!0,attrs:{class:"token string"}},[e._v('"125.0"')]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),t("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d1 "),t("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("where")]),e._v(" "),t("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("time")]),e._v(" "),t("span",{pre:!0,attrs:{class:"token operator"}},[e._v("<=")]),e._v(" "),t("span",{pre:!0,attrs:{class:"token number"}},[e._v("2020")]),t("span",{pre:!0,attrs:{class:"token operator"}},[e._v("-")]),t("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),t("span",{pre:!0,attrs:{class:"token operator"}},[e._v("-")]),t("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),e._v(" "),t("span",{pre:!0,attrs:{class:"token number"}},[e._v("00")]),e._v(":"),t("span",{pre:!0,attrs:{class:"token number"}},[e._v("00")]),e._v(":"),t("span",{pre:!0,attrs:{class:"token number"}},[e._v("30")]),e._v("\n")])])]),t("p",[e._v("Output series:")]),e._v(" "),t("div",{staticClass:"language- extra-class"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[e._v('+-----------------------------+------------------------------------------------------------------+\n|Time                         |range(root.test.d1.s1,"lower_bound"="101.0","upper_bound"="125.0")|\n+-----------------------------+------------------------------------------------------------------+\n|2020-01-01T00:00:02.000+08:00|                                                             100.0|\n|2020-01-01T00:00:28.000+08:00|                                                             126.0|\n+-----------------------------+------------------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=n.exports}}]);