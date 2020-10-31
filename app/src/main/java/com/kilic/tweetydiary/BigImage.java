package com.kilic.tweetydiary;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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

public class BigImage extends AppCompatActivity {
    ImageView myImage;
    String picture = "";
    ImageView imageViewZoomed;
    private String COLOR = "color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
     ///////   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        imageViewZoomed= findViewById(R.id.imageViewZoomed);

try {
    picture = getIntent().getStringExtra("image_url");

    Picasso.with(this).load(Uri.parse(picture))


            .into(imageViewZoomed);

}
catch(Exception ex){ex.printStackTrace();}

    }







    public void onResume() {
        super.onResume();



        try{
            SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


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
                    //  fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fab2));
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
                    actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient11));
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
                    actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient11));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                    }
                }

            } else {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient11));
                //  Toast.makeText(this, "333", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }


            }

        } catch (Exception ex) {ex.printStackTrace();}







        // prefs.getPreferenceScreen().getSharedPreferences()
        //       .registerOnSharedPreferenceChangeListener(this);

    }

}
