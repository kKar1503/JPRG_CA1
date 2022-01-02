
package jprg_assignment;

import java.util.ArrayList; // Import ArrayList class for resizable array.

public class Student {
    private String name, course;
    private int adminNumber;
    private double gpa;
    private ArrayList<Module> studentModuleList = new ArrayList<>();
    
    public Student() {
    }
    
    public Student (String name, String course, int adminNumber, ArrayList<Module> modules){
        this.name = name;
        this.course = course;
        this.adminNumber = adminNumber;
        this.studentModuleList = modules;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCourse() {
        return this.course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public int getAdminNumber() {
        return this.adminNumber;
    }
    
    public void setAdminNumber(int adminNumber) {
        this.adminNumber = adminNumber;
    }
    
    public ArrayList<Module> getModuleList() {
        return studentModuleList;
    }
    
    public String getGpa() {
        double totalGradePoints = 0;
        int totalCreditUnits = 0;
        for (int i = 0; i < studentModuleList.size(); i++) {
            if (studentModuleList.get(i).getMarks() >= 80) {
                totalGradePoints += 4 * studentModuleList.get(i).getCreditUnit();
            } else if (studentModuleList.get(i).getMarks() >= 70) {
                totalGradePoints += 3 * studentModuleList.get(i).getCreditUnit();
            } else if (studentModuleList.get(i).getMarks() >= 60) {
                totalGradePoints += 2 * studentModuleList.get(i).getCreditUnit();
            } else if (studentModuleList.get(i).getMarks() >= 50) {
                totalGradePoints += studentModuleList.get(i).getCreditUnit();
            } else {
                totalGradePoints += 0;
            }
            totalCreditUnits += studentModuleList.get(i).getCreditUnit();
        }
        this.gpa = totalGradePoints / totalCreditUnits;
        return String.format("%.2f",this.gpa);
    }
        
    public String[] getModuleInfo() {
        String[] moduleInfo = new String[studentModuleList.size()];
        for (int i = 0; i < studentModuleList.size(); i++) {
            String tempStr = studentModuleList.get(i).getCode() + "/"
                    + studentModuleList.get(i).getName() + "/"
                    + studentModuleList.get(i).getCreditUnit();
            moduleInfo[i] = tempStr;
        };
        return moduleInfo;
    }
    
    public double[] getModuleMarks() {
        double[] moduleMarks = new double[studentModuleList.size()];
        for (int i = 0; i < studentModuleList.size(); i++) {
            moduleMarks[i] = studentModuleList.get(i).getMarks();
        };
        return moduleMarks;
    }
}
