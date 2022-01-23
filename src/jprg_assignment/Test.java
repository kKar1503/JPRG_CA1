
package jprg_assignment;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> testList = null;
        if (testList == null) {
            testList = new ArrayList<>();
        }
        testList.add("Hello");
        System.out.println(testList.size());
    }
    
}
