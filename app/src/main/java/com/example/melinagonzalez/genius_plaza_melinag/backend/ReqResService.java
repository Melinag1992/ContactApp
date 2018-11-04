package com.example.melinagonzalez.genius_plaza_melinag.backend;

import com.example.melinagonzalez.genius_plaza_melinag.model.Person;
import com.example.melinagonzalez.genius_plaza_melinag.model.UserObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReqResService {
    @GET("api/users")
        Call<UserObject> getUsers();

    @POST("api/users")
    Call<Person>createCandidate(@Body Person postNewUser);


}
