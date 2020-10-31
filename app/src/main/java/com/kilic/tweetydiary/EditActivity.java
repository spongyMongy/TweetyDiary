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
import java.util.List;
import java.util.Locale;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import rjsv.floatingmenu.animation.enumerators.AnimationType;
import rjsv.floatingmenu.floatingmenubutton.FloatingMenuButton;
import rjsv.floatingmenu.floatingmenubutton.MovementStyle;
import rjsv.floatingmenu.floatingmenubutton.subbutton.FloatingSubButton;

import static com.kilic.tweetydiary.Contract.WordList.KEY_DREAM_MODE;
import static com.kilic.tweetydiary.Contract.WordList.KEY_ENTRY;
import static com.kilic.tweetydiary.Contract.WordList.KEY_HEART;
import static com.kilic.tweetydiary.Contract.WordList.KEY_ID;
import static com.kilic.tweetydiary.Contract.WordList.KEY_LOCATION;
import static com.kilic.tweetydiary.Contract.WordList.KEY_LOCATION_IMAGE;
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

public class EditActivity extends AppCompatActivity implements LocationListener {

    //MenuItem switchButton;
    private AdView adView;
    FloatingMenuButton fab_1edit;
    FloatingSubButton floatingSubButton1edit;
    FloatingSubButton floatingSubButton4edit;
    FloatingSubButton floatingSubButton2edit;
    FloatingSubButton floatingSubButton5edit;
    FloatingSubButton floatingSubButton3edit;
    private final String DREAMMODE_SITUATION = "dreammode_situation";
    Uri selectedImage;
    ImageView myImage;
    View viewLine2;

    private static final int  GALLERY_REQUEST=3;
    private static final int REQUEST_CODE = 1 ;
    ConstraintLayout editLayout;
TextView textViewEdit;



    ImageView imageView1edit;
    ImageView imageView2edit;
    ImageView imageView3edit;
    ImageView imageView4edit;
    ImageView imageView5edit;
    private String COLOR = "color";
    private String TEXTCOLORIS;
    ImageView imageViewMood2;



private int idWas;
    private String entryWas;
    private String locationWas;
    private int locationImageWas;
    private int heartWas;
    private int moodWas;
    private int dreamImageWas;
    private String pictureWas;




    EditText edittextEdit;
    //EntryListAdapter mAdapter;
    static String locationfordb = "";
    LocationManager locationManager;
    FloatingActionButton fab;
    static int keybonoff;
    boolean visible;

    SharedPreferences.Editor preferencesEditor;

     SharedPreferences mPreferences;
    private AdView mAdViewNewEntryEdit;
    private final String NICKNAME_KEY = "nickname";
    private final String MOODIMAGE_SITUATION = "moodimage_situation";
 //   private final String LOCATIONIMAGE_SITUATION = "locationimage_situation";

    // static final String mSharedPrefFile = "com.kilic.tweetydiary.hellosharedprefs";

    String nickkey;
    Location location;
    Button saveButton;



    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));

    }






    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
*/



        setContentView(R.layout.activity_edit);
        //to hide the status bar
     ///////////   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        adView = new AdView(this, getString(R.string.adfacebookbannerid3editActivity), AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        //AdSettings.addTestDevice("3b5b04bd-592c-468a-9e18-3d6b363651bc");

        adView.loadAd();






        myImage= findViewById(R.id.myImage);
        viewLine2= findViewById(R.id.viewLine2);

      ///////////  ActionBar actionBar = getSupportActionBar();
     ///////////   actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient));

        imageViewMood2=findViewById(R.id.imageViewMood2);

         mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
         preferencesEditor = mPreferences.edit();
        edittextEdit = findViewById(R.id.editTextEdit);
        textViewEdit = findViewById(R.id.textViewEdit);
        saveButton=findViewById(R.id.saveButton);



        preferencesEditor.putInt(MOODIMAGE_SITUATION,0);
        preferencesEditor.apply();



        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "G");








        editLayout = findViewById(R.id.editLayout);

///////////////////////////BAK  DIKKAT!!!!!!!!!!!!!
        if (mPreferences.contains(TEXTCOLORIS)){
            try{
                edittextEdit.setTextColor(mPreferences.getInt(TEXTCOLORIS,R.color.newEntryText));

            }
            catch(Exception ex){ex.printStackTrace();}

        }


        else{edittextEdit.setTextColor(getResources().getColor(R.color.colorEntry));}






        try {
            Intent intent = getIntent();
            String entryWas = intent.getStringExtra("entryIs");
            edittextEdit.setText(entryWas);

            idWas = intent.getIntExtra("idIs", 0);
            locationWas = intent.getStringExtra("locationIs");
            locationImageWas = intent.getIntExtra("locationImageIs", 0);
            heartWas = intent.getIntExtra("heartIs", 0);
            moodWas = intent.getIntExtra("moodImageIs", 0);
            dreamImageWas = intent.getIntExtra("dreamImageIs", 0);
            pictureWas = intent.getStringExtra("pictureIs");

            if(moodWas==1)
                imageViewMood2.setImageDrawable(getResources().getDrawable(R.drawable.one));
            else if(moodWas==2)
                imageViewMood2.setImageDrawable(getResources().getDrawable(R.drawable.two));
            else if(moodWas==3)
                imageViewMood2.setImageDrawable(getResources().getDrawable(R.drawable.three));
           else  if(moodWas==4)
                imageViewMood2.setImageDrawable(getResources().getDrawable(R.drawable.four));

            else if(moodWas==5)
                imageViewMood2.setImageDrawable(getResources().getDrawable(R.drawable.five));



        }



        catch (Exception ex){ex.printStackTrace();}











        fab= findViewById(R.id.floatingActionButtonEdit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keybonoff==0)
                {hideSoftKeyboard(EditActivity.this);
                    keybonoff=1;}
                else if(keybonoff==1)
                {showSoftKeyboard(EditActivity.this);
                    keybonoff=0;}
            }
        });






        fab_1edit = (FloatingMenuButton) findViewById(R.id.fab_1edit);
        fab_1edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fab_1edit.closeMenu();
                    }
                }, 5000);
            }
        });


        floatingSubButton3edit= (FloatingSubButton) findViewById(R.id.fab_1_sub_fab_left_3edit); // create the button
        floatingSubButton3edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase();

            }
        });



        floatingSubButton4edit= (FloatingSubButton) findViewById(R.id.fab_1_sub_fab_left_4edit); // create the button
        floatingSubButton4edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(EditActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

                }

                else {
                    ActivityCompat.requestPermissions(EditActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
                }






            }
        });






        floatingSubButton5edit= (FloatingSubButton) findViewById(R.id.fab_1_sub_fab_left_5edit); // create the button
        floatingSubButton5edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dreamImageWas == 0){
                    editLayout.setBackgroundResource(R.color.colorDreamMode);
                edittextEdit.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.unicorn), null, null);
              //////  edittextEdit.getText().insert(edittextEdit.getSelectionStart(), "\n" +
               ///////         getString(R.string.dreamMode));

                /////preferencesEditor.putInt(DREAMMODE_SITUATION,1).apply();

                    Toast toast=   Toast.makeText(getBaseContext(),
                            getString(R.string.dreamMode), Toast.LENGTH_LONG);
                    View viewToast = toast.getView();
                    viewToast.getBackground().setColorFilter(getBaseContext().getResources().getColor(R.color.color7), PorterDuff.Mode.SRC_IN);
                    toast.show();


                dreamImageWas = 1;
            }


            else{
                    edittextEdit.setCompoundDrawablesWithIntrinsicBounds( null,null,null , null);
                    editLayout.setBackgroundColor(Color.TRANSPARENT);
                    dreamImageWas = 0;
                    Toast toast=   Toast.makeText(getBaseContext(),
                            getString(R.string.dreamModeOff), Toast.LENGTH_LONG);
                    View viewToast = toast.getView();
                    viewToast.getBackground().setColorFilter(getBaseContext().getResources().getColor(R.color.DreamModeOff), PorterDuff.Mode.SRC_IN);
                    toast.show();

                }




            }
        });










        floatingSubButton2edit= (FloatingSubButton) findViewById(R.id.fab_1_sub_fab_left_2edit); // create the button
        floatingSubButton2edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar rightNow = Calendar.getInstance();
                int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
                int currentMin = rightNow.get(Calendar.MINUTE);

                edittextEdit.getText().insert(edittextEdit.getSelectionStart(), "\n" +"[" + String.valueOf(currentHour) + ":" + String.valueOf(currentMin) + "]   ~ ");


            }
        });






        floatingSubButton1edit= (FloatingSubButton) findViewById(R.id.fab_1_sub_fab_left_1edit);
        floatingSubButton1edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.mood_dialog_layout,null);

                builder.setView(dialogView);

                // Button one = (Button) dialogView.findViewById(R.id.button1);
                // Button two = (Button) dialogView.findViewById(R.id.button2);
                // Button three = (Button) dialogView.findViewById(R.id.button3);
                imageView1edit= dialogView.findViewById(R.id.imageView1);
                imageView2edit= dialogView.findViewById(R.id.imageView2);
                imageView3edit= dialogView.findViewById(R.id.imageView3);
                imageView4edit= dialogView.findViewById(R.id.imageView4);
                imageView5edit= dialogView.findViewById(R.id.imageView5);


                final AlertDialog dialog = builder.create();


                imageView1edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Animation animFadeOut = AnimationUtils.loadAnimation(EditActivity.this, R.anim.fadeout);

                        Animation animFadeIn = AnimationUtils.loadAnimation(EditActivity.this, R.anim.fadein);

                        animFadeOut.reset();
                        imageView1edit.clearAnimation();
                        imageView1edit.startAnimation(animFadeOut);
                        animFadeIn.reset();
                        imageView1edit.clearAnimation();
                        imageView1edit.startAnimation(animFadeIn);

                        imageView1edit.animate().alpha(0f).setDuration(1000);
                        imageView1edit.animate().alpha(1f).setDuration(500);

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
                        imageViewMood2.setImageDrawable(getResources().getDrawable(R.drawable.one));


                    }
                });




                imageView2edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Animation animFadeOut = AnimationUtils.loadAnimation(EditActivity.this, R.anim.fadeout);

                        Animation animFadeIn = AnimationUtils.loadAnimation(EditActivity.this, R.anim.fadein);

                        animFadeOut.reset();
                        imageView2edit.clearAnimation();
                        imageView2edit.startAnimation(animFadeOut);
                        animFadeIn.reset();
                        imageView2edit.clearAnimation();
                        imageView2edit.startAnimation(animFadeIn);

                        imageView2edit.animate().alpha(0f).setDuration(1000);
                        imageView2edit.animate().alpha(1f).setDuration(500);
                        preferencesEditor.putInt(MOODIMAGE_SITUATION,2);
                        preferencesEditor.apply();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.cancel();
                            }
                        }, 1000);

                      //  switchButton.setIcon(R.drawable.two);

                        imageViewMood2.setImageDrawable(getResources().getDrawable(R.drawable.two));


                    }
                });


                imageView3edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Animation animFadeOut = AnimationUtils.loadAnimation(EditActivity.this, R.anim.fadeout);

                        Animation animFadeIn = AnimationUtils.loadAnimation(EditActivity.this, R.anim.fadein);

                        animFadeOut.reset();
                        imageView3edit.clearAnimation();
                        imageView3edit.startAnimation(animFadeOut);
                        animFadeIn.reset();
                        imageView3edit.clearAnimation();
                        imageView3edit.startAnimation(animFadeIn);

                        imageView3edit.animate().alpha(0f).setDuration(1000);
                        imageView3edit.animate().alpha(1f).setDuration(500);
                        preferencesEditor.putInt(MOODIMAGE_SITUATION,3);
                        preferencesEditor.apply();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.cancel();
                            }
                        }, 1000);
                      //  switchButton.setIcon(R.drawable.three);
                        imageViewMood2.setImageDrawable(getResources().getDrawable(R.drawable.three));



                    }
                });



                imageView4edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Animation animFadeOut = AnimationUtils.loadAnimation(EditActivity.this, R.anim.fadeout);

                        Animation animFadeIn = AnimationUtils.loadAnimation(EditActivity.this, R.anim.fadein);

                        animFadeOut.reset();
                        imageView4edit.clearAnimation();
                        imageView4edit.startAnimation(animFadeOut);
                        animFadeIn.reset();
                        imageView4edit.clearAnimation();
                        imageView4edit.startAnimation(animFadeIn);

                        imageView4edit.animate().alpha(0f).setDuration(1000);
                        imageView4edit.animate().alpha(1f).setDuration(500);
                        preferencesEditor.putInt(MOODIMAGE_SITUATION,4);
                        preferencesEditor.apply();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.cancel();
                            }
                        }, 1000);

                       // switchButton.setIcon(R.drawable.four);
                        imageViewMood2.setImageDrawable(getResources().getDrawable(R.drawable.four));

                    }
                });




                imageView5edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Animation animFadeOut = AnimationUtils.loadAnimation(EditActivity.this, R.anim.fadeout);

                        Animation animFadeIn = AnimationUtils.loadAnimation(EditActivity.this, R.anim.fadein);

                        animFadeOut.reset();
                        imageView5edit.clearAnimation();
                        imageView5edit.startAnimation(animFadeOut);
                        animFadeIn.reset();
                        imageView5edit.clearAnimation();
                        imageView5edit.startAnimation(animFadeIn);

                        imageView5edit.animate().alpha(0f).setDuration(1000);
                        imageView5edit.animate().alpha(1f).setDuration(500);

                        preferencesEditor.putInt(MOODIMAGE_SITUATION,5);
                        preferencesEditor.apply();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.cancel();
                            }
                        }, 1000);
                       // switchButton.setIcon(R.drawable.five);
                        imageViewMood2.setImageDrawable(getResources().getDrawable(R.drawable.five));

                    }
                });



                //  final AlertDialog dialog = builder.create();

                dialog.show();

            }
        });




        initUi();


        //////////////////adsensemazi
/*
        mAdViewNewEntryEdit = findViewById(R.id.adViewNewEntryEdit);
       AdRequest adRequest7 = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
        mAdViewNewEntryEdit.loadAd(adRequest7);*/









        //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    /////////    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


    /////////    preferencesEditor.putInt(LOCATIONIMAGE_SITUATION,0 );
     ///////////   preferencesEditor.apply();


        //////////////Ççç

    /*    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.ACCESS_COARSE_LOCATION}, 101);



        }*/

      ////////  locationfordb="";




        //this.mDB=mDB;
        edittextEdit = findViewById(R.id.editTextEdit);


       ////   Toast.makeText(this, "moodwas is: " + moodWas, Toast.LENGTH_SHORT).show();



        // locationEdittext= findViewById(R.id.trialedittext);
        //getLocationBtn = (Button) findViewById(R.id.getLocationBtn);
      //  saveBtn=findViewById(R.id.save_buttonEdit);

        //karakter sınırı koyuyoruz
        //edittext.setFilters(new InputFilter[]{new InputFilter.LengthFilter(280)});
        //eğer yazarken altta sinir bozucu çizgi çıkarsa yazdığım kelimelerde, onu siliyor:)))
     ///   edittextEdit.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
//belki context fln eklenmesi gerekebilir, tam bilmiyorum..


    ///    edittextEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);


        edittextEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    getWindow().setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                    );
                    handled = true;
                }
                return handled;
            }
        });


    }

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


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if(resultCode == this.RESULT_OK)
        switch (requestCode) {
            case GALLERY_REQUEST:

                if (data != null && data.getData() != null) {
                    selectedImage = data.getData();
                    pictureWas= String.valueOf(selectedImage);
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


        }
    }


















    private void initUi() {


        fab_1edit.setStartAngle(0)
                .setEndAngle(180)
                .setRadius(325)
                .setAnimationType(AnimationType.RADIAL)
                .setMovementStyle(MovementStyle.ANCHORED);


        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            fab_1edit.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.magic) );
        } else {
            fab_1edit.setBackground(ContextCompat.getDrawable(this, R.drawable.magic));
        }



        fab_1edit.getAnimationHandler()
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



    public void saveToDatabase() {
        //çç   WordListOpenHelper mDB=new WordListOpenHelper(getApplicationContext());
        //çç    mDB.insert(edittext.getText().toString());

        // SharedPreferences mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String nick;

        try {
            nickkey = mPreferences.getString(NICKNAME_KEY, "");
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putString(NICKNAME_KEY,"" );
            preferencesEditor.apply();
        }
        catch (Exception ex){




            ex.printStackTrace();}

        //Toast.makeText(this, "nickkey : " + nickkey, Toast.LENGTH_LONG).show();



        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy  ");
        String strDate = mdformat.format(calendar.getTime());

        String word = edittextEdit.getText().toString();
        int moodimage= mPreferences.getInt(MOODIMAGE_SITUATION,0);

        if (word.length() != 0|| moodimage==1|| moodimage==2|| moodimage==3|| moodimage==4|| moodimage==5) {
            ContentValues values = new ContentValues();
            values.put(KEY_ENTRY, word);
            values.put(KEY_ID, idWas);
            values.put(KEY_HEART, heartWas);
            values.put(KEY_LOCATION, locationWas);
            values.put(KEY_LOCATION_IMAGE, locationImageWas);
            values.put(KEY_DREAM_MODE, dreamImageWas);
            values.put(KEY_PICTURE, pictureWas);




            //////////////////////////////////////////////////////////////




            WordListOpenHelper woh = new WordListOpenHelper(this);


            if (moodimage==1 || moodimage==2 || moodimage==3|| moodimage==4 || moodimage==5){
                woh.update6(idWas, word, heartWas, locationWas, locationImageWas, moodimage, pictureWas, dreamImageWas);
            }
            else {
                woh.update6(idWas, word, heartWas, locationWas, locationImageWas, moodWas , pictureWas, dreamImageWas);

            }

           /// woh.update6(idWas, word, heartWas, locationWas, locationImageWas, moodWas);
            // values.put(KEY_LOCATION_IMAGE, 0);
        //    int locationimage=mPreferences.getInt(LOCATIONIMAGE_SITUATION, 0);
         //   if (locationimage==0) values.put(KEY_LOCATION_IMAGE, 0);
         //   else if (locationimage==1) values.put(KEY_LOCATION_IMAGE, 1);


         //   getContentResolver().update(CONTENT_URI, values, KEY_ID + " = ?", // selection criteria for row (in this case, the _id column)
           //         new String[]{String.valueOf(idWas)});




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


        Intent intentback = new Intent(this, MainActivity.class);
        //////////////çççççç    mAdapter.notifyDataSetChanged();

/*try {
    MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();
}catch(Exception ex) {ex.printStackTrace();}*/


        preferencesEditor.putInt(DREAMMODE_SITUATION,0).apply();
        selectedImage=null;
        myImage.setImageResource(R.drawable.blank);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                edittextEdit.getText().clear();
                edittextEdit.setCompoundDrawablesWithIntrinsicBounds( null,null,null , null);
                //mAdViewNewEntryEdit.setBackgroundColor(Color.TRANSPARENT);
                editLayout.setBackgroundColor(Color.TRANSPARENT);
            }
        }, 500);




        //  MainActivity.mRecyclerView.getAdapter().notifyItemInserted(11);

        ////// recyclerview.adapter.notifydatasetchanged  şkelinde denemek gerekiyormuş olmazasa...

        startActivity(intentback);
    }












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





















/*


    public Location getLocation() {

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(LOCATIONIMAGE_SITUATION,1 );
        preferencesEditor.apply();

        try {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // getting GPS status
            ////çç  isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (*/
/*!isGPSEnabled && *//*
 !isNetworkEnabled) {
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
                       */
/*     try {
                                Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
                                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                ///çç    trialedittext.setText( "\n"+addresses.get(0).getAddressLine(0)+", ");
                                locationfordb = "\n" + addresses.get(0).getAddressLine(0);
                            } catch (Exception e) {


                            }*//*

                        }
                    }
                    // //çç if GPS Enabled get lat/long using GPS Services
                */
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
                    }*//*

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
*/





















    //@Override
    public void onLocationChanged(Location location) {
        ///çç  (trialedittext.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
        //locationfordb="Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude();
        //////ççç,,,,, Toast.makeText(this, "locatiooooooon", Toast.LENGTH_SHORT).show();

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            ///çç    trialedittext.setText( "\n"+addresses.get(0).getAddressLine(0)+", ");
            locationfordb= "\n"+addresses.get(0).getAddressLine(0) ;

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



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_edit, menu);
        switchButton = menu.findItem(R.id.edit_selectedMode);


        if(moodWas==1)
            switchButton.setIcon(R.drawable.one);
        if(moodWas==2)
            switchButton.setIcon(R.drawable.two);
        if(moodWas==3)
            switchButton.setIcon(R.drawable.three);
        if(moodWas==4)
            switchButton.setIcon(R.drawable.four);

        if(moodWas==5)
            switchButton.setIcon(R.drawable.five);

        return true;
    }*/





    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted



                    edittextEdit.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    recreate();


                } else {
                    // Permission Denied

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void onResume() {
        super.onResume();
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        /////ActionBar actionBar = getSupportActionBar();
        changeStatusActionBarColors();



    }

    public void saveShortcut(View view) {

        // if(keybonoff==0)

        try{
            if(keybonoff==0)
            {hideSoftKeyboard(EditActivity.this);
                keybonoff=1;}
        }
        catch (Exception ex){ex.printStackTrace();}


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                saveToDatabase();

            }
        }, 600);


       // saveToDatabase();
    }






    public void changeStatusActionBarColors(){
        if (mPreferences.contains(COLOR)) {
            String colorstate=mPreferences.getString(COLOR, "ffffff");



            if(colorstate.equals("#33b5e5")){
                ///  actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient1));
                // getLocationBtn.setTextColor(Color.parseColor("#0091EA"));
                //   saveBtn.setTextColor(Color.parseColor("#0091EA"));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color1));
                }
                //   viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient1));
                textViewEdit.setTextColor(getResources().getColor(color1));
                saveButton.setTextColor(getResources().getColor(color1));

            }


            else  if(colorstate.equals("#aa66cc")){
                ///        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient2));
                //   getLocationBtn.setTextColor(Color.parseColor("#6200EE"));
                //  saveBtn.setTextColor(Color.parseColor("#6200EE"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color2));
                }
                //  viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient2));
                textViewEdit.setTextColor(getResources().getColor(color2));
                saveButton.setTextColor(getResources().getColor(color2));

            }

            else  if(colorstate.equals("#99cc00")){
                ////////actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient3));
                // getLocationBtn.setTextColor(Color.parseColor("#388E3C"));
                //  saveBtn.setTextColor(Color.parseColor("#388E3C"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color3));
                }
                //   viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient3));
                textViewEdit.setTextColor(getResources().getColor(color3));
                saveButton.setTextColor(getResources().getColor(color3));

            }


            else  if(colorstate.equals("#ffbb33")){
                ///////     actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient4));
                // getLocationBtn.setTextColor(Color.parseColor("#E65100"));
                // saveBtn.setTextColor(Color.parseColor("#E65100"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color4));
                }
                //  viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient4));
                textViewEdit.setTextColor(getResources().getColor(color4));
                saveButton.setTextColor(getResources().getColor(color4));

            }

            else  if(colorstate.equals("#ff4444")){
                ///   actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient5));
                // getLocationBtn.setTextColor(Color.parseColor("#ff4da6"));
                //   saveBtn.setTextColor(Color.parseColor("#ff4da6"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color5));
                }
                //  viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient5));
                textViewEdit.setTextColor(getResources().getColor(color5));
                saveButton.setTextColor(getResources().getColor(color5));

            }

            else  if(colorstate.equals("#0099cc")){
                ////   actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient6));
                // getLocationBtn.setTextColor(Color.parseColor("#0099cc"));
                //   saveBtn.setTextColor(Color.parseColor("#0099cc"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color6));
                }
                //  viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient6));
                textViewEdit.setTextColor(getResources().getColor(color6));
                saveButton.setTextColor(getResources().getColor(color6));

            }

            else  if(colorstate.equals("#9933cc")){
                /////    actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient7));
//                getLocationBtn.setTextColor(Color.parseColor("#9933cc"));
                //    saveBtn.setTextColor(Color.parseColor("#9933cc"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color7));
                }
                //   viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient7));
                textViewEdit.setTextColor(getResources().getColor(color7));
                saveButton.setTextColor(getResources().getColor(color7));

            }

            else  if(colorstate.equals("#669900")){
                ///////     actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient8));
                //  getLocationBtn.setTextColor(Color.parseColor("#64DD17"));
                //  saveBtn.setTextColor(Color.parseColor("#64DD17"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color8));
                }
                // viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient8));
                textViewEdit.setTextColor(getResources().getColor(color8));
                saveButton.setTextColor(getResources().getColor(color8));

            }

            else  if(colorstate.equals("#ff8800")){
                ///////       actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient9));
                // getLocationBtn.setTextColor(Color.parseColor("#ff8800"));
                //   saveBtn.setTextColor(Color.parseColor("#ff8800"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color9));
                }
                //  viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient9));
                textViewEdit.setTextColor(getResources().getColor(color9));
                saveButton.setTextColor(getResources().getColor(color9));

            }

            else  if(colorstate.equals("#cc0000")){
                ////////      actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient10));
                //  getLocationBtn.setTextColor(Color.parseColor("#F50057"));
                //  saveBtn.setTextColor(Color.parseColor("#F50057"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color10));
                }
                //viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient10));
                textViewEdit.setTextColor(getResources().getColor(color10));
                saveButton.setTextColor(getResources().getColor(color10));

            }

            else  if(colorstate.equals("#ffffff")){
                //////      actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient11));
                //   getLocationBtn.setTextColor(Color.parseColor("#9E9E9E"));
                //   saveBtn.setTextColor(Color.parseColor("#9E9E9E"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }
                // viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient11));
                textViewEdit.setTextColor(getResources().getColor(color5));
                saveButton.setTextColor(getResources().getColor(color5));

            }

            else  if(colorstate.equals("#eeeeee")){
                ///////     actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient12));
                //  getLocationBtn.setTextColor(Color.parseColor("#BDBDBD"));
                //   saveBtn.setTextColor(Color.parseColor("#BDBDBD"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }
                //  viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient12));
                textViewEdit.setTextColor(getResources().getColor(color12));
                saveButton.setTextColor(getResources().getColor(color12));

            }


            else  if(colorstate.equals("#cccccc")){
                //////      actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient13));
                //     getLocationBtn.setTextColor(Color.parseColor("#888888"));
                //    saveBtn.setTextColor(Color.parseColor("#888888"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color13));
                }
                //  viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient13));
                textViewEdit.setTextColor(getResources().getColor(color13));
                saveButton.setTextColor(getResources().getColor(color13));

            }

            else  if(colorstate.equals("#888888")){
                //////     actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient14));
                //   getLocationBtn.setTextColor(Color.parseColor("#000000"));
                //        saveBtn.setTextColor(Color.parseColor("#000000"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color14));
                }
                // viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient14));
                textViewEdit.setTextColor(getResources().getColor(color14));
                saveButton.setTextColor(getResources().getColor(color14));

            }




            else{
                ////////     actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient2));
//                getLocationBtn.setTextColor(Color.parseColor("#9933cc"));
                // saveBtn.setTextColor(Color.parseColor("#9933cc"));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }
                //   viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient2));
                textViewEdit.setTextColor(getResources().getColor(color5));
                saveButton.setTextColor(getResources().getColor(color5));

            }

        }

        else{
            ////////   actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient2));
//                getLocationBtn.setTextColor(Color.parseColor("#9933cc"));
            // saveBtn.setTextColor(Color.parseColor("#9933cc"));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
            }
            //  viewLine2.setBackground(getResources().getDrawable(R.drawable.ab_gradient2));
            textViewEdit.setTextColor(getResources().getColor(color5));
            saveButton.setTextColor(getResources().getColor(color5));

        }



    }


    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }




}
