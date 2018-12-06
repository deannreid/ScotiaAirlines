/*
 Scotia Airlines - HND Computer Science
 Version 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Airline {

    //attributes
    private final HashMap<String, Flight> flights;

    //Getter for Flight Hashmap
    public HashMap<String, Flight> getFlights() {
        return flights;
    }

    //Constructor
    public Airline() {
        flights = new HashMap<>();
    }

    //Getter for Specific Flights
    public Flight getFlights(String flightNo) {

        if (flights.containsKey(flightNo)) {
            return flights.get(flightNo);
        } else {
            Flight aNewFlight = new Flight(0, 0);
            flights.put(aNewFlight.getFlightNumber(), aNewFlight);
            return aNewFlight;
        }

    }

    //Add Flight to Hashmap
    public void addFlight(Flight aFlight) {
        flights.put(aFlight.getFlightNumber(), aFlight);
    }

    //Loads all Flights from the Database and inserts into Hashmap
    public void loadFlightsFromDB() {
        try {
            Connection connection = SQLConnector.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Flight");
            System.out.println("=======================");
            System.out.println("Loading Flight Database");
            System.out.println("=======================");
            while (rs.next()) {
                String flightNo = rs.getString(1);
                String departure = rs.getString(2);
                String arrival = rs.getString(3);
                int rows = rs.getInt(4);
                int columns = rs.getInt(5);

                Flight aNewFlight = new Flight(flightNo, departure, arrival, rows, columns);
                addFlight(aNewFlight);
                Flight Flight = aNewFlight;
                System.out.println("Database Flight: '" + flightNo + "' Loaded");
            }
        } catch (SQLException e) {
            System.out.println("Database Flights Failed to Load");
            e.printStackTrace();
        }
    }

    //Loads Seats from the Database into Hashmap
    public void loadSeatsFromDB() throws SQLException {
        try {
            System.out.println("");
            System.out.println("=======================");
            System.out.println("Loading Seat and Passenger Database");
            System.out.println("=======================");
            Connection connection = SQLConnector.getConnection();
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
                System.out.println("Seat '" + SeatNo + "' on flight: '" + FlightNo + "' Loaded");
            }

        } catch (SQLException e) {
            System.out.println("Database Seats Failed to Load");
            e.printStackTrace();
        }
    }

    //Loads Passengers from the Database
    public void loadPassengersFromDB(String flightNo, Seat passengerSeat, int status, float takings) {
        try {
            Connection connection = SQLConnector.getConnection();
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
                    System.out.println("Passenger: '" + passengerName + "' on flight: '" + flightNumber + "' Loaded");
                }

            }
        } catch (SQLException e) {
            System.out.println("Database Passengers Failed to Load");
        }
    }

    /*
     Empties the Database of all entities
     so that new values can be written
     */
    public void EmptyDB() {
        UI ui = new UI(this);
        PreparedStatement preparedStatement = null;
        String deleteSeat = "DELETE FROM Seat";
        String deletePassenger = "DELETE FROM Passenger";
        try {
            System.out.println("Cleaning DB");
            Connection connection = SQLConnector.getConnection();
            //Statement stmt = connection.createStatement();
            //int rs = stmt.executeUpdate("DELETE * FROM Seat");
            preparedStatement = connection.prepareStatement(deleteSeat);
            //preparedStatement.setInt(1, 1001);
            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                System.out.println("Seat Tables Emptied");
            }
        } catch (SQLException e) {
            System.out.println("Seat table cannot be emptied");
            e.printStackTrace();
        }
        try {
            Connection connection = SQLConnector.getConnection();
            //Statement stmt = connection.createStatement();
            //int rs = stmt.executeUpdate("DELETE * FROM Passenger");
            preparedStatement = connection.prepareStatement(deletePassenger);
            //preparedStatement.setInt(1, 1001);
            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                System.out.println("Passengers Emptied");

            }
        } catch (SQLException e) {
            System.out.println("Passenger table cannot be emptied");
            e.printStackTrace();
        }
    }

    //Saves Seat information to Database
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

                    Connection connection = SQLConnector.getConnection();
                    Statement stmt = connection.createStatement();
                    int rs = stmt.executeUpdate("INSERT INTO Seat (SeatNo, Status, Takings, FlightID) VALUES('" + seatNo + "','" + seatStatus + "','" + seatTakings + "','" + flightNo + "')");
                    System.out.println(rs);
                    if (rs > 0) {
                        System.out.println("Seat: '" + seatNo + "' booked on flight: '" + flightNo + "' by passenger: '" + currentSeat.getValue().getaPassenger().getPassengerName() +"'");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Seats failed to save to DB");
        }
    }

    //Saves Passenger information to Database
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
                        Connection connection = SQLConnector.getConnection();
                        Statement stmt = connection.createStatement();
                        int rs = stmt.executeUpdate("INSERT INTO Passenger(SeatNo, PassengerName, Type, Information, FlightID) VALUES('" + currentSeat.getValue().getSeatNumber() + "','" + currentSeat.getValue().getaPassenger().getPassengerName() + "','" + passengerType + "','" + passengerInfo + "','" + flightNo + "')");
                        System.out.println(rs);
                        for (int i = 0; i < rs; i++) {
                            System.out.println("Passenger Saved: '" + currentSeat.getValue().getaPassenger().getPassengerName() + "'");
                        }
                    }
                }

            }
        } catch (SQLException e) {
            System.out.println("Seats failed to save to DB");
        }
    }

    //Gets seat and adds them to the specific Flight Number
    public Seat getSeat(String FlightNo, String SeatNo) {
        if (flights.containsKey(FlightNo)) {
            //if flight exists input flightNo
            Flight currentFlight = flights.get(FlightNo);

            //if seat exists within flight return seat found
            if (currentFlight.getSeats().containsKey(SeatNo)) {
                Seat foundSeat = currentFlight.getSeats().get(SeatNo);

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
