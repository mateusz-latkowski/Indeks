package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class UserProfil extends AppCompatActivity {

    private TextView a, b, c, d, e, f;
    private DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        a = (TextView) findViewById(R.id.textViewValue_1);
        b = (TextView) findViewById(R.id.textViewValue_2);
        c = (TextView) findViewById(R.id.textViewValue_3);
        d = (TextView) findViewById(R.id.textViewValue_4);
        e = (TextView) findViewById(R.id.textViewValue_5);
        f = (TextView) findViewById(R.id.textViewValue_6);

        Intent intent = getIntent();
        String s_ID = intent.getStringExtra("student_ID");

        reff = FirebaseDatabase.getInstance().getReference().child("students").child(s_ID);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Name").getValue().toString();
                String surname = dataSnapshot.child("Surname").getValue().toString();
                String pesel_id = dataSnapshot.child("Pesel_ID").getValue().toString();
                String street = dataSnapshot.child("Street").getValue().toString();
                String city = dataSnapshot.child("City").getValue().toString();
                String phoneNumber = dataSnapshot.child("PhoneNumber").getValue().toString();

                a.setText(name);
                b.setText(surname);
                c.setText(pesel_id);
                d.setText(street);
                e.setText(city);
                f.setText(phoneNumber);
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
