/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 1.2
 @Author: Dean D. Reid
 */
package couk.deanreid.scotiaairlines.utils;

/**
 Easy References -
 Some entries will soon be ported to the config file to allow user
 to modify settings.
 */
public class Reference {

    /**
     Enable Debug Mode All The Lines...
     */
    public static final boolean DEBUG_MODE = true;

    /**
     Enable Dark Mode - Everyone has one of these nowadays - I Like it. The
     Dark Side of the Force.
     */
    public static final boolean DARK_MODE = true;

    /**
     Force the CLI - for Testing purposes
     CLI is Broken.
     */
    public static final boolean FORCE_CLI = false;

    /**
     Set Configuration Filename.

     */
    public static final String CONFIG_FILE_NAME = "scotiaairlines";

    /**
     Program Name
     */
    public static final String PROG_NAME = "Scotia Airlines";

    /**
     Program Version and Build Number
     */
    public final String version = this.getClass().getPackage().getImplementationVersion();
    public static final String VERSION_NUMBER = "3.0 FINAL (build 68)";

    /**
     Not a very secret Secret...
     */
    public static final String PROG_SECRET = "http://xenohost.co.uk/deancollege/images/img/15.jpg";

//Database Related Constants    
    /**
     Database URL
     */
    public static final String DB_URL = "comp-hons.uhi.ac.uk";

    /**
     Table Name for Data
     */
    public static final String DB_TABLE = "pe10004084";

    /**
     This just combines the Host and Table for college database
     */
    public static final String DB_FULLURL = DB_URL + "/" + DB_TABLE;

    /**
     Database User
     */
    public static final String DB_USER = "pe10004084";

    /**
     Database Password
     */
    public static final String DB_PASSWORD = "deanreid";

    /**
     List of Colours to Choose
     */
    public enum TextPaint {
        BOLD("\033[0;1m"),
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
}