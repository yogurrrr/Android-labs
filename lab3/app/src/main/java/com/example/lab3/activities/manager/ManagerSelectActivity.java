package com.example.lab3.activities.manager;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab3.R;
import com.example.lab3.activities.sale.ManagerSelectResultActivity;
import com.example.lab3.database.AppDatabase;

public class ManagerSelectActivity extends AppCompatActivity {

    AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_select);

        mDb = AppDatabase.getInstance(this);

        Button buttonFindBySurname = findViewById(R.id.button_find_manager_by_surname);
        buttonFindBySurname.setOnClickListener(view -> goToSelectBySurnameResult());

        Button buttonFindByEmail = findViewById(R.id.button_find_manager_by_email);
        buttonFindByEmail.setOnClickListener(view -> goToSelectByEmailResult());

        Button buttonFindByManagerId = findViewById(R.id.button_find_manager_by_manager_id);
        buttonFindByManagerId.setOnClickListener(view -> goToSelectByManagerIdResult());
    }

    private void goToSelectBySurnameResult() {
        Intent intent = new Intent(this, ManagerSelectResultActivity.class);
        TextView textSurname = findViewById(R.id.surnameInput);
        String surname = textSurname.getText().toString();
        intent.putExtra("Surname", surname);
        startActivity(intent);
    }

    private void goToSelectByEmailResult() {
        Intent intent = new Intent(this, ManagerSelectResultActivity.class);
        TextView textEmail = findViewById(R.id.emailInput);
        String email = textEmail.getText().toString();
        intent.putExtra("Email", email);
        startActivity(intent);
    }

    private void goToSelectByManagerIdResult() {
        Intent intent = new Intent(this, ManagerSelectResultActivity.class);
        TextView textManagerId = findViewById(R.id.managerIdInput);
        String managerId = textManagerId.getText().toString();
        intent.putExtra("Manager ID", managerId);
        startActivity(intent);
    }
}