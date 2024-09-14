package vn.edu.tdtu.bai1_lab5;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewholder> {
    Context context;
    ArrayList<item> list;
    ArrayList<Integer> list_pos_remove= new ArrayList<>();
    Boolean check_state = false;

    public MyAdapter(Context context, ArrayList<item> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewholder(LayoutInflater.from(context).inflate(R.layout.itemview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        item item = list.get(position);
        holder.textView.setText(list.get(holder.getAdapterPosition()).getName());

        holder.checkBox.setChecked(list.get(holder.getAdapterPosition()).isCheck());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check) {

//                list.get(holder.getAdapterPosition()).setCheck(compoundButton.isChecked());

                if(check){

                    list_pos_remove.add(holder.getAdapterPosition());
                    Log.e("TAG", "onClick: add " + holder.getAdapterPosition()+ "  list size: "+ getRemoveCount());
                    Log.d("TAG", "onClick: add " + holder.getAdapterPosition()+ "list remove:" + list_pos_remove);
                }else{

                    list_pos_remove.remove((Integer) holder.getAdapterPosition());
                    Log.e("TAG", "onClick: remove " + holder.getAdapterPosition()+ "  list size: "+getRemoveCount());
                }

            }
        });


    }
    //xoa item duoc chon
    public void delete_selected_item(){
        if(list_pos_remove.isEmpty()){
            return;
        }

        ArrayList<item> temp = new ArrayList<>();

        for(Integer index : list_pos_remove){
            if(index < list.size()){
                temp.add(list.get(index));
                Log.e("TAG", "delete_selected_item: "+list.get(index).getName() );

            }
        }
        list.removeAll(temp);
        list_pos_remove.clear();
        notifyDataSetChanged();


    }
    //xoa toan bo item
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
//        Log.e("TAG", String.valueOf(getItemCount()));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public int getRemoveCount() {
        return list_pos_remove.size();
    }
    public void checkAllitem(){
        if(list_pos_remove.isEmpty()){
            for(item i : list){
                i.setCheck(true);
            }
        }else {
            for(item i : list){
                i.setCheck(false);
            }
        }
        notifyDataSetChanged();

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
