package com.example.bjanash_c196;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

public class AlertReminder extends BroadcastReceiver {
    public static final String LOG_INFO = "AlertReminder Event";

    int notify_id;

    @Override
    public void onReceive(Context context, Intent newInboundIntent) {

        Log.d(LOG_INFO, "Title received from Intent " + newInboundIntent.getStringExtra("my_title"));
        Log.d(LOG_INFO, "Message received from Intent " + newInboundIntent.getStringExtra("my_message"));
        Log.d(LOG_INFO, "Notification received from Intent " + newInboundIntent.getIntExtra("notify_id", 747));

        Calendar calDate = Calendar.getInstance();
        Intent receiveIntent = new Intent(context, MainActivity.class);
        PendingIntent thisPendIntent = PendingIntent.getActivity(context,0,receiveIntent,0);

        NotificationCompat.Builder thisBuilder = new NotificationCompat.Builder(context, "dgwChanId")
                .setSmallIcon(R.drawable.ic_baseline_calendar_today_24)
                .setContentTitle(newInboundIntent.getStringExtra("my_title"))
                .setContentText(newInboundIntent.getStringExtra("my_message"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(thisPendIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(newInboundIntent.getStringExtra("my_message")))
                .setAutoCancel(true);
        notify_id = newInboundIntent.getIntExtra("notify_id", 747);
        NotificationManagerCompat notifyManager = NotificationManagerCompat.from(context);
        notifyManager.notify(notify_id, thisBuilder.build());
        Log.d(LOG_INFO, "Notify Id is " + notify_id);
    }
}
