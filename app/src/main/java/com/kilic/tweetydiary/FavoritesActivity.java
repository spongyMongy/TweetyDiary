package com.kilic.tweetydiary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

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

public class FavoritesActivity extends AppCompatActivity {
    TextView diary_text ;
    String timeMessage=""; //for getting the choosen date of the reminder and show as a toast message TOGETHER with the choosen time.
    static RecyclerView mRecyclerView2;
    static FavoritesListAdapter mAdapter2;  //for recyclerview
  ///////////////  private AdView mAdViewFavorites;
    private String COLOR = "color";
    ActionBar actionBar;


    private static SharedPreferences mPreferences;
    private static final String mSharedPrefFile = "com.kilic.tweetydiary.hellosharedprefs";
    private static final String STATE = "state";

    private static final String BOUGHT = "boughtstate";



    //For database
    private WordListOpenHelper2 mDB2;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        // to maek fullsecreen
      /////////  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        if(getSupportActionBar() != null){
            actionBar = getSupportActionBar();
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient5));
        }


        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "G");

      ///////////////  mAdViewFavorites = findViewById(R.id.adViewFavorites);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (mPreferences.contains(BOUGHT)) {
            Boolean boughtState= mPreferences.getBoolean(BOUGHT, false);
            if (boughtState == true) {
                setContentView(R.layout.activity_favorites_withnoads);
                // Toast.makeText(this, "ffffffffffff", Toast.LENGTH_SHORT).show();

            }
            else{
              setContentView(R.layout.activity_favorites);
           ///////////////     AdRequest adRequest3 = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
           /////////////////     mAdViewFavorites .loadAd(adRequest3);
                //  Toast.makeText(this, "kkkkkkkkkk", Toast.LENGTH_SHORT).show();

            }

        }

        else{

            setContentView(R.layout.activity_favorites);
       ///////////     AdRequest adRequest3 = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
       ///////////     mAdViewFavorites.loadAd(adRequest3);
            // Toast.makeText(this, "kkkkkkkkkk", Toast.LENGTH_SHORT).show();

        }


////////////////////Çç
/*
        mAdViewFavorites = findViewById(R.id.adViewFavorites);
        AdRequest adRequest3 = new AdRequest.Builder().build();
        mAdViewFavorites.loadAd(adRequest3);*/



        mDB2 = new WordListOpenHelper2(this);
        mLayoutManager = new LinearLayoutManager(FavoritesActivity.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);


        //diary_text = (TextView) findViewById(R.id.favorites_entry);
        mRecyclerView2 = (RecyclerView) findViewById(R.id.recyclerviewForFavorites);
        mRecyclerView2.setItemAnimator(new DefaultItemAnimator());  //animeting fln herhalde.
// Create an adapter and supply the data to be displayed.
        ///ç  mAdapter = new EntryListAdapter(this, mEntryList);
        mAdapter2 = new FavoritesListAdapter (this, mDB2);

// Connect the adapter with the RecyclerView.
        mRecyclerView2.setAdapter(mAdapter2);
// Give the RecyclerView a default layout manager.
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));


        mRecyclerView2.setLayoutManager(mLayoutManager);
        mRecyclerView2.setAdapter(mAdapter2);







    }


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }



    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idshare :
                //implicit intent
                shareImplicitIntent();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }



    private void shareImplicitIntent() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);

        String title = FavoritesListAdapter.entryToNotification2;
        //String title = EntryListAdapter.clickedEntry;
        shareIntent.putExtra(Intent.EXTRA_TEXT, title);
        shareIntent.setType("text/plain");
        Intent chooser = Intent.createChooser(shareIntent, title);
// Resolve the intent before starting the activity
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }



    public void onResume() {
        super.onResume();

        try {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ActionBar actionBar = getSupportActionBar();
        /*    Toolbar actionBarToolbar = (Toolbar)this.findViewById(R.id.action_bar);
            if (actionBarToolbar != null)
              actionBarToolbar.setTitleTextColor(Color.RED);*/


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
           /////////waw:)     actionBar.setTitle(Html.fromHtml("<font color='#000099'>Hello World</font>"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                    //if (actionBarToolbar != null)
                      //  actionBarToolbar.setTitleTextColor(Color.RED);
                }


            } else if (colorstate.equals("#eeeeee")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient12));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
            }
            // Toast.makeText(this, "333", Toast.LENGTH_SHORT).show();

        }

    }catch (Exception ex) {ex.printStackTrace();}

    }





}
