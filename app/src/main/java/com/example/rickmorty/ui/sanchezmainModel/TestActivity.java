package com.example.rickmorty.ui.sanchezmainModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.rickmorty.Constants;
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
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentCharacter;
    private static final String TAG = "TestActivity";


    private CharactersListAdapter mAdapter;
    List<Result> resultsList;


    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.locationButton) Button mLocationButton;
    //    @BindView(R.id.name) TextView mName;
//    @BindView(R.id.error) TextView mError;
//    @BindView(R.id.progressBar) ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        Log.d(TAG, "onCreate: called");

        Intent intent = getIntent();
        String character = intent.getStringExtra("character");

//        getResult(character);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(TestActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
       mRecentCharacter = mSharedPreferences.getString(Constants.PREFERENCES_CHARACTER_KEY, null);
        //Log.d("Shared Pref Character", mRecentCharacter);

        if (mRecentCharacter != null){
//            getResult(mRecentCharacter);
        }

        RickandmortyApi service = RickandmortyClient.getClient().create(RickandmortyApi.class);
        Call<Response> call = service.getInformation();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Log.v("Info",response.body().getResults().get(0).getName().toString());
                    resultsList = response.body().getResults();
                    //Log.d(TAG, "onResponse: "+response.body().getResults().get(0).getName());
                    mAdapter = new CharactersListAdapter(TestActivity.this, resultsList);
                    mRecyclerView.setAdapter(mAdapter);
                    }
                }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
//                Log.e(TAG, "onFailure: ", t);
//                hideProgressBar();
            }
        });

        mLocationButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(TestActivity.this, CharacterDetailActivity.class);
            startActivity(intent1);
        });
    }
    private void addToSharedPreferences(String character) {
        mEditor.putString(Constants.PREFERENCES_CHARACTER_KEY, character).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem =menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
//                getResult(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
