package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class VictimRegistrationPage extends AppCompatActivity {

    public List<Victim> victimList = new ArrayList<>();
    GoogleLocationTracker glt = new GoogleLocationTracker();
    Victim person = new Victim();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim_registration_page);
    }

    public void submitButtonHandlerEnter(View view) {
        //Victim person = new Victim();
        EditText edit1 = (EditText)findViewById(R.id.name);
        String name = edit1.getText().toString();
        EditText edit2 = (EditText)findViewById(R.id.icnumber);
        String icNum = edit2.getText().toString();
        EditText edit3 = (EditText)findViewById(R.id.phone);
        String phoneNum = edit3.getText().toString();
        trackLocation();
        person.storeVictimDetails(glt.getLatitude(), glt.getLongitude(), name, icNum, phoneNum);
        //victimList.add(person);
    }

    public void trackLocation() {
        Intent intent = new Intent(this, GoogleLocationTracker.class);
        startActivity(intent);
    }
}