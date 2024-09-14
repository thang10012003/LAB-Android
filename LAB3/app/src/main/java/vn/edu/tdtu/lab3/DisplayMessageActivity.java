package vn.edu.tdtu.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    public static  final  String  EXTRA_MESSAGE ="vn.lab.tdtu.lab3";
    private TextView tv_welcome;
    private EditText et_input;
    private Button btn_dangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        String message =  intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        tv_welcome = findViewById(R.id.tv_Welcome);
        tv_welcome.setText("Xin chào, " + message + ". Vui lòng nhập tên");
    }
    public  void  sentMessage(View view){
        Intent intent  = new Intent(this, MainActivity.class);
        et_input = findViewById(R.id.et_Input);
        String message = et_input.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
}