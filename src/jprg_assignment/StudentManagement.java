// Class : DIT / FT / 1B / 04
// Admission Number : P2123181
// Name : Yam Kar Lok
package jprg_assignment;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class StudentManagement {

    private static ArrayList<Student> studentList = new ArrayList<>();

    public static DefaultTableModel displayAllStudents() {
        // Array for column headers
        String[] cols = {
            "Name", "Course", "Admin Number", "Modules", "Marks"
        };
        // Initialize tableModel
        DefaultTableModel tableModel;
        if (!studentList.isEmpty()) {
            // Initialize ArrayList to store all information for display
            ArrayList<Object[]> displayInfo = new ArrayList<>();

            // For loop to fill in students' data from studentList into ArrayList
            for (int i = 0; i < studentList.size(); i++) {
                Object[] tempArray = {
                    studentList.get(i) instanceof InternationalStudent
                    ? studentList.get(i).getName() + " (International Student)"
                    : studentList.get(i).getName() + " (Local Student)",
                    studentList.get(i).getCourse(),
                    "P" + studentList.get(i).getAdminNumber(),
                    studentList.get(i).getModuleInfo()[0],
                    studentList.get(i).getModuleMarks()[0],};
                displayInfo.add(tempArray);
                for (int j = 1; j < studentList.get(i).getModuleList().size(); j++) {
                    Object[] tempArray2 = {
                        null,
                        null,
                        null,
                        studentList.get(i).getModuleInfo()[j],
                        studentList.get(i).getModuleMarks()[j],};
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
            Object[][] rows = displayInfo.toArray(new Object[][]{});

            // Display all information generated using tableModel
            UserActivityLogger.infoLog("Displayed all students' data.");
            tableModel = new DefaultTableModel(rows, cols) {
                // Override isCellEditable to false
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        } else {
            UserActivityLogger.errLog("No students found in system.", new Throwable("Missing Data"));
            tableModel = new DefaultTableModel(0, cols.length) {
                // Override isCellEditable to false
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tableModel.setColumnIdentifiers(cols);
        }
        return tableModel;
    }

    public static ArrayList<Student> searchStudent(boolean fullAccess, String username, String query) {
        ArrayList<Student> results = new ArrayList<>();
        if (!studentList.isEmpty()) {
            String userInput;
            boolean resultFound = false;

            if (fullAccess) {
                // Use query to search
                userInput = query;
            } else {
                // Limited access option
                userInput = username;
            }

            // For-loop to loop through studentList to find match with userInput
            for (int i = 0; i < studentList.size(); i++) {
                // Utilizing contains with toLowerCase() to ensure search is not case-sensitive
                if (studentList.get(i).getName().toLowerCase().contains(userInput.toLowerCase())) {
                    results.add(studentList.get(i));
                }
            }
            // Handle no search result found
            if (results.isEmpty()) {
                SoundPlayer.errorSound();
                UserActivityLogger.errLog("User not found: " + userInput,
                        new Throwable("User not found"));
            }
        } else {
            SoundPlayer.errorSound();
            UserActivityLogger.errLog("No students found in system.", new Throwable("Missing Data"));
        }
        return results;
    }

    public static ArrayList<Module> searchModule(String query) {
        ArrayList<Module> results = new ArrayList<>();
        if (!studentList.isEmpty()) {

            // For-loop to loop through all students' moduleList to find matching modules
            for (int i = 0; i < studentList.size(); i++) {
                for (int j = 0; j < studentList.get(i).getModuleList().size(); j++) {
                    if (studentList.get(i).getModuleList().get(j).getName().equalsIgnoreCase(query)) {
                        results.add(studentList.get(i).getModuleList().get(j));
                    }
                }
            }
            if (results.isEmpty()) {
                SoundPlayer.errorSound();
                UserActivityLogger.errLog("Module not found: " + query,
                        new Throwable("Module not found"));
            }
        } else {
            SoundPlayer.errorSound();
            UserActivityLogger.errLog("No students found in system.", new Throwable("Missing Data"));
        }
        return results;
    }

    public static String printStatistics() {
        String returnString;
        if (!studentList.isEmpty()) {
            int numGoodStudent = 0, numBadStudent = 0;
            for (int i = 0; i < studentList.size(); i++) {
                double studentGpa = Double.parseDouble(studentList.get(i).getGpa());
                if (studentGpa > 3.5) {
                    numGoodStudent++;
                } else if (studentGpa < 1) {
                    numBadStudent++;
                } else {
                    // Do Nothing
                }
            }
            double percentGoodStudent = (double) numGoodStudent / studentList.size() * 100;
            double percentBadStudent = (double) numBadStudent / studentList.size() * 100;
            returnString = "STATISTIC:\n"
                    + "---------------------\n\n"
                    + "There are " + studentList.size() + " students in total.\n\n"
                    + "There is/are " + numGoodStudent + " student(s) "
                    + "getting GPA greater than 3.5. This is "
                    + String.format("%.2f", percentGoodStudent) + "%.\n\n"
                    + "There is/are " + numBadStudent + " student(s) "
                    + "getting GPA less than 1. This is "
                    + String.format("%.2f", percentBadStudent) + "%.\n ";
        } else {
            returnString = "NO STUDENT IN DATABASE FOUND\n"
                    + "---------------------\n\n"
                    + "If you think there may be an error, "
                    + "please check the Students.txt file for import or "
                    + "check the UserActivity.log file.";
            UserActivityLogger.errLog("No students found in system.", new Throwable("Missing Data"));
        }
        return returnString;
    }

    // Method returns Table in JScrollPane given row and col data
    public static JScrollPane generateTable(Object[][] rows, String[] cols) {
        JTable table = new JTable(rows, cols);

        // Center all cell values 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
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
        scrollPane.setPreferredSize(new Dimension(975, 495));
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
