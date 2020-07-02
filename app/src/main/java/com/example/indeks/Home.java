package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewUserName;

    private CardView buttonGrades;
    private CardView buttonLessonPlan;
    private CardView buttonCalendar;
    private CardView buttonTeachers;
    private CardView buttonChat;
    private CardView buttonSendMail;
    private CardView buttonUserProfile;
    private CardView buttonSett;
    private CardView buttonDeanery;
    private CardView buttonLogOut;

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

        buttonGrades = findViewById(R.id.cardViewGrades);
        buttonLessonPlan = findViewById(R.id.cardViewLessonPlan);
        buttonCalendar = findViewById(R.id.cardViewCalendar);
        buttonTeachers = findViewById(R.id.cardViewTeachers);
        buttonChat = findViewById(R.id.cardViewChat);
        buttonSendMail = findViewById(R.id.cardViewEmail);
        buttonUserProfile = findViewById(R.id.cardViewUserProfile);
        buttonSett = findViewById(R.id.cardViewSettings);
        buttonDeanery = findViewById(R.id.cardViewDeanery);
        buttonLogOut = findViewById(R.id.cardViewLogout);

        buttonGrades.setOnClickListener(this);
        buttonLessonPlan.setOnClickListener(this);
        buttonCalendar.setOnClickListener(this);
        buttonTeachers.setOnClickListener(this);
        buttonChat.setOnClickListener(this);
        buttonSendMail.setOnClickListener(this);
        buttonUserProfile.setOnClickListener(this);
        buttonSett.setOnClickListener(this);
        buttonDeanery.setOnClickListener(this);
        buttonLogOut.setOnClickListener(this);
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

        if (v == buttonDeanery) {
            startActivity(new Intent(Home.this, Deanery.class));
        }
    }
}
