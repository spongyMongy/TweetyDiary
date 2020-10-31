

package com.kilic.tweetydiary;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class  FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.FavoritesViewHolder> {
    private String queryUri = Contract.CONTENT_URI.toString(); // base uri
    private String sortOrder = "ASC";
    private String TEXTCOLORIS;
    private int color;
    //ç private  ArrayList<String> mEntryList;
    private LayoutInflater mInflater;
    Context context;
    public static String entryToNotification2;
    public EditText tvnickName;
    public static String nickName;
    int counter = 0;
    WordListOpenHelper2 mDB2;
    static int pos;
    static String clickedEntry;
    SharedPreferences mPreferences;

/*  ///çç
    public EntryListAdapter(Context context, ArrayList<String> entryList) {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        this.mEntryList = entryList;

    }*/

    public FavoritesListAdapter(Context context, WordListOpenHelper2 db) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        mDB2 = db;
    }


    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.favoriteslist_item, parent, false);
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(parent.getContext());


        if (mPreferences.contains(TEXTCOLORIS)){
            try{
                color = mPreferences.getInt(TEXTCOLORIS,R.color.newEntryText);

            }
            catch(Exception ex){ex.printStackTrace();}

        }
        else{color= context.getResources().getColor(R.color.colorEntry);

            //  Toast.makeText(mItemView.getContext(), "aaaaaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
        }


        return new FavoritesViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder holder, int position) {
        ///ç  String mCurrent = mEntryList.get(position);
        ///ç  holder.entryItemView.setText(mCurrent);
        final FavoritesViewHolder h= holder;

        final Entry_Model current = mDB2.query(position);


        holder.entryItemView.setText(current.getmEntry());
        holder.entryItemView.setTextColor(color);

        holder.tvdatetime.setText(current.getmNickname_date());
        holder.location.setText(current.getmLocation());


        String entrySituation=current.getmEntry();
        int idSituation= current.getmId();

         final int heartSituation= current.getmHeart();
        if (heartSituation==0)
        holder.imageHeart.setImageResource(R.drawable.like);

        else if (heartSituation==1)
            holder.imageHeart.setImageResource(R.drawable.heart);



        ///////////////////////////  ///////////////////////////  ///////////////////////////

        final int moodSituation= current.getmMood();
        if (moodSituation==1)
            holder.favoritesMoodImage.setImageResource(R.drawable.one);
        else if (moodSituation==2)
            holder.favoritesMoodImage.setImageResource(R.drawable.two);
        else if (moodSituation==3)
            holder.favoritesMoodImage.setImageResource(R.drawable.three);
       else if (moodSituation==4)
            holder.favoritesMoodImage.setImageResource(R.drawable.four);
       else if (moodSituation==5)
            holder.favoritesMoodImage.setImageResource(R.drawable.five);
        else  {
            holder.favoritesMoodImage.setImageResource(R.drawable.blank);
        }





        holder.imageHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

try{
                //unnecessary here
                /* if (heartSituation==0){
                    h.imageHeart.setImageResource(R.drawable.heart);
                    current.setmHeart(1);
                }*/

                if (heartSituation == 1) {
                    h.imageHeart.setImageResource(R.drawable.like);
                    current.setmHeart(0);
                    WordListOpenHelper2 woh2 = new WordListOpenHelper2(context);
                    woh2.delete(current.getmId());

                    WordListOpenHelper woh = new WordListOpenHelper(context);
                    woh.update2(current.getmId(), 0);

                }
                // WordListOpenHelper2 woh2= new WordListOpenHelper2(context);
                // woh2.update(current.getmId(), current.getmHeart());
                notifyDataSetChanged();
               //////////// MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();
            }

                  catch (Exception ex){ex.printStackTrace();}

            }
        });


    }

    @Override
    public int getItemCount() {
        return (int) mDB2.count();
    }


    public class FavoritesViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public final TextView entryItemView;
        final FavoritesListAdapter mAdapter;
        public TextView tvdatetime;
        public ImageView imageHeart;
        public TextView location;
        public ImageView favoritesMoodImage;
        Context context;


        public FavoritesViewHolder(View itemView, FavoritesListAdapter adapter) {
            super(itemView);
            entryItemView = (TextView) itemView.findViewById(R.id.favorites_entry);
            tvdatetime = itemView.findViewById(R.id.favoritesDATE_NICKNAME);
            imageHeart = itemView.findViewById(R.id.favoritesImage);
            location=itemView.findViewById(R.id.favorites_location);
            favoritesMoodImage= itemView.findViewById(R.id.favoritesMoodImage);
            this.mAdapter = adapter;
            itemView.setOnCreateContextMenuListener(this);



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




            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos =getAdapterPosition();


                   // Cursor cursor = context.getContentResolver().query(Uri.parse(
                     //       queryUri), mProjection, null, null, sortOrder);
                    WordListOpenHelper2 wh2= new WordListOpenHelper2(context);
                 //   Cursor c2=WordListOpenHelper2.query("SELECT * FROM " + FAVORITES_LIST_TABLE, null);
                    Entry_Model entry_model2= new Entry_Model();

                    entry_model2=wh2.query(pos);
                    entryToNotification2=entry_model2.getmEntry();


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

        }



    }
}
