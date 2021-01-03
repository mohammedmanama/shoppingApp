package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    public static String name;
    EditText emailEd,passwordEd;
    Button signin,signUp;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        emailEd = findViewById(R.id.emailEd);
        passwordEd = findViewById(R.id.passwordEd);
        signin = findViewById(R.id.signin);
        signUp = findViewById(R.id.signUp);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                }

            }
        };
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEd.getText().toString();
                String password = passwordEd.getText().toString();

                if(email.isEmpty()){
                    emailEd.setError("Please enter your email");
                    emailEd.requestFocus();
                }
                else if (password.isEmpty()){
                    passwordEd.setError("Please enter your password");
                    passwordEd.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && password.isEmpty())){
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });


                }
                else {
                    Toast.makeText(LoginActivity.this,"Error Ocurred!",Toast.LENGTH_SHORT).show();
                }

                signUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                        startActivity(intent);
                    }
                });
            }

            protected void onStart() {
                LoginActivity.super.onStart();
                mAuth.addAuthStateListener(mAuthStateListener);
            }
        });



    }
}

