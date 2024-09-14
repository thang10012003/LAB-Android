package vn.edu.tdtu.bai2_lab5;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Locale;

public class addEvent extends AppCompatActivity {
    Toolbar toolbarAdd;
    EditText edtName,edtPlace,edtDate,edtTime;
    ImageButton btnBack, btnSave;
    int positionToEdit = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        toolbarAdd = findViewById(R.id.toolbarAdd);
        setSupportActionBar(toolbarAdd);

        edtName = findViewById(R.id.edtName);
        edtPlace = findViewById(R.id.edtPlace);
        edtDate = findViewById(R.id.edtDate);
        edtTime = findViewById(R.id.edtTime);


        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quay trở lại MainActivity
                Intent intent = new Intent(addEvent.this, bai2.class);
                startActivity(intent);

                // Kết thúc hoạt động hiện tại
                finish();
            }
        });

        Intent receivedIntent = getIntent();
        if (receivedIntent != null) {
            // Lấy chế độ chỉnh sửa (nếu có)
            boolean isEditMode = receivedIntent.getBooleanExtra("isEditMode", false);
            if (isEditMode) {
                showToastForEdit();
                // Đây là chế độ chỉnh sửa, lấy dữ liệu từ Intent
                String name = receivedIntent.getStringExtra("name");
                String place = receivedIntent.getStringExtra("place");
                String date = receivedIntent.getStringExtra("date");
                String time = receivedIntent.getStringExtra("time");
                positionToEdit = receivedIntent.getIntExtra("position", -1);

                // Hiển thị dữ liệu trong EditText
                edtName.setText(name);
                edtPlace.setText(place);
                edtDate.setText(date);
                edtTime.setText(time);
            } else {
                // Đây là chế độ thêm mới
                // ...
            }
        }

        btnSave = findViewById(R.id.btnSave);
        // Trong addEvent
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ EditText
                String name = edtName.getText().toString();
                String place = edtPlace.getText().toString();
                String date = edtDate.getText().toString();
                String time = edtTime.getText().toString();

                Intent resultIntent = new Intent();

                if (positionToEdit != -1) {
                    // Đây là chế độ chỉnh sửa, gửi dữ liệu cũ và vị trí
                    resultIntent.putExtra("name", name);
                    resultIntent.putExtra("place", place);
                    resultIntent.putExtra("date", date);
                    resultIntent.putExtra("time", time);
                    resultIntent.putExtra("position", positionToEdit); // Truyền vị trí chỉnh sửa
                } else {
                    // Đây là chế độ thêm mới, gửi dữ liệu mới
                    resultIntent.putExtra("name", name);
                    resultIntent.putExtra("place", place);
                    resultIntent.putExtra("date", date);
                    resultIntent.putExtra("time", time);
                }

                setResult(RESULT_OK, resultIntent);
                finish(); // Đóng màn hình AddEventActivity
            }
        });

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
        edtPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlaceOptionsDialog();
            }
        });

    }
    private void showToastForEdit() {
        Toast.makeText(this, "Chế độ chỉnh sửa từ MainActivity", Toast.LENGTH_SHORT).show();
    }

    private void showTimePicker() {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                EditText edtTime = findViewById(R.id.edtTime);
                edtTime.setText(selectedTime);
            }
        }, hour, minute, true);

        timePickerDialog.show();
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                EditText edtDate = findViewById(R.id.edtDate);
                edtDate.setText(selectedDate);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void showPlaceOptionsDialog() {
        final CharSequence[] items = {"C201", "C202", "C203", "C204"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Place Option");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Lấy lựa chọn của người dùng và đặt nó vào EditText "Place"
                EditText edtPlace = findViewById(R.id.edtPlace);
                edtPlace.setText(items[item]);
            }
        });
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}