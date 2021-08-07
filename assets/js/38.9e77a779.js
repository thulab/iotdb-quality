(window.webpackJsonp=window.webpackJsonp||[]).push([[38],{410:function(e,t,s){"use strict";s.r(t);var a=s(45),n=Object(a.a)({},(function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("ContentSlotsDistributor",{attrs:{"slot-key":e.$parent.slotKey}},[s("h1",{attrs:{id:"mode"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#mode"}},[e._v("#")]),e._v(" Mode")]),e._v(" "),s("h2",{attrs:{id:"usage"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#usage"}},[e._v("#")]),e._v(" Usage")]),e._v(" "),s("p",[e._v("This function is used to calculate the mode of time series, that is, the value that occurs most frequently.")]),e._v(" "),s("p",[s("strong",[e._v("Name:")]),e._v(" MODE")]),e._v(" "),s("p",[s("strong",[e._v("Input Series:")]),e._v(" Only support a single input series. The type is arbitrary.")]),e._v(" "),s("p",[s("strong",[e._v("Output Series:")]),e._v(" Output a single series. The type is the same as the input. There is only one data point in the series, whose timestamp is 0 and value is the mode.")]),e._v(" "),s("p",[s("strong",[e._v("Note:")])]),e._v(" "),s("ul",[s("li",[e._v("If there are multiple values with the most occurrences, the arbitrary one will be output.")]),e._v(" "),s("li",[e._v("Missing points and null points in the input series will be ignored, but "),s("code",[e._v("NaN")]),e._v(" will not.")])]),e._v(" "),s("h2",{attrs:{id:"examples"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#examples"}},[e._v("#")]),e._v(" Examples")]),e._v(" "),s("p",[e._v("Input series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+---------------+\n|                         Time|root.test.d2.s2|\n+-----------------------------+---------------+\n|1970-01-01T08:00:00.001+08:00|          Hello|\n|1970-01-01T08:00:00.002+08:00|          hello|\n|1970-01-01T08:00:00.003+08:00|          Hello|\n|1970-01-01T08:00:00.004+08:00|          World|\n|1970-01-01T08:00:00.005+08:00|          World|\n|1970-01-01T08:00:01.600+08:00|          World|\n|1970-01-15T09:37:34.451+08:00|          Hello|\n|1970-01-15T09:37:34.452+08:00|          hello|\n|1970-01-15T09:37:34.453+08:00|          Hello|\n|1970-01-15T09:37:34.454+08:00|          World|\n|1970-01-15T09:37:34.455+08:00|          World|\n+-----------------------------+---------------+\n")])])]),s("p",[e._v("SQL for query:")]),e._v(" "),s("div",{staticClass:"language-sql extra-class"},[s("pre",{pre:!0,attrs:{class:"language-sql"}},[s("code",[s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("select")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("mode")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v("(")]),e._v("s2"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(")")]),e._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[e._v("from")]),e._v(" root"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("test"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[e._v(".")]),e._v("d2\n")])])]),s("p",[e._v("Output series:")]),e._v(" "),s("div",{staticClass:"language- extra-class"},[s("pre",{pre:!0,attrs:{class:"language-text"}},[s("code",[e._v("+-----------------------------+---------------------+\n|                         Time|mode(root.test.d2.s2)|\n+-----------------------------+---------------------+\n|1970-01-01T08:00:00.000+08:00|                World|\n+-----------------------------+---------------------+\n")])])])])}),[],!1,null,null,null);t.default=n.exports}}]);