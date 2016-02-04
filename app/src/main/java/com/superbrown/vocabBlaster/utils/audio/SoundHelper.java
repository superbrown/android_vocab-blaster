package com.superbrown.vocabBlaster.utils.audio;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by IntelliJ IDEA.
 * User: Mike Brown
 * Date: 10/15/11
 * Time: 8:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class SoundHelper
{
    public static void playSound(final Context context, final Integer resourceIdentifier)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run()
            {
                playSound_internalMethod(context, resourceIdentifier);
            }
        };

        // So this is non-blocking
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static void playSound_internalMethod(Context context, Integer resourceIdentifier) {
        MediaPlayer mediaPlayer = null;

        try
        {
            mediaPlayer = MediaPlayer.create(context, resourceIdentifier);
        }
        catch (Throwable e)
        {
            if (mediaPlayer != null)
            {
                mediaPlayer.release();
            }

            return;
        }

        mediaPlayer.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener()
                {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer)
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                    }
                }
        );

        try
        {
            mediaPlayer.start();
        }
        catch (Throwable e)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
