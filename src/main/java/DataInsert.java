import org.apache.iotdb.jdbc.IoTDBSQLException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DataInsert {
    public static void main(String[] args) throws SQLException, IOException {
        try (Connection connection = getConnection()) {
            if (connection == null) {
                return;
            }
            Statement statement = connection.createStatement();

//            statement.execute("delete timeseries root.test.s2");

            try {
                statement.execute("CREATE TIMESERIES root.test.s5 WITH DATATYPE=DOUBLE,ENCODING=RLE;");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            BufferedReader br = new BufferedReader(new FileReader("E:/dataset/norm0.csv"));
            br.readLine();
            long index = 0;
            String line;
            while((line = br.readLine()) != null){
                statement.addBatch("insert into root.test(timestamp,s5) values(" + index + "," + line + ");");
                index++;
                if(index % 100000 == 0){
                    System.out.println(index);
                    statement.executeBatch();
                    statement.clearBatch();
                    if(index == 100000){
                        break;
                    }
                }
            }

            //Delete time series
//            statement.execute("delete timeseries root.demo.s0");

            //close connection
            statement.close();
        }
    }

    public static Connection getConnection() {
        // JDBC driver name and database URL
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
