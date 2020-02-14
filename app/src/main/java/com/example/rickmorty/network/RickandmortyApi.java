package com.example.rickmorty.network;

import com.example.rickmorty.models.RickandmortyApiInformationSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RickandmortyApi {
    @GET("information/search")
    Call<RickandmortyApiInformationSearchResponse> getInformation(
            @Query("characters") String characters,
            @Query("locations") String locations,
            @Query("episodes") String episodes
    );
}
