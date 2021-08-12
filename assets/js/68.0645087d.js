(window.webpackJsonp=window.webpackJsonp||[]).push([[68],{442:function(t,s,a){"use strict";a.r(s);var e=a(45),n=Object(e.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"ksigma"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#ksigma"}},[t._v("#")]),t._v(" KSigma")]),t._v(" "),a("h2",{attrs:{id:"usage"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),a("p",[t._v("This function is used to detect anomalies based on the Dynamic K-Sigma Algorithm.\nWithin a sliding window, the input value with a deviation of more than k times the standard deviation from the average will be output as anomaly.")]),t._v(" "),a("p",[a("strong",[t._v("Name:")]),t._v(" KSIGMA")]),t._v(" "),a("p",[a("strong",[t._v("Input Series:")]),t._v(" Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),t._v(" "),a("ul",[a("li",[a("code",[t._v("k")]),t._v(": How many times to multiply on standard deviation to define anomaly, the default value is 3.")]),t._v(" "),a("li",[a("code",[t._v("window")]),t._v(": The window size of Dynamic K-Sigma Algorithm, the default value is 10000.")])]),t._v(" "),a("p",[a("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is same as input series.")]),t._v(" "),a("p",[a("strong",[t._v("Note:")]),t._v(" Only when is larger than 0, the anomaly detection will be performed. Otherwise, nothing will be output.")]),t._v(" "),a("h2",{attrs:{id:"examples"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),a("h3",{attrs:{id:"assigning-k"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#assigning-k"}},[t._v("#")]),t._v(" Assigning k")]),t._v(" "),a("p",[t._v("Input series:")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:02.000+08:00|            0.0|\n|2020-01-01T00:00:03.000+08:00|           50.0|\n|2020-01-01T00:00:04.000+08:00|          100.0|\n|2020-01-01T00:00:06.000+08:00|          150.0|\n|2020-01-01T00:00:08.000+08:00|          200.0|\n|2020-01-01T00:00:10.000+08:00|          200.0|\n|2020-01-01T00:00:14.000+08:00|          200.0|\n|2020-01-01T00:00:15.000+08:00|          200.0|\n|2020-01-01T00:00:16.000+08:00|          200.0|\n|2020-01-01T00:00:18.000+08:00|          200.0|\n|2020-01-01T00:00:20.000+08:00|          150.0|\n|2020-01-01T00:00:22.000+08:00|          100.0|\n|2020-01-01T00:00:26.000+08:00|           50.0|\n|2020-01-01T00:00:28.000+08:00|            0.0|\n|2020-01-01T00:00:30.000+08:00|            NaN|\n+-----------------------------+---------------+\n")])])]),a("p",[t._v("SQL for query:")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" ksigma"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"k"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"1.0"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1 "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("where")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("time")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("<=")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("2020")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("-")]),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("01")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("-")]),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("01")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("00")]),t._v(":"),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("00")]),t._v(":"),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("30")]),t._v("\n")])])]),a("p",[t._v("Output series:")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v('+-----------------------------+---------------------------------+\n|Time                         |ksigma(root.test.d1.s1,"k"="3.0")|\n+-----------------------------+---------------------------------+\n|2020-01-01T00:00:02.000+08:00|                              0.0|\n|2020-01-01T00:00:03.000+08:00|                             50.0|\n|2020-01-01T00:00:26.000+08:00|                             50.0|\n|2020-01-01T00:00:28.000+08:00|                              0.0|\n+-----------------------------+---------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=n.exports}}]);