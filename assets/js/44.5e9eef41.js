(window.webpackJsonp=window.webpackJsonp||[]).push([[44],{413:function(t,s,a){"use strict";a.r(s);var e=a(45),n=Object(e.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"qlb"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#qlb"}},[t._v("#")]),t._v(" QLB")]),t._v(" "),a("h2",{attrs:{id:"usage"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),a("p",[t._v("This function is used to calculate Ljung-Box statistics "),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-msub",[a("mjx-mi",{staticClass:"mjx-i",attrs:{noIC:"true"}},[a("mjx-c",{attrs:{c:"Q"}})],1),a("mjx-script",{staticStyle:{"vertical-align":"-0.15em"}},[a("mjx-TeXAtom",{attrs:{size:"s"}},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"L"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"B"}})],1)],1)],1)],1)],1)],1),t._v(" for time series, and convert it to p value.")],1),t._v(" "),a("p",[a("strong",[t._v("Name:")]),t._v(" QLB")]),t._v(" "),a("p",[a("strong",[t._v("Input Series:")]),t._v(" Only support a single input series. The type is INT32 / INT64 / FLOAT / DOUBLE.")]),t._v(" "),a("p",[a("strong",[t._v("Parameters")]),t._v(":")]),t._v(" "),a("p",[a("code",[t._v("shift")]),t._v(": max lag to calculate. Legal input shall be integer from 1 to n-2, where n is the sample number. Default value is n-2.")]),t._v(" "),a("p",[a("strong",[t._v("Output Series:")]),t._v(" Output a single series. The type is DOUBLE. The output series is p value, and timestamp means lag.")]),t._v(" "),a("p",[a("strong",[t._v("Note:")]),t._v(" If you want to calculate Ljung-Box statistics "),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-msub",[a("mjx-mi",{staticClass:"mjx-i",attrs:{noIC:"true"}},[a("mjx-c",{attrs:{c:"Q"}})],1),a("mjx-script",{staticStyle:{"vertical-align":"-0.15em"}},[a("mjx-TeXAtom",{attrs:{size:"s"}},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"L"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"B"}})],1)],1)],1)],1)],1)],1),t._v(" instead of p value, you may use AutoCorrelation function.")],1),t._v(" "),a("h2",{attrs:{id:"examples"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),a("h3",{attrs:{id:"using-default-parameter"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#using-default-parameter"}},[t._v("#")]),t._v(" Using Default Parameter")]),t._v(" "),a("p",[t._v("Input series:")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|1970-01-01T00:00:00.100+08:00|           1.22|\n|1970-01-01T00:00:00.200+08:00|          -2.78|\n|1970-01-01T00:00:00.300+08:00|           1.53|\n|1970-01-01T00:00:00.400+08:00|           0.70|\n|1970-01-01T00:00:00.500+08:00|           0.75|\n|1970-01-01T00:00:00.600+08:00|          -0.72|\n|1970-01-01T00:00:00.700+08:00|          -0.22|\n|1970-01-01T00:00:00.800+08:00|           0.28|\n|1970-01-01T00:00:00.900+08:00|           0.57|\n|1970-01-01T00:00:01.000+08:00|          -0.22|\n|1970-01-01T00:00:01.100+08:00|          -0.72|\n|1970-01-01T00:00:01.200+08:00|           1.34|\n|1970-01-01T00:00:01.300+08:00|          -0.25|\n|1970-01-01T00:00:01.400+08:00|           0.17|\n|1970-01-01T00:00:01.500+08:00|           2.51|\n|1970-01-01T00:00:01.600+08:00|           1.42|\n|1970-01-01T00:00:01.700+08:00|          -1.34|\n|1970-01-01T00:00:01.800+08:00|          -0.01|\n|1970-01-01T00:00:01.900+08:00|          -0.49|\n|1970-01-01T00:00:02.000+08:00|           1.63|\n+-----------------------------+---------------+\n")])])]),a("p",[t._v("SQL for query:")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" QLB"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),a("p",[t._v("Output series:")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+--------------------+\n|                         Time|QLB(root.test.d1.s1)|\n+-----------------------------+--------------------+\n|1970-01-01T00:00:00.001+08:00|  0.2168702295315677|\n|1970-01-01T00:00:00.002+08:00|  0.3068948509261751|\n|1970-01-01T00:00:00.003+08:00|  0.4217859150918444|\n|1970-01-01T00:00:00.004+08:00|  0.5114539874276656|\n|1970-01-01T00:00:00.005+08:00|  0.6560619525616759|\n|1970-01-01T00:00:00.006+08:00|  0.7722398654053280|\n|1970-01-01T00:00:00.007+08:00|  0.8532491661465290|\n|1970-01-01T00:00:00.008+08:00|  0.9028575017542528|\n|1970-01-01T00:00:00.009+08:00|  0.9434989988192729|\n|1970-01-01T00:00:00.010+08:00|  0.8950280161464689|\n|1970-01-01T00:00:00.011+08:00|  0.7701048398839656|\n|1970-01-01T00:00:00.012+08:00|  0.7845536060001281|\n|1970-01-01T00:00:00.013+08:00|  0.5943030981705825|\n|1970-01-01T00:00:00.014+08:00|  0.4618413512531093|\n|1970-01-01T00:00:00.015+08:00|  0.2645948244673964|\n|1970-01-01T00:00:00.016+08:00|  0.3167530476666645|\n|1970-01-01T00:00:00.017+08:00|  0.2330010780351453|\n|1970-01-01T00:00:00.018+08:00|  0.0666611237622325|\n+-----------------------------+--------------------+\n")])])])])}),[],!1,null,null,null);s.default=n.exports}}]);