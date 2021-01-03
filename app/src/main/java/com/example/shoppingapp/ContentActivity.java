package com.example.shoppingapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ContentActivity extends AppCompatActivity implements ElementAdapter2.onClick {

    FirebaseFirestore firestore;
    FirebaseAuth auth = null;
    static RecyclerView recyclerViewElemrnts;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarXXX);
        setSupportActionBar(toolbar);


        recyclerViewElemrnts = findViewById(R.id.recyclerViewElements);
        final List<Elements> list1 = new ArrayList<>();
        final ElementAdapter2 elementAdapter = new ElementAdapter2(getApplicationContext(), list1);
        recyclerViewElemrnts.setAdapter(elementAdapter);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewElemrnts.setLayoutManager(manager);

        ArrayList<OptionsMenu> list = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerViewX);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        final OptionsMenuAdapter adapter = new OptionsMenuAdapter(getApplicationContext(), list, R.layout.menu_item, recyclerViewElemrnts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(llm);


        OptionsMenu optionsMenu1 = new OptionsMenu();
        optionsMenu1.setName("Vegetable");
        optionsMenu1.setImage(R.drawable.vegetables);

        OptionsMenu optionsMenu2 = new OptionsMenu();
        optionsMenu2.setName("Fruit");
        optionsMenu2.setImage(R.drawable.fruit);

        OptionsMenu optionsMenu3 = new OptionsMenu();
        optionsMenu3.setName("Meat");
        optionsMenu3.setImage(R.drawable.steak);

        OptionsMenu optionsMenu4 = new OptionsMenu();
        optionsMenu4.setName("Fish");
        optionsMenu4.setImage(R.drawable.fish);

        OptionsMenu optionsMenu5 = new OptionsMenu();
        optionsMenu5.setName("Juices");
        optionsMenu5.setImage(R.drawable.juices);

        list.add(optionsMenu1);
        list.add(optionsMenu2);
        list.add(optionsMenu3);
        list.add(optionsMenu4);
        list.add(optionsMenu5);

        adapter.notifyDataSetChanged();


        SharedPreferences sharedPreferences = getSharedPreferences("flag" , MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean("flag" , false);

        if (flag) {

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

                        list1.add(elements);
                        elementAdapter.notifyDataSetChanged();
                    }
                }
            });
        }else {
            Toast.makeText(this, "Get Me My Money", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            Intent intent = new Intent(getApplicationContext() , PurchasesActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.logOut){
            if (auth.getCurrentUser().getEmail() != null) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                auth.signOut();
            }
        }else if (id == R.id.aboutUs){
            final AlertDialog.Builder builder = new AlertDialog.Builder(ContentActivity.this);
            builder.setTitle("Shopp App");
            builder.setIcon(R.drawable.logo);
            builder.setMessage("Welcome To Our Shopp App , this app enable to you all thing from our specific market needs " +
                    "what are you Waiting  ??  Shopp Now .");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i){
                    dialogInterface.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickItem() {

    }
}
