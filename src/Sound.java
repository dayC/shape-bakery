import java.net.URL;
import java.applet.Applet;
import java.applet.AudioClip;

/// statically call the method to play a basic click sound effect
public class Sound {

    public static void playSound(){
        URL url = Sound.class.getResource("audio/click.wav"); ///< location of the audio file
        AudioClip sound = Applet.newAudioClip(url);
        sound.play();
    }
}
