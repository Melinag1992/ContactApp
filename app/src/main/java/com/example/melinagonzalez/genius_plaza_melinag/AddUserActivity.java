package com.example.melinagonzalez.genius_plaza_melinag;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.melinagonzalez.genius_plaza_melinag.backend.ReqResService;
import com.example.melinagonzalez.genius_plaza_melinag.model.Person;
import com.example.melinagonzalez.genius_plaza_melinag.retrofit.RetrofitSingleInstance;

import butterknife.BindView;
import retrofit2.Call;

public class AddUserActivity extends AppCompatActivity {
    @BindView(R.id.last_name_au)
    private EditText lastnameET;

    @BindView(R.id.first_name_au)
    private EditText firstnameET;

    @BindView(R.id.job)
    private EditText jobET;

    private String firstNameParsed;
    private String lastNameParsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adduserlayout);
    }

    public void postUser(){

        Person newCreatedUser = new Person();
//        newCreatedUser.set_name(newUserNameEV.getText());
//        newCreatedUser.setJob(JobET.getText());

        Call<Person> postUserCall = RetrofitSingleInstance.getRetrofitSingleInstance().create(ReqResService.class).createNewUser(newCreatedUser);

    }

    public String parseUserName(String firstName, String lastName){
        firstNameParsed = firstName.substring(0, 1).toUpperCase().concat(firstName.substring(1));
        lastNameParsed = lastName.substring(0, 1).toUpperCase().concat(lastName.substring(1));

        return firstNameParsed + " " + lastNameParsed;
    }
}
