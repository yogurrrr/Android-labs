package com.example.lab3.adapters.manager;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab3.R;
import com.example.lab3.activities.manager.ManagerEditActivity;
import com.example.lab3.constants.Constants;
import com.example.lab3.database.AppDatabase;
import com.example.lab3.entities.Manager;

import java.util.List;

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.MyViewHolder> {
    private Context context;
    private List<Manager> mManagerList;

    public ManagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.manager_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.managerId.setText(mManagerList.get(i).getManagerId());
        myViewHolder.name.setText(mManagerList.get(i).getName());
        myViewHolder.surname.setText(mManagerList.get(i).getSurname());
        myViewHolder.phone.setText(mManagerList.get(i).getPhone());
        myViewHolder.email.setText(mManagerList.get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        if (mManagerList == null) {
            return 0;
        }
        return mManagerList.size();

    }

    public void setTasks(List<Manager> managerList) {
        mManagerList = managerList;
        notifyDataSetChanged();
    }

    public List<Manager> getTasks() {

        return mManagerList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView managerId, name, surname, phone, email;
        ImageView editImage;
        AppDatabase mDb;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            managerId = itemView.findViewById(R.id.manager_id);
            name = itemView.findViewById(R.id.manager_name);
            surname = itemView.findViewById(R.id.manager_surname);
            phone = itemView.findViewById(R.id.manager_phone);
            email = itemView.findViewById(R.id.manager_email);
            editImage = itemView.findViewById(R.id.edit_Image);
            editImage.setOnClickListener(v -> {
                String elementId = mManagerList.get(getAdapterPosition()).getManagerId();
                Intent intent = new Intent(context, ManagerEditActivity.class);
                intent.putExtra(Constants.UPDATE_Manager_Id, elementId);
                context.startActivity(intent);
            });
        }
    }
}
