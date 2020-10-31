package com.kilic.tweetydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.kilic.tweetydiary.Contract.ALL_ITEMS;
import static com.kilic.tweetydiary.Contract.CONTENT_URI;
import static com.kilic.tweetydiary.Contract.WordList.DATABASE_NAME;
import static com.kilic.tweetydiary.Contract.WordList.KEY_DREAM_MODE;
import static com.kilic.tweetydiary.Contract.WordList.KEY_ENTRY;
import static com.kilic.tweetydiary.Contract.WordList.KEY_HEART;
import static com.kilic.tweetydiary.Contract.WordList.KEY_ID;
import static com.kilic.tweetydiary.Contract.WordList.KEY_LOCATION;
import static com.kilic.tweetydiary.Contract.WordList.KEY_LOCATION_IMAGE;
import static com.kilic.tweetydiary.Contract.WordList.KEY_MOOD_IMAGE;
import static com.kilic.tweetydiary.Contract.WordList.KEY_NICKNAME_DATE;
import static com.kilic.tweetydiary.Contract.WordList.KEY_PICTURE;
import static com.kilic.tweetydiary.Contract.WordList.KEY_YOUTUBE_LINK;

public class WordListOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = WordListOpenHelper.class.getSimpleName();
    // has to be 1 first time or app will crash



    private static   final int DATABASE_VERSION = 2;
    private static final String DIARY_LIST_TABLE = "diary";
Context ctxctx;
    private static final String DATABASE_ALTER_COLUMNS = "ALTER TABLE "
            + Contract.WordList.DIARY_LIST_TABLE + " ADD COLUMN " + KEY_MOOD_IMAGE + " INTEGER;";


    /*private static final String DATABASE_ALTER_COLUMNS2 = "ALTER TABLE "
            + Contract.WordList.DIARY_LIST_TABLE + " ADD COLUMN " + KEY_YOUTUBE_LINK + " TEXT;";*/


    private static final String DATABASE_ALTER_COLUMNS2 = "ALTER TABLE "
            + Contract.WordList.DIARY_LIST_TABLE + " ADD COLUMN " + KEY_PICTURE + " TEXT;";


    private static final String DATABASE_ALTER_COLUMNS3 = "ALTER TABLE "
            + Contract.WordList.DIARY_LIST_TABLE + " ADD COLUMN " + Contract.WordList.KEY_DREAM_MODE + " TEXT;";


    //ç private static final String DATABASE_NAME = "Mydiary";
    // Column names...
   //ç public static final String KEY_ID = "_id";
   //ç public static final String KEY_ENTRY = "entry";
   //ç public static final String KEY_NICKNAME_DATE = "nickname_date";

    // ... and a string array of columns.
    private static final String[] COLUMNS = { KEY_ID, KEY_ENTRY, KEY_NICKNAME_DATE, KEY_HEART , KEY_LOCATION, KEY_LOCATION_IMAGE , KEY_MOOD_IMAGE, Contract.WordList.KEY_PICTURE,Contract.WordList.KEY_DREAM_MODE } ;
    public static SQLiteDatabase mWritableDB;
    public static SQLiteDatabase mReadableDB;


    // Build the SQL query that creates the table.
    private static final String DIARY_TABLE_CREATE =
            "CREATE TABLE " + DIARY_LIST_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"  +
// id will auto-increment if no value passed
                    KEY_ENTRY + " TEXT, "+
                    KEY_NICKNAME_DATE+ " TEXT, " +
                    KEY_LOCATION+ " TEXT, " +
                    KEY_LOCATION_IMAGE+ " INTEGER, " +
    //////////////
      KEY_MOOD_IMAGE+ " INTEGER, " +
           //  KEY_YOUTUBE_LINK + " TEXT, " +
   //////////////
   KEY_PICTURE+ " TEXT, " +
    /////////////////
    KEY_DREAM_MODE+ " INTEGER, " +

                    KEY_HEART + " INTEGER );   ";





   /* private static final String WORD_LIST_TABLE_CREATE =
            "CREATE TABLE " + WORD_LIST_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " + // will auto-increment if no value passed
                    KEY_WORD + " TEXT );";
    */

    public String strDate;
    //to enter the date to the top of the entry



    public WordListOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //Log.d(TAG, "Construct WordListOpenHelper");
        ctxctx=context;
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DIARY_TABLE_CREATE);

       // fillDatabaseWithData(db); ///
       /* Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy  ");
         strDate =  mdformat.format(calendar.getTime());*/
    }




    /*public void fillDatabaseWithData(SQLiteDatabase db) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy  ");
        strDate =  mdformat.format(calendar.getTime());


        String[] words = {"Android", "Adapter", "ListView", "AsyncTask", "Android Studio",
                "SQLiteDatabase", "SQLOpenHelper", "Data model", "ViewHolder",
                "Android Performance", "OnClickListener"};

        // Create a container for the data.
        ContentValues values = new ContentValues();
        mWritableDB = getWritableDatabase();

        for (int i=0; i < words.length;i++) {
            // Put column/value pairs into the container. put() overwrites existing values.
            values.put(KEY_ENTRY, words[i]);
            values.put(KEY_NICKNAME_DATE,  "yorname");
           // mWritableDB.insert(DIARY_LIST_TABLE, null, values);
           // insert(values);
            ///ctxctx.getContentResolver().insert(CONTENT_URI, values);
        }
    }*/


//sadece verilen pozisyondaki veriyi çekmek için db den.
    //güzel metot valla kullanılor heryerde:))
   /*ç public Entry_Model query(int position) {
        String query = "SELECT * FROM " + DIARY_LIST_TABLE +
                " ORDER BY " + KEY_ID + " ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;
        Entry_Model entry_model= new Entry_Model();

        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry_model.setmId(cursor.getInt(cursor.getColumnIndex(Contract.WordList.KEY_ID)));
            entry_model.setmEntry(cursor.getString(cursor.getColumnIndex(Contract.WordList.KEY_ENTRY)));
            entry_model.setmNickname_date(cursor.getString(cursor.getColumnIndex(Contract.WordList.KEY_NICKNAME_DATE)) );

        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            cursor.close();
            return entry_model;
        }
    }
*/


    public Cursor query(int position) {
        String query = null;
        if (position != ALL_ITEMS) {
            position++; // Because database starts counting at 1.
            query = "SELECT * FROM " + DIARY_LIST_TABLE +
                    " ORDER BY " + KEY_ID + " ASC " +
                    "LIMIT " + position + ",1";

        }

        else {
            query = "SELECT  * FROM " + DIARY_LIST_TABLE + " ORDER BY " + KEY_ID + " ASC ";
        }

        Cursor cursor = null;
        try {
            if (mReadableDB == null) {
                mReadableDB = this.getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
        } catch (Exception e) {
            //Log.d(TAG, "QUERY EXCEPTION! " + e);
        } finally {
            return cursor;
        }
    }









    /*ç public long count(){
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        } return DatabaseUtils.queryNumEntries(mReadableDB, DIARY_LIST_TABLE);
    }
    */

    public Cursor count(){
        MatrixCursor cursor = new MatrixCursor(new String[] {Contract.CONTENT_PATH});
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            int count = (int) DatabaseUtils.queryNumEntries(mReadableDB, DIARY_LIST_TABLE);
            cursor.addRow(new Object[]{count});
        } catch (Exception e) {
           // Log.d(TAG, "EXCEPTION " + e);
        } return cursor;
    }


   /*çç public long insert(String entry){
        //for entering the time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy  ");
        strDate =  mdformat.format(calendar.getTime());

        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(KEY_ENTRY, entry);
        values.put(KEY_NICKNAME_DATE, "@" + Constants.nickName + " ・" + strDate );

        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            } newId = mWritableDB.insert(DIARY_LIST_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());
        } return newId;
    }*/


    public long insert(ContentValues values) {
        //for entering the time
     //   Calendar calendar = Calendar.getInstance();
      //  SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy  ");
      //  strDate = mdformat.format(calendar.getTime());

        long newId = 0;

        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            newId = mWritableDB.insert(DIARY_LIST_TABLE, null, values);
        } catch (Exception e) {
           // Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());
        }
        return newId;


    }



    public long insertFull(ContentValues values) {
        //for entering the time
        //   Calendar calendar = Calendar.getInstance();
        //  SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy  ");
        //  strDate = mdformat.format(calendar.getTime());

        long newId = 0;

        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            newId = mWritableDB.insert(DIARY_LIST_TABLE, null, values);
        } catch (Exception e) {
            // Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());
        }
        return newId;


    }





    public int delete(int id) {
        int deleted = 0;
        try {
            if (mWritableDB == null) {
                mWritableDB = this.getWritableDatabase();
            }
            deleted = mWritableDB.delete(DIARY_LIST_TABLE,
                    KEY_ID + " = ? ", new String[]{String.valueOf(id)});
        } catch (Exception e) {
           // Log.d (TAG, "DELETE EXCEPTION " + e);
        }
        return deleted;
    }









    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*Log.w(WordListOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");*/
        //db.execSQL("DROP TABLE IF EXISTS " + DIARY_LIST_TABLE);


//////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////
        if(newVersion > oldVersion)
        {
             db.execSQL(DATABASE_ALTER_COLUMNS);
      /////////////      db.execSQL(DATABASE_ALTER_COLUMNS2);
            db.execSQL(DATABASE_ALTER_COLUMNS2);
            db.execSQL(DATABASE_ALTER_COLUMNS3);


           /* Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(ctxctx, "birrrrrrrrrrrrrr", Toast.LENGTH_SHORT).show();

                }
            }, 5000);
           // Toast.makeText(ctxctx, "ikiiiiiiiiiiiiiiiiiii", Toast.LENGTH_SHORT).show();*/
        }




        //onCreate(db);
    }


    public Cursor search(String searchString) {
        String[] columns = new String[]{KEY_ENTRY,  KEY_NICKNAME_DATE   , KEY_LOCATION};
        String where =  KEY_NICKNAME_DATE + " LIKE ? OR " + KEY_ENTRY +       " LIKE ? OR " + KEY_LOCATION +
                " LIKE ?"  ;                     //KEY_ENTRY + " LIKE ?";
        searchString = "%" + searchString + "%";
        String[] whereArgs = new String[]{searchString, searchString, searchString};

        Cursor cursor = null;
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.query(DIARY_LIST_TABLE, columns, where, whereArgs, null, null, null);
        } catch (Exception e) {
           // Log.d(TAG, "SEARCH EXCEPTION! " + e); // Just log the exception
        }
        return cursor;
    }







    public Cursor searchMood(String searchString) {
        String[] columns = new String[]{KEY_NICKNAME_DATE, Contract.WordList.KEY_MOOD_IMAGE  };
        String where =  KEY_NICKNAME_DATE + " LIKE ? OR " + KEY_MOOD_IMAGE +
                " LIKE ?"  ;        ;                     //KEY_ENTRY + " LIKE ?";
        searchString = "%" + searchString + "%";
        String[] whereArgs = new String[]{searchString};

        Cursor cursor = null;
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.query(DIARY_LIST_TABLE, columns, where, whereArgs, null, null, null);
        } catch (Exception e) {
            // Log.d(TAG, "SEARCH EXCEPTION! " + e); // Just log the exception
        }
        return cursor;
    }








    public int update2(int id,  int heart) {
        int updated = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_HEART, heart);
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            updated = mWritableDB.update(DIARY_LIST_TABLE, //table to change
                    values, // new values to insert
                    KEY_ID + " = ?", // selection criteria for row (in this case, the _id column)
                    new String[]{String.valueOf(id)}); //selection args; the actual value of the id

        } catch (Exception e) {
          //  Log.d ("tag", "UPDATE EXCEPTION " + e);
        }
        return updated;
    }



    public int update3(int id,  int locationImage) {
        int updated = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LOCATION_IMAGE, locationImage);
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            updated = mWritableDB.update(DIARY_LIST_TABLE, //table to change
                    values, // new values to insert
                    KEY_ID + " = ?", // selection criteria for row (in this case, the _id column)
                    new String[]{String.valueOf(id)}); //selection args; the actual value of the id

        } catch (Exception e) {
          //  Log.d ("tag", "UPDATE EXCEPTION " + e);
        }
        return updated;
    }


   /* public int update4(int id,  int heart, int locationImage) {
        int updated = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_HEART, heart);
            values.put(KEY_LOCATION_IMAGE, locationImage);

            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            updated = mWritableDB.update(DIARY_LIST_TABLE, //table to change
                    values, // new values to insert
                    KEY_ID + " = ?", // selection criteria for row (in this case, the _id column)
                    new String[]{String.valueOf(id)}); //selection args; the actual value of the id

        } catch (Exception e) {
           // Log.d ("tag", "UPDATE EXCEPTION " + e);
        }
        return updated;
    }


*/




  /*  public int update5(int id,  String entry, int heart, String location, int locationImage) {
        int updated = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_ID, id);
            values.put(KEY_ENTRY, entry);

            values.put(KEY_HEART, heart);
            values.put(KEY_LOCATION, location);

            values.put(KEY_LOCATION_IMAGE, locationImage);

            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            updated = mWritableDB.update(DIARY_LIST_TABLE, //table to change
                    values, // new values to insert
                    KEY_ID + " = ?", // selection criteria for row (in this case, the _id column)
                    new String[]{String.valueOf(id)}); //selection args; the actual value of the id

        } catch (Exception e) {
            // Log.d ("tag", "UPDATE EXCEPTION " + e);
        }
        return updated;
    }
*/






    public int update6(int id,  String entry, int heart, String location, int locationImage, int moodimage, String picture, int dreamimage) {
        int updated = 0;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_ID, id);
            values.put(KEY_ENTRY, entry);

            values.put(KEY_HEART, heart);
            values.put(KEY_LOCATION, location);

            values.put(KEY_LOCATION_IMAGE, locationImage);
            values.put(KEY_MOOD_IMAGE, moodimage);
            values.put(KEY_PICTURE, picture);
            values.put(KEY_DREAM_MODE, dreamimage);



            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            updated = mWritableDB.update(DIARY_LIST_TABLE, //table to change
                    values, // new values to insert
                    KEY_ID + " = ?", // selection criteria for row (in this case, the _id column)
                    new String[]{String.valueOf(id)}); //selection args; the actual value of the id

        } catch (Exception e) {
            // Log.d ("tag", "UPDATE EXCEPTION " + e);
        }
        return updated;
    }













   /* public Cursor raw() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " +DIARY_LIST_TABLE, new String[]{});

        return res;
    }
*/


    public Cursor raw() {

        SQLiteDatabase db = this.getReadableDatabase();
     //   Cursor res = db.rawQuery("SELECT "  +    Contract.WordList.KEY_NICKNAME_DATE + "," +  Contract.WordList.KEY_ENTRY                    + " FROM  " + DIARY_LIST_TABLE, new String[]{});
     ////////////   Cursor res = db.rawQuery("SELECT "  +  Contract.WordList.KEY_ID+ "," + Contract.WordList.KEY_ENTRY + "," +  Contract.WordList.KEY_NICKNAME_DATE + "," +  Contract.WordList.KEY_LOCATION  + ","  + Contract.WordList.KEY_LOCATION_IMAGE + ","  + Contract.WordList.KEY_HEART               + " FROM  " + DIARY_LIST_TABLE, new String[]{});
        Cursor res = db.rawQuery("SELECT "  +   Contract.WordList.KEY_ENTRY + "," +  Contract.WordList.KEY_NICKNAME_DATE + "," +  Contract.WordList.KEY_LOCATION    + " FROM  " + DIARY_LIST_TABLE, new String[]{});

        return res;
    }




}
