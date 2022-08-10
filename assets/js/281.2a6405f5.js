(window.webpackJsonp=window.webpackJsonp||[]).push([[281],{651:function(t,s,a){"use strict";a.r(s);var e=a(45),r=Object(e.a)({},(function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[a("h1",{attrs:{id:"regexmatch"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#regexmatch"}},[t._v("#")]),t._v(" RegexMatch")]),t._v(" "),a("h2",{attrs:{id:"函数简介"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),a("p",[t._v("本函数用于正则表达式匹配文本中的具体内容并返回。")]),t._v(" "),a("p",[a("strong",[t._v("函数名：")]),t._v(" REGEXMATCH")]),t._v(" "),a("p",[a("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 TEXT。")]),t._v(" "),a("p",[a("strong",[t._v("参数：")])]),t._v(" "),a("ul",[a("li",[a("code",[t._v("regex")]),t._v(": 匹配的正则表达式，支持所有Java正则表达式语法，比如"),a("code",[t._v("\\d+\\.\\d+\\.\\d+\\.\\d+")]),t._v("将会匹配任意IPv4地址.")]),t._v(" "),a("li",[a("code",[t._v("group")]),t._v(": 输出的匹配组序号，根据java.util.regex规定，第0组为整个正则表达式，此后的组按照左括号出现的顺序依次编号。\n如"),a("code",[t._v("A(B(CD))")]),t._v("中共有三个组，第0组"),a("code",[t._v("A(B(CD))")]),t._v("，第1组"),a("code",[t._v("B(CD)")]),t._v("和第2组"),a("code",[t._v("CD")]),t._v("。")])]),t._v(" "),a("p",[a("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为TEXT。")]),t._v(" "),a("p",[a("strong",[t._v("提示：")]),t._v(" 空值或无法匹配给定的正则表达式的数据点没有输出结果。")]),t._v(" "),a("h2",{attrs:{id:"使用示例"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),a("p",[t._v("输入序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v("+-----------------------------+-------------------------------+\n|                         Time|                root.test.d1.s1|\n+-----------------------------+-------------------------------+\n|2021-01-01T00:00:01.000+08:00|        [192.168.0.1] [SUCCESS]|\n|2021-01-01T00:00:02.000+08:00|       [192.168.0.24] [SUCCESS]|\n|2021-01-01T00:00:03.000+08:00|           [192.168.0.2] [FAIL]|\n|2021-01-01T00:00:04.000+08:00|        [192.168.0.5] [SUCCESS]|\n|2021-01-01T00:00:05.000+08:00|      [192.168.0.124] [SUCCESS]|\n+-----------------------------+-------------------------------+\n")])])]),a("p",[t._v("用于查询的SQL语句：")]),t._v(" "),a("div",{staticClass:"language-sql extra-class"},[a("pre",{pre:!0,attrs:{class:"language-sql"}},[a("code",[a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" regexmatch"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"regex"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"\\d+\\.\\d+\\.\\d+\\.\\d+"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"group"')]),a("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),a("span",{pre:!0,attrs:{class:"token string"}},[t._v('"0"')]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),a("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),a("p",[t._v("输出序列：")]),t._v(" "),a("div",{staticClass:"language- extra-class"},[a("pre",{pre:!0,attrs:{class:"language-text"}},[a("code",[t._v('+-----------------------------+----------------------------------------------------------------------+\n|                         Time|regexmatch(root.test.d1.s1, "regex"="\\d+\\.\\d+\\.\\d+\\.\\d+", "group"="0")|\n+-----------------------------+----------------------------------------------------------------------+\n|2021-01-01T00:00:01.000+08:00|                                                           192.168.0.1|\n|2021-01-01T00:00:02.000+08:00|                                                          192.168.0.24|\n|2021-01-01T00:00:03.000+08:00|                                                           192.168.0.2|\n|2021-01-01T00:00:04.000+08:00|                                                           192.168.0.5|\n|2021-01-01T00:00:05.000+08:00|                                                         192.168.0.124|\n+-----------------------------+----------------------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=r.exports}}]);