package vn.edu.tdtu.bai4_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class bai4 extends AppCompatActivity {

    static int MAX = 100;
    static int MIN = 10;
    ArrayList<String> list;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);

        int rand_num = (int)(Math.random()*(MAX-MIN + 1)+MIN);
        list = new ArrayList<>();
        for (int i = 0; i < rand_num; i++) {
            list.add("PC "+(i+1));
        }

        recyclerView = findViewById(R.id.recycleview);

        MyAdapter myAdapter = new MyAdapter(this,list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(myAdapter);


    }
}