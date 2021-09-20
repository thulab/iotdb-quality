(window.webpackJsonp=window.webpackJsonp||[]).push([[59],{428:function(e,t,s){"use strict";s.r(t);var a=s(45),n=Object(a.a)({},(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[s("h1",{attrs:{id:"valuefill"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#valuefill"}},[e._v("#")]),e._v(" ValueFill")]),e._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[e._v("#")]),e._v(" Usage")]),e._v(" "),s("p",[e._v("This function is used to impute time series. Several methods are supported.")]),e._v(" "),s("p",[s("strong",[e._v("Name")]),e._v(": ValueFill\n"),s("strong",[e._v("Input Series:")]),e._v(" Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),e._v(" "),s("p",[s("strong",[e._v("Parameters:")])]),e._v(" "),s("ul",[s("li",[s("code",[e._v("method")]),e._v(': {"mean", "previous", "linear", "likelihood", "AR", "MA", "SCREEN"}, default "linear".\nMethod to use for imputation in series. "mean": use global mean value to fill holes; "previous": propagate last valid observation forward to next valid. "linear": simplest interpolation method; "likelihood":Maximum likelihood estimation based on the normal distribution of speed; "AR": auto regression; "MA": moving average; "SCREEN": speed constraint.')])]),e._v(" "),s("p",[s("strong",[e._v("Output Series:")]),e._v(" Output a single series. The type is the same as the input. This series is the input after repairing.")]),e._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[e._v("#")]),e._v(" Examples")]),e._v(" "),s("h3",{attrs:{id:"fill-with-linear"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#fill-with-linear"}},[e._v("#")]),e._v(" Fill with linear")]),e._v(" "),s("p",[e._v("When "),s("code",[e._v("method")]),e._v(' is "linear" or the default, Screen method is used to impute.')]),e._v(" "),s("p",[e._v("Input series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+---------------+\n|                         Time|root.test.d2.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:02.000+08:00|            NaN|\n|2020-01-01T00:00:03.000+08:00|          101.0|\n|2020-01-01T00:00:04.000+08:00|          102.0|\n|2020-01-01T00:00:06.000+08:00|          104.0|\n|2020-01-01T00:00:08.000+08:00|          126.0|\n|2020-01-01T00:00:10.000+08:00|          108.0|\n|2020-01-01T00:00:14.000+08:00|            NaN|\n|2020-01-01T00:00:15.000+08:00|          113.0|\n|2020-01-01T00:00:16.000+08:00|          114.0|\n|2020-01-01T00:00:18.000+08:00|          116.0|\n|2020-01-01T00:00:20.000+08:00|            NaN|\n|2020-01-01T00:00:22.000+08:00|            NaN|\n|2020-01-01T00:00:26.000+08:00|          124.0|\n|2020-01-01T00:00:28.000+08:00|          126.0|\n|2020-01-01T00:00:30.000+08:00|          128.0|\n+-----------------------------+---------------+\n")])])]),s("p",[e._v("SQL for query:")]),e._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" valuefill"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d2\n")])])]),s("p",[e._v("Output series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+-----------------------+\n|                         Time|valuefill(root.test.d2)|\n+-----------------------------+-----------------------+\n|2020-01-01T00:00:02.000+08:00|                    NaN|\n|2020-01-01T00:00:03.000+08:00|                  101.0|\n|2020-01-01T00:00:04.000+08:00|                  102.0|\n|2020-01-01T00:00:06.000+08:00|                  104.0|\n|2020-01-01T00:00:08.000+08:00|                  126.0|\n|2020-01-01T00:00:10.000+08:00|                  108.0|\n|2020-01-01T00:00:14.000+08:00|                  108.0|\n|2020-01-01T00:00:15.000+08:00|                  113.0|\n|2020-01-01T00:00:16.000+08:00|                  114.0|\n|2020-01-01T00:00:18.000+08:00|                  116.0|\n|2020-01-01T00:00:20.000+08:00|                  118.7|\n|2020-01-01T00:00:22.000+08:00|                  121.3|\n|2020-01-01T00:00:26.000+08:00|                  124.0|\n|2020-01-01T00:00:28.000+08:00|                  126.0|\n|2020-01-01T00:00:30.000+08:00|                  128.0|\n+-----------------------------+-----------------------+\n")])])]),s("h3",{attrs:{id:"previous-fill"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#previous-fill"}},[e._v("#")]),e._v(" Previous Fill")]),e._v(" "),s("p",[e._v("When "),s("code",[e._v("method")]),e._v(' is "previous", previous method is used.')]),e._v(" "),s("p",[e._v("Input series is the same as above, the SQL for query is shown below:")]),e._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" valuefill"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(",")]),s("span",{pre:!0,attrs:{class:"token string"}},[e._v('"method"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[e._v("=")]),s("span",{pre:!0,attrs:{class:"token string"}},[e._v('"previous"')]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d2\n")])])]),s("p",[e._v("Output series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v('+-----------------------------+-------------------------------------------+\n|                         Time|valuefill(root.test.d2,"method"="previous")|\n+-----------------------------+-------------------------------------------+\n|2020-01-01T00:00:02.000+08:00|                                        NaN|\n|2020-01-01T00:00:03.000+08:00|                                      101.0|\n|2020-01-01T00:00:04.000+08:00|                                      102.0|\n|2020-01-01T00:00:06.000+08:00|                                      104.0|\n|2020-01-01T00:00:08.000+08:00|                                      126.0|\n|2020-01-01T00:00:10.000+08:00|                                      108.0|\n|2020-01-01T00:00:14.000+08:00|                                      110.5|\n|2020-01-01T00:00:15.000+08:00|                                      113.0|\n|2020-01-01T00:00:16.000+08:00|                                      114.0|\n|2020-01-01T00:00:18.000+08:00|                                      116.0|\n|2020-01-01T00:00:20.000+08:00|                                      116.0|\n|2020-01-01T00:00:22.000+08:00|                                      116.0|\n|2020-01-01T00:00:26.000+08:00|                                      124.0|\n|2020-01-01T00:00:28.000+08:00|                                      126.0|\n|2020-01-01T00:00:30.000+08:00|                                      128.0|\n+-----------------------------+-------------------------------------------+\n')])])])])}),[],!1,null,null,null);t.default=n.exports}}]);