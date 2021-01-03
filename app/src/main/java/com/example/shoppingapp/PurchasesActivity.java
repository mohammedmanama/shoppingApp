package com.example.shoppingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PurchasesActivity extends AppCompatActivity implements PurchasesAdapter2.onClick {


    FirebaseFirestore firestore;
    CollectionReference reference;
    TextView allSalary;
    static double allS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);

        SharedPreferences sharedPreferences = getSharedPreferences("flag" , MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean("flag" , false);

        firestore = FirebaseFirestore.getInstance();
        reference = firestore.collection(LoginActivity.name);

        allSalary = findViewById(R.id.allSalary);


        RecyclerView recyclerView = findViewById(R.id.recyclerViewPurchases);
        final List<Elements> list = new ArrayList<>();

        final PurchasesAdapter2 adapter = new PurchasesAdapter2(getApplicationContext(), list, reference);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        if (flag) {
            Task<QuerySnapshot> q = reference.get();
            q.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {

                        Elements book = new Elements();
                        Elements b = queryDocumentSnapshot.toObject(Elements.class);


                        book.setName(b.getName());
                        book.setImage(b.getImage());
                        book.setSalary(b.getSalary());


                        list.add(book);

                        allS = 0;
                        for (int i = 0; i < list.size(); i++) {
                            allS += list.get(i).getSalary();
                        }

                        allSalary.setText(allS + "");
                    }
                    adapter.notifyDataSetChanged();

                }
            });


            q.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                startActivity(intent);
            }
        });

        Button nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PayActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClickItem() {

    }
}

