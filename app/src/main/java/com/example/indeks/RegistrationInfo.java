package com.example.indeks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class RegistrationInfo extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextPesel;
    private EditText editTextStreet;
    private EditText editTextCity;
    private EditText editTextPostalAddress;
    private EditText editTextBirthdayDate;
    private EditText editTextPhoneNumber;
    private Spinner spinner;
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
        editTextPostalAddress = (EditText) findViewById(R.id.editTextPostalAddress);
        editTextBirthdayDate = (EditText) findViewById(R.id.editTextBirthdayDate);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(RegistrationInfo.this, R.array.kierunki, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        buttonSave = (Button) findViewById(R.id.buttonSaveInfo);
        buttonSave.setOnClickListener(this);
    }

    private void saveUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();
        String email = user.getEmail();

        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();
        String pesel = editTextPesel.getText().toString().trim();
        String street = editTextStreet.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
        String postalAddress = editTextPostalAddress.getText().toString().trim();
        String birthdayDate = editTextBirthdayDate.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        String study = String.valueOf(spinner.getSelectedItem());

        UserInformation userInformation = new UserInformation(name, surname, pesel, street, city, postalAddress, birthdayDate, study, phoneNumber, email, userID);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
