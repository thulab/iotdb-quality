(window.webpackJsonp=window.webpackJsonp||[]).push([[13],{387:function(e,t,s){"use strict";s.r(t);var n=s(45),a=Object(n.a)({},(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[s("h1",{attrs:{id:"missdetect"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#missdetect"}},[e._v("#")]),e._v(" MissDetect")]),e._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[e._v("#")]),e._v(" Usage")]),e._v(" "),s("p",[e._v("This function is used to detect missing anomalies.\nIn some datasets, missing values are filled by linear interpolation.\nThus, there are several long perfect linear segments.\nBy discovering these perfect linear segments,\nmissing anomalies are detected.")]),e._v(" "),s("p",[s("strong",[e._v("Name:")]),e._v(" MISSDETECT")]),e._v(" "),s("p",[s("strong",[e._v("Input Series:")]),e._v(" Only support a single input series. The data type is INT32 / INT64 / FLOAT / DOUBLE.")]),e._v(" "),s("p",[s("strong",[e._v("Parameter:")])]),e._v(" "),s("p",[s("code",[e._v("error")]),e._v(": The minimum length of the detected missing anomalies, which is an integer greater than or equal to 10. By default, it is 10.")]),e._v(" "),s("p",[s("strong",[e._v("Output Series:")]),e._v(" Output a single series. The type is BOOLEAN. Each data point which is miss anomaly will be labeled as true.")]),e._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[e._v("#")]),e._v(" Examples")]),e._v(" "),s("p",[e._v("Input series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+---------------+\n|                         Time|root.test.d2.s2|\n+-----------------------------+---------------+\n|2021-07-01T12:00:00.000+08:00|            0.0|\n|2021-07-01T12:00:01.000+08:00|            1.0|\n|2021-07-01T12:00:02.000+08:00|            0.0|\n|2021-07-01T12:00:03.000+08:00|            1.0|\n|2021-07-01T12:00:04.000+08:00|            0.0|\n|2021-07-01T12:00:05.000+08:00|            0.0|\n|2021-07-01T12:00:06.000+08:00|            0.0|\n|2021-07-01T12:00:07.000+08:00|            0.0|\n|2021-07-01T12:00:08.000+08:00|            0.0|\n|2021-07-01T12:00:09.000+08:00|            0.0|\n|2021-07-01T12:00:10.000+08:00|            0.0|\n|2021-07-01T12:00:11.000+08:00|            0.0|\n|2021-07-01T12:00:12.000+08:00|            0.0|\n|2021-07-01T12:00:13.000+08:00|            0.0|\n|2021-07-01T12:00:14.000+08:00|            0.0|\n|2021-07-01T12:00:15.000+08:00|            0.0|\n|2021-07-01T12:00:16.000+08:00|            1.0|\n|2021-07-01T12:00:17.000+08:00|            0.0|\n|2021-07-01T12:00:18.000+08:00|            1.0|\n|2021-07-01T12:00:19.000+08:00|            0.0|\n|2021-07-01T12:00:20.000+08:00|            1.0|\n+-----------------------------+---------------+\n")])])]),s("p",[e._v("SQL for query:")]),e._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" missdetect"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s2"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(",")]),s("span",{pre:!0,attrs:{class:"token string"}},[e._v("'minlen'")]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[e._v("'10'")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d2\n")])])]),s("p",[e._v("Output series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v('+-----------------------------+------------------------------------------+\n|                         Time|missdetect(root.test.d2.s2, "minlen"="10")|\n+-----------------------------+------------------------------------------+\n|2021-07-01T12:00:00.000+08:00|                                     false|\n|2021-07-01T12:00:01.000+08:00|                                     false|\n|2021-07-01T12:00:02.000+08:00|                                     false|\n|2021-07-01T12:00:03.000+08:00|                                     false|\n|2021-07-01T12:00:04.000+08:00|                                      true|\n|2021-07-01T12:00:05.000+08:00|                                      true|\n|2021-07-01T12:00:06.000+08:00|                                      true|\n|2021-07-01T12:00:07.000+08:00|                                      true|\n|2021-07-01T12:00:08.000+08:00|                                      true|\n|2021-07-01T12:00:09.000+08:00|                                      true|\n|2021-07-01T12:00:10.000+08:00|                                      true|\n|2021-07-01T12:00:11.000+08:00|                                      true|\n|2021-07-01T12:00:12.000+08:00|                                      true|\n|2021-07-01T12:00:13.000+08:00|                                      true|\n|2021-07-01T12:00:14.000+08:00|                                      true|\n|2021-07-01T12:00:15.000+08:00|                                      true|\n|2021-07-01T12:00:16.000+08:00|                                     false|\n|2021-07-01T12:00:17.000+08:00|                                     false|\n|2021-07-01T12:00:18.000+08:00|                                     false|\n|2021-07-01T12:00:19.000+08:00|                                     false|\n|2021-07-01T12:00:20.000+08:00|                                     false|\n+-----------------------------+------------------------------------------+\n')])])])])}),[],!1,null,null,null);t.default=a.exports}}]);