package com.example.rickmorty.models.characters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rickmorty.R;
import com.example.rickmorty.ui.sanchezmainModel.SavedCharacterListActivity;
import com.example.rickmorty.ui.sanchezmainModel.TestActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.internal.Constants;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private ValueEventListener mSearchedCharacterReferenceListener;

    private DatabaseReference mSearchedCharacterReference;
    @BindView(R.id.viewMoreButton) Button mViewMoreButton;
    @BindView(R.id.formEdittext) EditText mFormEditText;
    @BindView(R.id.facebookView) TextView mFacebook;
    @BindView(R.id.savedCharactersButton) Button mSavedCharactersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedCharacterReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(com.example.rickmorty.Constants.FIREBASE_CHILD_SEARCHED_CHARACTER);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mSearchedCharacterReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot characterSnapshot: dataSnapshot.getChildren()){
                    String character = characterSnapshot.getValue().toString();
                    Log.d("Characters updated", "character: " + character);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            //update  UI here if error occured.
            }


        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mFormEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //If the event is a keydown event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN)&& (keyCode == KeyEvent.KEYCODE_ENTER)){
                    //On press
//                    mViewMoreButton();

                }
                return false;
            }
        });

        mSavedCharactersButton.setOnClickListener(this);
        mViewMoreButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                if (v == mViewMoreButton){
                    String character = mFormEditText.getText().toString();

                    saveCharacterToFirebase(character);
//                    if(!(character).equals("")){
//                        addToSharedPreferences(character);
//                    }

                    Intent intent = new Intent(MainActivity.this, TestActivity.class);
                    intent.putExtra("character", character);
                    startActivity(intent);
                }

                if (v == mSavedCharactersButton) {
                    Intent intent = new Intent(MainActivity.this, SavedCharacterListActivity.class);
                    startActivity(intent);
                }

            }

            public void saveCharacterToFirebase(String character){
                mSearchedCharacterReference.push().setValue(character);
            }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedCharacterReference.removeEventListener(mSearchedCharacterReferenceListener);
    }


//            private void addToSharedPreferences(String character){
//                mEditor.putString(Constants.PREFERENCES_CHARACTER_KEY, character).apply();
//            }
    }


