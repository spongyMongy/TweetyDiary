package com.kilic.tweetydiary;

import android.net.Uri;
import android.provider.BaseColumns;

public class Contract {

    public static final int ALL_ITEMS = -2;
    public static final String COUNT = "count";
    public static final String AUTHORITY =
            "com.kilic.tweetydiary.tweetydiarywithcontentprovider.provider";
    public static final String CONTENT_PATH = "words";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH);
    public static final Uri ROW_COUNT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH + "/" + COUNT);

    static final String SINGLE_RECORD_MIME_TYPE =
            "vnd.android.cursor.item/vnd.com.example.provider.words";
    static final String MULTIPLE_RECORDS_MIME_TYPE =
            "vnd.android.cursor.item/vnd.com.example.provider.words";

    private Contract() {}

    public static abstract class WordList implements BaseColumns {
        public static final String DIARY_LIST_TABLE  = "diary";
        public static final String DATABASE_NAME = "Mydiary";
        public static final String KEY_ID = "_id";
        public static final String KEY_ENTRY = "entry";
        public static final String KEY_HEART = "heart";
        public static final String KEY_NICKNAME_DATE = "nickname_date";
        public static final String KEY_LOCATION = "location";
        public static final String KEY_LOCATION_IMAGE = "location_image";
        public static final String KEY_MOOD_IMAGE = "mood_image";
        public static final String KEY_YOUTUBE_LINK = "key_youtube_link";
        public static final String KEY_PICTURE = "key_picture";
        public static final String KEY_DREAM_MODE = "key_dream_mode";


    }



}
