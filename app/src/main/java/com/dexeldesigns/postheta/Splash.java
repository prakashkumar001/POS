package com.dexeldesigns.postheta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Creative IT Works on 20-Apr-17.
 */

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        final int SPLASH_DISPLAY_TIME = 3000;
        new Handler().postDelayed(new Runnable() {
            public void run() {

                Splash.this.finish();
                overridePendingTransition(R.anim.fadeinact,
                        R.anim.fadeoutact);
                Intent mainIntent = new Intent(
                        Splash.this,
                        Login.class);

                Splash.this.startActivity(mainIntent);


            }
        }, SPLASH_DISPLAY_TIME);

    }
}
