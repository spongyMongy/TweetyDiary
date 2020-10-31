

package com.kilic.tweetydiary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static android.view.View.VISIBLE;


public class  EntryListAdapter extends RecyclerView.Adapter<EntryListAdapter.EntryViewHolder> {
    private Cursor mCursor = null;
    private String TEXTCOLORIS;
    SharedPreferences mPreferences;
private int color;
    private WordListOpenHelper mDB3;

    int heart;
    int mood_image;
    String word = "";
    String nickdate="";
    int id ;
    String location="";
    String picture="";
    int dream_image;

    int location_image;
    String youtubelink="";
    static final int WORD_REQUEST = 1;  // The request code

    //ç private  ArrayList<String> mEntryList;
    private LayoutInflater mInflater;
   // Context context;
    public static String entryToNotification;
    public EditText tvnickName;
    public static String nickName;
   // int counter = 0;
    //WordListOpenHelper mDB;
    //static int pos;
   // static String clickedEntry;
    private static final String TAG = EntryListAdapter.class.getSimpleName();

    FavoritesListAdapter mAdapter2;


    // bu iki statik atayıp mainacitivity de delete işlemi için gerekli position ve id ye ulaşmış olduk.. çakalca ama çalışıyor.
   public static int choosenPos;
   public static int tobeDeletedId;

    public static String result0;
    String strDate;


    private String queryUri = Contract.CONTENT_URI.toString(); // base uri
    private static final String[] projection = new String[] {Contract.CONTENT_PATH}; //table
    private String selectionClause = null;
    private String selectionArgs[] = null;
    private String sortOrder = "ASC";
    private Context mContext;

/*  ///çç
    public EntryListAdapter(Context context, ArrayList<String> entryList) {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        this.mEntryList = entryList;

    }*/

    public EntryListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;


        //mDB = db;


    }


    public void setData(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }





    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.diary_listitem, parent, false);
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(parent.getContext());

        if (mPreferences.contains(TEXTCOLORIS)){
            try{
                color = mPreferences.getInt(TEXTCOLORIS,R.color.newEntryText);

            }
            catch(Exception ex){ex.printStackTrace();}

        }
        else{color= mContext.getResources().getColor(R.color.colorEntry);

          //  Toast.makeText(mItemView.getContext(), "aaaaaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
        }









        return new EntryViewHolder(mItemView, this);
    }






    @Override
    public void onBindViewHolder(final EntryViewHolder holder, int position) {
        ///ç  String mCurrent = mEntryList.get(position);
        ///ç  holder.entryItemView.setText(mCurrent);


       ///çç final Entry_Model current = mDB.query(position);
        ///çç holder.entryItemView.setText(current.getmEntry());
       ///çç holder.tvdatetime.setText(current.getmNickname_date());
        final EntryViewHolder h = holder;


        final Cursor cursor = mContext.getContentResolver().query(Uri.parse(
                queryUri), null, null, null, sortOrder);


      /*  if(cursor.moveToPosition(0)){
            int indexNickDate = cursor.getColumnIndex(Contract.WordList.KEY_NICKNAME_DATE);
            nickdate = cursor.getString(indexNickDate);
            if (!nickdate.contains("1")){
                holder.tvdatetime.setText("dsfsdfsdfsdfsdfsdf");

            }
        }
*/


       /* SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yy");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        strDate = dateFormat.format(date);
*/
        /*mDB3= new WordListOpenHelper(mContext);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yy");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        strDate = dateFormat.format(date);
        Cursor cursor2 = mDB3.search(strDate);
        cursor2.moveToFirst();
       result0="";
        if (cursor2 != null & cursor2.getCount() > 0) {

            int index5 = cursor2.getColumnIndex(Contract.WordList.KEY_NICKNAME_DATE);
            result0 = cursor2.getString(index5);
            cursor2.close();
           Toast.makeText(mContext, "sssssss   "+ result0, Toast.LENGTH_SHORT).show();
        }*/



        /*if(!result0.contains(strDate)){
            Toast.makeText(mContext, "bbbbbb   "+ strDate, Toast.LENGTH_SHORT).show();
            holder.entryItemView.setText("aaaaaaaaaa");

        }*/





        try {
             if (cursor != null) {
                 if (cursor.moveToPosition(position)) {

                    /* if(cursor.getPosition()==cursor.getCount()-1){
                         int indexNickDate = cursor.getColumnIndex(Contract.WordList.KEY_NICKNAME_DATE);
                         nickdate = cursor.getString(indexNickDate);
                         if (nickdate.contains(strDate)){
                            // Toast.makeText(mContext, "  "+ nickdate, Toast.LENGTH_SHORT).show();

                             holder.entryItemView.setText("dsfsdfsdfsdfsdfsdf");
                             holder.imageLocation.setImageResource(R.drawable.blank);
                             holder.imageHeart.setImageResource(R.drawable.blank);
                             holder.imageMood.setImageResource(R.drawable.blank);
                             holder.tvdatetime.setText("");
                             holder.forbackgroundrelativeLayout.setBackgroundColor(Color.parseColor("#f2e6ff"));

                             cursor.moveToNext();
                         }
                     }*/









                 //    Toast.makeText(mContext, "position: " + position, Toast.LENGTH_SHORT).show();
                     int indexWord = cursor.getColumnIndex(Contract.WordList.KEY_ENTRY);
                     word = cursor.getString(indexWord);
                            holder.entryItemView.setText(word);
                           holder.entryItemView.setTextColor(color);
                   //  holder.entryItemView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.dream, 0, 0, 0);



                     int indexNickDate = cursor.getColumnIndex(Contract.WordList.KEY_NICKNAME_DATE);
                     nickdate = cursor.getString(indexNickDate);
                     holder.tvdatetime.setText(nickdate);


                     int indexId = cursor.getColumnIndex(Contract.WordList.KEY_ID);
                     id = cursor.getInt(indexId);


////////////////çç
                     int indexLocation = cursor.getColumnIndex(Contract.WordList.KEY_LOCATION);
                     location = cursor.getString(indexLocation);
                     holder.locationTv.setText(location);




///////////////////////////////////////////////////////////
                     /////////////////////////////////////

                     int indexMood = cursor.getColumnIndex(Contract.WordList.KEY_MOOD_IMAGE);
                     mood_image = cursor.getInt(indexMood);
                     if (mood_image == 1) {
                         holder.imageMood.setImageResource(R.drawable.one);
                     }
                     else if (mood_image == 2) {
                         holder.imageMood.setImageResource(R.drawable.two);
                     }
                     else if (mood_image == 3) {
                         holder.imageMood.setImageResource(R.drawable.three);
                     }
                     else if (mood_image == 4) {
                         holder.imageMood.setImageResource(R.drawable.four);
                     }
                     else if (mood_image == 5) {
                         holder.imageMood.setImageResource(R.drawable.five);
                     }
                     else  {
                         holder.imageMood.setImageResource(R.drawable.blank);
                     }







                     int indexDream = cursor.getColumnIndex(Contract.WordList.KEY_DREAM_MODE);
                     dream_image = cursor.getInt(indexDream);
                    // Toast.makeText(mContext, "dreamiamge  " + dream_image, Toast.LENGTH_SHORT).show();

                        if (dream_image==1){
                         holder.dreamImage.setImageResource(R.drawable.dream);
                         //holder.itemView.setBackgroundResource(R.drawable.aa);

                            //////////// uykulu olanlara arkaplan eklemek istersek
                          //   holder.forbackgroundrelativeLayout.setBackgroundResource(R.drawable.night);
                            holder.forbackgroundrelativeLayout.setBackgroundColor(Color.parseColor("#f2e6ff"));
//#f0e6ff
                        }
                     else {
                         holder.dreamImage.setImageResource(R.drawable.blank);
                                        holder.forbackgroundrelativeLayout.setBackgroundResource(0);
                        }





                     int indexPicture = cursor.getColumnIndex(Contract.WordList.KEY_PICTURE);
                     picture = cursor.getString(indexPicture);

                    // Toast.makeText(mContext, "pictureeee: " + picture , Toast.LENGTH_SHORT).show();
                       // holder.pictureView.setImageURI((Uri.parse(picture)));
                     if(picture.equals("null")){
                         holder.pictureView.setVisibility(View.GONE);
                         //Toast.makeText(mContext, "sssssssssssssssssss", Toast.LENGTH_SHORT).show();
                         holder.view1.setVisibility(View.GONE);
                         holder.view2.setVisibility(View.GONE);

                     }
                    else {
                         // Uri jg = Uri.parse(cursor.getString(cursor.getColumnIndex("picture")));
                         ///////Picasso.with(mContext).cancelRequest(holder.pictureView);
                         //  Picasso.with(mContext).load( Uri.parse(picture)).transform(new GradientTransformation()).fit().into(holder.pictureView);
                         Picasso.with(mContext).load(Uri.parse(picture)).centerCrop()
                                 .transform(new CropCircleTransformation())
                                 .fit()
                                 .into(holder.pictureView);

                         holder.pictureView.setVisibility(View.VISIBLE);
                         holder.view1.setVisibility(View.VISIBLE);
                         holder.view2.setVisibility(View.VISIBLE);


                     }











    /*

                     File sd = Environment.getExternalStorageDirectory();
                     File image = new File(picture);
                     BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                     Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
                     //bitmap = Bitmap.createScaledBitmap(bitmap,parent.getWidth(),parent.getHeight(),true);
                     holder.pictureView.setImageBitmap(bitmap);


*/





                     int indexLocationImage = cursor.getColumnIndex(Contract.WordList.KEY_LOCATION_IMAGE);
                     location_image = cursor.getInt(indexLocationImage);
                     if (location_image == 1) {
                         holder.imageLocation.setVisibility(VISIBLE);
                         holder.imageLocation.setImageResource(R.drawable.location2);
                         holder.locationTv.setVisibility(View.INVISIBLE);
                     }
                     else  if (location_image == 0) {
                         if (!h.locationTv.getText().equals("")){
                             holder.imageLocation.setImageResource(R.drawable.location1);
                             /// holder.imageLocation.setVisibility(View.INVISIBLE);
                             holder.locationTv.setVisibility(View.VISIBLE);
                         }

                         else {
                             holder.imageLocation.setImageResource(R.drawable.blank);
                             /// holder.imageLocation.setVisibility(View.INVISIBLE);
                             holder.locationTv.setVisibility(View.VISIBLE);
                         }

                     }








///////////////////////////////////////////////////////

//                     int indexYoutubeLink = cursor.getColumnIndex(Contract.WordList.KEY_YOUTUBE_LINK);
//                     youtubelink = cursor.getString(indexYoutubeLink);
//                     if (youtubelink.equals("")) {holder.imageYoutube.setImageResource(R.drawable.blank);}
//                     else                     {holder.imageYoutube.setImageResource(R.drawable.youtube);}

                     // else  {holder.imageYoutube.setImageResource(R.drawable.youtube);}
                    //////////////// holder.imageYoutube.setImageResource(R.drawable.youtube);






////////////////////////////////////////////////////////////////





                     int indexHeart = cursor.getColumnIndex(Contract.WordList.KEY_HEART);
                     heart = cursor.getInt(indexHeart);
                     if (heart == 0) {
                         holder.imageHeart.setImageResource(R.drawable.like);
                     } else if (heart == 1) {
                         holder.imageHeart.setImageResource(R.drawable.heart);
                     }






//                     int indexLocation = cursor.getColumnIndex(Contract.WordList.KEY_LOCATION);
//                     location = cursor.getString(indexLocation);
//                 //    if (location_image == 0) holder.locationTv.setText(location); holder.locationTv.setVisibility(View.VISIBLE);
//                  //   if (location_image == 1) holder.locationTv.setText(location); holder.locationTv.setVisibility(View.VISIBLE);
//                     holder.locationTv.setText(location);
                    // holder.locationTv.setVisibility(View.INVISIBLE);
                     //if (location_image == 0) holder.locationTv.setText(location); holder.locationTv.setVisibility(View.VISIBLE);


             ///      Toast.makeText(mContext, "nickdate : " +nickdate  + position , Toast.LENGTH_SHORT).show();


                 } else {
                     holder.entryItemView.setText(R.string.error_no_word);
                 }
                 cursor.close();
             } else {
                 //Log.e(TAG, "onBindViewHolder: Cursor is null.");
             }
         }
         catch (Exception ex) {
            // Log.d("Exception", "Cursor problem");
         }


//////////////////////////////////////////

         holder.pictureView.setOnClickListener(new MyButtonOnClickListener( id, nickdate, word, heart, location,location_image, mood_image, picture, dream_image){

             public void onClick(View v) {
                 Intent intent= new Intent(mContext,BigImage.class);
                 intent.putExtra("image_url",picture);
                 mContext.startActivity(intent);


             }



        });



         holder.entryItemView.setOnClickListener(new MyButtonOnClickListener( id, nickdate, word, heart, location,location_image, mood_image, picture, dream_image) {
            @Override
            public void onClick(View v) {


                Intent i3= new Intent(mContext , EditActivity.class);
                i3.putExtra("entryIs", word);

                // Toast.makeText(mContext, "dsfdsf sdfsdf sdf " + word, Toast.LENGTH_SHORT).show();
                i3.putExtra("idIs", id);
                i3.putExtra("locationIs", location);
                i3.putExtra("heartIs", heart);
                i3.putExtra("locationImageIs", location_image);
                i3.putExtra("moodImageIs",mood_image);
                i3.putExtra("dreamImageIs",dream_image);
                i3.putExtra("pictureIs",picture);

                //  i3.putExtra("youtubeImageIs",youtubelink);

                // notifyDataSetChanged();

             //   Toast.makeText(mContext, "moodwas is: " +mood_image, Toast.LENGTH_SHORT).show();
                ///    Toast.makeText(mContext, "heartwas is: " + heart, Toast.LENGTH_SHORT).show();


                ( mContext) .startActivity(i3);
            }
        });







        holder.imageLocation.setOnClickListener(new MyButtonOnClickListener( id, nickdate, word, heart, location,location_image, mood_image, picture, dream_image) {
            @Override
            public void onClick(View v) {

                try{
                    if (location_image == 0) {
                   /* h.imageLocation.setImageResource(R.drawable.location2);

                    h.locationTv.setVisibility(View.INVISIBLE);

                    location_image=1;
                    */
                        if (!h.locationTv.getText().equals("")) {
                            location_image = 1;
                            h.locationTv.setVisibility(View.INVISIBLE);
                        }

                        if (h.locationTv.getText().equals("")) {
                            location_image = 0;
                            h.locationTv.setVisibility(View.VISIBLE);

                        }

                    } else if (location_image == 1) {
                        location_image = 0;
                        ////h.locationTv.setVisibility(View.VISIBLE);


                        //h.imageLocation.setImageResource(R.drawable.location1);


                        //h.locationTv.setVisibility(View.VISIBLE);
                        // location_image=0;


                    }
                    WordListOpenHelper woh = new WordListOpenHelper(mContext);
                    woh.update3(id, location_image);

                    //veya   MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();
                    notifyDataSetChanged();
                }



                catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        });







        holder.imageHeart.setOnClickListener(new MyButtonOnClickListener( id, nickdate, word, heart, location,location_image, mood_image, picture, dream_image) {
            @Override
            public void onClick(View v) {

                try {
                    if (heart == 0) {
                        h.imageHeart.setImageResource(R.drawable.heart);
                        heart = 1;
                        WordListOpenHelper2 woh2 = new WordListOpenHelper2(mContext);
                        // String st= h.getItemId();


                        woh2.insert(id, word, nickdate, heart, location, mood_image);
                        // Toast.makeText(v.getContext(), "constant.nickname : " +word + " " + nickdate , Toast.LENGTH_LONG).show();

                        // mAdapter2.notifyDataSetChanged();
                        // FavoritesActivity.mRecyclerView2.getAdapter().notifyDataSetChanged();


                    } else if (heart == 1) {
                        h.imageHeart.setImageResource(R.drawable.like);
                        heart = 0;
                        WordListOpenHelper2 woh2 = new WordListOpenHelper2(mContext);

                        woh2.delete(id);
//                    FavoritesActivity.mRecyclerView2.getAdapter().notifyItemRemoved(h.getAdapterPosition());
                        //                   FavoritesActivity.mRecyclerView2.getAdapter().notifyItemRangeChanged(h.getAdapterPosition(), getItemCount());
                        // mAdapter2.notifyDataSetChanged();
                        //FavoritesActivity.mRecyclerView2.getAdapter().notifyDataSetChanged();


                    }
                    WordListOpenHelper woh = new WordListOpenHelper(mContext);
                    woh.update2(id, heart);
                    //         woh.update4(id, heart,location_image);


                    //veya   MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();
                    notifyDataSetChanged();

                }

                catch (Exception ex) {
                    ex.printStackTrace();
                }





            }
        });






    }

  //ç  public int getItemCount() {
   //     return (int) mDB.count();
   // }

    @Override
    public int getItemCount() {
        Cursor cursor = mContext.getContentResolver().query(
                Contract.ROW_COUNT_URI, new String[] {"count(*) AS count"},
                selectionClause, selectionArgs, sortOrder);
        try {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count;
        } catch (Exception e){
          //  Log.d(TAG, "EXCEPTION getItemCount: " + e);
            return -1;
        }
    }




    public class EntryViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public final TextView entryItemView;
        final EntryListAdapter mAdapter;
        public TextView tvdatetime;
        public ImageView imageHeart;
        public TextView locationTv;
        public ImageView imageLocation;
        public ImageView imageMood;
        public ImageView pictureView;
        public ImageView dreamImage;

        public RelativeLayout forbackgroundrelativeLayout;
        View view1;
           View view2;
        //////////public ImageView imageYoutube;


        Context context;


        public EntryViewHolder(View itemView, EntryListAdapter adapter) {
            super(itemView);
            entryItemView = (TextView) itemView.findViewById(R.id.diary_entry);
            tvdatetime = itemView.findViewById(R.id.dateofday);
            imageHeart = itemView.findViewById(R.id.favoritesImage);
            imageLocation = itemView.findViewById(R.id.locationImage);
            locationTv = itemView.findViewById(R.id.diary_location);
            imageMood = itemView.findViewById(R.id.moodImage);
            pictureView = itemView.findViewById(R.id.imageViewPicture);
            dreamImage = itemView.findViewById(R.id.dreamImage);

            //////////   imageYoutube = itemView.findViewById(R.id.YoutubeImage);
            forbackgroundrelativeLayout= itemView.findViewById(R.id.forbackgroundrelativeLayout);


            view1= itemView.findViewById(R.id.view1);
            view2= itemView.findViewById(R.id.view2);

            this.mAdapter = adapter;
            itemView.setOnCreateContextMenuListener(this);

          choosenPos=getAdapterPosition();







         /*   final Cursor cursor = mContext.getContentResolver().query(Uri.parse(
                    queryUri), null, null, null, sortOrder);


         pictureView.setOnClickListener(new MyButtonOnClickListener( id, nickdate, word, heart, location,location_image, mood_image, picture, dream_image){

             public void onClick(View v) {

                 if (cursor != null) {
                     if (cursor.moveToPosition(getAdapterPosition())) {

                         int indexPicture = cursor.getColumnIndex(Contract.WordList.KEY_PICTURE);
                         picture = cursor.getString(indexPicture);
                     }
                     Intent intent = new Intent(mContext, BigImage.class);
                     intent.putExtra("image_url", picture);
                     mContext.startActivity(intent);

                 }
             }
        });




*/









///////// bu şekilde de çalışıyor ama şu anlık ihtiyacım yok gibi. eğer hızda sorun olursa recyclerviewin bu (önerilen) sistme çevir aynı şekilde
            /*entryItemView.setOnClickListener(new MyButtonOnClickListener( id, nickdate, word, heart, location,location_image, mood_image) {
                @Override
                public void onClick(View v) {


                    Intent i3= new Intent(mContext , EditActivity.class);
                    i3.putExtra("entryIs", word);

                    // Toast.makeText(mContext, "dsfdsf sdfsdf sdf " + word, Toast.LENGTH_SHORT).show();
                    i3.putExtra("idIs", id);
                    i3.putExtra("locationIs", location);
                    i3.putExtra("heartIs", heart);
                    i3.putExtra("locationImageIs", location_image);
                    i3.putExtra("moodImageIs", mood);



                    // notifyDataSetChanged();

                    Toast.makeText(mContext, "moodwas is: " + mood, Toast.LENGTH_SHORT).show();
                    ///    Toast.makeText(mContext, "heartwas is: " + heart, Toast.LENGTH_SHORT).show();


                    ( mContext) .startActivity(i3);
                }
            });
*/







           /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "sssssssdfgdfgsssss", Toast.LENGTH_LONG).show();
int pos =getAdapterPosition();
Cursor c=WordListOpenHelper.mReadableDB.rawQuery("SELECT * FROM diary", null);
                    c.moveToPosition(pos);
                    Entry_Model entry_model= new Entry_Model();
                    entryToNotification=entry_model.getmEntry();
            }
        });
*/
//bu çalışıyor sadece tıklamayla ama contextmenu yü üptal edersek tabi bi manası da kalmıyor seçemeyeceğmiz için share remind me yi falan:/


            //onlongclick listener oluşturma dolaylı yoldan.. normalde recycleviewer a direk clicklistener uygulanamıyordu
            //biz de niyeyse viewholder on constructoreına yazarak birşekilde atlatmış olduk bunu
            //BU UYG. da seçilen entryi kopyalayıp reminder zamanı gelince ekrana yapıştırmak için kullandık..)

            //ÇOK İYİ.. Onlongclick listenerda contextmenu da uzun tıklayınca çalıştığı için ikisini birden tıklattık
            //biri (onlongclicklistener) değeri almak için diğeri zaten menüyü göstermek için (mainactivity de de operasyonları var oncreatexcontextin.. tek bu sayfada da yapılabilirdi sanırım ama oldu birk kere:))
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    try{
                    int pos = getAdapterPosition();
                    //çç WordListOpenHelper wlo=new WordListOpenHelper(v.getContext());
                    //çç  entryToNotification =wlo.query(pos).getmEntry();


                    String word = "";
                    //String nickdate="";
                    int id = -1;
                    String[] mProjection =
                            {Contract.WordList.KEY_ENTRY,    // Contract class constant for the _ID column name
                            };


                    Cursor cursor = mContext.getContentResolver().query(Uri.parse(
                            queryUri), mProjection, null, null, sortOrder);

                    if (cursor != null) {
                        if (cursor.moveToPosition(pos)) {
                            int indexWord = cursor.getColumnIndex(Contract.WordList.KEY_ENTRY);
                            word = cursor.getString(indexWord);
                            int choosenId = cursor.getColumnIndex(Contract.WordList.KEY_ID);
                            id = cursor.getInt(choosenId);

                        }
                    }


                    tobeDeletedId = id;
                    entryToNotification = word;
                    ///çç  Toast.makeText(v.getContext(), entryToNotification, Toast.LENGTH_LONG).show();


                    // ContentValues values = new ContentValues();
                    // values.put(KEY_ENTRY, entryToNotification);
                    // values.put(KEY_NICKNAME_DATE, "@" + Constants.nickName + " ・" + "12121212121232323232" );


                    //////////////////////////////// /////////////////
                    // WordListOpenHelper2 woh2= new WordListOpenHelper2(v.getContext());
                    // woh2.insert(entryToNotification);
                    // FavoritesActivity.mAdapter2.notifyDataSetChanged();


                }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        return false;
                    }
                    return false;
                }
            });










           /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     int pos =getAdapterPosition();
                      // Toast.makeText(v.getContext(), pos + "", Toast.LENGTH_LONG).show();

                    //if(pos != RecyclerView.NO_POSITION){
                    ///ç   String clickedDataItem = mEntryList.get(pos);

                    WordListOpenHelper wlo=new WordListOpenHelper(v.getContext());
                    entryToNotification =wlo.query(pos).getmEntry();
                    Toast.makeText(v.getContext(), entryToNotification, Toast.LENGTH_LONG).show();

                   *//* Cursor c = WordListOpenHelper.mReadableDB.rawQuery("SELECT * FROM " + WordListOpenHelper.DIARY_LIST_TABLE , null);
                    c.moveToPosition(pos);
                    Entry_Model entry_model = new Entry_Model();
                    entryToNotification = entry_model.getmEntry();*//*



                     //Toast.makeText(v.getContext(), "You clicked " + entryToNotification , Toast.LENGTH_SHORT).show();
                    //}
                    //ç entryToNotification=clickedDataItem;

                }


            });*/


        }






        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle(R.string.Choices);
            menu.setHeaderIcon(R.drawable.share31);

            menu.add(0, R.id.idshare, 0, R.string.share);//groupId, itemId, order, title
           // menu.add(0, R.id.remindMyself, 0, "Remind me");
            menu.add(0, R.id.delete, 0, R.string.delete);
        }



    }





  /*  public class TodayViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public final TextView todayItemView;
        public ImageView todayAddImage;


        //   public RelativeLayout forbackgroundrelativeLayout;
        //  View view1;
        // View view2;

        Context context;

        public TodayViewHolder(View itemView, EntryListAdapter adapter) {
            super(itemView);
            todayItemView = (TextView) itemView.findViewById(R.id.today_item);
            todayAddImage = itemView.findViewById(R.id.todayImageView12);

            // forbackgroundrelativeLayout= itemView.findViewById(R.id.forbackgroundrelativeLayout);


            // view1= itemView.findViewById(R.id.view1);
            //  view2= itemView.findViewById(R.id.view2);

            itemView.setOnCreateContextMenuListener(this);

            choosenPos = getAdapterPosition();


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    try {
                        int pos = getAdapterPosition();
                        //çç WordListOpenHelper wlo=new WordListOpenHelper(v.getContext());
                        //çç  entryToNotification =wlo.query(pos).getmEntry();


                        String word = "";
                        //String nickdate="";
                        int id = -1;
                        String[] mProjection =
                                {Contract.WordList.KEY_ENTRY,    // Contract class constant for the _ID column name
                                };


                        Cursor cursor = mContext.getContentResolver().query(Uri.parse(
                                queryUri), mProjection, null, null, sortOrder);

                        if (cursor != null) {
                            if (cursor.moveToPosition(pos)) {
                                int indexWord = cursor.getColumnIndex(Contract.WordList.KEY_ENTRY);
                                word = cursor.getString(indexWord);
                                int choosenId = cursor.getColumnIndex(Contract.WordList.KEY_ID);
                                id = cursor.getInt(choosenId);

                            }
                        }


                        tobeDeletedId = id;
                        entryToNotification = word;


                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return false;
                    }
                    return false;
                }
            });


        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }*/




}
