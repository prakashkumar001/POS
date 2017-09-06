package com.dexeldesigns.postheta;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dexeldesigns.postheta.common.GlobalClass;
import com.dexeldesigns.postheta.fragments.*;
import com.dexeldesigns.postheta.model.Tables;
import com.dexeldesigns.postheta.splitbill.SplitFragment;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Creative IT Works on 11-Jul-17.
 */

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayoutHeader;
    TextView table_no, clock_in_time, clock_in_Timer;
    Timer timer;
    TimerTask timerTask;
    Handler handler;
    Runnable runnable;
    ImageView staff_clock;
    GlobalClass global;
    int drawables_unselect[] = new int[]{R.mipmap.home_unselect, R.mipmap.staff_unselect, R.mipmap.setting_unselect, R.mipmap.favourite_unselect, R.mipmap.tabel_unselect};
    int drawables_select[] = new int[]{R.mipmap.home_select, R.mipmap.staff_select, R.mipmap.setting_select, R.mipmap.favourite_select, R.mipmap.tabel_select};
    private Paint p = new Paint();
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupTabLayout();

        global = (GlobalClass) getApplicationContext();
        try {

          /*  String clockins = databaseHelper.getStaffClockin("111");
            String[] clock = clockins.split(" ");
            String clock_date = clock[0];
            String clock_time = clock[1];*/
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String clock_time=dateFormatter.format(new Date()).toString();

            clock_in_time.setText(clock_time);
            // yyyy-MM-dd HH:mm:ss
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            countDownStart(clock_in_Timer, dateFormat.format(new Date()));


        } catch (Exception e) {

        }

        if(global.paytype.equalsIgnoreCase("split"))
        {
            splitcheckTotal();
        }else {
            initFragments();
        }


        staff_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void initFragments() {

        Home fragments = new Home();


        loadFragment(fragments);


    }

    public void loadFragment(Fragment fragments) {


        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fadeinact,R.anim.fadeoutact);
                ft.replace(R.id.container, fragments, fragments.getClass().getSimpleName()).commit();

    }

    public void loadFragmentsfromTable(String tableno) {


        Home fragments = new Home();
        Bundle b = new Bundle();
        b.putString("TableNo", tableno);
        fragments.setArguments(b);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.left_to_right,R.anim.right_to_left);
                ft.replace(R.id.container, fragments, fragments.getClass().getSimpleName()).commit();


    }
    public void loadFragmentsfromTakeAway(String takeawayno) {


        Home fragments = new Home();
        Bundle b = new Bundle();
        b.putString("TakeAwayno", takeawayno);
        fragments.setArguments(b);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.left_to_right,R.anim.right_to_left);
        ft.replace(R.id.container, fragments, fragments.getClass().getSimpleName()).commit();


    }
    public void countDownStart(final TextView t, final String times) {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    // Here Set your Event Date
                    Date eventDate = dateFormat.parse(times);
                    Date currentDate = new Date();
                    if (!eventDate.after(currentDate)) {
                        long diff = currentDate.getTime()
                                - eventDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                       /* tvDay.setText("" + String.format("%02d", days));
                        tvHour.setText("" + String.format("%02d", hours));
                        tvMinute.setText("" + String.format("%02d", minutes));
                        tvSecond.setText("" + String.format("%02d", seconds));*/


                        t.setText(" " + String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                    } else {

                        handler.removeCallbacks(runnable);
                        // handler.removeMessages(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);

    }

    private void init() {

        staff_clock = (ImageView) findViewById(R.id.staff_clock);
        tabLayoutHeader = (TabLayout) findViewById(R.id.tab_header);
        clock_in_time = (TextView) findViewById(R.id.clockin_time);
        clock_in_Timer = (TextView) findViewById(R.id.clock_timer);
        table_no = (TextView) findViewById(R.id.table_no);
    }

    private void setupTabLayout() {
        //tabLayoutHeader initial
        tabLayoutHeader.addTab(tabLayoutHeader.newTab().setIcon(R.mipmap.staff_unselect), true);
        tabLayoutHeader.addTab(tabLayoutHeader.newTab().setIcon(R.mipmap.staff_unselect));
        tabLayoutHeader.addTab(tabLayoutHeader.newTab().setIcon(R.mipmap.staff_unselect));
        tabLayoutHeader.addTab(tabLayoutHeader.newTab().setIcon(R.mipmap.staff_unselect));
        tabLayoutHeader.addTab(tabLayoutHeader.newTab().setIcon(R.mipmap.staff_unselect));
        int tabCount = tabLayoutHeader.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tab = tabLayoutHeader.getTabAt(i);
            View tabView = ((ViewGroup) tabLayoutHeader.getChildAt(0)).getChildAt(i);
            tabView.requestLayout();
            View view = LayoutInflater.from(this).inflate(R.layout.customview, null);

            ImageView image = (ImageView) view.findViewById(R.id.image);
            if (i == 0) {
                image.setImageResource(drawables_select[i]);
            } else

                image.setImageResource(drawables_unselect[i]);

            tab.setCustomView(view);
        }

        LinearLayout linearLayout;
        GradientDrawable drawable;
        linearLayout = (LinearLayout) tabLayoutHeader.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        drawable = new GradientDrawable();
        drawable.setColor(Color.GRAY);
        drawable.setSize(1, 1);
        linearLayout.setDividerPadding(10);
        linearLayout.setDividerDrawable(drawable);
        tabLayoutHeader.setSelectedTabIndicatorColor(getResources().getColor(R.color.orange));
        tabLayoutHeader.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));


        tabLayoutHeader.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                ImageView imageView = (ImageView) tab.getCustomView().findViewById(R.id.image);
                imageView.setImageResource(drawables_select[tab.getPosition()]);

                if (tab.getPosition() == 4) {
                   /* if(global.mOrderType.equalsIgnoreCase("Dine-in"))
                    {
                        Intent i=new Intent(getApplicationContext(),WaiterTableReserve.class);
                        startActivityForResult(i,111);
                    }*/

                    // initFragments();

                    TableService fragments = new TableService();

                    loadFragment(fragments);


                } else {

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ImageView imageView = (ImageView) tab.getCustomView().findViewById(R.id.image);
                imageView.setImageResource(drawables_unselect[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 4) {
                    if (global.mOrderType.equalsIgnoreCase("Dine-in")) {
                       /* Intent i = new Intent(getApplicationContext(), WaiterTableReserve.class);
                        startActivityForResult(i, 111);*/
                    } else {
                        global.TableNo = "0";


                    }


                }
            }
        });


    }

    @Override
    public void onBackPressed() {


        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);

        if(currentFragment instanceof Home)
        {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }       else
        {

           global.TableNo="0";
            initFragments();
        }

        }
    public void logout()
    {
        Toast.makeText(getApplicationContext(),"Payment success",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this, Login.class);
        startActivity(i);
        finish();
    }


    void  splitcheckTotal()
    {
        double d=0.0;
        double totalvalue=0.0;


        for(int k=0;k<global.orders.get(global.TableNo).size();k++)
        {

            String total=global.orders.get(global.TableNo).get(k).getTotal_price_row();
            d=d+Double.parseDouble(total);

        }

        totalvalue=totalvalue+d;


        if(totalvalue==0.0)
        {

            global.paytype="";
            for(int i=0;i<global.select_tables.size();i++)
            {
                Tables tables=global.select_tables.get(i);
                Log.i("TTTTTTTT","TTTTTT"+tables.getTable_number().getText().toString());
                if(tables.getTable_number().getText().toString().equals(global.TableNo))
                {
                    global.select_tables.remove(i);
                }
            }
            initFragments();
        }else
        {
            SplitFragment fragment=new SplitFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Totalpayment",String.valueOf(totalvalue)); // Put anything what you want
            fragment.setArguments(bundle);
            loadFragment(fragment);

        }



    }

}