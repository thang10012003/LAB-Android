package vn.edu.tdtu.bai1_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {
    private Button btn_signin;
    private EditText editText_username;
    private  EditText editText_password;
    private TextView txt_reset;
    protected boolean password_validation(String pass){
        if(pass.length()<6){
            return  false;
        }
//
        if(!pass.matches(".*[a-zA-Z].*")){
            return  false;
        }

        return  true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_signin = findViewById(R.id.btn_singin);
        editText_username = findViewById(R.id.edit_username);
        editText_password = findViewById(R.id.edit_password);
        txt_reset = findViewById(R.id.txt_reset);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence tb = "Vui long nhap username hoac password";
                String username = String.valueOf(editText_username.getText());
                String password = String.valueOf(editText_password.getText());
                if(username.compareTo("")==0|| password.compareTo("")==0){

                        Toast.makeText(getApplicationContext(), tb, Toast.LENGTH_SHORT).show();
                }else {
                    if(!password_validation(password)){
                        Toast.makeText(getApplicationContext(), "Mat khau khong dung yeu cau", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        txt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = String.valueOf(editText_username.getText());
                if(username.compareTo("")==0){
                    Toast.makeText(getApplicationContext(), "Vui long nhap username", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Reset mat khau thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}