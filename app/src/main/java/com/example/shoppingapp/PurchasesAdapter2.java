package com.example.shoppingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PurchasesAdapter2 extends RecyclerView.Adapter<PurchasesViewHolder> {


    private CollectionReference firestore;
    private Context context;
    private List<Elements> elements;
    private ArrayList<String> key = new ArrayList<>();
    private ElementAdapter2.onClick click_btn;


    PurchasesAdapter2(Context context, List<Elements> elements, CollectionReference firebaseFirestore){
        this.context = context;
        this.elements = elements;
        this.firestore = firebaseFirestore;
    }



    @NonNull
    @Override
    public PurchasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchases_item , parent , false) ;
        return new PurchasesViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PurchasesViewHolder holder,final int position) {

        holder.nameP.setText(elements.get(position).getName());
        holder.salaryP.setText(elements.get(position).getSalary()+"");

        if (elements.get(position).getImage().isEmpty()){
            Picasso.get().load(R.drawable.apple).into(holder.imageP);
        }else {
            Picasso.get().load(elements.get(position).getImage()).into(holder.imageP);
        }

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task<QuerySnapshot> task =  firestore.get();
                task.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                            key.add(snapshot.getReference().getPath());
                        }
                        int index = key.get(position).indexOf("/");
                        String id =  key.get(position).substring(index);

                        firestore.document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(context , "Remove" , Toast.LENGTH_SHORT).show();
                                key.remove(position);
                            }
                        });
                    }
                });
            }
        });


    }


    @Override
    public int getItemCount() {
        return elements.size();
    }

    public void setClick_btn(ElementAdapter2.onClick click_btn) {
        this.click_btn = click_btn;
    }

    public interface onClick{
        void onClickItem();
    }
}