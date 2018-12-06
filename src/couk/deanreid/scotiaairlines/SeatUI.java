/*
 Scotia Airlines - HND Computer Science
 Version 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.*;

/*
 @Not Implemented this is just to test code
 */
public class SeatUI {

    private final int rows = 7; // to be set by Hashmap variable
    private final int columns = 2; // to be set by Hashmap variable
    private int buttonCounter = 2;

    private Icon free = (new javax.swing.ImageIcon(getClass().getResource("/assets/tbico.png")));
    private Icon reserved = (new javax.swing.ImageIcon(getClass().getResource("/assets/tbico.png")));
    private Icon booked = (new javax.swing.ImageIcon(getClass().getResource("/assets/tbico.png")));

    public SeatUI() {
        Airline scotiaAirline = null;
        UI UI = new UI(scotiaAirline);
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
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
            JButton tempButtonF = new JButton("SeatNumber: " + currentFlight.getValue().getFlightNumber() + new javax.swing.ImageIcon(getClass().getResource("/assets/seatFree.png")));
            JButton tempButtonR = new JButton("SeatNumber: " + currentFlight.getValue().getFlightNumber() + new javax.swing.ImageIcon(getClass().getResource("/assets/seatReserved.png")));
            JButton tempButtonB = new JButton("SeatNumber: " + currentFlight.getValue().getFlightNumber() + new javax.swing.ImageIcon(getClass().getResource("/assets/seatBooked.png")));
//Free Button
            tempButtonF.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //If Seat is Free, Go to booking menu
                    //If Seat is Booked or Reserved, Disable button.
                }
            });
// Reserved Button   
            tempButtonR.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //If Seat is Free, Go to booking menu
                    //If Seat is Booked or Reserved, Disable button.
                }
            });

// Booked Button            
             tempButtonB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //If Seat is Free, Go to booking menu
                    //If Seat is Booked or Reserved, Disable button.
                }
            });           

            return tempButtonF;
        }).map((tempButtonF) -> {
            buttonCounter += 1;
            return tempButtonF;
        }).forEachOrdered((tempButtonF) -> {
            saPanel.add(tempButtonF);
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
                UI.mainMenu();
            } // default action performed is a click
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                SeatUI seatUI = new SeatUI();
            }
        });
    }
}
