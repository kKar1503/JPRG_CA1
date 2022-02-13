
// Class : DIT / FT / 1B / 04
// Admission Number : P2123181
// Name : Yam Kar Lok

package jprg_assignment;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Index {
    
    private static ArrayList<String[]> loginCredentials = new ArrayList<>();
    
    public static void main(String[] args) {
        
        // Sound effect for starting up the application
        SoundPlayer.playSound("SoundEffects\\\\Start.wav");
        // Initialize logger
        UserActivityLogger.setupLogger();
        // Log Program Initation
        UserActivityLogger.infoLog("Program started");
        // Initialize Default Students
        IOSystem.initializeStudents();
        // Initialize Default Users' Credentials
        getCredentials();
        
        JTextField username = new JTextField();
        JTextField password = new JPasswordField();
        
        ImageIcon icon = new ImageIcon("img\\\\sp.png");
        
        Object[] login = {
            "Username:", username,
            "Password:", password
        };
        
        Object[] options = {"Login",
                "Exit"};
        
        String[] user = new String[3];
        boolean exit = false, loginCorrect = false;
        
        do {
            int option = JOptionPane.showOptionDialog(null, 
                    login, 
                    "Mini Student System", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    options,
                    options[0]);

            if (option == JOptionPane.OK_OPTION) {
                if (username.getText().isEmpty()) {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                            "Username is empty.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (password.getText().isEmpty()) {
                    SoundPlayer.errorSound();
                    JOptionPane.showMessageDialog(null, 
                            "Password is empty.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    for (int i = 0; i < loginCredentials.size(); i++) {
                        if (username.getText().toLowerCase().equals(loginCredentials.get(i)[0].toLowerCase())
                                && password.getText().equals(loginCredentials.get(i)[1])){
                            loginCorrect = true;
                            exit = true;
                            user[0] = loginCredentials.get(i)[0]; // Map Username to user[0]
                            user[1] = loginCredentials.get(i)[2]; // Map Usertype to user[1]
                            user[2] = loginCredentials.get(i)[3]; // Map User Access to user[2]
                        }
                    }
                }
            } else {
                exit = true;
            }
        } while (!exit);
        
        if (loginCorrect) {
            if (user[1].equals("StudentUser")) {
                // Upon Successful login transfer to StudentUser Menu
                StudentUser.menu(user);
            } else if (user[1].equals("Administrator")) {
                // Upon Successful login transfer to Admin Menu
                Administrator.menu(user);
            }
            
        } else {
            // Log Program termination
            UserActivityLogger.infoLog("Program Terminated");
            // Good bye sound effect
            SoundPlayer.playSound("SoundEffects\\\\Bye.wav");
        }
    }
    
    private static void getCredentials() {
        
        // Best Practice to have credentials hashed and stored in database
        // For Assignment's simplicity credentials are initiated and generated
        // within private method.
        
        String[] u1 = {"ClassRep","Password1!","StudentUser","Full_Access"}; // User 1 with Full Access
        String[] u2 = {"John Tan","Pass123","StudentUser","Partial_Access"}; // User 3 with Partial Access
        String[] u3 = {"Admin","King123!","Administrator",null}; // Admin
        
        loginCredentials.addAll(Arrays.asList(u1, u2, u3));
        
        UserActivityLogger.infoLog("4 Default User Credentials initialized");
    }
}
