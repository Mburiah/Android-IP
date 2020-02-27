package com.example.rickmorty.ui.sanchezmainModel;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rickmorty.Constants;
import com.example.rickmorty.models.characters.Result;

import com.example.rickmorty.R;
import com.example.rickmorty.models.characters.Result;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterDetailFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "CharacterDetailFragment";

    @BindView(R.id.fragCharNameTextView) TextView mFragchar;
    @BindView(R.id.fragGenderTextView) TextView mFragGen;
    @BindView(R.id.fragStatusTextView) TextView mFragStatus;
    @BindView(R.id.fragIconImageView) ImageView mFragicon;
    @BindView(R.id.favoriteTextView) TextView mFavorite;
    @BindView(R.id.saveCharacterButton) Button mSaveCharacter;
    private Result mCharacter;

    public CharacterDetailFragment() {
        // Required empty public constructor
    }

    public static CharacterDetailFragment newInstance(Result character){

        Log.d(TAG, "newInstance: instantiated");
        
        CharacterDetailFragment characterDetailFragment = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("characters", Parcels.wrap(character));
        characterDetailFragment.setArguments(args);

        return characterDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mCharacter = Parcels.unwrap(getArguments().getParcelable("character"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d(TAG, "onCreateView from fragment: called");
        View view = inflater.inflate(R.layout.fragment_character_detail, container, false);
        ButterKnife.bind(this, view);

        Log.d(TAG, "onCreateView: image url" + mCharacter.getImage());

        Picasso.get().load(mCharacter.getImage()).into(mFragicon);

//        List<String> characters = new ArrayList<>();
//        for (Character character: mCharacter.getcharacters()){
//            characters.add(character.getName());
//        }


        mFragchar.setText(mCharacter.getName());
        mFragGen.setText(mCharacter.getName());
        mFragStatus.setText(mCharacter.getStatus());

        mSaveCharacter.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v){
        if (v == mSaveCharacter) {
            DatabaseReference characterRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_CHARACTERS);
            characterRef.push().setValue(mCharacter);
            Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mCharacter.getUrl()));
        }
    }

}
