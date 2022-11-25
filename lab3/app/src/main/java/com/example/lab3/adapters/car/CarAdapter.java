package com.example.lab3.adapters.car;

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
import com.example.lab3.activities.car.CarEditActivity;
import com.example.lab3.constants.Constants;
import com.example.lab3.database.AppDatabase;
import com.example.lab3.entities.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {
    private Context context;
    private List<Car> mCarList;

    public CarAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.brand.setText(mCarList.get(i).getBrand());
        myViewHolder.model.setText(mCarList.get(i).getModel());
        myViewHolder.vinNumber.setText(mCarList.get(i).getVinNumber());
        myViewHolder.saleId.setText(mCarList.get(i).getSaleId());
        myViewHolder.mileage.setText(mCarList.get(i).getMileage());
    }

    @Override
    public int getItemCount() {
        if (mCarList == null) {
            return 0;
        }
        return mCarList.size();

    }

    public void setTasks(List<Car> carList) {
        mCarList = carList;
        notifyDataSetChanged();
    }

    public List<Car> getTasks() {

        return mCarList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView brand, model, saleId, vinNumber, mileage;
        ImageView editImage;
        AppDatabase mDb;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            brand = itemView.findViewById(R.id.manager_id);
            model = itemView.findViewById(R.id.manager_name);
            saleId = itemView.findViewById(R.id.manager_surname);
            vinNumber = itemView.findViewById(R.id.manager_phone);
            mileage = itemView.findViewById(R.id.manager_email);
            editImage = itemView.findViewById(R.id.edit_Image);
            editImage.setOnClickListener(v -> {
                int elementId = mCarList.get(getAdapterPosition()).getCarId();
                Intent intent = new Intent(context, CarEditActivity.class);
                intent.putExtra(Constants.UPDATE_Car_Id, elementId);
                context.startActivity(intent);
            });
        }
    }
}
