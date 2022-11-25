package com.example.lab3.activities.car;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab3.R;
import com.example.lab3.database.AppDatabase;

public class CarSelectActivity extends AppCompatActivity {

    AppDatabase mDb;
//    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_select);

        mDb = AppDatabase.getInstance(this);

        Button buttonFindByVin = findViewById(R.id.button_find_car_by_vin);
        buttonFindByVin.setOnClickListener(view -> goToSelectByVinResult());

        Button buttonFindBySaleId = findViewById(R.id.button_find_car_by_sale_id);
        buttonFindBySaleId.setOnClickListener(view -> goToSelectBySaleIdResult());
    }

    private void goToSelectByVinResult() {
        Intent intent = new Intent(this, CarSelectResultActivity.class);
        TextView textVinNumber = findViewById(R.id.vinNumberInput);
        String vinNumber = textVinNumber.getText().toString();
        intent.putExtra("VIN number", vinNumber);
        startActivity(intent);
    }

    private void goToSelectBySaleIdResult() {
        Intent intent = new Intent(this, CarSelectResultActivity.class);
        TextView textSaleId = findViewById(R.id.saleIdInput);
        String saleId = textSaleId.getText().toString();
        intent.putExtra("Sale ID", saleId);
        startActivity(intent);
    }
}