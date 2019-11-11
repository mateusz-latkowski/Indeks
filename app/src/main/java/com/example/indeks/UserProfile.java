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

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserProfile extends AppCompatActivity {

    private TextView studentName;
    private TextView studentSurname;
    private TextView studentPesel;
    private TextView studentStreet;
    private TextView studentCity;
    private TextView studentPostalAddress;
    private TextView studentBirthdayDate;
    private TextView studentStudy;
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
        studentPostalAddress = (TextView) findViewById(R.id.textViewValue_7);
        studentBirthdayDate = (TextView) findViewById(R.id.textViewValue_8);
        studentStudy = (TextView) findViewById(R.id.textViewValue_9);
        image = (ImageView) findViewById(R.id.imageViewStudent);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Students").child(userID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("User_Information").child("Name").getValue().toString();
                String surname = dataSnapshot.child("User_Information").child("Surname").getValue().toString();
                String pesel = dataSnapshot.child("User_Information").child("Pesel").getValue().toString();
                String street = dataSnapshot.child("User_Information").child("Street").getValue().toString();
                String city = dataSnapshot.child("User_Information").child("City").getValue().toString();
                String postalAddress = dataSnapshot.child("User_Information").child("PostalAddress").getValue().toString();
                String birthdayDate = dataSnapshot.child("User_Information").child("BirthdayDate").getValue().toString();
                String study = dataSnapshot.child("User_Information").child("Study").getValue().toString();
                String phoneNumber = dataSnapshot.child("User_Information").child("PhoneNumber").getValue().toString();

                String image_url = dataSnapshot.child("Image_Information").child("URL").getValue().toString();

                if (image_url != "Empty") {
                    Glide.with(getApplicationContext()).load(image_url).into(image);
                }
                else {
                    Toast.makeText(UserProfile.this, "Nie załączono zdjęcia!", Toast.LENGTH_SHORT).show();
                }

                studentName.setText(name);
                studentSurname.setText(surname);
                studentPesel.setText(pesel);
                studentStreet.setText(street);
                studentCity.setText(city);
                studentPhoneNumber.setText(phoneNumber);
                studentPostalAddress.setText(postalAddress);
                studentBirthdayDate.setText(birthdayDate);
                studentStudy.setText(study);
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
