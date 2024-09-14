package vn.edu.tdtu.bai2_lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<DataItem> {
    public CustomAdapter(Context context, int resource, List<DataItem> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.layout_listview, null);
        }

        // Lấy ra dữ liệu từ danh sách
        DataItem dataItem = getItem(position);

        // Tìm các thành phần trong custom_layout.xml và gán dữ liệu vào chúng
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvPlace = view.findViewById(R.id.tvPlace);
        TextView tvDate = view.findViewById(R.id.tvDate);
        TextView tvTime = view.findViewById(R.id.tvTime);
        Switch switchItem = view.findViewById(R.id.switchItem);



        if (dataItem != null) {
            tvName.setText(dataItem.getName());
            tvPlace.setText(dataItem.getPlace());
            tvDate.setText(dataItem.getDate());
            tvTime.setText(dataItem.getTime());
            switchItem.setChecked(dataItem.isChecked());
            // Gán dữ liệu cho Switch hoặc thực hiện các hành động khác tùy theo dữ liệu
            switchItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    dataItem.setChecked(isChecked);
                }
            });
        }

        return view;
    }
    public boolean[] getSwitchStates() {
        boolean[] switchStates = new boolean[getCount()];
        for (int i = 0; i < getCount(); i++) {
            DataItem dataItem = getItem(i);
            if (dataItem != null) {
                switchStates[i] = dataItem.isChecked();
            }
        }
        return switchStates;
    }
    public DataItem[] getDataItems() {
        DataItem[] items = new DataItem[getCount()];
        for (int i = 0; i < getCount(); i++) {
            items[i] = getItem(i);
        }
        return items;
    }
    public void removeUncheckedItems() {
        for (int i = getCount() - 1; i >= 0; i--) {
            DataItem dataItem = getItem(i);
            if (dataItem != null && !dataItem.isChecked()) {
                remove(dataItem);
            }
        }
        notifyDataSetChanged();
    }

}
