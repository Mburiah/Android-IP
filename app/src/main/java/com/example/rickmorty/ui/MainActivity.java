package com.example.rickmorty.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;

import com.example.rickmorty.R;

import butterknife.ButterKnife;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewMoreButton) Button mViewMoreButton;
    @BindView(R.id.formEdittext) EditText mFormEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mViewMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String form = mFormEditText.getText().toString();

                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                intent.putExtra(form, form);
                startActivity(intent);
            }
        });
    }
}
