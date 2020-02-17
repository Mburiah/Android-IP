package com.example.rickmorty.ui.sanchezmainModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rickmorty.R;

import com.example.rickmorty.models.characters.Response;
import com.example.rickmorty.models.characters.Result;
import com.example.rickmorty.network.RickandmortyApi;
import com.example.rickmorty.network.RickandmortyClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class TestActivity extends AppCompatActivity {
    List<Result> results;
    @BindView(R.id.name) TextView mName;
    @BindView(R.id.error) TextView mError;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.locationButton) Button mLocationButton;


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
                    List<Result> resultsList = response.body().getResults();
                    for (Result result : results) {
                        mName.setText(result.getName());
                    }
                } else {
                    mError.setText(response.code());
                }

//                ArrayAdapter adapter = new RickAndMortyArrayAdapter(TestActivity.this, android.R.layout.simple_list_item_1, results);
//                mName.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
//                Log.e(TAG, "onFailure: ", t);
//                hideProgressBar();
            }
        });

        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, NextActivity.class);
                startActivity(intent);
            }
        });

    }

}
