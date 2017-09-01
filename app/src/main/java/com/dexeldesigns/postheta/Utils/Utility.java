package com.dexeldesigns.postheta.Utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Creative IT Works on 02-May-17.
 */

public class Utility {
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }
}