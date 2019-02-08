/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.7
 Code Version: 1.5
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.network;

import couk.deanreid.scotiaairlines.utils.Reference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBProxy {

    static String getConnection;

    private static final DBProxy SQLI = new DBProxy();

    //Load Driver
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    private DBProxy() {
        try {
            if (Reference.DEBUG_MODE) {
                System.out.println("=======================");
                System.out.println("Connecting to SQL database: " + Reference.DB_FULLURL);
            }
            // Load MySQL driver
            Class.forName(DRIVER_CLASS);
        } catch (final ClassNotFoundException e) {
            //e.printStackTrace();
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
            connection = DriverManager.getConnection("jdbc:mysql://" + Reference.DB_FULLURL, Reference.DB_USER, Reference.DB_PASSWORD);
        } catch (SQLException e) {

            log("Driver loaded, but cannot connect to db: " + Reference.DB_FULLURL);
            //e.printStackTrace();
        }
        return connection;
    }

    private static void log(Object aObject) {
        System.out.println(aObject);
    }

    public static Connection getConnection() throws SQLException {
        return SQLI.createConnection();
    }
}
