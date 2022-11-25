package com.example.lab3.adapters.sale;

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
import com.example.lab3.activities.sale.SaleEditActivity;
import com.example.lab3.constants.Constants;
import com.example.lab3.database.AppDatabase;
import com.example.lab3.entities.Sale;

import java.util.List;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.MyViewHolder> {
    private Context context;
    private List<Sale> mSaleList;

    public SaleAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.sale_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.saleId.setText(mSaleList.get(i).getSaleId());
        myViewHolder.managerId.setText(mSaleList.get(i).getManagerId());
        myViewHolder.date.setText(mSaleList.get(i).getDate());
        myViewHolder.cost.setText(mSaleList.get(i).getCost());
        myViewHolder.loan.setText(mSaleList.get(i).getLoan());
    }

    @Override
    public int getItemCount() {
        if (mSaleList == null) {
            return 0;
        }
        return mSaleList.size();

    }

    public void setTasks(List<Sale> saleList) {
        mSaleList = saleList;
        notifyDataSetChanged();
    }

    public List<Sale> getTasks() {
        return mSaleList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView saleId, managerId, cost, date, loan;
        ImageView editImage;
        AppDatabase mDb;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            saleId = itemView.findViewById(R.id.manager_id);
            managerId = itemView.findViewById(R.id.manager_name);
            cost = itemView.findViewById(R.id.manager_surname);
            date = itemView.findViewById(R.id.manager_phone);
            loan = itemView.findViewById(R.id.manager_email);
            editImage = itemView.findViewById(R.id.edit_Image);
            editImage.setOnClickListener(v -> {
                String elementId = mSaleList.get(getAdapterPosition()).getSaleId();
                Intent intent = new Intent(context, SaleEditActivity.class);
                intent.putExtra(Constants.UPDATE_Sale_Id, elementId);
                context.startActivity(intent);
            });
        }
    }
}
