
// Class : DIT / FT / 1B / 04
// Admission Number : P2123181
// Name : Yam Kar Lok

package jprg_assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ReportExport {
    public static void generateReport(ArrayList<Student> studentList) {
        int num = 0;
        
        // Set file name declaration to export_num.csv
        String filename = "Export_" + String.valueOf(num);
        File file = new File(filename + ".csv");
        
        // While loop to check for available filename
        while (file.exists()) {
            num ++;
            filename = "Export_" + String.valueOf(num);
            file = new File(filename + ".csv");
        }
        try {
            // Setting up PrintWriter for creating csv
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            // Setting up header
            pw.println("Student Name,Admin Number,Student Course,Student GPA,"
                    + "Student Module Name,Student Module Code,Student Module Credit Unit,Student Module Marks");
            
            // Deconstructing information from Student ArrayList
            for (int i = 0; i < studentList.size(); i++) {
                for (int j = 0; j < studentList.get(i).getModuleList().size(); j++) {
                    pw.println(studentList.get(i).getName() + ","
                    + studentList.get(i).getAdminNumber() + ","
                    + studentList.get(i).getCourse() + ","
                    + studentList.get(i).getGpa() + ","
                    + studentList.get(i).getModuleList().get(j).getName() + ","
                    + studentList.get(i).getModuleList().get(j).getCode() + ","
                    + studentList.get(i).getModuleList().get(j).getCreditUnit() + ","
                    + studentList.get(i).getModuleList().get(j).getMarks());
                }
            }
            
            pw.flush();
            pw.close();
            
            UserActivityLogger.infoLog("Successfully exported Full Student Reports to " + filename);
            
            JOptionPane.showMessageDialog(null, 
                    "Successfully generate Full Student Report (" + filename + ".csv).");
        } catch (Exception e) {
            UserActivityLogger.errLog("Export to csv failed.", e);
            JOptionPane.showMessageDialog(null, 
                    "Record not saved due to some error.\nCheck log file for more details.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
