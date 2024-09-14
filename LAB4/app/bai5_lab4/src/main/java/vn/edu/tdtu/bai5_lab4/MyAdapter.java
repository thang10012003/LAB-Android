package vn.edu.tdtu.bai5_lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvUser.setText(list.get(position).getUser());
        holder.tvEmail.setText(list.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void  addItem(int pos){
        list.add(new User(pos));
        notifyItemInserted(pos);
    }
    public void  removeItem(int pos){
        list.remove(pos);
        notifyItemRemoved(pos);
    }
    public  class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvUser;
        TextView tvEmail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
