/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 2.5
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines;

//Local Imports
import couk.deanreid.scotiaairlines.core.Airline;
import couk.deanreid.scotiaairlines.handler.NotificationHandler;
import couk.deanreid.scotiaairlines.ui.UI;
import couk.deanreid.scotiaairlines.ui.UIConsole;
import couk.deanreid.scotiaairlines.utils.LogHelper;
import couk.deanreid.scotiaairlines.utils.Reference;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class ScotiaAirline {

    /**
     Main Class Startup

     @param args

     @throws SQLException
     @throws IOException
     */
    public static void main(String[] args) throws SQLException, IOException, Exception {
            LogHelper.debug("Debug Mode is Enabled. Everything will show here.");
            LogHelper.info("Debug Mode is Disabled. Only major errors will show");
        Airline scotiaAirline = new Airline();
        scotiaAirline.loadFlightsFromDB();
        scotiaAirline.loadSeatsFromDB();

//If program is on server environment, load CLI else load GUI unless forced by code using debug
        //Allow forcing cli and to check if GUI is possible
        if (Reference.FORCE_CLI) {
            System.out.println(Reference.TextPaint.GREEN + "===========================" + Reference.TextPaint.RESET);
            LogHelper.warn(Reference.TextPaint.ORANGE + "UI DEBUG: CLI Forced On" + Reference.TextPaint.RESET);
            //Check if GUI is possible
            if (!GraphicsEnvironment.isHeadless()) {
                LogHelper.info(Reference.TextPaint.ORANGE + "UI DEBUG: GUI Disabled - No Display Detected. Running CLI" + Reference.TextPaint.RESET);
                LogHelper.error(Reference.TextPaint.RED + "UI DEBUG: CLI Code is Incomplete. Please run in a GUI Enviironment" + Reference.TextPaint.RESET);
                System.out.println(Reference.TextPaint.GREEN + "===========================" + Reference.TextPaint.RESET);
                UIConsole uic = new UIConsole(scotiaAirline);
                uic.mainMenu();
            }
        } else {
            if (Reference.DARK_MODE) {
                LogHelper.debug("UI DEBUG:" + Reference.TextPaint.GREEN + " Dark Mode Enabled!" + Reference.TextPaint.RESET);
                UIManager.put("control", new Color(128, 128, 128));
                UIManager.put("info", new Color(128, 128, 128));
                UIManager.put("nimbusBase", new Color(29, 29, 29));
                UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
                UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
                UIManager.put("nimbusFocus", new Color(115, 164, 209));
                UIManager.put("nimbusGreen", new Color(176, 179, 50));
                UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
                UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
                UIManager.put("nimbusOrange", new Color(191, 98, 4));
                UIManager.put("nimbusRed", new Color(169, 46, 34));
                UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
                UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
                UIManager.put("text", new Color(230, 230, 230));
            } else {
                LogHelper.debug("UI DEBUG:" + Reference.TextPaint.BLUE + " Dark Mode Disabled!" + Reference.TextPaint.RESET);
            }
            try {
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            }
            UI uig = new UI(scotiaAirline);
            uig.mainMenu();
            LogHelper.debug("UI DEBUG:" + Reference.TextPaint.GREEN + " GUI Loaded" + Reference.TextPaint.RESET);
        }
    }

    /**
     If user closes program via X then this will ensure current data is saved.

     @param evt
     */
    public static void WindowClosing(WindowEvent evt) {
        try {
            Airline scotiaAirline = new Airline();
            scotiaAirline.SaveSeatsToDB();
            scotiaAirline.SavePassengersToDB();
            if (SystemTray.isSupported()) {
                NotificationHandler.Notify("Program Exit", "Adding changes to the database!");
            } else {
                JOptionPane.showMessageDialog(null, "Please Wait while changes are saved!");
            }
        } catch (HeadlessException q) {
            JOptionPane.showMessageDialog(null, q);
            LogHelper.warn(q);
        } catch (AWTException | MalformedURLException ex) {
            LogHelper.fatal(ex);
        }
        System.exit(0);
    }
}
