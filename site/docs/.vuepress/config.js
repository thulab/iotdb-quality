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
          { text: 'Download', link: '/en/Download.md' }
        ],
        sidebar: {
          '/': [
            "/", //指的是根目录的md文件 也就是 README.md 里面的内容      
            {
              title: "Data Profiling",
              children: ["/en/DataProfiling/Histogram.md",
                "/en/DataProfiling/Sample.md",
                "/en/DataProfiling/Stddev.md",
                "/en/DataProfiling/Mode.md",
                "/en/DataProfiling/Spread.md"
              ]
            },
            {
              title: "Data Quality",
              children: ["/en/DataQuality/Completeness.md",
                "/en/DataQuality/Consistency.md",
                "/en/DataQuality/Timeliness.md",
                "/en/DataQuality/Validity.md"
              ]
            },
            {
              title: "Anomaly",
              children: ["/en/Anomaly/PreviousFill.md",
                "/en/Anomaly/LinearFill.md",
                "/en/Anomaly/ScreenRepair.md",
                "/en/Anomaly/LsGreedyRepair.md"
              ]
            },
            {
              title: "Complex Event Processing",
              children: [
                "/en/ComplexEventProcessing/EventMatching.md",
                "/en/ComplexEventProcessing/EventRecovery.md"
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
              title: "数据画像",
              children: ["DataProfiling/Histogram.md",
                "DataProfiling/Sample.md",
                "DataProfiling/Stddev.md",
                "DataProfiling/Mode.md",
                "DataProfiling/Spread.md"
              ]
            },
            {
              title: "数据质量",
              children: ["DataQuality/Completeness.md",
                "DataQuality/Consistency.md",
                "DataQuality/Timeliness.md",
                "DataQuality/Validity.md"
              ]
            },
            {
              title: "数据异常",
              children: ["Anomaly/PreviousFill.md",
                "Anomaly/LinearFill.md",
                "Anomaly/ScreenRepair.md",
                "Anomaly/LsGreedyRepair.md"
              ]
            },
            {
              title: "复杂事件处理",
              children: [
                "ComplexEventProcessing/EventMatching.md",
                "ComplexEventProcessing/EventRecovery.md"
              ]
            }
          ]
        },
        sidebarDepth: 1
      }
    }
  }
}