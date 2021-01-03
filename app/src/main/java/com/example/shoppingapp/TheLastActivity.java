package com.example.shoppingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TheLastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_last);

        Button shoppingAgain = findViewById(R.id.again);
        Button ending = findViewById(R.id.ending);

        shoppingAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , ContentActivity.class);
                startActivity(intent);
            }
        });

        ending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TheLastActivity.this);
                builder.setTitle("Exit");
                builder.setMessage("Do you want to exit ??");
                builder.setPositiveButton("Yes. Exit now!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        finishAffinity();
                    }
                });
                builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
