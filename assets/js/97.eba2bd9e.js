(window.webpackJsonp=window.webpackJsonp||[]).push([[97],{472:function(e,t,s){"use strict";s.r(t);var a=s(45),n=Object(a.a)({},(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[s("h1",{attrs:{id:"period"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#period"}},[e._v("#")]),e._v(" Period")]),e._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[e._v("#")]),e._v(" Usage")]),e._v(" "),s("p",[e._v("The function is used to compute the period of a numeric time series.")]),e._v(" "),s("p",[s("strong",[e._v("Name:")]),e._v(" PERIOD")]),e._v(" "),s("p",[s("strong",[e._v("Input Series:")]),e._v(" Only support a single input series. The data type is INT32 / INT64 / FLOAT / DOUBLE.")]),e._v(" "),s("p",[s("strong",[e._v("Output Series:")]),e._v(" Output a single series. The type is INT32. There is only one data point in the series, whose timestamp is 0 and value is the period.")]),e._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[e._v("#")]),e._v(" Examples")]),e._v(" "),s("p",[e._v("Input series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+---------------+\n|                         Time|root.test.d3.s1|\n+-----------------------------+---------------+\n|1970-01-01T08:00:00.001+08:00|            1.0|\n|1970-01-01T08:00:00.002+08:00|            2.0|\n|1970-01-01T08:00:00.003+08:00|            3.0|\n|1970-01-01T08:00:00.004+08:00|            1.0|\n|1970-01-01T08:00:00.005+08:00|            2.0|\n|1970-01-01T08:00:00.006+08:00|            3.0|\n|1970-01-01T08:00:00.007+08:00|            1.0|\n|1970-01-01T08:00:00.008+08:00|            2.0|\n|1970-01-01T08:00:00.009+08:00|            3.0|\n+-----------------------------+---------------+\n")])])]),s("p",[e._v("SQL for query:")]),e._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" period"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d3\n")])])]),s("p",[e._v("Output series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+-----------------------+\n|                         Time|period(root.test.d3.s1)|\n+-----------------------------+-----------------------+\n|1970-01-01T08:00:00.000+08:00|                      3|\n+-----------------------------+-----------------------+\n")])])]),s("h3",{attrs:{id:"examples-on-zeppelin"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples-on-zeppelin"}},[e._v("#")]),e._v(" examples on zeppelin")]),e._v(" "),s("p",[e._v("link: "),s("a",{attrs:{href:"http://101.6.15.213:18181/#/notebook/2GEJBUSZ9",target:"_blank",rel:"noopener noreferrer"}},[e._v("http://101.6.15.213:18181/#/notebook/2GEJBUSZ9"),s("OutboundLink")],1)])])}),[],!1,null,null,null);t.default=n.exports}}]);