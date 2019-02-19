package com.cedesistemas.cameraandgallery;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private ArrayList<String> arrayPhotos;


    public void setPHotos(ArrayList<String> arrayPhotos){
        this.arrayPhotos = arrayPhotos;
    }


    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return arrayPhotos.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        private ImageView imageViewPhoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPhoto = itemView.findViewById(R.id.imageViewPhoto);
        }
    }
}
