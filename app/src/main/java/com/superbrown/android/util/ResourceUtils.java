package com.superbrown.android.util;

import android.content.res.Resources;

/**
 * Created by Mike on 2/18/14.
 */
public class ResourceUtils {
    public static int toLengthInLightOfScreenDensity(Resources resources, int length)
    {
        float density = resources.getDisplayMetrics().density;
        return (int) (length * density);
    }
}
