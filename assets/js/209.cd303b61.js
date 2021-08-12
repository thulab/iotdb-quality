(window.webpackJsonp=window.webpackJsonp||[]).push([[209],{585:function(t,s,a){"use strict";a.r(s);var r=a(45),e=Object(r.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"mad"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#mad"}},[t._v("#")]),t._v(" Mad")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数用于计算单列数值型数据的精确或近似绝对中位差，绝对中位差为所有数值与其中位数绝对偏移量的中位数。")]),t._v(" "),a("p",[t._v("如有数据集${1,3,3,5,5,6,7,8,9}$，其中位数为5，所有数值与中位数的偏移量的绝对值为${0,0,1,2,2,2,3,4,4}$，其中位数为2，故而原数据集的绝对中位差为2。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" MAD")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。")]),t._v(" "),a("p",[a("strong",[t._v("参数：")])]),t._v(" "),a("ul",[a("li",[a("code",[t._v("error")]),t._v("：近似绝对中位差的基于数值的误差百分比，取值范围为[0,1)，默认值为0。如当"),a("code",[t._v("error")]),t._v("=0.01时，记精确绝对中位差为a，近似绝对中位差为b，不等式 $0.99a \\le b \\le 1.01a$ 成立。当"),a("code",[t._v("error")]),t._v("=0时，计算结果为精确绝对中位差。")])]),t._v(" "),a("p",[a("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为DOUBLE，序列仅包含一个时间戳为0、值为绝对中位差的数据点。")]),t._v(" "),a("p",[a("strong",[t._v("提示：")]),t._v(" 数据中的空值、缺失值和"),a("code",[t._v("NaN")]),t._v("将会被忽略。")]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("h3",{attrs:{id:"精确查询"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#精确查询"}},[t._v("#")]),t._v(" 精确查询")]),t._v(" "),a("p",[t._v("当"),a("code",[t._v("error")]),t._v("参数缺省或为0时，本函数计算精确绝对中位差。")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+------------+\n|                         Time|root.test.s0|\n+-----------------------------+------------+\n|2021-03-17T10:32:17.054+08:00|   0.5319929|\n|2021-03-17T10:32:18.054+08:00|   0.9304316|\n|2021-03-17T10:32:19.054+08:00|  -1.4800133|\n|2021-03-17T10:32:20.054+08:00|   0.6114087|\n|2021-03-17T10:32:21.054+08:00|   2.5163336|\n|2021-03-17T10:32:22.054+08:00|  -1.0845392|\n|2021-03-17T10:32:23.054+08:00|   1.0562582|\n|2021-03-17T10:32:24.054+08:00|   1.3867859|\n|2021-03-17T10:32:25.054+08:00| -0.45429882|\n|2021-03-17T10:32:26.054+08:00|   1.0353678|\n|2021-03-17T10:32:27.054+08:00|   0.7307929|\n|2021-03-17T10:32:28.054+08:00|   2.3167255|\n|2021-03-17T10:32:29.054+08:00|    2.342443|\n|2021-03-17T10:32:30.054+08:00|   1.5809103|\n|2021-03-17T10:32:31.054+08:00|   1.4829416|\n|2021-03-17T10:32:32.054+08:00|   1.5800357|\n|2021-03-17T10:32:33.054+08:00|   0.7124368|\n|2021-03-17T10:32:34.054+08:00| -0.78597564|\n|2021-03-17T10:32:35.054+08:00|   1.2058644|\n|2021-03-17T10:32:36.054+08:00|   1.4215064|\n|2021-03-17T10:32:37.054+08:00|   1.2808295|\n|2021-03-17T10:32:38.054+08:00|  -0.6173715|\n|2021-03-17T10:32:39.054+08:00|  0.06644377|\n|2021-03-17T10:32:40.054+08:00|    2.349338|\n|2021-03-17T10:32:41.054+08:00|   1.7335888|\n|2021-03-17T10:32:42.054+08:00|   1.5872132|\n............\nTotal line number = 10000\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" mad"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s0"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+------------------+\n|                         Time| mad(root.test.s0)|\n+-----------------------------+------------------+\n|1970-01-01T08:00:00.000+08:00|0.6806197166442871|\n+-----------------------------+------------------+\n")])])]),a("h3",{attrs:{id:"近似查询"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#近似查询"}},[t._v("#")]),t._v(" 近似查询")]),t._v(" "),a("p",[t._v("当"),a("code",[t._v("error")]),t._v("参数取值不为0时，本函数计算近似绝对中位差。")]),t._v(" "),a("p",[t._v("输入序列同上，用于查询的SQL语句如下：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" mad"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s0"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"error"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"0.01"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v('+-----------------------------+---------------------------------+\n|                         Time|mad(root.test.s0, "error"="0.01")|\n+-----------------------------+---------------------------------+\n|1970-01-01T08:00:00.000+08:00|               0.6806616245859518|\n+-----------------------------+---------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=e.exports}}]);