package com.example.shoppingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OptionsMenuAdapter extends   RecyclerView.Adapter<CompontViewHolder> {
    private FirebaseFirestore firestore;
    private ArrayList<OptionsMenu> optionsMenus;
    private final int itemLayoutId ;
    private final Context context;
    private final RecyclerView recyclerView;


    OptionsMenuAdapter(Context context, ArrayList<OptionsMenu> optionsMenus, int itemLayoutId, RecyclerView recyclerView) {

        this.optionsMenus = optionsMenus;
        this.context = context;
        this.itemLayoutId = itemLayoutId;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public CompontViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayoutId , viewGroup , false) ;
        return new CompontViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CompontViewHolder compontViewHolder, @SuppressLint("RecyclerView") final int i) {

        final OptionsMenu o = optionsMenus.get(i);
        compontViewHolder.Img.setImageResource(o.getImage());
        compontViewHolder.name.setText(o.getName());
        firestore = FirebaseFirestore.getInstance();

        final View view = compontViewHolder.itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = context.getSharedPreferences("flag" , Context.MODE_PRIVATE);
                boolean flag = sharedPreferences.getBoolean("flag" , false);
                if (flag) {

                    switch (i) {
                        case 0:
                            final List<Elements> listV = new ArrayList<>();
                            final ElementAdapter2 adapterV = new ElementAdapter2(context, listV);
                            recyclerView.setAdapter(adapterV);
                            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(manager);
                            CollectionReference reference = firestore.collection("Vegetable");
                            Task<QuerySnapshot> q = reference.get();
                            q.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {

                                        Elements elements = new Elements();
                                        Elements b = queryDocumentSnapshot.toObject(Elements.class);

                                        elements.setName(b.getName());
                                        elements.setImage(b.getImage());
                                        elements.setSalary(b.getSalary());

                                        listV.add(elements);
                                        adapterV.notifyDataSetChanged();

                                    }
                                }
                            });

                            break;
                        case 1:

                            final List<Elements> listF = new ArrayList<>();
                            final ElementAdapter2 adapterF = new ElementAdapter2(context, listF);
                            recyclerView.setAdapter(adapterF);
                            StaggeredGridLayoutManager managerF = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(managerF);
                            CollectionReference reference1 = firestore.collection("Fruit");
                            Task<QuerySnapshot> q1 = reference1.get();
                            q1.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {

                                        Elements elements = new Elements();
                                        Elements b = queryDocumentSnapshot.toObject(Elements.class);

                                        elements.setName(b.getName());
                                        elements.setImage(b.getImage());
                                        elements.setSalary(b.getSalary());

                                        listF.add(elements);
                                        adapterF.notifyDataSetChanged();
                                    }
                                }
                            });


                            break;
                        case 2:

                            final List<Elements> listM = new ArrayList<>();
                            final ElementAdapter2 adapterM = new ElementAdapter2(context, listM);
                            recyclerView.setAdapter(adapterM);
                            StaggeredGridLayoutManager managerM = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(managerM);
                            CollectionReference reference2 = firestore.collection("Meat");
                            Task<QuerySnapshot> q2 = reference2.get();
                            q2.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {

                                        Elements elements = new Elements();
                                        Elements b = queryDocumentSnapshot.toObject(Elements.class);

                                        elements.setName(b.getName());
                                        elements.setImage(b.getImage());
                                        elements.setSalary(b.getSalary());

                                        listM.add(elements);
                                        adapterM.notifyDataSetChanged();

                                    }
                                }
                            });


                            break;
                        case 3:

                            final List<Elements> listFish = new ArrayList<>();
                            final ElementAdapter2 adapterf = new ElementAdapter2(context, listFish);
                            recyclerView.setAdapter(adapterf);
                            StaggeredGridLayoutManager managerf = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(managerf);
                            CollectionReference reference3 = firestore.collection("Fish");
                            Task<QuerySnapshot> q3 = reference3.get();
                            q3.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {

                                        Elements elements = new Elements();
                                        Elements b = queryDocumentSnapshot.toObject(Elements.class);

                                        elements.setName(b.getName());
                                        elements.setImage(b.getImage());
                                        elements.setSalary(b.getSalary());

                                        listFish.add(elements);
                                        adapterf.notifyDataSetChanged();

                                    }
                                }
                            });

                            break;
                        case 4:

                            final List<Elements> listJ = new ArrayList<>();
                            final ElementAdapter2 adapterJ = new ElementAdapter2(context, listJ);
                            recyclerView.setAdapter(adapterJ);
                            StaggeredGridLayoutManager managerJ = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(managerJ);
                            CollectionReference reference4 = firestore.collection("Juices");
                            Task<QuerySnapshot> q4 = reference4.get();
                            q4.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {

                                        Elements elements = new Elements();
                                        Elements b = queryDocumentSnapshot.toObject(Elements.class);

                                        elements.setName(b.getName());
                                        elements.setImage(b.getImage());
                                        elements.setSalary(b.getSalary());

                                        listJ.add(elements);
                                        adapterJ.notifyDataSetChanged();

                                    }
                                }
                            });

                            break;
                    }
                }else {
                    Toast.makeText(context, "Get Me My Money", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionsMenus.size();
    }
}

