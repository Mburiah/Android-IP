package com.example.rickmorty.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rickmorty.models.characters.Result;
import com.example.rickmorty.ui.sanchezmainModel.CharacterDetailFragment;

import java.util.List;

public class CharacterPagerAdapter extends FragmentPagerAdapter {
    private List<Result> mCharacters;

    public CharacterPagerAdapter(FragmentManager fm, int behavior, List<Result> characters){
        super(fm, behavior);
        mCharacters = characters;
    }
     @Override
    public Fragment getItem(int position){
        return CharacterDetailFragment.newInstance(mCharacters.get(position));
     }

     @Override
    public  int getCount(){
        return mCharacters.size();
     }

     @Override
    public CharSequence getPageTitle(int position) {
        return mCharacters.get(position).getName();
     }
}
