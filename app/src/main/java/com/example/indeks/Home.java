package com.example.indeks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewUserEmail;
    private Button buttonLogOut;
    private Button buttonUserProfile;
    private Button buttonCalendar;
    private Button buttonSett;
    private Button buttonSendMail;
    private Button buttonGrades;
    private Button buttonTeachers;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(Home.this, MainActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Witaj " + user.getEmail());

        buttonLogOut = (Button) findViewById(R.id.buttonLogOut);
        buttonUserProfile = (Button) findViewById(R.id.buttonUserProfile);
        buttonCalendar = (Button) findViewById(R.id.buttonCalendar);
        buttonSett = (Button) findViewById(R.id.buttonSettings);
        buttonSendMail = (Button) findViewById(R.id.buttonEmail);
        buttonGrades = (Button) findViewById(R.id.buttonGrades);
        buttonTeachers = (Button) findViewById(R.id.buttonTeachers);

        buttonLogOut.setOnClickListener(this);
        buttonUserProfile.setOnClickListener(this);
        buttonCalendar.setOnClickListener(this);
        buttonSett.setOnClickListener(this);
        buttonSendMail.setOnClickListener(this);
        buttonGrades.setOnClickListener(this);
        buttonTeachers.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonLogOut) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(Home.this, MainActivity.class));
        }

        if (v == buttonUserProfile) {
            startActivity(new Intent(Home.this, UserProfile.class)); }

        if (v == buttonSett) {
            startActivity(new Intent(Home.this, Settings.class)); }

        if (v == buttonSendMail) {
            startActivity(new Intent(Home.this, SendEmail.class)); }

        if (v == buttonGrades) {
            startActivity(new Intent(Home.this, Grades.class)); }

        if (v == buttonTeachers) {
            startActivity(new Intent(Home.this, Teachers.class)); }

        if (v == buttonCalendar) {
            startActivity(new Intent(Home.this, Calendar.class)); }
    }
}
