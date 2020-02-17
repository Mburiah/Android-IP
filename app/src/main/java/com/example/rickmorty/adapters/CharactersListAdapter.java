package com.example.rickmorty.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rickmorty.R;
import com.example.rickmorty.models.characters.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class CharactersListAdapter extends RecyclerView.Adapter<CharactersListAdapter.CharactersViewHolder> {
    private List<Result> results;
    private Context mContext;

    public CharactersListAdapter(Context context, List<Result> characters) {
        mContext = context;
        results = characters;
    }

    @Override
    public CharactersListAdapter.CharactersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.characters_list, parent, false);
        Log.d(TAG, "onCreateViewHolder: creating view....");
        CharactersViewHolder viewHolder = new CharactersViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CharactersListAdapter.CharactersViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: binding view... " );
        holder.bindCharacter(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class CharactersViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.IDTextView) TextView mID;
        @BindView(R.id.characterNameTextView) TextView mName;
        @BindView(R.id.statusTextView) TextView mStatus;
        @BindView(R.id.speciesTextView) TextView mSpecies;
        @BindView(R.id.typeTextView) TextView mType;
        @BindView(R.id.genderTextView) TextView mGender;
        @BindView(R.id.originTextView) TextView Origin;
        @BindView(R.id.locationView) TextView mLocation;
        @BindView(R.id.imageTextView) TextView mImage;

//        private Context context;

        public CharactersViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindCharacter (Result characters){
            Log.d(TAG, "bindCharacter: bind char...");
            mID.setText(characters.getName());

            mName.setText(characters.getName());
            mStatus.setText(characters.getStatus());
            mSpecies.setText(characters.getStatus());
            mType.setText(characters.getType());
            mGender.setText(characters.getGender());
            Origin.setText(characters.getName());
            mLocation.setText(characters.getName());
            mImage.setText(characters.getImage());
        }

    }

}
