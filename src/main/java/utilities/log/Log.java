package utilities.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    public static void Info(String message) {
        Logger logger = LogManager.getLogger(getCallerClassName());
        logger.info(message);
        logger.info(System.getProperty("line.separator"));
    }

    public static void Error(String message) {
        Logger logger = LogManager.getLogger(getCallerClassName());
        logger.error(message);
        logger.info(System.getProperty("line.separator"));
    }

    public static void Warn(String message) {
        Logger logger = LogManager.getLogger(getCallerClassName());
        logger.warn(message);
        logger.info(System.getProperty("line.separator"));
    }

    public static void Debug(String message) {
        Logger logger = LogManager.getLogger(getCallerClassName());
        logger.debug(message);
        logger.info(System.getProperty("line.separator"));
    }

    private static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(Log.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) {
                return ste.getClassName();
            }
        }
        return null;
    }
}
