
package jprg_assignment;

import java.util.ArrayList;

public class Authentication {
    private static ArrayList<Credential> credentialsList = new ArrayList<>();
    
    public static Object[] authenticate(String username, String password) {
        // Return object[] for authentication
        // Return types: boolean, 
        // Boolean : User Exist in Database
        // String  : User's Name
        // Boolean : Admin User
        // Boolean : Users' Access
        Object[] returnObject = {false, "", false, false};
        // Read and Store credentials temporarily in Credential ArrayList
        readCredentials();
        // Check username & password
        for (int i = 0; i < credentialsList.size() || returnObject.equals(false); i++) {
            if (credentialsList.get(i).getUsername().equals(username) 
            && credentialsList.get(i).getPassword().equals(password)) {
                returnObject[0] = true;
                returnObject[1] = credentialsList.get(i).getName();
                returnObject[2] = credentialsList.get(i).isAdmin();
                returnObject[3] = credentialsList.get(i).isAccess();
            }
        }
        credentialsList.clear();
        return returnObject;
    }
    
    private static void readCredentials(){
        credentialsList.add(new Credential("student", "stu1", "Student 1", false, false));
        credentialsList.add(new Credential("classrep", "stu2", "Student 2", false, true));
        credentialsList.add(new Credential("admin", "king", "Admin", true, true));
        System.out.println("Credentials read!");
    }
}
