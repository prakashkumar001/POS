package com.dexeldesigns.postheta;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.dexeldesigns.postheta.Utils.Border_utils;

/**
 * Created by Creative IT Works on 20-Jul-17.
 */

public class ClockActivity extends AppCompatActivity {

    Button clockout,clockin,breaks;
    Border_utils border_utils;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock);

        clockout=(Button)findViewById(R.id.clockout);
        clockin=(Button)findViewById(R.id.clockin);
        breaks=(Button)findViewById(R.id.breaks);
        // Initialize a new GradientDrawable
        border_utils=new Border_utils();
        border_utils.Border_utils(clockout,"#F98B8A");
        border_utils.Border_utils(clockin,"#ABD373");
        border_utils.Border_utils(breaks,"#B1B1B1");

        clockin.setFocusable(false);



    }
}
