package example.com.realmapp.CustomAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import example.com.realmapp.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView txtName;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        txtName = itemView.findViewById(R.id.txt_name);
    }
}
