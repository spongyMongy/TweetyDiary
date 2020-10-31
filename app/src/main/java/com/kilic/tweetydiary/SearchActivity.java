package com.kilic.tweetydiary;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.LinkedList;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static com.kilic.tweetydiary.R.color.color1;
import static com.kilic.tweetydiary.R.color.color10;
import static com.kilic.tweetydiary.R.color.color12;
import static com.kilic.tweetydiary.R.color.color13;
import static com.kilic.tweetydiary.R.color.color2;
import static com.kilic.tweetydiary.R.color.color3;
import static com.kilic.tweetydiary.R.color.color4;
import static com.kilic.tweetydiary.R.color.color5;
import static com.kilic.tweetydiary.R.color.color6;
import static com.kilic.tweetydiary.R.color.color7;
import static com.kilic.tweetydiary.R.color.color8;
import static com.kilic.tweetydiary.R.color.color9;

public class SearchActivity extends AppCompatActivity {
 private TextView searhResult;
 private EditText msearch;

 private ImageButton imageButton;
 private WordListOpenHelper mDB;
    private String COLOR = "color";
ActionBar actionBar;
//bu kısım kendi recyclerviewe i için
 private  LinkedList<Entry_Model> mWordList;
    private RecyclerView mRecyclerView;
    private SearchListAdapter mAdapter;
 //////////////  private AdView mAdViewSearch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //to hide the status bar
      //////////  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        if(getSupportActionBar() != null){
            actionBar = getSupportActionBar();
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient5));
        }

        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "G");


      /*  mAdViewSearch = findViewById(R.id.adViewSearch);
        AdRequest adRequest4 = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
     mAdViewSearch.loadAd(adRequest4);
*/




        mWordList = new LinkedList<>();

        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewForSearch);
// Create an adapter and supply the data to be displayed.
        mAdapter = new SearchListAdapter(this, mWordList);
// Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
// Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        searhResult=findViewById(R.id.searchResultTV);
        msearch= findViewById(R.id.searchET);
       // msearch.setImeActionLabel("Custom text", KeyEvent.KEYCODE_ENTER);
    imageButton= findViewById(R.id.searchButton);


        mDB= new WordListOpenHelper(this);






        msearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = true;
                 //if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {


                     //entera basıncı keyboardın kaybolması için IME flan
                     if (actionId == EditorInfo.IME_ACTION_DONE) {
                         // do something, e.g. set your TextView here via .setText()
                         Constants.nickName = msearch.getText().toString();

                         InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                         imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                         return true;
                     }

//

                // }

                return handled;
            }
        });





///burdan sonra search sonuçlarını recyclerview le verme kısmı.****

    }









    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }




    public void showResult(View view) {

       //SearchListAdapter adapter = new SearchListAdapter(getApplicationContext(), mWordList);
        //mWordList.setAdapter(adapter);
        mWordList.clear();


        mAdapter.notifyDataSetChanged();


   String  word = msearch.getText().toString();
        searhResult.setText(getString(R.string.resultFor) +" " +  word + ":\n\n");
        // Search for the word in the database.


        try {
            Cursor cursor = mDB.search(word);
            // You must move the cursor to the first item.
            cursor.moveToFirst();
            // Only process a non-null cursor with rows.

            if (!word.equals("")) {  //boş arama yapmasını engellemek için..
                if (cursor != null & cursor.getCount() > 0) {
                    int index;
                    String result;

                    int index2;
                    String result2;


                    int index3;
                    String result3;

                    int index4;
                    int result4;

                    // Iterate over the cursor, while there are entries.
                    do {
                        //this part for putting the searched cursor items into linkedlist to be used in recyclerview later
                        Entry_Model emodel = new Entry_Model();


                        // Don't guess at the column index. Get the index for the named column.
                        index = cursor.getColumnIndex(Contract.WordList.KEY_ENTRY);
                        index2 = cursor.getColumnIndex(Contract.WordList.KEY_NICKNAME_DATE);
                        index3 = cursor.getColumnIndex(Contract.WordList.KEY_LOCATION);
                       ////////////           index4 = cursor.getColumnIndex(Contract.WordList.KEY_MOOD_IMAGE);


                        // Get the value from the column for the current cursor.
                        result = cursor.getString(index);
                        result2 = cursor.getString(index2);
                        result3 = cursor.getString(index3);
                      //////////////              result4 = cursor.getInt(index4);

                        // Add result to what's already in the text view.

                        emodel.setmEntry(result);
                        emodel.setmNickname_date(result2);
                        emodel.setmLocation(result3);
                          //////////////          emodel.setmMood(result4);

                        mWordList.add(emodel);


                        //searhResult.append(result2 + "\n" + result + "\n"+ "\n");

                    } while (cursor.moveToNext());
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    cursor.close();
                } else {
                    searhResult.append(getString(R.string.no_result));
                }
            }
        }
        catch (Exception ex){ ex.printStackTrace();}







    }



    public void onResume() {
        super.onResume();
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        try{
        ActionBar actionBar = getSupportActionBar();

        if (mPreferences.contains(COLOR)) {
            String colorstate = mPreferences.getString(COLOR, "ffffff");


            if(colorstate.equals("#33b5e5")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient1));
                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search66);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color1),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color1));
                }


            }   else  if(colorstate.equals("#aa66cc")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient2));

                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search22);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color2),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color2));
                }

            }
            else  if(colorstate.equals("#99cc00")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient3));

                //GradientDrawable drawable = new GradientDrawable();
                // drawable.setShape(GradientDrawable.RECTANGLE);
                // drawable.setStroke(5, Color.MAGENTA);
                // imageButton.setBackgroundDrawable(drawable);
                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search33);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color3),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color3));
                }
            }
            else  if(colorstate.equals("#ffbb33")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient4));

                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search44);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color4),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color4));
                }
            }
            else  if(colorstate.equals("#ff4444")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient5));

                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search55);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color5));
                }
            }
            else  if(colorstate.equals("#0099cc")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient6));

                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search66);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color6),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color6));
                }
            } else if (colorstate.equals("#9933cc")) {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient7));
                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search77);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color7),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color7));
                }
            }
            else  if(colorstate.equals("#669900")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient8));
                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search88);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color8),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color8));
                }
            }
            else  if(colorstate.equals("#ff8800")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient9));
                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search99);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color9),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color9));
                }
            }
            else  if(colorstate.equals("#cc0000")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient10));
                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search10);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color10),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color10));
                }
            }
            else  if(colorstate.equals("#ffffff")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient12));
                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search13);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color11),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }
            }
            else  if(colorstate.equals("#eeeeee")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient12));
                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search12);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color12),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }
            }   else  if(colorstate.equals("#cccccc")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient13));
                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search13);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color13),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color13));
                }
            }  else  if(colorstate.equals("#888888")){
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient14));
                imageButton.setBackgroundResource(R.drawable.border);
                imageButton.setImageResource(R.drawable.search14);
                msearch.setTextColor(getResources().getColor(R.color.colorwhite));
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color14),
                        PorterDuff.Mode.SRC_ATOP);

            } else {
                actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient12));
                imageButton.setBackgroundResource(R.drawable.border);

                imageButton.setImageResource(R.drawable.search55);
                msearch.getBackground().setColorFilter(getResources().getColor(R.color.color5),
                        PorterDuff.Mode.SRC_ATOP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color5));
                }
            }

        } else {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient12));
            imageButton.setBackgroundResource(R.drawable.border);

            imageButton.setImageResource(R.drawable.search55);
            msearch.getBackground().setColorFilter(getResources().getColor(R.color.color5),
                    PorterDuff.Mode.SRC_ATOP);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
            }

        }


    }
    catch (Exception ex) {ex.printStackTrace();}
    }







}
