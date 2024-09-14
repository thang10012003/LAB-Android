package vn.edu.tdtu.bai2_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class bai2 extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> items;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(false);

        int num = (int)(Math.random()*(20-10 + 1))+10;
        items = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            items.add("item " + (i+1));
        }
        Log.d("TAG", "num: " + String.valueOf(num));
        adapter = new CustomAdapter(getApplicationContext(), items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}