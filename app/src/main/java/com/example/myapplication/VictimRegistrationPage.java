package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class VictimRegistrationPage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
    }

    public void submitButtonHandlerEnter(View view) {
        EditText edit1 = (EditText)findViewById(R.id.name);
        String Name = edit1.getText().toString();
        EditText edit2 = (EditText)findViewById(R.id.icnumber);
        String ICNum = edit2.getText().toString();
        EditText edit3 = (EditText)findViewById(R.id.phone);
        String PhoneNum = edit3.getText().toString();

        trackLocation();
    }

    public void trackLocation() {
        Intent intent = new Intent(this, GoogleLocationTracker.class);
        startActivity(intent);
    }
}