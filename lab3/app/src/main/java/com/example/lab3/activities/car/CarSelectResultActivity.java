package com.example.lab3.activities.car;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lab3.R;
import com.example.lab3.adapters.car.CarAdapter;
import com.example.lab3.database.AppDatabase;
import com.example.lab3.database.AppExecutors;
import com.example.lab3.entities.Car;

import java.util.List;

public class CarSelectResultActivity extends AppCompatActivity {

    private Intent intent;
    private RecyclerView mRecyclerView;
    private CarAdapter mAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_select_result);
        mDb = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();

        mRecyclerView = findViewById(R.id.carSelectRecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new CarAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveTasks();
    }

    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(() -> {
            if (intent.hasExtra("VIN number")) {
                final List<Car> selectedCars = mDb.carDao().loadCarsByVin(intent.getStringExtra("VIN number"));
                runOnUiThread(() -> mAdapter.setTasks(selectedCars));
            }
            else if (intent.hasExtra("Sale ID")) {
                final List<Car> selectedCars = mDb.carDao().loadCarsBySaleId(intent.getStringExtra("Sale ID"));
                runOnUiThread(() -> mAdapter.setTasks(selectedCars));
            }
        });


    }
}