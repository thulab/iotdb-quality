module.exports = {
  title: 'TsClean-IoTDB',  // 设置网站标题
  description : 'TsClean in Apache IoTDB\'s UDFs, including data quality indicator calculation, value filling, value repairing',
  base : '/tsclean-iotdb/',
  themeConfig : {
    nav : [
        { text: 'Document', link: '/' },
        { text: 'Download', link: '/error' }
    ],
    sidebar: {
        '/' : [
			"/", //指的是根目录的md文件 也就是 README.md 里面的内容
            "README_zh.md"  //根目录创建 apiword.md文件
        ]
    },
    sidebarDepth : 2
  }
}