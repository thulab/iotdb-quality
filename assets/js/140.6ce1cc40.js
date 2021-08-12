(window.webpackJsonp=window.webpackJsonp||[]).push([[140],{517:function(t,s,a){"use strict";a.r(s);var n=a(45),r=Object(n.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"crosscorrelation"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#crosscorrelation"}},[t._v("#")]),t._v(" CrossCorrelation")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数用于计算两条时间序列的互相关函数值，\n对离散序列而言，互相关函数可以表示为\n$$CR(n) = \\frac{1}{N} \\sum_{m=1}^N S_1[m]S_2[m+n]$$\n常用于表征两条序列在不同对齐条件下的相似度。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" CROSSCORRELATION")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持两个输入序列，类型均为 INT32 / INT64 / FLOAT / DOUBLE。")]),t._v(" "),a("p",[a("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为DOUBLE。序列中共包含$2N-1$个数据点，\n其中正中心的值为两条序列按照预先对齐的结果计算的互相关系数（即等于以上公式的$CR(0)$），\n前半部分的值表示将后一条输入序列向前平移时计算的互相关系数，\n直至两条序列没有重合的数据点（不包含完全分离时的结果$CR(-N)=0.0$），\n后半部分类似。\n用公式可表示为（所有序列的索引从1开始计数）：\n$$OS[i] = CR(-N+i) = \\frac{1}{N} \\sum_{m=1}^{i} S_1[m]S_2[N-i+m],\\ if\\ i <= N$$\n$$OS[i] = CR(i-N) = \\frac{1}{N} \\sum_{m=1}^{2N-i} S_1[i-N+m]S_2[m],\\ if\\ i > N$$")]),t._v(" "),a("p",[a("strong",[t._v("提示：")])]),t._v(" "),a("ul",[a("li",[t._v("两条序列中的"),a("code",[t._v("null")]),t._v("和"),a("code",[t._v("NaN")]),t._v("值会被忽略，在计算中表现为0。")])]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+---------------+\n|                         Time|root.test.d1.s1|root.test.d1.s2|\n+-----------------------------+---------------+---------------+\n|2020-01-01T00:00:01.000+08:00|           null|              6|\n|2020-01-01T00:00:02.000+08:00|              2|              7|\n|2020-01-01T00:00:03.000+08:00|              3|            NaN|\n|2020-01-01T00:00:04.000+08:00|              4|              9|\n|2020-01-01T00:00:05.000+08:00|              5|             10|\n+-----------------------------+---------------+---------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" crosscorrelation"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" s2"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1 "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("where")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("time")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("<=")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("2020")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("-")]),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("01")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("-")]),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("01")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("00")]),t._v(":"),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("00")]),t._v(":"),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("05")]),t._v("\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+--------------------------------------------------+\n|                         Time|crosscorrelation(root.test.d1.s1, root.test.d1.s2)|\n+-----------------------------+--------------------------------------------------+\n|1970-01-01T08:00:00.001+08:00|                                               0.0|\n|1970-01-01T08:00:00.002+08:00|                                               4.0|\n|1970-01-01T08:00:00.003+08:00|                                               9.6|\n|1970-01-01T08:00:00.004+08:00|                                              13.4|\n|1970-01-01T08:00:00.005+08:00|                                              20.0|\n|1970-01-01T08:00:00.006+08:00|                                              15.6|\n|1970-01-01T08:00:00.007+08:00|                                               9.2|\n|1970-01-01T08:00:00.008+08:00|                                              11.8|\n|1970-01-01T08:00:00.009+08:00|                                               6.0|\n+-----------------------------+--------------------------------------------------+\n")])])]),a("h3",{attrs:{id:"zeppelin示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#zeppelin示例"}},[t._v("#")]),t._v(" Zeppelin示例")]),t._v(" "),a("p",[t._v("链接: "),a("a",{attrs:{href:"http://101.6.15.213:18181/#/notebook/2GETVW6AT",target:"_blank",rel:"noopener noreferrer"}},[t._v("http://101.6.15.213:18181/#/notebook/2GETVW6AT"),a("OutboundLink")],1)])])}),[],!1,null,null,null);s.default=r.exports}}]);