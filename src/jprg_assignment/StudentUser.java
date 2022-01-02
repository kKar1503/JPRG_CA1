
package jprg_assignment;

import javax.swing.*;

public class StudentUser {
    public static void menu(String[] user) {
        // Logs Student User
        UserActivityLogger.infoLog("Logged in as StudentUser: " + user[0]);
        
        // Check if user has full access to student datas
        boolean fullAccess = false;
        if (user[2].equals("Full_Access")) {
            fullAccess = true;
        }
        
        // Initialize userInput & intUserInput variable
        String userInput;
        int intUserInput = 0;
        
        try {
            do {
                // Initialize Mainpage
                userInput = JOptionPane.showInputDialog(null,
                        "Enter your option:\n\n"
                                + "1.  Display all students\n"
                                + "2.  Search Student by Name\n"
                                + "3.  Search Module by Name\n"
                                + "4.  Print Statistic\n"
                                + "5.  Exit\n ",
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
                            if (fullAccess) {
                                UserActivityLogger.infoLog("User accessed displayAllStudents.");
                                StudentManagement.displayAllStudents();
                            } else {
                                SoundPlayer.errorSound();
                                JOptionPane.showMessageDialog(null, 
                                        "You are not authorized!",
                                        "Forbidden",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        case 2:
                            UserActivityLogger.infoLog("User accessed searchStudent.");
                            StudentManagement.searchStudent(fullAccess, user[0]);
                            break;
                        case 3:
                            UserActivityLogger.infoLog("User accessed searchModule.");
                            StudentManagement.searchModule();
                            break;
                        case 4: 
                            if (fullAccess) {
                                UserActivityLogger.infoLog("User accessed printStatistics.");
                                StudentManagement.printStatistics();
                            } else {
                                SoundPlayer.errorSound();
                                JOptionPane.showMessageDialog(null, 
                                        "You are not authorized!",
                                        "Forbidden",
                                        JOptionPane.ERROR_MESSAGE);
                            }
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
        }
        
        // Log Program termination
        UserActivityLogger.infoLog("Program Terminated");
        // Good bye sound effect
        SoundPlayer.playSound("SoundEffects\\\\Bye.wav");
        
        // Program terminated
        JOptionPane.showMessageDialog(null, 
                "Program terminated.\nThank You!",
                "Message",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
