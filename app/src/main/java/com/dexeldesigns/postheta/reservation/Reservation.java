package com.dexeldesigns.postheta.reservation;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.dexeldesigns.postheta.MainActivity;
import com.dexeldesigns.postheta.R;
import com.dexeldesigns.postheta.db_tables.model.Reserve;
import com.dexeldesigns.postheta.reservation.notification.NotificationScheduler;
import com.dexeldesigns.postheta.reservation.notification.ReservationNotification;

import java.util.Calendar;

import static com.dexeldesigns.postheta.helper.Helper.getHelper;

/**
 * Created by Creative IT Works on 05-Oct-17.
 */

public class Reservation extends Fragment {
    private static TextView set_date, set_time;
    EditText name,phone,address,email;
    Button submit;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.reservation, container, false);

        set_date = (TextView) view.findViewById(R.id.set_date);
        set_time = (TextView) view.findViewById(R.id.set_time);
        name = (EditText) view.findViewById(R.id.name);
        phone = (EditText) view.findViewById(R.id.phone);
        email= (EditText) view.findViewById(R.id.email);
        address = (EditText) view.findViewById(R.id.address);
        submit=(Button)view.findViewById(R.id.submit);

        set_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Show Date dialog
                DatePickerFragment mDatePicker = new DatePickerFragment();

                mDatePicker.show(getFragmentManager(), "Select date");
            }
        });
        set_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Show time dialog
                TimePicker mTimePicker = new TimePicker();
                mTimePicker.show(getFragmentManager(), "Select time");
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString().length()>0 && phone.getText().toString().length()>0 && set_date.getText().toString().length()>0 && set_time.getText().length()>0)
                {
                    Reserve reserve=new Reserve();
                    reserve.setName(name.getText().toString());
                    reserve.setEmail(email.getText().toString());
                    reserve.setAddress(address.getText().toString());
                    reserve.setPhone(phone.getText().toString());
                    reserve.setDate(set_date.getText().toString());
                    reserve.setTime(set_time.getText().toString());
                    getHelper().getDaoSession().insert(reserve);

                    String[] times=set_time.getText().toString().split(":");
                    int hour=Integer.parseInt(times[0]);
                    int minutes=Integer.parseInt(times[1]);


                    NotificationScheduler.setReminder(getActivity(), ReservationNotification.class,hour, minutes,name.getText().toString());

                }

            }
        });





        return view;

    }

    public static class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            String AM_PM;
            if (hourOfDay < 12) {
                AM_PM = "AM";
            } else {
                AM_PM = "PM";
            }
            set_time.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute) /*+ " " + AM_PM*/);
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

            return dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            set_date.setText(String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day));
        }
    }
}

