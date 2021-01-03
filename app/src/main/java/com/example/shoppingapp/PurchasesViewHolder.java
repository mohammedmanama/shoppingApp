package com.example.shoppingapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PurchasesViewHolder extends RecyclerView.ViewHolder {

    TextView nameP;
    TextView salaryP;
    ImageView imageP;
    Button remove;

    public PurchasesViewHolder(@NonNull View itemView) {
        super(itemView);
        nameP = itemView.findViewById(R.id.nameChases2);
        salaryP = itemView.findViewById(R.id.salaryChases2);
        imageP = itemView.findViewById(R.id.photochases2);
        remove = itemView.findViewById(R.id.deleteElement);
    }
}
