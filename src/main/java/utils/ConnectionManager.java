package utils;

import reporting.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {

    private Map<String, Connection> connections = new HashMap<>();

    private ConnectionManager() {
    }

    private static ConnectionManager instance;

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public void closeAllConnections() {
        Logger.debug("Closing connections");
        for (Map.Entry<String, Connection> entry : connections.entrySet()) {
            Connection connection = entry.getValue();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connections.clear();
    }

    private void initConnection(String dbName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Logger.debug("Connecting to " + dbName);
        Connection connection = null;
        String user = "jowel";
        String password = "wg1-Yx_2dhsa347z~hdYn-ahn7";
        String url = "jdbc:mysql://build-a-list-dev-and-stage.cveysws4sctc.us-east-1.rds.amazonaws.com";
        try {

            connection = DriverManager.getConnection(url, user, password);
            Logger.debug("Connection to " + url + " created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connections.put(dbName, connection);
    }

    public Connection getConnection(String dbName) {
        if (connections.get(dbName) == null) initConnection(dbName);
        return connections.get(dbName);
    }
}
