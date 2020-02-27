package com.example.rickmorty.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rickmorty.adapters.CharactersListAdapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rickmorty.R;
import com.example.rickmorty.models.characters.Result;
import com.example.rickmorty.ui.sanchezmainModel.CharacterDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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

    public class CharactersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.characterNameTextView) TextView mName;
        @BindView(R.id.statusTextView) TextView mStatus;
        @BindView(R.id.speciesTextView) TextView mSpecies;
        @BindView(R.id.imgView) ImageView mImage;
        @BindView(R.id.genderTextView) TextView mGender;
//        @BindView(R.id.originTextView) TextView Origin;
//        @BindView(R.id.IDTextView) TextView mID;
//        @BindView(R.id.locationView) TextView mLocation;
//        @BindView(R.id.typeTextView) TextView mType;

//        private Context context;

        public CharactersViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            Intent intent= new Intent(mContext, CharacterDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("characters", Parcels.wrap(results));
            mContext.startActivity(intent);
        }

        public void bindCharacter (Result characters){
            Log.d(TAG, "bindCharacter: bind char...");

            Picasso.get().load(characters.getImage()).into(mImage);

//            mID.setText(characters.getId());
            mName.setText("Name:" + " " + characters.getName());
            mStatus.setText("Status:" + " " + characters.getStatus());
            mSpecies.setText("Species:" + " " +characters.getSpecies());
//            mType.setText(characters.getType());
            mGender.setText("Gender:" + " " + characters.getGender());
//            Origin.setText(characters.getName());
//            mLocation.setText(characters.getName());
            //mImage.setImage(characters.getImage());


        }

    }

}
