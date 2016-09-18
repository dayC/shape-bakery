import java.net.MalformedURLException;
import java.net.URL;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Created by zoltan.batoczki663 on 9/7/16.
 */
public class Sound {

    public static void playSound(){
        URL url = Sound.class.getResource("audio/click.wav");
        AudioClip sound = Applet.newAudioClip(url);
        sound.play();
    }
}
