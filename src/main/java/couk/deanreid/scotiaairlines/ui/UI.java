/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 2.8
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.ui;

import couk.deanreid.scotiaairlines.ScotiaAirline;
import couk.deanreid.scotiaairlines.core.*;
import couk.deanreid.scotiaairlines.handler.NotificationHandler;
import couk.deanreid.scotiaairlines.utils.LogHelper;
import couk.deanreid.scotiaairlines.utils.Reference;
import java.awt.AWTException;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.jdesktop.swingx.JXDatePicker;

public class UI {

    private int buttonCounter = 2;
    private final Airline scotiaAirline;

    /**
     * UI Class Constructor
     *
     * @param scotiaAir
     */
    public UI(Airline scotiaAir) {
        scotiaAirline = scotiaAir;
    }

    /**
     * Generic Popup for displaying information
     *
     * @param inputMessage
     */
    public void genericPopup(String inputMessage) {
        final JFrame saFrame = new JFrame();
        saFrame.setSize(350, 200);
        saFrame.setLocationRelativeTo(null);
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setTitle(Reference.PROG_NAME);

        JLabel saTitle = new JLabel();
        saTitle.setFont(new java.awt.Font("Arial", 1, 18));
        saTitle.setIcon(new ImageIcon(getClass().getResource("/icon.png")));
        saTitle.setText(Reference.PROG_NAME + " Booking System");

        JPanel saPanel = new JPanel();
        JLabel message = new JLabel(inputMessage);
        JButton backBtn = new JButton("Main Menu");

        Container con1 = new Container();
        con1.setLayout(new GridLayout(1, 0));
        con1.add(message);
        saPanel.add(con1);

        Container con2 = new Container();
        con2.setLayout(new GridLayout(1, 0));
        con2.add(backBtn);
        saPanel.add(con2);

        saFrame.add(saPanel);
        saFrame.setVisible(true);

        backBtn.addActionListener((ActionEvent e) -> {
            saFrame.dispose();
            mainMenu();
        });
    }

    /**
     * Displays Main Menu
     */
    public void mainMenu() {
        //create frame object and size.
        final JFrame saFrame = new JFrame();

        //Frame Settings
        saFrame.setTitle(Reference.PROG_NAME + " - Main Menu | Version: " + Reference.VERSION_NUMBER);
        saFrame.setSize(390, 420);
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setLocationRelativeTo(null);
        saFrame.getContentPane().setLayout(null);
        saFrame.setIconImage(new ImageIcon("icon.png").getImage());
        saFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ScotiaAirline.windowClosing(evt);
            }
        });
        //need atleast one panel on a window, default will be full size of frame
        JPanel saPanel = new JPanel();

        JLabel saLabel = new JLabel(new ImageIcon(getClass().getResource("/mainmenu.png")), SwingConstants.CENTER);
        saFrame.getContentPane().add(saLabel);
        saLabel.setBounds(0, 0, 378, 100);

//setBounds(X,Y,Width,Height
        JButton testMenuBtn = new JButton();
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

        testMenuBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            displayTestMenu();
        });

        flightAdminBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            adminMenu();
        });

        bookingMenuBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            selectFlight("Booking");
        });

        displayFlightDetailsBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            selectFlight("DisplayFlightDetails");
        });

        displaySeatDetailsBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            selectFlight("DisplaySeatDetails");
        });

        exitBtn.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(exitBtn, "Please Wait while changes are saved!");
            saFrame.dispose();
            scotiaAirline.emptyDB();
            scotiaAirline.saveSeatsToDB();
            scotiaAirline.savePassengersToDB();
            Runtime.getRuntime().exit(0);
        });
    }

    /**
     * Displays Administrative Menu
     */
    public void adminMenu() {
        final JFrame saFrame = new JFrame();
        saFrame.setBackground(new java.awt.Color(255, 255, 255));
        saFrame.setSize(310, 350);
        saFrame.setLocationRelativeTo(null);
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setTitle(Reference.PROG_NAME + " - Admin Menu | Version: " + Reference.VERSION_NUMBER);
        saFrame.setLayout(null);
        saFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ScotiaAirline.windowClosing(evt);
            }
        });

        JLabel saTitle = new JLabel(new ImageIcon(getClass().getResource("/adminmenu.png")), SwingConstants.CENTER);
        saTitle.setBounds(0, 0, 278, 85);
        saFrame.add(saTitle);

        //Add Flight
        JButton addFlightBtn = new JButton("Add Flight");
        //addFlightBtn.setEnabled(false);
        saFrame.getContentPane().add(addFlightBtn);
        addFlightBtn.setBounds(10, 91, 122, 78);

        //Delete Flight
        JButton deleteFlightBtn = new JButton("<html>Delete Flight - <br> Very Buggy</html>");
        //deleteFlightBtn.setEnabled(false);
        saFrame.getContentPane().add(deleteFlightBtn);
        deleteFlightBtn.setBounds(138, 91, 140, 78);

        //Change Flight Status
        JButton changeFlightStatusBtn = new JButton("Change Flight Status");
        //changeFlightStatusBtn.setEnabled(false);
        saFrame.getContentPane().add(changeFlightStatusBtn);
        changeFlightStatusBtn.setBounds(72, 175, 150, 78);

        //Back Button
        JButton backBtn = new JButton("Back");
        saFrame.getContentPane().add(backBtn);
        backBtn.setBounds(10, 259, 100, 34);

        //Exit Button
        JButton exitBtn = new JButton("Exit");
        saFrame.getContentPane().add(exitBtn);
        exitBtn.setBounds(188, 259, 100, 34);

        saFrame.setVisible(true);

////////////////////Event Handlers///////////////////        
        addFlightBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            addNewFlight();
        });

        deleteFlightBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            deleteFlight("DeleteFlight");
        });

        changeFlightStatusBtn.addActionListener((ActionEvent e) -> {
            // JOptionPane.showMessageDialog(changeFlightStatusBtn, "Not Implemented Yet");
            saFrame.setVisible(false);
            saFrame.dispose();
            selectFlight("ChangeFlightStatus");
        });

        backBtn.addActionListener((ActionEvent e) -> {
            saFrame.dispose();
            mainMenu();
        });

        exitBtn.addActionListener((ActionEvent e) -> {
            saFrame.dispose();
            Runtime.getRuntime().exit(0);
        });

    }

    /**
     * Displays Panel for adding a new flight
     */
    public void addNewFlight() {
        final JFrame saFrame = new JFrame();
        saFrame.setBackground(new java.awt.Color(255, 255, 255));
        saFrame.setSize(321, 360);
        saFrame.setLocationRelativeTo(null);
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setTitle(Reference.PROG_NAME + " - Create Flight | Version: " + Reference.VERSION_NUMBER);
        saFrame.setLayout(null);
        saFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ScotiaAirline.windowClosing(evt);
            }
        });
        //Set image title                  
        JLabel saTitle = new JLabel(new ImageIcon(getClass().getResource("/adminmenu.png")), SwingConstants.CENTER);
        saTitle.setBounds(0, 0, 278, 85);
        saFrame.add(saTitle);

        //Create Flight
        JButton createFlightBtn = new JButton("Create Flight");
        //showSeatPlanBtn.setEnabled(false);
        saFrame.getContentPane().add(createFlightBtn);
        createFlightBtn.setBounds(120, 280, 162, 34);
        //Clear Button
        JButton clearBtn = new JButton("Clear Entry");
        saFrame.getContentPane().add(clearBtn);
        clearBtn.setBounds(90, 240, 100, 34);
        //Back Button
        JButton backBtn = new JButton("Back");
        saFrame.getContentPane().add(backBtn);
        backBtn.setBounds(10, 280, 100, 34);

        JLabel flightID = new JLabel("Flight Number ");
        flightID.setBounds(10, 110, 90, 30);
        flightID.setFont(new java.awt.Font("Tahoma", 0, 14));
        saFrame.getContentPane().add(flightID);

        final JTextField txtFlightID = new JTextField(10);
        txtFlightID.setBounds(100, 110, 60, 30);
        saFrame.getContentPane().add(txtFlightID);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(165, 110, 90, 30);
        dateLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
        saFrame.getContentPane().add(dateLabel);

        final JXDatePicker txtDatePick = new JXDatePicker();
        txtDatePick.setBounds(200, 110, 105, 30);
        txtDatePick.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtDatePick.setFormats(new String[]{"dd-MM-yyyy"});
        saFrame.getContentPane().add(txtDatePick);

        JLabel departure = new JLabel("Departure ");
        departure.setBounds(10, 150, 90, 30);
        departure.setFont(new java.awt.Font("Tahoma", 0, 14));
        saFrame.getContentPane().add(departure);

        final JTextField txtDeparture = new JTextField(10);
        txtDeparture.setBounds(80, 150, 60, 30);
        saFrame.getContentPane().add(txtDeparture);

        JLabel arrival = new JLabel("Arrival: ");
        arrival.setBounds(150, 150, 90, 30);
        arrival.setFont(new java.awt.Font("Tahoma", 0, 14));
        saFrame.getContentPane().add(arrival);

        final JTextField txtArrival = new JTextField(10);
        txtArrival.setBounds(200, 150, 60, 30);
        saFrame.getContentPane().add(txtArrival);

        JLabel rows = new JLabel("Rows: ");
        rows.setBounds(150, 190, 90, 30);
        rows.setFont(new java.awt.Font("Tahoma", 0, 14));
        saFrame.getContentPane().add(rows);

        final JTextField txtRows = new JTextField(10);
        txtRows.setBounds(200, 190, 60, 30);
        saFrame.getContentPane().add(txtRows);

        JLabel columns = new JLabel("Columns: ");
        columns.setBounds(10, 190, 90, 30);
        columns.setFont(new java.awt.Font("Tahoma", 0, 14));
        saFrame.getContentPane().add(columns);

        final JTextField txtColumns = new JTextField(10);
        txtColumns.setBounds(80, 190, 60, 30);
        saFrame.getContentPane().add(txtColumns);

        saFrame.setVisible(true);

////////////////////Event Handlers///////////////////                      
        backBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            adminMenu();
        });

        clearBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            addNewFlight();
        });

        //submits the entered flight details
        createFlightBtn.addActionListener((ActionEvent e) -> {
            String flightNo = txtFlightID.getText();
            String departure1 = txtDeparture.getText();
            String arrival1 = txtArrival.getText();
            int rows1 = Integer.parseInt(txtRows.getText());
            int columns1 = Integer.parseInt(txtColumns.getText());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            txtDatePick.setFormats(dateFormat);
            DateFormat sysDate = new SimpleDateFormat("yyyy-MM-dd");
            String date_to_store = sysDate.format(txtDatePick.getDate());

            Flight newFlight = new Flight(flightNo, departure1, arrival1, rows1, columns1, date_to_store);
            try {
                newFlight.addFlightToDB();
            } catch (AWTException | MalformedURLException | ParseException | NumberFormatException ex) {
                LogHelper.fatal(ex);
                LogHelper.fatal("Number Format");
            }
            scotiaAirline.addFlight(newFlight);
            saFrame.setVisible(false);
            saFrame.dispose();
            mainMenu();
            try {
                //display added flight
                NotificationHandler.notify("Flight Added", "Flight: '" + flightNo + "' Added Successfully");
            } catch (AWTException | MalformedURLException ex) {
                try {
                    NotificationHandler.notify("FLIGHT ERROR", "Flight Failed to add, See Log");
                } catch (AWTException | MalformedURLException | NumberFormatException ex1) {
                    LogHelper.fatal(ex);
                }
                LogHelper.fatal(ex);
            }
        });
    }

    /**
     * Deletes Selected Flight. TODO: Framework, SQL Methods.
     *
     * @param status
     */
    public void deleteFlight(final String status) {
        buttonCounter = 2;
        //create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(445, 460);
        saFrame.setTitle(Reference.PROG_NAME + " - Delete Flight | Version: " + Reference.VERSION_NUMBER);
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setLocationRelativeTo(null);
        saFrame.setLayout(null);
        saFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ScotiaAirline.windowClosing(evt);
            }
        });

        JLabel title = new JLabel(new ImageIcon(getClass().getResource("/adminmenu.png")));
        title.setBounds(80, -10, 259, 100);
        saFrame.add(title);

        JPanel saPanel = new JPanel();
        saPanel.setBounds(0, 90, 450, 310);

        //displaying all flights in hashmap
        scotiaAirline.getFlights().entrySet().stream().map((currentFlight) -> {
            JButton tempButton = new JButton("No: " + currentFlight.getValue().getFlightNumber() + " Depature: "
                    + currentFlight.getValue().getDeparture() + " Arrival: " + currentFlight.getValue().getArrival());
            tempButton.addActionListener((ActionEvent e) -> {

                if (status.equalsIgnoreCase("DeleteFlight")) {
                    LogHelper.info("This is really broken, Kinda breaks things.. best not to use it");
                    /*try {
                        String deleteFlight = "DELETE FROM Flight WHERE FlightID ='" + currentFlight.getValue().getFlightNumber()+"'";
                        String deleteSeat = "DELETE FROM Seat WHERE FlightID ='" + currentFlight.getValue().getFlightNumber()+"'";
                        String deletePassenger = "DELETE FROM Passenger WHERE FlightID ='" + currentFlight.getValue().getFlightNumber()+"'";
                        PreparedStatement preparedStatement;
                        Connection connection = DBProxy.getConnection();
                        try {       
                            NotificationHandler.Notify("Flight '" + currentFlight.getValue().getFlightNumber() + "' Deleted", "Flight has been deleted succesfully.");
                        } catch (AWTException | MalformedURLException ex) {
                            LogHelper.fatal(ex);
                        }
                        saFrame.setVisible(false);
                        saFrame.dispose();
                        mainMenu(); 
                    } catch (SQLException ex) {
                        LogHelper.fatal(ex);
                    }*/
                    mainMenu();
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

        backBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            mainMenu();
        });

    }

    /**
     * Method for selecting a flight Clicking on specified flight will direct
     * user to appropriate screen depending on which status is passed
     *
     * @param status
     */
    public void selectFlight(final String status) {
        buttonCounter = 2;
        //create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(600, 600);
        saFrame.setTitle(Reference.PROG_NAME + " - Select Flight | Version: " + Reference.VERSION_NUMBER);
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setLocationRelativeTo(null);
        saFrame.setLayout(null);
        saFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ScotiaAirline.windowClosing(evt);
            }
        });

        JLabel title = new JLabel(new ImageIcon(getClass().getResource("/tbselectflight.png")));
        title.setBounds(150, -10, 259, 100);
        saFrame.add(title);

        JPanel saPanel = new JPanel();
        saPanel.setBounds(10, 90, 560, 500);
        //displaying all flights in hashmap
        scotiaAirline.getFlights().entrySet().stream().map((currentFlight) -> {
            JButton tempButton = new JButton(
                    "FlightNo: "
                    + currentFlight.getValue().getFlightNumber()
                    + "  Depature: "
                    + currentFlight.getValue().getDeparture()
                    + "  Arrival: "
                    + currentFlight.getValue().getArrival()
                    + "  Date: "
                    + currentFlight.getValue().getDate()
            );

            tempButton.addActionListener((ActionEvent e) -> {
                if (status.equalsIgnoreCase("Booking")) {
                    saFrame.dispose();
                    displayBookingMenu(e.getActionCommand());
                } else if (status.equalsIgnoreCase("DisplayFlightDetails")) {
                    saFrame.dispose();
                    Flight tempFlight = currentFlight.getValue();
                    genericPopup(tempFlight.displayFlightInfo());
                } else if (status.equalsIgnoreCase("TestMenu")) {
                    saFrame.dispose();
                    bookPassenger(e.getActionCommand(), e.getID(), e.paramString());
                } else if (status.equalsIgnoreCase("DisplaySeatDetails")) {
                    saFrame.dispose();
                    getSeatno(currentFlight.getValue().getFlightNumber(), 4);
                } else if (status.equalsIgnoreCase("DisplaySeatMap")) {
                    saFrame.dispose();
                    displaySeatMap(currentFlight.getValue().getFlightNumber());
                } else if (status.equalsIgnoreCase("ChangeFlightStatus")) {
                    saFrame.dispose();
                    displayChangeStatusMenu(e.getActionCommand());
                }
            });
            return tempButton;
        }).map((tempButton) -> {
            buttonCounter += 1;
            return tempButton;
        }).forEachOrdered((tempButton) -> {
            saPanel.add(tempButton);
        });

        saPanel.setLayout(new GridLayout(buttonCounter, 1));
        saFrame.add(saPanel);

        JButton backBtn = new JButton("Main Menu");
        backBtn.setBounds(10, 10, 100, 30);
        saFrame.add(backBtn);

        saFrame.setVisible(true);

        backBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            mainMenu();
        });

    }

    /**
     * Volatile Menu.
     */
    public void displayTestMenu() {
        final JFrame saFrame = new JFrame();
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setSize(390, 420);
        saFrame.setLocationRelativeTo(null);
        saFrame.getContentPane().setLayout(null);
        saFrame.setTitle(Reference.PROG_NAME + " Developer Menu | Version: " + Reference.VERSION_NUMBER);

        JLabel saLabel = new JLabel();
        saLabel.setIcon(new ImageIcon(getClass().getResource("/testmenu.png")));
        saLabel.setBounds(50, 0, 278, 85);
        saFrame.getContentPane().add(saLabel);

        JButton btnPassenger = new JButton();
        btnPassenger.setText("View Flight Map");
        btnPassenger.setBounds(90, 110, 220, 60);
        saFrame.getContentPane().add(btnPassenger);

        JButton btnWipe = new JButton();
        btnWipe.setText("Force Clear Database");
        btnWipe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnWipe.setForeground(new java.awt.Color(255, 0, 51));
        btnWipe.setBounds(90, 175, 220, 60);
        saFrame.getContentPane().add(btnWipe);

        JButton btnBook = new JButton();
        btnBook.setText("New Booking Menu");
        btnBook.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBook.setForeground(new java.awt.Color(255, 100, 210));
        saFrame.getContentPane().add(btnBook);
        btnBook.setBounds(90, 240, 220, 60);

        JButton btnBack = new JButton();
        btnBack.setText("Back");
        btnBack.setBounds(30, 300, 100, 60);
        saFrame.getContentPane().add(btnBack);

        JButton btnExit = new JButton();
        btnExit.setText("Exit");
        btnExit.setBounds(260, 300, 100, 60);
        saFrame.getContentPane().add(btnExit);

        saFrame.setVisible(true);

        btnPassenger.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            selectFlight("DisplaySeatMap");
        });

        btnWipe.addActionListener((ActionEvent e) -> {
            if (SystemTray.isSupported()) {
                try {
                    JOptionPane.showMessageDialog(null, "<html>Cleaning the Database You cant undo this! <br>  The Program will now Exit!</html>");
                    NotificationHandler.notify(Reference.PROG_NAME + " Alert", "Clearing Database - Irreversible Action.");
                } catch (AWTException | MalformedURLException ex) {
                    LogHelper.fatal(ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cleaning the Database You cant undo this!");
            }
            saFrame.setVisible(false);
            saFrame.dispose();
            scotiaAirline.emptyDB();
            Runtime.getRuntime().exit(0);
        });

        btnBook.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            selectFlight("TestMenu");
        });

        btnBack.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            mainMenu();
        });
        btnExit.addActionListener((ActionEvent e) -> {
            saFrame.dispose();
            scotiaAirline.emptyDB();
            scotiaAirline.saveSeatsToDB();
            scotiaAirline.savePassengersToDB();
            Runtime.getRuntime().exit(0);
        });
    }

    /**
     * Displays Menu for Changing Flight Status
     *
     * @param FlightInfo
     */
    public void displayChangeStatusMenu(final String FlightInfo) {

        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        saFrame.setLocationRelativeTo(null);

        JPanel saPanel = new JPanel();
        saPanel.setLayout(new GridLayout(6, 1));

        JLabel title = new JLabel("Change Flight Status", SwingConstants.CENTER);
        saPanel.add(title);

        JButton btnSeatsAvailable = new JButton("Seats Available");
        saPanel.add(btnSeatsAvailable);
        JButton btnCheckingIn = new JButton("Checking In");
        saPanel.add(btnCheckingIn);
        JButton btnBoarding = new JButton("Boarding");
        saPanel.add(btnBoarding);
        JButton btnFlightClosed = new JButton("Closed");
        saPanel.add(btnFlightClosed);
        JButton back = new JButton("Back To Flight Selection");
        saPanel.add(back);

        saFrame.add(saPanel);
        saFrame.setVisible(true);

        ////////////////////////////////////////Action Listeners///////////////////////////////////////////////////////////////		
        btnSeatsAvailable.addActionListener(new ActionListener() {
            //splits by space
            String[] parsedFlightInfo = FlightInfo.split("\\s+");
            Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean boarding;
                boolean closed;
                boolean checkingIn;

                boarding = false;
                closed = false;
                checkingIn = false;

                if (chosenFlight.isFull() == false) {
                    chosenFlight.setBoarding(boarding);
                    chosenFlight.setCheckingIn(closed);
                    chosenFlight.setClosed(checkingIn);
                    chosenFlight.setStatusMessage(parsedFlightInfo[1] + " Seats Available");
                    try {
                        NotificationHandler.notify("Flight Information", "Seats now available on flight " + parsedFlightInfo[1]);
                    } catch (AWTException | MalformedURLException ex) {
                        genericPopup("Seats now available on flight " + parsedFlightInfo[1]);
                    }
                } else {
                    try {
                        NotificationHandler.notify("Flight Information", "Sorry, The flight: '" + parsedFlightInfo[1] + " is now full. ");
                    } catch (AWTException | MalformedURLException ex) {
                        genericPopup("Error, flight " + parsedFlightInfo[1] + " is full");
                    }
                }

            }
        });
        btnCheckingIn.addActionListener(new ActionListener() {
            //splits by space
            String[] parsedFlightInfo = FlightInfo.split("\\s+");
            Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean boarding;
                boolean closed;
                boolean checkingIn;

                boarding = false;
                closed = false;
                checkingIn = true;

                chosenFlight.setBoarding(boarding);
                chosenFlight.setCheckingIn(checkingIn);
                chosenFlight.setClosed(closed);
                chosenFlight.setStatusMessage("Flight: " + parsedFlightInfo[1] + " is Checking In");
                try {
                    NotificationHandler.notify("Flight Information", parsedFlightInfo[1] + " is now checking in");
                } catch (AWTException | MalformedURLException ex) {
                    genericPopup(parsedFlightInfo[1] + " is now checking in");
                }

            }
        });
        btnFlightClosed.addActionListener(new ActionListener() {
            //splits by space
            String[] parsedFlightInfo = FlightInfo.split("\\s+");
            Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean boarding;
                boolean closed;
                boolean checkingIn;

                boarding = false;
                closed = true;
                checkingIn = false;

                chosenFlight.setBoarding(boarding);
                chosenFlight.setCheckingIn(checkingIn);
                chosenFlight.setClosed(closed);
                chosenFlight.setStatusMessage("Flight: " + parsedFlightInfo[1] + " is Closed");
                try {
                    NotificationHandler.notify("Flight Information", parsedFlightInfo[1] + " is now closed");
                } catch (AWTException | MalformedURLException ex) {
                    genericPopup(parsedFlightInfo[1] + " is now closed");
                }
            }
        });
        btnBoarding.addActionListener(new ActionListener() {
            //splits by space
            String[] parsedFlightInfo = FlightInfo.split("\\s+");
            Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean boarding;
                boolean closed;
                boolean checkingIn;

                boarding = true;
                closed = true;
                checkingIn = false;

                chosenFlight.setBoarding(boarding);
                chosenFlight.setCheckingIn(checkingIn);
                chosenFlight.setClosed(closed);
                chosenFlight.setStatusMessage("Flight: " + parsedFlightInfo[1] + " Flight is Boarding");
                try {
                    NotificationHandler.notify("Flight Information", parsedFlightInfo[1] + " is now boarding");
                } catch (AWTException | MalformedURLException ex) {
                    genericPopup(parsedFlightInfo[1] + " is now boarding");
                }
            }
        });
        back.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            selectFlight("ChangeFlightStatus");
        });
    }

    /**
     * Displays Booking Menu
     *
     * @param FlightInfo
     */
    public void displayBookingMenu(final String FlightInfo) {
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setLocationRelativeTo(null);
        saFrame.setTitle(Reference.PROG_NAME + " Booking Menu | Version: " + Reference.VERSION_NUMBER);
        saFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ScotiaAirline.windowClosing(evt);
            }
        });

        JPanel saPanel = new JPanel();
        saPanel.setLayout(new GridLayout(6, 1));

        JLabel title = new JLabel("Flight Information", SwingConstants.CENTER);
        saPanel.add(title);

        JButton cancelFlight = new JButton("Cancel Booking/Reservation");
        saPanel.add(cancelFlight);
        JButton reserveFlight = new JButton("Reserve A Seat");
        saPanel.add(reserveFlight);
        JButton bookingFlight = new JButton("Book A Seat");
        saPanel.add(bookingFlight);
        JButton seatPlan = new JButton("View Seat Plan");
        seatPlan.setEnabled(false);
        saPanel.add(seatPlan);
        JButton back = new JButton("Back To Flight Selection");
        saPanel.add(back);

        saFrame.add(saPanel);
        saFrame.setVisible(true);

////////////////////////////////////////Action Listeners///////////////////////////////////////////////////////////////		
        cancelFlight.addActionListener((ActionEvent e) -> {
            //splits by space
            String[] parsedFlightInfo = FlightInfo.split("\\s+");

            Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

            boolean boarding = chosenFlight.isBoarding();
            boolean closed = chosenFlight.isClosed();

            if (boarding == true || closed == true) {
                try {
                    NotificationHandler.notify("Flight Error", "Cancellations not available " + chosenFlight.getStatusMessage());
                } catch (AWTException | MalformedURLException ex) {
                    genericPopup("Cancellations not available. " + chosenFlight.getStatusMessage());
                }
                return;
            }

            getSeatno(parsedFlightInfo[1], 1);
            saFrame.dispose();
        });
        reserveFlight.addActionListener((ActionEvent e) -> {
            //splits by space
            String[] parsedFlightInfo = FlightInfo.split("\\s+");

            Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);

            boolean boarding = chosenFlight.isBoarding();
            boolean closed = chosenFlight.isClosed();
            boolean full = chosenFlight.isFull();
            boolean checkingIn = chosenFlight.isCheckingIn();

            if (boarding == true || closed == true || full == true || checkingIn == true) {
                try {
                    NotificationHandler.notify("Flight Error", "Reservations not available " + chosenFlight.getStatusMessage());
                } catch (AWTException | MalformedURLException ex) {
                    genericPopup("Reservations not available. " + chosenFlight.getStatusMessage());
                }
                return;
            }

            getSeatno(parsedFlightInfo[1], 2);
            saFrame.dispose();
        });
        bookingFlight.addActionListener((ActionEvent e) -> {
            //splits by space
            //use to get the flight number from flight info
            String[] parsedFlightInfo = FlightInfo.split("\\s+");

            Flight chosenFlight = scotiaAirline.getFlights().get(parsedFlightInfo[1]);
            LogHelper.debug(Arrays.toString(parsedFlightInfo));
            LogHelper.debug(chosenFlight);
            boolean boarding = chosenFlight.isBoarding();
            boolean closed = chosenFlight.isClosed();
            boolean full = chosenFlight.isFull();

            if (boarding == true || closed == true || full == true) {

                try {
                    NotificationHandler.notify("Flight Error", "Bookings not available " + chosenFlight.getStatusMessage());
                } catch (AWTException | MalformedURLException ex) {
                    genericPopup("Bookings not available. " + chosenFlight.getStatusMessage());
                }
                return;

            }

            getSeatno(parsedFlightInfo[1], 3);
            saFrame.dispose();
        });
        seatPlan.addActionListener((ActionEvent e) -> {
            //Shows Interface with a seat plan of the selected flight number
            saFrame.setVisible(false);
            saFrame.dispose();
        });
        back.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            selectFlight("Booking");
        });
    }

    /**
     * Panel which asks for user to input a seat number on selected flight
     * Checks whether seat is valid and if it is Free | Booked | Reserved
     *
     * Will soon be deprecated and replaced by graphical screen showing seat
     * layout
     *
     * @param flightNo
     * @param choice
     */
    public void getSeatno(final String flightNo, final int choice) {

        String labelDisplay = "";
        String buttonDisplay = "";

        final JFrame saFrame = new JFrame();
        saFrame.setSize(330, 200);
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setLocationRelativeTo(null);
        saFrame.setTitle(Reference.PROG_NAME + " Select Seat | Version: " + Reference.VERSION_NUMBER);
        saFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ScotiaAirline.windowClosing(evt);
            }
        });

        JPanel saPanel = new JPanel();
        saPanel.setLayout(null);
        saFrame.add(saPanel);

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

            default:
                break;

        }

        JLabel heading = new JLabel("<html>Enter Seat Number to " + labelDisplay + "<br><b><center>Example (1A)</center></b></html>");
        heading.setBounds(75, 0, 200, 40);
        saPanel.add(heading);
        final JTextField inputText = new JTextField();
        inputText.setBounds(105, 50, 110, 40);
        saPanel.add(inputText);
        JButton btnSubmit = new JButton("Submit " + buttonDisplay);
        btnSubmit.setBounds(170, 110, 140, 40);
        saPanel.add(btnSubmit);
        JButton btnBack = new JButton("<html><center>Return to<br> Flight Selection</center></html>");
        btnBack.setBounds(10, 110, 140, 40);
        saPanel.add(btnBack);

        saFrame.setVisible(true);

//////////////////////////////////////////////////Action Listeners////////////////////////////////////////////////////////	
        btnSubmit.addActionListener((ActionEvent e) -> {
            String seatNo = inputText.getText();
            Flight currentFlight = scotiaAirline.getFlights(flightNo);

            if (!currentFlight.isValidSeatNumber(seatNo)) {
                try {
                    NotificationHandler.notify("Seat: '" + seatNo + "' Invalid", "Please type a valid number (EG. 1A)");
                } catch (AWTException | MalformedURLException ex) {
                    genericPopup("<html>" + Reference.TextPaint.RED + seatNo + Reference.TextPaint.RESET + " <br>is not a valid seat number Correct Format is Number:Letter");
                }
            } else {
                //determine status of seat after receiving passenger choice
                switch (choice) {
                    case 1: {
                        int updateCounters;
                        Seat tempSeat = scotiaAirline.getSeat(flightNo, seatNo);
                        switch (tempSeat.getCurrentStatus()) {
                            case 1:
                                updateCounters = -1; //update seat counters
                                tempSeat.setCurrentStatus(1);
                                currentFlight.updateSeat(updateCounters);
                                currentFlight.calculateTotalFlightTakings(); //recalculate total flight takings
                                try {
                                    NotificationHandler.notify("Booking Error", "Error " + tempSeat.getSeatNumber() + "Seat Is Already Free");
                                } catch (AWTException | MalformedURLException ex) {
                                    genericPopup("Error " + tempSeat.getSeatNumber() + "Seat Is Already Free");
                                }
                                break;
                            case 2:
                                updateCounters = 1;
                                tempSeat.setCurrentStatus(1);
                                currentFlight.updateSeat(updateCounters);
                                tempSeat.setaPassenger(null);
                                currentFlight.calculateTotalFlightTakings();

                                try {
                                    NotificationHandler.notify("Booking Cancelled", tempSeat.getSeatNumber() + " Has Been Cancelled");
                                } catch (AWTException | MalformedURLException ex) {
                                    genericPopup(tempSeat.getSeatNumber() + " Has Been Cancelled");
                                }
                                break;
                            //end of nested if
                            case 3:
                                updateCounters = 2;
                                tempSeat.setCurrentStatus(1);
                                currentFlight.updateSeat(updateCounters);
                                tempSeat.setaPassenger(null);
                                currentFlight.calculateTotalFlightTakings();
                                try {
                                    NotificationHandler.notify("Booking Cancelled", tempSeat.getSeatNumber() + " Has Been Cancelled - No Refund");
                                } catch (AWTException | MalformedURLException ex) {
                                    genericPopup(tempSeat.getSeatNumber() + " Has Been Cancelled - No Refund");
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    }
                    case 2:
                    case 3:
                        //BookPassenger(flightNo, choice, seatNo);
                        getPassengerDetails(flightNo, choice, seatNo); // Old Booking system.
                        break;
                    case 4: {
                        Seat tempSeat = scotiaAirline.getSeat(flightNo, seatNo);
                        genericPopup(tempSeat.displaySeatDetails());
                        break;
                    }
                    default:
                        break;
                }
                saFrame.setVisible(false);
                saFrame.dispose();

            }
        });

        btnBack.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            mainMenu();
        });

    }

    /**
     * Code for new Booking Page Display which will be available on V3. Will be
     * integrated into a single panel which will reduce clicks.
     *
     * @param bookingChoice
     * @param seatNo
     * @see selectFlight
     * @param flightNumber - Takes Flight number from selectFlight Panel
     */
    public void bookPassenger(final String flightNumber, final int bookingChoice, final String seatNo) {
        final JFrame saFrame = new JFrame();

        saFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ScotiaAirline.windowClosing(evt);
            }
        });
        //Set Frame
        saFrame.setBackground(new java.awt.Color(255, 255, 255));
        saFrame.setSize(400, 370);
        saFrame.setLocationRelativeTo(null);
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setTitle(Reference.PROG_NAME + " Passenger Booking | Version: " + Reference.VERSION_NUMBER);
        saFrame.setLayout(null);

        //Set image title                  
        JLabel saTitle = new JLabel(new ImageIcon(getClass().getResource("/testmenu.png")), SwingConstants.CENTER);
        saTitle.setBounds(70, 10, 278, 85);
        saFrame.add(saTitle);

        JLabel saPassengerNameLabel = new JLabel("Passenger Name");
        saPassengerNameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        saFrame.add(saPassengerNameLabel);
        saPassengerNameLabel.setBounds(10, 100, 110, 30);

        JTextField txtPassengerName = new JTextField();
        saFrame.add(txtPassengerName);
        txtPassengerName.setBounds(120, 100, 180, 30);

        ButtonGroup saPassengerBGroup = new ButtonGroup();

        JRadioButton btnBusinessPassenger = new JRadioButton();
        saPassengerBGroup.add(btnBusinessPassenger);
        btnBusinessPassenger.setText("Business");
        saFrame.add(btnBusinessPassenger);
        btnBusinessPassenger.setBounds(10, 140, 80, 23);

        JRadioButton btnIslandPassenger = new JRadioButton();
        saPassengerBGroup.add(btnIslandPassenger);
        btnIslandPassenger.setText("Island");
        saFrame.add(btnIslandPassenger);
        btnIslandPassenger.setBounds(110, 140, 80, 23);

        JRadioButton btnOrdinaryPassenger = new JRadioButton();
        saPassengerBGroup.add(btnOrdinaryPassenger);
        btnOrdinaryPassenger.setText("Ordinary");
        saFrame.add(btnOrdinaryPassenger);
        btnOrdinaryPassenger.setBounds(200, 140, 80, 23);

        JCheckBox btnOrdinaryPromotion = new JCheckBox("Promotion?");
        btnOrdinaryPromotion.setBounds(280, 140, 90, 23);

        JLabel saIslandNameLabel = new JLabel("Island Name");
        saIslandNameLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        saIslandNameLabel.setBounds(10, 180, 79, 30);

        JTextField txtIslandName = new JTextField();
        txtIslandName.setBounds(90, 180, 200, 30);

        JLabel saBusinessNameLabel = new JLabel("Business Name");
        saBusinessNameLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        saBusinessNameLabel.setBounds(10, 180, 79, 30);

        JTextField txtBusinessName = new JTextField();
        txtBusinessName.setBounds(90, 180, 200, 30);

        btnBusinessPassenger.addActionListener((ActionEvent e) -> {
            saFrame.add(saBusinessNameLabel);
            saFrame.add(txtBusinessName);
            saFrame.remove(saIslandNameLabel);
            saFrame.remove(txtIslandName);
            saFrame.remove(btnOrdinaryPromotion);
            saFrame.repaint();
        });

        btnIslandPassenger.addActionListener((ActionEvent e) -> {
            saFrame.add(saIslandNameLabel);
            saFrame.add(txtIslandName);
            saFrame.remove(saBusinessNameLabel);
            saFrame.remove(txtBusinessName);
            saFrame.remove(btnOrdinaryPromotion);
            saFrame.repaint();
        });

        btnOrdinaryPassenger.addActionListener((ActionEvent e) -> {
            saFrame.add(btnOrdinaryPromotion);
            saFrame.remove(saBusinessNameLabel);
            saFrame.remove(txtBusinessName);
            saFrame.remove(saIslandNameLabel);
            saFrame.remove(txtIslandName);
            saFrame.repaint();
        });

        //Back Button
        JButton backBtn = new JButton("Back");
        saFrame.getContentPane().add(backBtn);
        backBtn.setBounds(10, 260, 170, 34);

        //Select Button
        JButton submitBtn = new JButton("Submit Information");
        submitBtn.setFont(new java.awt.Font("Tahoma", 1, 11));
        saFrame.getContentPane().add(submitBtn);
        submitBtn.setBounds(80, 220, 230, 34);

        //Exit Button
        JButton retrnMainMenuBtn = new JButton("Return to Main Menu");
        saFrame.getContentPane().add(retrnMainMenuBtn);
        retrnMainMenuBtn.setBounds(200, 260, 170, 34);

        saFrame.setVisible(true);

        backBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            selectFlight("ChangeFlightStatus");
        });

        retrnMainMenuBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            mainMenu();
        });

        submitBtn.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();

            String passengerName = txtPassengerName.getText();//.toString();
            //String island = txtIslandName.getText();//.toString();
            //String company = txtBusinessName.getText();//.toString();
            //boolean promo = btnOrdinaryPromotion.isSelected();//.toString();

            if (btnBusinessPassenger.isEnabled()) {
                saFrame.dispose();
                saFrame.setVisible(false);
                String company = txtBusinessName.getText();//.toString();
                PassengerBusiness newPassenger = new PassengerBusiness(passengerName, company);
                Flight currentFlight = scotiaAirline.getFlights(flightNumber);
                Seat passengerSeat;// = new Seat();
                passengerSeat = scotiaAirline.getSeat(flightNumber, seatNo);
                int fullSeatStatus = passengerSeat.changeSeatStatus(scotiaAirline, bookingChoice, newPassenger, currentFlight);
                currentFlight.updateSeat(fullSeatStatus);
                currentFlight.calculateTotalFlightTakings();

            } else if (btnIslandPassenger.isEnabled()) {
                saFrame.dispose();
                saFrame.setVisible(false);
                String island = txtIslandName.getText();//.toString();
                PassengerWestern newPassenger = new PassengerWestern(passengerName, island);
                Flight currentFlight = scotiaAirline.getFlights(flightNumber);
                Seat passengerSeat;// = new Seat();
                passengerSeat = scotiaAirline.getSeat(flightNumber, seatNo);
                int fullSeatStatus = passengerSeat.changeSeatStatus(scotiaAirline, bookingChoice, newPassenger, currentFlight);
                currentFlight.updateSeat(fullSeatStatus);
                currentFlight.calculateTotalFlightTakings();
            } else if (btnOrdinaryPassenger.isEnabled()) {
                saFrame.dispose();
                saFrame.setVisible(false);
                boolean yes = true;
                boolean no = false;

                String n = String.valueOf(false);

                String promo = btnOrdinaryPromotion.toString();
                PassengerOrdinary newPassenger = new PassengerOrdinary(passengerName, promo.charAt(0));
                Flight currentFlight = scotiaAirline.getFlights(flightNumber);
                Seat passengerSeat;// = new Seat();
                passengerSeat = scotiaAirline.getSeat(flightNumber, seatNo);
                int fullSeatStatus = passengerSeat.changeSeatStatus(scotiaAirline, bookingChoice, newPassenger, currentFlight);
                currentFlight.updateSeat(fullSeatStatus);
                currentFlight.calculateTotalFlightTakings();

                scotiaAirline.saveSeatsToDB();
                scotiaAirline.savePassengersToDB();
            } else {
                genericPopup("You haven't completed the form, make sure you've selected your passenger Type.");
                try {
                    NotificationHandler.notify(" WARNING", "You haven't completed the form.");
                } catch (AWTException | MalformedURLException ex) {
                    genericPopup("You haven't completed the form.");
                }
            }
        });

    }

    /**
     * Displays the seating plan for the selected flight
     *
     * @param flightNo
     */
    public void displaySeatMap(final String flightNo) {
        final JFrame saFrame = new JFrame();
        saFrame.setSize(800, 800);
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setLocationRelativeTo(null);
        saFrame.setTitle(Reference.PROG_NAME + " Seat Map | Flight Number: " + flightNo);
        saFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ScotiaAirline.windowClosing(evt);
            }
        });
        saFrame.getContentPane().setLayout(null);
        JLabel sfTitle = new JLabel();
        sfTitle.setIcon(new ImageIcon(getClass().getResource("/mainmenu.png")));
        saFrame.add(sfTitle);
        sfTitle.setBounds(180, 0, 370, 100);

        JLabel imgFree = new JLabel();
        imgFree.setIcon(new ImageIcon(getClass().getResource("/seatFree.png")));
        imgFree.setText("Free");
        saFrame.add(imgFree);
        imgFree.setBounds(180, 630, 76, 50);

        JLabel imgReserved = new JLabel();
        imgReserved.setIcon(new ImageIcon(getClass().getResource("/seatReserved.png")));
        imgReserved.setText("Reserved");
        saFrame.add(imgReserved);
        imgReserved.setBounds(280, 630, 96, 50);

        JLabel imgBooked = new JLabel();
        imgBooked.setIcon(new ImageIcon(getClass().getResource("/seatBooked.png")));
        imgBooked.setText("Booked");
        saFrame.add(imgBooked);
        imgBooked.setBounds(400, 630, 76, 50);

        JLabel flightNumber = new JLabel();
        flightNumber.setText("<html><b>Flight Number:<br>" + flightNo + "</b></html>");
        flightNumber.setHorizontalAlignment(SwingConstants.CENTER);
        flightNumber.setFont(new java.awt.Font("Tahoma", 1, 14));
        saFrame.add(flightNumber);
        flightNumber.setBounds(580, 40, 170, 50);

        JEditorPane seatMap = new JEditorPane();
        seatMap.setBounds(0, 130, 785, 490);
        seatMap.setEditable(false);
        try {
            seatMap.setPage(Reference.PROG_SECRET);
        } catch (IOException e) {
            seatMap.setContentType("text/html");
            seatMap.setText("<html>Could not load</html>");
        }

        saFrame.add(seatMap);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setEnabled(false);
        btnSubmit.setBounds(600, 700, 162, 40);
        saFrame.add(btnSubmit);

        JButton btnBack = new JButton("Return to Menu");
        btnBack.setBounds(10, 700, 120, 40);
        saFrame.add(btnBack);

        saFrame.setVisible(true);

    }

    /**
     * Basic Web Get to enable interactive seating plan. TODO: ADD PHP Support
     * possible API Extension.
     *
     * @throws IOException
     */
    public void webGet() throws IOException {
        try {
            String path = Reference.PROG_SECRET;
            URL url = new URL(path);
            BufferedImage image = ImageIO.read(url);
            JLabel label = new JLabel(new ImageIcon(image));
            JFrame webFrame = new JFrame();
            webFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            webFrame.getContentPane().add(label);
            webFrame.pack();
            webFrame.setLocation(200, 200);
            webFrame.setVisible(true);
        } catch (MalformedURLException ex) {
            LogHelper.error(ex);
        }
    }

    /**
     * Code below deprecated in version 3.
     */
    /**
     * Gets the Passengers Details and passes to appropriate method based on
     * selection
     *
     * @param flightNumber
     * @param bookingChoice
     * @param seatNo /@deprecated - Version 3 - Combined into single method
     */
    public void getPassengerDetails(final String flightNumber, final int bookingChoice, final String seatNo) {
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        saFrame.setLocationRelativeTo(null);

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
        btnBack.addActionListener((ActionEvent e) -> {
            saFrame.dispose();
            getSeatno(flightNumber, bookingChoice);
        });

        btnBusiness.addActionListener((ActionEvent e) -> {
            saFrame.dispose();
            String passengerName = txtPassengerName.getText();//.toString();
            getCompany(flightNumber, bookingChoice, seatNo, passengerName);
        });

        btnLocal.addActionListener((ActionEvent e) -> {
            saFrame.dispose();
            String passengerName = txtPassengerName.getText();//.toString();
            getResidence(flightNumber, bookingChoice, seatNo, passengerName);
        });

        btnOrdinary.addActionListener((ActionEvent e) -> {
            saFrame.dispose();
            String passengerName = txtPassengerName.getText();//.toString();
            getPromo(flightNumber, bookingChoice, seatNo, passengerName);
        });

    }

    /**
     * Asks user for Island information if user selected Island in
     * getPassengerDetails Applies Residence Discount
     *
     * @param flightNumber
     * @param bookingChoice
     * @param seatNo
     * @param passengerName /@deprecated - Version 3 - Combined into single
     * method
     */
    public void getResidence(final String flightNumber, final int bookingChoice, final String seatNo, final String passengerName) {

        // create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        // sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);

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
        btnBack.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            getPassengerDetails(flightNumber, bookingChoice, seatNo);
        });

        btnSubmit.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            mainMenu();
            String island = txtIslandOfResidence.getText();//.toString();
            PassengerWestern newPassenger = new PassengerWestern(passengerName, island);
            Flight currentFlight = scotiaAirline.getFlights(flightNumber);
            Seat passengerSeat;// = new Seat();
            passengerSeat = scotiaAirline.getSeat(flightNumber, seatNo);
            int fullSeatStatus = passengerSeat.changeSeatStatus(scotiaAirline, bookingChoice, newPassenger, currentFlight);
            currentFlight.updateSeat(fullSeatStatus);
            currentFlight.calculateTotalFlightTakings();
        });

    }

    /**
     * Asks user for Company information if user selected Business in
     * getPassengerDetails Applies Company Discount
     *
     * @param flightNumber
     * @param bookingChoice
     * @param seatNo
     * @param passengerName /@deprecated - Version 3 - Combined into single
     * method
     */
    public void getCompany(final String flightNumber, final int bookingChoice, final String seatNo, final String passengerName) {

        // create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        // sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);

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
        btnBack.addActionListener((ActionEvent e) -> {
            saFrame.dispose();
            getPassengerDetails(flightNumber, bookingChoice, seatNo);
        });

        btnSubmit.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            mainMenu();
            String company = txtCompanyName.getText();//.toString();
            PassengerBusiness newPassenger = new PassengerBusiness(passengerName, company);
            Flight currentFlight = scotiaAirline.getFlights(flightNumber);
            Seat passengerSeat;// = new Seat();
            passengerSeat = scotiaAirline.getSeat(flightNumber, seatNo);
            int fullSeatStatus = passengerSeat.changeSeatStatus(scotiaAirline, bookingChoice, newPassenger, currentFlight);
            currentFlight.updateSeat(fullSeatStatus);
            currentFlight.calculateTotalFlightTakings();
        });
    }

    /**
     * Asks user if their booking is apart of a promotion if they selected
     * ordinary in getPassengerDetails Applies Promotion Discount
     *
     * @param flightNumber
     * @param bookingChoice
     * @param seatNo
     * @param passengerName /@deprecated - Version 3 - Combined into single
     * method
     */
    public void getPromo(final String flightNumber, final int bookingChoice, final String seatNo, final String passengerName) {

        // create frame object and size
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 350);
        // sets frame in middle of the screen when null value is set
        saFrame.setLocationRelativeTo(null);

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
        btnBack.addActionListener((ActionEvent e) -> {
            saFrame.dispose();
            saFrame.setVisible(false);
            getPassengerDetails(flightNumber, bookingChoice, seatNo);
        });

        btnSubmit.addActionListener((ActionEvent e) -> {
            saFrame.setVisible(false);
            saFrame.dispose();
            mainMenu();
            String promo = txtPromo.getText();//.toString();
            PassengerOrdinary newPassenger = new PassengerOrdinary(passengerName, promo.charAt(0));
            Flight currentFlight = scotiaAirline.getFlights(flightNumber);
            Seat passengerSeat;// = new Seat();
            passengerSeat = scotiaAirline.getSeat(flightNumber, seatNo);
            int fullSeatStatus = passengerSeat.changeSeatStatus(scotiaAirline, bookingChoice, newPassenger, currentFlight);
            currentFlight.updateSeat(fullSeatStatus);
            currentFlight.calculateTotalFlightTakings();
        });
    }
}
