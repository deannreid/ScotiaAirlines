/*
 Scotia Airlines - HND Computer Science
 Version 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class UIModern {

    //Instance Variable
    private int buttonCounter = 2;
    private final Airline scotiaAirline;
    
    //Variable declaration
        //Generic Output
            private javax.swing.JButton goButton;
            private javax.swing.JLabel goTitle;
            private javax.swing.JPanel goPanelI;
            private javax.swing.JLabel goOutput;
        //Main Menu
            private javax.swing.JLabel saTitle;

    //Constructor
            
    public UIModern(Airline aAirline) {
        scotiaAirline = aAirline;
    }

    
//Generic Output
    public void genericOutput(String inputMessage) {
    
    final JFrame goFrame = new JFrame();
    goButton  = new javax.swing.JButton();
    goTitle  = new javax.swing.JLabel();
    goPanelI  = new javax.swing.JPanel();
    goOutput  = new javax.swing.JLabel(inputMessage);
    
    //Set Frame
        goFrame.setBackground(new java.awt.Color(255, 255, 255));
            goFrame.setSize(330,420);
                goFrame.setLocationRelativeTo(null);
                    goFrame.setType(java.awt.Window.Type.UTILITY);
                        goFrame.setTitle("Scotia Airlines - Message Box ");
                            goFrame.setLayout(null);
    //Set image title                  
        goTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tbico.png")));
            goTitle.setBounds(34, 0, 278, 85);
                goFrame.add(goTitle);
                
    //set Input Panel
        goPanelI.setBackground(new java.awt.Color(255,255,255));
            goPanelI.setBounds(20, 103, 292, 258);
                goPanelI.add(goOutput);
                    goFrame.add(goPanelI);
    
    //Set Return Button      
        goButton.setBackground(new java.awt.Color(153, 255, 255));
            goButton.setText("Return to Menu");
                goButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                    goButton.setBounds(113, 367, 124, 37);
                        goFrame.add(goButton);

    }
  
   public void displayTray() throws AWTException, MalformedURLException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Hello, World", "notification demo", MessageType.INFO);
    }
//All Menus
    public void mainMenu() {
    final JFrame saFrame = new JFrame();
    saTitle  = new javax.swing.JLabel();
    JPanel saPanel = new JPanel();
    //Set Frame
        saFrame.setBackground(new java.awt.Color(255, 255, 255));
            saFrame.setSize(330,420);
                saFrame.setLocationRelativeTo(null);
                    saFrame.setType(java.awt.Window.Type.UTILITY);
                        saFrame.setTitle("Scotia Airlines - Main Menu | Program Version:" + ScotiaAirline.version);
                            saFrame.setLayout(null);
    //Set image title                  
        saTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tbico.png")));
            saTitle.setBounds(34, 0, 278, 85);
                saFrame.add(saTitle);   
//setBounds(X,Y,Width,Height
    //Test Button
        JButton testMenuBtn = new javax.swing.JButton();
            testMenuBtn.setText("Test Menu");
                saFrame.getContentPane().add(testMenuBtn);
                    testMenuBtn.setBounds(10, 10, 100, 30);
    //Booking Menu
        JButton bookingMenuBtn = new JButton("Booking Menu");
            //bookingMenuBtn.setEnabled(false);
                bookingMenuBtn.setText("Book Flight");
                    bookingMenuBtn.setFont(new java.awt.Font("Tahoma", 1, 11));
                        saFrame.getContentPane().add(bookingMenuBtn);
                            bookingMenuBtn.setBounds(83, 106, 220, 60);

    //Flight Admin Menu
        JButton flightAdminBtn = new JButton("Flight Admin");
            //flightAdminBtn.setEnabled(false);
                saFrame.getContentPane().add(flightAdminBtn);
                    flightAdminBtn.setBounds(83, 300, 110, 60);
    //Exit Button
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
                adminMenu();
            } //default action performed is a click
        });

        bookingMenuBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.setVisible(false);
                saFrame.dispose();
                selectFlight("Booking");
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
    
    public void adminMenu() {}
    
    public void bookingMenu() {}
    
    public void statusMenu() {}
    
//Sub Menus
    public void selectFlight() {}
    
    
    
    
}
