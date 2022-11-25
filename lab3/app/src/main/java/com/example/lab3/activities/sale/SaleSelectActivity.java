package com.example.lab3.activities.sale;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab3.R;
import com.example.lab3.database.AppDatabase;

public class SaleSelectActivity extends AppCompatActivity {

    AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_select);

        mDb = AppDatabase.getInstance(this);

        Button buttonFindByDate = findViewById(R.id.button_find_sale_by_date);
        buttonFindByDate.setOnClickListener(view -> goToSelectByDateResult());

        Button buttonFindByManagerId = findViewById(R.id.button_find_sale_by_manager_id);
        buttonFindByManagerId.setOnClickListener(view -> goToSelectByManagerIdResult());

        Button buttonFindBySaleId = findViewById(R.id.button_find_sale_by_sale_id);
        buttonFindBySaleId.setOnClickListener(view -> goToSelectBySaleIdResult());
    }

    private void goToSelectByDateResult() {
        Intent intent = new Intent(this, SaleSelectResultActivity.class);
        TextView textDate = findViewById(R.id.dateInput);
        String date = textDate.getText().toString();
        intent.putExtra("Date", date);
        startActivity(intent);
    }

    private void goToSelectBySaleIdResult() {
        Intent intent = new Intent(this, SaleSelectResultActivity.class);
        TextView textSaleId = findViewById(R.id.saleIdInput);
        String saleId = textSaleId.getText().toString();
        intent.putExtra("Sale ID", saleId);
        startActivity(intent);
    }

    private void goToSelectByManagerIdResult() {
        Intent intent = new Intent(this, SaleSelectResultActivity.class);
        TextView textManagerId = findViewById(R.id.managerIdInput);
        String managerId = textManagerId.getText().toString();
        intent.putExtra("Manager ID", managerId);
        startActivity(intent);
    }
}