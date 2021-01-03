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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ElementAdapter2 extends RecyclerView.Adapter<ElementViewHolder> {

    FirebaseFirestore firestore;

    private Context context;
    private List<Elements> elements;
    private onClick click_btn;


    ElementAdapter2(Context context, List<Elements> elements){

        this.context = context;
        this.elements = elements;

    }

    @NonNull
    @Override
    public ElementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_item2 , parent , false) ;
        return new ElementViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ElementViewHolder holder,final int position) {
        firestore = FirebaseFirestore.getInstance();


        holder.salary.setText(elements.get(position).getSalary()+"");
        holder.name.setText(elements.get(position).getName());

        if (elements.get(position).getImage().isEmpty()){
            Picasso.get().load(R.drawable.apple).into(holder.image);
        }else {
            Picasso.get().load(elements.get(position).getImage()).into(holder.image);
        }


        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PurchasesActivity.allS <=100) {
                    CollectionReference reference = firestore.collection(LoginActivity.name);

                    Elements element = new Elements();

                    element.setName(elements.get(position).getName());
                    element.setSalary(elements.get(position).getSalary());
                    element.setImage(elements.get(position).getImage());

                    Task<DocumentReference> t = reference.add(element);
                    t.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(context, "Add Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(context, "Your Purchases are Many", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.money.setImageResource(R.drawable.ic_attach_money_black_24dp);

    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public void setClick_btn(onClick click_btn) {
        this.click_btn = click_btn;
    }

    public interface onClick{
        void onClickItem();
    }
}

