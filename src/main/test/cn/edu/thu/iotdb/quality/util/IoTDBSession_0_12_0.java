package cn.edu.thu.iotdb.quality.util;

import org.apache.iotdb.session.Session;

// IoTDB 0.12.0 session interface
public class IoTDBSession_0_12_0 {
    Session session;
    public IoTDBSession_0_12_0(String host, int rpcPort){
        session = new Session(host, rpcPort);
    }

    public IoTDBSession_0_12_0(String host, String rpcPort, String username, String password){
        session = new Session(host, rpcPort, username, password);
    }

    public IoTDBSession_0_12_0(String host, int rpcPort, String username, String password){
        session = new Session(host, rpcPort, username, password);
    }


}
