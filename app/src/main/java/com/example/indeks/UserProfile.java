package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserProfile extends AppCompatActivity {

    private TextView studentName;
    private TextView studentSurname;
    private TextView studentPesel;
    private TextView studentStreet;
    private TextView studentCity;
    private TextView studentPhoneNumber;
    private DatabaseReference databaseReference;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        studentName = (TextView) findViewById(R.id.textViewValue_1);
        studentSurname = (TextView) findViewById(R.id.textViewValue_2);
        studentPesel = (TextView) findViewById(R.id.textViewValue_3);
        studentStreet = (TextView) findViewById(R.id.textViewValue_4);
        studentCity = (TextView) findViewById(R.id.textViewValue_5);
        studentPhoneNumber = (TextView) findViewById(R.id.textViewValue_6);
        image = (ImageView) findViewById(R.id.imageViewStudent);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Students").child(userID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Name").getValue().toString();
                String surname = dataSnapshot.child("Surname").getValue().toString();
                String pesel_id = dataSnapshot.child("Pesel").getValue().toString();
                String street = dataSnapshot.child("Street").getValue().toString();
                String city = dataSnapshot.child("City").getValue().toString();
                String phoneNumber = dataSnapshot.child("PhoneNumber").getValue().toString();

                studentName.setText(name);
                studentSurname.setText(surname);
                studentPesel.setText(pesel_id);
                studentStreet.setText(street);
                studentCity.setText(city);
                studentPhoneNumber.setText(phoneNumber);
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
