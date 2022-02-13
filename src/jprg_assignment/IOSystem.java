package jprg_assignment;

import java.awt.Desktop;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class IOSystem {

    // File lists
    final private static File CREDENTIAL_FILE = new File("dat\\\\Credentials.dat");
    final private static File STUDENT_FILE = new File("dat\\\\Students.dat");
    final private static File DEFAULT_CREDENTIAL = new File("dat\\\\Default_Credentials.dat");

    public static void credentialSerialization(Credential credential) {
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
                    new FileOutputStream(CREDENTIAL_FILE))) {
                outStream.writeObject(currentCredentials);
                outStream.close();
            } catch (IOException e) {
                UserActivityLogger.errLog("Unable to serialize Credential Objects", e);
            }
        } else {
            UserActivityLogger.errLog("New Credential is not added as there's duplicated username", null);
        }
    }

    public static ArrayList<Credential> credentialDeserialization() {
        try (ObjectInputStream inStream = new ObjectInputStream(
                new FileInputStream(CREDENTIAL_FILE))) {
            ArrayList<Credential> credentials = (ArrayList<Credential>) inStream.readObject();
            inStream.close();
            return credentials;
        } catch (EOFException e) {
            // Ignore, reached end of file
        } catch (FileNotFoundException e) {
            // When there is no data initialized
            UserActivityLogger.errLog("No serialize file found.", e);
        } catch (IOException | ClassNotFoundException e) {
            UserActivityLogger.errLog("Unable to deserialize Credential Objects.", e);
        }
        return null;
    }

    public static void credentialChange(String password, String name) {
        ArrayList<Credential> currentCredentials = credentialDeserialization();
        for (Credential credential : currentCredentials) {
            if (credential.getName().equalsIgnoreCase(name)) {
                credential.setPassword(password);
            }
        }
        try (ObjectOutputStream outStream = new ObjectOutputStream(
                new FileOutputStream(CREDENTIAL_FILE))) {
            outStream.writeObject(currentCredentials);
            outStream.close();
        } catch (IOException e) {
            UserActivityLogger.errLog("Unable to serialize new Credential Objects", e);
        }
    }

    public static void studentSerialization(ArrayList<Student> studentList) {
        try (ObjectOutputStream outStream = new ObjectOutputStream(
                new FileOutputStream(STUDENT_FILE))) {
            outStream.writeObject(studentList);
            outStream.close();
        } catch (IOException e) {
            UserActivityLogger.errLog("Unable to serialize Student Objects", e);
        }
    }

    public static ArrayList<Student> studentDeserialization() {
        try (ObjectInputStream inStream = new ObjectInputStream(
                new FileInputStream(STUDENT_FILE))) {
            ArrayList<Student> studentList = (ArrayList<Student>) inStream.readObject();
            inStream.close();
            return studentList;
        } catch (EOFException e) {
            // Ignore, reached end of file
        } catch (FileNotFoundException e) {
            // When there is no data initialized
            UserActivityLogger.errLog("No serialize file found.", e);
        } catch (IOException | ClassNotFoundException e) {
            UserActivityLogger.errLog("Unable to deserialize Student Objects.", e);
        }
        return null;
    }

    public static void initializeStudents() {
        BufferedReader br;
        String[] studentInfo;
        int numberOfStudents, numberOfModules;
        try {
            br = new BufferedReader(new FileReader("Students.txt"));
            numberOfStudents = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < numberOfStudents; i++) {
                ArrayList<Module> moduleList = new ArrayList<>();
                studentInfo = br.readLine().split(";");
                int count = 0;
                numberOfModules = Integer.parseInt(studentInfo[3]);
                while (count < numberOfModules) {
                    Module m = new Module(studentInfo[4 + count * 4],
                            studentInfo[5 + count * 4],
                            Integer.parseInt(studentInfo[6 + count * 4]),
                            Double.parseDouble(studentInfo[7 + count * 4]));
                    moduleList.add(m);
                    count++;
                }
                if (studentInfo[4 + numberOfModules * 4].equals("Local Student")) {
                    LocalStudent student = new LocalStudent(studentInfo[0],
                            studentInfo[1],
                            Integer.parseInt(studentInfo[2].substring(1)),
                            moduleList);
                    StudentManagement.getStudentList().add(student);
                } else if (studentInfo[4 + numberOfModules * 4].equals("International Student")) {
                    InternationalStudent student = new InternationalStudent(studentInfo[0],
                            studentInfo[1],
                            Integer.parseInt(studentInfo[2].substring(1)),
                            moduleList,
                            Boolean.parseBoolean(studentInfo[studentInfo.length - 1]));
                    StudentManagement.getStudentList().add(student);
                } else {
                    UserActivityLogger.errLog("Unable to add student #" + (i + 1), null);
                }
            }
            br.close();
            UserActivityLogger.infoLog("Students initialization completed.");
        } catch (IOException e) {
            UserActivityLogger.errLog("Unable to initialize students from students.txt", e);
        } catch (NumberFormatException e) {
            UserActivityLogger.errLog("Initialization file has error.", e);
        }
    }

    public static void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            UserActivityLogger.errLog("Failed to open specified folder.", ioe);
        }
    }

    public static void generateReport(File file, ArrayList<Student> studentList) {
        int num = 0;

        try {
            // Setting up PrintWriter for creating csv
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            // Setting up header
            pw.println("Student Name,Admin Number,Student Course,Student GPA,"
                    + "Student Module Name,Student Module Code,Student Module Credit Unit,Student Module Marks,"
                    + "Student Type,Has Student Pass");

            // Deconstructing information from Student ArrayList
            for (int i = 0; i < studentList.size(); i++) {
                for (int j = 0; j < studentList.get(i).getModuleList().size(); j++) {
                    String studentType, studentPass;
                    if (studentList.get(i) instanceof LocalStudent) {
                        studentType = "Local Student";
                        studentPass = "Not Applicable";
                    } else if (studentList.get(i) instanceof InternationalStudent) {
                        studentType = "International Student";
                        if (((InternationalStudent) studentList.get(i)).hasStudentPass()) {
                            studentPass = "True";
                        } else {
                            studentPass = "False";
                        }
                    } else {
                        studentType = "Unknown";
                        studentPass = "Not Applicable";
                    }
                    pw.println(studentList.get(i).getName() + ","
                            + studentList.get(i).getAdminNumber() + ","
                            + studentList.get(i).getCourse() + ","
                            + studentList.get(i).getGpa() + ","
                            + studentList.get(i).getModuleList().get(j).getName() + ","
                            + studentList.get(i).getModuleList().get(j).getCode() + ","
                            + studentList.get(i).getModuleList().get(j).getCreditUnit() + ","
                            + studentList.get(i).getModuleList().get(j).getMarks() + ","
                            + studentType + ","
                            + studentPass);
                }
            }

            pw.flush();
            pw.close();

            UserActivityLogger.infoLog("Successfully exported Full Student Reports to " + file.toString());
        } catch (Exception e) {
            UserActivityLogger.errLog("Export to csv failed.", e);
            JOptionPane.showMessageDialog(null,
                    "Record not saved due to some error.\nCheck log file for more details.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void exportLog(File file, String logString) {
        try {
            // Setting up PrintWriter for creating log file
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.print(logString);

            pw.flush();
            pw.close();

            UserActivityLogger.infoLog("Successfully exported search log to " + file.toString());
        } catch (IOException ioe) {
            UserActivityLogger.errLog("Unable to export Log.", ioe);
        }
    }

    public static void emptyCredentials() {
        try {
            CREDENTIAL_FILE.delete();
            CREDENTIAL_FILE.createNewFile();
        } catch (IOException ex) {
            UserActivityLogger.errLog("Unable to empty Credentials.", ex);
        }
    }
    
    public static void reinitializeDefaultAccounts() throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inputStream = new FileInputStream(DEFAULT_CREDENTIAL);
            outputStream = new FileOutputStream(CREDENTIAL_FILE);
            inChannel = inputStream.getChannel();
            outChannel = outputStream.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } finally {
            inChannel.close();
            outChannel.close();
            inputStream.close();
            outputStream.close();
        }

    }
}
