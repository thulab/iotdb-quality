(window.webpackJsonp=window.webpackJsonp||[]).push([[83],{457:function(t,e,s){"use strict";s.r(e);var a=s(45),r=Object(a.a)({},(function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"patternsymmetric"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#patternsymmetric"}},[t._v("#")]),t._v(" PatternSymmetric")]),t._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),s("p",[t._v("This function is used to find all symmetric subseries in the input whose degree of symmetry is less than the threshold.\nThe degree of symmetry is calculated by DTW.\nThe smaller the degree, the more symmetrical the series is.")]),t._v(" "),s("p",[s("strong",[t._v("Name:")]),t._v(" PATTERNSYMMETRIC")]),t._v(" "),s("p",[s("strong",[t._v("Input Series:")]),t._v(" Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE。")]),t._v(" "),s("p",[s("strong",[t._v("Parameter:")])]),t._v(" "),s("ul",[s("li",[s("code",[t._v("window")]),t._v(": The length of the symmetric subseries. It's a positive integer and the default value is 10.")]),t._v(" "),s("li",[s("code",[t._v("threshold")]),t._v(": The threshold of the degree of symmetry. It's non-negative. Only the subseries whose degree of symmetry is below it will be output. By default, all subseries will be output.")])]),t._v(" "),s("p",[s("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is DOUBLE. Each data point in the output series corresponds to a symmetric subseries. The output timestamp is the starting timestamp of the subseries and the output value is the degree of symmetry.")]),t._v(" "),s("h2",{attrs:{id:"example"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#example"}},[t._v("#")]),t._v(" Example")]),t._v(" "),s("p",[t._v("Input series:")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s4|\n+-----------------------------+---------------+\n|2021-01-01T12:00:00.000+08:00|            1.0|\n|2021-01-01T12:00:01.000+08:00|            2.0|\n|2021-01-01T12:00:02.000+08:00|            3.0|\n|2021-01-01T12:00:03.000+08:00|            2.0|\n|2021-01-01T12:00:04.000+08:00|            1.0|\n|2021-01-01T12:00:05.000+08:00|            1.0|\n|2021-01-01T12:00:06.000+08:00|            1.0|\n|2021-01-01T12:00:07.000+08:00|            1.0|\n|2021-01-01T12:00:08.000+08:00|            2.0|\n|2021-01-01T12:00:09.000+08:00|            3.0|\n|2021-01-01T12:00:10.000+08:00|            2.0|\n|2021-01-01T12:00:11.000+08:00|            1.0|\n+-----------------------------+---------------+\n")])])]),s("p",[t._v("SQL for query:")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" patternsymmetric"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s4"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'window'")]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'5'")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'threshold'")]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v("'0'")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),s("p",[t._v("Output series:")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v('+-----------------------------+----------------------------------------------------------------+\n|                         Time|patternsymmetric(root.test.d1.s4, "window"="5", "threshold"="0")|\n+-----------------------------+----------------------------------------------------------------+\n|2021-01-01T12:00:00.000+08:00|                                                             0.0|\n|2021-01-01T12:00:07.000+08:00|                                                             0.0|\n+-----------------------------+----------------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);e.default=r.exports}}]);