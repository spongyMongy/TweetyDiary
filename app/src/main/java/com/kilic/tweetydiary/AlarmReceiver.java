package com.kilic.tweetydiary;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import static android.provider.Settings.Global.getString;
import static com.kilic.tweetydiary.R.string.notificationMessage;

public class AlarmReceiver extends BroadcastReceiver {
    SharedPreferences mPreferences;
    String nickkey;
    private final String NICKNAME_KEY = "nickname";
    public static final String NOTIFICATION_CHANNEL_ID = "10001";


    public String sentenceToBePut;
    private static final int NOTIFICATION_ID = 0;
    private static final String ACTION_DAILY_NOTIFICATION = "action_daily_notification";
    private static final String ACTION_REMINDER_NOTIFICATION = "action_reminder_notification";



    public AlarmReceiver() {
    }


    public void onReceive(Context context, Intent intent) {
//       if(Constants.alarmFromWho.equals("fromDailyReminder")) sentenceToBePut="Waiting for you to enter your future past";
        //    if(Constants.alarmFromWho.equals("fromDiaryReminder")) sentenceToBePut="Do you remembered me?";
//bu constantla halletmeye çalışınca sorunluç aklında olsun.. hemen alarmı kurunca sorun olmuyor da telerfon uyuyunca fln gidiyor constantlar, dolayısıyla hata:Q
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Create the content intent for the notification, which launches this activity
        Intent contentIntent = new Intent(context, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.owl2)

                //.setContentText(EntryListAdapter.entryToNotification)
                .setContentIntent(contentPendingIntent)
                .setAutoCancel(true)
            .setColor(ContextCompat.getColor(context, R.color.color7))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);


        /*if (intent.getAction().equalsIgnoreCase(ACTION_REMINDER_NOTIFICATION))
        {builder.setContentTitle(context.getResources().getString(notificationMessage));
            builder.setContentText(context.getResources().getString(notificationMessage));}
            */

        try {
            if (intent.getAction().equalsIgnoreCase(ACTION_DAILY_NOTIFICATION)) {
                mPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());


                if (!mPreferences.getString(NICKNAME_KEY, "").equals("")) {
                    nickkey = context.getString(R.string.alarm_name) + " " + mPreferences.getString(NICKNAME_KEY, "");
                } else
                    nickkey = context.getString(R.string.alarm_name);


                builder.setContentTitle(nickkey);
                builder.setContentText(context.getResources().getString(notificationMessage));

                //Deliver the notification

            }
        }


catch (Exception ex) {
            ex.printStackTrace();
        }




        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                assert notificationManager != null;
                builder.setChannelId(NOTIFICATION_CHANNEL_ID);


                notificationManager.createNotificationChannel(notificationChannel);
            }
            assert notificationManager != null;
            notificationManager.notify(0 /* Request Code */, builder.build());

            notificationManager.notify(NOTIFICATION_ID, builder.build());

        }

        catch (Exception ex) {
            ex.printStackTrace();
        }





    }
}
