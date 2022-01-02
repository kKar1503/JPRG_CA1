
// Class : DIT / FT / 1B / 04
// Admission Number : P2123181
// Name : Yam Kar Lok

package jprg_assignment;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Administrator {
    public static void menu(String[] user) {
        // Logs Student User
        UserActivityLogger.infoLog("Logged in as Admin: " + user[0]);
        
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
                                + "5.  Admin Features\n"
                                + "6.  Exit\n ",
                        "Mini Student System (Admin)",
                        JOptionPane.QUESTION_MESSAGE);
                if (!VerifyInput.isInt(userInput)[0]) { 
                    SoundPlayer.errorSound();
                    // Handles empty input
                    JOptionPane.showMessageDialog(null, 
                            "Missing input! Please enter in the range from 1 to 6.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                } else if (!VerifyInput.isInt(userInput)[1]) { 
                    SoundPlayer.errorSound();
                    // Handles non-integer Input
                    JOptionPane.showMessageDialog(null, 
                            "Invalid input! Please enter only numeric value.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > 6) {
                    SoundPlayer.errorSound();
                    // Handles out of range input
                    JOptionPane.showMessageDialog(null,
                            "Invalid option! Please enter in the range from 1 to 6.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Handles input choices
                    intUserInput = Integer.parseInt(userInput);
                    switch(intUserInput) {
                        case 1:
                            UserActivityLogger.infoLog("Admin accessed displayAllStudents.");
                            StudentManagement.displayAllStudents();
                            break;
                        case 2:
                            UserActivityLogger.infoLog("Admin accessed searchStudent.");
                            StudentManagement.searchStudent(true, user[0]);
                            break;
                        case 3:
                            UserActivityLogger.infoLog("Admin accessed searchModule.");
                            StudentManagement.searchModule();
                            break;
                        case 4: 
                            UserActivityLogger.infoLog("Admin accessed printStatistics.");
                            StudentManagement.printStatistics();
                            break;
                        case 5:
                            UserActivityLogger.infoLog("Admin accessed adminMenu.");
                            adminMenu();
                            break;
                        case 6:
                            UserActivityLogger.infoLog("Admin terminated program.");
                            break;
                    }
                }
            } while (intUserInput != 6);
        } catch (NullPointerException npe) {
            // Handles admin clicking cancel
            UserActivityLogger.errLog("Admin selected cancel in Main Menu, terminating program", npe);
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
    
    private static void adminMenu() {
        // Initialize userInput & intUserInput variable
        String userInput;
        int intUserInput = 0;
        
        try {
            do {
                // Initialize Mainpage
                userInput = JOptionPane.showInputDialog(null,
                        "Enter your option:\n\n"
                                + "1.  Add Student\n"
                                + "2.  Delete Student\n"
                                + "3.  Modify Student Details\n"
                                + "4.  Return\n ",
                        "Admin Menu",
                        JOptionPane.QUESTION_MESSAGE);
                if (!VerifyInput.isInt(userInput)[0]) { 
                    SoundPlayer.errorSound();
                    // Handles empty input
                    JOptionPane.showMessageDialog(null, 
                            "Missing input! Please enter in the range from 1 to 4.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                } else if (!VerifyInput.isInt(userInput)[1]) { 
                    SoundPlayer.errorSound();
                    // Handles non-integer Input
                    JOptionPane.showMessageDialog(null, 
                            "Invalid input! Please enter only numeric value.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > 4) {
                    SoundPlayer.errorSound();
                    // Handles out of range input
                    JOptionPane.showMessageDialog(null,
                            "Invalid option! Please enter in the range from 1 to 4.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // Handles input choices
                    intUserInput = Integer.parseInt(userInput);
                    switch(intUserInput) {
                        case 1:
                            UserActivityLogger.infoLog("Admin initiated adding student.");
                            addStudent();
                            break;
                        case 2:
                            UserActivityLogger.infoLog("Admin initiated deleting student.");
                            deleteStudent();
                            break;
                        case 3:
                            UserActivityLogger.infoLog("Admin initiated modify student.");
                            modifyStudent();
                            break;
                        case 4: 
                            UserActivityLogger.infoLog("Admin exiting admin menu.");
                            break;
                    }
                }
            } while (intUserInput != 4);
        } catch (NullPointerException npe) {
            // Handles admin clicking cancel
            UserActivityLogger.errLog("Admin selected cancel in Admin Menu, Returning to Main Menu", npe);
        }
    }
    
    private static void addStudent() {
        boolean next = false;
        String name, course, adminNumber, moduleName, moduleCode;
        int intAdminNumber = 0, moduleCU = 0;
        double moduleMarks = 0.0;
        ArrayList<Module> moduleList = new ArrayList<>();
        
        try {
            // Input New Student Name
            do {
                name = JOptionPane.showInputDialog(null,
                        "Student's Name:",
                        "Adding New Student",
                        JOptionPane.QUESTION_MESSAGE);
                if (!name.isEmpty()){
                    next = true;
                } else {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                            "Student name cannot be null.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } while (!next);
            next = false;

            // Input New Student Course
            do {
                course = JOptionPane.showInputDialog(null,
                        "Student's Course:",
                        "Adding New Student",
                        JOptionPane.QUESTION_MESSAGE);
                if (!course.isEmpty()){
                    next = true;
                } else {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                            "Student course cannot be null.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } while (!next);
            next = false;

            // Input New Student Admin Number
            do {
                adminNumber = JOptionPane.showInputDialog(null,
                        "Student's Admin Number (without P):",
                        "Adding New Student",
                        JOptionPane.QUESTION_MESSAGE);
                if (!VerifyInput.isInt(adminNumber)[0]) {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                            "Student admin number cannot be null.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (!VerifyInput.isInt(adminNumber)[1]) {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                            "Student admin number has to be numeric.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    intAdminNumber = Integer.parseInt(adminNumber);
                    next = true;
                }
            } while (!next);
            next = false;


            // Input New Student Modules
            do {
                String numModule = JOptionPane.showInputDialog(null,
                        "Number of Module Student (" + name + ") is taking:",
                        "Adding New Student",
                        JOptionPane.QUESTION_MESSAGE);
                if (!VerifyInput.isInt(numModule)[0]) {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                            "Number of module cannot be null.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (!VerifyInput.isInt(numModule)[1]) {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                            "Number of module has to be numeric.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    int intNumModule = Integer.parseInt(numModule);
                    boolean modNext = false;
                    for (int i = 0; i < intNumModule; i++) {

                        // Input Student Module Name
                        do {
                            moduleName = JOptionPane.showInputDialog(null,
                                "Module " + (i+1) + " name:",
                                "Adding Module " + (i+1),
                                JOptionPane.QUESTION_MESSAGE);
                            if (!moduleName.isEmpty()) {
                                modNext = true;
                            }
                        } while (!modNext);
                        modNext = false;

                        // Input Student Module Code
                        do {
                            moduleCode = JOptionPane.showInputDialog(null,
                                "Module " + (i+1) + " code:",
                                "Adding Module " + (i+1),
                                JOptionPane.QUESTION_MESSAGE);
                            if (!moduleCode.isEmpty()) {
                                modNext = true;
                            }
                        } while (!modNext);
                        modNext = false;

                        // Input Student Module Credit Unit
                        do {
                            String strModuleCU = JOptionPane.showInputDialog(null,
                                "Module " + (i+1) + " credit unit:",
                                "Adding Module " + (i+1),
                                JOptionPane.QUESTION_MESSAGE);
                            if (!VerifyInput.isInt(strModuleCU)[0]) {
                                SoundPlayer.errorSound();
                                JOptionPane.showMessageDialog(null, 
                                    "Module credit unit cannot be null.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            } else if (!VerifyInput.isInt(strModuleCU)[1]) {
                                SoundPlayer.errorSound();
                                JOptionPane.showMessageDialog(null, 
                                    "Module credit unit has to be numeric.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            } else if (Integer.parseInt(strModuleCU) < 0) {
                                SoundPlayer.errorSound();
                                JOptionPane.showMessageDialog(null, 
                                    "Module credit unit cannot be negative.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            } else {
                                moduleCU = Integer.parseInt(strModuleCU);
                                modNext = true;
                            }
                        } while (!modNext);
                        modNext = false;

                        // Input Student Module Marks
                        do {
                            String strModuleMarks = JOptionPane.showInputDialog(null,
                                "Module " + (i+1) + " marks:",
                                "Adding Module " + (i+1),
                                JOptionPane.QUESTION_MESSAGE);
                            if (!VerifyInput.isDouble(strModuleMarks)[0]) {
                                SoundPlayer.errorSound();
                                JOptionPane.showMessageDialog(null, 
                                    "Module marks cannot be null.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            } else if (!VerifyInput.isDouble(strModuleMarks)[1]) {
                                SoundPlayer.errorSound();
                                JOptionPane.showMessageDialog(null, 
                                    "Module marks has to be numeric.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            } else if (Integer.parseInt(strModuleMarks) < 0 || Integer.parseInt(strModuleMarks) > 100) {
                                SoundPlayer.errorSound();
                                JOptionPane.showMessageDialog(null, 
                                    "Module marks can only be between 0 and 100.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            } else {
                                moduleMarks = Double.parseDouble(strModuleMarks);
                                modNext = true;
                            }
                        } while (!modNext);
                        Module tempModule = new Module(moduleCode, moduleName, moduleCU, moduleMarks);
                        moduleList.add(tempModule);
                    }
                    next = true;
                }
            } while (!next);
            Student tempStudent = new Student(name, course, intAdminNumber, moduleList);
            StudentManagement.addNewStudent(tempStudent);
        } catch (NullPointerException npe) {
            // Handles user clicking cancel
            UserActivityLogger.errLog("Admin cancelled while adding students, returning to admin menu.", npe);
        }
    }
    
    private static void deleteStudent() {
        try {
            ArrayList<Student> displayStudents = StudentManagement.getStudentList();

            if (!displayStudents.isEmpty()) {

                String message = "Which student would you like to remove?\n\n";

                for (int i = 0; i < displayStudents.size(); i++) {
                    message += (i+1) + ".  " + displayStudents.get(i).getName() + "\n";
                }

                message += "\nPlease enter the respective index number.";

                String userInput = JOptionPane.showInputDialog(null, 
                        message,
                        "Delete Student",
                        JOptionPane.WARNING_MESSAGE);
                if (!VerifyInput.isInt(userInput)[0]) {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                        "Index cannot be null.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                } else if (!VerifyInput.isInt(userInput)[1]) {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                        "Index has to be numeric.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > displayStudents.size()){
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                        "Index has to be within 1 and " + displayStudents.size(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    StudentManagement.deleteStudent(Integer.parseInt(userInput)-1);
                }
            } else {
                SoundPlayer.errorSound();
                JOptionPane.showMessageDialog(null, 
                        "There are no students in the system.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                UserActivityLogger.errLog("No students found in system.", new Throwable("Missing Data"));
            }
        } catch (NullPointerException npe) {
            // Handles user clicking cancel
            UserActivityLogger.errLog("Admin cancelled while adding students, returning to admin menu.", npe);
        }
    }
    
    private static void modifyStudent() {
        try {
            ArrayList<Student> displayStudents = StudentManagement.getStudentList();

            if (!displayStudents.isEmpty()) {
                String message = "Which student would you like to modify?\n\n";

                for (int i = 0; i < displayStudents.size(); i++) {
                    message += (i+1) + ".  " + displayStudents.get(i).getName() + "\n";
                }

                message += "\nPlease enter the respective index number.";

                String userInput = JOptionPane.showInputDialog(null, 
                        message,
                        "Modify Student",
                        JOptionPane.WARNING_MESSAGE);
                if (!VerifyInput.isInt(userInput)[0]) {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                        "Index cannot be null.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                } else if (!VerifyInput.isInt(userInput)[1]) {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                        "Index has to be numeric.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > displayStudents.size()){
                    JOptionPane.showMessageDialog(null, 
                        "Index has to be within 1 and " + displayStudents.size());
                } else {
                    message = "Which student data you would like to modify?\n\n"
                            + "1.  Name\n"
                            + "2.  Course\n"
                            + "3.  Admin Number\n"
                            + "\nPlease enter the respective number.";
                    String userInput2 = JOptionPane.showInputDialog(null, 
                        message,
                        "Modify Student",
                        JOptionPane.WARNING_MESSAGE);
                    if (!VerifyInput.isInt(userInput2)[0]) {
                        SoundPlayer.errorSound();
                        JOptionPane.showMessageDialog(null, 
                            "Index cannot be null.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    } else if (!VerifyInput.isInt(userInput2)[1]) {
                        SoundPlayer.errorSound();
                        JOptionPane.showMessageDialog(null, 
                            "Index has to be numeric.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    } else if (Integer.parseInt(userInput2) < 1 || Integer.parseInt(userInput2) > 3){
                        JOptionPane.showMessageDialog(null, 
                            "Index has to be within 1 and 3.");
                    } else {
                        switch(Integer.parseInt(userInput2)) {
                            case 1:
                                String newName = JOptionPane.showInputDialog(null,
                                        "Student's new name:",
                                        "Modify Student",
                                        JOptionPane.QUESTION_MESSAGE);
                                displayStudents.get(Integer.parseInt(userInput)-1).setName(newName);
                                break;
                            case 2:
                                String newCourse = JOptionPane.showInputDialog(null,
                                        "Student's new course:",
                                        "Modify Student",
                                        JOptionPane.QUESTION_MESSAGE);
                                displayStudents.get(Integer.parseInt(userInput)-1).setCourse(newCourse);
                                break;
                            case 3:
                                String newAdminNumber = JOptionPane.showInputDialog(null,
                                        "Student's new admin number:",
                                        "Modify Student",
                                        JOptionPane.QUESTION_MESSAGE);
                                if (!VerifyInput.isInt(newAdminNumber)[0]) {
                                    SoundPlayer.errorSound();
                                    JOptionPane.showMessageDialog(null, 
                                        "Admin number cannot be null.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                } else if (!VerifyInput.isInt(newAdminNumber)[1]) {
                                    SoundPlayer.errorSound();
                                    JOptionPane.showMessageDialog(null, 
                                        "Admin number has to be numeric.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                } else {
                                displayStudents.get(Integer.parseInt(userInput)-1).setAdminNumber(Integer.parseInt(newAdminNumber));
                                }
                                break;
                        }
                    }
                }
            } else {
                SoundPlayer.errorSound();
                JOptionPane.showMessageDialog(null, 
                        "There are no students in the system.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                UserActivityLogger.errLog("No students found in system.", new Throwable("Missing Data"));
            }
        } catch (NullPointerException npe) {
            // Handles user clicking cancel
            UserActivityLogger.errLog("Admin cancelled while adding students, returning to admin menu.", npe);
        }
    }
}
