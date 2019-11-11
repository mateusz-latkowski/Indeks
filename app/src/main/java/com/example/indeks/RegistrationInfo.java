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
    private FirebaseUser user;
    private String userID;

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

    public static String study;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_info);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(RegistrationInfo.this, MainActivity.class));
        }

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

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

        String email = user.getEmail();

        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();
        String pesel = editTextPesel.getText().toString().trim();
        String street = editTextStreet.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
        String postalAddress = editTextPostalAddress.getText().toString().trim();
        String birthdayDate = editTextBirthdayDate.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        study = String.valueOf(spinner.getSelectedItem());

        UserInformation userInformation = new UserInformation(name, surname, pesel, street, city, postalAddress, birthdayDate, study, phoneNumber, email, userID);

        databaseReference.child(userID).child("User_Information").setValue(userInformation);
        databaseReference.child(userID).child("Image_Information").child("Name").setValue("Empty");
        databaseReference.child(userID).child("Image_Information").child("URL").setValue("Empty");

        Toast.makeText(this, "Zapisywanie danych...", Toast.LENGTH_SHORT).show();

        finish();
        startActivity(new Intent(RegistrationInfo.this, Home.class));
    }

    public static String getStudy() {
        return study;
    }

    private void saveGrades() {
        if (study.equals("Informatyka Stosowana")) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Students").child(userID).child("Grades").child("InformatykaStosowana");

            databaseReference.child("Semestr_1").child("Matematyka I").setValue("Empty");
            databaseReference.child("Semestr_1").child("Lektorat języka angielskiego I").setValue("Empty");
            databaseReference.child("Semestr_1").child("Ochrona własności intelektualnej").setValue("Empty");
            databaseReference.child("Semestr_1").child("Podstawy użytkowania systemów komputerowych").setValue("Empty");
            databaseReference.child("Semestr_1").child("WF").setValue("Empty");
            databaseReference.child("Semestr_1").child("Wstęp do informatyki").setValue("Empty");
            databaseReference.child("Semestr_1").child("Wstęp do programowania").setValue("Empty");
            databaseReference.child("Semestr_1").child("Wstęp do pomiarów i automatyki").setValue("Empty");
            databaseReference.child("Semestr_1").child("_SEMESTR_1_ZALICZONY_?").setValue("NIE");

            databaseReference.child("Semestr_2").child("Matematyka II").setValue("Empty");
            databaseReference.child("Semestr_2").child("Lektorat języka angielskiego II").setValue("Empty");
            databaseReference.child("Semestr_2").child("Fizyka").setValue("Empty");
            databaseReference.child("Semestr_2").child("Algorytmy i programowanie").setValue("Empty");
            databaseReference.child("Semestr_2").child("WF").setValue("Empty");
            databaseReference.child("Semestr_2").child("Podstawy elektrotechniki i elektroniki").setValue("Empty");
            databaseReference.child("Semestr_2").child("Architektura komputerów").setValue("Empty");
            databaseReference.child("Semestr_2").child("_SEMESTR_2_ZALICZONY_?").setValue("NIE");

            databaseReference.child("Semestr_3").child("Algorytmy i struktury danych").setValue("Empty");
            databaseReference.child("Semestr_3").child("Bazy danych").setValue("Empty");
            databaseReference.child("Semestr_3").child("Lektorat języka angielskiego III").setValue("Empty");
            databaseReference.child("Semestr_3").child("Matematyka dyskretna").setValue("Empty");
            databaseReference.child("Semestr_3").child("Podstawy techniki mikroprocesorowej").setValue("Empty");
            databaseReference.child("Semestr_3").child("Prawo informatyczne").setValue("Empty");
            databaseReference.child("Semestr_3").child("Sieci komputerowe").setValue("Empty");
            databaseReference.child("Semestr_3").child("_SEMESTR_3_ZALICZONY_?").setValue("NIE");

            databaseReference.child("Semestr_4").child("Etyka biznesu i etyki zawodowe").setValue("Empty");
            databaseReference.child("Semestr_4").child("Lektorat języka angielskiego IV").setValue("Empty");
            databaseReference.child("Semestr_4").child("Podstawy automatyki i robotyki").setValue("Empty");
            databaseReference.child("Semestr_4").child("Podstawy inżynierii oprogramwoania").setValue("Empty");
            databaseReference.child("Semestr_4").child("Podstawy metod probabilistycznych i statystyki").setValue("Empty");
            databaseReference.child("Semestr_4").child("Podstawy sztucznej inteligencji").setValue("Empty");
            databaseReference.child("Semestr_4").child("Systemy wbudowane").setValue("Empty");
            databaseReference.child("Semestr_4").child("Użytkowanie oprogramowania inżynierskiego").setValue("Empty");
            databaseReference.child("Semestr_4").child("_SEMESTR_4_ZALICZONY_?").setValue("NIE");

            databaseReference.child("Semestr_5").child("Elementy grafiki komputerowej i przetwarzania obrazu").setValue("Empty");
            databaseReference.child("Semestr_5").child("Elementy administracji baz danych").setValue("Empty");
            databaseReference.child("Semestr_5").child("Kurs C").setValue("Empty");
            databaseReference.child("Semestr_5").child("Kurs Java").setValue("Empty");
            databaseReference.child("Semestr_5").child("Systemy operacyjne i programowania systemowe").setValue("Empty");
            databaseReference.child("Semestr_5").child("Srodowiska i narzędzia wytwarzania oprogramowania").setValue("Empty");
            databaseReference.child("Semestr_5").child("Wstęp do przedsiębiorczości").setValue("Empty");
            databaseReference.child("Semestr_5").child("_SEMESTR_5_ZALICZONY_?").setValue("NIE");

            databaseReference.child("Semestr_6").child("Kurs C++").setValue("Empty");
            databaseReference.child("Semestr_6").child("Kurs JavaScript").setValue("Empty");
            databaseReference.child("Semestr_6").child("Kurs programowania gier komputerowych z wykorzystaniem silnika Unity").setValue("Empty");
            databaseReference.child("Semestr_6").child("Pracownia inżynierska - część 1").setValue("Empty");
            databaseReference.child("Semestr_6").child("Pracownia programowania zespołowego - część 1").setValue("Empty");
            databaseReference.child("Semestr_6").child("Praktyka inżynierska").setValue("Empty");
            databaseReference.child("Semestr_6").child("Proseminarium inżynierskie").setValue("Empty");
            databaseReference.child("Semestr_6").child("_SEMESTR_6_ZALICZONY_?").setValue("NIE");

            databaseReference.child("Semestr_7").child("Praca dyplomowa").setValue("Empty");
            databaseReference.child("Semestr_7").child("Pracownia inżynierska - część 2").setValue("Empty");
            databaseReference.child("Semestr_7").child("Pracownia programowania zespołowego - część 2").setValue("Empty");
            databaseReference.child("Semestr_7").child("Seminarium inżynierskie").setValue("Empty");
            databaseReference.child("Semestr_7").child("_SEMESTR_7_ZALICZONY_?").setValue("NIE");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == buttonSave) {
            saveUserInformation();
            saveGrades();
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
