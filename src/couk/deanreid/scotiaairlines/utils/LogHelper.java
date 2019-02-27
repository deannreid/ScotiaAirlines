package couk.deanreid.scotiaairlines.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 Scotia Airlines - HND Computer Science
 Program Version: 2.9
 Code Version: 1.1
 @Author: Dean D. Reid
 */
public class LogHelper {
 private static final Logger log = LogManager.getLogger(LogHelper.class);

   public static void log(Level logLevel, Object object) {
        //Logger.log(logLevel, String.valueOf(object), new Object[0]);
    }

    public static void error(Object object) {
        log(Level.ERROR,"[ERROR:] " + object);
    }

    public static void warn(Object object) {
        log(Level.WARN,"[WARN:] " + object);
    }

    public static void trace(Object object) {
        log(Level.TRACE,"[TRACE:] " + object);
    }
    
    public static void fatal(Object object) {
        log(Level.FATAL,"[FATAL:] " + object);
    }
    
    public static void info(Object object) {
        log(Level.INFO,"[INFO:] " + object);
    }
    public static void debug(Object object) {
        if (Reference.DEBUG_MODE) {
            log(Level.INFO, "[DEBUG:] " + object);
        }
    }
}
