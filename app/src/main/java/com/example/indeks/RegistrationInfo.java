package com.example.indeks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class RegistrationInfo extends AppCompatActivity implements View.OnClickListener {

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

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSurname = (EditText) findViewById(R.id.editTextSurname);
        editTextPesel = (EditText) findViewById(R.id.editTextPesel);
        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);

        buttonSave = (Button) findViewById(R.id.buttonSaveInfo);
        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // jass
    }
}
