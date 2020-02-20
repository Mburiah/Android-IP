package com.example.rickmorty.ui.sanchezmainModel;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rickmorty.models.characters.Result;

import com.example.rickmorty.R;
import com.example.rickmorty.models.characters.Result;
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

    @BindView(R.id.fragCharNameTextView) TextView mFragchar;
    @BindView(R.id.fragGenderTextView) TextView mFragGen;
    @BindView(R.id.fragStatusTextView) TextView mFragStatus;
    @BindView(R.id.fragIconImageView) ImageView mFragicon;
    @BindView(R.id.favoriteTextView) TextView mFavorite;

    private Result mCharacter;

    public CharacterDetailFragment() {
        // Required empty public constructor
    }

    public static CharacterDetailFragment newInstance(Result character){
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
        View view = inflater.inflate(R.layout.fragment_character_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mCharacter.getImage()).into(mFragicon);

        List<String> characters = new ArrayList<>();

//        for (Character character: mCharacter.getcharacters()){
//            characters.add(character.getName());
//        }
        mFragchar.setText(mCharacter.getName());
        mFragGen.setText(mCharacter.getName());
        mFragStatus.setText(mCharacter.getStatus());

        mFavorite.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        if (v == mFavorite) {

            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mCharacter.getUrl()));
        }
    }

}
