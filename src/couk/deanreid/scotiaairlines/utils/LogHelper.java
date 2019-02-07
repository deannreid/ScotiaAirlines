package couk.deanreid.scotiaairlines.utils;

import org.apache.logging.log4j.Level;

/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.6
 Code Version: 1.1
 @Author: Dean D. Reid
 */
public class LogHelper {

    /**
    TODO Actually Implement these eventually. Save the Lines.
    */
    
    
    public static void log(Level logLevel, Object object) {
        log(logLevel, object);
    }

    public static void all(Object object) {
        log(Level.ALL, object);
    }

    public static void debug(Object object) {
        log(Level.DEBUG, object);
    }

    public static void error(Object object) {
        log(Level.ERROR, object);
    }

    public static void fatal(Object object) {
        log(Level.FATAL, object);
    }

    public static void info(Object object) {
        log(Level.INFO, object);
    }

    public static void off(Object object) {
        log(Level.OFF, object);
    }

    public static void trace(Object object) {
        log(Level.TRACE, object);
    }

    public static void warn(Object object) {
        log(Level.WARN, object);
    }
}
