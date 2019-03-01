/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 2.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.core;

import couk.deanreid.scotiaairlines.handler.NotificationHandler;
import couk.deanreid.scotiaairlines.network.DBProxy;
import couk.deanreid.scotiaairlines.utils.LogHelper;
import couk.deanreid.scotiaairlines.utils.Reference;
import java.awt.AWTException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashMap;

public class Flight {

    // Attributes
    private String flightNumber;
    private String departure;
    private String arrival;
    private String date;
    private int freeSeats;
    private int bookedSeats;
    private int reservedSeats;
    private final int columns;
    private final int rows;
    private boolean isFull;
    private boolean checkingIn;
    private boolean closed;
    private boolean boarding;
    private String statusMessage;
    private float totalFlightTakings;
    private HashMap<String, Seat> seats;
    private String status; //0.no status, 1.checking in, 2.boarding, 3.closed
    double seatprice;

    /**
     Return the Departure information for the selected flight

     @return departure
     */
    public String getDeparture() {
        return departure;
    }

    /**
     Return the Arrival information for the selected flight

     @return arrival
     */
    public String getArrival() {
        return arrival;
    }

    /**
     Return the Date information for the selected flight

     @return date
     */
    public String getDate() {
        return date;
    }

    /**
     Return the value of free seats for the selected flight

     @return freeSeats
     */
    public int getFreeSeats() {
        return freeSeats;
    }

    /**
     Return the value of booked seats for the selected flight

     @return bookedSeats
     */
    public int getBookedSeats() {
        return bookedSeats;
    }

    /**
     Return the value of reserved seats for the selected flight

     @return
     */
    public int getReservedSeats() {
        return reservedSeats;
    }

    /**
     Return the operating status of the selected flight

     @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     Returns the flight number of the user selected flight

     @return
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     Returns the seat number for the selected flight and creates it

     @param seatNumber

     @return
     */
    public Seat getSeatNo(String seatNumber) {

        if (seats.containsKey(seatNumber)) {
            return seats.get(seatNumber);
        } else {
            Seat aNewSeat = new Seat();
            seats.put(aNewSeat.getSeatNumber(), aNewSeat);
            return aNewSeat;
        }
    }

    /**
     Returns the total taking for the selected flight

     @return
     */
    public float getTotalFlightTakings() {
        return totalFlightTakings;
    }

    /**

     @param seatNo

     @return
     */
    public Seat getSeats(String seatNo) {
        if (seats.containsKey(seatNo)) {
            return seats.get(seatNo);
        } else {
            Seat newSeat = new Seat();
            seats.put(newSeat.getSeatNumber(), newSeat);
            return newSeat;
        }
    }

    /**
     Returns the flight status message

     @return
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     Return isFull value of the selected flight if there are no free seats

     @return isFull
     */
    public boolean isFull() {
        return isFull;
    }

    /**
     Return isCheckingIn value if the flight status is set to checking in

     @return
     */
    public boolean isCheckingIn() {
        return checkingIn;
    }

    /**
     Return isClosed value if the flight status is set to closed

     @return
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     Return isBoarding value if the flight status is set to boarding

     @return
     */
    public boolean isBoarding() {
        return boarding;
    }

    /**
     Returns a HashMap value of seats available on the selected flight

     @return
     */
    public HashMap<String, Seat> getSeats() {
        return seats;
    }

    /**

     @param seats
     */
    public void setSeats(HashMap<String, Seat> seats) {
        this.seats = seats;
    }

    /**

     @param price
     */
    public void setSeatPrice(double price) {
        this.seatprice = price;
    }

    /**

     @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**

     @param departure
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    /**

     @param arrival
     */
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    /**

     @param flightNumberIn
     @param departureIn
     @param arrivalIn
     @param dateIn
     */
    public void setFlightDetails(String flightNumberIn, String departureIn, String arrivalIn, String dateIn) {

        flightNumber = flightNumberIn;
        departure = departureIn;
        arrival = arrivalIn;
        date = dateIn;
    }

    /**
     Sets the message for the flight status

     @param statusMessage
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     Sets status to Boarding

     @param boardingIn
     */
    public void setBoarding(boolean boardingIn) {
        boarding = boardingIn;
    }

    public void setDate(String dateIn) {
        date = dateIn;
    }

    /**
     Sets status to Closed

     @param closedIn
     */
    public void setClosed(boolean closedIn) {
        closed = closedIn;
    }

    /**
     Sets status to Checking in

     @param isCheckingIn
     */
    public void setCheckingIn(boolean isCheckingIn) {
        checkingIn = isCheckingIn;
    }

    /**
     Sets the flight number created by user

     @param flightNumber
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     Sets the status code selected

     @param statusCode
     */
    public void setFlightStatus(int statusCode) {
        switch (statusCode) {
            case 1:
                checkingIn = true;
                statusMessage = "Checking In";
                break;
            case 2:
                boarding = true;
                statusMessage = "Boarding";
                break;
            case 3:

                closed = true;
                statusMessage = "Flight Closed";
                break;
            case 4:

                isFull = true;
                statusMessage = "Full";
                break;
            default:
                statusMessage = "Seats Available";
                break;
        }

    }// end of set flight status

    /**
     Constructor

     @param columnsIn
     @param rowsIn
     */
    public Flight(int columnsIn, int rowsIn) {
        flightNumber = "";
        departure = "";
        arrival = "";
        date = "";
        freeSeats = columnsIn * rowsIn;
        columns = columnsIn;
        rows = rowsIn;
        isFull = false;
        checkingIn = false;
        closed = false;
        boarding = false;
        statusMessage = "Seats Available";
        seats = new HashMap<>();
        totalFlightTakings = 0.0f;

    }

    /**
     Constructor

     @param flightNoIn
     @param departureIn
     @param arrivalIn
     @param rowsIn
     @param columnsIn
     @param dateIn
     */
    public Flight(String flightNoIn, String departureIn, String arrivalIn, int rowsIn, int columnsIn, String dateIn) {

        flightNumber = flightNoIn;
        departure = departureIn;
        arrival = arrivalIn;
        date = dateIn;
        freeSeats = columnsIn * rowsIn;
        columns = columnsIn;
        rows = rowsIn;
        isFull = false;
        checkingIn = false;
        closed = false;
        boarding = false;
        statusMessage = "Seats Available";
        seats = new HashMap<>();
        totalFlightTakings = 0.0f;

    }

    /**
     Updates the seats for the flight based on booking choice

     @param bookingChoice
     */
    public void updateSeat(int bookingChoice) {
        switch (bookingChoice) {

            // cancel a reserved seat
            case 1:
                freeSeats += 1;
                reservedSeats -= 1;
                if (boarding == false && closed == false) {
                    statusMessage = "Seats Available";
                    isFull = false;
                }
                break;
            // cancel a booked seat
            case 2:
                freeSeats += 1;
                bookedSeats = (bookedSeats - 1);
                if (boarding == false && closed == false) {
                    statusMessage = "Seats Available";
                    isFull = false;
                }
                break;
            // reserve a seat
            case 3:
                reservedSeats += 1;
                freeSeats -= 1;
                break;
            // book a seat
            case 4:
                bookedSeats += 1;
                freeSeats -= 1;
                break;
            // book a reserved seat
            case 5:
                bookedSeats += 1;
                reservedSeats -= 1;
                break;
            // default
            default:
                break;

        }// end switch

        if (freeSeats == 0) {
            isFull = true;
            statusMessage = "Flight full";
        }
    }

    /**
     Add flights to database

     @throws java.awt.AWTException
     @throws java.net.MalformedURLException
     @throws java.text.ParseException
     */
    @SuppressWarnings("CallToPrintStackTrace")
    public void addFlightToDB() throws AWTException, MalformedURLException, ParseException {
        boolean shouldLeaveLoop = false;
        if (shouldLeaveLoop == false) {
            try {
                Connection connection = DBProxy.getConnection();
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("INSERT INTO Flight(FlightID, Departure, Arrival, Rows, Columns, Date) VALUES('"
                        + flightNumber
                        + "','"
                        + departure
                        + "','"
                        + arrival
                        + "','"
                        + rows
                        + "','"
                        + columns
                        + "','"
                        + date
                        + "')");
                NotificationHandler.notify("Flight Added", "Flight '" + flightNumber + "' added to database");
                LogHelper.debug(Reference.TextPaint.BLUE + "FLIGHT DEBUG:" + "Flight '" + flightNumber + "' added to database" + Reference.TextPaint.RESET);
            } catch (SQLException e) {
                shouldLeaveLoop = true;
                NotificationHandler.notify("Failed to add Flight", "Failed to add Flight, Please make sure you have correctly inserted all fields");
                LogHelper.error(Reference.TextPaint.RED + "FLIGHT ERROR:" + "Failed to add Flight, Please make sure you have correctly inserted all fields" + Reference.TextPaint.RESET);
                LogHelper.error(e);
            } catch (NumberFormatException e) {
                shouldLeaveLoop = true;
                NotificationHandler.notify("Failed to add Flight", "Failed to add Flight, You can only use numbers for Rows and Columns");
                LogHelper.error(Reference.TextPaint.RED + "FLIGHT ERROR:" + Reference.TextPaint.RESET + e);
                LogHelper.error(e);
            }
        }
    }

    /**
     Add flights to database

     @throws java.awt.AWTException
     @throws java.net.MalformedURLException
     */
    @SuppressWarnings("CallToPrintStackTrace")
    public void deleteFlightFromDB() throws AWTException, MalformedURLException {
        try {
            Connection connection = DBProxy.getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM Flight WHERE FlightID('"
                    + flightNumber
                    + "','");
            NotificationHandler.notify("Flight Deleted", "Flight '" + flightNumber + "' deleted from database");

            LogHelper.debug(Reference.TextPaint.BLUE + "FLIGHT DEBUG:" + "Flight '" + flightNumber + "' deleted from database" + Reference.TextPaint.RESET);

        } catch (SQLException e) {
            NotificationHandler.notify("Failed to delete Flight", "Failed to delete Flight");
            LogHelper.error(Reference.TextPaint.RED + "FLIGHT ERROR:" + "Failed to delete Flight" + Reference.TextPaint.RESET);
            LogHelper.error(e);
        }
    }

    /**
     Calculates the total flight takings for the flights

     @return
     */
    public float calculateTotalFlightTakings() {
        totalFlightTakings = 0.0f;
        seats.entrySet().forEach((tempSeat) -> {
            totalFlightTakings += tempSeat.getValue().getSeatTakings();
        });
        return totalFlightTakings;
    }

    /**
     Formats and Displays the flight Information

     @return
     */
    public String displayFlightInfo() {
        String output;

        output = "<html> Flight No: " + flightNumber + "<br /> Arrival Airport: " + arrival
                + "<br /> Departure Airport: " + departure + "<br /> Date: " + date + "<br /> Number Of Free Seats: " + freeSeats
                + " <br /> Number Of Reserved Seats: " + reservedSeats + "<br /> Number Of Booked Seats: " + bookedSeats
                + "<br /> Status Message: " + statusMessage + "<br /> Total Flight Takings: £" + totalFlightTakings;

        output = output + "</html>";

        return output;
    }

    /**
     Check whether the Seat Number provided is valid

     @param seatNo

     @return
     */
    public boolean isValidSeatNumber(String seatNo) {
        String number = "";
        String letter;
        int checkIfNum;
        int element = -1;
        boolean shouldLeaveLoop = false;

        for (char c : seatNo.toCharArray()) {
            if (!shouldLeaveLoop) {
                try {
                    // checks if first value is numeric, if not set should leave
                    // loop to true
                    String character;
                    character = String.valueOf(c);
                    checkIfNum = Integer.parseInt(character);
                    number = number + c;
                    element++;

                } catch (NumberFormatException e) {

                    shouldLeaveLoop = true;

                }
            }
        }

        // checking second character is a letter
        boolean lastPartIsCharacter = true;
        letter = seatNo.substring(element + 1);

        if (letter.length() == 1) {
            char letterChar = letter.charAt(0);

            if (!Character.isLetter(letterChar)) {
                lastPartIsCharacter = false;
            }
        } else {
            lastPartIsCharacter = false;
        }

        // making sure seat number falls within total number of seats on flight
        try {
            return !(Integer.parseInt(number) > columns || number.equalsIgnoreCase("") || letter.length() != 1);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
