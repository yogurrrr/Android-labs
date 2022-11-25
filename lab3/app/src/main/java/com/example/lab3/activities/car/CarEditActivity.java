package com.example.lab3.activities.car;

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
import com.example.lab3.entities.Car;

public class CarEditActivity extends AppCompatActivity {
    EditText brand, model, saleId, vinNumber, mileage;
    Button button;
    int mCarId;
    Intent intent;
    private AppDatabase mDb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initViews();
        mDb = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Car_Id)) {
            button.setText(R.string.button_edit_text);

            mCarId = intent.getIntExtra(Constants.UPDATE_Car_Id, -1);

            AppExecutors.getInstance().diskIO().execute(() -> {
                Car car = mDb.carDao().loadCarById(mCarId);
                populateUI(car);
            });


        }

    }

    private void populateUI(Car car) {

        if (car == null) {
            return;
        }

        brand.setText(car.getBrand());
        model.setText(car.getModel());
        saleId.setText(car.getSaleId());
        vinNumber.setText(car.getVinNumber());
        mileage.setText(car.getMileage());
    }

    private void initViews() {
        brand = findViewById(R.id.edit_manager_id);
        model = findViewById(R.id.edit_manager_name);
        saleId = findViewById(R.id.edit_manager_surname);
        vinNumber = findViewById(R.id.edit_manager_phone);
        mileage = findViewById(R.id.edit_manager_email);
        button = findViewById(R.id.button_go_to_car_selects);
        button.setOnClickListener(v -> onSaveButtonClicked());
    }

    public void onSaveButtonClicked() {
        final Car car = new Car(
                brand.getText().toString(),
                model.getText().toString(),
                saleId.getText().toString(),
                vinNumber.getText().toString(),
                mileage.getText().toString());

        AppExecutors.getInstance().diskIO().execute(() -> {
            if (!intent.hasExtra(Constants.UPDATE_Car_Id)) {
                mDb.carDao().insertCar(car);
            } else {
                car.setCarId(mCarId);
                mDb.carDao().updateCar(car);
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
