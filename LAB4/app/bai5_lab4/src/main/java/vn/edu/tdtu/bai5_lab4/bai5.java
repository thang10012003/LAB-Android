package vn.edu.tdtu.bai5_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.temporal.Temporal;
import java.util.ArrayList;

public class bai5 extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<User> list_user;
    Button bAdd;
    Button bRemove;
    int num = 0;
    TextView tvTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai5);

        recyclerView = findViewById(R.id.recyclerview);
        list_user = new ArrayList<>();
        bAdd = findViewById(R.id.bAdd);
        bRemove = findViewById(R.id.bRemove);
        tvTotal = findViewById(R.id.tvTotal);
        tvTotal.setText("Total users:" + num);


        MyAdapter myAdapter = new MyAdapter(this,list_user);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = num;
                for (int i = number; i < number+5; i++) {
                    myAdapter.addItem(i);
                    num+=1;
                }
                tvTotal.setText("Total users:" + num);
            }
        });
        bRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num == 0){
                    Toast.makeText(getApplicationContext(),"List of users is empty",Toast.LENGTH_SHORT).show();
                }else{
                    int number = num;
                    for (int i = number-1; i >=number-5 ; i--) {
                        myAdapter.removeItem(i);
                        num-=1;
                    }
                    tvTotal.setText("Total users:" + num);

                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }
}