
package jprg_assignment;

import javax.swing.*;
import java.util.*;

public class StudentUser {
    public static void main(String[] args) {
        // Sound effect for starting up the application
        SoundPlayer.playSound("SoundEffects\\\\Start.wav");
        // Initialize logger
        UserActivityLogger.setupLogger();
        // Initialize Default Students
        StudentManagement.initializeStudents();
        StudentManagement.displayAllStudents();
    }
}
