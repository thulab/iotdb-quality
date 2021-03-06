module.exports = {
	title: 'IoTDB-Quality', // 设置网站标题
	description:
		"Apache IoTDB's UDFs about data quality",
	base: '/tsclean-iotdb/',
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
						'/', //指的是根目录的md文件，也就是 README.md 里面的内容
                        {
							title: 'Data Profiling (13)',
							children: [
								'/en/DataProfiling/Cov.md',
								'/en/DataProfiling/Distinct.md',
								'/en/DataProfiling/Histogram.md',
								'/en/DataProfiling/Integral.md',
								'/en/DataProfiling/Mean.md',
								'/en/DataProfiling/Median.md',
								'/en/DataProfiling/Mode.md',
								'/en/DataProfiling/Pearson.md',
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
								'/en/DataRepairing/ValueRepair.md',
								'/en/DataRepairing/TimestampRepair.md',
							],
						},
						{
							title: 'Data Matching (3)',
							children: [
								'/en/DataMatching/SeriesAlign.md',
								'/en/DataMatching/SeriesSimilarity.md',
								'/en/DataMatching/DTW.md',
							],
						},
						{
							title: 'Anomaly Detection (3)',
							children: [
								'/en/AnomalyDetection/Range.md',
								'/en/AnomalyDetection/KSigma.md',
								'/en/AnomalyDetection/LOF.md',
							],
						},
						{
							title: 'Complex Event Processing (6)',
							children: [
								'/en/ComplexEventProcessing/EventMatching.md',
								'/en/ComplexEventProcessing/MissingEventRecovery.md',
								'/en/ComplexEventProcessing/EventNameRepair.md',
								'/en/ComplexEventProcessing/EventTimeRepair.md',
                                '/en/ComplexEventProcessing/SEQ.md',
                                '/en/ComplexEventProcessing/AND.md',
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
						'/zh/',
						{
							title: '数据画像 (13)',
							children: [
								'DataProfiling/Cov.md',
								'DataProfiling/Distinct.md',
								'DataProfiling/Histogram.md',
								'DataProfiling/Integral.md',
								'DataProfiling/Mean.md',
								'DataProfiling/Median.md',
								'DataProfiling/Mode.md',
								'DataProfiling/Pearson.md',
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
								'DataRepairing/ValueRepair.md',
								'DataRepairing/TimestampRepair.md',
							],
						},
						{
							title: '数据匹配 (3)',
							children: [
								'DataMatching/SeriesAlign.md',
								'DataMatching/SeriesSimilarity.md',
								'DataMatching/DTW.md',
							],
						},
						{
							title: '异常检测 (3)',
							children: [
								'AnomalyDetection/Range.md',
								'AnomalyDetection/KSigma.md',
								'AnomalyDetection/LOF.md',
							],
						},
						{
							title: '复杂事件处理 (6)',
							children: [
								'ComplexEventProcessing/EventMatching.md',
								'ComplexEventProcessing/MissingEventRecovery.md',
								'ComplexEventProcessing/EventNameRepair.md',
								'ComplexEventProcessing/EventTimeRepair.md',
                                'ComplexEventProcessing/SEQ.md',
                                'ComplexEventProcessing/AND.md',
							],
						}
					],
				},
				sidebarDepth: 1,
			},
		},
	},
};
