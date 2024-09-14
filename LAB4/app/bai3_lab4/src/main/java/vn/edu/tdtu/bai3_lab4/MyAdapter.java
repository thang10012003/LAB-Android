package vn.edu.tdtu.bai3_lab4;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewholder> {
    Context context;
    ArrayList<String> list;
    ArrayList<Integer> list_pos_remove;
    private SparseBooleanArray selectedItems; // Dùng để lưu trạng thái đánh dấu

    public MyAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
        list_pos_remove = new ArrayList<>();
    }


    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewholder(LayoutInflater.from(context).inflate(R.layout.itemview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        holder.textView.setText(list.get(position).toString());
        holder.checkBox.setTag(position);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = (Integer) holder.checkBox.getTag();
                if(holder.checkBox.isChecked()){
                    list_pos_remove.add(holder.getAdapterPosition());
                    Log.e("TAG", "onClick: add " + holder.getAdapterPosition()+ "list size: "+list_pos_remove.size() );
                }else {
                    for (Integer index : list_pos_remove){
                        if (index.equals(pos)){
                            list_pos_remove.remove(index);
                            Log.e("TAG", "onClick: remove " + holder.getAdapterPosition()+ "list size: "+list_pos_remove.size());
                        }
                    }
                }
            }
        });

    }
    public void delete_selected_item(){
        for(Integer pos : list_pos_remove){
            removeItem(pos);
//            int pos1 = pos;
//            list.remove(pos1);
//            notifyItemRemoved(pos1);
        }
        list_pos_remove.clear();
    }
    public void delete_all_item(){
        int num = getItemCount();
        for (int i = num-1; i >=0 ; i--) {
            removeItem(i);
        }
        list_pos_remove.clear();
    }

    public void  removeItem(int pos){
        list.remove((pos));
        notifyItemRemoved(pos);
        Log.e("TAG", String.valueOf(getItemCount()));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView textView ;
        CheckBox checkBox;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tDevice);
            checkBox = itemView.findViewById(R.id.cbSelect);
        }
    }
}
