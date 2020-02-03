package com.example.rickmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.BindView;

public class ListActivity extends AppCompatActivity{
    @BindView(R.id.charactersTextView) TextView mCharactersTextView;
    @BindView(R.id.listView1) ListView mListView;
    @BindView(R.id.locationButton) Button mLocationButton;

    private String[]characters = new String[]{"Rick Sanchez", "Morty Smith", "Jerry Smith", "Unity", "Summer Smith", "Beth Smith", "Mr. Meeseeks", "Elom Tusk" };

    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String characters = ((TextView)view).getText().toString();
                Toast.makeText(ListActivity.this, characters,Toast.LENGTH_LONG).show();
            }
        });

        ArrayAdapter adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1, characters);
        mListView.setAdapter(adapter);

        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this,NextActivity.class);
                startActivity(intent);
            }
        });


    }

}
