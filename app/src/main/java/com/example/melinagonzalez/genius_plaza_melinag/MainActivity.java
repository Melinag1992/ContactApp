package com.example.melinagonzalez.genius_plaza_melinag;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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
    private FloatingActionButton floatingActionButton;
    private boolean isLoading;
    private boolean isLastPage;
    private boolean isFirstPage;
    private int currentPage = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setBackgroundTintList((getResources().getColorStateList(R.color.geniusWhite)));


        setUpRecyclerView();
        userAdapter.notifyDataSetChanged();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });


        isLoading = false;
        isLastPage = false;

        // amount of items you want to load per page
        final int pageSize = 3;

        // set up scroll listener
        userRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // number of visible items
                int visibleItemCount = linearLayoutManager.getChildCount();
                // number of items in layout
                int totalItemCount = linearLayoutManager.getItemCount();
                // the position of first visible item
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                boolean isNotLoadingAndNotLastPage = !isLoading && !isLastPage;
                // flag if number of visible items is at the last
                boolean isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount;
                // validate non negative values
                boolean isValidFirstItem = firstVisibleItemPosition >= 0;
                // validate total items are more than possible visible items
                boolean totalIsMoreThanVisible = totalItemCount >= pageSize;
                // flag to know whether to load more
                boolean shouldLoadMore = isValidFirstItem && isAtLastItem && totalIsMoreThanVisible && isNotLoadingAndNotLastPage;

                if (shouldLoadMore) getuserlist(false);
            }
        });

        // load the first page
        getuserlist(true);
    }


    public void setUpRecyclerView() {
        userRecyclerView = findViewById(R.id.recyclerview);

        userAdapter = new UserAdapter(userList, getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        userRecyclerView.setAdapter(userAdapter);
        userRecyclerView.setLayoutManager(linearLayoutManager);

    }

    public void getuserlist(final boolean isFirstPage) {

        isLoading = true;
        currentPage = currentPage + 1;

        Call<UserObject> userObjectCall = RetrofitSingleInstance.getRetrofitSingleInstance().create(ReqResService.class).getUsers(currentPage);

        userObjectCall.enqueue(new Callback<UserObject>() {
            @Override
            public void onResponse(Call<UserObject> call, Response<UserObject> response) {


                if (response.body().getData() == null) {
                    return;
                } else if (!isFirstPage) {

                    userAdapter.addAll(response.body().getData());
                } else {
                    userAdapter.setList(response.body().getData());
                }

                isLoading = false;
                isLastPage = currentPage == response.body().getTotal_pages();

            }

            @Override
            public void onFailure(Call<UserObject> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

}
