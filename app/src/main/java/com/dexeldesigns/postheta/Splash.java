package com.dexeldesigns.postheta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dexeldesigns.postheta.db_tables.model.Staff;

import java.util.ArrayList;
import java.util.List;

import static com.dexeldesigns.postheta.helper.Helper.getHelper;

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



                insertStaffinDB();


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

    void insertStaffinDB()
    {
        List<Staff> staffs=new ArrayList<>();
        staffs.add(new Staff(Long.parseLong("1"),"admin","1234"));
        staffs.add(new Staff(Long.parseLong("2"),"Prakash","1111"));
        staffs.add(new Staff(Long.parseLong("3"),"Sreeni","5265"));
        staffs.add(new Staff(Long.parseLong("4"),"Peter","4321"));
        staffs.add(new Staff(Long.parseLong("5"),"Mark","1789"));


       for(Staff staff:staffs)
       {
           Long staffId = getHelper().getDaoSession().insertOrReplace(staff);
       }

    }
}
