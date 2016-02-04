package com.superbrown.vocabBlaster.utils.audio;

import android.content.Context;
import com.superbrown.utils.RandomList;

import java.io.IOException;

/**
 */
public class RandomSoundPlayer
{
    private RandomList fileNames;

    private Context context;

    public RandomSoundPlayer(RandomList fileNames, Context context)
    {
        this.fileNames = fileNames;
        this.context = context;
    }

    public void playSound() throws IOException
    {
        Integer resourceIdentifier = (Integer)fileNames.getNextElement();
        SoundHelper.playSound(context, resourceIdentifier);
    }
}
