
package jprg_assignment;

import javax.swing.*;
import java.util.*;

public class StudentUser {
    public static void main(String[] args) {
        // Sound effect for starting up the application
        SoundPlayer.playSound("SoundEffects\\\\Start.wav");
        // Initialize logger
        UserActivityLogger.setupLogger();
        // Initialize Default Students
        StudentManagement.initializeStudents();
        
        // Initialize userInput & intUserInput variable
        String userInput;
        int intUserInput = 0;
        
        try {
            do {
                // Initialize Mainpage
                userInput = JOptionPane.showInputDialog(null,
                        "Enter your option:\n\n"
                                + "1.\tDisplay all students\n"
                                + "2.\tSearch Student by Name\n"
                                + "3.\tSearch Module by Name\n"
                                + "4.\tPrint Statistic\n"
                                + "5.\tExit\n ",
                        "Mini Student System",
                        JOptionPane.QUESTION_MESSAGE);
                if (!VerifyInput.isInt(userInput)[0]) { 
                    SoundPlayer.errorSound();
                    // Handles empty input
                    JOptionPane.showMessageDialog(null, 
                            "Missing input! Please enter in the range from 1 to 5.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                } else if (!VerifyInput.isInt(userInput)[1]) { 
                    SoundPlayer.errorSound();
                    // Handles non-integer Input
                    JOptionPane.showMessageDialog(null, 
                            "Invalid input! Please enter only numeric value.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > 5) {
                    SoundPlayer.errorSound();
                    // Handles out of range input
                    JOptionPane.showMessageDialog(null,
                            "Invalid option! Please enter in the range from 1 to 5.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Handles input choices
                    intUserInput = Integer.parseInt(userInput);
                    switch(intUserInput) {
                        case 1:
                            UserActivityLogger.infoLog("User accessed displayAllStudents.");
                            StudentManagement.displayAllStudents();
                            break;
                        case 2:
                            UserActivityLogger.infoLog("User accessed searchStudent.");
                            StudentManagement.searchStudent();
                            break;
                        case 3:
                            UserActivityLogger.infoLog("User accessed searchModule.");
                            StudentManagement.searchModule();
                            break;
                        case 4: 
                            UserActivityLogger.infoLog("User accessed printStatistics.");
                            StudentManagement.printStatistics();
                            break;
                        case 5:
                            UserActivityLogger.infoLog("User terminated program.");
                            break;
                    }
                }
            } while (intUserInput != 5);
        } catch (NullPointerException npe) {
            // Handles user clicking cancel
            UserActivityLogger.errLog("User selected cancel in Main Menu, terminating program", npe);
        };
        
        // Good bye sound effect
        SoundPlayer.playSound("SoundEffects\\\\Bye.wav");
        
        // Program terminated
        JOptionPane.showMessageDialog(null, 
                "Program terminated.\nThank You!",
                "Message",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
