package com.example.shoppingapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ElementViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView salary;
    ImageView image;
    Button add;
    ImageView money;
    public ElementViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.elementTitle);
        salary = itemView.findViewById(R.id.elementS);
        image = itemView.findViewById(R.id.elementPhoto);
        add = itemView.findViewById(R.id.addElement);
        money = itemView.findViewById(R.id.money);

    }
}

