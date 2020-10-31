package com.kilic.tweetydiary;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdView;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.kilic.tweetydiary.R.color.color1;
import static com.kilic.tweetydiary.R.color.color10;
import static com.kilic.tweetydiary.R.color.color12;
import static com.kilic.tweetydiary.R.color.color13;
import static com.kilic.tweetydiary.R.color.color14;
import static com.kilic.tweetydiary.R.color.color2;
import static com.kilic.tweetydiary.R.color.color3;
import static com.kilic.tweetydiary.R.color.color4;
import static com.kilic.tweetydiary.R.color.color5;
import static com.kilic.tweetydiary.R.color.color6;
import static com.kilic.tweetydiary.R.color.color7;
import static com.kilic.tweetydiary.R.color.color8;
import static com.kilic.tweetydiary.R.color.color9;

public class SettingsActivity extends AppCompatActivity {
    private AdView mAdViewSettingsss;
    private AdView adView;


    public static NotificationManager reminderNotifyManager;

    private String COLOR = "color";


    private  final int REQUEST_CODE2 =123;
    private static final String SETTHEPASSWORD = "setthepassword";




    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        //to hide the status bar
       /// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settingsactivity);

       ////////////////// ActionBar actionBar = getSupportActionBar();
       ////////////// actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient));


        /////////çç

/*

        mAdViewSettingsss = findViewById(R.id.adViewSettingsActivity);
        AdRequest adRequest7 = new AdRequest.Builder().build();
        mAdViewSettingsss.loadAd(adRequest7);
*/





        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();


        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "G");



    }







/*   // meditTextPreference = (EditTextPreference)  getPreferenceManager().findPreference("edittext_preference");
private static  Preference.OnPreferenceChangeListener listener= new Preference.OnPreferenceChangeListener() {
       @Override
       public boolean onPreferenceChange(Preference preference, Object newValue) {
           return false;
       }
   };*/

   /* public static void processTimePickerResult(Context context, int hour, int minute) {
// Convert time elements into strings.
        //\ hourrr= TimePickerNotificationFragment.hourr;
        //\ minuteee= TimePickerNotificationFragment.minutee;
        hourrr=hour;
        minuteee=minute;
        // Toast.makeText(context, "entered time **" + TimePickerNotificationFragment.hourr + ":" + TimePickerNotificationFragment.minutee, Toast.LENGTH_SHORT).show();

        String hour_string = Integer.toString(hourrr);
        String minute_string = Integer.toString(minuteee);
        if(minuteee<10){

            minute_string="0"+minuteee;
            timeMessage = (hour_string + ":" + minute_string);
            ///\  Toast.makeText(getActivity(), getString(string.time) + " " +timeMessage,
            ///\       Toast.LENGTH_SHORT).show();

        }
// Assign the concatenated strings to timeMessage.
        else {
            timeMessage = (hour_string + ":" + minute_string);
            //   Toast.makeText(this, "Reminder is set to "   +getString(R.string.time) + " " + timeMessage,
            //           Toast.LENGTH_SHORT).show();
        }

        //Constants.alarmFromWho="fromDailyReminder";
        reminderNotifyManager =  (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);







        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent notifyIntent = new Intent(context, AlarmReceiver.class);
        notifyIntent.setAction(ACTION_DAILY_NOTIFICATION);
        //*********
        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (context, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();


        cal.set(Calendar.HOUR_OF_DAY, hourrr);
        cal.set(Calendar.MINUTE, minuteee);
        // cal.set(Calendar.SECOND, 1);

        // long triggerTime = SystemClock.elapsedRealtime();



//////////////////////////////çççç
    *//*    mPreferences = context.getSharedPreferences(mSharedPrefFile, 0);
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(NOTIFHOUR, hourrr);
        preferencesEditor.putInt(NOTIFMIN, minuteee);
        preferencesEditor.apply();
        preferencesEditor.commit();*//*

        //long repeatInterval = 60*1000;

        // alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), notifyPendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60*1000*60*24, notifyPendingIntent);




        Toast toast=  Toast.makeText(context, context.getString(R.string.toastreminderSetted) + " " + timeMessage,  Toast.LENGTH_SHORT);
        View viewToast = toast.getView();
        viewToast.getBackground().setColorFilter(context.getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
        toast.show();




        //int hr = mPreferences.getInt(NOTIFHOUR,0);
        // int mn = mPreferences.getInt(NOTIFMIN,0);
        //  Toast.makeText(context, "AAAAAA "  + hr + " " + mn,  Toast.LENGTH_SHORT).show();


    }
*/


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {



        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onRequestPermissionsResult(requestCode, permissions,grantResults);
        }


    }












    public void onResume() {
        super.onResume();
        SharedPreferences mPreferences = getDefaultSharedPreferences(getApplicationContext());

try{
        ActionBar actionBar = getSupportActionBar();

        if (mPreferences.contains(COLOR)) {
            String colorstate = mPreferences.getString(COLOR, "ffffff");


            if (colorstate.equals("#33b5e5")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient1));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color1));
                }
            } else if (colorstate.equals("#aa66cc")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient2));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color2));
                }
            } else if (colorstate.equals("#99cc00")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient3));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color3));
                }
            } else if (colorstate.equals("#ffbb33")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient4));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color4));
                }
            } else if (colorstate.equals("#ff4444")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient5));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color5));
                }
            } else if (colorstate.equals("#0099cc")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient6));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color6));
                }
            } else if (colorstate.equals("#9933cc")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient7));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color7));
                }
            } else if (colorstate.equals("#669900")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient8));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color8));
                }
            } else if (colorstate.equals("#ff8800")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient9));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color9));
                }
            } else if (colorstate.equals("#cc0000")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient10));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color10));
                }
            } else if (colorstate.equals("#ffffff")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient12));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }
            } else if (colorstate.equals("#eeeeee")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient12));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }
            } else if (colorstate.equals("#cccccc")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient13));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color13));
                }
            } else if (colorstate.equals("#888888")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient14));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color14));
                }
            } else {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient12));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }
            }

        } else {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient12));
            // Toast.makeText(this, "333", Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
            }
        }
    }
    catch (Exception ex) {ex.printStackTrace();}

    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        //super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }




        /////////////Çç
       /* if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }*/
        // Toast.makeText(getContext(), "hiiiiiiiiiiiiiiiiiii", Toast.LENGTH_SHORT).show();
        //super.onActivityResult(requestCode, resultCode, data);


/*








        switch (requestCode) {
            case REQUEST_CODE2:
                if (resultCode == EnterPinActivity.RESULT_BACK_PRESSED) {
                    Toast.makeText(this, "back pressed", Toast.LENGTH_LONG).show();

                }
                break;
        }*/









       /* Toast.makeText(this, " pressed", Toast.LENGTH_LONG).show();





            if (requestCode==123) {
                if (resultCode == EnterPinActivity.RESULT_BACK_PRESSED)   {//resultCode == EnterPinActivity.RESULT_BACK_PRESSED
                if (true) {
                    Toast.makeText(this, "back pressedddddddddd", Toast.LENGTH_LONG).show();


                    SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                    preferencesEditor.putBoolean(SETTHEPASSWORD, false);
                    preferencesEditor.apply();
                    Toast.makeText(this, "pass eklendi", Toast.LENGTH_SHORT).show();
                    SettingsFragment.myPasswordPref.setChecked(false);


                }
            }
            }*/






      /*  switch (requestCode) {
            case REQUEST_CODE2:

                if (resultCode == EnterPinActivity.RESULT_SHARED) {
                    Toast.makeText(getContext(), "hiiiiiiiiiiiiiiiiiii", Toast.LENGTH_SHORT).show();

                    if (mPreferences.contains(RESULTSETTEDSCUCCESS)) {

                        if (mPreferences.getBoolean(RESULTSETTEDSCUCCESS, true) == true) {
                            SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                            SharedPreferences.Editor preferencesEditor = mPreferences.edit();

                            preferencesEditor = getPreferenceManager().getSharedPreferences().edit();
                            preferencesEditor.putBoolean(SETTHEPASSWORD, true);
                            preferencesEditor.apply();
                            Toast.makeText(getContext(), "pass eklendi", Toast.LENGTH_SHORT).show();
                            myPasswordPref.setChecked(true);

                        }
                    }

                }

                break;
        }*/


    }



}
