package cn.edu.thu.dquality;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.iotdb.jdbc.IoTDBSQLException;

import java.sql.*;
import java.util.Date;

public class DataInsertTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        if (connection == null) {
            System.out.println("get connection defeat");
            return;
        }
        Statement statement = connection.createStatement();
        //Create storage group
        try {
            statement.execute("SET STORAGE GROUP TO root.test");
        }catch (IoTDBSQLException e){
            System.out.println(e.getMessage());
        }

        //Show storage group
        statement.execute("SHOW STORAGE GROUP");
        outputResult(statement.getResultSet());

        //Create time series
        //Different data type has different encoding methods. Here use INT32 as an example
//        statement.execute("CREATE TIMESERIES root.test.s0 WITH DATATYPE=DOUBLE,ENCODING=RLE;");

        //Execute insert statements in batch
        long timestamp = new Date().getTime();
        NormalDistribution distribution = new NormalDistribution(1,1);
        for(int i = 0; i < 10000; ++i){
            statement.addBatch("insert into root.test(timestamp,s0) values(" + (timestamp + i * 1000) + "," + distribution.sample() + ");");
        }
        statement.executeBatch();
        statement.clearBatch();

        //Full query statement
        String sql = "select s0 from root.test";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("sql: " + sql);
        outputResult(resultSet);

        //Delete time series
//        statement.execute("delete timeseries root.test.s0");

        //close connection
        statement.close();
        connection.close();
    }

    public static Connection getConnection() {
        String driver = "org.apache.iotdb.jdbc.IoTDBDriver";
        String url = "jdbc:iotdb://127.0.0.1:6667/";

        // Database credentials
        String username = "root";
        String password = "root";

        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * This is an example of outputting the results in the ResultSet
     */
    private static void outputResult(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            System.out.println("--------------------------");
            final ResultSetMetaData metaData = resultSet.getMetaData();
            final int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                System.out.print(metaData.getColumnLabel(i + 1) + " ");
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; ; i++) {
                    System.out.print(resultSet.getString(i));
                    if (i < columnCount) {
                        System.out.print(", ");
                    } else {
                        System.out.println();
                        break;
                    }
                }
            }
            System.out.println("--------------------------\n");
        }
    }
}
