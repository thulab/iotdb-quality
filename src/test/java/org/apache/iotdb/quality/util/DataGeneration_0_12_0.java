package org.apache.iotdb.quality.util;

import java.io.IOException;

/** For data generation using IoTDB benchmark for 0.12.0. */
public class DataGeneration_0_12_0 {
  public static void generateData(String scriptType) throws IOException, InterruptedException {
    if (scriptType.equalsIgnoreCase("cmd")) {
      Runtime run = Runtime.getRuntime();
      run.exec("src\\resources\\iotdb-0.12-0.0.1\\benchmark.bat");
      Thread.sleep(10000);
      System.out.println("Generation succeeded.");
    } else if (scriptType.equalsIgnoreCase("bash")) {
      Runtime run = Runtime.getRuntime();
      run.exec("sh src\\resources\\iotdb-0.12-0.0.1\\benchmark.sh");
      Thread.sleep(10000);
      System.out.println("Generation succeeded.");
    }
  }
}
