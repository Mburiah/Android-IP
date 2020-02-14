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
     public static RickandmortyApi getClient(){
         if (retrofit == null){
             OkHttpClient okHttpClient= new OkHttpClient.Builder()
                     .addInterceptor(new Interceptor() {
                         @Override
                         public Response intercept(Chain chain) throws IOException {
                             Request newRequest = chain.request().newBuilder()
                                     .build();
                             return chain.proceed(newRequest);
                         }
                     })
                     .build();
             retrofit = new Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .client(okHttpClient)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
         }
         return retrofit.create(RickandmortyApi.class);
     }
}
