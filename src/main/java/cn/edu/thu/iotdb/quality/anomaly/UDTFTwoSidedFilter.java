package cn.edu.thu.iotdb.quality.anomaly;

import org.apache.iotdb.db.query.udf.api.UDTF;
import org.apache.iotdb.db.query.udf.api.access.RowWindow;
import org.apache.iotdb.db.query.udf.api.collector.PointCollector;
import org.apache.iotdb.db.query.udf.api.customizer.config.UDTFConfigurations;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameterValidator;
import org.apache.iotdb.db.query.udf.api.customizer.parameter.UDFParameters;
import org.apache.iotdb.db.query.udf.api.customizer.strategy.SlidingSizeWindowAccessStrategy;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

public class UDTFTwoSidedFilter implements UDTF {
    private TSDataType dataType;
    private double len;
    private double threshold;
    
	@Override
	public void validate(UDFParameterValidator validator) throws Exception{
		validator.validateInputSeriesNumber(1)
        .validateInputSeriesDataType(0,
                TSDataType.INT32,
                TSDataType.INT64,
                TSDataType.FLOAT,
                TSDataType.DOUBLE);
	}
	
	@Override
	public void beforeStart(UDFParameters udfp, UDTFConfigurations udtfc) throws Exception {
		udtfc.setAccessStrategy(new SlidingSizeWindowAccessStrategy(Integer.MAX_VALUE))
        .setOutputDataType(udfp.getDataType(0));
        this.len = udfp.getDoubleOrDefault("len",5);
        this.threshold = udfp.getDoubleOrDefault("threshold",0.4);
        this.dataType = udfp.getDataType(0);

	}
	
	@Override
	public void transform(RowWindow rowWindow, PointCollector collector) throws Exception{
		WindowDetect wd = new WindowDetect(rowWindow.getRowIterator(),len,threshold);
        double[] repaired = wd.getRepaired();
        long[] time = wd.getTime();
        switch (rowWindow.getDataType(0)) {
            case DOUBLE:
                for (int i = 0; i < time.length; i++) {
                    collector.putDouble(time[i], repaired[i]);
                }
                break;
            case FLOAT:
                for (int i = 0; i < time.length; i++) {
                    collector.putFloat(time[i], (float) repaired[i]);
                }
                break;
            case INT32:
                for (int i = 0; i < time.length; i++) {
                    collector.putInt(time[i], (int) Math.round(repaired[i]));
                }
                break;
            case INT64:
                for (int i = 0; i < time.length; i++) {
                    collector.putLong(time[i], Math.round(repaired[i]));
                }
                break;
            default:
                throw new Exception();
        }
	}
	 
	@Override
	public void terminate(PointCollector collector) throws Exception {}
}
