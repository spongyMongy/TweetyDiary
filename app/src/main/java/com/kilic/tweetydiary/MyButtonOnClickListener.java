package com.kilic.tweetydiary;

import android.view.View;

public class MyButtonOnClickListener implements View.OnClickListener {
  //  private static final String TAG = View.OnClickListener.class.getSimpleName();
String nickdate;
    int id;
    String word;
    int heart;
String location;
int location_image;
int mood_image;
String youtubelink;
String picture;
int dream_image;

    public MyButtonOnClickListener(int id, String nickdate, String word, int heart, String  location, int location_image, int mood_image, String picture, int dream_image) {
        this.nickdate=nickdate;
        this.id = id;
        this.word = word;
        this.heart=heart;
        this.location= location;
        this.location_image=location_image;
         this.mood_image=mood_image;
         //this.youtubelink= youtubelink;
        this.picture=picture;
        this.dream_image=dream_image;
    }











    public MyButtonOnClickListener(int id, String nickdate, String word, int heart, String  location, int location_image) {
        this.nickdate=nickdate;
        this.id = id;
        this.word = word;
        this.heart=heart;
        this.location= location;
        this.location_image=location_image;
       // this.mood_image=mood_image;
    }


    public MyButtonOnClickListener(int id, String nickdate, String word, int heart,  int location_image) {
        this.nickdate=nickdate;
        this.id = id;
        this.word = word;
        this.heart=heart;

        this.location_image=location_image;
    }




    public MyButtonOnClickListener( String nickdate, String word, int heart, String  location, int location_image, int mood_image) {
        this.nickdate=nickdate;

        this.word = word;
        this.heart=heart;
        this.location= location;
        this.location_image=location_image;
        this.mood_image=mood_image;
    }




    public void onClick(View v) {
        // Implemented in EntryListAdapter
    }
}