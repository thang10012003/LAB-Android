package vn.edu.tdtu.bai2_lab5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class bai2 extends AppCompatActivity {

    private static final int ADD_EVENT_REQUEST_CODE = 1;
    private static final int EDIT_EVENT_REQUEST_CODE = 2;
    private List<DataItem> dataItems;
    private List<Boolean> itemSwitchStates;
    private List<DataItem> dataItemsTemp;
    private CustomAdapter adapter;
    private SwitchCompat switchButton;
    private DataItem[] originalDataItems; // To store the original data
    private boolean[] originalSwitchStates;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton btnAddItem = findViewById(R.id.btnAddItem);

        // Thêm sự kiện nghe cho nút btnAddItem
        dataItems = new ArrayList<>();
        dataItems.add(new DataItem("Item 1", "Place 1", "Date 1", "Time 1"));
        dataItems.add(new DataItem("Item 2", "Place 2", "Date 2", "Time 2"));

        ListView listView = findViewById(R.id.lv);
        adapter = new CustomAdapter(this, R.layout.layout_listview, dataItems);
        listView.setAdapter(adapter);

        // ...

        btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở màn hình AddEventActivity để thêm sự kiện
                Intent intent = new Intent(bai2.this, addEvent.class);
                startActivityForResult(intent, ADD_EVENT_REQUEST_CODE);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DataItem dataItem = dataItems.get(position);

                String[] options = {"Edit", "Delete"};

                AlertDialog.Builder builder = new AlertDialog.Builder(bai2.this);
                builder.setTitle("Select an Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedOption = options[which];
                        if (selectedOption.equals("Edit")) {
                            // Nếu chọn "Edit", chuyển sang màn hình chỉnh sửa
                            Intent intent = new Intent(bai2.this, addEvent.class);
                            intent.putExtra("isEditMode", true); // Đánh dấu chế độ chỉnh sửa
                            intent.putExtra("name", dataItem.getName());
                            intent.putExtra("place", dataItem.getPlace());
                            intent.putExtra("date", dataItem.getDate());
                            intent.putExtra("time", dataItem.getTime());
                            intent.putExtra("position", position); // Truyền vị trí chỉnh sửa
                            startActivityForResult(intent, ADD_EVENT_REQUEST_CODE);
                        } else if (selectedOption.equals("Delete")) {
                            // Xử lý xóa mục ở đây nếu cần
                            new AlertDialog.Builder(bai2.this)
                                    .setTitle("Delete Item")
                                    .setMessage("Do you want to delete this item?")
                                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Xử lý xóa mục ở đây
                                            dataItems.remove(position);
                                            adapter.notifyDataSetChanged();
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                        dialog.dismiss();
                    }
                });
                builder.show();

                return true;
            }
        });
        switchButton = findViewById(R.id.switchButton);

        // Đặt sự kiện lắng nghe cho switch
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Lưu danh sách và trạng thái `Switch` ban đầu vào biến tạm
                    originalDataItems = new DataItem[dataItems.size()];
                    dataItems.toArray(originalDataItems);

                    originalSwitchStates = adapter.getSwitchStates().clone();

                    adapter.removeUncheckedItems();
                }
                else {
                    dataItems.clear();
                    adapter.notifyDataSetChanged();

                    // Thêm lại các mục từ biến tạm
                    for (DataItem item : originalDataItems) {
                        dataItems.add(item);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
    private void updateListBasedOnSwitch() {
        adapter.clear();
        for (DataItem item : dataItems) {
            if (item.isChecked()) {
                adapter.add(item);
            }
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.new_game) {
            // Người dùng đã chọn "Remove all"
            // Xóa tất cả các mục trong danh sách
            new AlertDialog.Builder(bai2.this)
                    .setTitle("Delete All Items")
                    .setMessage("Do you want to delete all items?")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Xóa tất cả các mục
                            dataItems.clear();
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show(); // Cập nhật giao diện
            return true;
        } else if (id == R.id.help) {
            // Người dùng đã chọn "About"
            // Xử lý About ở đây nếu cần
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_EVENT_REQUEST_CODE && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String place = data.getStringExtra("place");
            String date = data.getStringExtra("date");
            String time = data.getStringExtra("time");

            int positionToEdit = data.getIntExtra("position", -1);

            if (positionToEdit != -1) {
                // Đây là chế độ chỉnh sửa, cập nhật dữ liệu tại vị trí đó
                DataItem editedDataItem = new DataItem(name, place, date, time);
                dataItems.set(positionToEdit, editedDataItem);
                Toast.makeText(this, "Chỉnh sửa tại vị trí: " + positionToEdit, Toast.LENGTH_SHORT).show();
            } else {
                // Đây là chế độ thêm mới, thêm một mục mới
                dataItems.add(new DataItem(name, place, date, time));
                Toast.makeText(this, "Thêm mới mục", Toast.LENGTH_SHORT).show();
            }

            adapter.notifyDataSetChanged();
        }
    }
}