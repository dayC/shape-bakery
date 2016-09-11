/**
 * Created by phillip.porter234 on 9/7/16.
 */

import java.util.*;
import javax.swing.*;

public class GameEngine extends JApplet
{
    // GAME_DURATION is 20 by default
    int gameDuration = 20;
    final int MIN_GAME_DURATION = 1;
    final int MAX_GAME_DURATION = 40;

    // CAKE_SEQUENCE_LENGTH is 4 by default
    int cakeSequenceLength = 4;
    final int MIN_CAKE_SEQUENCE_LENGTH = 1;
    final int MAX_CAKE_SEQUENCE_LENGTH = 8;


    private boolean setDuration(int turns)
    {
        if (turns >= MIN_GAME_DURATION && turns <= MAX_GAME_DURATION)
        {
            this.gameDuration = turns;
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean setCakeSequenceLength(int length)
    {
        if (length >= MIN_CAKE_SEQUENCE_LENGTH && length <= MAX_CAKE_SEQUENCE_LENGTH)
        {
            this.cakeSequenceLength = length;
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main(String [] args)
    {





    }
}






