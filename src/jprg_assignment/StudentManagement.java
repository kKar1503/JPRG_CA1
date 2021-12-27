
package jprg_assignment;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class StudentManagement {
    
    private static ArrayList<Student> studentList = new ArrayList<>();
    
    public static void initializeStudents() {
        // Modules for Student 1
        Module m1 = new Module("ST0501", "FED", 5, 75.0);
        Module m2 = new Module("ST0502", "FOP", 6, 85.0);
        Module m3 = new Module("ST2413", "FOC", 4, 70.0);
        Module m4 = new Module("LC0860", "CAT", 2, 65.0);
        Module m5 = new Module("LC0855", "CPR", 2, 90.0);
        
        // Modules for Student 2
        Module m6 = new Module("ST0503", "BED", 5, 90.0);
        Module m7 = new Module("ST0504", "MAD", 4, 59.0);
        Module m8 = new Module("ST0509", "JPRG", 6, 82.0);
        Module m9 = new Module("MS0105", "MATH", 4, 70.0);
        Module m10 = new Module("LC0861", "NAT", 2, 75.0);
        
        // Modules for Student 3
        Module m11 = new Module("ST0501", "FED", 5, 50.0);
        Module m12 = new Module("ST0503", "BED", 5, 65.0);
        Module m13 = new Module("ST0509", "JPRG", 6, 60.0);
        Module m14 = new Module("ST0277", "DEUI", 3, 49.0);
        Module m15 = new Module("LC0861", "NAT", 2, 70.0);
        Module m16 = new Module("LC0855", "CPR", 2, 80.0);
        
        // Module ArrayLists for Students
        ArrayList<Module> moduleList1 = new ArrayList<>(Arrays.asList(m1,m2,m3,m4,m5)); // Student 1
        ArrayList<Module> moduleList2 = new ArrayList<>(Arrays.asList(m6,m7,m8,m9,m10)); // Student 2
        ArrayList<Module> moduleList3 = new ArrayList<>(Arrays.asList(m11,m12,m13,m14,m15,m16)); // Student 3
        
        // Initialize Students
        Student s1 = new Student("John Tan", "DCITP", 2123181, moduleList1);
        Student s2 = new Student("James Lim", "DIT", 2134599, moduleList2);
        Student s3 = new Student("Jimmy Loh", "DAAA", 2136034, moduleList3);
        
        // Initialize Student ArrayList
        studentList.addAll(Arrays.asList(s1, s2, s3));
        
        UserActivityLogger.infoLog("3 Default Students initialized");
    }
    
    public static void displayAllStudents() {
        if (studentList.size() != 0) {
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
                };
            };

            // Initialize data from ArrayList into Array of Object Array and String
            // Array for column headers
            Object[][] rows = displayInfo.toArray(new Object[][]{});
            String[] cols = {
                "Name", "Course", "Admin Number", "Modules", "Marks"
            };

            // Display all information generated using JOptionPane
            JOptionPane.showMessageDialog(null, 
                    generateTable(rows, cols), 
                    "All Students", 
                    JOptionPane.INFORMATION_MESSAGE);
            UserActivityLogger.infoLog("Displayed all students' data.");
        } else {
            SoundPlayer.errorSound();
            JOptionPane.showMessageDialog(null, 
                    "There are no students in the system.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            UserActivityLogger.errLog("No students found in system.", new Throwable("Missing Data"));
        };
    }
    
    public static void searchStudent() {
        // Take in userInput with showInputDialog
        String userInput = JOptionPane.showInputDialog(null, 
                "Enter the Student name to search:\n(Search is not case-sensitive)", 
                "Input", 
                JOptionPane.QUESTION_MESSAGE);
        
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
                };
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
                    "GPA:",
                    studentList.get(i).getGpa(),
                    null
                };
                displayInfo.add(gpa);
                
                // Initialize data from ArrayList into Array of Object Array and String
                // Array for column headers
                Object[][] rows = displayInfo.toArray(new Object[][]{});
                String[] cols = {
                    "Name", "Course", "Admin Number", "Modules", "Marks"
                };
                
                // Display all information generated using JOptionPane
                JOptionPane.showMessageDialog(null, 
                        generateTable(rows, cols),
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                resultFound = true;
            };
        };
        // Handle no search result found
        if (!resultFound) {
            JOptionPane.showMessageDialog(null, 
                    "Cannot find the student \"" + userInput + "\"!!", 
                    "Info", 
                    JOptionPane.ERROR_MESSAGE);
        };
    }
    
    public static void searchModule() {
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
            JOptionPane.showMessageDialog(null, 
                    "No student taking " + userInput.toUpperCase() + ".",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void printStatistics() {
        
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
        scrollPane.setPreferredSize(new Dimension(500,250));
        return scrollPane;
    }
    
}
