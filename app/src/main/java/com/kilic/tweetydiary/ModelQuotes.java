package com.kilic.tweetydiary;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelQuotes {
    @SerializedName("list")
    public ArrayList<MyObject> list;

    static public class MyObject {
        @SerializedName("text")
        public String text;
        @SerializedName("from")
        public String from;
    }
}