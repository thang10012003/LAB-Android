package vn.edu.tdtu.bai1_lab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import vn.edu.tdtu.bai1_lab6.databinding.ActivityBai1Binding;

public class bai1 extends AppCompatActivity {



    ActivityBai1Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBai1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvCount.setText("2");
    }
}