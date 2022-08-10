(window.webpackJsonp=window.webpackJsonp||[]).push([[275],{645:function(t,s,a){"use strict";a.r(s);var c=a(45),n=Object(c.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"stft"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#stft"}},[t._v("#")]),t._v(" STFT")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数对输入序列进行短时傅里叶变换。将输入序列划分为若干个等长的窗口，并对每一个窗口进行傅里叶变换，计算各个频率分量的幅值。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" STFT")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE")]),t._v(" "),a("p",[a("strong",[t._v("参数：")])]),t._v(" "),a("ul",[a("li",[a("code",[t._v("nfft")]),t._v("：短时傅里叶变换的窗口大小，最后一个窗口可能小于该值。在缺省情况下，所有输入数据都被作为一个窗口。")]),t._v(" "),a("li",[a("code",[t._v("beta")]),t._v("：量化等级，即将"),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"x"}})],1)],1)],1),t._v("量化为"),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"r"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"o"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"u"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"n"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"d"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"x"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"22C5"}})],1),a("mjx-msup",[a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"2"}})],1),a("mjx-script",{staticStyle:{"vertical-align":"0.363em"}},[a("mjx-TeXAtom",{attrs:{size:"s"}},[a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"2212"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"3B2"}})],1)],1)],1)],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"22C5"}})],1),a("mjx-msup",[a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"2"}})],1),a("mjx-script",{staticStyle:{"vertical-align":"0.363em"}},[a("mjx-mi",{staticClass:"mjx-i",attrs:{size:"s"}},[a("mjx-c",{attrs:{c:"3B2"}})],1)],1)],1)],1)],1),t._v("。默认值为0，仅在不给定"),a("code",[t._v("T_SNR")]),t._v("时使用。")],1),t._v(" "),a("li",[a("code",[t._v("T_SNR")]),t._v("：目标SNR，通过该参数可以自动确定"),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"3B2"}})],1)],1)],1),t._v("。")],1)]),t._v(" "),a("p",[a("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为DOUBLE，与输入序列的时间戳一一对应。")]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|1970-01-01T08:00:00.000+08:00|            0.0|\n|1970-01-01T08:00:01.000+08:00|            1.0|\n|1970-01-01T08:00:02.000+08:00|            0.0|\n|1970-01-01T08:00:03.000+08:00|           -1.0|\n|1970-01-01T08:00:04.000+08:00|            0.0|\n|1970-01-01T08:00:05.000+08:00|           -2.0|\n|1970-01-01T08:00:06.000+08:00|            0.0|\n|1970-01-01T08:00:07.000+08:00|            2.0|\n+-----------------------------+---------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" stft"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'nfft'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'4'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v('+-----------------------------+---------------------------------+\n|                         Time|stft(root.test.d1.s1, "nfft"="4")|\n+-----------------------------+---------------------------------+\n|1970-01-01T08:00:00.000+08:00|                              0.0|\n|1970-01-01T08:00:01.000+08:00|                              1.0|\n|1970-01-01T08:00:02.000+08:00|                              0.0|\n|1970-01-01T08:00:03.000+08:00|                              0.0|\n|1970-01-01T08:00:04.000+08:00|                              0.0|\n|1970-01-01T08:00:05.000+08:00|                              2.0|\n|1970-01-01T08:00:06.000+08:00|                              0.0|\n|1970-01-01T08:00:07.000+08:00|                              0.0|\n+-----------------------------+---------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=n.exports}}]);