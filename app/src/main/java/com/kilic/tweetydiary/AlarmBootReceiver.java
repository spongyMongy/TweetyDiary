package com.kilic.tweetydiary;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;
import static com.kilic.tweetydiary.Constants.NOTIFICATION_ID;

public class AlarmBootReceiver extends BroadcastReceiver {
    private static AlarmManager alarmManagerElapsed;
    private static PendingIntent alarmIntentElapsed;
    public static int ALARM_TYPE_RTC = 100;
    public static NotificationManager reminderNotifyManager;
    private static final String ACTION_DAILY_NOTIFICATION = "action_daily_notification";
    private static final String NOTIFHOUR = "";
    private static final String NOTIFMIN = "";
    private static SharedPreferences mPreferences;
    private static final String mSharedPrefFile = "com.kilic.tweetydiary.hellosharedprefs";

    private static  int NOTIFICATION_ID = 0;


    public static int ALARM_TYPE_ELAPSED = 101;


    private static final String LOG_TAG = AlarmBootReceiver.class.toString();


    private static final String STATESENT = "state";
   // private static  boolean UPDATECHECK = false;
    //private static boolean CHECK =true;
////////////////////////////////BU SINIF düzenlenecek.. bu reboot için alarm ayarı.. ama timepicker-datepickerla
    //main activ. reminder için seçtiğimiz tarihleri nasıl geri atıyoruz daha yapmadım.. bir ihtimal
    ///  databasee kaydedilip ordan alınabilir (böylece uyg. silinip yüklense dahi alarmlar tkrar yüklenmiş olur gibi)

    @Override
    public void onReceive(Context context, Intent intent) {
// Tuzağı kurduk (yani kurmuş olucaz bitince) bu sınıfla.. eğer reboot fln olursa cihaz yeniden yüklüyor alarmaları direk bu sınıfa gidiyor otomatik olarak.
       // Log.i(LOG_TAG, "Received action: " + intent.getAction());

       try {

           if (intent.getAction().equals("android.intent.action.QUICKBOOT_POWERON")) {
               //only enabling one type of notifications for demo purposes


               scheduleRepeatingElapsedNotification(context);
               Intent newIntent = new Intent(context, MainActivity.class);
               context.startActivity(newIntent);
           }

           if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
               //only enabling one type of notifications for demo purposes
               try {
                   scheduleRepeatingElapsedNotification(context);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }

               Intent newIntent = new Intent(context, MainActivity.class);
               context.startActivity(newIntent);
           }


       }
       catch (Exception ex) {
           ex.printStackTrace();
       }

    }


    public static void scheduleRepeatingElapsedNotification(Context context) {
       // Toast.makeText(context, "Alarm Set"+"OnBootttttttttttttttttt",       Toast.LENGTH_LONG).show();
     //   Log.i(LOG_TAG, "alarm is being set: " );
        NOTIFICATION_ID = 0;
        reminderNotifyManager =  (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);

        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent notifyIntent = new Intent(context, AlarmReceiver.class);
        notifyIntent.setAction(ACTION_DAILY_NOTIFICATION);
        //*********
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (context, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(context);


      //  String str_hour = mPreferences.getString(NOTIFHOUR, "");
      //  String str_min = mPreferences.getString(NOTIFMIN, "");





        try {

            if (mPreferences.contains(STATESENT)) {
                Boolean switchStateWas = mPreferences.getBoolean(STATESENT, false);

                if (switchStateWas == true) {

                    int hr = mPreferences.getInt(NOTIFHOUR,20);
                    int mn = mPreferences.getInt(NOTIFMIN,0);


                    /////Integer.parseInt(str_hour));
                    ///// Integer.parseInt(str_min));
                    cal.set(Calendar.HOUR_OF_DAY, hr);
                    cal.set(Calendar.MINUTE, mn);
//                    cal.set(Calendar.SECOND, 1);

                    // long triggerTime = SystemClock.elapsedRealtime();
                 //   long repeatInterval = 60*1000;





                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60 * 1000 * 60 * 24, notifyPendingIntent);
//s
                    SettingsFragment stf = new SettingsFragment();
                    stf.turnOnSwitch();
                    //s

                }


            }


        }
        catch(Exception ex){
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();

            preferencesEditor.putBoolean(STATESENT, false);
            preferencesEditor.apply();

                ex.printStackTrace();}









/*
        //Setting intent to class where notification will be handled
        Intent intent = new Intent(context, AlarmReceiver.class);

        //Setting pending intent to respond to broadcast sent by AlarmManager everyday at 8am
        alarmIntentElapsed = PendingIntent.getBroadcast(context, ALARM_TYPE_ELAPSED, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //getting instance of AlarmManager service
        alarmManagerElapsed = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        //Inexact alarm everyday since device is booted up. This is a better choice and
        //scales well when device time settings/locale is changed
        //We're setting alarm to fire notification after 15 minutes, and every 15 minutes there on
        alarmManagerElapsed.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, alarmIntentElapsed);*/
    }

}
