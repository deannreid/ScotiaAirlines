/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 0.8
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.handler;

import couk.deanreid.scotiaairlines.utils.LogHelper;
import couk.deanreid.scotiaairlines.utils.Reference;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;

public class NotificationHandler {
    /**
     * Method to check if the System Tray is supported.
     *
     * @param inputHeader
     * @param inputMessage
     *
     *
     * @throws AWTException
     * @throws MalformedURLException
     */
    public static void notify (String inputHeader, String inputMessage) throws AWTException, MalformedURLException {
        if (SystemTray.isSupported ()) {
            NotificationHandler Notify = new NotificationHandler ();
            Notify.displayTray (inputHeader, inputMessage);
        } else {
            LogHelper.error ("System tray not supported!");
        }
    }

    /**
     * Main Method - Testing Windows Notification System -- Not implemented
     * fully yet.
     *
     * @param inputHeader
     * @param inputMessage
     * @throws AWTException
     * @throws MalformedURLException
     */
    public void displayTray (String inputHeader, String inputMessage) throws AWTException, MalformedURLException {
        //Obtain only one instance of the SystemTray object
        SystemTray Notify = SystemTray.getSystemTray ();
        Image image = Toolkit.getDefaultToolkit ().
                createImage (getClass ().
                        getResource ("/icon.png"));
        TrayIcon trayIcon = new TrayIcon (image, Reference.PROG_NAME
                + " Information System");
        trayIcon.setImageAutoSize (true);
        trayIcon.setToolTip (Reference.PROG_NAME
                + " Information System");
        Notify.add (trayIcon);
        trayIcon.displayMessage (Reference.PROG_NAME
                + " "
                + inputHeader, inputMessage, MessageType.NONE);
        LogHelper.debug ("POPUP DEBUG: "
                + " | Header: "
                + inputHeader
                + " | Message: "
                + inputMessage);
    }

}
