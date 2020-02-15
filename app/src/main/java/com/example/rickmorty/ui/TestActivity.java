package com.example.rickmorty.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rickmorty.R;

import com.example.rickmorty.models.characters.Response;
import com.example.rickmorty.models.characters.Result;
import com.example.rickmorty.network.RickandmortyApi;
import com.example.rickmorty.network.RickandmortyClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class TestActivity extends AppCompatActivity {
    List<Result> results;
    @BindView(R.id.name) TextView mName;
    @BindView(R.id.error) TextView mError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        RickandmortyApi service = RickandmortyClient.getClient().create(RickandmortyApi.class);
        Call<Response> call = service.getInformation();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    results = response.body().getResults();
                    for (Result result : results) {
                        mName.setText(result.getName());
                    }
                } else {
                    mError.setText(response.code());
                }
            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

}
