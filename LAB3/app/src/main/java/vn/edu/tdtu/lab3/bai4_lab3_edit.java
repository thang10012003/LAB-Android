package vn.edu.tdtu.lab3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.imageview.ShapeableImageView;

public class bai4_lab3_edit extends AppCompatActivity {

    EditText etName;
    ShapeableImageView icPerson;
    Bitmap photo;
    EditText etMail;
    EditText etPhone;
    EditText etAddress;
    EditText etHomepage;
    EditText etJob;
    ShapeableImageView icImage;
    Button bSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4_lab3_edit);

        icPerson = findViewById(R.id.icPerson);
        etName = findViewById(R.id.etName);
        etMail = findViewById(R.id.etMail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etHomepage = findViewById(R.id.etHomepage);
        etJob = findViewById(R.id.etJob);
        bSave = findViewById(R.id.bSave);
        icImage = findViewById(R.id.icImage);

        etName.setText(getIntent().getStringExtra("name"));
        etMail.setText(getIntent().getStringExtra("mail"));
        etPhone.setText(getIntent().getStringExtra("phone"));
        etAddress.setText(getIntent().getStringExtra("address"));
        etHomepage.setText(getIntent().getStringExtra("homepage"));
        etJob.setText(getIntent().getStringExtra("job"));

        icImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ContextCompat.checkSelfPermission(bai4_lab3_edit.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(bai4_lab3_edit.this, new String []{Manifest.permission.CAMERA}, 1);
                    return;
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 2);

            }

        });
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(bai4_lab3_edit.this, bai4_lab3.class );
                String name = etName.getText().toString();
                String mail = etMail.getText().toString();
                String phone = etPhone.getText().toString();
                String address = etAddress.getText().toString();
                String homepage = etHomepage.getText().toString();
                String job = etJob.getText().toString();

                intent1.putExtra("name", name);
                intent1.putExtra("mail", mail);
                intent1.putExtra("address", address);
                intent1.putExtra("phone", phone);
                intent1.putExtra("homepage", homepage);
                intent1.putExtra("job", job);
                //Xu ly anh:

                intent1.putExtra("photo", photo);
                setResult(RESULT_OK, intent1);
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2){
            if(resultCode == RESULT_OK && data != null){
                Bundle bundle = data.getExtras();
                photo = (Bitmap) bundle.get("data");
                icPerson.setImageBitmap(photo);


            }
        }
    }
}