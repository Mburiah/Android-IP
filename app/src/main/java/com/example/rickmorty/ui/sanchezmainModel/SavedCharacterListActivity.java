package com.example.rickmorty.ui.sanchezmainModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickmorty.Constants;
import com.example.rickmorty.R;
import com.example.rickmorty.adapters.FirebaseCharacterViewHolder;
import com.example.rickmorty.models.characters.Result;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedCharacterListActivity extends AppCompatActivity {
    private DatabaseReference mCharacterReference;
    private FirebaseRecyclerAdapter<Result, FirebaseCharacterViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        mCharacterReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CHARACTERS);
        setUpFirebaseAdapter();

    }
    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Result> options = new FirebaseRecyclerOptions.Builder<Result>()
                .setQuery(mCharacterReference, Result.class)
                .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Result, FirebaseCharacterViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseCharacterViewHolder holder, int position, @NonNull Result model) {
                FirebaseCharacterViewHolder.bindCharacter(model);
            }
            @NonNull
            @Override
            public FirebaseCharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.characters_list,parent,false);
                return new FirebaseCharacterViewHolder(view);
            }
        };

        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(mFirebaseAdapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        mFirebaseAdapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        if(mFirebaseAdapter!=null){
            mFirebaseAdapter.stopListening();
        }
    }
    }

}
