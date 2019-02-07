/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.4
 Code Version: 1.1
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.ui;

import couk.deanreid.scotiaairlines.core.Airline;
import couk.deanreid.scotiaairlines.core.Flight;
import couk.deanreid.scotiaairlines.core.Seat;
import java.awt.AWTException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

public class UIConsole {

    private int buttonCounter = 2;
   Airline scotiaAirline;
    Flight scFlight;
    Seat scSeat;
        Scanner reader;
    
    
    
    String menuChoice = "";
    boolean stopMainMenu = false;
    boolean stopSubMenu = false;
    boolean stopSubSubMenu = false;


    public UIConsole() {
        reader = new Scanner(System.in);
    }

    public void loadConsoleInterface() throws IOException, AWTException, MalformedURLException, ParseException {

        do {
            this.displayMainMenu();

            menuChoice = reader.next();
            do {

            } while (1 == 2);
            switch (menuChoice) {
                //Start of 1.Flight Administration
                case "1":

                    do {
                        //Inside Flight Admin Menu

                        this.displayAdminMenu();
                        stopSubMenu = false;

                        menuChoice = reader.next();

                        switch (menuChoice) {
                            //start of 1.Add flight details
                            case "1":

         
                                System.out.println("Enter flight number: ");
                                scFlight.setFlightNumber(reader.next());

                                System.out.println("Enter flight departure: ");
                                scFlight.setDeparture(reader.next());

                                System.out.println("Enter flight arrival: ");
                                scFlight.setArrival(reader.next());

                                System.out.println("0. No Status");
                                System.out.println("1. Checking in");
                                System.out.println("2. Boarding");
                                System.out.println("3. Flight closed");
                                System.out.println("Enter flight status: ");

                                scFlight.setStatus(reader.next());

                                System.out.println("Enter seat price: ");
                                scFlight.setSeatPrice(Double.parseDouble(reader.next()));

                                scFlight.addFlightToDB();
                                break;
                            //2.Update flight status
                            case "2":

                                this.displayAllFlights();

                                System.out.println("Enter flight number: ");

                                String flightNo = null;
                                String departure = null;
                                String arrival = null;
                                int rows = 0;
                                int columns = 0;
                                //Flight aNewFlight = new Flight(flightNo, departure, arrival, rows, columns);
                                //addFlight(aNewFlight);
                                //Flight Flight = aNewFlight;
                                String newStatus = "";

                                boolean terminate = false;

                                do {
                                    System.out.println("0. Available for bookings");
                                    System.out.println("1. Checking in");
                                    System.out.println("2. Boarding");
                                    System.out.println("3. Flight closed");
                                    System.out.println("Enter new flight status: ");

                                    newStatus = reader.next();

                                    if (newStatus.equals("0") || newStatus.equals("1") || newStatus.equals("2") || newStatus.equals("3")) {
                                        terminate = true;
                                    }

                                } while (!terminate);

                                //aNewFlight.updateStatus(flightNo, newStatus);
                                break;

                            case "3":
                                this.displayAllFlights();

                                break;

                            default:
                            case "R":
                            case "r":
                                stopSubMenu = true;
                        }
                    } while (!stopSubMenu); // end do while

                    //end of 1.Flight Administration	
                    break;

                case "2":
                    do {

                        this.displayBookingsMenu();

                        menuChoice = reader.next();

                        switch (menuChoice) {
                            //1. Cancel seat reservation
                            case "1":
                                break;
                            //2. Reserve a seat.	
                            case "2":
                                break;
                            //3. Book a seat
                            case "3":
                                break;

                            default:
                            case "R":
                            case "r":
                                stopSubSubMenu = true;
                        } // end switch comind5
                    } while (!stopSubSubMenu); // end do while
                    break;

                case "3":
                    break;

                case "4":
                    break;
                default:
                case "Q":
                case "q":
                    stopMainMenu = true;
            } // end switch comind1				
        } while (!stopMainMenu);// end do while

    }

    public void displayMainMenu() {
        // display the Main Menu
        System.out.println("Scotia Airlines - Main Menu");
        System.out.println("===========================");
        System.out.println("1.Flight Administration");
        System.out.println("2.Bookings");
        System.out.println("3.Display Seat Details");
        System.out.println("4.Display Flight Details");
        System.out.println("Q.Quit");
        System.out.println("Make your selection >");
    }

    public void displayAdminMenu() {
        // display the Flight Administration Menu
        System.out.println("Scotia Airlines - Flight Admin");
        System.out.println("==============================");
        System.out.println("1.Add flight details");
        System.out.println("2.Update flight status");
        System.out.println("3.View all flights");
        System.out.println("R.Return to Main Menu");
        System.out.println("Make your selection >");
    }

    public void displayBookingsMenu() {
        // display the Bookings Menu
        System.out.println("Scotia Airlines - Bookings");
        System.out.println("==========================");
        System.out.println("1.Cancel a reservation/booking");
        System.out.println("2.Reserve a seat");
        System.out.println("3.Book a seat");
        System.out.println("R.Return to Main Menu");
        System.out.println("Make your selection >");
    }

    public void displayFlightMenu() {
        // display the Flights Menu
        System.out.println("Scotia Airlines - Flights");
        System.out.println("=========================");
        System.out.println("1.SA123 Glasgow to Barra");
        System.out.println("2.SA124 Barra to Glasgow");
        System.out.println("3.SA234 Glasgow to Benbecula");
        System.out.println("4.SA235 Benbecula to Glasgow");
        System.out.println("5.SA345 Glasgow to Stornoway");
        System.out.println("6.SA346 Stornoway to Glasgow");
        System.out.println("R.Return to Flight Admin Menu");
        System.out.println("Make your selection >");
    }

    public void displayStatusMenu() {
        // display the Flight status
        System.out.println("Scotia Airlines - Flight Status");
        System.out.println("===============================");
        System.out.println("1.Checking in");
        System.out.println("2.Boarding");
        System.out.println("3.Flight closed");
        System.out.println("R.Return to Flight Admin Menu");
        System.out.println("Make your selection >");
    }

    public void displayAllFlights() {
        // ArrayList<Flight> flights = aFlight.getAllFlightsFromFile();
        // System.out.println("Flight Number\tDeparture\tArrival\t\tStatus\t\tSeat Price");

        // for (int i = 0; i < flights.size(); i++) {
        // System.out.println(
        //          flights.get(i).getFlightNumber()
        //           + "\t\t"
        //          + flights.get(i).getDeparture()
        //           + "\t\t"
        //           + flights.get(i).getArrival()
        //          + "\t\t"
        //            + flights.get(i).displayFlightStatus()
        //         + "\t\t"
        //           + flights.get(i).getSeatPrice());
        //}
        //}
    } // end of UserInterface class
}
