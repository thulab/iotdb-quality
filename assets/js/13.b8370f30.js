(window.webpackJsonp=window.webpackJsonp||[]).push([[13],{384:function(t,s,e){"use strict";e.r(s);var a=e(45),n=Object(a.a)({},(function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[e("h1",{attrs:{id:"lof"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#lof"}},[t._v("#")]),t._v(" LOF")]),t._v(" "),e("h2",{attrs:{id:"usage"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),e("p",[t._v("This function is used to detect density anomaly of time series. According to k-th distance calculation parameter and local outlier factor (lof) threshold, the function judges if a set of input values is an density anomaly, and a bool mark of anomaly values will be output.")]),t._v(" "),e("p",[e("strong",[t._v("Name:")]),t._v(" LOF")]),t._v(" "),e("p",[e("strong",[t._v("Input Series:")]),t._v(" Multiple input series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),t._v(" "),e("ul",[e("li",[e("code",[t._v("method")]),t._v(':assign a detection method. The default value is "default", when input data has multiple dimensions. The alternative is "series", when a input series will be transformed to high dimension.')]),t._v(" "),e("li",[e("code",[t._v("k")]),t._v(":use the k-th distance to calculate lof. Default value is 3.")]),t._v(" "),e("li",[e("code",[t._v("window")]),t._v(": size of window to split origin data points. Default value is 10000.")]),t._v(" "),e("li",[e("code",[t._v("windowsize")]),t._v(':dimension that will be transformed into when method is "series".  The default value is 5.')])]),t._v(" "),e("p",[e("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is DOUBLE.")]),t._v(" "),e("p",[e("strong",[t._v("Note:")]),t._v(" Incomplete rows will be ignored. They are neither calculated nor marked as anomaly.")]),t._v(" "),e("h2",{attrs:{id:"examples"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),e("h3",{attrs:{id:"using-default-parameters"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#using-default-parameters"}},[t._v("#")]),t._v(" Using default parameters")]),t._v(" "),e("p",[t._v("Input series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+---------------+---------------+\n|                         Time|root.test.d1.s1|root.test.d1.s2|\n+-----------------------------+---------------+---------------+\n|1970-01-01T08:00:00.100+08:00|            0.0|            0.0|\n|1970-01-01T08:00:00.200+08:00|            0.0|            1.0|\n|1970-01-01T08:00:00.300+08:00|            1.0|            1.0|\n|1970-01-01T08:00:00.400+08:00|            1.0|            0.0|\n|1970-01-01T08:00:00.500+08:00|            0.0|           -1.0|\n|1970-01-01T08:00:00.600+08:00|           -1.0|           -1.0|\n|1970-01-01T08:00:00.700+08:00|           -1.0|            0.0|\n|1970-01-01T08:00:00.800+08:00|            2.0|            2.0|\n|1970-01-01T08:00:00.900+08:00|            0.0|           null|\n+-----------------------------+---------------+---------------+\n")])])]),e("p",[t._v("SQL for query:")]),t._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" lof"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("s2"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1 "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("where")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("time")]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("<")]),e("span",{pre:!0,attrs:{class:"token number"}},[t._v("1000")]),t._v("\n")])])]),e("p",[t._v("Output series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+-------------------------------------+\n|                         Time|lof(root.test.d1.s1, root.test.d1.s2)|\n+-----------------------------+-------------------------------------+\n|1970-01-01T08:00:00.100+08:00|                   3.8274824267668244|\n|1970-01-01T08:00:00.200+08:00|                   3.0117631741126156|\n|1970-01-01T08:00:00.300+08:00|                    2.838155437762879|\n|1970-01-01T08:00:00.400+08:00|                   3.0117631741126156|\n|1970-01-01T08:00:00.500+08:00|                     2.73518261244453|\n|1970-01-01T08:00:00.600+08:00|                    2.371440975708148|\n|1970-01-01T08:00:00.700+08:00|                     2.73518261244453|\n|1970-01-01T08:00:00.800+08:00|                   1.7561416374270742|\n+-----------------------------+-------------------------------------+\n")])])]),e("h3",{attrs:{id:"diagnosing-1d-timeseries"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#diagnosing-1d-timeseries"}},[t._v("#")]),t._v(" Diagnosing 1d  timeseries")]),t._v(" "),e("p",[t._v("Input series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|1970-01-01T08:00:00.100+08:00|            1.0|\n|1970-01-01T08:00:00.200+08:00|            2.0|\n|1970-01-01T08:00:00.300+08:00|            3.0|\n|1970-01-01T08:00:00.400+08:00|            4.0|\n|1970-01-01T08:00:00.500+08:00|            5.0|\n|1970-01-01T08:00:00.600+08:00|            6.0|\n|1970-01-01T08:00:00.700+08:00|            7.0|\n|1970-01-01T08:00:00.800+08:00|            8.0|\n|1970-01-01T08:00:00.900+08:00|            9.0|\n|1970-01-01T08:00:01.000+08:00|           10.0|\n|1970-01-01T08:00:01.100+08:00|           11.0|\n|1970-01-01T08:00:01.200+08:00|           12.0|\n|1970-01-01T08:00:01.300+08:00|           13.0|\n|1970-01-01T08:00:01.400+08:00|           14.0|\n|1970-01-01T08:00:01.500+08:00|           15.0|\n|1970-01-01T08:00:01.600+08:00|           16.0|\n|1970-01-01T08:00:01.700+08:00|           17.0|\n|1970-01-01T08:00:01.800+08:00|           18.0|\n|1970-01-01T08:00:01.900+08:00|           19.0|\n|1970-01-01T08:00:02.000+08:00|           20.0|\n+-----------------------------+---------------+\n")])])]),e("p",[t._v("SQL for query:")]),t._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" lof"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"method"')]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"series"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1 "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("where")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("time")]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("<")]),e("span",{pre:!0,attrs:{class:"token number"}},[t._v("1000")]),t._v("\n")])])]),e("p",[t._v("Output series:")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+--------------------+\n|                         Time|lof(root.test.d1.s1)|\n+-----------------------------+--------------------+\n|1970-01-01T08:00:00.100+08:00|    3.77777777777778|\n|1970-01-01T08:00:00.200+08:00|    4.32727272727273|\n|1970-01-01T08:00:00.300+08:00|    4.85714285714286|\n|1970-01-01T08:00:00.400+08:00|    5.40909090909091|\n|1970-01-01T08:00:00.500+08:00|    5.94999999999999|\n|1970-01-01T08:00:00.600+08:00|    6.43243243243243|\n|1970-01-01T08:00:00.700+08:00|    6.79999999999999|\n|1970-01-01T08:00:00.800+08:00|                 7.0|\n|1970-01-01T08:00:00.900+08:00|                 7.0|\n|1970-01-01T08:00:01.000+08:00|    6.79999999999999|\n|1970-01-01T08:00:01.100+08:00|    6.43243243243243|\n|1970-01-01T08:00:01.200+08:00|    5.94999999999999|\n|1970-01-01T08:00:01.300+08:00|    5.40909090909091|\n|1970-01-01T08:00:01.400+08:00|    4.85714285714286|\n|1970-01-01T08:00:01.500+08:00|    4.32727272727273|\n|1970-01-01T08:00:01.600+08:00|    3.77777777777778|\n+-----------------------------+--------------------+\n")])])]),e("h3",{attrs:{id:""}},[e("a",{staticClass:"header-anchor",attrs:{href:"#"}},[t._v("#")])])])}),[],!1,null,null,null);s.default=n.exports}}]);