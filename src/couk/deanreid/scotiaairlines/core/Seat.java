/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.6
 Code Version: 2.3
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.core;

import couk.deanreid.scotiaairlines.handler.NotificationHandler;
import couk.deanreid.scotiaairlines.ui.UI;
import java.awt.AWTException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Seat {

    /**
     @TODO: Add Windows 10 Notifications.
     */
    //Attributes
    private String seatNumber;
    private float seatPrice;
    private float seatTakings;
    private int currentStatus;
    private int completeSeatStatus;
    private Passenger aPassenger;

    //Getters
    public void setCompleteSeatStatus(int completeSeatStatusIn) {
        completeSeatStatus = completeSeatStatusIn;
    }

    public String getSeatNumber() {

        return seatNumber;
    }

    public void setSeatNumber(String seatNumberIn) {
        seatNumber = seatNumberIn;
    }

    public float getSeatPrice() {
        return seatPrice;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public Passenger getaPassenger() {
        return aPassenger;
    }

    //Setters
    public void setSeatPrice(float seatPriceIn) {
        seatPrice = seatPriceIn;
    }

    public float getSeatTakings() {
        return seatTakings;
    }

    public void setSeatTakings(float seatTakingsIn) {
        seatTakings = seatTakingsIn;
    }

    public void setCurrentStatus(int currentStatusIn) {
        currentStatus = currentStatusIn;
    }

    public void setaPassenger(Passenger aPassengerIn) {
        aPassenger = aPassengerIn;
    }

    //Default Constructor
    public Seat() {
        seatNumber = "";
        seatPrice = 100;
        seatTakings = 0f;
        currentStatus = 1;
        aPassenger = null;
    }

    /**
     Constructor

     @param column
     @param row
     */
    public Seat(int column, char row) {
        seatNumber = String.valueOf(column) + row;
        seatPrice = 100;
        seatTakings = 0f;
        currentStatus = 1;
        aPassenger = null;
    }

    /**
     Constructor

     @param seatNo
     */
    public Seat(String seatNo) {
        seatNumber = seatNo;
        seatPrice = 100;
        seatTakings = 0f;
        currentStatus = 1;
        aPassenger = null;
    }

    //Method to display details for specific seat
    public String DisplaySeatDetails() {
        String output = "";
        String seatStatus = "";

        switch (currentStatus) {
            case 1:
                seatStatus = "Free";
                break;

            case 2:

                seatStatus = "Reserved";
                break;

            case 3:

                seatStatus = "Booked";

        }

        output = "<html> Seat No: " + seatNumber + "<br /> Current Status: "
                + seatStatus + "<br /> Seat Price: £" + seatPrice
                + "<br /> Seat Takings: £" + seatTakings;

        if (aPassenger != null) {
            output = output + "<br /> Passenger Name: " + aPassenger.getPassengerName();

        }

        output = output + "</html>";

        return output;

    }

    //Method which changes status of particular seat based on passenger type
    public int changeSeatStatus(int seatStatus, float currentTakings, String passengerName, char passengerType, String passengerInfo) {
        completeSeatStatus = -1;
        seatTakings = currentTakings;

        switch (passengerType) {
            case 'I': {
                PassengerWestern newPassenger = new PassengerWestern(passengerName, passengerInfo);
                aPassenger = newPassenger;
                if (seatStatus == 2) {
                    currentStatus = 2;
                    completeSeatStatus = 3;
                } else if (seatStatus == 3) {
                    currentStatus = 3;
                    completeSeatStatus = 4;
                }
                break;
            }
            case 'B': {
                PassengerBusiness newPassenger = new PassengerBusiness(passengerName, passengerInfo);
                aPassenger = newPassenger;
                if (seatStatus == 2) {
                    currentStatus = 2;
                    completeSeatStatus = 3;
                } else if (seatStatus == 3) {
                    currentStatus = 3;
                    completeSeatStatus = 4;
                }
                break;
            }
            case 'O': {
                PassengerOrdinary newPassenger = new PassengerOrdinary(passengerName, passengerInfo.charAt(0));
                aPassenger = newPassenger;
                if (seatStatus == 2) {
                    currentStatus = 2;
                    completeSeatStatus = 3;
                } else if (seatStatus == 3) {
                    currentStatus = 3;
                    completeSeatStatus = 4;
                }
                break;
            }
            default:
                break;
        }

        return completeSeatStatus;
    }

    //Method which changes status of particular seat depending on booking choice made by passenger
    public int changeSeatStatus(Airline scotiaAirline, int newStatus, Passenger newPassenger, Flight newFlight) {
        UI UI = new UI(scotiaAirline);

        //cancel seat
        switch (newStatus) {
            case 1:
                switch (currentStatus) {
                    case 1:
                        UI.genericPopup("Seat Number: '" + seatNumber + "' Is Already Free");
                        return -1;
                    case 2:
                        currentStatus = 1;
                        aPassenger = null;
                        completeSeatStatus = 1;
                        UI.genericPopup("Seat Number: '" + seatNumber + "' Has Been Cancelled");
                        break;
                    case 3:
                        currentStatus = 1;
                        aPassenger = null;
                        completeSeatStatus = 2;
                        UI.genericPopup("Seat Number: '" + seatNumber + "' Has Been Cancelled - No Refund Eligible");
                        break;
                    default:
                        break;
                }
                //reserve seat
                break;
            case 2:
                switch (currentStatus) {
                    case 1:
                        currentStatus = 2;
                        aPassenger = newPassenger;
                        completeSeatStatus = 3;
                        UI.genericPopup("Seat Number: '" + seatNumber + "' Has Now Been Reserved By '" + newPassenger.getPassengerName() + "'");
                        break;
                    case 2:
                        UI.genericPopup("Seat Number: '" + seatNumber + "' Is Already Reserved By '" + aPassenger.getPassengerName() + "'");
                        return -1;
                    case 3:
                        UI.genericPopup("Seat Number: '" + seatNumber + "' Is Already Booked By '" + aPassenger.getPassengerName() + "'");
                        return -1;
                    default:
                        break;
                }

                //book seat
                break;
            case 3:
                switch (currentStatus) {
                    case 1:
                        currentStatus = 3;
                        aPassenger = newPassenger;
                        completeSeatStatus = 4;
                        seatTakings += (seatPrice * newPassenger.getDiscountAmount());
                         {
                            try {
                                NotificationHandler.Notify("Seat Booked", "Seat Number: '" + seatNumber + "' Has Now Been Booked By '" + newPassenger.getPassengerName() + "'");
                            } catch (AWTException | MalformedURLException ex) {
                                Logger.getLogger(Seat.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case 2:
                        if (newPassenger.getPassengerName().equalsIgnoreCase(aPassenger.getPassengerName())) {
                            currentStatus = 3;
                            aPassenger = newPassenger;
                            completeSeatStatus = 5;
                            seatTakings += (seatPrice * newPassenger.getDiscountAmount());
                            try {
                                NotificationHandler.Notify("Seat Booked", "Seat Number: '" + seatNumber + "' Has Now Been Booked By '" + newPassenger.getPassengerName() + "'");
                            } catch (AWTException | MalformedURLException ex) {
                                Logger.getLogger(Seat.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                NotificationHandler.Notify("Seat Already Reserved", "Seat Number: '" + seatNumber + "' Is Already Reserved By '" + aPassenger.getPassengerName() + "'");
                            } catch (AWTException | MalformedURLException ex) {
                                Logger.getLogger(Seat.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            return -1;
                        }
                        break;
                    case 3: {
                        try {
                            NotificationHandler.Notify("Seat Already Booked", "Seat Number: '" + seatNumber + "' Is Already Booked By '" + aPassenger.getPassengerName() + "'");
                        } catch (AWTException | MalformedURLException ex) {
                            Logger.getLogger(Seat.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    return -1;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        return completeSeatStatus;

    }
}
