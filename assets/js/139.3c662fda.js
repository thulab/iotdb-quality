(window.webpackJsonp=window.webpackJsonp||[]).push([[139],{509:function(t,s,a){"use strict";a.r(s);var c=a(45),m=Object(c.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"timeweightedavg"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#timeweightedavg"}},[t._v("#")]),t._v(" TimeWeightedAvg")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数用于计算时间序列的时间加权平均值，即在相同时间单位下的数值积分除以序列总的时间跨度。更多关于数值积分计算的信息请参考"),a("code",[t._v("Integral")]),t._v("函数。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" TIMEWEIGHTEDAVG")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。")]),t._v(" "),a("p",[a("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为DOUBLE，序列仅包含一个时间戳为0、值为时间加权平均结果的数据点。")]),t._v(" "),a("p",[a("strong",[t._v("提示：")])]),t._v(" "),a("ul",[a("li",[a("p",[t._v("时间加权的平均值等于在任意时间单位"),a("code",[t._v("unit")]),t._v("下计算的数值积分（即折线图中每相邻两个数据点和时间轴形成的直角梯形的面积之和），\n除以相同时间单位下输入序列的时间跨度，其值与具体采用的时间单位无关，默认与IoTDB时间单位一致。")])]),t._v(" "),a("li",[a("p",[t._v("数据中的"),a("code",[t._v("NaN")]),t._v("将会被忽略。折线将以临近两个有值数据点为准。")])]),t._v(" "),a("li",[a("p",[t._v("输入序列为空时，函数输出结果为0；仅有一个数据点时，输出结果为该点数值。")])])]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:01.000+08:00|              1|\n|2020-01-01T00:00:02.000+08:00|              2|\n|2020-01-01T00:00:03.000+08:00|              5|\n|2020-01-01T00:00:04.000+08:00|              6|\n|2020-01-01T00:00:05.000+08:00|              7|\n|2020-01-01T00:00:08.000+08:00|              8|\n|2020-01-01T00:00:09.000+08:00|            NaN|\n|2020-01-01T00:00:10.000+08:00|             10|\n+-----------------------------+---------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" timeweightedavg"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1 "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("where")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("time")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("<=")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("2020")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("-")]),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("01")]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("-")]),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("01")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("00")]),t._v(":"),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("00")]),t._v(":"),a("span",{pre:!0,attrs:{class:"token number"}},[t._v("10")]),t._v("\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+--------------------------------+\n|                         Time|timeweightedavg(root.test.d1.s1)|\n+-----------------------------+--------------------------------+\n|1970-01-01T08:00:00.000+08:00|                            5.75|\n+-----------------------------+--------------------------------+\n")])])]),a("p",[t._v("其计算公式为：\n")]),a("p",[a("mjx-container",{staticClass:"MathJax",attrs:{jax:"CHTML",display:"true"}},[a("mjx-math",{staticClass:" MJX-TEX",attrs:{display:"true"}},[a("mjx-mfrac",[a("mjx-frac",{attrs:{type:"d"}},[a("mjx-num",[a("mjx-nstrut",{attrs:{type:"d"}}),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"1"}})],1)],1),a("mjx-dbox",[a("mjx-dtable",[a("mjx-line",{attrs:{type:"d"}}),a("mjx-row",[a("mjx-den",[a("mjx-dstrut",{attrs:{type:"d"}}),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"2"}})],1)],1)],1)],1)],1)],1)],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"["}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"1"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"2"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"D7"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"1"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"2"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"5"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"D7"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"1"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"5"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"6"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"D7"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"1"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"6"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"7"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"D7"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"1"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"7"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"8"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"D7"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"3"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"("}})],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"8"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"+"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"1"}}),a("mjx-c",{attrs:{c:"0"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:")"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"D7"}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"3"}},[a("mjx-c",{attrs:{c:"2"}})],1),a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"]"}})],1),a("mjx-TeXAtom",[a("mjx-mo",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"/"}})],1)],1),a("mjx-mn",{staticClass:"mjx-n"},[a("mjx-c",{attrs:{c:"1"}}),a("mjx-c",{attrs:{c:"0"}})],1),a("mjx-mo",{staticClass:"mjx-n",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"="}})],1),a("mjx-mn",{staticClass:"mjx-n",attrs:{space:"4"}},[a("mjx-c",{attrs:{c:"5"}}),a("mjx-c",{attrs:{c:"."}}),a("mjx-c",{attrs:{c:"7"}}),a("mjx-c",{attrs:{c:"5"}})],1)],1)],1)],1),a("p")])}),[],!1,null,null,null);s.default=m.exports}}]);