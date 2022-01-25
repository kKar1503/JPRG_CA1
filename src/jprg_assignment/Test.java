
package jprg_assignment;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void initializeCreds() {
        byte[] salt1 = Authentication.saltGenerator();
        byte[] salt2 = Authentication.saltGenerator();
        byte[] salt3 = Authentication.saltGenerator();
        Credential johntan = new Credential("johntan", Authentication.hashPassword("johntan123", salt1), "John Tan", false, false, salt1);
        Credential classrep = new Credential("classrep", Authentication.hashPassword("classrep123", salt2), "Class Rep", false, true, salt2);
        Credential admin = new Credential("superadmin", Authentication.hashPassword("king123!", salt3), "Super Admin", true, true, salt3);
        ObjectIO.credentialSerialization(johntan);
        ObjectIO.credentialSerialization(classrep);
        ObjectIO.credentialSerialization(admin);
        ArrayList<Credential> credentialList = ObjectIO.credentialDeserialization();
        for (Credential credential: credentialList) {
            System.out.println(credential.getName());
        }
    }
    
    public static ArrayList<Student> initializeStudents() {
        // Modules for Student 1
        Module m1 = new Module("ST0501", "FED", 5, 75.0);
        Module m2 = new Module("ST0502", "FOP", 6, 85.0);
        Module m3 = new Module("ST2413", "FOC", 4, 77.0);
        Module m4 = new Module("LC0860", "CAT", 2, 83.0);
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
        
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(s1);        
        studentList.add(s2);
        studentList.add(s3);
        return studentList;
    }
    
    public static void main(String[] args) {
        ObjectIO.studentSerialization(initializeStudents());
    }
}
