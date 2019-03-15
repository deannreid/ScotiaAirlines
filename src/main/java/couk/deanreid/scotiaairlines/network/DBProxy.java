/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 1.5
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.network;

import couk.deanreid.scotiaairlines.utils.LogHelper;
import couk.deanreid.scotiaairlines.utils.Reference;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBProxy {
    
    static String getConnection;
    
    private static final DBProxy SQLI = new DBProxy();

    //Load Driver
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    
    private DBProxy() {
        try {
            LogHelper.debug("=======================");
            LogHelper.debug("Connecting to SQL database: " + Reference.DB_FULLURL);
            
            Class.forName(DRIVER_CLASS);
        } catch (final ClassNotFoundException e) {
            LogHelper.fatal(e);
        }
    }
    
    private Connection createConnection() throws SQLException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        Connection connection = null;
            try {
            Class.forName(DRIVER_CLASS).getConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            LogHelper.fatal("Check classpath. Cannot load db driver: " + DRIVER_CLASS);
            LogHelper.fatal(ex);
        }
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + Reference.DB_FULLURL, Reference.DB_USER, Reference.DB_PASSWORD);
        } catch (SQLException e) {
            LogHelper.warn(e);
            LogHelper.warn("Driver loaded, but cannot connect to db: " + Reference.DB_FULLURL);
        }
        return connection;
    }
    
    public static Connection getConnection() throws SQLException {
        try {
            return SQLI.createConnection();
        } catch (NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
            LogHelper.fatal(ex);
        }
        return null;
    }
}
