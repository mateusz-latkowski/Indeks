package com.example.indeks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class RegistrationInfo extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextPesel;
    private EditText editTextStreet;
    private EditText editTextCity;
    private EditText editTextPhoneNumber;

    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_info);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(RegistrationInfo.this, MainActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Students");

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSurname = (EditText) findViewById(R.id.editTextSurname);
        editTextPesel = (EditText) findViewById(R.id.editTextPesel);
        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);

        buttonSave = (Button) findViewById(R.id.buttonSaveInfo);
        buttonSave.setOnClickListener(this);
    }

    private void saveUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();

        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();
        String pesel = editTextPesel.getText().toString().trim();
        String street = editTextStreet.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();


        UserInformation userInformation = new UserInformation(name, surname, pesel, street, city, phoneNumber);

        databaseReference.child((userID)).setValue(userInformation);

        Toast.makeText(this, "Zapisywanie danych...", Toast.LENGTH_LONG).show();

        finish();
        startActivity(new Intent(RegistrationInfo.this, Home.class));
    }

    @Override
    public void onClick(View v) {
        if (v == buttonSave) {
            saveUserInformation();
        }
    }
}
