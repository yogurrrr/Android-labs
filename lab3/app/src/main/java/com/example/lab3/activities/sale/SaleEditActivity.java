package com.example.lab3.activities.sale;

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
import com.example.lab3.entities.Sale;

public class SaleEditActivity extends AppCompatActivity {
    EditText saleId, managerId, cost, date, loan;
    Button button;
    String mSaleId;
    Intent intent;
    private AppDatabase mDb;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initViews();
        mDb = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Sale_Id)) {
//            button.setText("Update");
            button.setText(R.string.button_edit_text);

            mSaleId = intent.getStringExtra(Constants.UPDATE_Sale_Id);

            AppExecutors.getInstance().diskIO().execute(() -> {
                Sale sale = mDb.saleDao().loadSaleById(mSaleId);
                populateUI(sale);
            });


        }

    }

    private void populateUI(Sale sale) {

        if (sale == null) {
            return;
        }

        saleId.setText(sale.getSaleId());
        managerId.setText(sale.getManagerId());
        cost.setText(sale.getCost());
        date.setText(sale.getDate());
        loan.setText(sale.getLoan());
    }

    private void initViews() {
        saleId = findViewById(R.id.edit_manager_id);
        managerId = findViewById(R.id.edit_manager_name);
        cost = findViewById(R.id.edit_manager_surname);
        date = findViewById(R.id.edit_manager_phone);
        loan = findViewById(R.id.edit_manager_email);
        button = findViewById(R.id.button_go_to_car_selects);
        button.setOnClickListener(v -> onSaveButtonClicked());
    }

    public void onSaveButtonClicked() {
        final Sale sale = new Sale(
                saleId.getText().toString(),
                managerId.getText().toString(),
                cost.getText().toString(),
                date.getText().toString(),
                loan.getText().toString());

        AppExecutors.getInstance().diskIO().execute(() -> {
            if (!intent.hasExtra(Constants.UPDATE_Sale_Id)) {
                mDb.saleDao().insertSale(sale);
            } else {
                sale.setSaleId(mSaleId);
                mDb.saleDao().updateSale(sale);
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
