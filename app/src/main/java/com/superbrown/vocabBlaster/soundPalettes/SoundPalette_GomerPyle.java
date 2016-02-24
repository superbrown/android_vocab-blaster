package com.superbrown.vocabBlaster.soundPalettes;

import android.content.Context;
import com.superbrown.vocabBlaster.utils.audio.SoundPalette;
import com.superbrown.vocabBlaster.R;

/**
 */
public class SoundPalette_GomerPyle extends SoundPalette
{
    public SoundPalette_GomerPyle(Context context)
    {
        super(context);

        setSuccessSounds(new Integer[]
                {
                    R.raw.gomer_congrats,
                    R.raw.gomer_golly,
                    R.raw.gomer_surprise,
                    R.raw.gomer_shazam2,
                });

        setFailureSounds(new Integer[]
                {
                    R.raw.gomer_shame,
                    R.raw.gomer_shut_up,
                    R.raw.gomer_terrible,
                    R.raw.gomer_what_is_this,
                });
    }
}
