import java.net.URL;
import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Used to play sound effects.
 */
public class Sound {
    /**
     * Plays a sound, given the string of the name of the audio file to be played.
     * Throws a null pointer exception if the sound file could not be found.
     * @param audioFile = The string of the name of the audio file to be played.
     */
    public static void playSound(String audioFile) {
        try {
            URL url = Sound.class.getResource(audioFile); ///< location of the audio file
            AudioClip sound = Applet.newAudioClip(url);
            sound.play();
        }
        //Throws a null pointer exception if sound file not found.
        catch (NullPointerException npe) {
            System.err.println("Error: Audio file " + audioFile + " not found!");
        }
    }
}
