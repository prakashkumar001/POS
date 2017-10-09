package com.dexeldesigns.postheta.reservation.notification;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.dexeldesigns.postheta.MainActivity;
import com.dexeldesigns.postheta.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.dexeldesigns.postheta.reservation.notification.NotificationScheduler.TAG;

/**
 * Created by Creative IT Works on 06-Oct-17.
 */

public class ReservationNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String hour=intent.getStringExtra("hour");
        String min=intent.getStringExtra("minutes");
        String user=intent.getStringExtra("user");
        if (intent.getAction() != null && context != null)
        {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                // int hour=intent.getIntExtra("hour",1);
                // int min=intent.getIntExtra("minutes",1);
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                // NotificationScheduler.setReminder(context, ReservationNotification.class,hour,min);
                return;
            }
        }
        Log.d(TAG, "onReceive: ");

        //Trigger the notification
        NotificationScheduler.showNotification(context, MainActivity.class,
                user+"have reserve at"+ hour +":"+min+" Time Please arrange a table", "Watch them now?");


    }


}
