(window.webpackJsonp=window.webpackJsonp||[]).push([[56],{425:function(t,s,a){"use strict";a.r(s);var m=a(45),c=Object(m.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"histogram"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#histogram"}},[t._v("#")]),t._v(" Histogram")]),t._v(" "),a("h2",{attrs:{id:"usage"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[t._v("#")]),t._v(" Usage")]),t._v(" "),a("p",[t._v("This function is used to calculate the distribution histogram of a single column of numerical data.")]),t._v(" "),a("p",[a("strong",[t._v("Name:")]),t._v(" HISTOGRAM")]),t._v(" "),a("p",[a("strong",[t._v("Input Series:")]),t._v(" Only supports a single input sequence, the type is INT32 / INT64 / FLOAT / DOUBLE")]),t._v(" "),a("p",[a("strong",[t._v("Parameters:")])]),t._v(" "),a("ul",[a("li",[a("code",[t._v("start")]),t._v(": The lower limit of the requested data range, the default value is -Double.MAX_VALUE.")]),t._v(" "),a("li",[a("code",[t._v("end")]),t._v(": The upper limit of the requested data range, the default value is Double.MAX_VALUE, and the value of start must be less than or equal to end.")]),t._v(" "),a("li",[a("code",[t._v("count")]),t._v(": The number of buckets of the histogram, the default value is 1. It must be a positive integer.")])]),t._v(" "),a("p",[a("strong",[t._v("Output Series:")]),t._v(" The value of the bucket of the histogram, where the lower bound represented by the i-th bucket (index starts from 1) is "),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"s"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"a"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"r"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"i"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"2212"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"1"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"22C5"}})],1),a("mjx-mfrac",{attrs:{space:"3"}},[a("mjx-frac",[a("mjx-num",[a("mjx-nstrut"),a("mjx-mrow",{attrs:{size:"s"}},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"e"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"n"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"d"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"2212"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"s"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"a"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"r"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1)],1)],1),a("mjx-dbox",[a("mjx-dtable",[a("mjx-line"),a("mjx-row",[a("mjx-den",[a("mjx-dstrut"),a("mjx-mrow",{attrs:{size:"s"}},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"c"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"o"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"u"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"n"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1)],1)],1)],1)],1)],1)],1)],1)],1)],1),t._v(" and the upper bound is "),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"s"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"a"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"r"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mi",{staticClass:"mjx-i",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"i"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"22C5"}})],1),a("mjx-mfrac",{attrs:{space:"3"}},[a("mjx-frac",[a("mjx-num",[a("mjx-nstrut"),a("mjx-mrow",{attrs:{size:"s"}},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"e"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"n"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"d"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"2212"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"s"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"a"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"r"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1)],1)],1),a("mjx-dbox",[a("mjx-dtable",[a("mjx-line"),a("mjx-row",[a("mjx-den",[a("mjx-dstrut"),a("mjx-mrow",{attrs:{size:"s"}},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"c"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"o"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"u"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"n"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1)],1)],1)],1)],1)],1)],1)],1)],1)],1),t._v(".")],1),t._v(" "),a("p",[a("strong",[t._v("Note:")])]),t._v(" "),a("ul",[a("li",[t._v("If the value is lower than "),a("code",[t._v("start")]),t._v(", it will be put into the 1st bucket. If the value is larger than "),a("code",[t._v("end")]),t._v(", it will be put into the last bucket.")]),t._v(" "),a("li",[t._v("Missing points, null points and "),a("code",[t._v("NaN")]),t._v(" in the input series will be ignored.")])]),t._v(" "),a("h2",{attrs:{id:"examples"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[t._v("#")]),t._v(" Examples")]),t._v(" "),a("p",[t._v("Input series:")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:00.000+08:00|            1.0|\n|2020-01-01T00:00:01.000+08:00|            2.0|\n|2020-01-01T00:00:02.000+08:00|            3.0|\n|2020-01-01T00:00:03.000+08:00|            4.0|\n|2020-01-01T00:00:04.000+08:00|            5.0|\n|2020-01-01T00:00:05.000+08:00|            6.0|\n|2020-01-01T00:00:06.000+08:00|            7.0|\n|2020-01-01T00:00:07.000+08:00|            8.0|\n|2020-01-01T00:00:08.000+08:00|            9.0|\n|2020-01-01T00:00:09.000+08:00|           10.0|\n|2020-01-01T00:00:10.000+08:00|           11.0|\n|2020-01-01T00:00:11.000+08:00|           12.0|\n|2020-01-01T00:00:12.000+08:00|           13.0|\n|2020-01-01T00:00:13.000+08:00|           14.0|\n|2020-01-01T00:00:14.000+08:00|           15.0|\n|2020-01-01T00:00:15.000+08:00|           16.0|\n|2020-01-01T00:00:16.000+08:00|           17.0|\n|2020-01-01T00:00:17.000+08:00|           18.0|\n|2020-01-01T00:00:18.000+08:00|           19.0|\n|2020-01-01T00:00:19.000+08:00|           20.0|\n+-----------------------------+---------------+\n")])])]),a("p",[t._v("SQL for query:")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" histogram"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"start"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"1"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"end"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"20"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"count"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"10"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),a("p",[t._v("Output series:")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v('+-----------------------------+-----------------------------------------------------------------+\n|                         Time|histogram(root.test.d1.s1, "start"="1", "end"="20", "count"="10")|\n+-----------------------------+-----------------------------------------------------------------+\n|1970-01-01T08:00:00.000+08:00|                                                                2|\n|1970-01-01T08:00:00.001+08:00|                                                                2|\n|1970-01-01T08:00:00.002+08:00|                                                                2|\n|1970-01-01T08:00:00.003+08:00|                                                                2|\n|1970-01-01T08:00:00.004+08:00|                                                                2|\n|1970-01-01T08:00:00.005+08:00|                                                                2|\n|1970-01-01T08:00:00.006+08:00|                                                                2|\n|1970-01-01T08:00:00.007+08:00|                                                                2|\n|1970-01-01T08:00:00.008+08:00|                                                                2|\n|1970-01-01T08:00:00.009+08:00|                                                                2|\n+-----------------------------+-----------------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=c.exports}}]);