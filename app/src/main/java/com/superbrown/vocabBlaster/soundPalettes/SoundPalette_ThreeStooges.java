package com.superbrown.vocabBlaster.soundPalettes;


import android.content.Context;
import com.superbrown.vocabBlaster.utils.audio.SoundPalette;
import com.superbrown.vocabBlaster.R;

/**
 */
public class SoundPalette_ThreeStooges extends SoundPalette
{
    public SoundPalette_ThreeStooges(Context context)
    {
        super(context);
        
        setSuccessSounds(new Integer[]
                {
                    R.raw.stooges_certnly,
                    R.raw.stooges_curly,
                    R.raw.stooges_wowowowo,
                    R.raw.stooges_heylarry,
                    R.raw.stooges_heymoe,
                });

        setFailureSounds(new Integer[]
                {
                    R.raw.stooges_brains,
                    R.raw.stooges_nyuk,
                    R.raw.stooges_think,
                    R.raw.stooges_sorrymoe,
                    R.raw.stooges_murder,
                });
    }
}
