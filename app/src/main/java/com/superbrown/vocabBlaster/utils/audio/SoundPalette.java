package com.superbrown.vocabBlaster.utils.audio;

import android.content.Context;
import com.superbrown.utils.RandomList;

import java.io.IOException;

/**
 */
public class SoundPalette
{
    private RandomSoundPlayer successSound;
    private RandomSoundPlayer failureSound;

    boolean disabled = false;
    private Context context;

    public SoundPalette(Context context)
    {
        this.context = context;
    }

    public SoundPalette()
    {
        disabled = true;
    }

    public void playSuccessSound() throws IOException
    {
        if (!disabled)
        {
            successSound.playSound();
        }
    }

    public void playFailureSound() throws IOException
    {
        if (!disabled)
        {
            failureSound.playSound();
        }
    }

    public void setSuccessSounds(Object[] successSounds)
    {
        RandomList randomList = new RandomList(successSounds);
        successSound = new RandomSoundPlayer(randomList, context);
    }

    public RandomSoundPlayer getFailureSound()
    {
        return failureSound;
    }

    public void setFailureSounds(Object[] successSounds)
    {
        RandomList randomList = new RandomList(successSounds);
        failureSound = new RandomSoundPlayer(randomList, context);
    }
}
