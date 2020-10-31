package com.kilic.tweetydiary.Fragments;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

import com.kilic.tweetydiary.MainActivity;
import com.kilic.tweetydiary.NewEntry;
import com.kilic.tweetydiary.R;
import com.kilic.tweetydiary.SettingsActivity;
import com.kilic.tweetydiary.SettingsFragment;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerNotificationFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener  {

    public static Integer hourr;
    public static Integer minutee;

    private   static SharedPreferences mPreferences;
    private static final String CANCELSTATE = "cancelstate";



    public Dialog onCreateDialog(Bundle savedInstanceState) {
         mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

// Use the current time as the default values for the picker.
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
// Create a new instance of TimePickerDialog and return it.
    ////////////////çç
     /*return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
   */
        //TimePicker timePicker = new TimePicker(new ContextThemeWrapper(MainActivity.this, R.style.CustomPickerTheme));


        TimePickerDialog tp =new TimePickerDialog(getActivity(), R.style.CustomPickerTheme, this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
        tp.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which)
            {
                if (which == DialogInterface.BUTTON_NEGATIVE)
                {
                    SharedPreferences.Editor preferencesEditor = mPreferences.edit();

                    preferencesEditor.putBoolean(CANCELSTATE, true);
                    preferencesEditor.apply();
                    //SettingsFragment settingsFragment = new SettingsFragment();
                  //  Toast.makeText(getContext(), "çalışmıyor cancel", Toast.LENGTH_SHORT).show();
                   SettingsFragment stf = new SettingsFragment();
                //  stf.turnOffSwitch();
                   // Toast.makeText(getContext(), "turn off switch "  , Toast.LENGTH_SHORT).show();
               //     FragmentManager fm = getFragmentManager();
                //    SettingsFragment fragm = (SettingsFragment) fm.findFragmentById(R.id.settings);
               ///     fragm.turnOffSwitch();

                }
                if (which == DialogInterface.BUTTON_POSITIVE){
                    SharedPreferences.Editor preferencesEditor = mPreferences.edit();

                    preferencesEditor.putBoolean(CANCELSTATE, false);
                    preferencesEditor.apply();
                }
            }
        });

return tp;
    }




    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.hourr=hourOfDay;
        this.minutee=minute;
       // Toast.makeText(getContext(), "entered time **" + hourr + ":" + minutee, Toast.LENGTH_SHORT).show();



      // SettingsActivity activity = (SettingsActivity) getActivity();





        SettingsFragment.processTimePickerResult(getContext(), hourOfDay, minute);



    }
}
