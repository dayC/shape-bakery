/**
 * Created by phillip.porter234 on 9/7/16.
 */

import java.util.*;
import javax.swing.*;

public class GameEngine extends JApplet
{
    // GAME_DURATION is 20 by default
    private int gameDuration = 20;
    private final int MIN_GAME_DURATION = 1;
    private final int MAX_GAME_DURATION = 40;

    // Turns remaining
    private int turnsRemaining = this.gameDuration;

    // CAKE_SEQUENCE_LENGTH is 4 by default
    private int cakeSequenceLength = 4;
    private final int MIN_CAKE_SEQUENCE_LENGTH = 1;
    private final int MAX_CAKE_SEQUENCE_LENGTH = 8;


    public int getGameDuration()
    {
        return this.gameDuration;
    }

    public int getTurnsRemaining()
    {
        return this.turnsRemaining;
    }

    public int getCakeSequenceLength()
    {
        return cakeSequenceLength;
    }

    public int nextTurn()
    {
        return --this.turnsRemaining;
    }

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
    
}






