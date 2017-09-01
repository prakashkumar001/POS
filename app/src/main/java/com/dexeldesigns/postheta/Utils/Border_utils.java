package com.dexeldesigns.postheta.Utils;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

/**
 * Created by Creative IT Works on 20-Jul-17.
 */

public class Border_utils {

    public View  Border_utils(View view, String colorcode)
    {
        GradientDrawable gd = new GradientDrawable();

        // Specify the shape of drawable
        gd.setShape(GradientDrawable.RECTANGLE);

        // Set the fill color of drawable
        gd.setColor(Color.parseColor(colorcode)); // make the background transparent

       /* // Create a 2 pixels width red colored border for drawable
        gd.setStroke(2, Color.RED); // border width and color
*/
        // Make the border rounded
        gd.setCornerRadius(10.0f); // border corner radius

        // Finally, apply the GradientDrawable as TextView background
        view.setBackground(gd);

        return view;
    }
}
