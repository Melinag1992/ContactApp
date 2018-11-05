package com.example.melinagonzalez.genius_plaza_melinag.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.example.melinagonzalez.genius_plaza_melinag.model.Data;



public class UserViewHolder extends RecyclerView.ViewHolder {
    private String TAG;

    public UserViewHolder(View itemView) {
        super(itemView);
        
        
    }


    public void onBind(Data personData){


        Log.d(TAG, "onBind: " + personData.getFirst_name());

    }
}
