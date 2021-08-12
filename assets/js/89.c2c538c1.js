(window.webpackJsonp=window.webpackJsonp||[]).push([[89],{471:function(e,t,s){"use strict";s.r(t);var r=s(45),a=Object(r.a)({},(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[s("h1",{attrs:{id:"crosscorrelation"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#crosscorrelation"}},[e._v("#")]),e._v(" CrossCorrelation")]),e._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[e._v("#")]),e._v(" Usage")]),e._v(" "),s("p",[e._v("This function is used to calculate the cross correlation function of given two time series.\nFor discrete time series, cross correlation is given by\n$$CR(n) = \\frac{1}{N} \\sum_{m=1}^N S_1[m]S_2[m+n]$$\nwhich represent the similarities between two series with different index shifts.")]),e._v(" "),s("p",[s("strong",[e._v("Name:")]),e._v(" CROSSCORRELATION")]),e._v(" "),s("p",[s("strong",[e._v("Input Series:")]),e._v(" Only support two input numeric series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),e._v(" "),s("p",[s("strong",[e._v("Output Series:")]),e._v(" Output a single series with DOUBLE as datatype.\nThere are $2N-1$ data points in the series, the center of which represents the cross correlation\ncalculated with pre-aligned series(that is $CR(0)$ in the formula above),\nand the previous(or post) values represent those with shifting the latter series forward(or backward otherwise)\nuntil the two series are no longer overlapped(not included).\nIn short, the values of output series are given by(index starts from 1)\n$$OS[i] = CR(-N+i) = \\frac{1}{N} \\sum_{m=1}^{i} S_1[m]S_2[N-i+m],\\ if\\ i <= N$$\n$$OS[i] = CR(i-N) = \\frac{1}{N} \\sum_{m=1}^{2N-i} S_1[i-N+m]S_2[m],\\ if\\ i > N$$")]),e._v(" "),s("p",[s("strong",[e._v("Note:")])]),e._v(" "),s("ul",[s("li",[s("code",[e._v("null")]),e._v(" and "),s("code",[e._v("NaN")]),e._v(" values in the input series will be ignored and treated as 0.")])]),e._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[e._v("#")]),e._v(" Examples")]),e._v(" "),s("p",[e._v("Input series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+---------------+---------------+\n|                         Time|root.test.d1.s1|root.test.d1.s2|\n+-----------------------------+---------------+---------------+\n|2020-01-01T00:00:01.000+08:00|           null|              6|\n|2020-01-01T00:00:02.000+08:00|              2|              7|\n|2020-01-01T00:00:03.000+08:00|              3|            NaN|\n|2020-01-01T00:00:04.000+08:00|              4|              9|\n|2020-01-01T00:00:05.000+08:00|              5|             10|\n+-----------------------------+---------------+---------------+\n")])])]),s("p",[e._v("SQL for query:")]),e._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" crosscorrelation"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(",")]),e._v(" s2"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d1 "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("where")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("time")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("<=")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("2020")]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("-")]),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("01")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("00")]),e._v(":"),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("00")]),e._v(":"),s("span",{pre:!0,attrs:{class:"token number"}},[e._v("05")]),e._v("\n")])])]),s("p",[e._v("Output series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+--------------------------------------------------+\n|                         Time|crosscorrelation(root.test.d1.s1, root.test.d1.s2)|\n+-----------------------------+--------------------------------------------------+\n|1970-01-01T08:00:00.001+08:00|                                               0.0|\n|1970-01-01T08:00:00.002+08:00|                                               4.0|\n|1970-01-01T08:00:00.003+08:00|                                               9.6|\n|1970-01-01T08:00:00.004+08:00|                                              13.4|\n|1970-01-01T08:00:00.005+08:00|                                              20.0|\n|1970-01-01T08:00:00.006+08:00|                                              15.6|\n|1970-01-01T08:00:00.007+08:00|                                               9.2|\n|1970-01-01T08:00:00.008+08:00|                                              11.8|\n|1970-01-01T08:00:00.009+08:00|                                               6.0|\n+-----------------------------+--------------------------------------------------+\n")])])]),s("h3",{attrs:{id:"examples-on-zeppelin"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples-on-zeppelin"}},[e._v("#")]),e._v(" examples on zeppelin")]),e._v(" "),s("p",[e._v("link: "),s("a",{attrs:{href:"http://101.6.15.213:18181/#/notebook/2GETVW6AT",target:"_blank",rel:"noopener noreferrer"}},[e._v("http://101.6.15.213:18181/#/notebook/2GETVW6AT"),s("OutboundLink")],1)])])}),[],!1,null,null,null);t.default=a.exports}}]);