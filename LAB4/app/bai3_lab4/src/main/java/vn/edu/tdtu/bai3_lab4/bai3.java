package vn.edu.tdtu.bai3_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class bai3 extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> listdevice;
    Button bSelected;
    Button bAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        recyclerView = findViewById(R.id.recyclerview);
        bSelected = findViewById(R.id.bSelected);
        bAll = findViewById(R.id.bAll);
        recyclerView.setHasFixedSize(false);


        listdevice = new ArrayList<>();
        listdevice.add("Apple");
        listdevice.add("Samsung");
        listdevice.add("Nokia");
        listdevice.add("Oppo");

        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), listdevice);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        bSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.delete_selected_item();
            }
        });
        bAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.delete_all_item();
            }
        });



    }
}