(window.webpackJsonp=window.webpackJsonp||[]).push([[190],{560:function(t,s,n){"use strict";n.r(s);var a=n(45),e=Object(a.a)({},(function(){var t=this,s=t.$createElement,n=t._self._c||s;return n("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[n("h1",{attrs:{id:"zscore"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#zscore"}},[t._v("#")]),t._v(" ZScore")]),t._v(" "),n("h2",{attrs:{id:"函数简介"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),n("p",[t._v("本函数将输入序列使用z-score方法进行归一化。")]),t._v(" "),n("p",[n("strong",[t._v("函数名：")]),t._v(" ZSCORE")]),t._v(" "),n("p",[n("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。")]),t._v(" "),n("p",[n("strong",[t._v("参数：")])]),t._v(" "),n("ul",[n("li",[n("code",[t._v("method")]),t._v('：若设置为"batch"，则将数据全部读入后转换；若设置为"stream"，则需用户提供均值及方差进行流式计算转换。默认为"batch"。')]),t._v(" "),n("li",[n("code",[t._v("avg")]),t._v("：使用流式计算时的均值。")]),t._v(" "),n("li",[n("code",[t._v("sd")]),t._v("：使用流式计算时的标准差。")])]),t._v(" "),n("p",[n("strong",[t._v("输出序列")]),t._v("：输出单个序列，类型为DOUBLE。")]),t._v(" "),n("h2",{attrs:{id:"使用示例"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),n("h3",{attrs:{id:"全数据计算"}},[n("a",{staticClass:"header-anchor",attrs:{href:"#全数据计算"}},[t._v("#")]),t._v(" 全数据计算")]),t._v(" "),n("p",[t._v("输入序列：")]),t._v(" "),n("div",{staticClass:"language- extra-class"},[n("pre",{pre:!0,attrs:{class:"language-text"}},[n("code",[t._v("+-----------------------------+------------+\n|                         Time|root.test.s1|\n+-----------------------------+------------+\n|1970-01-01T08:00:00.100+08:00|         0.0|\n|1970-01-01T08:00:00.200+08:00|         0.0|\n|1970-01-01T08:00:00.300+08:00|         1.0|\n|1970-01-01T08:00:00.400+08:00|        -1.0|\n|1970-01-01T08:00:00.500+08:00|         0.0|\n|1970-01-01T08:00:00.600+08:00|         0.0|\n|1970-01-01T08:00:00.700+08:00|        -2.0|\n|1970-01-01T08:00:00.800+08:00|         2.0|\n|1970-01-01T08:00:00.900+08:00|         0.0|\n|1970-01-01T08:00:01.000+08:00|         0.0|\n|1970-01-01T08:00:01.100+08:00|         1.0|\n|1970-01-01T08:00:01.200+08:00|        -1.0|\n|1970-01-01T08:00:01.300+08:00|        -1.0|\n|1970-01-01T08:00:01.400+08:00|         1.0|\n|1970-01-01T08:00:01.500+08:00|         0.0|\n|1970-01-01T08:00:01.600+08:00|         0.0|\n|1970-01-01T08:00:01.700+08:00|        10.0|\n|1970-01-01T08:00:01.800+08:00|         2.0|\n|1970-01-01T08:00:01.900+08:00|        -2.0|\n|1970-01-01T08:00:02.000+08:00|         0.0|\n+-----------------------------+------------+\n")])])]),n("p",[t._v("用于查询的SQL语句：")]),t._v(" "),n("div",{staticClass:"language-sql extra-class"},[n("pre",{pre:!0,attrs:{class:"language-sql"}},[n("code",[n("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" zscore"),n("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),n("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),n("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),n("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test\n")])])]),n("p",[t._v("输出序列：")]),t._v(" "),n("div",{staticClass:"language- extra-class"},[n("pre",{pre:!0,attrs:{class:"language-text"}},[n("code",[t._v("+-----------------------------+--------------------+\n|                         Time|zscore(root.test.s1)|\n+-----------------------------+--------------------+\n|1970-01-01T08:00:00.100+08:00|-0.20672455764868078|\n|1970-01-01T08:00:00.200+08:00|-0.20672455764868078|\n|1970-01-01T08:00:00.300+08:00| 0.20672455764868078|\n|1970-01-01T08:00:00.400+08:00| -0.6201736729460423|\n|1970-01-01T08:00:00.500+08:00|-0.20672455764868078|\n|1970-01-01T08:00:00.600+08:00|-0.20672455764868078|\n|1970-01-01T08:00:00.700+08:00|  -1.033622788243404|\n|1970-01-01T08:00:00.800+08:00|  0.6201736729460423|\n|1970-01-01T08:00:00.900+08:00|-0.20672455764868078|\n|1970-01-01T08:00:01.000+08:00|-0.20672455764868078|\n|1970-01-01T08:00:01.100+08:00| 0.20672455764868078|\n|1970-01-01T08:00:01.200+08:00| -0.6201736729460423|\n|1970-01-01T08:00:01.300+08:00| -0.6201736729460423|\n|1970-01-01T08:00:01.400+08:00| 0.20672455764868078|\n|1970-01-01T08:00:01.500+08:00|-0.20672455764868078|\n|1970-01-01T08:00:01.600+08:00|-0.20672455764868078|\n|1970-01-01T08:00:01.700+08:00|  3.9277665953249348|\n|1970-01-01T08:00:01.800+08:00|  0.6201736729460423|\n|1970-01-01T08:00:01.900+08:00|  -1.033622788243404|\n|1970-01-01T08:00:02.000+08:00|-0.20672455764868078|\n+-----------------------------+--------------------+\n")])])])])}),[],!1,null,null,null);s.default=e.exports}}]);