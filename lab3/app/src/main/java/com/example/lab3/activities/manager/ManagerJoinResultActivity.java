package com.example.lab3.activities.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lab3.R;
import com.example.lab3.adapters.car.CarAdapter;
import com.example.lab3.adapters.sale.SaleAdapter;
import com.example.lab3.database.AppDatabase;
import com.example.lab3.database.AppExecutors;
import com.example.lab3.entities.Car;
import com.example.lab3.entities.Sale;

import java.util.List;

public class ManagerJoinResultActivity extends AppCompatActivity {

    private Intent intent;
    private RecyclerView mRecyclerView;
    private SaleAdapter mAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_join_result);
        mDb = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();

        mRecyclerView = findViewById(R.id.managerJoinRecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new SaleAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveTasks();
    }

    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(() -> {
            if (intent.hasExtra("Manager ID")) {
                final List<Sale> sales = mDb.managerDao().loadAllManagersAndSales(intent.getStringExtra("Manager ID"));
                runOnUiThread(() -> mAdapter.setTasks(sales));
            }
        });


    }
}