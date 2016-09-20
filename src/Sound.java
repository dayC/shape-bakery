import java.net.URL;
import java.applet.Applet;
import java.applet.AudioClip;

/// statically call the method to play a basic click sound effect
public class Sound {

    public static void playSound(String audioFile) {
        try {
            URL url = Sound.class.getResource(audioFile); ///< location of the audio file
            AudioClip sound = Applet.newAudioClip(url);
            sound.play();
        } catch (NullPointerException npe) {
            System.err.println("Error: Audio file " + audioFile + " not found!");
        }
    }
}
