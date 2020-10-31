package com.kilic.tweetydiary;

public class Entry_Model {
    private int mId;
    private String mEntry;
    private String mNickname_date;
    private int mHeart;
    private String mLocation;
    private int mMood;

    public Entry_Model() {
    }


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmEntry() {
        return mEntry;
    }

    public void setmEntry(String mEntry) {
        this.mEntry = mEntry;
    }

    public String getmNickname_date() {
        return mNickname_date;
    }

    public void setmNickname_date(String mNickname_date) {
        this.mNickname_date = mNickname_date;
    }

    public int getmHeart() {
        return mHeart;
    }

    public void setmHeart(int mHeart) {
        this.mHeart = mHeart;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public int getmMood() {
        return mMood;
    }

    public void setmMood(int mMood) {
        this.mMood = mMood;
    }
}
