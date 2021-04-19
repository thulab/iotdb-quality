(window.webpackJsonp=window.webpackJsonp||[]).push([[70],{440:function(s,t,e){"use strict";e.r(t);var a=e(45),r=Object(a.a)({},(function(){var s=this,t=s.$createElement,e=s._self._c||t;return e("ContentSlotsDistributor",{attrs:{"slot-key":s.$parent.slotKey}},[e("h1",{attrs:{id:"sample"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#sample"}},[s._v("#")]),s._v(" Sample")]),s._v(" "),e("h2",{attrs:{id:"usage"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[s._v("#")]),s._v(" Usage")]),s._v(" "),e("p",[s._v("This function is used to sample the input series,\nthat is, select a specified number of data points from the input series and output them.\nCurrently, two sampling methods are supported:\n"),e("strong",[s._v("Reservoir sampling")]),s._v(" randomly selects data points.\nAll of the points have the same probability of being sampled.\n"),e("strong",[s._v("Isometric sampling")]),s._v(" selects data points at equal index intervals.")]),s._v(" "),e("p",[e("strong",[s._v("Name:")]),s._v(" SAMPLE")]),s._v(" "),e("p",[e("strong",[s._v("Input Series:")]),s._v(" Only support a single input series. The type is arbitrary.")]),s._v(" "),e("p",[e("strong",[s._v("Parameters:")])]),s._v(" "),e("ul",[e("li",[e("code",[s._v("method")]),s._v(": The method of sampling, which is 'reservoir' or 'isometric'. By default, reservoir sampling is used.")]),s._v(" "),e("li",[e("code",[s._v("k")]),s._v(": The number of sampling, which is a positive integer. By default, it's 1.")])]),s._v(" "),e("p",[e("strong",[s._v("Output Series:")]),s._v(" Output a single series. The type is the same as the input. The length of the output series is "),e("code",[s._v("k")]),s._v(". Each data point in the output series comes from the input series.")]),s._v(" "),e("p",[e("strong",[s._v("Note:")]),s._v(" If "),e("code",[s._v("k")]),s._v(" is greater than the length of input series, all data points in the input series will be output.")]),s._v(" "),e("h2",{attrs:{id:"examples"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[s._v("#")]),s._v(" Examples")]),s._v(" "),e("h3",{attrs:{id:"reservoir-sampling"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#reservoir-sampling"}},[s._v("#")]),s._v(" Reservoir Sampling")]),s._v(" "),e("p",[s._v("When "),e("code",[s._v("method")]),s._v(" is 'reservoir' or the default, reservoir sampling is used.\nDue to the randomness of this method, the output series shown below is only a possible result.")]),s._v(" "),e("p",[s._v("Input series:")]),s._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[s._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:01.000+08:00|            1.0|\n|2020-01-01T00:00:02.000+08:00|            2.0|\n|2020-01-01T00:00:03.000+08:00|            3.0|\n|2020-01-01T00:00:04.000+08:00|            4.0|\n|2020-01-01T00:00:05.000+08:00|            5.0|\n|2020-01-01T00:00:06.000+08:00|            6.0|\n|2020-01-01T00:00:07.000+08:00|            7.0|\n|2020-01-01T00:00:08.000+08:00|            8.0|\n|2020-01-01T00:00:09.000+08:00|            9.0|\n|2020-01-01T00:00:10.000+08:00|           10.0|\n+-----------------------------+---------------+\n")])])]),e("p",[s._v("SQL for query:")]),s._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("select")]),s._v(" sample"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("(")]),s._v("s1"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),e("span",{pre:!0,attrs:{class:"token string"}},[s._v("'method'")]),e("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),e("span",{pre:!0,attrs:{class:"token string"}},[s._v("'reservoir'")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),e("span",{pre:!0,attrs:{class:"token string"}},[s._v("'k'")]),e("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),e("span",{pre:!0,attrs:{class:"token string"}},[s._v("'5'")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(")")]),s._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("from")]),s._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("test"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("d1\n")])])]),e("p",[s._v("Output series:")]),s._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[s._v('+-----------------------------+------------------------------------------------------+\n|                         Time|sample(root.test.d1.s1, "method"="reservoir", "k"="5")|\n+-----------------------------+------------------------------------------------------+\n|2020-01-01T00:00:02.000+08:00|                                                   2.0|\n|2020-01-01T00:00:03.000+08:00|                                                   3.0|\n|2020-01-01T00:00:05.000+08:00|                                                   5.0|\n|2020-01-01T00:00:08.000+08:00|                                                   8.0|\n|2020-01-01T00:00:10.000+08:00|                                                  10.0|\n+-----------------------------+------------------------------------------------------+\n')])])]),e("h3",{attrs:{id:"isometric-sampling"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#isometric-sampling"}},[s._v("#")]),s._v(" Isometric Sampling")]),s._v(" "),e("p",[s._v("When "),e("code",[s._v("method")]),s._v(" is 'isometric', isometric sampling is used.")]),s._v(" "),e("p",[s._v("Input series is the same as above, the SQL for query is shown below:")]),s._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("select")]),s._v(" sample"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("(")]),s._v("s1"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),e("span",{pre:!0,attrs:{class:"token string"}},[s._v("'method'")]),e("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),e("span",{pre:!0,attrs:{class:"token string"}},[s._v("'isometric'")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),e("span",{pre:!0,attrs:{class:"token string"}},[s._v("'k'")]),e("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),e("span",{pre:!0,attrs:{class:"token string"}},[s._v("'5'")]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(")")]),s._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("from")]),s._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("test"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("d1\n")])])]),e("p",[s._v("Output series:")]),s._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[s._v('+-----------------------------+------------------------------------------------------+\n|                         Time|sample(root.test.d1.s1, "method"="isometric", "k"="5")|\n+-----------------------------+------------------------------------------------------+\n|2020-01-01T00:00:01.000+08:00|                                                   1.0|\n|2020-01-01T00:00:03.000+08:00|                                                   3.0|\n|2020-01-01T00:00:05.000+08:00|                                                   5.0|\n|2020-01-01T00:00:07.000+08:00|                                                   7.0|\n|2020-01-01T00:00:09.000+08:00|                                                   9.0|\n+-----------------------------+------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);t.default=r.exports}}]);