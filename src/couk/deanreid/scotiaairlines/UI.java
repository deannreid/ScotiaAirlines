/*
 Scotia Airlines - HND Computer Science
 Version 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UI {

    //instance variables
    private int buttonCounter = 2;
    private final Airline scotiaAirline;

    //Constructor
    public UI(Airline aAirline) {

        scotiaAirline = aAirline;
    }

    //Generic popup for Information or Errors
    public void genericOutput(String inputMessage) {
        JLabel saTitle = new javax.swing.JLabel();
        final JFrame saFrame = new JFrame();
        saFrame.setSize(350, 200);
        saFrame.setLocationRelativeTo(null);
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setTitle("Scotia Airlines - ");

        saTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        saTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon.png"))); // NOI18N
        saTitle.setText("Scotia Airlines Booking System");

        JPanel saPanel = new JPanel();

        Container c1 = new Container();
        Container c2 = new Container();

        c1.setLayout(new GridLayout(1, 0));
        c2.setLayout(new GridLayout(1, 0));

        JLabel message = new JLabel(inputMessage);

        JButton backBtn = new JButton("Main Menu");

        c1.add(message);
        c2.add(backBtn);

        saPanel.add(c1);
        saPanel.add(c2);

        saFrame.add(saPanel);
        saFrame.setVisible(true);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.dispose();
                mainMenu();
            }
        });

    }

    //Displays Main Menu
    @SuppressWarnings("Convert2Lambda") //Disable Lambda warning for J8 as college doesn't have J8 runtime.
    public void mainMenu() {
        //create frame object and size.
        final JFrame saFrame = new JFrame();

        //Frame Settings
        saFrame.setTitle("Scotia Airlines - Booking System");
        saFrame.setSize(390, 420);
        // saFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        saFrame.setType(java.awt.Window.Type.POPUP);
        saFrame.setLocationRelativeTo(null);        //sets frame in middle of the screen when null value is set
        saFrame.getContentPane().setLayout(null);
        saFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ScotiaAirline.WindowClosing(evt);
            }
        });
        //need atleast one panel on a window, default will be full size of frame
        JPanel saPanel = new JPanel();

        JLabel saLabel = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/assets/tbico.png")), SwingConstants.CENTER);
        saFrame.getContentPane().add(saLabel);
        saLabel.setBounds(0, 0, 378, 100);

        //setBounds(X,Y,Width,Height
        JButton testMenuBtn = new javax.swing.JButton();
        testMenuBtn.setText("Test Menu");
        saFrame.getContentPane().add(testMenuBtn);
        testMenuBtn.setBounds(10, 10, 100, 30);

        JButton bookingMenuBtn = new JButton("Booking Menu");
        //bookingMenuBtn.setEnabled(false);
        bookingMenuBtn.setText("Book Flight");
        bookingMenuBtn.setFont(new java.awt.Font("Tahoma", 1, 11));
        saFrame.getContentPane().add(bookingMenuBtn);
        bookingMenuBtn.setBounds(83, 106, 220, 60);

        JButton displaySeatDetailsBtn = new JButton("Display Seat Details");
        //displaySeatDetailsBtn.setEnabled(false);
        saFrame.getContentPane().add(displaySeatDetailsBtn);
        displaySeatDetailsBtn.setBounds(83, 238, 220, 60);

        JButton displayFlightDetailsBtn = new JButton("Display Flight Details");
        //displayFlightDetailsBtn.setEnabled(false);
        saFrame.getContentPane().add(displayFlightDetailsBtn);
        displayFlightDetailsBtn.setBounds(83, 172, 220, 60);

        JButton flightAdminBtn = new JButton("Flight Admin");
        //flightAdminBtn.setEnabled(false);
        saFrame.getContentPane().add(flightAdminBtn);
        flightAdminBtn.setBounds(83, 300, 110, 60);

        JButton exitBtn = new JButton("Exit");
        saFrame.getContentPane().add(exitBtn);
        exitBtn.setBounds(200, 300, 103, 60);

        saFrame.add(saPanel);
        saFrame.setVisible(true);

        testMenuBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                displayTestMenu();
            } //default action performed is a click
        });

        flightAdminBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                FlightAdminHomepage();
            } //default action performed is a click
        });

        bookingMenuBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                selectFlight("Booking");
            } //default action performed is a click
        });

        displayFlightDetailsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                selectFlight("DisplayFlightDetails");
            } //default action performed is a click
        });

        displaySeatDetailsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                selectFlight("DisplaySeatDetails");
            } //default action performed is a click
        });

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(exitBtn, "Please Wait while changes are saved!");
                saFrame.dispose();
                scotiaAirline.EmptyDB();
                scotiaAirline.SaveSeatsToDB();
                scotiaAirline.SavePassengersToDB();
                System.exit(0);
            } // default action performed is a click
        });
    }

    //Displays Admin Screen
    public void FlightAdminHomepage() {
        // create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        // sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);
        saFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // need atleast one panel on a window, default will be full size of
        // frame
        JPanel saPanel = new JPanel();

        saPanel.setLayout(new GridLayout(5, 1));

        JLabel title = new JLabel("Flight Administration", SwingConstants.CENTER);

        JButton changeFlightStatusBtn = new JButton("Change Flight Status");
        JButton addNewFlight = new JButton("Add New Flight");
        JButton backBtn = new JButton("Return To Main Menu");

        // add components to panel then panel to frame
        // order which items are added is the order that they will appear
        saPanel.add(title);
        saPanel.add(changeFlightStatusBtn);
        saPanel.add(addNewFlight);
        saPanel.add(backBtn);
        saFrame.add(saPanel);

        saFrame.setVisible(true);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                mainMenu();
            } //default action performed is a click
        });

        addNewFlight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                addNewFlight();
            } // default action performed is a click
        });

        changeFlightStatusBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                selectFlight("ChangeFlightStatus");
            } // default action performed is a click
        });

    }

    //Displays New Flight Screen
    public void addNewFlight() {
        // create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        // sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);
        saFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // need atleast one panel on a window, default will be full size of
        // frame
        JPanel saPanel = new JPanel();

        Container c = new Container();
        Container c1 = new Container();
        Container c2 = new Container();

        c.setLayout(new GridLayout(1, 0));
        c1.setLayout(new GridLayout(5, 1));
        c2.setLayout(new GridLayout(3, 1));

        JLabel title = new JLabel("Add New Flight", SwingConstants.CENTER);

        JLabel flightID = new JLabel("Flight ID: ");
        //how many characters wide, scrolls
        final JTextField txtFlightID = new JTextField(10);

        JLabel departure = new JLabel("Departure: ");
        //how many characters wide, scrolls
        final JTextField txtDeparture = new JTextField(10);

        JLabel arrival = new JLabel("Arrival: ");
        //how many characters wide, scrolls
        final JTextField txtArrival = new JTextField(10);

        JLabel rows = new JLabel("Rows: ");
        //how many characters wide, scrolls
        final JTextField txtRows = new JTextField(10);

        JLabel columns = new JLabel("Columns: ");
        //how many characters wide, scrolls
        final JTextField txtColumns = new JTextField(10);

        JButton submitBtn = new JButton("Submit");
        JButton clearBtn = new JButton("Clear");
        JButton backBtn = new JButton("Back To Admin Options");

        c.add(title);
        c1.add(flightID);
        c1.add(txtFlightID);
        c1.add(departure);
        c1.add(txtDeparture);
        c1.add(arrival);
        c1.add(txtArrival);
        c1.add(rows);
        c1.add(txtRows);
        c1.add(columns);
        c1.add(txtColumns);
        c2.add(submitBtn);
        c2.add(clearBtn);
        c2.add(backBtn);

        saPanel.add(c);
        saPanel.add(c1);
        saPanel.add(c2);

        saFrame.add(saPanel);
        saFrame.setVisible(true);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                FlightAdminHomepage();
            } // default action performed is a click
        });

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                addNewFlight();
            } // default action performed is a click
        });

        //submits the entered flight details
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String flightNo = txtFlightID.getText();
                String departure1 = txtDeparture.getText();
                String arrival1 = txtArrival.getText();
                int rows1 = Integer.parseInt(txtRows.getText());
                int columns1 = Integer.parseInt(txtColumns.getText());
                Flight newFlight = new Flight(flightNo, departure1, arrival1, rows1, columns1);
                newFlight.addFlightToDB();
                scotiaAirline.addFlight(newFlight);
                saFrame.setVisible(false);
                saFrame.dispose();
                //display added flight
                genericOutput("Flight: " + flightNo + " added");
            } // default action performed is a click
        });

    }//end of add new Flight

    //select flight method, displays a list of flights
    //clicking on specified flight will take you to appropriate screen depending on status which is passed in
    public void selectFlight(final String status) {

        buttonCounter = 2;
        //create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        //sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);
        saFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //need atleast one panel on a window, defualt will be full size of frame
        JPanel saPanel = new JPanel();

        JLabel title = new JLabel("Select Flight", SwingConstants.CENTER);

        saPanel.add(title);

        //displaying all flights in hashmap
        scotiaAirline.getFlights().entrySet().stream().map((currentFlight) -> {
            JButton tempButton = new JButton("No: " + currentFlight.getValue().getFlightNumber() + " Depature: "
                    + currentFlight.getValue().getDeparture() + " Arrival: " + currentFlight.getValue().getArrival());
            tempButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (status.equalsIgnoreCase("Booking")) {
                        saFrame.dispose();
                        DisplayBookingMenu(e.getActionCommand());
                    } else if (status.equalsIgnoreCase("DisplayFlightDetails")) {
                        Flight tempFlight = currentFlight.getValue();
                        genericOutput(tempFlight.DisplayFlightInfo());
                    } else if (status.equalsIgnoreCase("DisplaySeatDetails")) {
                        getSeatno(currentFlight.getValue().getFlightNumber(), 4);
                    } else if (status.equalsIgnoreCase("ChangeFlightStatus")) {
                        saFrame.dispose();
                        DisplayChangeStatusMenu(e.getActionCommand());
                    }
                }
            });
            return tempButton;
        }).map((tempButton) -> {
            buttonCounter += 1;
            return tempButton;
        }).forEachOrdered((tempButton) -> {
            saPanel.add(tempButton);
        });

        saPanel.setLayout(new GridLayout(buttonCounter, 0));

        JButton backBtn = new JButton("Return To Main Menu");

        saPanel.add(backBtn);
        saFrame.add(saPanel);

        saFrame.setVisible(true);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                mainMenu();
            } // default action performed is a click
        });

    }

    public void displayTestMenu() {
        final JFrame saFrame = new JFrame();
        JPanel saPanel = new JPanel();
        JLabel saLabel = new javax.swing.JLabel();
        JButton btnPassenger = new javax.swing.JButton();
        JButton btnExit = new javax.swing.JButton();
        JButton btnBack = new javax.swing.JButton();

        saFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        saFrame.setName("saFrame"); // NOI18N

        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setSize(390, 420);
        saFrame.getContentPane().setLayout(null);

        saLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/tbico.png"))); // NOI18N
        saFrame.getContentPane().add(saLabel);
        saLabel.setBounds(50, 0, 278, 85);

        btnPassenger.setText("Add Passenger");
        saFrame.getContentPane().add(btnPassenger);
        btnPassenger.setBounds(90, 110, 220, 60);

        btnBack.setText("Back");
        saFrame.getContentPane().add(btnBack);
        btnBack.setBounds(30, 300, 100, 60);

        btnExit.setText("Exit");
        saFrame.getContentPane().add(btnExit);
        btnExit.setBounds(260, 300, 100, 60);

        saFrame.add(saPanel);
        saFrame.add(btnPassenger);
        saFrame.add(btnBack);
        saFrame.add(btnExit);
        saFrame.setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                mainMenu();
            } //default action performed is a click
        });
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.dispose();
                scotiaAirline.EmptyDB();
                scotiaAirline.SaveSeatsToDB();
                scotiaAirline.SavePassengersToDB();
                System.exit(0);
            }
        });
    }

    //Displays Change Flight Status Menu
    public void DisplayChangeStatusMenu(final String FlightInfo) {
        //create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        //sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);
        saFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //need at least one panel on a window, default will be full size of frame
        JPanel saPanel = new JPanel();

        saPanel.setLayout(new GridLayout(6, 1));

        JLabel title = new JLabel("Change Flight Status", SwingConstants.CENTER);

        JButton btnSeatsAvailable = new JButton("Seats Available");
        JButton btnCheckingIn = new JButton("Checking In");
        JButton btnBoarding = new JButton("Boarding");
        JButton btnFlightClosed = new JButton("Closed");
        JButton back = new JButton("Back To Flight Selection");

        //add components to panel then panel to frame
        //order which items are added is the order that they will appear
        saPanel.add(title);
        saPanel.add(btnSeatsAvailable);
        saPanel.add(btnCheckingIn);
        saPanel.add(btnBoarding);
        saPanel.add(btnFlightClosed);
        saPanel.add(back);
        saFrame.add(saPanel);

        saFrame.setVisible(true);

        ////////////////////////////////////////Action Listeners///////////////////////////////////////////////////////////////		
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                selectFlight("ChangeFlightStatus");
            } // default action performed is a click
        });

        btnSeatsAvailable.addActionListener(new ActionListener() {

            //splits by space
            String[] parsedFlightInfo = FlightInfo.split("\\s+");

            Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

            // default action performed is a click
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean boarding;// = chosenFlight.isBoarding();
                boolean closed;// = chosenFlight.isClosed();
                boolean checkingIn;// = chosenFlight.isCheckingIn();

                boarding = false;
                closed = false;
                checkingIn = false;

                if (chosenFlight.isFull() == false) {
                    chosenFlight.setBoarding(boarding);
                    chosenFlight.setCheckingIn(closed);
                    chosenFlight.setClosed(checkingIn);
                    chosenFlight.setStatusMessage(parsedFlightInfo[1] + " Seats Available");

                    genericOutput("Seats now available on flight " + parsedFlightInfo[1]);
                } else {
                    genericOutput("Error, flight " + parsedFlightInfo[1] + " is full");
                }

            }
        });
        btnCheckingIn.addActionListener(new ActionListener() {

            //splits by space
            String[] parsedFlightInfo = FlightInfo.split("\\s+");

            Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

            // default action performed is a click
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean boarding;// = chosenFlight.isBoarding();
                boolean closed;// = chosenFlight.isClosed();
                boolean checkingIn;// = chosenFlight.isCheckingIn();

                boarding = false;
                closed = false;
                checkingIn = true;

                chosenFlight.setBoarding(boarding);
                chosenFlight.setCheckingIn(checkingIn);
                chosenFlight.setClosed(closed);
                chosenFlight.setStatusMessage("Flight: " + parsedFlightInfo[1] + " is Checking In");

                genericOutput(parsedFlightInfo[1] + " is now checking in");

            }
        });
        btnFlightClosed.addActionListener(new ActionListener() {

            //splits by space
            String[] parsedFlightInfo = FlightInfo.split("\\s+");

            Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

            // default action performed is a click
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean boarding;// = chosenFlight.isBoarding();
                boolean closed;// = chosenFlight.isClosed();
                boolean checkingIn;// = chosenFlight.isCheckingIn();

                boarding = false;
                closed = true;
                checkingIn = false;

                chosenFlight.setBoarding(boarding);
                chosenFlight.setCheckingIn(checkingIn);
                chosenFlight.setClosed(closed);
                chosenFlight.setStatusMessage("Flight: " + parsedFlightInfo[1] + " is Closed");

                genericOutput(parsedFlightInfo[1] + " is now closed");

            }
        });

        btnBoarding.addActionListener(new ActionListener() {

            //splits by space
            String[] parsedFlightInfo = FlightInfo.split("\\s+");

            Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

            // default action performed is a click
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean boarding;// = chosenFlight.isBoarding();
                boolean closed;// = chosenFlight.isClosed();
                boolean checkingIn;// = chosenFlight.isCheckingIn();

                boarding = true;
                closed = true;
                checkingIn = false;

                chosenFlight.setBoarding(boarding);
                chosenFlight.setCheckingIn(checkingIn);
                chosenFlight.setClosed(closed);
                chosenFlight.setStatusMessage("Flight: " + parsedFlightInfo[1] + " Flight is Boarding");

                genericOutput(parsedFlightInfo[1] + " is now boarding");

            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                selectFlight("ChangeFlightStatus");
            } // default action performed is a click
        });

    }

    //Displays Seating Plan of specified flight
    public void DisplaySeatPlan(final String FlightInfo) {

    }

    //Displays Booking Menu
    public void DisplayBookingMenu(final String FlightInfo) {
        //create frame object and size
        //Sets Icons for Seats
        //   Icon free = (new javax.swing.ImageIcon(getClass().getResource("/assets/seatFree.png")));
        //  Icon reserved = (new javax.swing.ImageIcon(getClass().getResource("/assets/seatReserved.png")));
        //  Icon booked = (new javax.swing.ImageIcon(getClass().getResource("/assets/seatBooked.png")));

        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        //sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);
        saFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //need at least one panel on a window, default will be full size of frame
        JPanel saPanel = new JPanel();

        saPanel.setLayout(new GridLayout(6, 1));

        JLabel title = new JLabel("Flight Information", SwingConstants.CENTER);

        JButton cancel = new JButton("Cancel Booking/Reservation");
        JButton reserve = new JButton("Reserve A Seat");
        JButton booking = new JButton("Book A Seat");
        JButton available = new JButton("View Available Seats");
        available.setEnabled(false);
        JButton back = new JButton("Back To Flight Selection");

////////////////////////////////////////Action Listeners///////////////////////////////////////////////////////////////		
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                selectFlight("Booking");
            } // default action performed is a click
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //splits by space
                String[] parsedFlightInfo = FlightInfo.split("\\s+");

                Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

                boolean boarding = chosenFlight.isBoarding();
                boolean closed = chosenFlight.isClosed();

                if (boarding == true || closed == true) {
                    genericOutput("Cancellations not available. " + chosenFlight.getStatusMessage());

                    return;
                }

                getSeatno(parsedFlightInfo[1], 1);
                saFrame.dispose();
            } // default action performed is a click
        });

        reserve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //splits by space
                String[] parsedFlightInfo = FlightInfo.split("\\s+");

                Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

                boolean boarding = chosenFlight.isBoarding();
                boolean closed = chosenFlight.isClosed();
                boolean full = chosenFlight.isFull();
                boolean checkingIn = chosenFlight.isCheckingIn();

                if (boarding == true || closed == true || full == true || checkingIn == true) {
                    genericOutput("Reservations not available. " + chosenFlight.getStatusMessage());

                    return;
                }

                getSeatno(parsedFlightInfo[1], 2);
                saFrame.dispose();
            } // default action performed is a click
        });

        booking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //splits by space
                //use to get the flight number from flight info
                String[] parsedFlightInfo = FlightInfo.split("\\s+");

                Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

                boolean boarding = chosenFlight.isBoarding();
                boolean closed = chosenFlight.isClosed();
                boolean full = chosenFlight.isFull();

                if (boarding == true || closed == true || full == true) {

                    genericOutput("Bookings not available. " + chosenFlight.getStatusMessage());

                    return;
                }

                getSeatno(parsedFlightInfo[1], 3);
                saFrame.dispose();
            } // default action performed is a click
        });

        available.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //splits by space
                String[] parsedFlightInfo = FlightInfo.split("\\s+");
                Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

                //Shows Interface with a seat plan of the selected flight number
                saFrame.dispose();
            } // default action performed is a click

        });

        //add components to panel then panel to frame
        //order which items are added is the order that they will appear
        saPanel.add(title);
        saPanel.add(cancel);
        saPanel.add(reserve);
        saPanel.add(booking);
        saPanel.add(available);
        saPanel.add(back);
        saFrame.add(saPanel);

        saFrame.setVisible(true);

    }

    //Gets Seat Number to check wether seat is either Free | Booked | Reserved
    public void getSeatno(final String flightNo, final int choice) {

        String labelDisplay = "";
        String buttonDisplay = "";

        // create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        // sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);
        saFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // need at least one panel on a window, default will be full size of
        // frame
        JPanel saPanel = new JPanel();
        saPanel.setLayout(new GridLayout(4, 1));

        //tailors text in frame dependent on requested action
        switch (choice) {

            case 1:
                labelDisplay = "To Cancel";
                buttonDisplay = "Cancellation";
                break;

            case 2:
                labelDisplay = "To Reserve";
                buttonDisplay = "Reservation";
                break;

            case 3:
                labelDisplay = "To Book";
                buttonDisplay = "Booking";
                break;

            case 4:
                labelDisplay = "To View";
                buttonDisplay = " ";
                break;

            case -1:
                break;

        }//end switch

        JLabel heading = new JLabel("Enter Seat No (e.g. 1A) " + labelDisplay, SwingConstants.CENTER);

        final JTextField inputText = new JTextField();

        JButton btnSubmit = new JButton("Submit " + buttonDisplay);
        JButton btnBack = new JButton("Back to Flight Selection");

        saPanel.add(heading);
        saPanel.add(inputText);
        saPanel.add(btnSubmit);
        saPanel.add(btnBack);

        saFrame.add(saPanel);
        saFrame.setVisible(true);

//////////////////////////////////////////////////Action Listeners////////////////////////////////////////////////////////	
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String seatNo = inputText.getText();

                Flight currentFlight = scotiaAirline.getFlights(flightNo);

                if (currentFlight.IsValidSeatNumber(seatNo) == false) {
                    genericOutput("<html>'" + seatNo + "' is not a valid seat number<br>"
                            + " Please make sure you have typed it correctly"
                            + "<br> The correct format is"
                            + "<br> 'Number:Letter'"
                            + "<br> <br> The seat number may also not exist");
                } else {
                    //determine status of seat after receiving passenger choice
                    switch (choice) {
                        case 1: {
                            int updateCounters = -1;
                            Seat tempSeat = scotiaAirline.getSeat(flightNo, seatNo);
                            switch (tempSeat.getCurrentStatus()) {
                                case 1:
                                    updateCounters = -1; //update seat counters
                                    tempSeat.setCurrentStatus(1);
                                    currentFlight.updateSeat(updateCounters);
                                    currentFlight.CalculateTotalFlightTakings(); //recalculate total flight takings
                                    genericOutput("Error " + tempSeat.getSeatNumber() + "Seat Is Already Free");
                                    break;
                                case 2:
                                    updateCounters = 1;
                                    tempSeat.setCurrentStatus(1);
                                    currentFlight.updateSeat(updateCounters);
                                    tempSeat.setaPassenger(null);
                                    currentFlight.CalculateTotalFlightTakings();
                                    genericOutput(tempSeat.getSeatNumber() + " Has Been Cancelled");
                                    break;
                                //end of nested if
                                case 3:
                                    updateCounters = 2;
                                    tempSeat.setCurrentStatus(1);
                                    currentFlight.updateSeat(updateCounters);
                                    tempSeat.setaPassenger(null);
                                    currentFlight.CalculateTotalFlightTakings();
                                    genericOutput(tempSeat.getSeatNumber() + " Has Been Cancelled - No Refund");
                                    break;
                                default:
                                    break;
                            }
                            break;
                        }
                        case 2:
                        case 3:
                            getPassengerDetails(flightNo, choice, seatNo);
                            break;
                        case 4: {
                            Seat tempSeat = scotiaAirline.getSeat(flightNo, seatNo);
                            genericOutput(tempSeat.DisplaySeatDetails());
                            break;
                        }
                        default:
                            break;
                    }
                    saFrame.setVisible(false);
                    saFrame.dispose();

                }
            } // default action performed is a click
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                mainMenu();
            } // default action performed is a click
        });

    }

    //Gets passengers information
    public void getPassengerDetails(final String flightNumber, final int bookingChoice, final String seatNo) {

        //create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        //sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);
        saFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //need at least one panel on a window, default will be full size of frame
        JPanel saPanel = new JPanel();

        saPanel.setLayout(new GridLayout(6, 1));

        JLabel enterPassengerName = new JLabel("Enter Passenger Name", SwingConstants.CENTER);
        final JTextField txtPassengerName = new JTextField();

        JButton btnLocal = new JButton("Local");
        JButton btnBusiness = new JButton("Business");
        JButton btnOrdinary = new JButton("Ordinary");
        JButton btnBack = new JButton("Back To Seat Selection");

        saPanel.add(enterPassengerName);
        saPanel.add(txtPassengerName);
        saPanel.add(btnLocal);
        saPanel.add(btnBusiness);
        saPanel.add(btnOrdinary);
        saPanel.add(btnBack);

        saFrame.add(saPanel);
        saFrame.setVisible(true);

//////////////////////////////Action Listeners//////////////////////////////////////////////
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.dispose();
                getSeatno(flightNumber, bookingChoice);
            } // default action performed is a click
        });

        btnBusiness.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.dispose();
                String passengerName = txtPassengerName.getText();//.toString();
                getCompany(flightNumber, bookingChoice, seatNo, passengerName);
            } // default action performed is a click
        });

        btnLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.dispose();
                String passengerName = txtPassengerName.getText();//.toString();
                getResidence(flightNumber, bookingChoice, seatNo, passengerName);
            } // default action performed is a click
        });

        btnOrdinary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.dispose();
                String passengerName = txtPassengerName.getText();//.toString();
                getPromo(flightNumber, bookingChoice, seatNo, passengerName);
            } // default action performed is a click
        });

    }

    //Gets the name of the Passengers Island of Residence
    public void getResidence(final String flightNumber, final int bookingChoice, final String seatNo, final String passengerName) {

        // create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        // sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);
        saFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // need at least one panel on a window, default will be full size of
        // frame
        JPanel saPanel = new JPanel();
        saPanel.setLayout(new GridLayout(4, 1));

        JLabel enterIslandOfResidence = new JLabel("Enter Island of Residence", SwingConstants.CENTER);
        final JTextField txtIslandOfResidence = new JTextField();

        JButton btnSubmit = new JButton("Submit");
        JButton btnBack = new JButton("Back to Passenger Name");

        saPanel.add(enterIslandOfResidence);
        saPanel.add(txtIslandOfResidence);
        saPanel.add(btnSubmit);
        saPanel.add(btnBack);

        saFrame.add(saPanel);
        saFrame.setVisible(true);

////////////////Action Listeners/////////////////////////////////////////////////////////////////////////////
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                getPassengerDetails(flightNumber, bookingChoice, seatNo);
            } // default action performed is a click
        });

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.dispose();
                saFrame.setVisible(false);
                String island = txtIslandOfResidence.getText();//.toString();
                PassengerWestern newPassenger = new PassengerWestern(passengerName, island);
                Flight currentFlight = scotiaAirline.getFlights(flightNumber);
                Seat passengerSeat;// = new Seat();
                passengerSeat = scotiaAirline.getSeat(flightNumber, seatNo);
                int fullSeatStatus = passengerSeat.changeSeatStatus(scotiaAirline, bookingChoice, newPassenger, currentFlight);
                currentFlight.updateSeat(fullSeatStatus);
                currentFlight.CalculateTotalFlightTakings();
            } // default action performed is a click
        });

    }

    //Gets information on the company name for the companies discount
    public void getCompany(final String flightNumber, final int bookingChoice, final String seatNo, final String passengerName) {

        // create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        // sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);
        saFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // need at least one panel on a window, default will be full size of
        // frame
        JPanel saPanel = new JPanel();
        saPanel.setLayout(new GridLayout(4, 1));

        JLabel enterCompanyName = new JLabel("Enter Company Name", SwingConstants.CENTER);
        final JTextField txtCompanyName = new JTextField();

        JButton btnSubmit = new JButton("Submit");
        JButton btnBack = new JButton("Back to Passenger Name");

        saPanel.add(enterCompanyName);
        saPanel.add(txtCompanyName);
        saPanel.add(btnSubmit);
        saPanel.add(btnBack);

        saFrame.add(saPanel);
        saFrame.setVisible(true);

////////////////Action Listeners//////////////////////////////////////////////
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.dispose();
                getPassengerDetails(flightNumber, bookingChoice, seatNo);
            } // default action performed is a click
        });

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.dispose();
                saFrame.setVisible(false);
                String company = txtCompanyName.getText();//.toString();
                PassengerBusiness newPassenger = new PassengerBusiness(passengerName, company);
                Flight currentFlight = scotiaAirline.getFlights(flightNumber);
                Seat passengerSeat;// = new Seat();
                passengerSeat = scotiaAirline.getSeat(flightNumber, seatNo);
                int fullSeatStatus = passengerSeat.changeSeatStatus(scotiaAirline, bookingChoice, newPassenger, currentFlight);
                currentFlight.updateSeat(fullSeatStatus);
                currentFlight.CalculateTotalFlightTakings();
            } // default action performed is a click
        });
    }

    //Checks with Ordinary Passenger if the booking is part of a promotion
    public void getPromo(final String flightNumber, final int bookingChoice, final String seatNo, final String passengerName) {

        // create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        // sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);
        saFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // need at least one panel on a window, default will be full size of
        // frame
        JPanel saPanel = new JPanel();
        saPanel.setLayout(new GridLayout(4, 1));

        JLabel isPromo = new JLabel("Is This Part Of A Promotion? (y/n)", SwingConstants.CENTER);
        final JTextField txtPromo = new JTextField();

        JButton btnSubmit = new JButton("Submit");
        JButton btnBack = new JButton("Back to Passenger Name");

        saPanel.add(isPromo);
        saPanel.add(txtPromo);
        saPanel.add(btnSubmit);
        saPanel.add(btnBack);

        saFrame.add(saPanel);
        saFrame.setVisible(true);

        ////////////////Action Listeners//////////////////////////////////////////////
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.dispose();
                saFrame.setVisible(false);
                getPassengerDetails(flightNumber, bookingChoice, seatNo);
            } // default action performed is a click
        });

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.dispose();
                saFrame.setVisible(false);
                String promo = txtPromo.getText();//.toString();
                PassengerOrdinary newPassenger = new PassengerOrdinary(passengerName, promo.charAt(0));
                Flight currentFlight = scotiaAirline.getFlights(flightNumber);
                Seat passengerSeat;// = new Seat();
                passengerSeat = scotiaAirline.getSeat(flightNumber, seatNo);
                int fullSeatStatus = passengerSeat.changeSeatStatus(scotiaAirline, bookingChoice, newPassenger, currentFlight);
                currentFlight.updateSeat(fullSeatStatus);
                currentFlight.CalculateTotalFlightTakings();
            } // default action performed is a click
        });
    }

}
