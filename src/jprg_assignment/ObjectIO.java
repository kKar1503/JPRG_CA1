
package jprg_assignment;

import java.io.*;
import java.util.ArrayList;

public class ObjectIO {
    public static void credentialSerialization(Credential credential) {
        File f = new File("dat\\\\Credentials.dat");
        // Import a list of existing credentials
        ArrayList<Credential> currentCredentials = credentialDeserialization();
        // If return null, create a new ArrayList
        if (currentCredentials == null) {
            currentCredentials = new ArrayList<>();
        }
        boolean dupeFound = false;
        // Loop through ArrayList and finds if there's duplicated username
        for (int i = 0; i < currentCredentials.size() && dupeFound == false; i++) {
            if (currentCredentials.get(i).getUsername()
                    .equalsIgnoreCase(credential.getUsername())) {
                dupeFound = true;
            }
        }
        // Adds the new credentials if duplicate is not found
        if (!dupeFound) {
            System.out.println("added");
            currentCredentials.add(credential);
            try (ObjectOutputStream outStream = new ObjectOutputStream(
                        new FileOutputStream(f))){
                outStream.writeObject(currentCredentials);
                outStream.close();
            } catch (IOException e) {
                UserActivityLogger.errLog("Unable to serialize Credential Object", e);
            }
        } else {
            UserActivityLogger.errLog("New Credential is not added as there's duplicated username", null);
        }
    }
    
    public static ArrayList<Credential> credentialDeserialization() {
        File f = new File("dat\\\\Credentials.dat");
        try (ObjectInputStream inStream = new ObjectInputStream(
                    new FileInputStream(f))){
            ArrayList<Credential> credentials = (ArrayList<Credential>)inStream.readObject();
            inStream.close();
            return credentials;
        } catch (EOFException e) {
            // Ignore, reached end of file
        } catch (FileNotFoundException e) {
            // When there is no data initialized
            UserActivityLogger.errLog("No serialize file found.", e);
        } catch (IOException | ClassNotFoundException e) {
            UserActivityLogger.errLog("Unable to deserialize Credential Object.", e);
        }
        return null;
    }
}
