(window.webpackJsonp=window.webpackJsonp||[]).push([[142],{512:function(e,s,t){"use strict";t.r(s);var a=t(45),n=Object(a.a)({},(function(){var e=this,s=e.$createElement,t=e._self._c||s;return t("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[t("h1",{attrs:{id:"valuerepair"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#valuerepair"}},[e._v("#")]),e._v(" ValueRepair")]),e._v(" "),t("h2",{attrs:{id:"函数简介"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[e._v("#")]),e._v(" 函数简介")]),e._v(" "),t("p",[e._v("本函数用于对时间序列的数值进行修复。目前，本函数支持两种修复方法："),t("strong",[e._v("Screen")]),e._v("是一种基于速度阈值的方法，在最小改动的前提下使得所有的速度符合阈值要求；"),t("strong",[e._v("LsGreedy")]),e._v("是一种基于速度变化似然的方法，将速度变化建模为高斯分布，并采用贪心算法极大化似然函数。")]),e._v(" "),t("p",[t("strong",[e._v("函数名：")]),e._v(" VALUEREPAIR")]),e._v(" "),t("p",[t("strong",[e._v("输入序列：")]),e._v(" 仅支持单个输入序列，类型为 INT32 / INT64 / FLOAT / DOUBLE。")]),e._v(" "),t("p",[t("strong",[e._v("参数：")])]),e._v(" "),t("ul",[t("li",[t("code",[e._v("method")]),e._v("：修复时采用的方法，取值为'Screen'或'LsGreedy'。在缺省情况下，使用Screen方法进行修复。")]),e._v(" "),t("li",[t("code",[e._v("minSpeed")]),e._v("：该参数仅在使用Screen方法时有效。当速度小于该值时会被视作数值异常点加以修复。在缺省情况下为中位数减去三倍绝对中位差。")]),e._v(" "),t("li",[t("code",[e._v("maxSpeed")]),e._v("：该参数仅在使用Screen方法时有效。当速度大于该值时会被视作数值异常点加以修复。在缺省情况下为中位数加上三倍绝对中位差。")]),e._v(" "),t("li",[t("code",[e._v("center")]),e._v("：该参数仅在使用LsGreedy方法时有效。对速度变化分布建立的高斯模型的中心。在缺省情况下为0。")]),e._v(" "),t("li",[t("code",[e._v("sigma")]),e._v(" ：该参数仅在使用LsGreedy方法时有效。对速度变化分布建立的高斯模型的标准差。在缺省情况下为绝对中位差。")])]),e._v(" "),t("p",[t("strong",[e._v("输出序列：")]),e._v(" 输出单个序列，类型与输入序列相同。该序列是修复后的输入序列。")]),e._v(" "),t("p",[t("strong",[e._v("提示：")]),e._v(" 输入序列中的"),t("code",[e._v("NaN")]),e._v("在修复之前会先进行线性插值填补。")]),e._v(" "),t("h2",{attrs:{id:"使用示例"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[e._v("#")]),e._v(" 使用示例")]),e._v(" "),t("h3",{attrs:{id:"使用screen方法进行修复"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#使用screen方法进行修复"}},[e._v("#")]),e._v(" 使用Screen方法进行修复")]),e._v(" "),t("p",[e._v("当"),t("code",[e._v("method")]),e._v("缺省或取值为'Screen'时，本函数将使用Screen方法进行数值修复。")]),e._v(" "),t("p",[e._v("输入序列：")]),e._v(" "),t("div",{staticClass:"language- extra-class"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[e._v("+-----------------------------+---------------+\n|                         Time|root.test.d2.s1|\n+-----------------------------+---------------+\n|2020-01-01T00:00:02.000+08:00|          100.0|\n|2020-01-01T00:00:03.000+08:00|          101.0|\n|2020-01-01T00:00:04.000+08:00|          102.0|\n|2020-01-01T00:00:06.000+08:00|          104.0|\n|2020-01-01T00:00:08.000+08:00|          126.0|\n|2020-01-01T00:00:10.000+08:00|          108.0|\n|2020-01-01T00:00:14.000+08:00|          112.0|\n|2020-01-01T00:00:15.000+08:00|          113.0|\n|2020-01-01T00:00:16.000+08:00|          114.0|\n|2020-01-01T00:00:18.000+08:00|          116.0|\n|2020-01-01T00:00:20.000+08:00|          118.0|\n|2020-01-01T00:00:22.000+08:00|          100.0|\n|2020-01-01T00:00:26.000+08:00|          124.0|\n|2020-01-01T00:00:28.000+08:00|          126.0|\n|2020-01-01T00:00:30.000+08:00|            NaN|\n+-----------------------------+---------------+\n")])])]),t("p",[e._v("用于查询的SQL语句：")]),e._v(" "),t("div",{staticClass:"language-sql extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sql"}},[t("code",[t("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" valuerepair"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s1"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),t("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d2\n")])])]),t("p",[e._v("输出序列：")]),e._v(" "),t("div",{staticClass:"language- extra-class"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[e._v("+-----------------------------+----------------------------+\n|                         Time|valuerepair(root.test.d2.s1)|\n+-----------------------------+----------------------------+\n|2020-01-01T00:00:02.000+08:00|                       100.0|\n|2020-01-01T00:00:03.000+08:00|                       101.0|\n|2020-01-01T00:00:04.000+08:00|                       102.0|\n|2020-01-01T00:00:06.000+08:00|                       104.0|\n|2020-01-01T00:00:08.000+08:00|                       106.0|\n|2020-01-01T00:00:10.000+08:00|                       108.0|\n|2020-01-01T00:00:14.000+08:00|                       112.0|\n|2020-01-01T00:00:15.000+08:00|                       113.0|\n|2020-01-01T00:00:16.000+08:00|                       114.0|\n|2020-01-01T00:00:18.000+08:00|                       116.0|\n|2020-01-01T00:00:20.000+08:00|                       118.0|\n|2020-01-01T00:00:22.000+08:00|                       120.0|\n|2020-01-01T00:00:26.000+08:00|                       124.0|\n|2020-01-01T00:00:28.000+08:00|                       126.0|\n|2020-01-01T00:00:30.000+08:00|                       128.0|\n+-----------------------------+----------------------------+\n")])])]),t("h3",{attrs:{id:"使用lsgreedy方法进行修复"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#使用lsgreedy方法进行修复"}},[e._v("#")]),e._v(" 使用LsGreedy方法进行修复")]),e._v(" "),t("p",[e._v("当"),t("code",[e._v("method")]),e._v("取值为'LsGreedy'时，本函数将使用LsGreedy方法进行数值修复。")]),e._v(" "),t("p",[e._v("输入序列同上，用于查询的SQL语句如下：")]),e._v(" "),t("div",{staticClass:"language-sql extra-class"},[t("pre",{pre:!0,attrs:{class:"language-sql"}},[t("code",[t("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" valuerepair"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s1"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(",")]),t("span",{pre:!0,attrs:{class:"token string"}},[e._v("'method'")]),t("span",{pre:!0,attrs:{class:"token operator"}},[e._v("=")]),t("span",{pre:!0,attrs:{class:"token string"}},[e._v("'LsGreedy'")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),t("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d2\n")])])]),t("p",[e._v("输出序列：")]),e._v(" "),t("div",{staticClass:"language- extra-class"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[e._v('+-----------------------------+-------------------------------------------------+\n|                         Time|valuerepair(root.test.d2.s1, "method"="LsGreedy")|\n+-----------------------------+-------------------------------------------------+\n|2020-01-01T00:00:02.000+08:00|                                            100.0|\n|2020-01-01T00:00:03.000+08:00|                                            101.0|\n|2020-01-01T00:00:04.000+08:00|                                            102.0|\n|2020-01-01T00:00:06.000+08:00|                                            104.0|\n|2020-01-01T00:00:08.000+08:00|                                            106.0|\n|2020-01-01T00:00:10.000+08:00|                                            108.0|\n|2020-01-01T00:00:14.000+08:00|                                            112.0|\n|2020-01-01T00:00:15.000+08:00|                                            113.0|\n|2020-01-01T00:00:16.000+08:00|                                            114.0|\n|2020-01-01T00:00:18.000+08:00|                                            116.0|\n|2020-01-01T00:00:20.000+08:00|                                            118.0|\n|2020-01-01T00:00:22.000+08:00|                                            120.0|\n|2020-01-01T00:00:26.000+08:00|                                            124.0|\n|2020-01-01T00:00:28.000+08:00|                                            126.0|\n|2020-01-01T00:00:30.000+08:00|                                            128.0|\n+-----------------------------+-------------------------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=n.exports}}]);