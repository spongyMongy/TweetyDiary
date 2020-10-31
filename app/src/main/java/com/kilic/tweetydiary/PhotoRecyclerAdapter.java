package com.kilic.tweetydiary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kc.unsplash.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class PhotoRecyclerAdapter extends RecyclerView.Adapter<PhotoRecyclerAdapter.ViewHolder> {

    private final List<Photo> photoList;
    private Context mContext;

    public PhotoRecyclerAdapter(List<Photo> photos, Context context) {
        photoList = photos;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Random r = new Random();
        int randomNum= r.nextInt((photoList.size()+1 )) ;

        Photo photo = photoList.get(position);

        Picasso.with(mContext).load(photo.getUrls().getRegular())
               // .resize(1200, 800)

                .placeholder(R.mipmap.ic_launcher)
                        //.fit()
                //.centerCrop()
                .into(holder.imageView);



               // .placeholder(R.drawable.ic_default)


    }

    @Override
    public int getItemCount() {
         return photoList.size();
     //   return 1;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }

    }




}
