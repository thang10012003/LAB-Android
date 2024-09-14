package vn.edu.tdtu.lab3;

import static android.icu.lang.UCharacter.toLowerCase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

public class bai4_lab3 extends AppCompatActivity {

    ShapeableImageView icCreate;

    TextView tvContactName;
    ShapeableImageView icPerson;
    TextView tvName;
    TextView tvContactPhone;
    TextView tvContactAddress;
    TextView tvContactHomepage;
    TextView tvContactMail;
    TextView tvJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4_lab3);
        icCreate = findViewById(R.id.icCreate);
        icCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvContactName = findViewById(R.id.tvContactName);
                tvContactPhone = findViewById(R.id.tvContactPhone);
                tvContactMail = findViewById(R.id.tvContactMail);
                tvContactHomepage = findViewById(R.id.tvContactHomepage);
                tvContactAddress = findViewById(R.id.tvContactAddress);
                icPerson = findViewById(R.id.icPerson);
                tvJob = findViewById(R.id.tvJob);
                tvName = findViewById(R.id.tvName);
                Intent intent = new Intent(bai4_lab3.this, bai4_lab3_edit.class);
                intent.putExtra("name", tvContactName.getText().toString());
                intent.putExtra("phone", tvContactPhone.getText().toString());
                intent.putExtra("mail", tvContactMail.getText().toString());
                intent.putExtra("address", tvContactAddress.getText().toString());
                intent.putExtra("homepage", tvContactHomepage.getText().toString());
                intent.putExtra("job", tvJob.getText().toString());
                startActivityForResult(intent, 1);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                tvContactName = findViewById(R.id.tvContactName);
                tvContactPhone = findViewById(R.id.tvContactPhone);
                tvContactMail = findViewById(R.id.tvContactMail);
                tvContactHomepage = findViewById(R.id.tvContactHomepage);
                tvContactAddress = findViewById(R.id.tvContactAddress);
                icPerson = findViewById(R.id.icPerson);
                tvJob = findViewById(R.id.tvJob);
                tvName = findViewById(R.id.tvName);
                tvContactName.setText(data.getStringExtra("name"));
                tvContactPhone.setText(data.getStringExtra("phone"));
                tvContactMail.setText(data.getStringExtra("mail"));
                tvContactHomepage.setText(data.getStringExtra("homepage"));
                tvContactAddress.setText(data.getStringExtra("address"));
                tvJob.setText(data.getStringExtra("job"));
                String name = data.getStringExtra("name");
                name = name.replaceAll(" ", "_");
                tvName.setText(toLowerCase(name));
//                //Lay anh:
                if(data.getExtras() != null){
                    Bitmap image = (Bitmap) data.getExtras().get("photo");
                    if(image != null){
                        icPerson.setImageBitmap(image);
                    }
                }
            }
        }
    }
}