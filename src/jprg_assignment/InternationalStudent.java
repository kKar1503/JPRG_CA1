
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
}
