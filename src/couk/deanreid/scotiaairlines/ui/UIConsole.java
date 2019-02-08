/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.7
 Code Version: 1.1
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.ui;

import couk.deanreid.scotiaairlines.core.Airline;
import couk.deanreid.scotiaairlines.core.Flight;
import couk.deanreid.scotiaairlines.utils.Reference;
import java.awt.AWTException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UIConsole {

    private final Airline scotiaAirlineC;
    String menuChoice = "";
    boolean stopMainMenu = false;
    boolean stopSubMenu = false;
    boolean stopSubSubMenu = false;
    Scanner reader;

    /**
     * Console UI Class Constructor
     *
     * @param scotiaAir
     */
    public UIConsole(Airline scotiaAir) {
        scotiaAirlineC = scotiaAir;
        reader = new Scanner(System.in);
    }

    public void genericPopup(String inputMessage) {
        System.out.println(Reference.TextPaint.GREEN + "ScotiaAirline Information System:: " + Reference.TextPaint.BLUE + inputMessage);
    }

    public void loadInterface() {

        do {

            mainMenu();

            menuChoice = reader.next();
            do {
            } while (1 == 2);
            switch (menuChoice) {
                //Start of 1.Flight Administration
                case "1":
                    do {
                        adminMenu();
                        stopSubMenu = false;
                        menuChoice = reader.next();
                        switch (menuChoice) {
                            //start of 1.Add flight details
                            case "1":
                                String flightNo = "";
                                String departure = "";
                                String arrival = "";
                                int rows = 0;
                                int columns = 0;
                                String date = "";

                                Flight flight = new Flight(flightNo, departure, arrival, rows, columns, date);

                                System.out.println("Enter flight number: ");
                                flight.setFlightNumber(reader.next());

                                System.out.println("Enter flight departure: ");
                                flight.setDeparture(reader.next());

                                System.out.println("Enter flight arrival: ");
                                flight.setArrival(reader.next());

                                System.out.println("Enter flight date: ");
                                flight.setDate(reader.next());
                                
                                flight.setStatus(reader.next());

                                System.out.println("Enter seat price: ");
                                flight.setSeatPrice(Double.parseDouble(reader.next()));

                                break;
                            //2.Update flight status
                            case "2":

                                selectFlight();

                                System.out.println("Enter flight number: ");

                                String flightNumber = reader.next();

                                Flight flightToEdit = Airline.getFlights(flightNumber);

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

                                flightToEdit.updateStatus(flightNumber, newStatus);

                                break;

                            case "3":
                                selectFlight();

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

                        displayBookingMenu();

                        menuChoice = reader.next();

                        switch (menuChoice) {
                            //1. Cancel seat reservation
                            case "1":
                                selectFlight();
                                System.out.println("Please enter flight number: ");
                                Flight flight = new Flight(reader.next());

                        switch (flight.getStatus()) {
                            case "2":
                                System.out.println("Error Message - Cancellation too late");
                                break;
                            case "3":
                                System.out.println("Error Message - Cancellation too late");
                                break;
                            default:
                                //carriage.retrieveFromFile();
                                int seatnosToCancel;
                                System.out.println("Please enter seat number (1-" + flight.getNosSeats() + ")");
                                seatnosToCancel = reader.nextInt();
                                flight.updateSeat(seatnosToCancel);
                        {
                            try {
                                Airline.SaveSeatsToDB();
                            } catch (AWTException | MalformedURLException | ParseException ex) {
                                Logger.getLogger(UIConsole.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                                break;
                        }

                                break;
                            //2. Reserve a seat.	
                            case "2":
                                selectFlight();
                                System.out.println("Please enter flight number: ");
                                Flight flight2 = new Flight(reader.next());

                                if (flight2.getStatus().equals("1")) {
                                    System.out.println("Error Message - Reservations not available");
                                } else {
                                    int seatnosToReserve;
                                    System.out.println("Please enter seat number (1-" + flight.getFreeSeats()+ ")");
                                    seatnosToReserve = reader.nextInt();
                                    flight.updateSeat(seatnosToReserve);
                                    
                            try {
                                Airline.SaveSeatsToDB();
                            } catch (AWTException | MalformedURLException | ParseException ex) {
                                Logger.getLogger(UIConsole.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                }

                                break;

                            //3. Book a seat
                            case "3":
                                selectFlight();
                                System.out.println("Please enter flight number: ");
                                Flight flight3 = new Flight(reader.next());
                                if (flight3.getStatus().equals("2")) {
                                    System.out.println("Error Message - bookings not available");
                                } else if (flight.getStatus().equals("3")) {
                                    System.out.println("Error Message - bookings not available");
                                } else {
                                    int seatnosToBook;
                                    System.out.println("Please enter seat number (1-" + flight.getNosSeats() + ")");
                                    seatnosToBook = reader.nextInt();
                                    flight.updateSeat(seatnosToBook);
                                    Airline.SaveSeatsToDB();
                                }

                                break;

                            default:
                            case "R":
                            case "r":
                                stopSubSubMenu = true;
                        } // end switch comind5
                    } while (!stopSubSubMenu); // end do while
                    break;

                case "3":
                    selectFlight();
                    System.out.println("Please enter flight number: ");
                    Flight flight4 = new Flight(reader.next());

                    flight4.getSeats();
                    break;

                case "4":
                    selectFlight();
                    System.out.println("Please enter flight number: ");
                    Flight flight5 = new Flight(reader.next());
                        flight5.DisplayFlightInfo();
                    break;

//				default:
                case "Q":
                case "q":
                    stopMainMenu = true;
            } // end switch comind1				
        } while (!stopMainMenu);// end do while

    }

    public void mainMenu() {
        System.out.println("Scotia Airlines - Main Menu");
        System.out.println("===========================");
        System.out.println("1.Flight Administration");
        System.out.println("2.Bookings");
        System.out.println("3.Display Seat Details");
        System.out.println("4.Display Flight Details");
        System.out.println("Q.Quit");
        System.out.println("Make your selection >");
    }

    public void adminMenu() {
        System.out.println("Scotia Airlines - Flight Admin");
        System.out.println("==============================");
        System.out.println("1.Add flight details");
        System.out.println("2.Update flight status");
        System.out.println("3.View all flights");
        System.out.println("R.Return to Main Menu");
        System.out.println("Make your selection >");
    }

    public void selectFlight() {
        System.out.println("Scotia Airlines - Flights");
        System.out.println("=========================");
        System.out.println("");

        /*
         Insert code here to show the flight hashmap
         */
        System.out.println("");
        System.out.println("R.Return to Flight Admin Menu");
        System.out.println("Make your selection >");
    }

    public void displayChangeStatusMenu() {
        System.out.println("Scotia Airlines - Flight Status");
        System.out.println("===============================");
        System.out.println("1.Checking in");
        System.out.println("2.Boarding");
        System.out.println("3.Flight closed");
        System.out.println("R.Return to Flight Admin Menu");
        System.out.println("Make your selection >");
    }

    public void displayBookingMenu() {
        System.out.println("Scotia Airlines - Bookings");
        System.out.println("==========================");
        System.out.println("1.Cancel a reservation/booking");
        System.out.println("2.Reserve a seat");
        System.out.println("3.Book a seat");
        System.out.println("R.Return to Main Menu");
        System.out.println("Make your selection >");
    }

    public void bookPassenger() {
        /* 
         insert booking code here 
         */
    }

}
