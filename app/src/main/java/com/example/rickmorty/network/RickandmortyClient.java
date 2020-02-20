package com.example.rickmorty.network;

import java.io.IOException;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RickandmortyClient {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/";

    private static Retrofit retrofit = null;
     public static Retrofit getClient(){
         if (retrofit == null){

             retrofit = new Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
         }
         return retrofit;
     }
}
