/*
 Scotia Airlines - HND Computer Science
 Version 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnector {

    static String getConnection;

    private static final SQLConnector Instance = new SQLConnector();

//College DB
    public static final String URL = "comp-hons.uhi.ac.uk/pe10004084";
    public static final String USER = "pe10004084";
    public static final String PASSWORD = "deanreid";
//Server DB
//    public static final String    URL			= "94.23.204.120/xenohost_scotiaairline";
//    public static final String    USER                  = "xenohost_scotiaa";
//    public static final String    PASSWORD		= "O^9Y]Oe]DE*N";
//Home DB
    // public static final String URL = "localhost/scotiaairlines";
    // public static final String USER = "root";
    // public static final String PASSWORD = "";

    //Load Driver
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    private SQLConnector() {
        try {
            System.out.println("=======================");
            System.out.println("Connecting to SQL database: " + URL);
            // Load MySQL driver
            Class.forName(DRIVER_CLASS);
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName(DRIVER_CLASS).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            log("Check classpath. Cannot load db driver: " + DRIVER_CLASS);
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + URL, USER, PASSWORD);
        } catch (SQLException e) {

            log("Driver loaded, but cannot connect to db: " + URL);
            e.printStackTrace();
        }
        return connection;
    }

    private static void log(Object aObject) {
        System.out.println(aObject);
    }

    public static Connection getConnection() throws SQLException {
        return Instance.createConnection();
    }
}
