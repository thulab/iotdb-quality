module.exports = {
	title: 'IoTDB-Quality', // 设置网站标题
	description:
		"Apache IoTDB's UDFs about data quality",
	base: '/iotdb-quality/',
	head: [
		['script', { async: "async", src: 'https://www.googletagmanager.com/gtag/js?id=G-V8CG1Y9FW6' }],
		['script', { type: 'text/javascript', src: '/assets/push.js' }],//用于Google Analytics网站访问分析
	],
	locales: {
		'/en/': {
			lang: 'en-US',
		},
		'/zh/': {
			lang: 'zh-CN',
		},
	},
	plugins: [
		[
			// 支持数学公式
			// https://github.com/vuepress/vuepress-plugin-mathjax
			'vuepress-plugin-mathjax',
			{}
		],		
	],
	themeConfig: {
		locales: {
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
					{
						text: '文档',
						items: [
							{ text: 'V2.0.x', link: '/zh/V2.0.x/' },
							{ text: 'V1.0.x', link: '/zh/V1.0.x/' },
						]
					},
					{ text: '下载', link: '/zh/Download.md' },
					 { text: 'Demo', link: '/zh/V1.0.x/Demo.md' },
				],
				sidebar: {
					'/zh/V2.0.x/': [
						{
							title: '用户文档 (V2.0.x)',
							collapsable: false,
						},
						{
							title: '开始',
							children: [
								'',
								'GetStarted/Comparison.md',
								'GetStarted/QA.md'
							]
						},
						{
							title: '数据画像 (22)',
							children: [
								'DataProfiling/ACF.md',
								'DataProfiling/Distinct.md',
								'DataProfiling/Histogram.md',
								'DataProfiling/Integral.md',
								'DataProfiling/IntegralAvg.md',
								'DataProfiling/Mad.md',
								'DataProfiling/Median.md',
                                'DataProfiling/MinMax.md',
								'DataProfiling/Mode.md',
                                'DataProfiling/MvAvg.md',
                                'DataProfiling/PACF.md',
								'DataProfiling/Percentile.md',
								'DataProfiling/Period.md',
								'DataProfiling/QLB.md',
                                'DataProfiling/Resample.md',
								'DataProfiling/Sample.md',
								'DataProfiling/Segment.md',	
								'DataProfiling/Skew.md',
                                'DataProfiling/Spline.md',
								'DataProfiling/Spread.md',
								'DataProfiling/Stddev.md',
                                'DataProfiling/ZScore.md',
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
								'DataRepairing/ValueFill.md',
								'DataRepairing/TimestampRepair.md',
								'DataRepairing/ValueRepair.md',
							],
						},
						{
							title: '数据匹配 (8)',
							children: [
                                'DataMatching/Cov.md',
								'DataMatching/DTW.md',
                                'DataMatching/PtnSym.md',
								'DataMatching/Pearson.md',
								'DataMatching/SeriesAlign.md',
								'DataMatching/SeriesSimilarity.md',
								'DataMatching/ValueAlign.md',
                                'DataMatching/XCorr.md',
							],
						},
						{
							title: '异常检测 (6)',
							children: [
                                'AnomalyDetection/IQR.md',
								'AnomalyDetection/KSigma.md',
								'AnomalyDetection/LOF.md',
								'AnomalyDetection/MissDetect.md',
								'AnomalyDetection/Range.md',
                                'AnomalyDetection/TwoSidedFilter.md'
							],
						},
						{
							title: '频域相关 (7)',
							children: [
								'Frequency/Conv.md',
								'Frequency/Deconv.md',
								'Frequency/DWT.md',
								'Frequency/FFT.md',
								'Frequency/HighPass.md',
								'Frequency/IFFT.md',
                                'Frequency/LowPass.md',
							],
						},
						{
							title: '序列发现 (2)',
							children: [
								'SeriesDiscovery/ConsecutiveSequences.md',
								'SeriesDiscovery/ConsecutiveWindows.md',
							],
						},
						{
							title: '字符串处理 (4)',
							children: [
								'StringProcessing/RegexMatch.md',
								'StringProcessing/RegexReplace.md',
								'StringProcessing/RegexSplit.md',
								'StringProcessing/StrReplace.md',
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
						},
					],
					'/zh/V1.0.x/': [
						{
							title: '用户文档 (V1.0.x)',
							collapsable: false,
						},
						{
							title: '开始',
							children: [
								'',
								'GetStarted/Comparison.md',
								'GetStarted/QA.md'
							]
						},
						{
							title: '数据画像 (21)',
							children: [
								'DataProfiling/Distinct.md',
								'DataProfiling/Histogram.md',
								'DataProfiling/Integral.md',
								'DataProfiling/Mad.md',
								'DataProfiling/Median.md',
                                'DataProfiling/MinMax.md',
								'DataProfiling/Mode.md',
                                'DataProfiling/MovingAverage.md',
                                'DataProfiling/PACF.md',
								'DataProfiling/Percentile.md',
								'DataProfiling/Period.md',
								'DataProfiling/QLB.md',
                                'DataProfiling/Resample.md',
								'DataProfiling/Sample.md',
								'DataProfiling/Segment.md',	
								'DataProfiling/Skew.md',
                                'DataProfiling/Spline.md',
								'DataProfiling/Spread.md',
								'DataProfiling/Stddev.md',
                                'DataProfiling/TimeWeightedAvg.md',
                                'DataProfiling/ZScore.md',
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
								'DataRepairing/ValueFill.md',
								'DataRepairing/TimestampRepair.md',
								'DataRepairing/ValueRepair.md',
							],
						},
						{
							title: '数据匹配 (9)',
							children: [                                
								'DataMatching/Cov.md',
                                'DataMatching/CrossCorrelation.md',
								'DataMatching/DTW.md',
                                'DataMatching/PatternSymmetric.md',
								'DataMatching/Pearson.md',
                                'DataMatching/SelfCorrelation.md',
								'DataMatching/SeriesAlign.md',
								'DataMatching/SeriesSimilarity.md',
								'DataMatching/ValueAlign.md',
							],
						},
						{
							title: '异常检测 (6)',
							children: [
								'AnomalyDetection/ADWIN.md',
                                'AnomalyDetection/IQR.md',
								'AnomalyDetection/KSigma.md',
								'AnomalyDetection/LOF.md',
								'AnomalyDetection/Range.md',
                                'AnomalyDetection/TwoSidedFilter.md'
							],
						},
						{
							title: '频域相关 (7)',
							children: [
								'Frequency/Conv.md',
								'Frequency/Deconv.md',
								'Frequency/DWT.md',
								'Frequency/FFT.md',
								'Frequency/HighPass.md',
								'Frequency/IFFT.md',
                                'Frequency/LowPass.md',
							],
						},
						{
							title: '序列发现 (2)',
							children: [
								'SeriesDiscovery/ConsecutiveSequences.md',
								'SeriesDiscovery/ConsecutiveWindows.md',
							],
						},
						{
							title: '字符串处理 (4)',
							children: [
								'StringProcessing/RegexMatch.md',
								'StringProcessing/RegexReplace.md',
								'StringProcessing/Replace.md',
								'StringProcessing/Split.md',
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
			'/en/': {
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
					{
						text: 'Document',
						items: [
							{ text: 'V1.0.x', link: '/en/V1.0.x/' },
							{ text: 'V2.0.x', link: '/en/V2.0.x/' },
						]
					},
					{ text: 'Download', link: '/en/Download.md' },
					 { text: 'Demo', link: '/en/V1.0.x/Demo.md' },
				],
				sidebar: {
					'/en/V2.0.x/': [
						{
							title: 'User Guide (V2.0.x)',
							collapsable: false,
						},
						{
							title: 'Get Started',
							children: [
								'',
								'GetStarted/Comparison.md',
								'GetStarted/QA.md'
							]
						},
						{
							title: 'Data Profiling (22)',
							children: [
								'DataProfiling/ACF.md',
								'DataProfiling/Distinct.md',
								'DataProfiling/Histogram.md',
								'DataProfiling/Integral.md',
								'DataProfiling/IntegralAvg.md',
								'DataProfiling/Mad.md',
								'DataProfiling/Median.md',
                                'DataProfiling/MinMax.md',
								'DataProfiling/Mode.md',
                                'DataProfiling/MvAvg.md',
                                'DataProfiling/PACF.md',
								'DataProfiling/Percentile.md',
								'DataProfiling/Period.md',
								'DataProfiling/QLB.md',
                                'DataProfiling/Resample.md',
								'DataProfiling/Sample.md',
								'DataProfiling/Segment.md',	
								'DataProfiling/Skew.md',
                                'DataProfiling/Spline.md',
								'DataProfiling/Spread.md',
								'DataProfiling/Stddev.md',
                                'DataProfiling/ZScore.md',
							],
						},
						{
							title: 'Data Quality (4)',
							children: [
								'DataQuality/Completeness.md',
								'DataQuality/Consistency.md',
								'DataQuality/Timeliness.md',
								'DataQuality/Validity.md',
							],
						},
						{
							title: 'Data Repairing (3)',
							children: [
								'DataRepairing/ValueFill.md',
								'DataRepairing/TimestampRepair.md',
								'DataRepairing/ValueRepair.md',
							],
						},
						{
							title: 'Data Matching (8)',
							children: [
                                'DataMatching/Cov.md',
								'DataMatching/DTW.md',
                                'DataMatching/PtnSym.md',
								'DataMatching/Pearson.md',
								'DataMatching/SeriesAlign.md',
								'DataMatching/SeriesSimilarity.md',
								'DataMatching/ValueAlign.md',
                                'DataMatching/XCorr.md',
							],
						},
						{
							title: 'Anomaly Detection (6)',
							children: [
                                'AnomalyDetection/IQR.md',
								'AnomalyDetection/KSigma.md',
								'AnomalyDetection/LOF.md',
								'AnomalyDetection/MissDetect.md',
								'AnomalyDetection/Range.md',
                                'AnomalyDetection/TwoSidedFilter.md'
							],
						},
						{
							title: 'Frequency Domain (7)',
							children: [
								'Frequency/Conv.md',
								'Frequency/Deconv.md',
								'Frequency/DWT.md',
								'Frequency/FFT.md',
								'Frequency/HighPass.md',
								'Frequency/IFFT.md',
                                'Frequency/LowPass.md',
							],
						},
						{
							title: 'Series Discovery (2)',
							children: [
								'SeriesDiscovery/ConsecutiveSequences.md',
								'SeriesDiscovery/ConsecutiveWindows.md',
							],
						},
						{
							title: 'String Processing (4)',
							children: [
								'StringProcessing/RegexMatch.md',
								'StringProcessing/RegexReplace.md',
								'StringProcessing/RegexSplit.md',
								'StringProcessing/StrReplace.md',
							],
						},
						{
							title: 'Complex Event Processing (7)',
							children: [
								'ComplexEventProcessing/AND.md',
								'ComplexEventProcessing/EventMatching.md',
								'ComplexEventProcessing/EventNameRepair.md',
								'ComplexEventProcessing/EventTag.md',
								'ComplexEventProcessing/EventTimeRepair.md',
								'ComplexEventProcessing/MissingEventRecovery.md',
								'ComplexEventProcessing/SEQ.md',
							],
						},
					],
					'/en/V1.0.x/': [
						{
							title: 'User Guide (V1.0.x)',
							collapsable: false,
						},
						{
							title: 'Get Started',
							children: [
								'',//指的是根目录的md文件，也就是 README.md 里面的内容
								'GetStarted/Comparison.md',
								'GetStarted/QA.md'
							]
						},
						{
							title: 'Data Profiling (21)',
							children: [
								'DataProfiling/Distinct.md',
								'DataProfiling/Histogram.md',
								'DataProfiling/Integral.md',
								'DataProfiling/Mad.md',
								'DataProfiling/Median.md',
                                'DataProfiling/MinMax.md',
								'DataProfiling/Mode.md',
                                'DataProfiling/MovingAverage.md',
                                'DataProfiling/PACF.md',
								'DataProfiling/Percentile.md',
								'DataProfiling/Period.md',
								'DataProfiling/QLB.md',
                                'DataProfiling/Resample.md',
								'DataProfiling/Sample.md',
								'DataProfiling/Segment.md',	
								'DataProfiling/Skew.md',
                                'DataProfiling/Spline.md',
								'DataProfiling/Spread.md',
								'DataProfiling/Stddev.md',
                                'DataProfiling/TimeWeightedAvg.md',
                                'DataProfiling/ZScore.md',
							],
						},
						{
							title: 'Data Quality (4)',
							children: [
								'DataQuality/Completeness.md',
								'DataQuality/Consistency.md',
								'DataQuality/Timeliness.md',
								'DataQuality/Validity.md',
							],
						},
						{
							title: 'Data Repairing (3)',
							children: [
								'DataRepairing/ValueFill.md',
								'DataRepairing/TimestampRepair.md',
								'DataRepairing/ValueRepair.md',
							],
						},
						{
							title: 'Data Matching (9)',
							children: [
                                'DataMatching/Cov.md',
                                'DataMatching/CrossCorrelation.md',
								'DataMatching/DTW.md',
                                'DataMatching/PatternSymmetric.md',
								'DataMatching/Pearson.md',
                                'DataMatching/SelfCorrelation.md',
								'DataMatching/SeriesAlign.md',
								'DataMatching/SeriesSimilarity.md',
								'DataMatching/ValueAlign.md',
							],
						},
						{
							title: 'Anomaly Detection (6)',
							children: [
								'AnomalyDetection/ADWIN.md',
                                'AnomalyDetection/IQR.md',
								'AnomalyDetection/KSigma.md',
								'AnomalyDetection/LOF.md',
								'AnomalyDetection/Range.md',
                                'AnomalyDetection/TwoSidedFilter.md'
							],
						},
						{
							title: 'Frequency Domain (7)',
							children: [
								'Frequency/Conv.md',
								'Frequency/Deconv.md',
								'Frequency/DWT.md',
								'Frequency/FFT.md',
								'Frequency/HighPass.md',
								'Frequency/IFFT.md',
                                'Frequency/LowPass.md',
							],
						},
						{
							title: 'Series Discovery (2)',
							children: [
								'SeriesDiscovery/ConsecutiveSequences.md',
								'SeriesDiscovery/ConsecutiveWindows.md',
							],
						},
						{
							title: 'String Processing (4)',
							children: [
								'StringProcessing/RegexMatch.md',
								'StringProcessing/RegexReplace.md',
								'StringProcessing/Replace.md',
								'StringProcessing/Split.md',
							],
						},
						{
							title: 'Complex Event Processing (7)',
							children: [
								'ComplexEventProcessing/AND.md',
								'ComplexEventProcessing/EventMatching.md',
								'ComplexEventProcessing/EventNameRepair.md',
								'ComplexEventProcessing/EventTag.md',
								'ComplexEventProcessing/EventTimeRepair.md',
								'ComplexEventProcessing/MissingEventRecovery.md',
								'ComplexEventProcessing/SEQ.md',
							],
						},
					],
				},
				sidebarDepth: 1,
			},
		},
	},
};
