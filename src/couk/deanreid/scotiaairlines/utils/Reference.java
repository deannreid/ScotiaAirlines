/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.6
 Code Version: 1.2
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.utils;

import javax.swing.Icon;

/**
 * Easy References - Save Typing and Makes life easier to change Global
 * Variables. Some entries will soon be ported to the config file to allow user
 * to modify settings. -- Nevermind.
 */
public class Reference {


    /**
     * Enable Debug Mode All The Lines...
     */
    public static final boolean DEBUG_MODE = true;

    /**
     * Enable Dark Mode - Everyone has one of these nowadays - I Like it. The
     * Dark Side of the Force.
     */
    public static final boolean DARK_MODE = true;

    /**
     * Set Configuration Filename.
     *
     * @deprecated - Since Version 2.6
     */
    public static final String CONFIG_FILE_NAME = "scotia.properties";

    /**
     * Set Configuration Location.
     *
     * @deprecated - Since Version 2.6 - Never used this.
     */
    public static final String CONFIG_FILE_LOCATION = "D:/Documents/Development/HND Computer Science/OOPS/";

    /**
     * Program Name
     */
    public static final String PROG_NAME = "Scotia Airlines";

    /**
     * Program Version and Build Number
     */
    public static final String VERSION_NUMBER = "2.7 (build 12)";

    /**
     * Not a very secret Secret...
     */
    public static final String PROG_SECRET = "http://xenohost.co.uk/deancollege/images/img/15.jpg";

//Database Related Constants    
    /**
     * Database URL
     */
    public static final String DB_URL = "comp-hons.uhi.ac.uk";

    /**
     * Table Name for Data
     */
    public static final String DB_TABLE = "pe10004084";

    /**
     * This just combines the Host and Table for college database
     */
    public static final String DB_FULLURL = DB_URL + "/" + DB_TABLE;

    /**
     * Database User
     */
    public static final String DB_USER = "pe10004084";

    /**
     * Database Password
     */
    public static final String DB_PASSWORD = "deanreid";

    /**
     * List of Colours to Choose
     */
    public enum TextPaint {
        BLACK("\u00A70"),
        DARK_BLUE("\u00A71"),
        DARK_GREEN("\u00A72"),
        GREEN("\u001B[32m"),
        DARK_CYAN("\u00A73"),
        DARK_RED("\u00A74"),
        RED("\u001B[31m"),
        PURPLE("\u00A75"),
        ORANGE("\u00A76"),
        LIGHT_GREY("\u00A77"),
        DARK_GREY("\u00A78"),
        LIGHT_GREEN("\u00A7a"),
        LIGHT_CYAN("\u00A7b"),
        LIGHT_RED("\u00A7c"),
        PINK("\u00A7d"),
        YELLOW("\u00A7e"),
        BLUE("\u001B[34m"),
        WHITE("\u00A7f"),
        RESET("\u001B[0m");

        private final String colour;

        TextPaint(String paint) {
            this.colour = paint;
        }

        public String getColour() {
            return colour;
        }

        @Override
        public String toString() {
            return colour;
        }
    }

//Images and Icons   
    /**
     * Main Menu Image
     *
     * @deprecated - As of Version 1.7 - decided not to work it this way as it
     * means more code.
     */
    public Icon MAIN_MENU = (new javax.swing.ImageIcon(getClass().getResource("/assets/mainmenu.png")));

    /**
     * Admin Menu Image
     *
     * @deprecated - As of Version 1.7 - decided not to work it this way as it
     * means more code.
     */
    public Icon ADMIN_MENU = (new javax.swing.ImageIcon(getClass().getResource("/assets/adminmenu.png")));

    /**
     * Flight Menu Image
     *
     * @deprecated - As of Version 1.7 - decided not to work it this way as it
     * means more code.
     */
    public Icon FLIGHT_MENU = (new javax.swing.ImageIcon(getClass().getResource("/assets/tbselectflight.png")));

    /**
     * Test Menu Image
     *
     * @deprecated - As of Version 1.7 - decided not to work it this way as it
     * means more code.
     */
    public Icon TEST_MENU = (new javax.swing.ImageIcon(getClass().getResource("/assets/testmenu.png")));

    /**
     * Free Seat Icon
     *
     * @deprecated - As of Version 1.7 - decided not to work it this way as it
     * means more code.
     */
    public Icon SEAT_FREE = (new javax.swing.ImageIcon(getClass().getResource("/assets/seatFree.png")));

    /**
     * Reserved Seat Icon
     *
     * @deprecated - As of Version 1.7 - decided not to work it this way as it
     * means more code.
     */
    public Icon SEAT_RESERVED = (new javax.swing.ImageIcon(getClass().getResource("/assets/seatReserved.png")));

    /**
     * Booked Seat Icon
     *
     * @deprecated - As of Version 1.7 - decided not to work it this way as it
     * means more code.
     */
    public Icon SEAT_BOOKED = (new javax.swing.ImageIcon(getClass().getResource("/assets/seatBooked.png")));

}
