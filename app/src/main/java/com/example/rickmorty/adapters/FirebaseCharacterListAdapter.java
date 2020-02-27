package com.example.rickmorty.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickmorty.R;
import com.example.rickmorty.models.characters.Result;
import com.example.rickmorty.ui.sanchezmainModel.CharacterDetailActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

import util.ItemTouchHelperAdapter;
import util.OnStartDragListener;

public class FirebaseCharacterListAdapter extends FirebaseRecyclerAdapter<Result, FirebaseCharacterViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private Result model;
    private FirebaseCharacterViewHolder firebaseCharacterViewHolder;
    private Result character;

    private ChildEventListener mChildEventListener;
    private ArrayList<Result> mResult = new ArrayList<>();

    @Override
    protected void onBindViewHolder(@NonNull FirebaseCharacterViewHolder holder, int position, @NonNull Result model) {
        firebaseCharacterViewHolder.bindCharacter(character);
        holder.bindCharacter(model);
    }

    public FirebaseCharacterListAdapter(FirebaseRecyclerOptions<Result> options, Query ref, OnStartDragListener onStartDragListener, Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener =onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mResult.add(dataSnapshot.getValue(Result.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    @Override
    protected void onBindViewHolder(@NonNull final FirebaseCharacterViewHolder viewHolder, Result model, int position, @NonNull Result character){
        viewHolder.bindCharacter(model);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (mContext, CharacterDetailActivity.class);
//                Intent.putExtra("position", viewHolder.getAdapterPosition());
//                Intent.putExtra("characters", Parcels.wrap(mResult));
                mContext.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public FirebaseCharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_list_item_drag, parent, false);
        return new FirebaseCharacterViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        Collections.swap(mResult, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        setIndexInFirebase();
        return false;
    }

    @Override
    public void onItemDismiss(int position){
        mResult.remove(position);
        getRef(position).removeValue();
    }
    @Override
    public void stopListening(){
        super.stopListening();
        mRef.removeEventListener(mChildEventListener);
    }

    private void setIndexInFirebase(){
        for(Result result: mResult){
            int index = mResult.indexOf(result);
            DatabaseReference ref = getRef(index);
            result.setIndex(Integer.toString(index));
            ref.setValue(result);
        }
    }
}
