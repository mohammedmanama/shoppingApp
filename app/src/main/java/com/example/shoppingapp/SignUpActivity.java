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

public class SignUpActivity extends AppCompatActivity {


    EditText nameEd,emailEd,passwordEd;
    Button signUp,signin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        nameEd = findViewById(R.id.nameEd);
        emailEd = findViewById(R.id.emailEd);
        passwordEd = findViewById(R.id.passwordEd);
        signin = findViewById(R.id.signin);
        signUp = findViewById(R.id.signUp);

        if(mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEd.getText().toString();
                String email = emailEd.getText().toString();
                String password = passwordEd.getText().toString();

                if(name.isEmpty()){
                    nameEd.setError("Please enter your name");
                    nameEd.requestFocus();
                }
                else if (email.isEmpty()){
                    emailEd.setError("Please enter your email");
                    emailEd.requestFocus();
                }
                else if (password.isEmpty()){
                    passwordEd.setError("Please enter your password");
                    passwordEd.requestFocus();
                }
                else if(name.isEmpty() && email.isEmpty() && password.isEmpty()){
                    Toast.makeText(SignUpActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(name.isEmpty() && email.isEmpty() && password.isEmpty())){
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(SignUpActivity.this,"Error Ocurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}