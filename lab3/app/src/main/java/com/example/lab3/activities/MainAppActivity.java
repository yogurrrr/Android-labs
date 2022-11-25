package com.example.lab3.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.lab3.R;
import com.example.lab3.activities.car.CarMainActivity;
import com.example.lab3.activities.sale.SaleMainActivity;

public class MainAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        Button buttonGoToCars = findViewById(R.id.button_go_to_cars);
        buttonGoToCars.setOnClickListener(view -> goToCars());

        Button buttonGoToSales = findViewById(R.id.button_go_to_sales);
        buttonGoToSales.setOnClickListener(view -> goToSales());

        Button buttonGoToManagers = findViewById(R.id.button_go_to_managers);
        buttonGoToManagers.setOnClickListener(view -> goToManagers());
    }

    public void goToCars() {
        Intent intent = new Intent(this, CarMainActivity.class);
        startActivity(intent);
    }

    public void goToSales() {
        Intent intent = new Intent(this, SaleMainActivity.class);
        startActivity(intent);
    }

    public void goToManagers() {
        Intent intent = new Intent(this, CarMainActivity.class);
        //TODO
        // поменять на ManagerMainActivity
        startActivity(intent);
    }
}