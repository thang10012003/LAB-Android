package vn.edu.tdtu.bai1_lab5;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class bai1 extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<item> listdevice;

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        recyclerView = findViewById(R.id.recyclerview);

        listdevice = new ArrayList<>();




        listdevice.add(new item("Apple",false));
        listdevice.add(new item("Samsung",false));
        listdevice.add(new item("Nokia",false));
        listdevice.add(new item("Oppo",false));

        myAdapter = new MyAdapter(getApplicationContext(), listdevice);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.item_check){
            myAdapter.checkAllitem();
        }else if(id == R.id.item_selected){
            myAdapter.delete_selected_item();

        }else if(id == R.id.item_all){
            myAdapter.delete_all_item();
        }else
            return true;
        return  true;
    }
}