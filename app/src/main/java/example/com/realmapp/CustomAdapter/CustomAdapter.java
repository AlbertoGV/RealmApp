package example.com.realmapp.CustomAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.realmapp.Model.User;
import example.com.realmapp.R;

public class CustomAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context c;
    ArrayList<User> user;

    public CustomAdapter(Context c, ArrayList<User> user) {
        this.c = c;
        this.user = user;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(c).inflate(R.layout.list_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        User u = user.get(i);

        myViewHolder.txtName.setText(u.getUser_name());
    }

    @Override
    public int getItemCount() {
        return user.size();
    }
}
