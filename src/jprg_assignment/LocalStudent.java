
// Class : DIT / FT / 1B / 04
// Admission Number : P2123181
// Name : Yam Kar Lok

package jprg_assignment;

import java.util.ArrayList;

public class LocalStudent extends Student {

    public LocalStudent() {
    }

    public LocalStudent(String name, String course, int adminNumber, ArrayList<Module> modules) {
        super(name, course, adminNumber, modules);
    }
    
    public String getStudentInformation() {
        return "I am a Local Student.";
    }
}
