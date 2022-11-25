package com.example.lab3.activities.sale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lab3.R;
import com.example.lab3.adapters.car.CarAdapter;
import com.example.lab3.adapters.manager.ManagerAdapter;
import com.example.lab3.adapters.sale.SaleAdapter;
import com.example.lab3.database.AppDatabase;
import com.example.lab3.database.AppExecutors;
import com.example.lab3.entities.Car;
import com.example.lab3.entities.Manager;
import com.example.lab3.entities.Sale;

import java.util.List;

public class ManagerSelectResultActivity extends AppCompatActivity {

    private Intent intent;
    private RecyclerView mRecyclerView;
    private ManagerAdapter mAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_select_result);
        mDb = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();

        mRecyclerView = findViewById(R.id.managerSelectRecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new ManagerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveTasks();
    }

    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(() -> {
            if (intent.hasExtra("Surname")) {
                final List<Manager> selectedManagers = mDb.managerDao().loadManagersBySurname(intent.getStringExtra("Surname"));
                runOnUiThread(() -> mAdapter.setTasks(selectedManagers));
            }
            else if (intent.hasExtra("Email")) {
                final List<Manager> selectedSales = mDb.managerDao().loadManagersByEmail(intent.getStringExtra("Email"));
                runOnUiThread(() -> mAdapter.setTasks(selectedSales));
            }
            else if (intent.hasExtra("Manager ID")) {
                final List<Manager> selectedSales = mDb.managerDao().loadManagersByManagerId(intent.getStringExtra("Manager ID"));
                runOnUiThread(() -> mAdapter.setTasks(selectedSales));
            }
        });


    }
}