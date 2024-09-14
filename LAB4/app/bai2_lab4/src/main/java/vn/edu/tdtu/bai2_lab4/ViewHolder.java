package vn.edu.tdtu.bai2_lab4;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public Button button;
    public ViewHolder(View view) {
        super(view);
        // Define click listener for the ViewHolder's View

        textView = (TextView) view.findViewById(R.id.item);
        button = (Button) view.findViewById(R.id.bDelete);

    }

    public TextView getTextView() {
        return textView;
    }
}
