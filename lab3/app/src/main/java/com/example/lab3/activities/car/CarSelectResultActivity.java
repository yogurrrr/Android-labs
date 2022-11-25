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
//    private List<Car> selectedCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_select_result);
        mDb = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
//        selectedCars = mDb.carDao().loadCarByVin(intent.getStringExtra("VIN number"));

        mRecyclerView = findViewById(R.id.carSelectRecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new CarAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
//        retrieveTasks(selectedCars);

//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            // Called when a user swipes left or right on a ViewHolder
//            @Override
//            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
//                // Here is where you'll implement swipe to delete
//                AppExecutors.getInstance().diskIO().execute(() -> {
//                    int position = viewHolder.getAdapterPosition();
//                    List<Car> tasks = mAdapter.getTasks();
//                    mDb.carDao().deleteCar(tasks.get(position));
//                    retrieveTasks();
//                });
//            }
//        }).attachToRecyclerView(mRecyclerView);

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