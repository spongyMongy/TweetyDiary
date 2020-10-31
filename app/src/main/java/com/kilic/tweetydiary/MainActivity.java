package com.kilic.tweetydiary;

import android.Manifest;
import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LoaderManager;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amirarcane.lockscreen.activity.EnterPinActivity;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAd;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.kc.unsplash.Unsplash;
import com.kc.unsplash.models.Photo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static android.view.View.GONE;
import static com.kilic.tweetydiary.Contract.CONTENT_PATH;
import static com.kilic.tweetydiary.Contract.CONTENT_URI;
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

////////SharedPreferences.OnSharedPreferenceChangeListener,  gerekirse implement et ama gerek yok şimdilik
public class MainActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>,  BillingProcessor.IBillingHandler  {
    //  private static final String FONT_TEXT = "font/ALEAWB.TTF";
    // private static final String FONT_NUMBER = "font/BLKCHCRY.TTF";
    private static final String UNSPLASH_QUERY = "unsplashquery";
    private final String CLIENT_ID = "fff11d696e2fe2b165550d618c8a2cb25d3a5c277598392238b7200f7138ba17";
    //for getting photo from API
    private Unsplash unsplash;
    private static final String FONTSTATE = "fontstate";
    private final String ADSSHOWING = "ads_showing";
    Bitmap bitmap2;
    private ImageView imageViewAPI;
    BottomNavigationView bottomNavigationView;
    String timeMessage=""; //for getting the choosen date of the reminder and show as a toast message TOGETHER with the choosen time.
    RecyclerView mRecyclerView;
    static EntryListAdapter mAdapter;  //for recyclerview
    private static final String BOUGHT = "boughtstate";
    private FirebaseAnalytics mFirebaseAnalytics;
    private AdView mAdView;
    private InterstitialAd interstitialAd;

    private String COLOR = "color";
    private  FloatingActionButton fab;
    private ConstraintLayout constraintLayout;
    boolean visible;
    private TextView photographerTextView;
    private TextView unsplashTextView;
    private static final int REQUEST_CODESavetoGallery = 012;

    private TextView photographerTextViewNOADS;
    private TextView unsplashTextViewNOADS;
    public DrawerLayout drawerLayout;
    ImageView pageChangeImage;

    public static SQLiteDatabase mWritableDB;
    public static SQLiteDatabase mWritableDB2;
    private String PASSSetted = "passsetted" ;
    private static final int REQUEST_CODE = 123;
    private static final int REQUEST_CODEFROMINTRO = 456;
    private static final int REQUEST_CODEPREMIUM = 789;

    private RelativeLayout photoinfosNOADS;
    private RelativeLayout photoinfos;
    ImageView introfabIV;


    ImageView imageView4;
    ///////////////// private InterstitialAd mInterstitialAd;
    ///////////// private InterstitialAd mInterstitialAdEntryImage;
    private CardView cardViewNoads;
    private CardView cardViewADS;

    private final String NICKNAME_KEY = "nickname";
    NativeAd nativeAd;
    public int randomNum;
    SharedPreferences mPreferences;
    //For database
    public ImageView imgdaynight;

    private WordListOpenHelper mDB;
    private static final String SETTHEPASSWORD = "setthepassword";

    private android.support.v7.widget.AppCompatEditText editTextSearchnightMode;

    //for using the same nickname everytime app started..

    BillingProcessor bp;
    SharedPreferences.Editor preferencesEditor;
    // for reverse order of recyclerview list
    private LinearLayoutManager mLayoutManager;

    FloatingActionButton floatingActionButtonDownloadImage;

    public static NotificationManager mNotifyManager;
    /// private RewardedVideoAd mRewardedVideoAd;
    private TextView quotes;
    private  TextView author;


    //ç public static ArrayList<String> mEntryList = new ArrayList<>(); // ****** sql fln gelicne bu arrayslisti silicezz ona geçicez flan
    //ç  private int mCount = 0;
    //public static RecyclerView mRecyclerView2;
    // public static FavoritesListAdapter mAdapter2;





    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    Bundle extras;

    Photo photo1;

    ByteArrayOutputStream bytearrayoutputstream;
    File file;
    FileOutputStream fileoutputstream;


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide the status bar

        //  getSupportActionBar().hide();
        /*if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
*/






        mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferencesEditor = mPreferences.edit();


        bp = BillingProcessor.newBillingProcessor(MainActivity.this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzPcXNQHMhODCjDMDZCilJZpkqO43oZgSF5snGD7W797tKNqikMwqUg6tRzeI544hRKvuU33G+SvLPGOPEu3pTDjzpCviVyR/0V/m5soRj0nfbTM6jaN2NFikKLKl5uPZMzXKocNBT+jHHEiTHdkFt4koqOheDnPOqzZxDz8L1hkH96dJDVcBzAL+IO+3gAE5mkXTEcqS5gHy7K/zYVYaMutImOcFWIiFgl67zAmJ4TiaugTNDyuOuqdCxfxc5IgfiVVBoKN/ZCGk/RjrKtfGJBt3YaCMocwa5A3TMhov2m3M4qccqAk1gG3ys9MgeZkpd7WuZ6I6/1M8zklAeQ3FCwIDAQAB", this); // doesn't bind
        bp.initialize(); // binds
        ////Ã§
        bp.loadOwnedPurchasesFromGoogle();



        interstitialAd = new InterstitialAd(this, getString(R.string.adfacebookbannerid4mainActivityInterstitial));
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        //AdSettings.addTestDevice("3b5b04bd-592c-468a-9e18-3d6b363651bc");

        interstitialAd.loadAd();









        extras = new Bundle();
        extras.putString("max_ad_content_rating", "G");


        setContentView(R.layout.activity_main);


   /*     mInterstitialAdEntryImage = new InterstitialAd(this);
        mInterstitialAdEntryImage.setAdUnitId(getString(R.string.ad_onlyentryinterstatialimage));
        mInterstitialAdEntryImage.loadAd(new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build());

        if (mInterstitialAdEntryImage.isLoaded() ) {
            mInterstitialAdEntryImage.show();}*/
        ///////////// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
// Obtain the FirebaseAnalytics instance.


/*
        mInterstitialAdEntryImage = new InterstitialAd(this);
        mInterstitialAdEntryImage.setAdUnitId(getString(R.string.ad_onlyentryinterstatialimage));
        mInterstitialAdEntryImage.loadAd(new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build());
*/






        // Intent i5 = new Intent(MainActivity.this, IntroActivity.class);
        //  startActivity(i5);

        preferencesEditor.putInt(ADSSHOWING,0).apply();
        preferencesEditor.putString(UNSPLASH_QUERY,"pink love").apply();


        ////////////Çç











        try {
            if (mPreferences.contains(SETTHEPASSWORD)) {
                if (mPreferences.getBoolean(SETTHEPASSWORD, true) == true) {
                    Intent intent = new Intent(MainActivity.this, EnterPinActivity.class);

                    startActivityForResult(intent, REQUEST_CODE);

                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


        MobileAds.initialize(this, getString(R.string.app_id));















   /*if(mPreferences.contains(PASSSetted)){
     //  Intent inten1= new Intent(this,PinViewActivity.class);


   }
*/


        //quoteCardView= findViewById(R.id.quoteCardView);
        // linearLayout= findViewById(R.id.llContainer);
        // rLayout= findViewById(R.id.rlContainer);

        ///////////////////////////////// ActionBar actionBar = getSupportActionBar();
        // actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient));
        ////////////////////////////////    actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient));
        //int color = android.preference.PreferenceManager.getDefaultSharedPreferences(this).getInt("color_preference", Color.RED);


        /*if (mPreferences.contains(COLOR)) {
            String colorstate=mPreferences.getString(COLOR, "ffffff");
            if(colorstate.equals("#9933cc")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient1));
                 Toast.makeText(this, "111", Toast.LENGTH_SHORT).show();

            }


            else{
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient));
                Toast.makeText(this, "222", Toast.LENGTH_SHORT).show();

            }

        }

        else{
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient));
            Toast.makeText(this, "333", Toast.LENGTH_SHORT).show();


        }*/

        // coordinatorLayoutNoAds= findViewById(R.id.coordinatorLayoutNoAds);


        unsplash = new Unsplash(CLIENT_ID);





        if (mPreferences.contains(BOUGHT)) {
            Boolean boughtState = mPreferences.getBoolean(BOUGHT, false);
            if (boughtState == true) {
                setContentView(R.layout.activity_main_withnoads);

                /*pageChangeImage = (ImageView) findViewById(R.id.imageView10);
              //  pageChangeImage.setVisibility(View.GONE);
introfabIV=findViewById(R.id.introfabIV);
                introfabIV.setVisibility(View.INVISIBLE);*/
                floatingActionButtonDownloadImage  = findViewById(R.id.floatingActionButtonDownloadImage);

                // Toast.makeText(this, "ffffffffffff", Toast.LENGTH_SHORT).show();
                photoinfosNOADS = findViewById(R.id.photoinfosNOADS);
                cardViewNoads = findViewById(R.id.cardViewNoAds);
                cardViewNoads.setVisibility(GONE);
                photoinfosNOADS.setVisibility(GONE);
                imageViewAPI = findViewById(R.id.imageViewNoADS);
                floatingActionButtonDownloadImage.setVisibility(GONE);
                floatingActionButtonDownloadImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveImagetoGallery();
                    }
                });


                ////////////  linearLayoutUP= findViewById(R.id.linearLayoutUP);
                /////////////  linearLayoutDOWN= findViewById(R.id.linearLayoutDOWN);
                //         linearLayoutUP.setVisibility(View.GONE);
                // linearLayoutUP.setWeightSum(0f);
                //  linearLayoutDOWN.setWeightSum(1f);

                cardViewNoads.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardViewNoads.setVisibility(GONE);
                        photoinfosNOADS.setVisibility(GONE);
                        floatingActionButtonDownloadImage.setVisibility(GONE);
                    }
                });


            } else {
                setContentView(R.layout.activity_main);
                imageViewAPI = findViewById(R.id.imageViewADS);
                floatingActionButtonDownloadImage  = findViewById(R.id.floatingActionButtonDownloadImage);

                ///////////////%&/&%/+%&/%&/%&/Bottom reklam ADMOB
            /*
                mAdView = findViewById(R.id.adView);
                 AdRequest adRequest = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
                mAdView.loadAd(adRequest);*/

                photoinfos = findViewById(R.id.photoinfos);
                photoinfos.setVisibility(GONE);

                cardViewADS = findViewById(R.id.cardViewADS);
                cardViewADS.setVisibility(GONE);
                floatingActionButtonDownloadImage.setVisibility(GONE);
                floatingActionButtonDownloadImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveImagetoGallery();
                    }
                });

                cardViewADS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardViewADS.setVisibility(GONE);
                        photoinfos.setVisibility(GONE);
                        floatingActionButtonDownloadImage.setVisibility(GONE);

                    }
                });



            }

        } else {

            setContentView(R.layout.activity_main);

            /////////////////&%/%&/%&/%& ADMOB bottom reklam
           /* mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
            mAdView.loadAd(adRequest);*/
            floatingActionButtonDownloadImage  = findViewById(R.id.floatingActionButtonDownloadImage);

            photoinfos = findViewById(R.id.photoinfos);
            photoinfos.setVisibility(GONE);
            imageViewAPI = findViewById(R.id.imageViewADS);
            cardViewADS = findViewById(R.id.cardViewADS);
            cardViewADS.setVisibility(GONE);
            floatingActionButtonDownloadImage.setVisibility(GONE);
            floatingActionButtonDownloadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveImagetoGallery();
                }
            });


            cardViewADS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardViewADS.setVisibility(GONE);
                    photoinfos.setVisibility(GONE);
                    floatingActionButtonDownloadImage.setVisibility(GONE);

                }
            });



        }








        if (isFirstTime()) {
            // What you do when the Application is Opened First time Goes here

            ////////////////////////////////////////////////////////////////////////////////////////////////
            preferencesEditor.putString(FONTSTATE, "fonts/Herofont.ttf").apply();

            // setContentView(R.layout.activity_main_withnoads);
            Intent i2 = new Intent(MainActivity.this, IntroActivity.class);
            startActivityForResult(i2, REQUEST_CODEFROMINTRO);


        }






        // imageViewAPI = findViewById(R.id.imageView2);

        ///////////////////////// mInterstitialAd = new InterstitialAd(this);
        ////////////////// mInterstitialAd.setAdUnitId(getString(R.string.ad_unitinterstitial_id));
        //////////////////////  mInterstitialAd.loadAd(new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build());












        // constraintLayout=findViewById(R.id.coordinatorLayout);

        fab = (FloatingActionButton) findViewById(R.id.fab);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        if (dl != null) {
            dl.addDrawerListener(t);
        }

        if (t != null) {
            t.syncState();
        }


        // dl.addDrawerListener(t);
        //t.syncState();

        /////////////////////////////////   getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nv = (NavigationView) findViewById(R.id.nv);
        nv.setItemIconTintList(null);

        View header = nv.getHeaderView(0);

        ImageView imageView4= (ImageView) header.findViewById(R.id.subscriptionIV);
        ImageButton imageButton= (ImageButton) header.findViewById(R.id.navImBut);





        final EditText editText = (EditText) header.findViewById(R.id.navET);

        if (bp.isPurchased("removeads")) {
            preferencesEditor.putBoolean(BOUGHT, true);
            preferencesEditor.apply();
            imageView4.setVisibility(View.GONE);
//imageButton.setEnabled(true);


        }
        else{
            // imageButton.setEnabled(false);
        }



        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!bp.isPurchased("removeads")){

                    InputMethodManager inputMethodManager =
                            (InputMethodManager) MainActivity.this.getSystemService(
                                    Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(
                            MainActivity.this.getCurrentFocus().getWindowToken(), 0);


                    boolean isOneTimePurchaseSupported = bp.isOneTimePurchaseSupported();
                    if (isOneTimePurchaseSupported) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        LayoutInflater inflater = getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.premium_dialog_layout, null);
                        builder.setView(dialogView);
                        final AlertDialog dialog = builder.create();
                        dialog.show();
                        bp.purchase(MainActivity.this, "removeads");

                    }
                }


            }
        });











        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!bp.isPurchased("removeads")){
                    //hide keyboard
                    InputMethodManager inputMethodManager =
                            (InputMethodManager) MainActivity.this.getSystemService(
                                    Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(
                            MainActivity.this.getCurrentFocus().getWindowToken(), 0);


                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.premium_dialog_layout, null);
                    builder.setView(dialogView);
                    final AlertDialog dialog = builder.create();
                    dialog.show();


                }



                if (bp.isPurchased("removeads")) {

                    preferencesEditor.putString(UNSPLASH_QUERY, editText.getText().toString()).apply();
                    produceRandomPhoto();

                    //hide keyboard
                    InputMethodManager inputMethodManager =
                            (InputMethodManager) MainActivity.this.getSystemService(
                                    Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(
                            MainActivity.this.getCurrentFocus().getWindowToken(), 0);


                    Handler handler5 = new Handler();
                    handler5.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            dl.closeDrawer(Gravity.LEFT, true);
                        }
                    }, 800);


                }



            }
        });



        String nickname;

        ///////////////////////////
        /*
             TextView name = (TextView) header.findViewById(R.id.navTV);
        if (mPreferences.contains(NICKNAME_KEY)) {
            nickname = mPreferences.getString(NICKNAME_KEY, "");
            name.setText(getString(R.string.welcome) + "  " + nickname + "  ");
        } else {
            name.setText(getString(R.string.welcome) + "  ");
        }*/


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.dayNight:
                        int nightModeFlags =
                                getResources().getConfiguration().uiMode &
                                        Configuration.UI_MODE_NIGHT_MASK;

                        switch (nightModeFlags) {
                            case Configuration.UI_MODE_NIGHT_YES:
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                                break;

                            case Configuration.UI_MODE_NIGHT_NO:
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                                break;

                        }

                        dl.closeDrawer(Gravity.LEFT, false);
                        recreate();
                        return true;


                    case R.id.action_settings:
                        // item.setChecked(true);

                        goSettings();
                        dl.closeDrawer(Gravity.LEFT, false);

                        //      dl.closeDrawers();


                                                            /* Handler handler = new Handler();
                                                             handler.postDelayed(new Runnable() {
                                                                 @Override
                                                                 public void run() {

                                                                     dl.closeDrawers();

                                                                 }
                                                             }, 1000);*/

                        //item.setChecked(false);

                        return true;


                    case R.id.favorites:
                        Intent intentFavorites = new Intent(MainActivity.this, FavoritesActivity.class);
                        // dl.closeDrawers();

                        startActivity(intentFavorites);
                        dl.closeDrawer(Gravity.LEFT, false);

                        return true;


                    case R.id.moodChart:
                        Intent i = new Intent(MainActivity.this, MoodChartActivity.class);
                        startActivity(i);
                        dl.closeDrawer(Gravity.LEFT, false);

                        return true;


                    case R.id.search:
                        Intent intentSearch = new Intent(MainActivity.this, SearchActivity.class);
                        startActivity(intentSearch);
                        dl.closeDrawer(Gravity.LEFT, false);

                        return true;




                    case R.id.navdrawer_item_settings:


                        Uri uri = Uri.parse("market://details?id=" + MainActivity.this.getPackageName());
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |     ////bu satır silinebilir belki, denersin
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName())));
                        }

                        dl.closeDrawer(Gravity.LEFT, false);

                        return true;




                    case R.id.navdrawer_item_bugReport:
                        Intent it = new Intent(Intent.ACTION_SEND_MULTIPLE);
                        it.putExtra(Intent.EXTRA_EMAIL, new String[] {"vergilstudios3@gmail.com"});
                        it.putExtra(Intent.EXTRA_SUBJECT, "I found a bug for you");

                        //it.setType("text/plain");
                        it.setType("message/rfc822");
                        startActivity(it);
                        return true;





                    case R.id.navdrawer_item_privacyPolicy:
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.wixstatic.com/ugd/6847d3_cae6a29d0d4c4310a0ec230d0b538ed7.pdf"));
                        startActivity(browserIntent);
                        return true;




                    default:
                        return true;
                }


            }

        });


        ////////  Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).into(target);

/////////////////////////////////çç
/*
        MobileAds.initialize(this, getString(R.string.app_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/


/////////////////////////////////////////ççç
        //  mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        //  mRewardedVideoAd.setRewardedVideoAdListener(this);
      /*  try {
            loadRewardedVideoAd();
        }
        catch (Exception ex){ex.printStackTrace();}
*/


        /////////////çç   mRewardedVideoAd.loadAd( "ca-app-pub-3940256099942544/6300978111", new AdRequest.Builder().build());

        //mRewardedVideoAd.loadAd( "ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());




////////////////////////////****************
        /*mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

            }
        });*/



        ////////////////////////////////////////******
        ///////////////////   Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).into(target);




////////////////////////////////////////çççç
        //rewardedvideoad listener
      /*  mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
           ///     Toast.makeText(getBaseContext(),
             ///           "Ad loaded.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdOpened() {
              //  Toast.makeText(getBaseContext(),
                 //       "Ad opened.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoStarted() {

                /////çç

             *//*   Toast toast=   Toast.makeText(getBaseContext(),
                        getString(R.string.thxforcoffee), Toast.LENGTH_LONG);
                View viewToast = toast.getView();
                viewToast.getBackground().setColorFilter(getBaseContext().getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                toast.show();*//*


            }

            @Override
            public void onRewardedVideoAdClosed() {
               // Toast.makeText(getBaseContext(),
                  //      "Ad closed.", Toast.LENGTH_SHORT).show();
                loadRewardedVideoAd();


            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
               // Toast.makeText(getBaseContext(),
                   //     "Ad triggered reward.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                //Toast.makeText(getBaseContext(),
                    //    "Ad left application.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
           ///     Toast.makeText(getBaseContext(),
             ///           "Ad failed to load.", Toast.LENGTH_SHORT).show();
            }


            public void onRewardedVideoCompleted() {
                Toast.makeText(MainActivity.this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();

            }






        });*/










        imgdaynight = findViewById(R.id.dayNight);

        ///////////////////////////////ççççççççç  mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
        mDB = new WordListOpenHelper(this);
        //// Constants.nickName = mPreferences.getString(NICKNAME_KEY, Constants.nickName);


        mLayoutManager = new LinearLayoutManager(MainActivity.this);
       /* mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);*/


        //for crash tirivials
       /* Button crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Crashlytics.getInstance().crash(); // Force a crash
            }
        });
        addContentView(crashButton,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));*/


        /// ç Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ////ç  setSupportActionBar(toolbar);

        //content_maain e bir textview de ekleyebilirsin, o da contextmenu yle sağ tıklanabilir.


//      registerForContextMenu(diary_text);

       /* if (isFirstTime()) {
            // What you do when the Application is Opened First time Goes here






            ///////////////////////
          */
        // Intent intent3= new Intent(this,MainActivity.class);
        // intent3.putExtra("position", 2);
        //  startActivity(intent3);



       /*
        Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy  ");
            String strDate = mdformat.format(calendar.getTime());


            WordListOpenHelper woh= new WordListOpenHelper(getApplicationContext());
            WordListOpenHelper2 woh2= new WordListOpenHelper2(getApplicationContext());
          //  woh.fillDatabaseWithData();
            ContentValues values1 = new ContentValues();
            ContentValues values2 = new ContentValues();
            ContentValues values3 = new ContentValues();
            ContentValues values4 = new ContentValues();
            ContentValues values5 = new ContentValues();



            mWritableDB = woh.getWritableDatabase();
            mWritableDB2 = woh2.getWritableDatabase();



            values1.put(KEY_ENTRY,getResources().getString(R.string.entry1) );
            values2.put(KEY_ENTRY,getResources().getString(R.string.entry2) );
            values3.put(KEY_ENTRY,getResources().getString(R.string.entry3) );
            values3.put(KEY_HEART, 1);

            values5.put(KEY_ENTRY,getResources().getString(R.string.entry3) );
            values5.put(KEY_HEART, 1 );


            values4.put(KEY_NICKNAME_DATE, "@" + getResources().getString(R.string.yourNickNname) + " ・" + strDate);
            values4.put(KEY_ENTRY,getResources().getString(R.string.entry4));
            values4.put(KEY_LOCATION, getResources().getString(R.string.your_location) );


            // mWritableDB.insert(DIARY_LIST_TABLE, null, values);


            mWritableDB2.insert(FAVORITES_LIST_TABLE, null, values5);
            getContentResolver().insert(CONTENT_URI, values4);
            getContentResolver().insert(CONTENT_URI, values3);
            getContentResolver().insert(CONTENT_URI, values2);
            getContentResolver().insert(CONTENT_URI, values1);*/


        // ************ welcome page gibi bir activity koy.. ilk sefer uygulama yüklendiğinde açılıyor sadece
        // ilk kullanıcı ismini fln girsin. sonra değiştirilebilir fln de


// Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());  //animeting fln herhalde.
// Create an adapter and supply the data to be displayed.
        ///ç  mAdapter = new EntryListAdapter(this, mEntryList);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    fab.hide();
                else if (dy < 0)
                    fab.show();
            }
        });




        mAdapter = new EntryListAdapter(this);
// Connect the adapter with the RecyclerView.
        /////////////// mRecyclerView.setAdapter(mAdapter);
// Give the RecyclerView a default layout manager.
        //  mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(mLayoutManager);

        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mRecyclerView.setAdapter(mAdapter);





//////////////////////////////////////////////////////////////////////////////


       /* nativeAd.setAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
               // mdb.
               // recipeListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });

        nativeAd.loadAd();
*/






        //   recyclerView = (RecyclerView)findViewById(R.id.recyclerviewPhotoAPI);


        ///////// photos = new ArrayList<>();
        // PhotoRecyclerAdapter adapter = new PhotoRecyclerAdapter(photos, MainActivity.this);
        ////////////adapter = new PhotoRecyclerAdapter(photos, getApplicationContext());

        ///// unsplash = new Unsplash(CLIENT_ID);
        ///// recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //randomize();


        //   recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
        //    recyclerView.setAdapter(adapter);

        ///////////////randomize();



        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navDrawerMenu:
                                dl.openDrawer(Gravity.LEFT);
                                break;


                            case R.id.moodChart:
                                Intent i = new Intent(MainActivity.this, MoodChartActivity.class);
                                startActivity(i);

                                break;


                            case R.id.new_picture:
                                produceRandomPhoto();
                                break;



                        }
                        return true;
                    }
                });







        produceRandomPhoto();

        // implementSwipeDismiss();
        //Picasso.with(getApplicationContext()).load(http://unsplash.it/200/?random=200).fit().into(imageViewAPI);


        ///çç   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                // text.setVisibility(visible ? View.VISIBLE : View.GONE);

                final Intent intent = new Intent(view.getContext(), NewEntry.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                // mEntryList.add("emilyo santos ölmemeliydi");

                dl= findViewById(R.id.drawer_layout);
                int x = dl.getRight();
                int y = dl.getBottom();

                int startRadius = 0;
                int endRadius = (int) Math.hypot(dl.getWidth(), dl.getHeight());

                Animator anim = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    anim = ViewAnimationUtils.createCircularReveal(dl, x, y, startRadius, endRadius);
                }

                fab.setVisibility(View.VISIBLE);

                anim.setDuration(500);

                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Handler handler4 = new Handler();
                        handler4.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(intent);

                            }
                        }, 400);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                anim.start();






/*
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fab_grow);
                pageChangeImage.startAnimation(animation);
                pageChangeImage.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageChangeImage.setVisibility(View.GONE);
                        startActivity(intent);

                    }
                }, 1000);*/




                //   int entryListSize = mEntryList.size();
                //  mRecyclerView.getAdapter().notifyItemInserted(entryListSize);
// Scroll to the bottom.
                //   mRecyclerView.smoothScrollToPosition(entryListSize);

                // o activityden intentle aldım say.. zaten ileride daha değişecek
            }
        });


        // ATTENTION: This was auto-generated to handle app links.
        //////////////////////////  Intent appLinkIntent = getIntent();
        /////////////////////////  String appLinkAction = appLinkIntent.getAction();
        ////////////////////////  Uri appLinkData = appLinkIntent.getData();







      /*  mInterstitialAdEntryImage.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                mInterstitialAdEntryImage.loadAd(new AdRequest.Builder().build());

            }
        });
*/






/*
        mInterstitialAdEntryImage = new InterstitialAd(this);
        mInterstitialAdEntryImage.setAdUnitId(getString(R.string.ad_onlyentryinterstatialimage));
        mInterstitialAdEntryImage.loadAd(new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build());

        if (mInterstitialAdEntryImage.isLoaded() ) {
            mInterstitialAdEntryImage.show();}
        */



try {
    String myJson = inputStreamToString(MainActivity.this.getResources().openRawResource(R.raw.myquotes));
    ModelQuotes myModel = new Gson().fromJson(myJson, ModelQuotes.class);

    quotes = (TextView) header.findViewById(R.id.QuotesTV);
    author = (TextView) header.findViewById(R.id.AuthorTV);
    Random r = new Random();
    int randomNumQuotes = r.nextInt((myModel.list.size() - 2) + 1) + 1;

    // Toast.makeText(this, "" + myModel.list.get(randomNumQuotes).text, Toast.LENGTH_SHORT).show();
    // Toast.makeText(this, "" + myModel.list.get(randomNumQuotes).from, Toast.LENGTH_SHORT).show();

    quotes.setText(myModel.list.get(randomNumQuotes).text);
    author.setText(myModel.list.get(randomNumQuotes).from);
}
catch (Exception ex){ex.printStackTrace();}




    }

    //        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
    //  mRewardedVideoAd.loadAd(getString(R.string.ad_unitvideo_id),


    ///////EĞER bu reward kullanılacaksa addNetworkExtrasBundle(AdMobAdapter.class, extras).  eklenecek yani şu şekilde olacak
    ///   mRewardedVideoAd.loadAd(getString(R.string.ad_unitvideo_id),
    ///       new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build());



   /* private void loadRewardedVideoAd() {
       ///////////////*****************
        *//* mRewardedVideoAd.loadAd(getString(R.string.ad_unitvideo_id),
                new AdRequest.Builder().build());*//*


        mRewardedVideoAd.loadAd(getString(R.string.ad_rewardedvideo),
                new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build());
    // *******bunları aç üsttteki ni kapa
        /////////////////////////   mRewardedVideoAd.loadAd(getString(R.string.ad_unitvideo_id),
         /////////////////       new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build());

        // Toast.makeText(getBaseContext(), "load oldu .", Toast.LENGTH_SHORT).show();

        /////çççç///////////////uyg yayınlamadan  ca-app-pub-2730344422290526/1676581359   ile değiştir video çin
        // bannerler için de xml lerde ca-app-pub-2730344422290526/4885604170 ile değiştir
    }
*/


/////////////////////************
    /*@Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {
      //  preferencesEditor.putInt(ADSSHOWING,1 ).apply();
        Toast toast=   Toast.makeText(getBaseContext(),
                getString(R.string.plswatchvideo), Toast.LENGTH_LONG);
        View viewToast = toast.getView();
        viewToast.getBackground().setColorFilter(getBaseContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        toast.show();
    }

    @Override
    public void onRewardedVideoStarted() {
     ///////////////   preferencesEditor.putInt(ADSSHOWING,1 ).apply();



    }

    @Override
    public void onRewardedVideoAdClosed() {
       // Toast.makeText(this, "pls watch the short video for saving image:)", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
    }



    public void onRewardedVideoCompleted() {
       // Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
     //   Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).into(target);

    }


    public void onRewarded(RewardItem rewardItem) {
        // Reward the user for watching the ad.
       // Toast.makeText(getBaseContext(), "Ad triggered reward.", Toast.LENGTH_SHORT).show();
        ///artık ödül fln count la fln bişeyler ekleyebilirz kafaya göre


        Toast toast=   Toast.makeText(getBaseContext(),
                getString(R.string.downloadWillStart) , Toast.LENGTH_LONG);
        View viewToast = toast.getView();
        viewToast.getBackground().setColorFilter(getBaseContext().getResources().getColor(R.color.newEntryText), PorterDuff.Mode.SRC_IN);
        toast.show();
        try {
            Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).into(target);
        }
        catch (Exception ex){ex.printStackTrace();}
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, getString(R.string.plswatchvideo), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }*/




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //  menu.getItem(3).setIcon(R.drawable.coffee);

        //for adding icons to the menu items


     /*  MenuItem item = menu.findItem(R.id.favorites);
        SpannableStringBuilder builder = new SpannableStringBuilder("Favorites");
        // replace "*" with icon
        builder.setSpan(new ImageSpan(this, R.drawable.sun), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        item.setTitle(builder);*/
        // /*

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if(t.onOptionsItemSelected(item))
            return true;


        switch (item.getItemId()){


            case R.id.action_settings :
                goSettings();

                return true;



            case R.id.quotes :
                produceRandomPhoto();

                return true;








            case R.id.search :
                Intent intentSearch = new Intent(this, SearchActivity.class);
                startActivity(intentSearch);
                return true;
            case R.id.favorites :
                Intent intentFavorites = new Intent(this, FavoritesActivity.class);
                startActivity(intentFavorites);
                return true;



            case R.id.dayNight :

                int nightModeFlags =
                        getResources().getConfiguration().uiMode &
                                Configuration.UI_MODE_NIGHT_MASK;

                switch (nightModeFlags) {
                    case Configuration.UI_MODE_NIGHT_YES:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                        break;

                    case Configuration.UI_MODE_NIGHT_NO:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                        break;

                }

                recreate();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }



    private void showRewardedVideo(){
     /*   if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }*/
    }













    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.idshare :
                //implicit intent
                shareImplicitIntent();
                return true;
            //çççcase R.id.remindMyself :
            //setDate();
            //setTime();

            //çççç   showDatePickerDialog();
            //çççç   showTimePickerDialog();
            //setAlarm();
            //kendime reminder gönder tarih seçip bir şekilde
            ////ççç    return true;

            case R.id.delete :
                String selectionArgs[] = null;
                EntryListAdapter ela= new EntryListAdapter(this);


                selectionArgs = new String[]{Integer.toString(EntryListAdapter.tobeDeletedId)};
                int deleted = getContentResolver().delete(CONTENT_URI, CONTENT_PATH,
                        selectionArgs);
                if (deleted > 0) {
                    // Need both calls
                    MainActivity.mAdapter.notifyDataSetChanged();
                    //MainActivity.mAdapter.notifyItemRemoved(EntryListAdapter.choosenPos);  //bu ikisni kullanınca sorun çıkabiliyor. direk notifydatasetchanged() daha iyi ynai;)
                    // MainActivity.mAdapter.notifyItemRangeChanged(EntryListAdapter.choosenPos, ela.getItemCount());
                    /*Toast toast=   Toast.makeText(this, "entry deleted", Toast.LENGTH_SHORT);
                    View viewToast = toast.getView();
                    viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                    toast.show();*/


                } else {
                    Toast toast=   Toast.makeText(this, R.string.entry_delete, Toast.LENGTH_SHORT);
                    View viewToast = toast.getView();
                    viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                    toast.show();
                }


                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void shareImplicitIntent() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);



        //zaten istediğimiz pozisyonu direk seçemedik de (burda örnek için 3 verdik)
        //onun dışın da her işlemi db den yapmak büyük yanlış olur çok yavaş olduğu için elimizin altında
        //hazır popüle edilmiş recyclerview varken..!!!!
        /*Cursor cursor=WordListOpenHelper.mReadableDB.rawQuery("SELECT * FROM diary", null);
        cursor.moveToPosition(EntryListAdapter.pos);
        Entry_Model entry_model= new Entry_Model();
        entry_model.setmEntry(cursor.getString(cursor.getColumnIndex(WordListOpenHelper.KEY_ENTRY)));*/
        //String title = entry_model.getmEntry();
        String title = EntryListAdapter.entryToNotification;
        //String title = EntryListAdapter.clickedEntry;
        shareIntent.putExtra(Intent.EXTRA_TEXT, title);
        shareIntent.setType("text/plain");
        Intent chooser = Intent.createChooser(shareIntent, title);
// Resolve the intent before starting the activity
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }





   /* public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.time_picker));

    }*/



    ////çç
   /* public void processTimePickerResult(int hourOfDay, int minute) {
// Convert time elements into strings.
        hourr= hourOfDay;
        minutee= minute;

        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        if(minute<10){

            minute_string="0"+minute;
            timeMessage = (hour_string + ":" + minute_string);
            // Toast.makeText(this, getString(R.string.time) + " " +timeMessage,
            //        Toast.LENGTH_SHORT).show();
            //
        }
// Assign the concatenated strings to timeMessage.
        else {
            timeMessage = (hour_string + ":" + minute_string);
            //   Toast.makeText(this, "Reminder is set to "   +getString(R.string.time) + " " + timeMessage,
            //           Toast.LENGTH_SHORT).show();
        }
    }

*/



///çç
    /*public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }*/





    //////çç
   /*
    public void processDatePickerResult(int year, int month, int day) {
        yearr= year;
        monthh=month;
        dayy=day;


        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (
                day_string + "/" + month_string +  "/" + year_string);



        mNotifyManager =  (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Constants.alarmFromWho="fromDiaryReminder";
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, Constants.NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        //?????

        Calendar cal = Calendar.getInstance();
        //cal.setTimeInMillis(System.currentTimeMillis());
        //cal.clear();
        //cal.set(yearr,monthh-1,dayy,hourr,minutee);


        cal.set(Calendar.HOUR_OF_DAY, hourr);
        cal.set(Calendar.MINUTE, minutee+1);
       // cal.set(Calendar.DAY_OF_MONTH, dayy);
        //cal.set(Calendar.YEAR, yearr);
       // cal.set(Calendar.MONTH, monthh-1);
        cal.set(Calendar.SECOND, 0);




        long triggerTime = SystemClock.elapsedRealtime();
        long repeatInterval = 10*1000;

        //If the Toggle is turned on, set the repeating alarm with a 15 minute interval
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), notifyPendingIntent);
        //alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
          //      cal.getTimeInMillis(), repeatInterval, notifyPendingIntent);

      ///  Toast.makeText(this, EntryListAdapter.entryToNotification, Toast.LENGTH_LONG).show();
        Constants.NOTIFICATION_ID++;
        // yeni alarmın eskisini override etmesini önlemek için falan filan.

        //Set the toast message for the "on" case
       // String toastMessage = getString(R.string.alarm_on_toast);
       // Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
      Toast.makeText(this, "Reminder is set to "  + dateMessage + "\n" + "                         " + timeMessage,  Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, "year "  + yearr + "  minute " + minutee  , Toast.LENGTH_SHORT).show();

    }
*/








    private boolean isFirstTime()
    {


        //SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = mPreferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            //Toast.makeText(this, "asdsadsad", Toast.LENGTH_SHORT).show();
            //SharedPreferences.Editor editor = preferences.edit();
            preferencesEditor.putBoolean("RanBefore", true);
            preferencesEditor.commit();
        }
        return !ranBefore;
    }



    // for notification button .. ileride settings içinde fln a göre ayarlanacak


    public void onStart() {


        super.onStart();
        //pageChangeImage.setVisibility(View.GONE);

    }

    public void onRestart() {


        super.onRestart();
        //pageChangeImage.setVisibility(View.GONE);

    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onResume() {
        super.onResume();
        // pageChangeImage.setVisibility(View.GONE);




        ///////////////////// produceRandomPhoto();

        mAdapter.notifyDataSetChanged();
        mRecyclerView.getLayoutManager().smoothScrollToPosition(mRecyclerView,new RecyclerView.State(), mRecyclerView.getAdapter().getItemCount());



        /////////////****
/*
        if (mRewardedVideoAd != null)
            mRewardedVideoAd.resume(this);*/


        try{
            // SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



            int [][] states = new int [][]{
                    new int[] { android.R.attr.state_enabled, -android.R.attr.state_checked},
                    new int[] {android.R.attr.state_enabled, android.R.attr.state_checked}
            };



            int[] colors = new int[] {
                    color13,
                    Color.RED,
            };

            ColorStateList myList1 = new ColorStateList(states, colors);




            int[] colors2 = new int[] {
                    ContextCompat.getColor(this,R.color.colornavview),
                    Color.WHITE,
            };

            ColorStateList myList2 = new ColorStateList(states, colors2);




            int[] colors3 = new int[] {
                    ContextCompat.getColor(this,R.color.color13),
                    Color.WHITE,
            };

            ColorStateList myList3 = new ColorStateList(states, colors3);




            //////////////ActionBar actionBar = getSupportActionBar();

            if (mPreferences.contains(COLOR)) {
                String colorstate = mPreferences.getString(COLOR, "ffffff");


                if (colorstate.equals("#33b5e5")) {
                    ////////////////         actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient1));
                    bottomNavigationView.setItemBackgroundResource((R.color.color1));
                    bottomNavigationView.setItemTextColor(myList2);
                    bottomNavigationView.setItemIconTintList(myList2);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color1));
                        bottomNavigationView.setItemTextColor(myList2);
                        bottomNavigationView.setItemIconTintList(myList2);
                    }

                } else if (colorstate.equals("#aa66cc")) {
                    ////////////////////              actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient2));
                    //  fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fab2));
                    bottomNavigationView.setItemBackgroundResource((R.color.color2));
                    bottomNavigationView.setItemTextColor(myList2);
                    bottomNavigationView.setItemIconTintList(myList2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color2));
                        bottomNavigationView.setItemTextColor(myList2);
                        bottomNavigationView.setItemIconTintList(myList2);
                    }


                } else if (colorstate.equals("#99cc00")) {
                    //////////////            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient3));
                    bottomNavigationView.setItemBackgroundResource((R.color.color3));
                    bottomNavigationView.setItemTextColor(myList2);
                    bottomNavigationView.setItemIconTintList(myList2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color3));
                        bottomNavigationView.setItemTextColor(myList2);
                        bottomNavigationView.setItemIconTintList(myList2);
                    }
                }



                else if (colorstate.equals("#ffbb33")) {
                    //////////////////             actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient4));
                    bottomNavigationView.setItemBackgroundResource((R.color.color4));
                    bottomNavigationView.setItemTextColor(myList3);
                    bottomNavigationView.setItemIconTintList(myList3);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color4));
                        bottomNavigationView.setItemTextColor(myList3);
                        bottomNavigationView.setItemIconTintList(myList3);
                    }


                }



                else if (colorstate.equals("#ff4444")) {
                    ////////////////            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient5));
                    bottomNavigationView.setItemBackgroundResource((R.color.color5));
                    bottomNavigationView.setItemTextColor(myList2);
                    bottomNavigationView.setItemIconTintList(myList2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color5));
                        bottomNavigationView.setItemTextColor(myList2);
                        bottomNavigationView.setItemIconTintList(myList2);
                    }
                }


                else if (colorstate.equals("#0099cc")) {
                    //////////////////              actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient6));
                    bottomNavigationView.setItemBackgroundResource((R.color.color6));
                    bottomNavigationView.setItemTextColor(myList2);
                    bottomNavigationView.setItemIconTintList(myList2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color6));
                        bottomNavigationView.setItemTextColor(myList2);
                        bottomNavigationView.setItemIconTintList(myList2);
                    }
                }


                else if (colorstate.equals("#9933cc")) {
                    //////////////////              actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient7));
                    bottomNavigationView.setItemBackgroundResource((R.color.color7));
                    bottomNavigationView.setItemTextColor(myList2);
                    bottomNavigationView.setItemIconTintList(myList2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color7));
                        bottomNavigationView.setItemTextColor(myList2);
                        bottomNavigationView.setItemIconTintList(myList2);
                    }
                }


                else if (colorstate.equals("#669900")) {
                    //////////////////           actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient8));
                    bottomNavigationView.setItemBackgroundResource((R.color.color8));
                    bottomNavigationView.setItemTextColor(myList2);
                    bottomNavigationView.setItemIconTintList(myList2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color8));
                        bottomNavigationView.setItemTextColor(myList2);
                        bottomNavigationView.setItemIconTintList(myList2);
                    }
                }


                else if (colorstate.equals("#ff8800")) {
                    //////////////////          actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient9));
                    bottomNavigationView.setItemBackgroundResource((R.color.color9));
                    bottomNavigationView.setItemTextColor(myList2);
                    bottomNavigationView.setItemIconTintList(myList2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color9));
                        bottomNavigationView.setItemTextColor(myList2);
                        bottomNavigationView.setItemIconTintList(myList2);
                    }
                }


                else if (colorstate.equals("#cc0000")) {
                    //////////////////            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient10));
                    bottomNavigationView.setItemBackgroundResource((R.color.color10));
                    bottomNavigationView.setItemTextColor(myList2);
                    bottomNavigationView.setItemIconTintList(myList2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color10));
                        bottomNavigationView.setItemTextColor(myList2);
                        bottomNavigationView.setItemIconTintList(myList2);
                    }
                }


                else if (colorstate.equals("#ffffff")) {
                    //////////////////            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient11));
                    bottomNavigationView.setItemBackgroundResource((R.color.color11));
                    bottomNavigationView.setItemTextColor(myList1);
                    bottomNavigationView.setItemIconTintList(myList1);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                        bottomNavigationView.setItemTextColor(myList1);
                        bottomNavigationView.setItemIconTintList(myList1);
                    }

                }


                else if (colorstate.equals("#eeeeee")) {
                    //////////////////            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient12));
                    bottomNavigationView.setItemBackgroundResource((R.color.color12));
                    bottomNavigationView.setItemTextColor(myList1);
                    bottomNavigationView.setItemIconTintList(myList1);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                        bottomNavigationView.setItemTextColor(myList1);
                        bottomNavigationView.setItemIconTintList(myList1);
                    }
                }


                else if (colorstate.equals("#cccccc")) {
                    //////////////////            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient13));
                    bottomNavigationView.setItemBackgroundResource((R.color.color13));
                    bottomNavigationView.setItemTextColor(myList2);
                    bottomNavigationView.setItemIconTintList(myList2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color13));
                        bottomNavigationView.setItemTextColor(myList2);
                        bottomNavigationView.setItemIconTintList(myList2);
                    }
                }


                else if (colorstate.equals("#888888")) {
                    //////////////////            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient14));
                    bottomNavigationView.setItemBackgroundResource((R.color.color14));
                    bottomNavigationView.setItemTextColor(myList2);
                    bottomNavigationView.setItemIconTintList(myList2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color14));
                        bottomNavigationView.setItemTextColor(myList2);
                        bottomNavigationView.setItemIconTintList(myList2);
                    }
                }


                else {
                    //////////////////             actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient));
                    bottomNavigationView.setItemBackgroundResource((R.color.color11));
                    bottomNavigationView.setItemTextColor(myList1);
                    bottomNavigationView.setItemIconTintList(myList1);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                        bottomNavigationView.setItemTextColor(myList1);
                        bottomNavigationView.setItemIconTintList(myList1);
                    }
                }

            } else {
                //////////////////        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient7));
                //  Toast.makeText(this, "333", Toast.LENGTH_SHORT).show();
                bottomNavigationView.setItemBackgroundResource((R.color.color11));
                bottomNavigationView.setItemTextColor(myList1);
                bottomNavigationView.setItemIconTintList(myList1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                    bottomNavigationView.setItemTextColor(myList1);
                    bottomNavigationView.setItemIconTintList(myList1);
                }


            }

        } catch (Exception ex) {ex.printStackTrace();}







        // prefs.getPreferenceScreen().getSharedPreferences()
        //       .registerOnSharedPreferenceChangeListener(this);

    }


    @Override
    protected void onPause() {
        super.onPause();

        //////////////////////////////////////////////ççççç
        /*mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(NICKNAME_KEY, Constants.nickName);
        preferencesEditor.apply();*/

        ////////////**
       /* if (mRewardedVideoAd != null)
            mRewardedVideoAd.pause(this);*/



        if (mPreferences.contains(ADSSHOWING)){
            if (mPreferences.getInt(ADSSHOWING, 0) == 1) {
                preferencesEditor.putInt(ADSSHOWING,0 ).apply();
                //   Toast.makeText(MainActivity.this, "reklamaçmıyoruz ", Toast.LENGTH_SHORT).show();
                return;
            }


        }



      /*Random r = new Random();
        randomNum= r.nextInt((5 - 1) + 1) + 1;*/
        // Toast.makeText(this, "random " + randomNum, Toast.LENGTH_SHORT).show();


        //////////////////////çç
        /*if (randomNum==3)
        {showRewardedVideo();  }*/


        Random r = new Random();

        if (mPreferences.contains(BOUGHT)) {
            Boolean boughtState= mPreferences.getBoolean(BOUGHT, false);
            if (boughtState == true) {

                randomNum= r.nextInt((6 - 1) + 1) + 1;
                if(randomNum==2){showAdWithDelay();}
             /*  if (mInterstitialAd.isLoaded() && randomNum==2) {
                    mInterstitialAd.show();
                  //   Toast.makeText(this, "alındı" + " random " + randomNum, Toast.LENGTH_SHORT).show();

                }*/


            }
            else{
                randomNum= r.nextInt((3 - 1) + 1) + 1;
                if(randomNum==2){showAdWithDelay();}

              /*  if (mInterstitialAd.isLoaded() && randomNum==2) {
                    mInterstitialAd.show();
                  //  Toast.makeText(this, "alınmadı" + " random " + randomNum, Toast.LENGTH_SHORT).show();

                }*/

            }

        }

        else{
            randomNum= r.nextInt((3 - 1) + 1) + 1;
            if(randomNum==2){showAdWithDelay();}
           /* if (mInterstitialAd.isLoaded() && randomNum==2) {
                mInterstitialAd.show();
              //  Toast.makeText(this, "alınmadı" + " random " + randomNum, Toast.LENGTH_SHORT).show();

            }*/
        }










    }

















    public void onDestroy() {
        super.onDestroy();

        if (interstitialAd != null) {
            interstitialAd.destroy();
        }

        if (bp != null) {
            bp.release();
        }

        // showRewardedVideo();
       /* if (randomNum==3)
        {showRewardedVideo();  }*/

        /*  if (mRewardedVideoAd != null)
        mRewardedVideoAd.destroy(this);*/

        preferencesEditor.putString(UNSPLASH_QUERY,"pink love").apply();



    }


    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String queryUri = CONTENT_URI.toString();
        String[] projection = new String[] {CONTENT_PATH};
        return new CursorLoader(this, Uri.parse(queryUri), projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.setData(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.setData(null);
    }




    //not working- not necessary6 now
 /*   @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("color_preference")) {
        //  recreate();
            //Toast.makeText(this, "recreated", Toast.LENGTH_SHORT).show();
        }


        if (key.equals(ADSSHOWING)) {
            // Set summary to be the user-description for the selected value
           Toast.makeText(this, "preference changed", Toast.LENGTH_SHORT).show();
        }




    }*/


///////////////////Çç

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }

        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == EnterPinActivity.RESULT_BACK_PRESSED) {
                    // Toast.makeText(MainActivity.this, "back pressed", Toast.LENGTH_LONG).show();
                    // Intent intent = new Intent (MainActivity.this, EnterPinActivity.class);
                    //  startActivity(intent);
                    finish();
                }
                break;

            case REQUEST_CODEFROMINTRO:
            { pageChangeImage = (ImageView) findViewById(R.id.imageView10);
                //  pageChangeImage.setVisibility(View.GONE);
                introfabIV=findViewById(R.id.introfabIV);


                introfabIV.setVisibility(View.VISIBLE);
                pageChangeImage.setVisibility(View.VISIBLE);

                TranslateAnimation animation = new TranslateAnimation(800.0f, 0.0f, 0.0f, 0.0f);
                ///////// final TranslateAnimation animation3 = new TranslateAnimation(-1300.0f, 0.0f, 0.0f, 0.0f);
                final TranslateAnimation animation3 = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);



                animation.setDuration(4000);
                animation.setRepeatCount(1);
                animation.setRepeatMode(2);

                pageChangeImage.startAnimation(animation);


                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        pageChangeImage.setVisibility(View.INVISIBLE);


                        TranslateAnimation animation2 = new TranslateAnimation(0f, 35f, 0.0f, 0.0f);
                        animation2.setDuration(100);
                        animation2.setRepeatMode(2);
                        animation2.setRepeatCount(18);
                        fab.startAnimation(animation2);



                        animation3.setDuration(4500);
                        animation3.setRepeatCount(1);
                        animation3.setRepeatMode(2);
                        introfabIV.startAnimation(animation3);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }


                });

                animation3.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        introfabIV.setVisibility(GONE);
                        pageChangeImage.setVisibility(GONE);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });





                introfabIV.setVisibility(View.INVISIBLE);
            }

            break;




        }
    }









 /*   public void randomize(){
        //EditText editText = findViewById(R.id.editText);
        // String query = editText.getText().toString();



        unsplash.searchPhotos("quotes", new Unsplash.OnSearchCompleteListener() {
            @Override
            public void onComplete(SearchResults results) {
 try {

     photos.clear();
     Log.d("Photos", "Total Results Found " + results.getTotal());
     List<Photo> photos = results.getResults();
    //PhotoRecyclerAdapter adapter = new PhotoRecyclerAdapter(photos, getApplicationContext());

//                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                recyclerView.setLayoutManager(mLayoutManager);


   //recyclerView.setAdapter(adapter);
//imageViewAPI.setImageResource(photos.get(4));



     Random r = new Random();
     int randomNum= r.nextInt((photos.size()-2 )) ;

     Photo photo = photos.get(randomNum);




     Picasso.with(getApplicationContext()).load(photo.getUrls().getRegular()).fit().into(imageViewAPI);


 }
 catch (Exception e) {
     Log.e("tag", "Json parsing error: " + e.getMessage());
 }

                adapter.notifyDataSetChanged();


            }


            @Override
            public void onError(String error) {
                Log.d("Unsplash", error);
            }
        });
    }*/











    public void produceRandomPhoto() {
        //imageViewAPI = findViewById(R.id.imageView2);
        //   linearLayoutUP= findViewById(R.id.linearLayoutUP);
        //  linearLayoutDOWN= findViewById(R.id.linearLayoutDOWN);
        /////  Toast.makeText(MainActivity.this, "pppppppp", Toast.LENGTH_SHORT).show();


        try{

            ////////////////////////////   Intent i = new Intent(this, MoodChartActivity.class);
            ///////////////////   startActivity(i);




            unsplash.getRandomPhoto(null,
                    null, null,
                    mPreferences.getString(UNSPLASH_QUERY, "pink love"), null,
                    null, null, new Unsplash.OnPhotoLoadedListener() {
                        @Override
                        public void onComplete(Photo photo) {

                            photo1 = photo;
                            //String photo2= photo.getUrls().getRegular();
                            if(photo1!=null&& imageViewAPI!=null) {


                                Boolean boughtState= mPreferences.getBoolean(BOUGHT, false);
                                //for no ads layout
                                //User usr= new User();

                                //  Toast.makeText(MainActivity.this, "urlllll " + photo1.getLinks().getHtml(), Toast.LENGTH_SHORT).show();
                                // Log.d(f"Link f"," " + photo1.getLinks().getHtml());
                                //photographerTextView.setText(photo1.getLinks().getHtml());





                         /*   if(isFirstTime()==true ){
                                try {


                                    imageViewAPI = findViewById(R.id.imageViewNoADS);
                                    cardViewNoads = findViewById(R.id.cardViewNoAds);
                                    photographerTextViewNOADS = findViewById(R.id.photographerTextViewNOADS);
                                    unsplashTextViewNOADS = findViewById(R.id.unsplashTextViewNOADS);
                                    photoinfosNOADS = findViewById(R.id.photoinfosNOADS);

                                    photographerTextViewNOADS.setText(photo1.getUser().getName() + " / ");
                                    photographerTextViewNOADS.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.unsplash.com"+"/@"+photo1.getUser().getUsername()));
                                            startActivity(in);
                                        }
                                    });

                                    unsplashTextViewNOADS.setText("Unsplash");
                                    unsplashTextViewNOADS.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent in2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.unsplash.com"));
                                            startActivity(in2);
                                        }
                                    });


                                    Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).into(imageViewAPI, new Callback() {
                                        @Override
                                        public void onSuccess() {
                                            cardViewNoads.setVisibility(View.VISIBLE);      //placeholder(imageViewAPI.getDrawable()) de olabilir altta
                                            photoinfosNOADS.setVisibility(View.VISIBLE);
                                            Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).fit().into(imageViewAPI);
                                        }

                                        @Override
                                        public void onError() {

                                        }
                                    });

                                }catch(Exception ex) {ex.printStackTrace();}

                            }
*/





                                if(boughtState==true){

                                    try {


                                        imageViewAPI = findViewById(R.id.imageViewNoADS);
                                        cardViewNoads = findViewById(R.id.cardViewNoAds);
                                        photographerTextViewNOADS = findViewById(R.id.photographerTextViewNOADS);
                                        unsplashTextViewNOADS = findViewById(R.id.unsplashTextViewNOADS);
                                        photoinfosNOADS = findViewById(R.id.photoinfosNOADS);

                                        photographerTextViewNOADS.setText(photo1.getUser().getName() + " / ");
                                        photographerTextViewNOADS.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.unsplash.com"+"/@"+photo1.getUser().getUsername()));
                                                startActivity(in);
                                            }
                                        });

                                        unsplashTextViewNOADS.setText("Unsplash");
                                        unsplashTextViewNOADS.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent in2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.unsplash.com"));
                                                startActivity(in2);
                                            }
                                        });


                                        Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).into(imageViewAPI, new Callback() {
                                            @Override
                                            public void onSuccess() {
                                                cardViewNoads.setVisibility(View.VISIBLE);      //placeholder(imageViewAPI.getDrawable()) de olabilir altta
                                                photoinfosNOADS.setVisibility(View.VISIBLE);
                                                floatingActionButtonDownloadImage.setVisibility(View.VISIBLE);
                                                Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).fit().into(imageViewAPI);
                                            }

                                            @Override
                                            public void onError() {

                                            }
                                        });









                      /*     //////**sil
                           bytearrayoutputstream = new ByteArrayOutputStream();
                           Bitmap bitmap=((BitmapDrawable)imageViewAPI.getDrawable()).getBitmap();

                           Bitmap image;
                           URL url = new URL("photo1.getUrls()");
                           image = BitmapFactory.decodeStream(url.openConnection().getInputStream());


                           image.compress(Bitmap.CompressFormat.PNG, 60, bytearrayoutputstream);
                           Toast.makeText(MainActivity.this, "bbbbbbbbbbbbbbbbbbb", Toast.LENGTH_LONG).show();

                           file = new File( Environment.getExternalStorageDirectory() + "/SampleImage.png");

                           try

                           {
                               file.createNewFile();

                               fileoutputstream = new FileOutputStream(file);

                               fileoutputstream.write(bytearrayoutputstream.toByteArray());

                               fileoutputstream.close();

                           }

                           catch (Exception e)

                           {

                               e.printStackTrace();

                           }

                           Toast.makeText(MainActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();



*/











                                    }catch(Exception ex) {ex.printStackTrace();}

                                    // Toast.makeText(MainActivity.this, "mmmmmmmmmmmmm", Toast.LENGTH_SHORT).show();
                                }










                                //for ads layout
                                else {

                                    try{
                                        imageViewAPI = findViewById(R.id.imageViewADS);
                                        cardViewADS = findViewById(R.id.cardViewADS);
                                        photographerTextView = findViewById(R.id.photographerTextView);
                                        unsplashTextView = findViewById(R.id.unsplashTextView);


                                        photographerTextView.setText(photo1.getUser().getName() + " / ");
                                        photographerTextView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.unsplash.com" + "/@" + photo1.getUser().getUsername()));
                                                startActivity(in);
                                            }
                                        });

                                        unsplashTextView.setText("Unsplash");
                                        unsplashTextView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent in2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.unsplash.com"));
                                                startActivity(in2);
                                            }
                                        });

                                        Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).into(imageViewAPI, new Callback() {
                                            @Override
                                            public void onSuccess() {
                                                cardViewADS.setVisibility(View.VISIBLE);      //placeholder(imageViewAPI.getDrawable()) de olabilir altta
                                                photoinfos.setVisibility(View.VISIBLE);
                                                floatingActionButtonDownloadImage.setVisibility(View.VISIBLE);
                                                Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).fit().into(imageViewAPI);
                                                //unsplash.getPhotoDownloadLink();
                                            }


                                            @Override
                                            public void onError() {

                                            }
                                        });




                                        ///////////////////    Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).into(target);























                                    }
                                    catch(Exception ex){ex.printStackTrace();}


                                }



                            }
                            else {
                                //  Toast.makeText(MainActivity.this, "sdfdsfdsfdsfsd", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(String error) {

                        }
                    });

        /*    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                File sd = Environment.getExternalStorageDirectory();
                File data = Environment.getDataDirectory();

                if (sd.canWrite()) {
                    Toast.makeText(MainActivity.this, "aaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
                    Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).into(target);

                }


            }




            else{
                Toast.makeText(MainActivity.this, "bbbbbbbbbbbbbbbbb", Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODESavetoGallery);}*/



        }
        catch (Exception e) {
            Log.e("tag", "Json parsing error: " + e.getMessage());
        }



















        ///////////7silllllllllllll
/*
         Target target = new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        File file = new File(
                                Environment.getExternalStorageDirectory().getPath()
                                        + "/saved.jpg");
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG,100,ostream);
                            ostream.close();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {}

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {}
        };


        Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).fit().into(target);*/





    }











    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.exit1))
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton(getString(R.string.no), null)
                .show();


    }




    public void saveImagetoGallery(){
        //Toast.makeText(this, "eeeeee", Toast.LENGTH_SHORT).show();

        try {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                preferencesEditor.putInt(ADSSHOWING,1 ).apply();
                ///////////////////////// showRewardedVideo();
                try {
                    Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).into(target);

                    Toast toast=   Toast.makeText(getBaseContext(),
                            getString(R.string.downloadWillStart), Toast.LENGTH_LONG);





                    View viewToast = toast.getView();
                    viewToast.getBackground().setColorFilter(getBaseContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
                    toast.show();
                }
                catch (Exception ex){ex.printStackTrace();}

            }

            else{

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODESavetoGallery);

            }
        }

        catch (Exception ex ){ex.printStackTrace();}

    }





    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        //  Toast.makeText(this, "yyyy", Toast.LENGTH_SHORT).show();

        File sd = Environment.getExternalStorageDirectory();
        // File data = Environment.getDataDirectory();

        switch (requestCode) {

            case REQUEST_CODESavetoGallery:
                try {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (sd.canWrite()) {
                            Picasso.with(MainActivity.this).load(photo1.getUrls().getRegular()).into(target);
                            showRewardedVideo();

                        }
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, getString(R.string.permission_denied), Toast.LENGTH_LONG);
                        View viewToast = toast.getView();
                        viewToast.getBackground().setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                        toast.show();
                    }
                    break;
                }
                catch (Exception ex){ex.printStackTrace();}

        }






        /*try{
            switch (requestCode){
                case REQUEST_CODESavetoGallery:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
                        File myDir = new File(root + "/pinkdiaryimage");
                        myDir.mkdirs();
                        Random generator = new Random();
                        //int n = 10000;
                        // n = generator.nextInt(n);
                        String fname = System.currentTimeMillis() + ".jpg";
                        File file = new File(myDir, fname);
                        if (file.exists())
                            file.delete();
                        try {
                            FileOutputStream out = new FileOutputStream(file);
                            bitmap2.compress(Bitmap.CompressFormat.JPEG, 90, out);
                            out.flush();
                            out.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap2, "", "");


                    }

                    else{
                        Toast toast = Toast.makeText(MainActivity.this, getString(R.string.permission_denied), Toast.LENGTH_LONG);
                        View viewToast = toast.getView();
                        viewToast.getBackground().setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                        toast.show();
                    }
                    break;



            }


        }
        catch(Exception ex){ex.printStackTrace();}*/



    }

















    private Target target = new Target() {
        //Bitmap bitmap2;
        @Override
        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ////////bitmap2=bitmap;
                    try {
                        if(true)
                        //  (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                        {
                            //Toast.makeText(MainActivity.this, "aaaaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();

                            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
                            File myDir = new File(root + "/pinkdiaryimage");
                            myDir.mkdirs();
                            //Random generator = new Random();
                            //int n = 10000;
                            // n = generator.nextInt(n);
                            String fname = System.currentTimeMillis() + ".jpg";
                            File file = new File(myDir, fname);
                            if (file.exists())
                                file.delete();
                            try {
                                FileOutputStream out = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                                out.flush();
                                out.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "", "");


//////////////////*******
                            /*  File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() +"/pinkdiaryimage.jpg");
                             *//*
                    File f1 = new File(Environment.getExternalStorageDirectory(), "/PinkDiary/");
                    String fileName = "/pinkdiary.jpg";
                    File file = new File(f1, fileName);*//*

                    try
                    {
                        file.createNewFile();
                        FileOutputStream ostream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, ostream);
                        ostream.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }*/
                        }

       /*  else {
             Toast.makeText(MainActivity.this, "bbbbbbbbbbbbbbbbbbbbbb", Toast.LENGTH_SHORT).show();
             ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODESavetoGallery);
         }*/
                    }
                    catch(Exception ex){ex.printStackTrace();}


                }
            }).start();


        }




        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }
        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            if (placeHolderDrawable != null) {
            }
        }


    };


    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        Toast toast = Toast.makeText(MainActivity.this, getString(R.string.purchase_was_done), Toast.LENGTH_SHORT);
        View viewToast = toast.getView();
        viewToast.getBackground().setColorFilter(MainActivity.this.getResources().getColor(R.color.colorNotifColor), PorterDuff.Mode.SRC_IN);
        toast.show();
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }





    private void showAdWithDelay() {
        /**
         * Here is an example for displaying the ad with delay;
         * Please do not copy the Handler into your project
         */
        // Handler handler = new Handler();

        // Check if interstitialAd has been loaded successfully
        if(interstitialAd == null || !interstitialAd.isAdLoaded()) {
            return;
        }
        // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
        if(interstitialAd.isAdInvalidated()) {
            return;
        }
        // Show the ad
        interstitialAd.show();

    }






    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

}





























