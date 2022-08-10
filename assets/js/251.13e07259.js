(window.webpackJsonp=window.webpackJsonp||[]).push([[251],{619:function(t,n,s){"use strict";s.r(n);var a=s(45),e=Object(a.a)({},(function(){var t=this,n=t.$createElement,s=t._self._c||n;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"qlb"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#qlb"}},[t._v("#")]),t._v(" QLB")]),t._v(" "),s("h2",{attrs:{id:"函数简介"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),s("p",[t._v("本函数对输入序列计算$Q_{LB} $​统计量，并计算对应的p值。p值越小表明序列越有可能为非平稳序列。")]),t._v(" "),s("p",[s("strong",[t._v("函数名：")]),t._v(" QLB")]),t._v(" "),s("p",[s("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。")]),t._v(" "),s("p",[s("strong",[t._v("参数：")])]),t._v(" "),s("ul",[s("li",[s("code",[t._v("lag")]),t._v("：计算时用到的最大延迟阶数，取值应为1至n-2之间的整数，n为序列采样总数。默认取n-2。")])]),t._v(" "),s("p",[s("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为DOUBLE。该序列是$Q_{LB} $统计量对应的p值，时间标签代表偏移阶数。")]),t._v(" "),s("p",[s("strong",[t._v("提示：")]),t._v(" $Q_{LB} $统计量由自相关系数求得，如需得到统计量而非p值，可以使用AutoCorrelation函数。")]),t._v(" "),s("h2",{attrs:{id:"使用示例"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),s("h3",{attrs:{id:"使用默认参数"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#使用默认参数"}},[t._v("#")]),t._v(" 使用默认参数")]),t._v(" "),s("p",[t._v("输入序列：")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|1970-01-01T00:00:00.100+08:00|           1.22|\n|1970-01-01T00:00:00.200+08:00|          -2.78|\n|1970-01-01T00:00:00.300+08:00|           1.53|\n|1970-01-01T00:00:00.400+08:00|           0.70|\n|1970-01-01T00:00:00.500+08:00|           0.75|\n|1970-01-01T00:00:00.600+08:00|          -0.72|\n|1970-01-01T00:00:00.700+08:00|          -0.22|\n|1970-01-01T00:00:00.800+08:00|           0.28|\n|1970-01-01T00:00:00.900+08:00|           0.57|\n|1970-01-01T00:00:01.000+08:00|          -0.22|\n|1970-01-01T00:00:01.100+08:00|          -0.72|\n|1970-01-01T00:00:01.200+08:00|           1.34|\n|1970-01-01T00:00:01.300+08:00|          -0.25|\n|1970-01-01T00:00:01.400+08:00|           0.17|\n|1970-01-01T00:00:01.500+08:00|           2.51|\n|1970-01-01T00:00:01.600+08:00|           1.42|\n|1970-01-01T00:00:01.700+08:00|          -1.34|\n|1970-01-01T00:00:01.800+08:00|          -0.01|\n|1970-01-01T00:00:01.900+08:00|          -0.49|\n|1970-01-01T00:00:02.000+08:00|           1.63|\n+-----------------------------+---------------+\n")])])]),s("p",[t._v("用于查询的SQL语句：")]),t._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" QLB"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),s("p",[t._v("输出序列：")]),t._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[t._v("+-----------------------------+--------------------+\n|                         Time|QLB(root.test.d1.s1)|\n+-----------------------------+--------------------+\n|1970-01-01T00:00:00.001+08:00|  0.2168702295315677|\n|1970-01-01T00:00:00.002+08:00|  0.3068948509261751|\n|1970-01-01T00:00:00.003+08:00|  0.4217859150918444|\n|1970-01-01T00:00:00.004+08:00|  0.5114539874276656|\n|1970-01-01T00:00:00.005+08:00|  0.6560619525616759|\n|1970-01-01T00:00:00.006+08:00|  0.7722398654053280|\n|1970-01-01T00:00:00.007+08:00|  0.8532491661465290|\n|1970-01-01T00:00:00.008+08:00|  0.9028575017542528|\n|1970-01-01T00:00:00.009+08:00|  0.9434989988192729|\n|1970-01-01T00:00:00.010+08:00|  0.8950280161464689|\n|1970-01-01T00:00:00.011+08:00|  0.7701048398839656|\n|1970-01-01T00:00:00.012+08:00|  0.7845536060001281|\n|1970-01-01T00:00:00.013+08:00|  0.5943030981705825|\n|1970-01-01T00:00:00.014+08:00|  0.4618413512531093|\n|1970-01-01T00:00:00.015+08:00|  0.2645948244673964|\n|1970-01-01T00:00:00.016+08:00|  0.3167530476666645|\n|1970-01-01T00:00:00.017+08:00|  0.2330010780351453|\n|1970-01-01T00:00:00.018+08:00|  0.0666611237622325|\n+-----------------------------+--------------------+\n")])])])])}),[],!1,null,null,null);n.default=e.exports}}]);