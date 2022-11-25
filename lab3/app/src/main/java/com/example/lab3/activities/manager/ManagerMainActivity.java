package com.example.lab3.activities.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.lab3.R;
import com.example.lab3.adapters.manager.ManagerAdapter;
import com.example.lab3.database.AppDatabase;
import com.example.lab3.database.AppExecutors;
import com.example.lab3.entities.Manager;

import java.util.List;

public class ManagerMainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    private RecyclerView mRecyclerView;
    private ManagerAdapter mAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_activity_main);

        floatingActionButton = findViewById(R.id.addFAB);

        floatingActionButton.setOnClickListener(v ->
                startActivity(new Intent(ManagerMainActivity.this, ManagerEditActivity.class)));

        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new ManagerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mDb = AppDatabase.getInstance(getApplicationContext());
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Here is where you'll implement swipe to delete
                AppExecutors.getInstance().diskIO().execute(() -> {
                    int position = viewHolder.getAdapterPosition();
                    List<Manager> tasks = mAdapter.getTasks();
                    mDb.managerDao().deleteManager(tasks.get(position));
                    retrieveTasks();
                });
            }
        }).attachToRecyclerView(mRecyclerView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveTasks();
    }

    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(() -> {
            final List<Manager> managers = mDb.managerDao().loadAllManagers();
            runOnUiThread(() -> mAdapter.setTasks(managers));
        });


    }
}
