package com.example.rickmorty.ui.sanchezmainModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rickmorty.R;

import com.example.rickmorty.adapters.CharactersListAdapter;
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
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private CharactersListAdapter mAdapter;
    List<Result> results;
    @BindView(R.id.name) TextView mName;
    @BindView(R.id.error) TextView mError;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.locationButton) Button mLocationButton;
    private static final String TAG = "TestActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(TestActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        RickandmortyApi service = RickandmortyClient.getClient().create(RickandmortyApi.class);
        Call<Response> call = service.getInformation();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Log.v("Info",response.body().getResults().toString());
                    List<Result> resultsList = response.body().getResults();
                    //Log.d(TAG, "onResponse: "+response.body().getResults().get(0).getName());
                    mAdapter = new CharactersListAdapter(TestActivity.this, resultsList);
                    mRecyclerView.setAdapter(mAdapter);
                    }
                }

//                ArrayAdapter adapter = new RickAndMortyArrayAdapter(TestActivity.this, android.R.layout.simple_list_item_1, results);
//                mName.setAdapter(adapter);

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
