(window.webpackJsonp=window.webpackJsonp||[]).push([[280],{650:function(t,s,e){"use strict";e.r(s);var a=e(45),r=Object(a.a)({},(function(){var t=this,s=t.$createElement,e=t._self._c||s;return e("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[e("h1",{attrs:{id:"regexreplace"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#regexreplace"}},[t._v("#")]),t._v(" RegexReplace")]),t._v(" "),e("h2",{attrs:{id:"函数简介"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#函数简介"}},[t._v("#")]),t._v(" 函数简介")]),t._v(" "),e("p",[t._v("本函数用于将文本中符合正则表达式的匹配结果替换为指定的字符串。")]),t._v(" "),e("p",[e("strong",[t._v("函数名：")]),t._v(" REGEXREPLACE")]),t._v(" "),e("p",[e("strong",[t._v("输入序列：")]),t._v(" 仅支持单个输入序列，类型为 TEXT。")]),t._v(" "),e("p",[e("strong",[t._v("参数：")])]),t._v(" "),e("ul",[e("li",[e("code",[t._v("regex")]),t._v(": 需要替换的正则表达式，支持所有Java正则表达式语法。")]),t._v(" "),e("li",[e("code",[t._v("replace")]),t._v(": 替换后的字符串，支持Java正则表达式中的后向引用，\n形如'$1'指代了正则表达式"),e("code",[t._v("regex")]),t._v("中的第一个分组，并会在替换时自动填充匹配到的子串。")]),t._v(" "),e("li",[e("code",[t._v("limit")]),t._v(": 替换次数，大于等于-1的整数，默认为-1表示所有匹配的子串都会被替换。")]),t._v(" "),e("li",[e("code",[t._v("offset")]),t._v(": 需要跳过的匹配次数，即前"),e("code",[t._v("offset")]),t._v("次匹配到的字符子串并不会被替换，默认为0。")]),t._v(" "),e("li",[e("code",[t._v("reverse")]),t._v(": 是否需要反向计数，默认为false即按照从左向右的次序。")])]),t._v(" "),e("p",[e("strong",[t._v("输出序列：")]),t._v(" 输出单个序列，类型为TEXT。")]),t._v(" "),e("h2",{attrs:{id:"使用示例"}},[e("a",{staticClass:"header-anchor",attrs:{href:"#使用示例"}},[t._v("#")]),t._v(" 使用示例")]),t._v(" "),e("p",[t._v("输入序列：")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v("+-----------------------------+-------------------------------+\n|                         Time|                root.test.d1.s1|\n+-----------------------------+-------------------------------+\n|2021-01-01T00:00:01.000+08:00|        [192.168.0.1] [SUCCESS]|\n|2021-01-01T00:00:02.000+08:00|       [192.168.0.24] [SUCCESS]|\n|2021-01-01T00:00:03.000+08:00|           [192.168.0.2] [FAIL]|\n|2021-01-01T00:00:04.000+08:00|        [192.168.0.5] [SUCCESS]|\n|2021-01-01T00:00:05.000+08:00|      [192.168.0.124] [SUCCESS]|\n+-----------------------------+-------------------------------+\n")])])]),e("p",[t._v("用于查询的SQL语句：")]),t._v(" "),e("div",{staticClass:"language-sql extra-class"},[e("pre",{pre:!0,attrs:{class:"language-sql"}},[e("code",[e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("select")]),t._v(" regexreplace"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),t._v("s1"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"regex"')]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"192\\.168\\.0\\.(\\d+)"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"replace"')]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"cluster-$1"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"limit"')]),e("span",{pre:!0,attrs:{class:"token operator"}},[t._v("=")]),e("span",{pre:!0,attrs:{class:"token string"}},[t._v('"1"')]),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),e("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("from")]),t._v(" root"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("test"),e("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),t._v("d1\n")])])]),e("p",[t._v("输出序列：")]),t._v(" "),e("div",{staticClass:"language- extra-class"},[e("pre",{pre:!0,attrs:{class:"language-text"}},[e("code",[t._v('+-----------------------------+-----------------------------------------------------------+\n|                         Time|regexreplace(root.test.d1.s1, "regex"="192\\.168\\.0\\.(\\d+)",|\n|                             |                       "replace"="cluster-$1", "limit"="1")|\n+-----------------------------+-----------------------------------------------------------+\n|2021-01-01T00:00:01.000+08:00|                                      [cluster-1] [SUCCESS]|\n|2021-01-01T00:00:02.000+08:00|                                     [cluster-24] [SUCCESS]|\n|2021-01-01T00:00:03.000+08:00|                                         [cluster-2] [FAIL]|\n|2021-01-01T00:00:04.000+08:00|                                      [cluster-5] [SUCCESS]|\n|2021-01-01T00:00:05.000+08:00|                                    [cluster-124] [SUCCESS]|\n+-----------------------------+-----------------------------------------------------------+\n')])])])])}),[],!1,null,null,null);s.default=r.exports}}]);