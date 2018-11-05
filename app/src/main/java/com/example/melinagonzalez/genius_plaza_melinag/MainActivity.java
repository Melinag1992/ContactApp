package com.example.melinagonzalez.genius_plaza_melinag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.melinagonzalez.genius_plaza_melinag.backend.ReqResService;
import com.example.melinagonzalez.genius_plaza_melinag.controller.UserAdapter;
import com.example.melinagonzalez.genius_plaza_melinag.model.Data;
import com.example.melinagonzalez.genius_plaza_melinag.model.UserObject;
import com.example.melinagonzalez.genius_plaza_melinag.retrofit.RetrofitSingleInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = "GETTING USER";
    private List<Data> userList = new ArrayList<>();
    private UserAdapter userAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView userRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();
        getuserlist();
    }

    public void setUpRecyclerView() {
        userRecyclerView = findViewById(R.id.recyclerview);

        userAdapter = new UserAdapter(userList, getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        userRecyclerView.setAdapter(userAdapter);
        userRecyclerView.setLayoutManager(linearLayoutManager);

    }

    public void getuserlist() {

        Call<UserObject> userObjectCall = RetrofitSingleInstance.getRetrofitSingleInstance().create(ReqResService.class).getUsers();

        userObjectCall.enqueue(new Callback<UserObject>() {
            @Override
            public void onResponse(Call<UserObject> call, Response<UserObject> response) {
                if (response.isSuccessful()) {
                    userList.addAll(response.body().getData());
                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<UserObject> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

}
