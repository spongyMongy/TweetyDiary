package com.kilic.tweetydiary;

import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;
import android.support.v7.preference.PreferenceManager;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class MYAPPLICATION extends MultiDexApplication {


    SharedPreferences mPreferences;
    SharedPreferences.Editor preferencesEditor ;
    private static final String FONTSTATE = "fontstate";
String myfont;


    public void onCreate() {
        super.onCreate();


        /*if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);*/






        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferencesEditor = mPreferences.edit();
        if(mPreferences.contains(FONTSTATE)){
           myfont= mPreferences.getString(FONTSTATE,"fonts/AnjelikaRose.ttf" );


        }

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath(myfont)
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
        //....
    }
}
