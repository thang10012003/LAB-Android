package vn.edu.tdtu.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class bai2_lab3 extends AppCompatActivity {

    private TextView tv_header ;
    private EditText et_URL;
    private Button btn_open;
    public static final String EXTRA_MESSAGE = "vn.tdtu.edu.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2_lab3);

        tv_header = findViewById(R.id.tv_header);
        et_URL = findViewById(R.id.et_URL);
        btn_open = findViewById(R.id.btn_Open);

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(et_URL.getText().toString()));
//                startActivity(intent);
                OpenWebBrowser(view);
            }
        });
    }
    protected void OpenWebBrowser(View view){
            String message = "https://"+et_URL.getText().toString();
//            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,WebBrowser.class);
            intent.putExtra(EXTRA_MESSAGE,message);
            startActivity(intent);


    }

}