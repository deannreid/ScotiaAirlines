/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 1.1
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.network;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLCreateTB {

    /**
     *
     * @depricated - Not Needed
     */
    public SQLCreateTB () throws SQLException {
        System.out.println ("Creating table in given database...");
        Connection connection = DBProxy.getConnection ();
        Statement stmt = connection.createStatement ();
        String flight = "CREATE TABLE IF NOT EXISTS FLIGHT "
                + "(FlightID VARCHAR(255) not NULL, "
                + " Departure VARCHAR(255), "
                + " Arrival VARCHAR(255), "
                + " Rows INTEGER, "
                + " Columns INTEGER, "
                + " PRIMARY KEY ( FlightID ))";
        String passenger = "CREATE TABLE IF NOT EXISTS PASSENGER "
                + "(SeatNo INTEGER not NULL, "
                + " PassengerName VARCHAR(255), "
                + " Type VARCHAR(255), "
                + " Information VARCHAR(255), "
                + "(FlightID VARCHAR(255) not NULL, "
                + " PRIMARY KEY ( SeatNo ))";
        String seat = "CREATE TABLE IF NOT EXISTS REGISTRATION "
                + "(SeatNO INTEGER not NULL, "
                + " Status VARCHAR(255), "
                + " Takings INTEGER, "
                + " FlightID VARCHAR(255), "
                + " PRIMARY KEY ( SeatNO ))";
        stmt.executeUpdate (flight
                + passenger
                + seat);
        System.out.println ("Created table in given database...");
    }

}
