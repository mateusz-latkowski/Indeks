package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Deanery extends AppCompatActivity {

    private TextView textViewDepartment;
    private TextView textViewEmployee;
    private TextView textViewEmail;
    private TextView textViewPhoneNumber;
    private TextView textViewRoom;

    private TextView textViewMonday;
    private TextView textViewTuesday;
    private TextView textViewWednesday;
    private TextView textViewThursday;
    private TextView textViewFriday;

    private TextView textViewBoss;
    private TextView textViewBossEmail;
    private TextView textViewBossRoom;

    private TextView textViewBossFriday;

    private String study;
    private String userID;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deanery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("DZIEKANAT");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        textViewDepartment = findViewById(R.id.textViewDepartment);
        textViewEmployee = findViewById(R.id.textViewEmployee);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);
        textViewRoom = findViewById(R.id.textViewRoom);

        textViewMonday = findViewById(R.id.textViewRowMondayHours);
        textViewTuesday = findViewById(R.id.textViewRowTuesdayyHours);
        textViewWednesday = findViewById(R.id.textViewRowWednesdayHours);
        textViewThursday = findViewById(R.id.textViewRowThursdayHours);
        textViewFriday = findViewById(R.id.textViewRowFridayHours);

        textViewBoss = findViewById(R.id.textViewBoss);
        textViewBossEmail = findViewById(R.id.textViewBossEmail);
        textViewBossRoom = findViewById(R.id.textViewBossRoom);

        textViewBossFriday = findViewById(R.id.textViewRowFridayBossHours);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Students").child("Students_Info").child(userID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                study = dataSnapshot.child("Study").getValue().toString();
                deanery(study);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private void deanery(String study) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Deanery").child(study);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String department = dataSnapshot.child("Wydział").getValue().toString();
                String employee = dataSnapshot.child("Pracownik").getValue().toString();
                String email = dataSnapshot.child("Email").getValue().toString();
                String phoneNumber = dataSnapshot.child("Telefon").getValue().toString();
                String room = dataSnapshot.child("Pokój").getValue().toString();
                String monday = dataSnapshot.child("Godziny").child("Poniedziałek").getValue().toString();
                String tuesday = dataSnapshot.child("Godziny").child("Wtorek").getValue().toString();
                String wednesday = dataSnapshot.child("Godziny").child("Środa").getValue().toString();
                String thursday = dataSnapshot.child("Godziny").child("Czwartek").getValue().toString();
                String friday = dataSnapshot.child("Godziny").child("Piątek").getValue().toString();
                String boss = dataSnapshot.child("Prodziekan").child("Pracownik").getValue().toString();
                String bossEmail = dataSnapshot.child("Prodziekan").child("Email").getValue().toString();
                String bossRoom = dataSnapshot.child("Prodziekan").child("Pokój").getValue().toString();
                String bossFriday = dataSnapshot.child("Prodziekan").child("Godziny").child("Piątek").getValue().toString();

                textViewDepartment.setText(department);
                textViewEmployee.setText(employee);
                textViewEmail.setText(email);
                textViewPhoneNumber.setText(phoneNumber);
                textViewRoom.setText(room);

                textViewMonday.setText(monday);
                textViewTuesday.setText(tuesday);
                textViewWednesday.setText(wednesday);
                textViewThursday.setText(thursday);
                textViewFriday.setText(friday);

                textViewBoss.setText(boss);
                textViewBossEmail.setText(bossEmail);
                textViewBossRoom.setText(bossRoom);

                textViewBossFriday.setText(bossFriday);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean OnCreateOptionsMenu(Menu menu) {
        return true;
    }
}
