
package jprg_assignment;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SoundPlayer {
    public static void playSound(String filepath) {
        InputStream music;
        try {
            // Set up audio file stream
            music = new FileInputStream(new File(filepath));
            AudioStream sound = new AudioStream(music);
            AudioPlayer.player.start(sound);
        } catch (Exception e) {
            // Catch any exception due to file errors i.e. missing audio file(s)
            UserActivityLogger.errLog("Error throw from playing sound effect.", e);
        }
    }
    
    // Set a default sound for all errors
    public static void errorSound() {
        SoundPlayer.playSound("SoundEffects\\\\Error.wav");
    }
    
}
