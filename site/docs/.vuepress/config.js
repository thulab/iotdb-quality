module.exports = {
	title: 'IoTDB-Quality', // 设置网站标题
	description:
		"Apache IoTDB's UDFs about data quality",
	base: '/iotdb-quality/',
	head: [
		['script', { async: "async", src: 'https://www.googletagmanager.com/gtag/js?id=G-V8CG1Y9FW6' }],
		['script', { type: 'text/javascript', src: '/assets/push.js' }],
	],
	locales: {
		'/': {
			lang: 'en-US',
		},
		'/zh/': {
			lang: 'zh-CN',
		},
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
						message: 'New content is available.',
						buttonText: 'Refresh',
					},
				},
				algolia: {},
				nav: [
					{ text: 'Document', link: '/' },
					{ text: 'Download', link: '/en/Download.md' },
				],
				sidebar: {
					'/': [
						{
							title: 'Get Started',
							children: [
								'/',//指的是根目录的md文件，也就是 README.md 里面的内容
								'/en/GetStarted/Comparison.md',
								'/en/GetStarted/QA.md'
							]
						},
						{
							title: 'Data Profiling (15)',
							children: [
								'/en/DataProfiling/Cov.md',
								'/en/DataProfiling/Distinct.md',
								'/en/DataProfiling/Histogram.md',
								'/en/DataProfiling/Integral.md',
								'/en/DataProfiling/Mad.md',
                                '/en/DataProfiling/Max.md',
								'/en/DataProfiling/Mean.md',
								'/en/DataProfiling/Median.md',
                                '/en/DataProfiling/Min.md',
								'/en/DataProfiling/Mode.md',
								'/en/DataProfiling/Percentile.md',
								'/en/DataProfiling/Sample.md',
								'/en/DataProfiling/Skew.md',
								'/en/DataProfiling/Spread.md',
								'/en/DataProfiling/Stddev.md',
							],
						},
						{
							title: 'Data Quality (4)',
							children: [
								'/en/DataQuality/Completeness.md',
								'/en/DataQuality/Consistency.md',
								'/en/DataQuality/Timeliness.md',
								'/en/DataQuality/Validity.md',
							],
						},
						{
							title: 'Data Repairing (3)',
							children: [
								'/en/DataRepairing/Fill.md',
								'/en/DataRepairing/TimestampRepair.md',
                                '/en/DataRepairing/ValueRepair.md',								
							],
						},
						{
							title: 'Data Matching (5)',
							children: [
								'/en/DataMatching/DTW.md',
                                '/en/DataMatching/Pearson.md',
                                '/en/DataMatching/SeriesAlign.md',
								'/en/DataMatching/SeriesSimilarity.md',
								'/en/DataMatching/ValueAlign.md',
							],
						},
						{
							title: 'Anomaly Detection (3)',
							children: [
								'/en/AnomalyDetection/KSigma.md',
								'/en/AnomalyDetection/LOF.md',
                                '/en/AnomalyDetection/Range.md',
                            ],
						},
						{
							title: 'Complex Event Processing (7)',
							children: [
								'/en/ComplexEventProcessing/AND.md',
                                '/en/ComplexEventProcessing/EventMatching.md',								
								'/en/ComplexEventProcessing/EventNameRepair.md',
                                '/en/ComplexEventProcessing/EventTag.md',
								'/en/ComplexEventProcessing/EventTimeRepair.md',
								'/en/ComplexEventProcessing/MissingEventRecovery.md',
                                '/en/ComplexEventProcessing/SEQ.md',								
							],
						},
					],
				},
				sidebarDepth: 1,
			},
			'/zh/': {
				selectText: '选择语言',
				label: '简体中文',
				editLinkText: '在 GitHub 上编辑此页',
				// Service Worker 的配置
				serviceWorker: {
					updatePopup: {
						message: '发现新内容可用.',
						buttonText: '刷新',
					},
				},
				// 当前 locale 的 algolia docsearch 选项
				algolia: {},
				nav: [
					{ text: '文档', link: '/zh/' },
					{ text: '下载', link: '/zh/Download.md' },
				],
				sidebar: {
					'/zh/': [
						{
							title: '开始',
							children: [
								'/zh/',
								'GetStarted/Comparison.md',
								'GetStarted/QA.md'
							]
						},
						{
							title: '数据画像 (15)',
							children: [
								'DataProfiling/Cov.md',
								'DataProfiling/Distinct.md',
								'DataProfiling/Histogram.md',
								'DataProfiling/Integral.md',
								'DataProfiling/Mad.md',
                                'DataProfiling/Max.md',
								'DataProfiling/Mean.md',
								'DataProfiling/Median.md',
								'DataProfiling/Min.md',
                                'DataProfiling/Mode.md',								
								'DataProfiling/Percentile.md',
								'DataProfiling/Sample.md',
								'DataProfiling/Skew.md',
								'DataProfiling/Spread.md',
								'DataProfiling/Stddev.md',
							],
						},
						{
							title: '数据质量 (4)',
							children: [
								'DataQuality/Completeness.md',
								'DataQuality/Consistency.md',
								'DataQuality/Timeliness.md',
								'DataQuality/Validity.md',
							],
						},
						{
							title: '数据修复 (3)',
							children: [
								'DataRepairing/Fill.md',								
								'DataRepairing/TimestampRepair.md',
                                'DataRepairing/ValueRepair.md',
                            ],
						},
						{
							title: '数据匹配 (5)',
							children: [
								'DataMatching/DTW.md',
                                'DataMatching/Pearson.md',
                                'DataMatching/SeriesAlign.md',
								'DataMatching/SeriesSimilarity.md',
                                'DataMatching/ValueAlign.md',
							],
						},
						{
							title: '异常检测 (3)',
							children: [
								'AnomalyDetection/KSigma.md',
								'AnomalyDetection/LOF.md',
                                'AnomalyDetection/Range.md',
                            ],
						},
						{
							title: '复杂事件处理 (7)',
							children: [
								'ComplexEventProcessing/AND.md',
                                'ComplexEventProcessing/EventMatching.md',								
								'ComplexEventProcessing/EventNameRepair.md',
                                'ComplexEventProcessing/EventTag.md',
								'ComplexEventProcessing/EventTimeRepair.md',
								'ComplexEventProcessing/MissingEventRecovery.md',
                                'ComplexEventProcessing/SEQ.md',								
							],
						}
					],
				},
				sidebarDepth: 1,
			},
		},
	},
};
