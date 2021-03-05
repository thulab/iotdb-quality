module.exports = {
  title: 'TsClean-IoTDB',  // 设置网站标题
  description: 'TsClean in Apache IoTDB\'s UDFs, including data quality indicator calculation, value filling, value repairing',
  base: '/tsclean-iotdb/',
  locales: {
    '/': {
      lang: 'en-US'
    },
    '/zh/': {
      lang: 'zh-CN'
    }
  },
  themeConfig: {
    locales: {
      '/': {
        selectText: 'Languages',
        label: 'English',
        ariaLabel: 'Languages',
        editLinkText: 'Edit this page on GitHub',
        serviceWorker: {
          updatePopup: {
            message: "New content is available.",
            buttonText: "Refresh"
          }
        },
        algolia: {},
        nav: [
          { text: 'Document', link: '/' },
          { text: 'Download', link: '/Download.md' }
        ],
        sidebar: {
          '/': [
            "/", //指的是根目录的md文件 也就是 README.md 里面的内容      
            {
              title: "Universal Function",
              children: ["Universal/Histogram.md",
                "Universal/Sample.md",
                "Universal/Stddev.md",
                "Universal/Mode.md",
                "Universal/Spread.md"
              ]
            },
            {
              title: "Data Quality Indicator",
              children: ["DataQualityIndicator/Completeness.md",
                "DataQualityIndicator/Consistency.md",
                "DataQualityIndicator/Timeliness.md",
                "DataQualityIndicator/Validity.md"
              ]
            },
            {
              title: "Value Filling",
              children: ["ValueFilling/PreviousFill.md",
                "ValueFilling/LinearFill.md"
              ]
            },
            {
              title: "Value Repairing",
              children: ["ValueRepairing/ScreenRepair.md",
                "ValueRepairing/LsGreedyRepair.md"
              ]
            }
          ]
        },
        sidebarDepth: 1
      },
      '/zh/': {
        selectText: '选择语言',
        label: '简体中文',
        editLinkText: '在 GitHub 上编辑此页',
        // Service Worker 的配置
        serviceWorker: {
          updatePopup: {
            message: "发现新内容可用.",
            buttonText: "刷新"
          }
        },
        // 当前 locale 的 algolia docsearch 选项
        algolia: {},
        nav: [
          { text: '文档', link: '/zh/' },
          { text: '下载', link: '/zh/Download.md' }
        ],
        sidebar: {
          '/zh/': [
            "/zh/",
            {
              title: "通用函数",
              children: ["Universal/Histogram.md",
                "Universal/Sample.md",
                "Universal/Stddev.md",
                "Universal/Mode.md",
                "Universal/Spread.md"
              ]
            },
            {
              title: "数据质量指标",
              children: ["DataQualityIndicator/Completeness.md",
                "DataQualityIndicator/Consistency.md",
                "DataQualityIndicator/Timeliness.md",
                "DataQualityIndicator/Validity.md"
              ]
            },
            {
              title: "数值填补",
              children: ["ValueFilling/PreviousFill.md",
                "ValueFilling/LinearFill.md"
              ]
            },
            {
              title: "数值修复",
              children: ["ValueRepairing/ScreenRepair.md",
                "ValueRepairing/LsGreedyRepair.md"
              ]
            }
          ]
        },
        sidebarDepth: 1
      }
    }
  }
}