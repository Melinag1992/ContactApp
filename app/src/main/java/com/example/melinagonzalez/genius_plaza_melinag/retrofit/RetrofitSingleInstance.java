package com.example.melinagonzalez.genius_plaza_melinag.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleInstance {

    private static Retrofit retrofitSingleInstance;

    public static Retrofit getRetrofitSingleInstance(){
        if(retrofitSingleInstance == null){
            retrofitSingleInstance = new Retrofit.Builder()
                    .baseUrl("https://reqres.in/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitSingleInstance;
    }

    private RetrofitSingleInstance (){

    }


}
