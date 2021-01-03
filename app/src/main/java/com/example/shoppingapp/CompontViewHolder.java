package com.example.shoppingapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CompontViewHolder extends RecyclerView.ViewHolder {

    ImageView Img;
    TextView name;
    CompontViewHolder(@NonNull View itemView) {
        super(itemView);
        Img = itemView.findViewById(R.id.cImageView);
        name = itemView.findViewById(R.id.nameTv);
    }

}

