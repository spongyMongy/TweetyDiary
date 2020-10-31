package com.kilic.tweetydiary;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.kilic.tweetydiary.R;

public class MyEditTextPreference  extends EditTextPreference{


    public MyEditTextPreference(Context context) {
        super(context);
    }

    @Override
    public View getView(View convertView, ViewGroup parent) {
        View view = super.getView(convertView, parent);

        return view;
    }


}
