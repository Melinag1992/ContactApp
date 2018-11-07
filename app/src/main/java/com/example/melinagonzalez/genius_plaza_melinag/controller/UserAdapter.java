package com.example.melinagonzalez.genius_plaza_melinag.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.melinagonzalez.genius_plaza_melinag.R;
import com.example.melinagonzalez.genius_plaza_melinag.model.Data;
import com.example.melinagonzalez.genius_plaza_melinag.view.UserViewHolder;


import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<Data> userList = new ArrayList<>();
    private Context context;

    public UserAdapter(List<Data> userList, Context applicationContext) {
        this.context = applicationContext;
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist_item_view, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        Data personData = userList.get(position);
        holder.onBind(personData);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setList(List<Data> list) {
        userList = list;
        notifyDataSetChanged();
    }

    public void addAll(List<Data> newList) {
        int lastIndex = userList.size() - 1;
        userList.addAll(newList);
        notifyItemRangeInserted(lastIndex, newList.size());
    }
}
