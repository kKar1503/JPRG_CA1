
// Class : DIT / FT / 1B / 04
// Admission Number : P2123181
// Name : Yam Kar Lok

package jprg_assignment;

import java.util.ArrayList;

public class InternationalStudent extends Student {
    private boolean studentPass;
    
    public InternationalStudent() {
    }

    public InternationalStudent(String name, String course, int adminNumber, ArrayList<Module> modules, boolean studentPass) {
        super(name, course, adminNumber, modules);
        this.studentPass = studentPass;
    }

    public boolean hasStudentPass() {
        return studentPass;
    }

    public void setStudentPass(boolean studentPass) {
        this.studentPass = studentPass;
    }
    
    public String getStudentInformation() {
        String studentPass = this.studentPass ? "do not need" : "need";
        return "I am an International Student. I " + studentPass + " a pass for internship.";
    }
}
