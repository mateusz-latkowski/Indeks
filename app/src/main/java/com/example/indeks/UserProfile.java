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
    private TextView studentBirthdayDate;
    private TextView studentID;
    private TextView studentStreet;
    private TextView studentCity;
    private TextView studentPostalAddress;
    private TextView studentStudy;
    private TextView studentEmail;
    private TextView studentPhoneNumber;

    private ImageView image;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("PROFIL");

        studentName = findViewById(R.id.textViewName);
        studentSurname = findViewById(R.id.textViewSurname);
        studentPesel = findViewById(R.id.textViewPesel);
        studentID = findViewById(R.id.textViewId);
        studentBirthdayDate = findViewById(R.id.textViewBirthdayDate);
        studentStreet = findViewById(R.id.textViewStreet);
        studentCity = findViewById(R.id.textViewCity);
        studentPostalAddress = findViewById(R.id.textViewPostalAddress);
        studentStudy = findViewById(R.id.textViewStudy);
        studentPhoneNumber = findViewById(R.id.textViewPhoneNumber);
        studentEmail = findViewById(R.id.textViewEmaill);
        image = findViewById(R.id.imageViewStudent);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String userID = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Students");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Students_Info").child(userID).child("Name").getValue().toString();
                String surname = dataSnapshot.child("Students_Info").child(userID).child("Surname").getValue().toString();
                String pesel = dataSnapshot.child("Students_Info").child(userID).child("Pesel").getValue().toString();
                String birthdayDate = dataSnapshot.child("Students_Info").child(userID).child("BirthdayDate").getValue().toString();
                String street = dataSnapshot.child("Students_Info").child(userID).child("Street").getValue().toString();
                String city = dataSnapshot.child("Students_Info").child(userID).child("City").getValue().toString();
                String postalAddress = dataSnapshot.child("Students_Info").child(userID).child("PostalAddress").getValue().toString();
                String study = dataSnapshot.child("Students_Info").child(userID).child("Study").getValue().toString();
                String email = user.getEmail();
                String phoneNumber = dataSnapshot.child("Students_Info").child(userID).child("PhoneNumber").getValue().toString();

                String image_url = dataSnapshot.child("Image_Info").child(userID).child("URL").getValue().toString();

                if (image_url != "Empty") {
                    Glide.with(getApplicationContext()).load(image_url).into(image);
                }

                studentName.setText(name);
                studentSurname.setText(surname);
                studentPesel.setText(pesel);
                studentBirthdayDate.setText(birthdayDate);
                studentID.setText(userID);
                studentStreet.setText(street);
                studentCity.setText(city);
                studentPostalAddress.setText(postalAddress);
                studentStudy.setText(study);
                studentEmail.setText(email);
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
