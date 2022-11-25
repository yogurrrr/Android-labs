package com.example.lab3.activities.manager;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab3.R;
import com.example.lab3.database.AppDatabase;

public class ManagerJoinActivity extends AppCompatActivity {

    AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_join);

        mDb = AppDatabase.getInstance(this);

        Button buttonFindByVin = findViewById(R.id.button_manager_join);
        buttonFindByVin.setOnClickListener(view -> goToManagerJoinResult());
    }

    private void goToManagerJoinResult() {
        Intent intent = new Intent(this, ManagerJoinResultActivity.class);
        TextView textManagerId = findViewById(R.id.managerIdInput);
        String managerId = textManagerId.getText().toString();
        intent.putExtra("Manager ID", managerId);
        startActivity(intent);
    }

}