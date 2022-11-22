package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        Configuration config = new Configuration();
        if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_NO) {
            config.keyboardHidden = Configuration.KEYBOARDHIDDEN_NO;
            Toast.makeText(getApplicationContext(), "The keyboard has changed state.", Toast.LENGTH_LONG).show();
        }
        super.onConfigurationChanged(config);
    }

}