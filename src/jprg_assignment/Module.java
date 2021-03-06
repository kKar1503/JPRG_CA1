
// Class : DIT / FT / 1B / 04
// Admission Number : P2123181
// Name : Yam Kar Lok

package jprg_assignment;

import java.io.Serializable;

public class Module implements Serializable{
    private String code, name;
    private int creditUnit;
    private double marks;
    
    public Module() {
    }
    
    public Module(String code, String name, int creditUnit, double marks) {
        this.code = code;
        this.name = name;
        this.creditUnit = creditUnit;
        this.marks = marks;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getCreditUnit() {
        return this.creditUnit;
    }
    
    public void setCreditUnit(int creditUnit) {
        this.creditUnit = creditUnit;
    }
    
    public double getMarks() {
        return this.marks;
    }
    
    public void setMarks(double marks) {
        this.marks = marks;
    }
}
