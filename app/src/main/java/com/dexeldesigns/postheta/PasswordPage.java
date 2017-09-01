package com.dexeldesigns.postheta;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dexeldesigns.postheta.Utils.Border_utils;
import com.dexeldesigns.postheta.common.GlobalClass;
import com.dexeldesigns.postheta.db_tables.model.Clock;
import com.dexeldesigns.postheta.db_tables.model.Staff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.dexeldesigns.postheta.helper.Helper.getHelper;

/**
 * Created by Creative IT Works on 20-Apr-17.
 */

public class PasswordPage extends AppCompatActivity {
    ImageView text1, text2, text3, text4, text5, text6, text7, text8, text9;
    ImageView erase, delete, login, back;
    ImageView i1, i2, i3, i4;
    // EditText input_number;
    String userId = "", uiPwd = "";
    // Button enter;
    GlobalClass global;
    Button clockin, cancel;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        global = (GlobalClass) getApplicationContext();
        initialize();
        //border_utils=new Border_utils();
        // input_number.setInputType(0);


        //  border_utils.Border_utils(enter, "#8EC63F");
       /* enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PasswordPage.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });*/
        if (getHelper().getClockData(Long.parseLong("1234")).size() > 0) {
            Clock clock = getHelper().getClockData(Long.parseLong("1234")).get(0);
            if (clock.getClock_out_time() == null) {
                clockin.setText("Clock Out");

            } else {
                clockin.setText("Clock in");
            }
        }


        clockin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /* Clock clock=new Clock();
               clock.setClock_in_time(new Date().toString());
               if(global.staffPin.equalsIgnoreCase("1234"))
               {
                   clock.setId(Long.parseLong("1"));
                   clock.setStaffId(Long.parseLong(global.staffPin));
                   getHelper().getDaoSession().insertOrReplace(clock);

               }*/

                if (getHelper().getClockData(Long.parseLong("1234")).size() > 0) {
                    Clock clock = getHelper().getClockData(Long.parseLong("1234")).get(0);
                    if (clock.getClock_out_time() == null) {
                        Calendar calendar = Calendar.getInstance();
                        String currenttime = df.format(calendar.getTime());
                        clock.setClock_out_time(currenttime);


                        try {
                            Date startDate = df.parse(clock.getClock_in_time());
                            Date stopDate = df.parse(clock.getClock_out_time());
                            String time = getTimeDifference(clock.getClock_in_time(), clock.getClock_out_time());
                            clock.setTotal_hours(time);
                            getHelper().getDaoSession().update(clock);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        clockin.setText("Clockin");
                    } else {
                        Clock clockdata = new Clock();

                        Calendar calendar = Calendar.getInstance();
                        String currenttime = df.format(calendar.getTime());
                        clockdata.setClock_in_time(currenttime);
                        clockdata.setStaffId(Long.parseLong("1234"));
                        Long id = getHelper().getDaoSession().insert(clockdata);
                        clockdata.setId(id);

                        clockin.setText("ClockOut");


                    }
                } else {
                    Clock clockdata = new Clock();

                    Calendar calendar = Calendar.getInstance();
                    String currenttime = df.format(calendar.getTime());
                    clockdata.setClock_in_time(currenttime);
                    clockdata.setStaffId(Long.parseLong("1234"));
                    Long id = getHelper().getDaoSession().insert(clockdata);
                    clockdata.setId(id);
                    clockin.setText("ClockOut");


                }

            }
        });


    }

    private void initialize() {
        text1 = (ImageView) findViewById(R.id.text1);
        text2 = (ImageView) findViewById(R.id.text2);
        text3 = (ImageView) findViewById(R.id.text3);
        text4 = (ImageView) findViewById(R.id.text4);
        text5 = (ImageView) findViewById(R.id.text5);
        text6 = (ImageView) findViewById(R.id.text6);
        text7 = (ImageView) findViewById(R.id.text7);
        text8 = (ImageView) findViewById(R.id.text8);
        text9 = (ImageView) findViewById(R.id.text9);
        erase = (ImageView) findViewById(R.id.erase);
        delete = (ImageView) findViewById(R.id.delete);
        //input_number=(EditText)findViewById(R.id.pin_number);
        i1 = (ImageView) findViewById(R.id.imageview_circle1);
        i2 = (ImageView) findViewById(R.id.imageview_circle2);
        i3 = (ImageView) findViewById(R.id.imageview_circle3);
        i4 = (ImageView) findViewById(R.id.imageview_circle4);

        // enter=(Button) findViewById(R.id.enter);
        clockin = (Button) findViewById(R.id.clockin);
        cancel = (Button) findViewById(R.id.cancel);

    }

    public void enterPin(View view) {

        ImageView clickedButton = (ImageView) view;
        if (clickedButton.getId() == erase.getId()) {

            if (userId.length() > 0) {
                uiPwd = "";
                userId = "";

            }
            passCode();
        } else if (clickedButton.getId() == delete.getId()) {

            if (userId.length() > 0) {
                uiPwd = uiPwd.substring(0, uiPwd.length() - 1);
                userId = userId.substring(0, userId.length() - 1);
            }
            passCode();

        } else {
            uiPwd = uiPwd + ".";
            userId = userId + clickedButton.getTag().toString();

            passCode();

        }
        //input_number.setText(uiPwd);


        //test();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();


        finish();
    }

    public void passCode() {
        if (userId.toString().length() == 0) {
            i1.setImageResource(R.drawable.ic_pin_unselect);
            i2.setImageResource(R.drawable.ic_pin_unselect);
            i3.setImageResource(R.drawable.ic_pin_unselect);
            i4.setImageResource(R.drawable.ic_pin_unselect);

        } else if (userId.toString().length() == 1) {
            i1.setImageResource(R.drawable.ic_pin_select);
            i2.setImageResource(R.drawable.ic_pin_unselect);
            i3.setImageResource(R.drawable.ic_pin_unselect);
            i4.setImageResource(R.drawable.ic_pin_unselect);

        } else if (userId.toString().length() == 2) {
            i1.setImageResource(R.drawable.ic_pin_select);
            i2.setImageResource(R.drawable.ic_pin_select);
            i3.setImageResource(R.drawable.ic_pin_unselect);
            i4.setImageResource(R.drawable.ic_pin_unselect);

        } else if (userId.toString().length() == 3) {
            i1.setImageResource(R.drawable.ic_pin_select);
            i2.setImageResource(R.drawable.ic_pin_select);
            i3.setImageResource(R.drawable.ic_pin_select);
            i4.setImageResource(R.drawable.ic_pin_unselect);

        } else if (userId.toString().length() == 4) {
            i1.setImageResource(R.drawable.ic_pin_select);
            i2.setImageResource(R.drawable.ic_pin_select);
            i3.setImageResource(R.drawable.ic_pin_select);
            i4.setImageResource(R.drawable.ic_pin_select);

            if (userId.equalsIgnoreCase("1234") || userId.equalsIgnoreCase("1111")) {
                if (userId.equalsIgnoreCase("1111")) {
                    global.staffType = "admin";
                } else {
                    global.staffType = "staff";
                    global.staffPin = "1234";

                    Staff staff = new Staff();
                    staff.setName("admin");
                    staff.setPin("1234");

                    Long staffId = getHelper().getDaoSession().insertOrReplace(staff);
                }
                Intent i = new Intent(PasswordPage.this, MainActivity.class);
                startActivity(i);
                finish();

            } else {
                onShakeImage();
            }


        } else if (userId.length() > 4) {
            onShakeImage();
            userId = userId.substring(0, userId.length() - 1);
            i1.setImageResource(R.drawable.ic_pin_select);
            i2.setImageResource(R.drawable.ic_pin_select);
            i3.setImageResource(R.drawable.ic_pin_select);
            i4.setImageResource(R.drawable.ic_pin_select);

        }
        // Toast.makeText(getApplicationContext(),userId,Toast.LENGTH_SHORT).show();
    }

    public void onShakeImage() {
        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);


        i1.startAnimation(shake); // starts animation
        i2.startAnimation(shake);
        i3.startAnimation(shake);
        i4.startAnimation(shake);
    }

    public String getTimeDifference(String dateStart, String dateStop) {
        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;
        long diffHours = 00;
        long diffMinutes = 00;
        long diffSeconds = 00;
        try {
            d1 = df.parse(dateStart);
            d2 = df.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            diffSeconds = diff / 1000 % 60;
            diffMinutes = diff / (60 * 1000) % 60;
            diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(diffHours) + ":" + String.valueOf(diffMinutes) + ":" + String.valueOf(diffSeconds);


    }

}