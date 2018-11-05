package com.example.melinagonzalez.genius_plaza_melinag;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.melinagonzalez.genius_plaza_melinag.backend.ReqResService;
import com.example.melinagonzalez.genius_plaza_melinag.model.Person;
import com.example.melinagonzalez.genius_plaza_melinag.retrofit.RetrofitSingleInstance;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity {
    @BindView(R.id.last_name_au)
     EditText lastnameET;

    @BindView(R.id.first_name_au)
     EditText firstnameET;

    @BindView(R.id.job)
     EditText jobET;


    @BindView(R.id.save_button)
     Button saveButton;

    private String firstNameParsed;
    private String lastNameParsed;
    private String TAG = "Post user retrofit Call";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adduserlayout);
        ButterKnife.bind(this);



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(lastnameET.getText() != null && firstnameET.getText() != null ){
                 postUser();

                    Toast.makeText(AddUserActivity.this, "New User Added", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(AddUserActivity.this, MainActivity.class));
            }
        });

    }

    public void postUser(){

        Person newCreatedUser = new Person();
        newCreatedUser.set_name(parseUserName(firstnameET.getText().toString(), lastnameET.getText().toString()));
        newCreatedUser.setJob(jobET.getText().toString());

        Call<Person> postUserCall = RetrofitSingleInstance.getRetrofitSingleInstance().create(ReqResService.class).createNewUser(newCreatedUser);

        postUserCall.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });

    }

    public String parseUserName(String firstName, String lastName){
        firstNameParsed = firstName.substring(0, 1).toUpperCase().concat(firstName.substring(1));
        lastNameParsed = lastName.substring(0, 1).toUpperCase().concat(lastName.substring(1));

        return firstNameParsed + " " + lastNameParsed;
    }
}
