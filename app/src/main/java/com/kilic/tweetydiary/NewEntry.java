package com.kilic.tweetydiary;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import rjsv.floatingmenu.animation.enumerators.AnimationType;
import rjsv.floatingmenu.floatingmenubutton.FloatingMenuButton;
import rjsv.floatingmenu.floatingmenubutton.MovementStyle;
import rjsv.floatingmenu.floatingmenubutton.subbutton.FloatingSubButton;

import static com.kilic.tweetydiary.Contract.CONTENT_URI;
import static com.kilic.tweetydiary.Contract.WordList.KEY_DREAM_MODE;
import static com.kilic.tweetydiary.Contract.WordList.KEY_ENTRY;
import static com.kilic.tweetydiary.Contract.WordList.KEY_HEART;
import static com.kilic.tweetydiary.Contract.WordList.KEY_LOCATION;
import static com.kilic.tweetydiary.Contract.WordList.KEY_LOCATION_IMAGE;
import static com.kilic.tweetydiary.Contract.WordList.KEY_MOOD_IMAGE;
import static com.kilic.tweetydiary.Contract.WordList.KEY_NICKNAME_DATE;
import static com.kilic.tweetydiary.Contract.WordList.KEY_PICTURE;
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



public class NewEntry extends AppCompatActivity implements LocationListener {


   // String youtubelink;
    ImageView myImage;
    Uri selectedImage;
  //  String filePath;
  private AdView adView;
    private static final int REQUEST_CODE = 1 ;
   // private static final int YOUTUBE_REQUEST_CODE = 2 ;
    private static final int  GALLERY_REQUEST=3;

    FloatingMenuButton fab_1;
   // FloatingMenuButton fab_2;
    FloatingSubButton floatingSubButton1;
    FloatingSubButton floatingSubButton4;
    FloatingSubButton floatingSubButton2;
    FloatingSubButton floatingSubButton5;
    FloatingSubButton floatingSubButton3;

ImageView diamondforfun;
    View viewLine;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
     ImageView imageViewMood;
ImageView dreamImage;
ConstraintLayout newEntrylayout;
Button saveButton;

    private TextView textViewwhatsInMind;
    private String COLOR = "color";
private String TEXTCOLORIS;

    EditText edittext;

    //EntryListAdapter mAdapter;

    static String locationfordb = "";
    LocationManager locationManager;
   // Button getLocationBtn;
   // Button saveBtn;

FloatingActionButton floatingActionNewEntry;
static int keybonoff;
boolean visible;

    ///////private AdView mAdViewNewEntry;
    private final String NICKNAME_KEY = "nickname";
    private final String LOCATIONIMAGE_SITUATION = "locationimage_situation";
    private final String MOODIMAGE_SITUATION = "moodimage_situation";
    private final String DREAMMODE_SITUATION = "dreammode_situation";

    // static final String mSharedPrefFile = "com.kilic.tweetydiary.hellosharedprefs";

    String nickkey;
    Boolean isNetworkEnabled;
    Boolean canGetLocation;
 Location location;
    SharedPreferences.Editor preferencesEditor;



    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        //fullscreentheme kullandığımzıa için buna gerek kalmadı
       /* if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
*/

        setContentView(R.layout.activity_new_entry);
        //to hide the status bar
     //////////   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //youtubelink="";

     /*   Bundle extras = getIntent().getExtras();
        //youtubelink="";
        if(extras!=null) {
             youtubelink = extras.getString(Intent.EXTRA_TEXT);

            Toast.makeText(this, "link " + youtubelink, Toast.LENGTH_SHORT).show();

            // ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            //ClipData clip = ClipData.newPlainText("link", link);
            //clipboard.setPrimaryClip(clip);
           //////Toast.makeText(this, "link " + youtubelink, Toast.LENGTH_SHORT).show();
        }*/


        ////ActionBar actionBar = getSupportActionBar();
       //// actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient));


        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "G");




        ////////////////////////////////////****
        adView = new AdView(this, getString(R.string.adfacebookbannerid1forNewEntry), AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
      //  AdSettings.addTestDevice("3b5b04bd-592c-468a-9e18-3d6b363651bc");
        adView.loadAd();




        final SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferencesEditor = mPreferences.edit();
        edittext = findViewById(R.id.editText);
      //    // //başka aktivitenin xml si olduğu için olmaz zaten diaryTextView = findViewById(R.id.diary_entry);

        preferencesEditor.putInt(MOODIMAGE_SITUATION,0);
        preferencesEditor.apply();


///////////////////////////BAK  DIKKAT!!!!!!!!!!!!!
    if (mPreferences.contains(TEXTCOLORIS)){
        try{
            edittext.setTextColor(mPreferences.getInt(TEXTCOLORIS,R.color.newEntryText));
           // //başka aktivitenin xml si olduğu için olmaz zaten diaryTextView.setTextColor(mPreferences.getInt(TEXTCOLORIS,R.color.newEntryText));

        }
        catch(Exception ex){ex.printStackTrace();}

    }


   else{edittext.setTextColor(getResources().getColor(R.color.colorEntry));}


     //   edittext.setTextColor(   Color.parseColor("#"+(Integer.toHexString(selectedColor)))   );


        newEntrylayout = findViewById(R.id.newEntrylayout);

        myImage= findViewById(R.id.myImage);
        viewLine=findViewById(R.id.viewLine);
        textViewwhatsInMind = findViewById(R.id.textViewwhatsInMind);
        saveButton=findViewById(R.id.saveButton);

        fab_1 = (FloatingMenuButton) findViewById(R.id.fab_1);

        fab_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    fab_1.closeMenu();
                    }
                }, 5000);
            }
        });


        floatingSubButton3= (FloatingSubButton) findViewById(R.id.fab_1_sub_fab_left_3); // create the button
        floatingSubButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase();

            }
        });





        floatingSubButton4= (FloatingSubButton) findViewById(R.id.fab_1_sub_fab_left_4); // create the button
        floatingSubButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(NewEntry.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

                }

                  else {
                    ActivityCompat.requestPermissions(NewEntry.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
                }






            }
        });






        floatingSubButton5= (FloatingSubButton) findViewById(R.id.fab_1_sub_fab_left_5); // create the button
        floatingSubButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///////// saveVideoURL();


                if (mPreferences.getInt(DREAMMODE_SITUATION, 0)==0){

                    newEntrylayout.setBackgroundResource(R.color.colorDreamMode);
                edittext.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.dreamcatcher), null, null);
             //   edittext.getText().insert(edittext.getSelectionStart(), "\n" +
             //           getString(R.string.dreamMode));

                    Toast toast=   Toast.makeText(getBaseContext(),
                            getString(R.string.dreamMode), Toast.LENGTH_LONG);
                    View viewToast = toast.getView();
                    viewToast.getBackground().setColorFilter(getBaseContext().getResources().getColor(R.color.color7), PorterDuff.Mode.SRC_IN);
                    toast.show();

                preferencesEditor.putInt(DREAMMODE_SITUATION, 1).apply();

            }


                      else{
                    edittext.setCompoundDrawablesWithIntrinsicBounds( null,null,null , null);
                    newEntrylayout.setBackgroundColor(Color.TRANSPARENT);
                    preferencesEditor.putInt(DREAMMODE_SITUATION, 0).apply();
                    Toast toast=   Toast.makeText(getBaseContext(),
                            getString(R.string.dreamModeOff), Toast.LENGTH_LONG);
                    View viewToast = toast.getView();
                    viewToast.getBackground().setColorFilter(getBaseContext().getResources().getColor(R.color.DreamModeOff), PorterDuff.Mode.SRC_IN);
                    toast.show();

                }






            }
        });



        floatingSubButton2= (FloatingSubButton) findViewById(R.id.fab_1_sub_fab_left_2); // create the button
        floatingSubButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar rightNow = Calendar.getInstance();
                int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
                int currentMin = rightNow.get(Calendar.MINUTE);

                edittext.getText().insert(edittext.getSelectionStart(), "\n" +"[" + String.valueOf(currentHour) + ":" + String.valueOf(currentMin) + "]   ~ ");


            }
        });






        floatingSubButton1= (FloatingSubButton) findViewById(R.id.fab_1_sub_fab_left_1);
        floatingSubButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(NewEntry.this);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.mood_dialog_layout,null);

                builder.setView(dialogView);

               // Button one = (Button) dialogView.findViewById(R.id.button1);
               // Button two = (Button) dialogView.findViewById(R.id.button2);
               // Button three = (Button) dialogView.findViewById(R.id.button3);
               imageView1= dialogView.findViewById(R.id.imageView1);
                imageView2= dialogView.findViewById(R.id.imageView2);
                imageView3= dialogView.findViewById(R.id.imageView3);
                imageView4= dialogView.findViewById(R.id.imageView4);
                imageView5= dialogView.findViewById(R.id.imageView5);


                final AlertDialog dialog = builder.create();


                imageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Animation animFadeOut = AnimationUtils.loadAnimation(NewEntry.this, R.anim.fadeout);

                        Animation animFadeIn = AnimationUtils.loadAnimation(NewEntry.this, R.anim.fadein);

                        animFadeOut.reset();
                        imageView1.clearAnimation();
                        imageView1.startAnimation(animFadeOut);
                        animFadeIn.reset();
                        imageView1.clearAnimation();
                        imageView1.startAnimation(animFadeIn);

                        imageView1.animate().alpha(0f).setDuration(1000);
                        imageView1.animate().alpha(1f).setDuration(500);

                        preferencesEditor.putInt(MOODIMAGE_SITUATION,1);
                        preferencesEditor.apply();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.cancel();
                            }
                        }, 1000);

                      //  switchButton.setIcon(R.drawable.one);
                        imageViewMood.setImageDrawable(getResources().getDrawable(R.drawable.one));
                    }
                });




                imageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Animation animFadeOut = AnimationUtils.loadAnimation(NewEntry.this, R.anim.fadeout);

                        Animation animFadeIn = AnimationUtils.loadAnimation(NewEntry.this, R.anim.fadein);

                        animFadeOut.reset();
                        imageView2.clearAnimation();
                        imageView2.startAnimation(animFadeOut);
                        animFadeIn.reset();
                        imageView2.clearAnimation();
                        imageView2.startAnimation(animFadeIn);

                        imageView2.animate().alpha(0f).setDuration(1000);
                        imageView2.animate().alpha(1f).setDuration(500);
                        preferencesEditor.putInt(MOODIMAGE_SITUATION,2);
                        preferencesEditor.apply();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.cancel();
                            }
                        }, 1000);

                   //     switchButton.setIcon(R.drawable.two);
                        imageViewMood.setImageDrawable(getResources().getDrawable(R.drawable.two));



                    }
                });


                imageView3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Animation animFadeOut = AnimationUtils.loadAnimation(NewEntry.this, R.anim.fadeout);

                        Animation animFadeIn = AnimationUtils.loadAnimation(NewEntry.this, R.anim.fadein);

                        animFadeOut.reset();
                        imageView3.clearAnimation();
                        imageView3.startAnimation(animFadeOut);
                        animFadeIn.reset();
                        imageView3.clearAnimation();
                        imageView3.startAnimation(animFadeIn);

                        imageView3.animate().alpha(0f).setDuration(1000);
                        imageView3.animate().alpha(1f).setDuration(500);
                        preferencesEditor.putInt(MOODIMAGE_SITUATION,3);
                        preferencesEditor.apply();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.cancel();
                            }
                        }, 1000);

                    //    switchButton.setIcon(R.drawable.three);
                        imageViewMood.setImageDrawable(getResources().getDrawable(R.drawable.three));


                    }
                });



                imageView4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Animation animFadeOut = AnimationUtils.loadAnimation(NewEntry.this, R.anim.fadeout);

                        Animation animFadeIn = AnimationUtils.loadAnimation(NewEntry.this, R.anim.fadein);

                        animFadeOut.reset();
                        imageView4.clearAnimation();
                        imageView4.startAnimation(animFadeOut);
                        animFadeIn.reset();
                        imageView4.clearAnimation();
                        imageView4.startAnimation(animFadeIn);

                        imageView4.animate().alpha(0f).setDuration(1000);
                        imageView4.animate().alpha(1f).setDuration(500);
                        preferencesEditor.putInt(MOODIMAGE_SITUATION,4);
                        preferencesEditor.apply();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.cancel();
                            }
                        }, 1000);

                      //  switchButton.setIcon(R.drawable.four);
                        imageViewMood.setImageDrawable(getResources().getDrawable(R.drawable.four));

                    }
                });




                imageView5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Animation animFadeOut = AnimationUtils.loadAnimation(NewEntry.this, R.anim.fadeout);

                        Animation animFadeIn = AnimationUtils.loadAnimation(NewEntry.this, R.anim.fadein);

                        animFadeOut.reset();
                        imageView5.clearAnimation();
                        imageView5.startAnimation(animFadeOut);
                        animFadeIn.reset();
                        imageView5.clearAnimation();
                        imageView5.startAnimation(animFadeIn);

                        imageView5.animate().alpha(0f).setDuration(1000);
                        imageView5.animate().alpha(1f).setDuration(500);

                        preferencesEditor.putInt(MOODIMAGE_SITUATION,5);
                        preferencesEditor.apply();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.cancel();
                            }
                        }, 1000);
                   //     switchButton.setIcon(R.drawable.five);
                        imageViewMood.setImageDrawable(getResources().getDrawable(R.drawable.five));

                    }
                });



              //  final AlertDialog dialog = builder.create();

                dialog.show();

            }
        });










        initUi();

/*
        diamondforfun=findViewById(R.id.diamondforfun);
        diamondforfun.setVisibility(View.VISIBLE);

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(diamondforfun, "alpha",  1f, 0);
        fadeOut.setDuration(8000);
        fadeOut.start();*/




        if (isFirstTime()) {
            // What you do when the Application is Opened First time Goes here

            ////////////////////////////////////////////////////////////////////////////////////////////////
/*

diamondforfun=findViewById(R.id.diamondforfun);
diamondforfun.setVisibility(View.VISIBLE);

            ObjectAnimator fadeOut = ObjectAnimator.ofFloat(diamondforfun, "alpha",  1f, 0);
            fadeOut.setDuration(12000);
            fadeOut.start();
*/

        }



///////////////////****

        floatingActionNewEntry= findViewById(R.id.floatingActionNewEntry);
        floatingActionNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //////////////////////?*******************
                if(keybonoff==0)
                {hideSoftKeyboard(NewEntry.this);
                keybonoff=1;
                   }
                else if(keybonoff==1)
                {showSoftKeyboard(NewEntry.this);
                keybonoff=0;
                   }
            }
        });










        imageViewMood= findViewById(R.id.imageViewMood);









        /////////mAdViewNewEntry = findViewById(R.id.adViewNewEntry);
       ///////////AdRequest adRequest2 = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
       ///////////// mAdViewNewEntry.loadAd(adRequest2);

        //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


    //    SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    //    SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(LOCATIONIMAGE_SITUATION,0 );
        preferencesEditor.apply();








        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.ACCESS_COARSE_LOCATION}, 101);



        }

        locationfordb="";

       /* try {



            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
           locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    ///çç  (trialedittext.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
                    //locationfordb="Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude();
                    Toast.makeText(getBaseContext(), "locatiooooooon", Toast.LENGTH_SHORT).show();

                    try {
                        Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        ///çç    trialedittext.setText( "\n"+addresses.get(0).getAddressLine(0)+", ");
                        locationfordb= "\n"+addresses.get(0).getAddressLine(0) ;
                    }catch(Exception e)
                    {

                    }

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5,  this);

        }
        catch(SecurityException e) {
            e.printStackTrace();
        }*/


        //this.mDB=mDB;

        edittext = findViewById(R.id.editText);
        // locationEdittext= findViewById(R.id.trialedittext);
     //   getLocationBtn = (Button) findViewById(R.id.getLocationBtn);
     //   saveBtn=findViewById(R.id.save_button);

        //karakter sınırı koyuyoruz
        //edittext.setFilters(new InputFilter[]{new InputFilter.LengthFilter(280)});
        //eğer yazarken altta sinir bozucu çizgi çıkarsa yazdığım kelimelerde, onu siliyor:)))
      /////////////////  edittext.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
//belki context fln eklenmesi gerekebilir, tam bilmiyorum..


        ///edittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

/*
        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_1.setFocusable(true);
                fab_1.setFocusableInTouchMode(true);///add this line
                fab_1.requestFocus();
            }
        });*/
        edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    getWindow().setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN

                    );
                    handled = true;
                }
               // Toast.makeText(NewEntry.this, "sssssssssssssssssssssssssssssssss", Toast.LENGTH_SHORT).show();
                return handled;
            }
        });


 /*       getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getLocation();

                //Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
               // getLocationBtn.startAnimation(aniRotate);


            }
        });*/

    }

   /* private void saveVideoURL() {

        Intent intent2 = new Intent(Intent.ACTION_VIEW);
        intent2.setData(Uri.parse("https://www.youtube.com/"));
        intent2.setPackage("com.google.android.youtube");
        intent2.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent2, YOUTUBE_REQUEST_CODE);
    }*/




/*
    ////ç
    public void saveToArrayList(View view) {
    MainActivity.mEntryList.add(edittext.getText().toString());

        Intent intentback= new Intent(view.getContext(), MainActivity.class);

        int entryListSize = MainActivity.mEntryList.size();
        MainActivity.mRecyclerView.getAdapter().notifyItemInserted(entryListSize);
// Scroll to the bottom.
        MainActivity.mRecyclerView.smoothScrollToPosition(entryListSize);


        startActivity(intentback);

    }

*/


 /*   @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Toast.makeText(this, "bbbbbbbbbbb  " + youtubelink, Toast.LENGTH_SHORT).show();

        try {
            Bundle extras = getIntent().getExtras();
            youtubelink = "";
            if (extras != null) {

                youtubelink = extras.getString(Intent.EXTRA_TEXT);
                Toast.makeText(this, "aaaaaaaaa  " + youtubelink, Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            Log.i("TAG", "Some exception " + e);
        }    }*/




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if(resultCode == this.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST:

                  if (data != null && data.getData() != null) {
                        selectedImage = data.getData();
                        //  filePath = selectedImage.getEncodedPath();

                        try {
                            /////Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                            //////myImage.setImageBitmap(bitmap);

                            Picasso.with(this).load(selectedImage).into(myImage);
                            // Toast.makeText(this, "address: "  + selectedImage, Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            Log.i("TAG", "Some exception " + e);
                        }

                    }
                    break;


               /* case YOUTUBE_REQUEST_CODE:
                    Toast.makeText(this, "birr", Toast.LENGTH_SHORT).show();

                   // if (resultCode == this.RESULT_OK){
                     //////   if (getIntent() != null && getIntent().getExtras() != null) {

                       try {
                                Bundle extras = getIntent().getExtras();
                                youtubelink = "";
                                if (extras != null) {

                                    youtubelink = extras.getString(Intent.EXTRA_TEXT);
                                    Toast.makeText(this, "aaaaaaaaa  " + youtubelink, Toast.LENGTH_SHORT).show();

                                }
                            } catch (Exception e) {
                                Log.i("TAG", "Some exception " + e);
                            }*/

                     //////   }
            //}

          }
    }














  /*  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        //  Toast.makeText(this, "link " + youtubelink, Toast.LENGTH_SHORT).show();

       *//* Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String link = extras.getString(Intent.EXTRA_TEXT);
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("link", link);
            clipboard.setPrimaryClip(clip);

            if ((clip != null) && (clip.getItemCount() != 0)) {
                youtubelink = clip.getItemAt(0).getText().toString();
                Toast.makeText(this, "aaaaaaa" + youtubelink, Toast.LENGTH_SHORT).show();
            }*//*


            ////// if (requestCode == YOUTUBE_REQUEST_CODE)
            {
                // Make sure the request was successful
                // if (resultCode == RESULT_OK)
                {

           *//*    Bundle extras = getIntent().getExtras();
                youtubelink="";
                if(extras!=null) {

                 youtubelink = extras.getString(Intent.EXTRA_TEXT);

                  //  youtubelink = data.getStringExtra(Intent.EXTRA_TEXT);
                    // ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    //ClipData clip = ClipData.newPlainText("link", link);
                    //clipboard.setPrimaryClip(clip);
                }*//*

                }
            }
        }*/






    private void initUi() {

        fab_1.setStartAngle(0)
                .setEndAngle(180)
                .setRadius(325)
                .setAnimationType(AnimationType.RADIAL)
                ///.setMovementStyle(MovementStyle.FREE);
        .setMovementStyle(MovementStyle.ANCHORED);



      /*  fab_1.setFocusable(true);
        fab_1.setFocusableInTouchMode(true);///add this line
        fab_1.requestFocus();*/



        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            fab_1.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.magic) );
        } else {
            fab_1.setBackground(ContextCompat.getDrawable(this, R.drawable.magic));
        }



        fab_1.getAnimationHandler()
                .setOpeningAnimationDuration(500)
                .setClosingAnimationDuration(200)
                .setLagBetweenItems(0)
                .setOpeningInterpolator(new FastOutSlowInInterpolator())
                .setClosingInterpolator(new FastOutLinearInInterpolator())
                .shouldFade(true)
                .shouldScale(true)
                .shouldRotate(false);

    }





    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }



    public static void showSoftKeyboard(Activity activity) {

    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

    }





/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_newentry, menu);
        switchButton = menu.findItem(R.id.selectedMode);

        return true;
    }*/














    public void saveToDatabase() {
        //çç   WordListOpenHelper mDB=new WordListOpenHelper(getApplicationContext());
        //çç    mDB.insert(edittext.getText().toString());

        // SharedPreferences mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
        getLocation();
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
String nick;



try {
    nickkey = mPreferences.getString(NICKNAME_KEY, "");

}
catch (Exception ex){


  //  SharedPreferences.Editor preferencesEditor = mPreferences.edit();
   ////////// preferencesEditor.putString(NICKNAME_KEY,"" );
   ////////// preferencesEditor.apply();

    ex.printStackTrace();}

        //Toast.makeText(this, "nickkey : " + nickkey, Toast.LENGTH_LONG).show();



        Calendar calendar = Calendar.getInstance();
      //  SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy  ");

     //   String strDate = mdformat.format(calendar.getTime());



        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yy");
        Date date = new Date(calendar.getTimeInMillis());
        String strDate = dateFormat.format(date);




        String word = edittext.getText().toString();
        int moodimage= mPreferences.getInt(MOODIMAGE_SITUATION,0);

        if (word.length() != 0|| moodimage==1|| moodimage==2|| moodimage==3|| moodimage==4|| moodimage==5) {
            ContentValues values = new ContentValues();
            values.put(KEY_ENTRY, word);
            ///çç  values.put(KEY_NICKNAME_DATE, "@" + Constants.nickName + " ・" + strDate );


            ////////////////////***values.put(KEY_NICKNAME_DATE, "@" + nickkey + " ・" + strDate);
            values.put(KEY_NICKNAME_DATE, strDate+ " ・" + "@" +  nickkey  );

            values.put(KEY_HEART, 0);
            values.put(KEY_LOCATION, locationfordb);
          values.put(KEY_LOCATION_IMAGE, 1);

                values.put(KEY_PICTURE, String.valueOf(selectedImage));
            values.put(KEY_DREAM_MODE, mPreferences.getInt(DREAMMODE_SITUATION,0));


            /*try {
                youtubelink= getIntent().getStringExtra(Intent.EXTRA_TEXT);
                Toast.makeText(this, "youtube " + youtubelink, Toast.LENGTH_SHORT).show();
            }
            catch(Exception ex) {ex.printStackTrace();}
*/

           ///////////// values.put(KEY_YOUTUBE_LINK, youtubelink);


            //////////////////////////////////////////////////////////////
            if (moodimage==1) values.put(KEY_MOOD_IMAGE, 1);
            else if (moodimage==2) values.put(KEY_MOOD_IMAGE, 2);
            else if (moodimage==3) values.put(KEY_MOOD_IMAGE, 3);
            else if (moodimage==4) values.put(KEY_MOOD_IMAGE, 4);
            else if (moodimage==5) values.put(KEY_MOOD_IMAGE, 5);
          else  values.put(KEY_MOOD_IMAGE, 0);

       //   Toast.makeText(this, "youtube link :  " + youtubelink, Toast.LENGTH_SHORT).show();


           /* int locationimage=mPreferences.getInt(LOCATIONIMAGE_SITUATION, 0);
            if (locationimage==0) values.put(KEY_LOCATION_IMAGE, 0);
            else if (locationimage==1) values.put(KEY_LOCATION_IMAGE, 1);
*/



            getContentResolver().insert(CONTENT_URI, values);



/*
//toast un rengini değiştirmek için
            Toast toast=   Toast.makeText(this, "constant.nicknamessssssssssssssss : " + Constants.nickName, Toast.LENGTH_LONG);
            View viewToast = toast.getView();

//Gets the actual oval background of the Toast then sets the colour filter
            viewToast.getBackground().setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);

//Gets the TextView from the Toast so it can be editted
            TextView text = view.findViewById(android.R.id.message);
           // text.setTextColor(yourTextColour);

            toast.show();*/


        }
try{
        //MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();
            ///////////////// MainActivity.mAdapter.notifyDataSetChanged();
   //////////////////////////************ çok önemli güzel.. başa veya istediğin yere çekiyor recyler pozisyonunu
   // MainActivity.mRecyclerView.getLayoutManager().smoothScrollToPosition(MainActivity.mRecyclerView,new RecyclerView.State(), MainActivity.mRecyclerView.getAdapter().getItemCount());

}
        catch(Exception ex){ex.printStackTrace();}
        preferencesEditor.putInt(DREAMMODE_SITUATION,0).apply();
        preferencesEditor.putInt(MOODIMAGE_SITUATION,0).apply();
       selectedImage=null;
myImage.setImageResource(R.drawable.blank);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                edittext.getText().clear();
                edittext.setCompoundDrawablesWithIntrinsicBounds( null,null,null , null);
                newEntrylayout.setBackgroundColor(Color.TRANSPARENT);
            }
        }, 500);


        Intent intentback = new Intent(this, MainActivity.class);
        //////////////çççççç    mAdapter.notifyDataSetChanged();
         //MainActivity.mRecyclerView.getAdapter().notifyItemInserted(11);

        ////// recyclerview.adapter.notifydatasetchanged  şkelinde denemek gerekiyormuş olmazasa...

        startActivity(intentback);
    }

/*
    public void getAddress() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

            getLocation();

        }

    }*/









   /* public void getLocation() {

        ////çç    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5, this);



       *//*   try {
         locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    ///çç  (trialedittext.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
                    //locationfordb="Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude();
                    Toast.makeText(getBaseContext(), "locatiooooooon", Toast.LENGTH_SHORT).show();

                    try {
                        Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        ///çç    trialedittext.setText( "\n"+addresses.get(0).getAddressLine(0)+", ");
                        locationfordb= "\n"+addresses.get(0).getAddressLine(0) ;
                    }catch(Exception e)
                    {

                    }

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5,  this);


        }
        catch(SecurityException e){
                e.printStackTrace();
            }
        }*//*
    }
    */


    public Location getLocation() {

      //  SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
      //  SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(LOCATIONIMAGE_SITUATION,1 );
        preferencesEditor.apply();

        try {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // getting GPS status
            ////çç  isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (/*!isGPSEnabled && */ !isNetworkEnabled) {
                // no network provider is enabled
               // Log.w("DOCOMO-2", "Network Connection failed");
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {

                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                       //  public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,

                            1000, // MIN_TIME_BW_UPDATES,
                            5, this); // MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        Location location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {

                            onLocationChanged( location);
                       /*     try {
                                Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
                                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                ///çç    trialedittext.setText( "\n"+addresses.get(0).getAddressLine(0)+", ");
                                locationfordb = "\n" + addresses.get(0).getAddressLine(0);
                            } catch (Exception e) {


                            }*/
                        }
                    }
                    // //çç if GPS Enabled get lat/long using GPS Services
                /*    if (isGPSEnabled) {
                        if (location == null) {
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,

                                    1000, // MIN_TIME_BW_UPDATES,
                                    5, this); // MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                            if (locationManager != null) {
                                location = locationManager
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                if (location != null) {
                                    try {
                                        Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
                                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                        ///çç    trialedittext.setText( "\n"+addresses.get(0).getAddressLine(0)+", ");
                                        locationfordb = "\n" + addresses.get(0).getAddressLine(0);
                                    } catch (Exception e) {
                                    }

                                }
                            }
                        }
                    }*/
                    locationManager = null;
                    location = null;

                }
            }

        }
        catch(Exception e)
                {
                    locationManager = null;
                    location = null;
                    e.printStackTrace();
                }
                return location;
            }














    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore2 = preferences.getBoolean("RanBefore2", false);
        if (!ranBefore2) {
            // first time
            //Toast.makeText(this, "asdsadsad", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore2", true);
            editor.commit();
        }
        return !ranBefore2;
    }






    //@Override
    public void onLocationChanged(Location location) {
        ///çç  (trialedittext.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
 //locationfordb="Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude();
      //////ççç,,,,, Toast.makeText(this, "locatiooooooon", Toast.LENGTH_SHORT).show();

       try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        ///çç    trialedittext.setText( "\n"+addresses.get(0).getAddressLine(0)+", ");
            locationfordb= addresses.get(0).getAddressLine(0) ;

           locationManager.removeUpdates(this);
        }catch(Exception e)
        {

        }
       //locationManager.removeUpdates(this);

    }

    @Override
    public void onProviderDisabled(String provider) {
       // Toast.makeText(this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();

        Toast toast=   Toast.makeText(getBaseContext(),
                R.string.open_network, Toast.LENGTH_LONG);
        View viewToast = toast.getView();
        viewToast.getBackground().setColorFilter(getBaseContext().getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
       toast.show();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    @Override
    public void onProviderEnabled(String provider) {

    }


    @Override
    protected void onPause() {
        super.onPause();
       // locationManager.removeUpdates(this);
       ///////çç   Toast.makeText(this, "listener closed", Toast.LENGTH_SHORT).show();
       // Log.i("TAG", "onPause, done");
    }


    @Override
    protected void onDestroy() {
        ///////////
        if (adView != null) {
            adView.destroy();
        }

        super.onDestroy();
    }




    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted



                   ////////////////////////// edittext.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                    //ilk açıldığında klavyeyi açmak içindi sanırım
                    /////////////////////recreate();

                } else {
                    // Permission Denied
                }
                break;


            case REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast toast = Toast.makeText(NewEntry.this, this.getString(R.string.permission_denied), Toast.LENGTH_LONG);
                    View viewToast = toast.getView();
                    viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                    toast.show();
                }
                break;


                /////////////////////////////////////////
             /*   try {

                    WordListOpenHelper woh = new WordListOpenHelper(getApplicationContext());
                    SQLiteDatabase db = woh.getWritableDatabase();


                    FileReader file = new FileReader("TweetyDiary/TweetyDiary.csv");
                    BufferedReader buffer = new BufferedReader(file);
                    String line = "";
                    String tableName = WordListOpenHelper.DIARY_LIST_TABLE;
                    String columns = "Contract.WordList.KEY_ID, Contract.WordList.KEY_ENTRY, Contract.WordList.KEY_NICKNAME_DATE, Contract.WordList.KEY_LOCATION, Contract.WordList.KEY_HEART";
                    String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
                    String str2 = ");";
                   /////// Toast.makeText(this, "sdfaasdfdsf" , Toast.LENGTH_SHORT).show();

                    db.beginTransaction();
                    while ((line = buffer.readLine()) != null) {
                        StringBuilder sb = new StringBuilder(str1);
                        String[] str = line.split(",");
                        sb.append("'" + str[0] + "',");
                        sb.append(str[1] + "',");
                        sb.append(str[2] + "',");
                        sb.append(str[3] + "'");
                        sb.append(str[4] + "'");
                        sb.append(str2);
                        db.execSQL(sb.toString());
                    }
                    db.setTransactionSuccessful();
                    db.endTransaction();

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }*/



            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }





    public void onResume() {
        super.onResume();


        changeActionStaturbarsColors();

    }






  /*  public void delete666(View view)  {

        WordListOpenHelper woh = new WordListOpenHelper(getApplicationContext());
        SQLiteDatabase db = woh.getWritableDatabase();
        try {

          // db.execSQL("delete  from " + WordListOpenHelper.DIARY_LIST_TABLE);
           //db.close();

            boolean result = this.deleteDatabase(Contract.WordList.DATABASE_NAME);
            if (result==true) {
                Toast.makeText(this, "DB Deleted!", Toast.LENGTH_LONG).show();
            }


       *//*
            db.deleteDatabase(new File("/data/" + "com.kilic.tweetydiary"
                    + "/databases/" + Contract.WordList.DATABASE_NAME));
           MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();*//*


        }
       catch(Exception ex){}

    }
*/








    public void saveShortcut(View view) {

       // if(keybonoff==0)

        try{
            if(keybonoff==0)
            {hideSoftKeyboard(NewEntry.this);
            keybonoff=1;}
        }
        catch (Exception ex){ex.printStackTrace();}


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                saveToDatabase();

            }
        }, 500);


          ////  saveToDatabase();

    }





/*

    public void restore666(View view) {

        try {
//checking wether the permission is already granted
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

// permission is already granted
// here you can directly access contacts



                WordListOpenHelper woh = new WordListOpenHelper(getApplicationContext());
                SQLiteDatabase db = woh.getWritableDatabase();
                try{

                    File exportDir = new File(Environment.getExternalStorageDirectory(), "/TweetyDiary/");

                    if (!exportDir.exists()) { exportDir.mkdirs(); }

                    File file2 = new File(exportDir, "TweetyDiary.csv");
                    FileReader file = new FileReader(file2);

                   ///////// FileReader file = new FileReader("/TweetyDiary/TweetyDiary.csv");
                    BufferedReader buffer = new BufferedReader(file);
                    ContentValues contentValues=new ContentValues(3);
                    String line = "";
                    String tableName =WordListOpenHelper.DIARY_LIST_TABLE;
                   // Toast.makeText(getBaseContext(), "sdfaasdfdsf" , Toast.LENGTH_SHORT).show();
 //int x=0;
                    db.beginTransaction();
                    while ((line = buffer.readLine()) != null)

                    {
                        x++;

                        String[] str = line.split(",");


                        if (str.length <= 2) {
                            Log.d("CSVParser", "Skipping Bad CSV Row");
                            continue;
                        }


                        // String[] str = line.replaceAll("'", "''").split(",");
                     //  Toast.makeText(this, "" + str[0], Toast.LENGTH_SHORT).show();

                    if (x>1)
                       {
                          // contentValues.put(Contract.WordList.KEY_ID, str[0]);
                          // Toast.makeText(this, "" + str[1], Toast.LENGTH_SHORT).show();

                           contentValues.put(Contract.WordList.KEY_ENTRY, str[0]);
                           Toast.makeText(this, "" + str[0], Toast.LENGTH_SHORT).show();
                           Toast.makeText(this, "" + str[1], Toast.LENGTH_SHORT).show();

                           contentValues.put(Contract.WordList.KEY_NICKNAME_DATE, str[1]);
                          contentValues.put(Contract.WordList.KEY_LOCATION, str[2]);
                           contentValues.put(Contract.WordList.KEY_LOCATION_IMAGE, str[3]);
                           contentValues.put(Contract.WordList.KEY_HEART, str[4]);
                           db.insert(tableName, null, contentValues);
                       }
                        //getContentResolver().insert(CONTENT_URI, contentValues);

                    }
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();
                }catch (IOException e){

                }















             */
/*   try {

                    WordListOpenHelper woh = new WordListOpenHelper(getApplicationContext());
                    SQLiteDatabase db = woh.getWritableDatabase();

                   // Toast.makeText(this, "aaaaaaaaaaaaaaaa" , Toast.LENGTH_SHORT).show();

                    FileReader file = new FileReader("TweetyDiary/TweetyDiary.csv");
                    BufferedReader buffer = new BufferedReader(file);
                    String line = "";
                    String tableName = Contract.WordList.DIARY_LIST_TABLE;
                    String columns = "Contract.WordList.KEY_ID, Contract.WordList.KEY_ENTRY, Contract.WordList.KEY_NICKNAME_DATE, Contract.WordList.KEY_LOCATION,Contract.WordList.KEY_LOCATION_IMAGE, Contract.WordList.KEY_HEART";
                    String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
                    String str2 = ");";
                    Toast.makeText(this, "sdfaasdfdsf" , Toast.LENGTH_SHORT).show();

                    db.begin();
                    while ((line = buffer.readLine()) != null) {
                        StringBuilder sb = new StringBuilder(str1);
                        String[] str = line.split(",");
                        sb.append("'" + str[0] + "',");
                        sb.append(str[1] + "',");
                        sb.append(str[2] + "',");
                        sb.append(str[3] + "'");
                        sb.append(str[4] + "'");
                        sb.append(str2);
                        db.execSQL(sb.toString());
                    }
                    db.setTransactionSuccessful();
                    db.endTransaction();

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }*//*




            } else {

//persmission is not granted yet
//Asking for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);


            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
*/






public void changeActionStaturbarsColors(){
    try{
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        //// ActionBar actionBar = getSupportActionBar();

        if (mPreferences.contains(COLOR)) {
            String colorstate = mPreferences.getString(COLOR, "ffffff");


            if (colorstate.equals("#33b5e5")) {
                //  actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient1));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color1));
                }
                //      viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient1));
                textViewwhatsInMind.setTextColor(getResources().getColor(color1));
                saveButton.setTextColor(getResources().getColor(color1));
            } else if (colorstate.equals("#aa66cc")) {
                ///     actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient2));
                //  fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fab2));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color2));
                }
                //    viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient2));
                textViewwhatsInMind.setTextColor(getResources().getColor(color2));
                saveButton.setTextColor(getResources().getColor(color2));


            } else if (colorstate.equals("#99cc00")) {
                ///     actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient3));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color3));
                }
                //   viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient3));
                textViewwhatsInMind.setTextColor(getResources().getColor(color3));
                saveButton.setTextColor(getResources().getColor(color3));


            } else if (colorstate.equals("#ffbb33")) {
                ///      actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient4));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color4));
                }
                //   viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient4));
                textViewwhatsInMind.setTextColor(getResources().getColor(color4));
                saveButton.setTextColor(getResources().getColor(color4));

            } else if (colorstate.equals("#ff4444")) {
                ///     actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient5));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color5));
                }
                //    viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient5));
                textViewwhatsInMind.setTextColor(getResources().getColor(color5));
                saveButton.setTextColor(getResources().getColor(color5));

            } else if (colorstate.equals("#0099cc")) {
                ///      actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient6));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color6));
                }
                //    viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient6));
                textViewwhatsInMind.setTextColor(getResources().getColor(color6));
                saveButton.setTextColor(getResources().getColor(color6));

            } else if (colorstate.equals("#9933cc")) {
                ///     actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient7));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color7));
                }
                //   viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient7));
                textViewwhatsInMind.setTextColor(getResources().getColor(color7));
                saveButton.setTextColor(getResources().getColor(color7));

            } else if (colorstate.equals("#669900")) {
                ///      actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient8));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color8));
                }
                //    viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient8));
                textViewwhatsInMind.setTextColor(getResources().getColor(color8));
                saveButton.setTextColor(getResources().getColor(color8));

            } else if (colorstate.equals("#ff8800")) {
                ///      actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient9));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color9));
                }
                //   viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient9));
                textViewwhatsInMind.setTextColor(getResources().getColor(color9));
                saveButton.setTextColor(getResources().getColor(color9));

            } else if (colorstate.equals("#cc0000")) {
                ///      actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient10));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color10));
                }
                //   viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient10));
                textViewwhatsInMind.setTextColor(getResources().getColor(color10));
                saveButton.setTextColor(getResources().getColor(color10));

            } else if (colorstate.equals("#ffffff")) {
                ///      actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient11));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }
                //  viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient11));
                textViewwhatsInMind.setTextColor(getResources().getColor(color5));
                saveButton.setTextColor(getResources().getColor(color5));

            } else if (colorstate.equals("#eeeeee")) {
                ///      actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient12));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }
                //    viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient12));
                textViewwhatsInMind.setTextColor(getResources().getColor(color5));
                saveButton.setTextColor(getResources().getColor(color5));

            } else if (colorstate.equals("#cccccc")) {
                ///      actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient13));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color13));
                }
                //    viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient13));
                textViewwhatsInMind.setTextColor(getResources().getColor(color13));
                saveButton.setTextColor(getResources().getColor(color13));

            } else if (colorstate.equals("#888888")) {
                ///      actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient14));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color14));
                }
                //    viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient14));
                textViewwhatsInMind.setTextColor(getResources().getColor(color14));
                saveButton.setTextColor(getResources().getColor(color14));

            } else {
                ///       actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }
                //   viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient2));
                textViewwhatsInMind.setTextColor(getResources().getColor(color5));
                saveButton.setTextColor(getResources().getColor(color5));

            }

        } else {
            ///    actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient2));
            //  Toast.makeText(this, "333", Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
            }
            //   viewLine.setBackground(getResources().getDrawable(R.drawable.ab_gradient2));
            textViewwhatsInMind.setTextColor(getResources().getColor(color5));
            saveButton.setTextColor(getResources().getColor(color5));



        }

    } catch (Exception ex) {ex.printStackTrace();}


}








}
