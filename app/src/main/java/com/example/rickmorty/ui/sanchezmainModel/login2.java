package com.example.rickmorty.ui.sanchezmainModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rickmorty.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class login2 extends AppCompatActivity {
    @BindView(R.id.lEmail) EditText mEmail;
    @BindView(R.id.lPass) EditText mPass;
    @BindView(R.id.loginBtn) Button loginBtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);


        String email = mEmail.getText().toString().trim();
        String password = mPass.getText().toString().trim();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if email or password are valid
                if (email==null){
                    mEmail.setError("Please enter a valid email");

                }
                if (password.length()<6){
                    mPass.setError("Please enter a valid password of atleast 6 characters");
                }


                Task<AuthResult> login_successful = mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(login2.this, "Authenticated Successfuly.",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),test1.class));

                        }else {
                            Toast.makeText(login2.this, "Authenticated Successfuly.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });

    }
}
