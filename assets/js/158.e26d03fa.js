(window.webpackJsonp=window.webpackJsonp||[]).push([[158],{531:function(s,t,a){"use strict";a.r(t);var e=a(45),n=Object(e.a)({},(function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("ContentSlotsDistributor",{attrs:{"slot-key":s.$parent.slotKey}},[a("h1",{attrs:{id:"resample"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#resample"}},[s._v("#")]),s._v(" Resample")]),s._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[s._v("#")]),s._v(" 函数简介")]),s._v(" "),a("p",[s._v("本函数对输入序列按照指定的频率进行重采样，包括上采样和下采样。目前，本函数支持的上采样方法包括"),a("code",[s._v("NaN")]),s._v("填充法(NaN)、前值填充法(FFill)、后值填充法(BFill)以及线性插值法(Linear)；本函数支持的下采样方法为分组聚合，聚合方法包括最大值(Max)、最小值(Min)、首值(First)、末值(Last)、平均值(Mean)和中位数(Median)。")]),s._v(" "),a("p",[a("strong",[s._v("函数名：")]),s._v(" RESAMPLE")]),s._v(" "),a("p",[a("strong",[s._v("输入序列：")]),s._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。")]),s._v(" "),a("p",[a("strong",[s._v("参数：")])]),s._v(" "),a("ul",[a("li",[a("code",[s._v("every")]),s._v("：重采样频率，是一个有单位的正数。目前支持五种单位，分别是'ms'（毫秒）、's'（秒）、'm'（分钟）、'h'（小时）和'd'（天）。该参数不允许缺省。")]),s._v(" "),a("li",[a("code",[s._v("interp")]),s._v("：上采样的插值方法，取值为'NaN'、'FFill'、'BFill'或'Linear'。在缺省情况下，使用"),a("code",[s._v("NaN")]),s._v("填充法。")]),s._v(" "),a("li",[a("code",[s._v("aggr")]),s._v("：下采样的聚合方法，取值为'Max'、'Min'、'First'、'Last'、'Mean'或'Median'。在缺省情况下，使用平均数聚合。")]),s._v(" "),a("li",[a("code",[s._v("start")]),s._v("：重采样的起始时间（包含），是一个格式为'yyyy-MM-dd HH:mm:ss'的时间字符串。在缺省情况下，使用第一个有效数据点的时间戳。")]),s._v(" "),a("li",[a("code",[s._v("end")]),s._v("：重采样的结束时间（不包含），是一个格式为'yyyy-MM-dd HH:mm:ss'的时间字符串。在缺省情况下，使用最后一个有效数据点的时间戳。")])]),s._v(" "),a("p",[a("strong",[s._v("输出序列：")]),s._v(" 输出单个序列，类型为DOUBLE。该序列按照重采样频率严格等间隔分布。")]),s._v(" "),a("p",[a("strong",[s._v("提示：")]),s._v(" 数据中的"),a("code",[s._v("NaN")]),s._v("将会被忽略。")]),s._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[s._v("#")]),s._v(" 使用示例")]),s._v(" "),a("h3",{attrs:{id:"上采样"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#上采样"}},[s._v("#")]),s._v(" 上采样")]),s._v(" "),a("p",[s._v("当重采样频率高于数据原始频率时，将会进行上采样。")]),s._v(" "),a("p",[s._v("输入序列：")]),s._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[s._v("+-----------------------------+---------------+\n|                         Time|root.test.d1.s1|\n+-----------------------------+---------------+\n|2021-03-06T16:00:00.000+08:00|           3.09|\n|2021-03-06T16:15:00.000+08:00|           3.53|\n|2021-03-06T16:30:00.000+08:00|            3.5|\n|2021-03-06T16:45:00.000+08:00|           3.51|\n|2021-03-06T17:00:00.000+08:00|           3.41|\n+-----------------------------+---------------+\n")])])]),a("p",[s._v("用于查询的SQL语句：")]),s._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("select")]),s._v(" resample"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("(")]),s._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'every'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'5m'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'interp'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'linear'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(")")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("from")]),s._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("d1\n")])])]),a("p",[s._v("输出序列：")]),s._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[s._v('+-----------------------------+----------------------------------------------------------+\n|                         Time|resample(root.test.d1.s1, "every"="5m", "interp"="linear")|\n+-----------------------------+----------------------------------------------------------+\n|2021-03-06T16:00:00.000+08:00|                                        3.0899999141693115|\n|2021-03-06T16:05:00.000+08:00|                                        3.2366665999094644|\n|2021-03-06T16:10:00.000+08:00|                                        3.3833332856496177|\n|2021-03-06T16:15:00.000+08:00|                                        3.5299999713897705|\n|2021-03-06T16:20:00.000+08:00|                                        3.5199999809265137|\n|2021-03-06T16:25:00.000+08:00|                                         3.509999990463257|\n|2021-03-06T16:30:00.000+08:00|                                                       3.5|\n|2021-03-06T16:35:00.000+08:00|                                         3.503333330154419|\n|2021-03-06T16:40:00.000+08:00|                                         3.506666660308838|\n|2021-03-06T16:45:00.000+08:00|                                         3.509999990463257|\n|2021-03-06T16:50:00.000+08:00|                                        3.4766666889190674|\n|2021-03-06T16:55:00.000+08:00|                                         3.443333387374878|\n|2021-03-06T17:00:00.000+08:00|                                        3.4100000858306885|\n+-----------------------------+----------------------------------------------------------+\n')])])]),a("h3",{attrs:{id:"下采样"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#下采样"}},[s._v("#")]),s._v(" 下采样")]),s._v(" "),a("p",[s._v("当重采样频率低于数据原始频率时，将会进行下采样。")]),s._v(" "),a("p",[s._v("输入序列同上，用于查询的SQL语句如下：")]),s._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("select")]),s._v(" resample"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("(")]),s._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'every'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'30m'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'aggr'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'first'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(")")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("from")]),s._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("d1\n")])])]),a("p",[s._v("输出序列：")]),s._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[s._v('+-----------------------------+--------------------------------------------------------+\n|                         Time|resample(root.test.d1.s1, "every"="30m", "aggr"="first")|\n+-----------------------------+--------------------------------------------------------+\n|2021-03-06T16:00:00.000+08:00|                                      3.0899999141693115|\n|2021-03-06T16:30:00.000+08:00|                                                     3.5|\n|2021-03-06T17:00:00.000+08:00|                                      3.4100000858306885|\n+-----------------------------+--------------------------------------------------------+\n')])])]),a("h3",{attrs:{id:"指定重采样时间段"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#指定重采样时间段"}},[s._v("#")]),s._v(" 指定重采样时间段")]),s._v(" "),a("p",[s._v("可以使用"),a("code",[s._v("start")]),s._v("和"),a("code",[s._v("end")]),s._v("两个参数指定重采样的时间段，超出实际时间范围的部分会被插值填补。")]),s._v(" "),a("p",[s._v("输入序列同上，用于查询的SQL语句如下：")]),s._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("select")]),s._v(" resample"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("(")]),s._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'every'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'30m'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(",")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'start'")]),a("span",{pre:!0,attrs:{class:"token operator"}},[s._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[s._v("'2021-03-06 15:00:00'")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(")")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[s._v("from")]),s._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(".")]),s._v("d1\n")])])]),a("p",[s._v("输出序列：")]),s._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[s._v('+-----------------------------+-----------------------------------------------------------------------+\n|                         Time|resample(root.test.d1.s1, "every"="30m", "start"="2021-03-06 15:00:00")|\n+-----------------------------+-----------------------------------------------------------------------+\n|2021-03-06T15:00:00.000+08:00|                                                                    NaN|\n|2021-03-06T15:30:00.000+08:00|                                                                    NaN|\n|2021-03-06T16:00:00.000+08:00|                                                      3.309999942779541|\n|2021-03-06T16:30:00.000+08:00|                                                     3.5049999952316284|\n|2021-03-06T17:00:00.000+08:00|                                                     3.4100000858306885|\n+-----------------------------+-----------------------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);t.default=n.exports}}]);