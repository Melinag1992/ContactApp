package com.example.melinagonzalez.genius_plaza_melinag.view;

import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.melinagonzalez.genius_plaza_melinag.R;
import com.example.melinagonzalez.genius_plaza_melinag.model.Data;



import de.hdodenhof.circleimageview.CircleImageView;


public class UserViewHolder extends RecyclerView.ViewHolder {
    private CircleImageView imageview;
    private TextView lastNameTV;
    private TextView firstNameTV;



    public UserViewHolder(View itemView) {
        super(itemView);
        setViews(itemView);
        
    }


    public void onBind(Data personData){
        firstNameTV.setText(personData.getFirst_name());
        lastNameTV.setText(personData.getLast_name());
        Glide.with(itemView.getContext())
                .load(personData.getAvatar())
                .into(imageview);


    }

    public void setViews(View itemView){
        firstNameTV = itemView.findViewById(R.id.first_name);
        lastNameTV =  itemView.findViewById(R.id.last_name);
        imageview = itemView.findViewById(R.id.avatar_imageview);

    }
}
