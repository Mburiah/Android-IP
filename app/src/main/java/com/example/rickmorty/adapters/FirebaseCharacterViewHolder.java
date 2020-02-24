package com.example.rickmorty.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickmorty.Constants;
import com.example.rickmorty.R;
import com.example.rickmorty.models.characters.Result;
import com.example.rickmorty.ui.sanchezmainModel.CharacterDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseCharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseCharacterViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindCharacter(Result character) {
        ImageView mImggView = (ImageView) mView.findViewById(R.id.imgView);
        TextView mcharacterName = (TextView) mView.findViewById(R.id.characterNameTextView);
        TextView mStatus = (TextView) mView.findViewById(R.id.statusTextView);
        TextView mSpecies = (TextView) mView.findViewById(R.id.speciesTextView);
        TextView mGenderr = (TextView) mView.findViewById(R.id.genderTextView);

        Picasso.get().load(character.getImage()).into(mImggView);

        mcharacterName.setText(character.getName());
        mStatus.setText(character.getStatus());
        mSpecies.setText(character.getSpecies());
        mGenderr.setText(character.getGender());
    }

    @Override
    public void onClick(View view){
        final ArrayList<Result> characters = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CHARACTERS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    characters.add(snapshot.getValue(Result.class));
                }
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, CharacterDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("characters", Parcels.wrap(characters));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
