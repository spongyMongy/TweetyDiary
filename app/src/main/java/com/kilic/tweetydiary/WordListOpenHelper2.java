package com.kilic.tweetydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.kilic.tweetydiary.Contract.WordList.KEY_LOCATION;
import static com.kilic.tweetydiary.Contract.WordList.KEY_MOOD_IMAGE;

public class WordListOpenHelper2 extends SQLiteOpenHelper {

    private static final String TAG2 = WordListOpenHelper2.class.getSimpleName();
    // has to be 1 first time or app will crash
    private static final int DATABASE_VERSION = 2;

    static final String FAVORITES_LIST_TABLE = "favorites";
    private static final String DATABASE_NAME2 = "Myfavorites";
    // Column names...
    public static final String KEY_ID = "_id";
    public static final String KEY_ENTRY = "entry";
    public static final String KEY_NICKNAME_DATE = "nickname_date";
    public static final String KEY_HEART = "heart";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_MOOD_IMAGE = "mood_image";



    // ... and a string array of columns.
    private static final String[] COLUMNS = { KEY_ID, KEY_ENTRY, KEY_NICKNAME_DATE, KEY_HEART, KEY_LOCATION};
    public static SQLiteDatabase mWritableDB;
    public static SQLiteDatabase mReadableDB;

    private static final String DATABASE_ALTER_COLUMNS = "ALTER TABLE "
            + FAVORITES_LIST_TABLE + " ADD COLUMN " + KEY_MOOD_IMAGE + " INTEGER;";




    // Build the SQL query that creates the table.
    private static final String FAVORITES_TABLE_CREATE =
            "CREATE TABLE " + FAVORITES_LIST_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY ,"   +  // KEY_ID + " INTEGER PRIMARY KEY," +
// id will auto-increment if no value passed
                    KEY_ENTRY + " TEXT, "+      //KEY_ENTRY + " TEXT, "+
                    KEY_NICKNAME_DATE+ " TEXT, " +
                    KEY_LOCATION+ " TEXT, " +
                    ///////////////////////////////////////////////////////////////////////////////////////
                    KEY_MOOD_IMAGE+ " INTEGER, " +

                    KEY_HEART + " INTEGER );";



    public String strDate;
    //to enter the date to the top of the entry



    public WordListOpenHelper2(Context context) {
        super(context, DATABASE_NAME2, null, DATABASE_VERSION);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FAVORITES_TABLE_CREATE);
      //  Calendar calendar = Calendar.getInstance();
      //  SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy  ");
       // strDate =  mdformat.format(calendar.getTime());
    }







    //sadece verilen pozisyondaki veriyi çekmek için db den.
    //güzel metot valla kullanılor heryerde:))
    public Entry_Model query(int position) {
        String query = "SELECT * FROM " + FAVORITES_LIST_TABLE +
                " ORDER BY " + KEY_ID + " ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;
        Entry_Model entry_model= new Entry_Model();

        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry_model.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry_model.setmEntry(cursor.getString(cursor.getColumnIndex(KEY_ENTRY)));
            entry_model.setmNickname_date(cursor.getString(cursor.getColumnIndex(KEY_NICKNAME_DATE))  );
            entry_model.setmHeart(cursor.getInt(cursor.getColumnIndex(KEY_HEART)) );
            entry_model.setmLocation(cursor.getString(cursor.getColumnIndex(KEY_LOCATION)) );
            entry_model.setmMood(cursor.getInt(cursor.getColumnIndex(KEY_MOOD_IMAGE)) );


        } catch (Exception e) {
        } finally {
            cursor.close();
            return entry_model;
        }
    }




    public long count(){
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        } return DatabaseUtils.queryNumEntries(mReadableDB, FAVORITES_LIST_TABLE);
    }







    public long insert(int id, String entry, String nicknamedate, int heart, String location, int moodimage){
        //for entering the time
      //  Calendar calendar = Calendar.getInstance();
      //  SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy  ");
       // strDate =  mdformat.format(calendar.getTime());

        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);

        values.put(KEY_ENTRY, entry);
        values.put(KEY_NICKNAME_DATE, nicknamedate );
        values.put(KEY_HEART, heart );
        values.put(KEY_LOCATION, location );
       //////////////////////////////////////////////////////////////////////////////
        values.put(KEY_MOOD_IMAGE, moodimage );




        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            } newId = mWritableDB.insert(FAVORITES_LIST_TABLE, null, values);
        } catch (Exception e) {
           // Log.d(TAG2, "INSERT EXCEPTION! " + e.getMessage());
        }





        return newId;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*Log.w(WordListOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");*/


        if(newVersion > oldVersion)
        {
            db.execSQL(DATABASE_ALTER_COLUMNS);
            /////////////      db.execSQL(DATABASE_ALTER_COLUMNS2);

        }
    }


    public Cursor search(String searchString) {
        String[] columns = new String[]{KEY_ENTRY,KEY_NICKNAME_DATE, KEY_HEART};
        String where =  KEY_ENTRY + " LIKE ?";
        searchString = "%" + searchString + "%";
        String[] whereArgs = new String[]{searchString};

        Cursor cursor = null;
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.query(FAVORITES_LIST_TABLE, columns, where, whereArgs, null, null, null);
        } catch (Exception e) {
          //  Log.d(TAG2, "SEARCH EXCEPTION! " + e); // Just log the exception
        }
        return cursor;
    }



    public int update(int id,  int heart) {
        int updated = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_HEART, heart);
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            updated = mWritableDB.update(FAVORITES_LIST_TABLE, //table to change
                    values, // new values to insert
                    KEY_ID + " = ?", // selection criteria for row (in this case, the _id column)
                    new String[]{String.valueOf(id)}); //selection args; the actual value of the id

        } catch (Exception e) {
         //   Log.d ("tag", "UPDATE EXCEPTION " + e);
        }
        return updated;
    }



    public int delete(int id) {
        int deleted = 0;
        try {
            if (mWritableDB == null) {
                mWritableDB = this.getWritableDatabase();
            }
            deleted = mWritableDB.delete(FAVORITES_LIST_TABLE,
                    KEY_ID + " = ? ", new String[]{String.valueOf(id)});
        } catch (Exception e) {
          //  Log.d ("TAG", "DELETE EXCEPTION " + e);
        }
        return deleted;
    }






}
