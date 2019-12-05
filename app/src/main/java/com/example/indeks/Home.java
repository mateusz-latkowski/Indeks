package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewUserName;
    private Button buttonLogOut;
    private ImageButton buttonUserProfile;
    private ImageButton buttonCalendar;
    private ImageButton buttonSett;
    private ImageButton buttonSendMail;
    private ImageButton buttonGrades;
    private ImageButton buttonTeachers;
    private ImageButton buttonChat;
    private ImageButton buttonLessonPlan;
    private FirebaseAuth firebaseAuth;

    private String name;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userID = user.getUid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Students").child("Students_Info").child(userID);

        textViewUserName = findViewById(R.id.textViewUserName);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("Name").getValue().toString();
                textViewUserName.setText("Witaj " + name + "! Kliknij w jedną z ikon!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonLogOut = findViewById(R.id.buttonLogout);
        buttonUserProfile = findViewById(R.id.imageButtonUserProfile);
        buttonCalendar = findViewById(R.id.imageButtonCalendar);
        buttonSett = findViewById(R.id.imageButtonSettings);
        buttonSendMail = findViewById(R.id.imageButtonEmail);
        buttonGrades = findViewById(R.id.imageButtonGrades);
        buttonTeachers = findViewById(R.id.imageButtonTeachers);
        buttonChat = findViewById(R.id.imageButtonChat);
        buttonLessonPlan = findViewById(R.id.imageButtonLessonPlan);

        buttonLogOut.setOnClickListener(this);
        buttonUserProfile.setOnClickListener(this);
        buttonCalendar.setOnClickListener(this);
        buttonSett.setOnClickListener(this);
        buttonSendMail.setOnClickListener(this);
        buttonGrades.setOnClickListener(this);
        buttonTeachers.setOnClickListener(this);
        buttonChat.setOnClickListener(this);
        buttonLessonPlan.setOnClickListener(this);
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
            startActivity(new Intent(Home.this, CalendarEvent.class)); }

        if (v == buttonChat) {
            startActivity(new Intent(Home.this, Chat.class)); }

        if (v == buttonLessonPlan) {
            startActivity(new Intent(Home.this, LessonPlan.class)); }
    }
}
