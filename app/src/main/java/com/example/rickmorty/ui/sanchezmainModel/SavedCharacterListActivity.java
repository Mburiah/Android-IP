package com.example.rickmorty.ui.sanchezmainModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickmorty.Constants;
import com.example.rickmorty.R;
import com.example.rickmorty.adapters.FirebaseCharacterListAdapter;
import com.example.rickmorty.adapters.FirebaseCharacterViewHolder;
import com.example.rickmorty.models.characters.Result;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import util.OnStartDragListener;
import util.SimpleItemTouchHelperCallback;

public class SavedCharacterListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mCharacterReference;
    private FirebaseCharacterListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
//    private FirebaseRecyclerAdapter<Result, FirebaseCharacterViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        mCharacterReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CHARACTERS);
        setUpFirebaseAdapter();

    }
    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_CHARACTERS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        FirebaseRecyclerOptions<Result> options =
                new FirebaseRecyclerOptions.Builder<Result>()
                        .setQuery(query, Result.class)
                        .build();

        mFirebaseAdapter = new FirebaseCharacterListAdapter(options, mCharacterReference, this, this);
//        mCharacterReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CHARACTERS).child(uid);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(mFirebaseAdapter);
        mRecyclerview.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerview);
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
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        mItemTouchHelper.startDrag(viewHolder);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mFirebaseAdapter.stopListening();
    }
    }

