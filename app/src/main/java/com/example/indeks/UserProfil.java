package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.ProgressDialog;
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

import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.UUID;

public class UserProfil extends AppCompatActivity {

    private TextView studentName;
    private TextView studentSurname;
    private TextView studentPesel;
    private TextView studentStreet;
    private TextView studentCity;
    private TextView studentPhoneNumber;
    private DatabaseReference databaseReference;

    private Button buttonUpload;
    private Button buttonChoose;
    private ImageView image;

    public Uri imageURI;

    private StorageTask uploadTask;

    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        storageReference = FirebaseStorage.getInstance().getReference("Images");

        studentName = (TextView) findViewById(R.id.textViewValue_1);
        studentSurname = (TextView) findViewById(R.id.textViewValue_2);
        studentPesel = (TextView) findViewById(R.id.textViewValue_3);
        studentStreet = (TextView) findViewById(R.id.textViewValue_4);
        studentCity = (TextView) findViewById(R.id.textViewValue_5);
        studentPhoneNumber = (TextView) findViewById(R.id.textViewValue_6);

        buttonUpload = (Button) findViewById(R.id.buttonImageUpload);
        buttonChoose = (Button) findViewById(R.id.buttonImageChoose);
        image = (ImageView) findViewById(R.id.imageViewStudent);

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(UserProfil.this, "Trwa aktualizowanie...", Toast.LENGTH_LONG).show();
                } else {
                    FileUploader();
                }
            }
        });

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


    private String getExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void FileUploader() {
        StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + getExtension(imageURI));

        uploadTask = ref.putFile(imageURI)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(UserProfil.this, "Zdjęcie zostało zaktualizowane!", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    private void FileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageURI = data.getData();
            image.setImageURI(imageURI);
        }
    }
}

