package com.example.rickmorty.network;
import com.example.rickmorty.models.characters.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RickandmortyApi {
    @GET("character")
    Call<Response> getInformation();
}
