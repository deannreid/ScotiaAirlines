/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.core;

import couk.deanreid.scotiaairlines.network.DBProxy;
import couk.deanreid.scotiaairlines.ui.UI;
import couk.deanreid.scotiaairlines.utils.LogHelper;
import couk.deanreid.scotiaairlines.utils.Reference;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Airline {

    //attributes
    private final HashMap<String, Flight> flights;

    /**
     Getter for Flight Hashmap

     @return
     */
    public HashMap<String, Flight> getFlights() {
        return flights;
    }

    /**
     Constructor
     */
    public Airline() {
        flights = new HashMap<>();
    }

    /**
     Getter for Flights

     @param flightNo

     @return
     */
    public Flight getFlights(String flightNo) {

        if (flights.containsKey(flightNo)) {
            return flights.get(flightNo);
        } else {
            Flight aNewFlight = new Flight(0, 0);
            flights.put(aNewFlight.getFlightNumber(), aNewFlight);
            return aNewFlight;
        }

    }

    /**
     Add Flights to Hashmap

     @param aFlight
     */
    public void addFlight(Flight aFlight) {
        flights.put(aFlight.getFlightNumber(), aFlight);
    }

    /**
     Loads all Flights from the Database and inserts into Hashmap

     @throws SQLException
     */
    @SuppressWarnings("CallToPrintStackTrace")
    public void loadFlightsFromDB() throws SQLException {
        try {
            Connection connection = DBProxy.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Flight");
            LogHelper.debug(Reference.TextPaint.GREEN + "=======================" + Reference.TextPaint.RESET);
            LogHelper.debug("Loading Flight Database");
            LogHelper.debug(Reference.TextPaint.GREEN + "=======================" + Reference.TextPaint.RESET);
            while (rs.next()) {
                String flightNo = rs.getString(1);
                String departure = rs.getString(2);
                String arrival = rs.getString(3);
                int rows = rs.getInt(4);
                int columns = rs.getInt(5);
                String date = rs.getString(6);

                Flight aNewFlight = new Flight(flightNo, departure, arrival, rows, columns, date);
                addFlight(aNewFlight);
                Flight Flight = aNewFlight;
                LogHelper.debug(Reference.TextPaint.BLUE + "Flight: '" + flightNo + "' Loaded from Database" + Reference.TextPaint.RESET);

            }
            LogHelper.info("Flights Imported");
        } catch (SQLException e) {
            LogHelper.error(Reference.TextPaint.RED + "Flights Failed to Load from Database" + Reference.TextPaint.RESET);
            LogHelper.error(e);
        }
    }

    /**
     Load Seats from Database

     @throws SQLException
     */
    public void loadSeatsFromDB() throws SQLException {
        try {
            System.out.println("");
            LogHelper.debug(Reference.TextPaint.GREEN + "===================================" + Reference.TextPaint.RESET);
            LogHelper.debug("Loading Seat and Passenger Database");
            LogHelper.debug(Reference.TextPaint.GREEN + "===================================" + Reference.TextPaint.RESET);
            Connection connection = DBProxy.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Seat");
            while (rs.next()) {
                String SeatNo = rs.getString(1);
                int status = rs.getInt(2);
                int takings = rs.getInt(3);
                String FlightNo = rs.getString(4);

                Seat passengerSeat = getSeat(FlightNo, SeatNo);
                passengerSeat.setSeatTakings(takings);
                loadPassengersFromDB(FlightNo, passengerSeat, status, takings);
                LogHelper.debug(Reference.TextPaint.BLUE + "Seat " + SeatNo + "' on flight: '" + FlightNo + "' Loaded from Database" + Reference.TextPaint.RESET);

            }
            LogHelper.info("Seats Imported");
        } catch (SQLException e) {
            LogHelper.error(Reference.TextPaint.RED + "Seats Failed to Load from database" + Reference.TextPaint.RESET);
            LogHelper.error(e);
        }
    }

    /**
     Loads Passengers from the Database

     @param flightNo
     @param passengerSeat
     @param status
     @param takings
     */
    public void loadPassengersFromDB(String flightNo, Seat passengerSeat, int status, float takings) {
        try {
            Connection connection = DBProxy.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Passenger");
            while (rs.next()) {
                String seatNo = rs.getString(1);
                String passengerName = rs.getString(2);
                char passengerType = rs.getString(3).charAt(0);
                String passengerInfo = rs.getString(4);
                String flightNumber = rs.getString(5);
                if (seatNo.equalsIgnoreCase(passengerSeat.getSeatNumber()) && flightNumber.equalsIgnoreCase(flightNo)) {
                    Flight passengersFlight = flights.get(flightNumber);
                    int choice = passengerSeat.changeSeatStatus(status, takings, passengerName, passengerType, passengerInfo);
                    passengersFlight.updateSeat(choice);
                    passengersFlight.CalculateTotalFlightTakings();
                    LogHelper.debug(Reference.TextPaint.BLUE + "Passenger: '" + passengerName + "' on flight: '" + flightNumber + "' Loaded from database" + Reference.TextPaint.RESET);
                }
            }
            LogHelper.info("Passengers Imported");
        } catch (SQLException e) {
            LogHelper.error(Reference.TextPaint.RED + "Passengers Failed to Load from database" + Reference.TextPaint.RESET);
            LogHelper.error(e);
        }
    }

    /**
     Empties the Database of all entities
     so that new values can be written
     */
    public void EmptyDB() {
        UI ui = new UI(this);
        PreparedStatement preparedStatement;// = null;
        String deleteSeat = "DELETE FROM Seat";
        String deletePassenger = "DELETE FROM Passenger";
        try {
            LogHelper.warn(Reference.TextPaint.BLUE + "DATABASE DEBUG: Cleaning DB" + Reference.TextPaint.RESET);
            Connection connection = DBProxy.getConnection();
            preparedStatement = connection.prepareStatement(deleteSeat);
            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                LogHelper.warn(Reference.TextPaint.BLUE + "SEAT DEBUG: Seat Tables Emptied" + Reference.TextPaint.RESET);
            }
        } catch (SQLException e) {
            LogHelper.error(Reference.TextPaint.RED + "SEAT ERROR: Seat table cannot be emptied" + Reference.TextPaint.RESET);
            LogHelper.error(e);
        }
        try {
            Connection connection = DBProxy.getConnection();
            preparedStatement = connection.prepareStatement(deletePassenger);
            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                LogHelper.warn(Reference.TextPaint.BLUE + "PASSENGER DEBUG: Passengers Emptied" + Reference.TextPaint.RESET);
            }
        } catch (SQLException e) {
            LogHelper.error(Reference.TextPaint.RED + "PASSENGER ERROR: Passenger table cannot be emptied" + Reference.TextPaint.RESET);
            LogHelper.error(e);
        }
    }

    /**
     Saves Seat information to Database
     */
    public void SaveSeatsToDB() {
        try {
            //for each flight
            for (Map.Entry<String, Flight> currentFlight : flights.entrySet()) {
                //for each seat in current flight
                for (Map.Entry<String, Seat> currentSeat : currentFlight.getValue().getSeats().entrySet()) {
                    //get values for current seat
                    String seatNo = currentSeat.getValue().getSeatNumber();
                    String seatTakings = String.valueOf(currentSeat.getValue().getSeatTakings());
                    String seatStatus = String.valueOf(currentSeat.getValue().getCurrentStatus());
                    String flightNo = currentFlight.getValue().getFlightNumber();

                    LogHelper.debug("SEAT DEBUG: Connecting and Preparing Statement");

                    Connection connection = DBProxy.getConnection();
                    Statement stmt = connection.createStatement();

                    LogHelper.debug("SEAT DEBUG: Execute Statement: " + stmt);

                    int rs = stmt.executeUpdate("INSERT INTO Seat (SeatNo, Status, Takings, FlightID) VALUES('" + seatNo + "','" + seatStatus + "','" + seatTakings + "','" + flightNo + "')");

                    if (rs > 0) {
                        LogHelper.info("SEAT DEBUG:" + Reference.TextPaint.BLUE + "Seat: " + seatNo + " booked on flight: " + flightNo + " by passenger: " + currentSeat.getValue().getaPassenger().getPassengerName() + Reference.TextPaint.RESET);
                        LogHelper.debug(rs);
                    }
                }
            }
        } catch (SQLException e) {
            LogHelper.error(Reference.TextPaint.RED + "SEAT ERROR: Seats failed to save to DB" + Reference.TextPaint.RESET);
            LogHelper.error(e);
        }
    }

    /**
     Saves Passenger information to Database
     */
    public void SavePassengersToDB() {
        UI ui = new UI(this);
        try {
            char type = ' ';
            String info = "";
            //for each flight
            for (Map.Entry<String, Flight> currentFlight : flights.entrySet()) {
                //for each seat in the current flight from previous loop
                for (Map.Entry<String, Seat> currentSeat : currentFlight.getValue().getSeats().entrySet()) {
                    //if statement to get passenger details
                    if (currentSeat.getValue().getaPassenger() != null) {
                        if (currentSeat.getValue().getaPassenger().getClass().isInstance(new PassengerBusiness())) {
                            type = 'B';
                            PassengerBusiness bp = (PassengerBusiness) currentSeat.getValue().getaPassenger();
                            info = bp.getCompanyName();
                        } else if (currentSeat.getValue().getaPassenger().getClass().isInstance(new PassengerWestern())) {
                            type = 'I';
                            PassengerWestern ip = (PassengerWestern) currentSeat.getValue().getaPassenger();
                            info = ip.getIslandOfResidence();
                        } else if (currentSeat.getValue().getaPassenger().getClass().isInstance(new PassengerOrdinary())) {
                            type = 'O';
                            PassengerOrdinary op = (PassengerOrdinary) currentSeat.getValue().getaPassenger();
                            info = String.valueOf(op.getCurrentPromotion());
                        }

                        String passengerType = String.valueOf(type);
                        String passengerInfo = info;
                        String flightNo = currentFlight.getValue().getFlightNumber();

                        LogHelper.debug("SEAT DEBUG: Connecting and Preparing Statement");

                        Connection connection = DBProxy.getConnection();
                        Statement stmt = connection.createStatement();

                        LogHelper.debug("PASSENGER DEBUG: Execute Statement: " + stmt);

                        int rs = stmt.executeUpdate("INSERT INTO Passenger(SeatNo, PassengerName, Type, Information, FlightID) VALUES('" + currentSeat.getValue().getSeatNumber() + "','" + currentSeat.getValue().getaPassenger().getPassengerName() + "','" + passengerType + "','" + passengerInfo + "','" + flightNo + "')");

                        for (int i = 0; i < rs; i++) {

                            LogHelper.debug("PASSENGER DEBUG:" + Reference.TextPaint.BLUE + "Passenger Saved: " + currentSeat.getValue().getaPassenger().getPassengerName() + Reference.TextPaint.RESET);

                            LogHelper.info(Reference.TextPaint.BLUE + "Passenger Saved: " + currentSeat.getValue().getaPassenger().getPassengerName() + Reference.TextPaint.RESET);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            LogHelper.error(Reference.TextPaint.RED + "PASSENGER ERROR: Passenger failed to save to DB" + Reference.TextPaint.RESET);
            LogHelper.error(e);
        }
    }

    /**
     Gets seat and adds them to the specific Flight Number

     @param FlightNo
     @param SeatNo

     @return
     */
    public Seat getSeat(String FlightNo, String SeatNo) {
        if (flights.containsKey(FlightNo)) {
            //if flight exists input flightNo
            Flight currentFlight = flights.get(FlightNo);

            //if seat exists within flight return seat found
            if (currentFlight.getSeats().containsKey(SeatNo)) {
                Seat foundSeat = currentFlight.getSeats().get(SeatNo);
                LogHelper.debug("SEAT DEBUG:" + Reference.TextPaint.BLUE + "Seat Found: " + foundSeat + Reference.TextPaint.RESET);
                return foundSeat;
            } else {
                Seat tempSeat = new Seat(SeatNo);
                currentFlight.getSeats().put(SeatNo, tempSeat);

                return tempSeat;
            }
        } else {
            return null;
        }
    }
}
