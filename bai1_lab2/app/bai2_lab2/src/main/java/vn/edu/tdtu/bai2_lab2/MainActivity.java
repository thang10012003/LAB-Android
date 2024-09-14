package vn.edu.tdtu.bai2_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_Following;
    private TextView tv_Followers;
    private Button btn_Follow;
    private  Button btn_Unfollow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_Followers = findViewById(R.id.txt_followers);
        tv_Following = findViewById(R.id.txt_following);
        btn_Follow = findViewById(R.id.btn_Follow);
        btn_Unfollow = findViewById(R.id.btn_UnFollow);

        int follower = (int)Math.floor(Math.random() *(10000 - 100 + 1) + 100);
        int following = (int)Math.floor(Math.random() *(10000 - 100 + 1) + 100);
        tv_Followers.setText(String.valueOf(follower));
        tv_Following.setText((String.valueOf(following)));

        btn_Follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_Follow.setVisibility(View.INVISIBLE);
                btn_Unfollow.setVisibility(View.VISIBLE);
                int num = Integer.valueOf(String.valueOf(tv_Followers.getText()));
                tv_Followers.setText(String.valueOf(num+1));
            }
        });
        btn_Unfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_Unfollow.setVisibility(View.INVISIBLE);
                btn_Follow.setVisibility(View.VISIBLE);
                int num = Integer.valueOf(String.valueOf(tv_Followers.getText()));
                tv_Followers.setText(String.valueOf(num-1));

            }
        });
    }
}