package com.kilic.tweetydiary;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class SearchListAdapter extends
RecyclerView.Adapter<SearchListAdapter.SearchViewHolder>  {


   //public TextView searchItemViewEntry;
    //public  TextView searchItemViewDateNickname;
    public static SearchListAdapter mAdapter;
    public LinkedList<Entry_Model> mWordList;
    public LayoutInflater mInflater;
    private String TEXTCOLORIS;
    private int color;
Context context;

    public SearchListAdapter(Context context, LinkedList<Entry_Model> wordList) {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }





    @Override
    public SearchListAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.searchlist_item, parent, false);

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



        return new SearchViewHolder(mItemView, this);
    }









    @Override
    public void onBindViewHolder(@NonNull SearchListAdapter.SearchViewHolder holder, int position) {
        String mSearchEntry = mWordList.get(position).getmEntry();
        holder.searchItemViewEntry.setText(mSearchEntry);
        holder.searchItemViewEntry.setTextColor(color);


        String mSearchDateNickname= mWordList.get(position).getmNickname_date();
        holder.searchItemViewDateNickname.setText(mSearchDateNickname);


        String mSearchDateLocation= mWordList.get(position).getmLocation();
        holder.searchItemViewLocation.setText(mSearchDateLocation);



        /*int mSearchMood= mWordList.get(position).getmMood();
       // holder.searchItemViewMood.setImageResource(R.drawable.mSearchMood);


        if (mSearchMood==1)
            holder.searchItemViewMood.setImageResource(R.drawable.one);
        else if (mSearchMood==2)
            holder.searchItemViewMood.setImageResource(R.drawable.two);
        else if (mSearchMood==3)
            holder.searchItemViewMood.setImageResource(R.drawable.three);
        else if (mSearchMood==4)
            holder.searchItemViewMood.setImageResource(R.drawable.four);
        else if (mSearchMood==5)
            holder.searchItemViewMood.setImageResource(R.drawable.five);
        else  {
            holder.searchItemViewMood.setImageResource(R.drawable.blank);
        }
*/








    }




    @Override
    public int getItemCount() {
        return mWordList.size();
    }



    public class SearchViewHolder extends RecyclerView.ViewHolder {
        public TextView searchItemViewEntry; //dikkat . en üstte girince nedense onbindviewholder tanımyıor!! o yüzden burda girmek lazım nedense
        public TextView searchItemViewDateNickname;
        public TextView searchItemViewLocation;
        public ImageView searchItemViewMood;


        public SearchViewHolder(View itemView, SearchListAdapter adapter) {
            super(itemView);
            searchItemViewEntry = (TextView) itemView.findViewById(R.id.search_entry);
            searchItemViewDateNickname = itemView.findViewById(R.id.searchDATE_NICKNAME);
            searchItemViewLocation = itemView.findViewById(R.id.search_location);
         ///////////   searchItemViewMood= itemView.findViewById(R.id.search_mood);
            mAdapter = adapter;
        }
    }




}
