package com.example.lab3.activities.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab3.R;
import com.example.lab3.constants.Constants;
import com.example.lab3.database.AppDatabase;
import com.example.lab3.database.AppExecutors;
import com.example.lab3.entities.Manager;

public class ManagerEditActivity extends AppCompatActivity {
    EditText managerId, name, surname, phone, email;
    Button button;
    String mManagerId;
    Intent intent;
    private AppDatabase mDb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_activity_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initViews();
        mDb = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Manager_Id)) {
//            button.setText("Update");
            button.setText(R.string.button_edit_text);

            mManagerId = intent.getStringExtra(Constants.UPDATE_Manager_Id);

            AppExecutors.getInstance().diskIO().execute(() -> {
                Manager manager = mDb.managerDao().loadManagerById(mManagerId);
                populateUI(manager);
            });


        }

    }

    private void populateUI(Manager manager) {

        if (manager == null) {
            return;
        }

        managerId.setText(manager.getManagerId());
        name.setText(manager.getName());
        surname.setText(manager.getSurname());
        phone.setText(manager.getPhone());
        email.setText(manager.getEmail());
    }

    private void initViews() {
        managerId = findViewById(R.id.edit_manager_id);
        name = findViewById(R.id.edit_manager_name);
        surname = findViewById(R.id.edit_manager_surname);
        phone = findViewById(R.id.edit_manager_phone);
        email = findViewById(R.id.edit_manager_email);
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> onSaveButtonClicked());
    }

    public void onSaveButtonClicked() {
        final Manager manager = new Manager(
                managerId.getText().toString(),
                name.getText().toString(),
                surname.getText().toString(),
                phone.getText().toString(),
                email.getText().toString());

        AppExecutors.getInstance().diskIO().execute(() -> {
            if (!intent.hasExtra(Constants.UPDATE_Manager_Id)) {
                mDb.managerDao().insertManager(manager);
            } else {
                manager.setManagerId(mManagerId);
                mDb.managerDao().updateManager(manager);
            }
            finish();
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Respond to the action bar's Up/Home button
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
