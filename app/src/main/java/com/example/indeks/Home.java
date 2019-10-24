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

        buttonLogOut.setOnClickListener(this);
        buttonUserProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonLogOut) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(Home.this, MainActivity.class));
        }

        if (v == buttonUserProfile) {
            startActivity(new Intent(Home.this, UserProfil.class));
        }
    }
}
