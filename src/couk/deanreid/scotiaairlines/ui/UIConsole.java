/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.7
 Code Version: 1.1
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.ui;

import couk.deanreid.scotiaairlines.core.Airline;

public class UIConsole {

    private final Airline scotiaAirlineC;
    
    /**
     * Console UI Class Constructor 
     * @param scotiaAir
     */  
    public UIConsole(Airline scotiaAir) {
        scotiaAirlineC = scotiaAir;
    }

    public void loadConsoleInterface() {}
    
    public void genericPopup(String inputMessage){}
    
    public void mainMenu(){}
    public void adminMenu(){}
    public void addNewFlight(){}
    public void selectFlight(final String status){}
    public void displayChangeStatusMenu(final String FlightInfo){}
    public void displayBookingMenu(final String FlightInfo){}
    public void bookPassenger (final String flightNumber, final int bookingChoice, final String seatNo){}
    
    

}
