package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class VictimRegistrationPageActivity extends AppCompatActivity {

    public List<Victim> victimList = new ArrayList<>();
    GoogleLocationTracker googleLocationTracker = new GoogleLocationTracker();
    Victim victim = new Victim();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim_registration_page);
    }

    public void submitButtonHandlerEnter(View view) {
        EditText editName = (EditText)findViewById(R.id.name);
        String name = editName.getText().toString();

        EditText editIcNum = (EditText)findViewById(R.id.icnumber);
        String icNum = editIcNum.getText().toString();

        EditText editPhoneNum = (EditText)findViewById(R.id.phone);
        String phoneNum = editPhoneNum.getText().toString();

        trackLocation();
        victim.storeVictimDetails((int)googleLocationTracker.getLatitude(),(int) googleLocationTracker.getLongitude(), name, icNum, phoneNum);
        //victimList.add(person);
    }

    public void trackLocation() {
        Intent track = new Intent(VictimRegistrationPageActivity.this, GoogleLocationTracker.class);
        startActivity(track);
    }
}