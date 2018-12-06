/*
 Scotia Airlines - HND Computer Science
 Version 1.4
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines;

import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class ScotiaAirline {

    static String version;

    //Main Class
    public static void main(String[] args) throws SQLException, IOException {
        
        Airline scotiaAirline = new Airline();
        scotiaAirline.loadFlightsFromDB();
        scotiaAirline.loadSeatsFromDB();
        version = "2.41";
//If program is on server environment, load CLI else load GUI
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Loading CLI");
            UIConsole ui = new UIConsole();
            ui.loadConsoleInterface();
        } else {
            try {
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {}
            UIModern mui = new UIModern(scotiaAirline);
            //UI ui = new UI(scotiaAirline);
            mui.mainMenu();
            System.out.println("GUI Loaded");
        }
    }

    static void WindowClosing(WindowEvent evt) {
        try {
            Airline scotiaAirline = new Airline();
            scotiaAirline.EmptyDB();
            JOptionPane.showMessageDialog(null, "Please Wait while changes are saved!");
            scotiaAirline.SaveSeatsToDB();
            scotiaAirline.SavePassengersToDB();
        } catch (HeadlessException q) {
            JOptionPane.showMessageDialog(null, q);
        }
        System.exit(0);

    }
}
