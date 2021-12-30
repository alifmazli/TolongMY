package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitButtonHandlerHelper(View view) {
        openHelper();
    }

    public void openHelper() {
        Intent intent = new Intent(this, MapsActivityHelper.class);
        startActivity(intent);
    }

    public void submitButtonHandlerVictim(View view) {
        openVictim();
    }

    public void openVictim() {
        Intent intent = new Intent(this, VictimRegistrationPage.class);
        startActivity(intent);
    }
}