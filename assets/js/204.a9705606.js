(window.webpackJsonp=window.webpackJsonp||[]).push([[204],{575:function(t,s,a){"use strict";a.r(s);var n=a(45),r=Object(n.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"fft"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#fft"}},[t._v("#")]),t._v(" FFT")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数对输入序列进行快速傅里叶变换。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" FFT")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE")]),t._v(" "),a("p",[a("strong",[t._v("参数：")])]),t._v(" "),a("ul",[a("li",[a("code",[t._v("type")]),t._v("：傅里叶变换的类型，取值为'uniform'或'nonuniform'，缺省情况下为'uniform'。当取值为'uniform'时，时间戳将被忽略，所有数据点都将被视作等距的，并应用等距快速傅里叶算法；当取值为'nonuniform'时，将根据时间戳应用非等距快速傅里叶算法（未实现）。")]),t._v(" "),a("li",[a("code",[t._v("result")]),t._v("：傅里叶变换的结果，取值为'real'、'imag'、'abs'或'angle'，分别对应于变换结果的实部、虚部、模和幅角。在缺省情况下，输出变换的模。")]),t._v(" "),a("li",[a("code",[t._v("nfft")]),t._v("：傅里叶变换的点数，是一个正整数。在缺省情况下，输入的所有数据点都会用于傅里叶变换。")]),t._v(" "),a("li",[a("code",[t._v("compress")]),t._v("：压缩参数，取值范围(0,1]，是有损压缩时保留的能量比例。在缺省情况下，不进行压缩。")])]),t._v(" "),a("p",[a("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为DOUBLE，长度与输入相等。序列的时间戳从0开始，仅用于表示顺序。")]),t._v(" "),a("p",[a("strong",[t._v("提示：")]),t._v(" 输入序列中的"),a("code",[t._v("NaN")]),t._v("将被忽略。")]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("h3",{attrs:{id:"等距傅里叶变换"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#等距傅里叶变换"}},[t._v("#")]),t._v(" 等距傅里叶变换")]),t._v(" "),a("p",[t._v("当"),a("code",[t._v("type")]),t._v("参数缺省或为'uniform'时，本函数进行等距傅里叶变换。")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|1970-01-01T08:00:00.000+08:00|       2.902113|\n|1970-01-01T08:00:01.000+08:00|      1.1755705|\n|1970-01-01T08:00:02.000+08:00|     -2.1755705|\n|1970-01-01T08:00:03.000+08:00|     -1.9021131|\n|1970-01-01T08:00:04.000+08:00|            1.0|\n|1970-01-01T08:00:05.000+08:00|      1.9021131|\n|1970-01-01T08:00:06.000+08:00|      0.1755705|\n|1970-01-01T08:00:07.000+08:00|     -1.1755705|\n|1970-01-01T08:00:08.000+08:00|      -0.902113|\n|1970-01-01T08:00:09.000+08:00|            0.0|\n|1970-01-01T08:00:10.000+08:00|       0.902113|\n|1970-01-01T08:00:11.000+08:00|      1.1755705|\n|1970-01-01T08:00:12.000+08:00|     -0.1755705|\n|1970-01-01T08:00:13.000+08:00|     -1.9021131|\n|1970-01-01T08:00:14.000+08:00|           -1.0|\n|1970-01-01T08:00:15.000+08:00|      1.9021131|\n|1970-01-01T08:00:16.000+08:00|      2.1755705|\n|1970-01-01T08:00:17.000+08:00|     -1.1755705|\n|1970-01-01T08:00:18.000+08:00|      -2.902113|\n|1970-01-01T08:00:19.000+08:00|            0.0|\n+-----------------------------+---------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" fft"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+----------------------+\n|                         Time|  fft(root.test.d1.s1)|\n+-----------------------------+----------------------+\n|1970-01-01T08:00:00.000+08:00|                   0.0|\n|1970-01-01T08:00:00.001+08:00| 1.2727111142703152E-8|\n|1970-01-01T08:00:00.002+08:00|  2.385520799101839E-7|\n|1970-01-01T08:00:00.003+08:00|  8.723291723972645E-8|\n|1970-01-01T08:00:00.004+08:00|    19.999999960195904|\n|1970-01-01T08:00:00.005+08:00|     9.999999850988388|\n|1970-01-01T08:00:00.006+08:00| 3.2260694930700566E-7|\n|1970-01-01T08:00:00.007+08:00|  8.723291605373329E-8|\n|1970-01-01T08:00:00.008+08:00|  1.108657103979944E-7|\n|1970-01-01T08:00:00.009+08:00| 1.2727110997246171E-8|\n|1970-01-01T08:00:00.010+08:00|1.9852334701272664E-23|\n|1970-01-01T08:00:00.011+08:00| 1.2727111194499847E-8|\n|1970-01-01T08:00:00.012+08:00|  1.108657103979944E-7|\n|1970-01-01T08:00:00.013+08:00|  8.723291785769131E-8|\n|1970-01-01T08:00:00.014+08:00|  3.226069493070057E-7|\n|1970-01-01T08:00:00.015+08:00|     9.999999850988388|\n|1970-01-01T08:00:00.016+08:00|    19.999999960195904|\n|1970-01-01T08:00:00.017+08:00|  8.723291747109068E-8|\n|1970-01-01T08:00:00.018+08:00| 2.3855207991018386E-7|\n|1970-01-01T08:00:00.019+08:00| 1.2727112069910878E-8|\n+-----------------------------+----------------------+\n")])])]),a("p",[t._v("注：输入序列服从"),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"y"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"="}})],1),a("mjx-mi",{staticClass:"mjx-i",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"s"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"i"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"n"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"2"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"3C0"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1),a("mjx-TeXAtom",[a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"/"}})],1)],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"4"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"2"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"s"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"i"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"n"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"2"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"3C0"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"t"}})],1),a("mjx-TeXAtom",[a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"/"}})],1)],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"5"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1)],1)],1),t._v("，长度为20，因此在输出序列中"),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"k"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"="}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"4"}})],1)],1)],1),t._v("和"),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"k"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"="}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"5"}})],1)],1)],1),t._v("处有尖峰。")],1),t._v(" "),a("h3",{attrs:{id:"等距傅里叶变换并压缩"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#等距傅里叶变换并压缩"}},[t._v("#")]),t._v(" 等距傅里叶变换并压缩")]),t._v(" "),a("p",[t._v("输入序列同上，用于查询的SQL语句如下：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" fft"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'result'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'real'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'compress'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'0.99'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" fft"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'result'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'imag'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'compress'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v("'0.99'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v('+-----------------------------+----------------------+----------------------+\n|                         Time|  fft(root.test.d1.s1,|  fft(root.test.d1.s1,|\n|                             |      "result"="real",|      "result"="imag",|\n|                             |    "compress"="0.99")|    "compress"="0.99")|\n+-----------------------------+----------------------+----------------------+\n|1970-01-01T08:00:00.000+08:00|                   0.0|                   0.0|\n|1970-01-01T08:00:00.001+08:00| -3.932894010461041E-9| 1.2104201863039066E-8|\n|1970-01-01T08:00:00.002+08:00|-1.4021739447490164E-7| 1.9299268669082926E-7|\n|1970-01-01T08:00:00.003+08:00| -7.057291240286645E-8|  5.127422242345858E-8|\n|1970-01-01T08:00:00.004+08:00|    19.021130288047125|    -6.180339875198807|\n|1970-01-01T08:00:00.005+08:00|     9.999999850988388| 3.501852745067114E-16|\n|1970-01-01T08:00:00.019+08:00| -3.932894898639461E-9|-1.2104202549376264E-8|\n+-----------------------------+----------------------+----------------------+\n')])])]),a("p",[t._v("注：基于傅里叶变换结果的共轭性质，压缩结果只保留前一半；根据给定的压缩参数，从低频到高频保留数据点，直到保留的能量比例超过该值；保留最后一个数据点以表示序列长度。")])])}),[],!1,null,null,null);s.default=r.exports}}]);