(window.webpackJsonp=window.webpackJsonp||[]).push([[146],{515:function(s,t,a){"use strict";a.r(t);var c=a(45),m=Object(c.a)({},(function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("ContentSlotsDistributor",{attrs:{"slot-key":s.$parent.slotKey}},[a("h1",{attrs:{id:"highpass"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#highpass"}},[s._v("#")]),s._v(" HighPass")]),s._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[s._v("#")]),s._v(" 函数简介")]),s._v(" "),a("p",[s._v("本函数对输入序列进行高通滤波，提取高于截止频率的分量。输入序列的时间戳将被忽略，所有数据点都将被视作等距的。")]),s._v(" "),a("p",[a("strong",[s._v("函数名：")]),s._v(" HIGHPASS")]),s._v(" "),a("p",[a("strong",[s._v("输入序列：")]),s._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE")]),s._v(" "),a("p",[a("strong",[s._v("参数：")])]),s._v(" "),a("ul",[a("li",[a("code",[s._v("wpass")]),s._v("：归一化后的截止频率，取值为(0,1)，不可缺省。")])]),s._v(" "),a("p",[a("strong",[s._v("输出序列：")]),s._v(" 输出单个序列，类型为DOUBLE，它是滤波后的序列，长度与时间戳均与输入一致。")]),s._v(" "),a("p",[a("strong",[s._v("提示：")]),s._v(" 输入序列中的"),a("code",[s._v("NaN")]),s._v("将被忽略。")]),s._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[s._v("#")]),s._v(" 使用示例")]),s._v(" "),a("p",[s._v("输入序列：")]),s._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[s._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|1970-01-01T08:00:00.000+08:00|       2.902113|\n|1970-01-01T08:00:01.000+08:00|      1.1755705|\n|1970-01-01T08:00:02.000+08:00|     -2.1755705|\n|1970-01-01T08:00:03.000+08:00|     -1.9021131|\n|1970-01-01T08:00:04.000+08:00|            1.0|\n|1970-01-01T08:00:05.000+08:00|      1.9021131|\n|1970-01-01T08:00:06.000+08:00|      0.1755705|\n|1970-01-01T08:00:07.000+08:00|     -1.1755705|\n|1970-01-01T08:00:08.000+08:00|      -0.902113|\n|1970-01-01T08:00:09.000+08:00|            0.0|\n|1970-01-01T08:00:10.000+08:00|       0.902113|\n|1970-01-01T08:00:11.000+08:00|      1.1755705|\n|1970-01-01T08:00:12.000+08:00|     -0.1755705|\n|1970-01-01T08:00:13.000+08:00|     -1.9021131|\n|1970-01-01T08:00:14.000+08:00|           -1.0|\n|1970-01-01T08:00:15.000+08:00|      1.9021131|\n|1970-01-01T08:00:16.000+08:00|      2.1755705|\n|1970-01-01T08:00:17.000+08:00|     -1.1755705|\n|1970-01-01T08:00:18.000+08:00|      -2.902113|\n|1970-01-01T08:00:19.000+08:00|            0.0|\n+-----------------------------+---------------+\n")])])]),a("p",[s._v("用于查询的SQL语句：")]),s._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("select")]),s._v(" highpass"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("(")]),s._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'wpass'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'0.45'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(")")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("from")]),s._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("d1\n")])])]),a("p",[s._v("输出序列：")]),s._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[s._v('+-----------------------------+-----------------------------------------+\n|                         Time|highpass(root.test.d1.s1, "wpass"="0.45")|\n+-----------------------------+-----------------------------------------+\n|1970-01-01T08:00:00.000+08:00|                       0.9999999534830373|\n|1970-01-01T08:00:01.000+08:00|                    1.7462829277628608E-8|\n|1970-01-01T08:00:02.000+08:00|                      -0.9999999593178128|\n|1970-01-01T08:00:03.000+08:00|                   -4.1115269056426626E-8|\n|1970-01-01T08:00:04.000+08:00|                       0.9999999925494194|\n|1970-01-01T08:00:05.000+08:00|                     3.328126513330016E-8|\n|1970-01-01T08:00:06.000+08:00|                      -1.0000000183304454|\n|1970-01-01T08:00:07.000+08:00|                    6.260191433311374E-10|\n|1970-01-01T08:00:08.000+08:00|                       1.0000000018134796|\n|1970-01-01T08:00:09.000+08:00|                   -3.097210911744423E-17|\n|1970-01-01T08:00:10.000+08:00|                      -1.0000000018134794|\n|1970-01-01T08:00:11.000+08:00|                   -6.260191627862097E-10|\n|1970-01-01T08:00:12.000+08:00|                       1.0000000183304454|\n|1970-01-01T08:00:13.000+08:00|                    -3.328126501424346E-8|\n|1970-01-01T08:00:14.000+08:00|                      -0.9999999925494196|\n|1970-01-01T08:00:15.000+08:00|                     4.111526915498874E-8|\n|1970-01-01T08:00:16.000+08:00|                       0.9999999593178128|\n|1970-01-01T08:00:17.000+08:00|                   -1.7462829341296528E-8|\n|1970-01-01T08:00:18.000+08:00|                      -0.9999999534830369|\n|1970-01-01T08:00:19.000+08:00|                   -1.035237222742873E-16|\n+-----------------------------+-----------------------------------------+\n')])])]),a("p",[s._v("注：输入序列服从"),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"y"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"="}})],1),a("mjx-mi",{staticClass:"mjx-i",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"s"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"i"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"n"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"2"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"3C0"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1),a("mjx-TeXAtom",[a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"/"}})],1)],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"4"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"2"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"s"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"i"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"n"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"2"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"3C0"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1),a("mjx-TeXAtom",[a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"/"}})],1)],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"5"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1)],1)],1),s._v("，长度为20，因此高通滤波之后的输出序列服从"),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"y"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"="}})],1),a("mjx-mi",{staticClass:"mjx-i",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"s"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"i"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"n"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"2"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"3C0"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1),a("mjx-TeXAtom",[a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"/"}})],1)],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"4"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1)],1)],1),s._v("。")],1)])}),[],!1,null,null,null);t.default=m.exports}}]);