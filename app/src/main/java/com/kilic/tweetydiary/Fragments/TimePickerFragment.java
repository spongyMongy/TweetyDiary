package com.kilic.tweetydiary.Fragments;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.kilic.tweetydiary.MainActivity;
import com.kilic.tweetydiary.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public  class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    public static int hourr;
    public static int minutee;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

// Use the current time as the default values for the picker.
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
// Create a new instance of TimePickerDialog and return it.
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
// Set the activity to the Main Activity.
                //MainActivity activity = (MainActivity) getActivity();

 //Invoke Main Activity 's processTimePickerResult() method.
     //if(activity.processTimePickerResult(hourOfDay, minute)!=null){}


        hourr=hourOfDay;
        minutee=minute;




        MainActivity activity = (MainActivity) getActivity();
     //ç   activity.processTimePickerResult(hourOfDay, minute);

        }





     //  Settings sactivity = (Settings) getActivity();
//sactivity.processTimePickerResult(hourOfDay, minute);

        //settingsFragment2 activity=(settingsFragment2) getParentFragment();
        //activity.processTimePickerResult(hourOfDay, minute);



}


