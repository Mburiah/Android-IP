package com.example.rickmorty;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NextActivity extends AppCompatActivity {
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView2) ListView mListView2;

    private String[] locations = new String[]{"Earth (C-137)","Earth","Earth(replacement dimension)", "Gear World","Fantasy World", "Unknown","Citadel of Ricks", "Post-Apocalyptic Earth"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_next);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,locations);
        mListView2.setAdapter(adapter);

        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String locations = ((TextView)view).getText().toString();
                Toast.makeText(NextActivity.this, locations, Toast.LENGTH_LONG);
            }
        });
    }
}
