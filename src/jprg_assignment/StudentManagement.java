
// Class : DIT / FT / 1B / 04
// Admission Number : P2123181
// Name : Yam Kar Lok

package jprg_assignment;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class StudentManagement {
    
    private static ArrayList<Student> studentList;
    
    public static void initializeStudents() {
        studentList = ObjectIO.studentDeserialization();
        
        UserActivityLogger.infoLog("3 Default Students initialized");
    }
    
    public static DefaultTableModel displayAllStudents() {
        if (!studentList.isEmpty()) {
            // Initialize ArrayList to store all information for display
            ArrayList<Object[]> displayInfo = new ArrayList<>();

            // For loop to fill in students' data from studentList into ArrayList
            for (int i = 0; i < studentList.size(); i++) {
                Object[] tempArray = {
                    studentList.get(i).getName(), 
                    studentList.get(i).getCourse(),
                    "P" + studentList.get(i).getAdminNumber(),
                    studentList.get(i).getModuleInfo()[0],
                    studentList.get(i).getModuleMarks()[0],
                };
                displayInfo.add(tempArray);
                for (int j = 1; j < studentList.get(i).getModuleList().size(); j++) {
                    Object[] tempArray2 = {
                        null,
                        null,
                        null,
                        studentList.get(i).getModuleInfo()[j],
                        studentList.get(i).getModuleMarks()[j],
                    };
                    displayInfo.add(tempArray2);
                }
                if (i != studentList.size() - 1) {
                    Object[] separator = {
                        null,
                        null,
                        null,
                        null,
                        null
                    };
                    displayInfo.add(separator);
                }
            }

            // Initialize data from ArrayList into Array of Object Array and String
            // Array for column headers
            Object[][] rows = displayInfo.toArray(new Object[][]{});
            String[] cols = {
                "Name", "Course", "Admin Number", "Modules", "Marks"
            };

            // Display all information generated using JOptionPane
            UserActivityLogger.infoLog("Displayed all students' data.");
            DefaultTableModel tableModel = new DefaultTableModel(rows, cols) {
                // Override isCellEditable to false
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            return tableModel;
        } else {
            SoundPlayer.errorSound();
            JOptionPane.showMessageDialog(null, 
                    "There are no students in the system.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            UserActivityLogger.errLog("No students found in system.", new Throwable("Missing Data"));
            return null;
        }
    }
    
    public static void searchStudent(boolean fullAccess, String username) {
        if (!studentList.isEmpty()) {
            try {
                String userInput;

                if (fullAccess) {
                    // Take in userInput with showInputDialog
                    userInput = JOptionPane.showInputDialog(null, 
                            "Enter the Student name to search:\n(Search is not case-sensitive)", 
                            "Input", 
                            JOptionPane.QUESTION_MESSAGE);
                } else {
                    // Limited access option
                    userInput = username;
                }

                // Initialize a boolean variable to handle no results found from search
                boolean resultFound = false;

                // For-loop to loop through studentList to find match with userInput
                for (int i = 0; i < studentList.size(); i++) {
                    // Utilizing toLowerCase() to ensure search is not case-sensitive
                    if(studentList.get(i).getName().toLowerCase().equals(userInput.toLowerCase())) {
                        //Initialize ArrayList for displayInfo to display on search
                        ArrayList<Object[]> displayInfo = new ArrayList<>();
                        Object[] tempArray = {
                            studentList.get(i).getName(), 
                            studentList.get(i).getCourse(),
                            "P" + studentList.get(i).getAdminNumber(),
                            studentList.get(i).getModuleInfo()[0],
                            studentList.get(i).getModuleMarks()[0],
                        };
                        displayInfo.add(tempArray);
                        for (int j = 1; j < studentList.get(i).getModuleList().size(); j++) {
                            Object[] tempArray2 = {
                                null,
                                null,
                                null,
                                studentList.get(i).getModuleInfo()[j],
                                studentList.get(i).getModuleMarks()[j],
                            };
                            displayInfo.add(tempArray2);
                        }
                        Object[] separator = {
                            null,
                            null,
                            null,
                            null,
                            null
                        };
                        displayInfo.add(separator);
                        // Add additional row at the bottom of table to display GPA
                        Object[] gpa = {
                            null,
                            null,
                            null,
                            "GPA:",
                            studentList.get(i).getGpa()
                        };
                        displayInfo.add(gpa);

                        // Initialize data from ArrayList into Array of Object Array and String
                        // Array for column headers
                        Object[][] rows = displayInfo.toArray(new Object[][]{});
                        String[] cols = {
                            "Name", "Course", "Admin Number", "Modules", "Marks"
                        };

                        UserActivityLogger.infoLog("User Searched for student: " + studentList.get(i).getName());

                        // Display all information generated using JOptionPane
                        JOptionPane.showMessageDialog(null, 
                                generateTable(rows, cols),
                                "Message",
                                JOptionPane.INFORMATION_MESSAGE);
                        resultFound = true;
                    }
                }
                // Handle no search result found
                if (!resultFound) {
                    SoundPlayer.errorSound();
                    UserActivityLogger.errLog("User not found: " + userInput, 
                            new Throwable("User not found"));
                    JOptionPane.showMessageDialog(null, 
                            "Cannot find the student \"" + userInput + "\"!!", 
                            "Info", 
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NullPointerException npe) {
                // Handles user clicking cancel
                UserActivityLogger.errLog("User selected cancel in searchStudent, returning to Main Menu", npe);
            }
        } else {
            SoundPlayer.errorSound();
            JOptionPane.showMessageDialog(null, 
                    "There are no students in the system.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            UserActivityLogger.errLog("No students found in system.", new Throwable("Missing Data"));
        }
    }
    
    public static void searchModule() {
        if (!studentList.isEmpty()) {
            try {
                // Take in userInput with showInputDialog
                String userInput = JOptionPane.showInputDialog(null, 
                        "Enter the Module name to search:\n(Search is not case-sensitive)", 
                        "Input", 
                        JOptionPane.QUESTION_MESSAGE);

                // Initialize a integer variable to keep track of number of matching results found
                int resultFound = 0;

                // Initialize a double variable to sum all the matching results' marks
                double totalMarks = 0;

                // For-loop to loop through all students' moduleList to find matching modules
                for (int i = 0; i < studentList.size(); i++) {
                    for (int j = 0; j < studentList.get(i).getModuleList().size(); j++) {
                        if(studentList.get(i).getModuleList().get(j).getName().toLowerCase().equals(userInput.toLowerCase())) {
                            resultFound ++;
                            totalMarks += studentList.get(i).getModuleList().get(j).getMarks();
                        }
                    }
                }

                if (resultFound > 0) {
                    double averageMarks = totalMarks / resultFound;
                    JOptionPane.showMessageDialog(null, 
                            "There are " + resultFound + " student(s) taking " + userInput.toUpperCase()
                            + " module.\n The average marks for " + userInput.toUpperCase()
                            + " is " + String.format("%.1f", averageMarks),
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                            "No student taking " + userInput.toUpperCase() + ".",
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NullPointerException npe) {
                // Handles user clicking cancel
                UserActivityLogger.errLog("User selected cancel in searchModule, returning to Main Menu", npe);
            }
        } else {
            SoundPlayer.errorSound();
            JOptionPane.showMessageDialog(null, 
                    "There are no students in the system.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            UserActivityLogger.errLog("No students found in system.", new Throwable("Missing Data"));
        }
    }
    
    public static void printStatistics() {
        if (!studentList.isEmpty()) {
            String userInput;
            int intUserInput = 0, numGoodStudent = 0, numBadStudent = 0;
            for (int i = 0; i < studentList.size(); i++) {
                double studentGpa = Double.parseDouble(studentList.get(i).getGpa());
                if (studentGpa > 3.5) {
                    numGoodStudent ++;
                } else if (studentGpa < 1) {
                    numBadStudent ++;
                } else {
                    // Do Nothing
                }
                System.out.println(studentList.get(i).getGpa());
            }
            double percentGoodStudent = (double)numGoodStudent / studentList.size() * 100;
            double percentBadStudent = (double)numBadStudent / studentList.size() * 100;
            try {
                JOptionPane.showMessageDialog(null, 
                        "STATISTIC:\n"
                                + "---------------------\n\n"
                                + "There are " + studentList.size() + " students in total.\n\n"
                                + "There is/are " + numGoodStudent + " student(s) "
                                + "getting GPA greater than 3.5. This is "
                                + String.format("%.2f", percentGoodStudent) + "%.\n\n"
                                + "There is/are " + numBadStudent + " student(s) "
                                + "getting GPA less than 1. This is "
                                + String.format("%.2f", percentBadStudent) + "%.\n ",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                do {
                    userInput = JOptionPane.showInputDialog(null,
                            "Would you like to export the full student report into excel?\n\n"
                                    + "       1.  Confirm\n"
                                    + "       2.  Cancel\n ",
                            "Export Report",
                            JOptionPane.QUESTION_MESSAGE);
                    if (!VerifyInput.isInt(userInput)[0]) {
                        SoundPlayer.errorSound();
                        // Handles empty input
                        JOptionPane.showMessageDialog(null, 
                                "Missing Input! Please enter either 1 or 2.", 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                    } else if (!VerifyInput.isInt(userInput)[1]) { 
                        SoundPlayer.errorSound();
                        // Handles non-integer Input
                        JOptionPane.showMessageDialog(null, 
                                "Invalid input! Please enter only numeric value.", 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                    } else if (!(Integer.parseInt(userInput) == 1 || Integer.parseInt(userInput) == 2)) {
                        SoundPlayer.errorSound();
                        // Handles out of range input
                        JOptionPane.showMessageDialog(null,
                                "Invalid option! Please enter either 1 or 2.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Handles input choices
                        intUserInput = Integer.parseInt(userInput);
                        switch(intUserInput) {
                            case 1:
                                ReportExport.generateReport(studentList);
                                UserActivityLogger.infoLog("User exported full student report.");
                                break;
                            case 2:
                                JOptionPane.showMessageDialog(null,
                                        "Returning to main menu...");
                                break;
                        }
                    }
                } while (!(intUserInput == 1 || intUserInput == 2));
            } catch (NullPointerException npe) {
                // Handles user clicking cancel
                UserActivityLogger.errLog("User selected cancel in printStatistic, returning to Main Menu", npe);
            }
        } else {
            SoundPlayer.errorSound();
            JOptionPane.showMessageDialog(null, 
                    "There are no students in the system.\nNo statistic to generate.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            UserActivityLogger.errLog("No students found in system.", new Throwable("Missing Data"));
        }
    }
    
    // Method returns Table in JScrollPane given row and col data
    public static JScrollPane generateTable(Object[][] rows, String[] cols){
        JTable table = new JTable(rows, cols);
        
        // Center all cell values 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < 5; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Remove Editor in table to prevent user from editting data in table
        table.setDefaultEditor(Object.class, null);
        
        // Set Column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(75);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(75);
        
        // Initialize JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(975,495));
        return scrollPane;
    }
    
    public static void addNewStudent(Student student) {
        studentList.add(student);
        JOptionPane.showMessageDialog(null, 
                "A student has been added. There is a total of " + studentList.size() + " students now.",
                "Notice",
                JOptionPane.INFORMATION_MESSAGE);
        UserActivityLogger.infoLog("Added new student to studentList.");
    }
    
    public static void deleteStudent(int index) {
        UserActivityLogger.infoLog("Deleted 1x student: " + studentList.get(index).getName());
        JOptionPane.showMessageDialog(null, 
                "Student (" + studentList.get(index).getName() + ") has been removed.",
                "Notice",
                JOptionPane.INFORMATION_MESSAGE);
        studentList.remove(index);
        
    }
    
    public static ArrayList<Student> getStudentList() {
        return studentList;
    }
}
