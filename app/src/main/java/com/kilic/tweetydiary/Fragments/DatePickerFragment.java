package com.kilic.tweetydiary.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.kilic.tweetydiary.MainActivity;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public static int yearr;
    public static int monthh;
    public static int dayy;

    public DatePickerFragment() {
        // Required empty public constructor
    }



    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
// Set the activity to the Main Activity.
        MainActivity activity = (MainActivity) getActivity();
// Invoke Main Activity's processDatePickerResult() method.
       /////////çç activity.processDatePickerResult(year, month, day);


    }





}
