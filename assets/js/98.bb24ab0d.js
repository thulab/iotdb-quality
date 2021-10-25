(window.webpackJsonp=window.webpackJsonp||[]).push([[98],{468:function(t,s,a){"use strict";a.r(s);var r=a(45),n=Object(r.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"iqr"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#iqr"}},[t._v("#")]),t._v(" IQR")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数用于检验超出上下四分位数1.5倍IQR的数据分布异常。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" IQR")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。")]),t._v(" "),a("p",[a("strong",[t._v("参数：")])]),t._v(" "),a("ul",[a("li",[a("code",[t._v("method")]),t._v('：若设置为"batch"，则将数据全部读入后检测；若设置为"stream"，则需用户提供上下四分位数进行流式检测。默认为"batch"。')]),t._v(" "),a("li",[a("code",[t._v("q1")]),t._v("：使用流式计算时的下四分位数。")]),t._v(" "),a("li",[a("code",[t._v("q3")]),t._v("：使用流式计算时的上四分位数。")])]),t._v(" "),a("p",[a("strong",[t._v("输出序列")]),t._v("：输出单个序列，类型为DOUBLE。")]),t._v(" "),a("p",[a("strong",[t._v("说明")]),t._v("："),a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML"}},[a("mjx-math",{staticClass:" MJX-TEX"},[a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"I"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"Q"}})],1),a("mjx-mi",{staticClass:"mjx-i"},[a("mjx-c",{attrs:{c:"R"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"="}})],1),a("mjx-msub",{attrs:{space:"4"}},[a("mjx-mi",{staticClass:"mjx-i",attrs:{noIC:"true"}},[a("mjx-c",{attrs:{c:"Q"}})],1),a("mjx-script",{staticStyle:{"vertical-align":"-0.15em"}},[a("mjx-mn",{staticClass:"mjx-n",attrs:{size:"s"}},[a("mjx-c",{attrs:{c:"3"}})],1)],1)],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"2212"}})],1),a("mjx-msub",{attrs:{space:"3"}},[a("mjx-mi",{staticClass:"mjx-i",attrs:{noIC:"true"}},[a("mjx-c",{attrs:{c:"Q"}})],1),a("mjx-script",{staticStyle:{"vertical-align":"-0.15em"}},[a("mjx-mn",{staticClass:"mjx-n",attrs:{size:"s"}},[a("mjx-c",{attrs:{c:"1"}})],1)],1)],1)],1)],1)],1),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("h3",{attrs:{id:"全数据计算"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#全数据计算"}},[t._v("#")]),t._v(" 全数据计算")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+------------+\n|                         Time|root.test.s1|\n+-----------------------------+------------+\n|1970-01-01T08:00:00.100+08:00|         0.0|\n|1970-01-01T08:00:00.200+08:00|         0.0|\n|1970-01-01T08:00:00.300+08:00|         1.0|\n|1970-01-01T08:00:00.400+08:00|        -1.0|\n|1970-01-01T08:00:00.500+08:00|         0.0|\n|1970-01-01T08:00:00.600+08:00|         0.0|\n|1970-01-01T08:00:00.700+08:00|        -2.0|\n|1970-01-01T08:00:00.800+08:00|         2.0|\n|1970-01-01T08:00:00.900+08:00|         0.0|\n|1970-01-01T08:00:01.000+08:00|         0.0|\n|1970-01-01T08:00:01.100+08:00|         1.0|\n|1970-01-01T08:00:01.200+08:00|        -1.0|\n|1970-01-01T08:00:01.300+08:00|        -1.0|\n|1970-01-01T08:00:01.400+08:00|         1.0|\n|1970-01-01T08:00:01.500+08:00|         0.0|\n|1970-01-01T08:00:01.600+08:00|         0.0|\n|1970-01-01T08:00:01.700+08:00|        10.0|\n|1970-01-01T08:00:01.800+08:00|         2.0|\n|1970-01-01T08:00:01.900+08:00|        -2.0|\n|1970-01-01T08:00:02.000+08:00|         0.0|\n+-----------------------------+------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" iqr"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+-----------------+\n|                         Time|iqr(root.test.s1)|\n+-----------------------------+-----------------+\n|1970-01-01T08:00:01.700+08:00|             10.0|\n+-----------------------------+-----------------+\n")])])])])}),[],!1,null,null,null);s.default=n.exports}}]);