
// Class : DIT / FT / 1B / 04
// Admission Number : P2123181
// Name : Yam Kar Lok

package jprg_assignment;

import java.util.logging.*;
import java.io.*;

public class UserActivityLogger {
    // Initialize a static constant for global logger
    private final static Logger LOGR = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    // Method to initialize logger
    public static void setupLogger(){
        LOGR.setUseParentHandlers(false);
        try {
            // Create FileHandler to log information into log files
            FileHandler fh = new FileHandler("UserActivity.log", 1000000, 1, true);
            fh.setLevel(Level.FINE);
            LOGR.addHandler(fh);
            
            // Simplify format of logger instead of xml output
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            // Throw IOException when file logging fails
            LOGR.log(Level.SEVERE, "File logger is not working!", e);
        }
    }
    
    // Method for general info log
    public static void infoLog(String logMessage){
        LOGR.info(logMessage);
    }
    
    // Method for general error log
    public static void errLog(String errMessage, Throwable error){
        LOGR.log(Level.SEVERE, errMessage, error);
    }
}
