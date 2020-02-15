package com.example.rickmorty.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
