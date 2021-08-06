(window.webpackJsonp=window.webpackJsonp||[]).push([[45],{419:function(e,s,t){"use strict";t.r(s);var a=t(45),r=Object(a.a)({},(function(){var e=this,s=e.$createElement,t=e._self._c||s;return t("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[t("h1",{attrs:{id:"selfcorrelation"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#selfcorrelation"}},[e._v("#")]),e._v(" SelfCorrelation")]),e._v(" "),t("h2",{attrs:{id:"usage"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[e._v("#")]),e._v(" Usage")]),e._v(" "),t("p",[e._v("This function is used to calculate the self-correlation function of the input time series,\nwhich equals to cross correlation between the same series.\nFor more information, please refer to "),t("code",[e._v("CrossCorrelation")]),e._v(" function.")]),e._v(" "),t("p",[t("strong",[e._v("Name:")]),e._v(" SELFCORRELATION")]),e._v(" "),t("p",[t("strong",[e._v("Input Series:")]),e._v(" Only support a single input numeric series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),e._v(" "),t("p",[t("strong",[e._v("Output Series:")]),e._v(" Output a single series. The type is DOUBLE.\nThere are $2N-1$ data points in the series, and the values are interpreted in details in "),t("code",[e._v("CrossCorrelation")]),e._v(" function.")]),e._v(" "),t("p",[t("strong",[e._v("Note:")])]),e._v(" "),t("ul",[t("li",[t("code",[e._v("null")]),e._v(" and "),t("code",[e._v("NaN")]),e._v(" values in the input series will be ignored and treated as 0.")])]),e._v(" "),t("h2",{attrs:{id:"examples"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[e._v("#")]),e._v(" Examples")]),e._v(" "),t("p",[e._v("Input series:")]),e._v(" "),t("div",{staticClass:"language- extra-class"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[e._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:01.000+08:00|              1|\n|2020-01-01T00:00:02.000+08:00|           null|\n|2020-01-01T00:00:03.000+08:00|              3|\n|2020-01-01T00:00:04.000+08:00|            NaN|\n|2020-01-01T00:00:05.000+08:00|              5|\n+-----------------------------+---------------+\n")])])]),t("p",[e._v("SQL for query:")]),e._v(" "),t("div",{staticClass:"language-sql extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sql"}},[t("code",[t("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" selfcorrelation"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s1"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),t("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d1 "),t("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("where")]),e._v(" "),t("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("time")]),e._v(" "),t("span",{pre:!0,attrs:{class:"token operator"}},[e._v("<=")]),e._v(" "),t("span",{pre:!0,attrs:{class:"token number"}},[e._v("2020")]),t("span",{pre:!0,attrs:{class:"token operator"}},[e._v("-")]),t("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),t("span",{pre:!0,attrs:{class:"token operator"}},[e._v("-")]),t("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),e._v(" "),t("span",{pre:!0,attrs:{class:"token number"}},[e._v("00")]),e._v(":"),t("span",{pre:!0,attrs:{class:"token number"}},[e._v("00")]),e._v(":"),t("span",{pre:!0,attrs:{class:"token number"}},[e._v("05")]),e._v("\n")])])]),t("p",[e._v("Output series:")]),e._v(" "),t("div",{staticClass:"language- extra-class"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[e._v("+-----------------------------+--------------------------------+\n|                         Time|selfcorrelation(root.test.d1.s1)|\n+-----------------------------+--------------------------------+\n|1970-01-01T08:00:00.001+08:00|                             1.0|\n|1970-01-01T08:00:00.002+08:00|                             0.0|\n|1970-01-01T08:00:00.003+08:00|                             3.6|\n|1970-01-01T08:00:00.004+08:00|                             0.0|\n|1970-01-01T08:00:00.005+08:00|                             7.0|\n|1970-01-01T08:00:00.006+08:00|                             0.0|\n|1970-01-01T08:00:00.007+08:00|                             3.6|\n|1970-01-01T08:00:00.008+08:00|                             0.0|\n|1970-01-01T08:00:00.009+08:00|                             1.0|\n+-----------------------------+--------------------------------+\n")])])])])}),[],!1,null,null,null);s.default=r.exports}}]);