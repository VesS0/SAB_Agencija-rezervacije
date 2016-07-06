/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vlada
 */
public class DB {

    public static final String HOST_NAME = "localhost";
    public static final String INSTANCE_NAME = "VLADAPC";
    public static final int PORT = 1433;
    public static final String DB_NAME = "Rezervacije";
    public static final String USERNAME = "vlada";
    public static final String USERPASS = "root";
    //jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]

    public static String CONNECTION = "jdbc:sqlserver://" + HOST_NAME + "\\" + INSTANCE_NAME + ":" + PORT + ";databaseName = " + DB_NAME;

//      public static String CONNECTION = "jdbc:sqlserver://" + INSTANCE_NAME + ":" + PORT + ";databaseName = " + DB_NAME
//            + ";username = " + USERNAME + ";password = " + USERPASS;
    private Connection connection;

    private static DB dBConnection = null;

    public static synchronized DB getDBInstance() {
        if (dBConnection == null) {
            try {
                
                dBConnection = new DB(CONNECTION);
                System.out.println("Connection opened!");
            } catch (SQLException ex) {
                System.out.println("Connection failed");
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dBConnection;
    }

    private DB(String connStr) throws SQLException {
        connection = DriverManager.getConnection(connStr, USERNAME, USERPASS);
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        System.out.println("Closing db connection...");
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed!");
            } catch (SQLException ex) {
            }
            connection = null;
        }
    }
}
