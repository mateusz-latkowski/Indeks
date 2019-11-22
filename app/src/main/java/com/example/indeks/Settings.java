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
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Settings extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private FirebaseUser user;

    private Button buttonUpload;
    private Button buttonChoose;
    private Button buttonChangePassword;
    private Button buttonChangeData;

    private EditText newPassword;
    private EditText newPassword2;
    private EditText newStreet;
    private EditText newCity;
    private EditText newPostalAddress;
    private EditText newPhoneNumber;

    private String userID;

    private Uri imageURI;

    private static final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("USTAWIENIA");

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        storageReference = FirebaseStorage.getInstance().getReference("Images");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Students").child(userID);

        buttonUpload = findViewById(R.id.buttonImageUpload);
        buttonChoose = findViewById(R.id.buttonImageChoose);
        buttonChangePassword = findViewById(R.id.buttonChangePassword);
        buttonChangeData = findViewById(R.id.buttonChangeData);

        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser();
            }
        });
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUploader();
            }
        });
        buttonChangeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeData();
            }
        });
    }

    private void changeData() {
        newStreet = findViewById(R.id.editTextChangeStreet);
        newCity =  findViewById(R.id.editTextChangeCity);
        newPostalAddress = findViewById(R.id.editTextChangePostalAddress);
        newPhoneNumber = findViewById(R.id.editTextChangePhoneNumber);

        String street = newStreet.getText().toString().trim();
        String city = newCity.getText().toString().trim();
        String postalAddress = newPostalAddress.getText().toString().trim();
        String phoneNumber = newPhoneNumber.getText().toString().trim();

        if (!street.isEmpty()) {
            databaseReference.child("User_Information").child("Street").setValue(street); }
        if (!city.isEmpty()) {
            databaseReference.child("User_Information").child("City").setValue(city); }
        if (!postalAddress.isEmpty()) {
            databaseReference.child("User_Information").child("PostalAddress").setValue(postalAddress); }
        if (!phoneNumber.isEmpty()) {
            databaseReference.child("User_Information").child("PhoneNumber").setValue(phoneNumber); }

        Toast.makeText(Settings.this, "Twoje dane zostały zaktualizowane!", Toast.LENGTH_SHORT).show();
        newStreet.setText("");
        newCity.setText("");
        newPostalAddress.setText("");
        newPhoneNumber.setText("");
    }

    private void changePassword() {
        newPassword = findViewById(R.id.editTextNewPassword);
        newPassword2 = findViewById(R.id.editTextNewPassword2);

        final String newPass = newPassword.getText().toString().trim();
        final String newPass2 = newPassword2.getText().toString().trim();

        if (newPass.isEmpty() && newPass2.isEmpty()) {
            Toast.makeText(Settings.this, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show(); }
        if (!newPass.equals(newPass2)) {
            Toast.makeText(Settings.this, "Podane hasła różnią się!", Toast.LENGTH_SHORT).show();
        } else {
            user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Settings.this, "Hasło zostało zmienione!", Toast.LENGTH_SHORT).show();
                        newPassword.setText("");
                        newPassword2.setText("");
                    }
                }
            });
        }
    }

    private void FileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageURI = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageURI);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void FileUploader() {
        if (imageURI != null) {
            StorageReference ref = storageReference.child(userID + "." + getExtension(imageURI));
            databaseReference.child("Image_Information").child("Name").setValue(userID + "." + getExtension(imageURI));

            ref.putFile(imageURI)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String download_url = uri.toString();
                                    databaseReference.child("Image_Information").child("URL").setValue(download_url);
                                }
                            });
                            Toast.makeText(Settings.this, "Zdjęcie zostało zaktualizowane!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Settings.this, "Zdjęcie nie zostało zaktualizowane!", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
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
