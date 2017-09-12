package com.dexeldesigns.postheta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dexeldesigns.postheta.common.GlobalClass;
import com.dexeldesigns.postheta.db_tables.model.Break;
import com.dexeldesigns.postheta.db_tables.model.Clock;
import com.dexeldesigns.postheta.db_tables.model.Staff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.dexeldesigns.postheta.helper.Helper.getHelper;

/**
 * Created by Creative IT Works on 20-Apr-17.
 */

public class Login extends AppCompatActivity {
    ImageView text1, text2, text3, text4, text5, text6, text7, text8, text9;
    ImageView erase, delete, login, back;
    ImageView i1, i2, i3, i4;
    // EditText input_number;
    String userId = "", uiPwd = "";
    // Button enter;
    GlobalClass global;
    Button clockin, clockout;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        global = (GlobalClass) getApplicationContext();
        initialize();


        clockin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!userId.equalsIgnoreCase(""))
                {
                    if (clockin.getText().toString().equalsIgnoreCase("Clock in")) {
                        Clock clockdata = new Clock();

                        Calendar calendar = Calendar.getInstance();
                        String currenttime = df.format(calendar.getTime());
                        clockdata.setClock_in_time(currenttime);
                        clockdata.setStaffId(Long.parseLong(userId));
                        Long id = getHelper().getDaoSession().insert(clockdata);
                        clockdata.setId(id);


                        clockin.setText("Break");
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    } else if (clockin.getText().toString().equalsIgnoreCase("Break")) {

                        Clock clock = getHelper().getClockData(Long.parseLong(userId)).get(0);
                        Break breaks = new Break();

                        Calendar calendar = Calendar.getInstance();
                        String currenttime = df.format(calendar.getTime());
                        breaks.setBreak_start_time(currenttime);
                        breaks.setStaffId(userId);
                        breaks.setClockId(clock.getId());

                        Long id = getHelper().getDaoSession().insert(breaks);
                        breaks.setId(id);

                        clockin.setText("Resume");


                    } else if (clockin.getText().toString().equalsIgnoreCase("Resume")) {

                        Clock clock = getHelper().getClockData(Long.parseLong(userId)).get(0);
                        List<Break> breakdata = getHelper().getBreakData(clock.getId());
                        if (breakdata.size() > 0) {
                            Break breaks = breakdata.get(0);

                            Calendar calendar = Calendar.getInstance();
                            String currenttime = df.format(calendar.getTime());
                            breaks.setBreak_end_time(currenttime);
                            breaks.setTotal_break_time(getTimeDifference(breaks.getBreak_start_time(), breaks.getBreak_end_time()));
                            breaks.setClockId(clock.getId());
                            getHelper().getDaoSession().update(breaks);


                            clockin.setText("Break");
                        }
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }


                }else
                {
                    Toast.makeText(getApplication(),"Please Enter correct pin",Toast.LENGTH_SHORT).show();
                }



            }
        });

        clockout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!userId.equalsIgnoreCase("")) {


                    Clock clock = getHelper().getClockData(Long.parseLong(userId)).get(0);
                    Calendar calendar = Calendar.getInstance();
                    String currenttime = df.format(calendar.getTime());
                    clock.setClock_out_time(currenttime);
                    clock.setTotal_hours(getTimeDifference(clock.getClock_in_time(), clock.getClock_out_time()));
                    getHelper().getDaoSession().update(clock);


                    if (getHelper().getBreakData(clock.getId()).size() > 0) {
                        if (getHelper().getBreakData(clock.getId()).get(0).getBreak_end_time() == null) {
                            Break breaks = getHelper().getBreakData(clock.getId()).get(0);
                            breaks.setBreak_end_time(currenttime);
                            breaks.setTotal_break_time(getTimeDifference(breaks.getBreak_start_time(), breaks.getBreak_end_time()));

                            getHelper().getDaoSession().update(breaks);
                        }
                    }


                    String totalclocktime = addTotaltime(getHelper().getClockData(Long.parseLong(userId)));
                    String totalbreaktime = addbreaktime(getHelper().getBreakData(userId));

                    String totalworkingtime = getTotalWorkhours(totalclocktime, totalbreaktime);

                    clock.setTotal_working_hours(totalworkingtime);
                    getHelper().getDaoSession().update(clock);


                    clockin.setText("Clock in");


                }else {
                    Toast.makeText(getApplication(),"Please Enter correct pin",Toast.LENGTH_SHORT).show();

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
        clockout = (Button) findViewById(R.id.clockout);

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

            if (getHelper().getStaff(userId)!=null ) {
                Staff staff=getHelper().getStaff(userId);
                if (staff.getPin().equalsIgnoreCase("1111")) {
                    global.staffType = "admin";
                } else {
                    global.staffType = "staff";
                    global.staffPin = "1234";

                }

                    if (getHelper().getClockData(Long.parseLong(userId)).size() > 0) {
                        if (getHelper().getClockData(Long.parseLong(userId)).get(0).getClock_out_time() == null) {

                            Clock clock = getHelper().getClockData(Long.parseLong(userId)).get(0);


                            if (getHelper().getBreakData(clock.getId()).size() == 0) {

                                clockin.setText("Break");

                                Intent i = new Intent(Login.this, MainActivity.class);
                                startActivity(i);
                                finish();

                            } else if (getHelper().getBreakData(clock.getId()).size() > 0) {
                                if (getHelper().getBreakData(clock.getId()).get(0).getBreak_end_time() == null) {
                                    clockin.setText("Resume");
                                } else {
                                    clockin.setText("Break");
                                    Intent i = new Intent(Login.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        }
                    } else {
                        clockin.setText("Clock in");
                    }





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

    public String addTotaltime(List<Clock> clocks) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        long totaldate = 0;
        for (int i = 0; i < clocks.size(); i++) {
            try {
                Date date1 = timeFormat.parse(clocks.get(i).getTotal_hours());
                totaldate = totaldate + date1.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        String date3 = timeFormat.format(new Date(totaldate));
        System.out.println("The sum is " + date3);

        return String.valueOf(date3);
    }


    public String addbreaktime(List<Break> breaks) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        long totaldate = 0;
        for (int i = 0; i < breaks.size(); i++) {
            try {
                Date date1 = timeFormat.parse(breaks.get(i).getTotal_break_time());
                totaldate = totaldate + date1.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        String date3 = timeFormat.format(new Date(totaldate));
        System.out.println("The sum is " + date3);

        return String.valueOf(date3);
    }

    public String getTotalWorkhours(String starttime,String endtime) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        long totaldate = 0;

            try {
                Date date1 = timeFormat.parse(starttime);
                Date date2 = timeFormat.parse(endtime);
                totaldate = date1.getTime() - date2.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }



        String date3 = timeFormat.format(new Date(totaldate));
        System.out.println("The sum is " + date3);

        return String.valueOf(date3);
    }
}