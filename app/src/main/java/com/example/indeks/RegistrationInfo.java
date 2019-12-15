package com.example.indeks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationInfo extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private FirebaseUser user;
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

    private String study;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_info);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(RegistrationInfo.this, MainActivity.class));
        }

        user = FirebaseAuth.getInstance().getCurrentUser();

        assert user != null;
        userID = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Students");

        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextPesel = findViewById(R.id.editTextPesel);
        editTextStreet = findViewById(R.id.editTextStreet);
        editTextCity = findViewById(R.id.editTextCity);
        editTextPostalAddress = findViewById(R.id.editTextPostalAddress);
        editTextBirthdayDate = findViewById(R.id.editTextBirthdayDate);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(RegistrationInfo.this, R.array.kierunki, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        buttonSave = findViewById(R.id.buttonSaveInfo);
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
        databaseReference.child("Students_Info").child(userID).setValue(userInformation);

        databaseReference.child("Image_Info").child(userID).child("Name").setValue("Empty");
        databaseReference.child("Image_Info").child(userID).child("URL").setValue("Empty");

        if (!name.isEmpty() && !surname.isEmpty() && !pesel.isEmpty() && !street.isEmpty() && !city.isEmpty() && !postalAddress.isEmpty() && !birthdayDate.isEmpty() && !phoneNumber.isEmpty()) {
            Toast.makeText(RegistrationInfo.this, "Zapisywanie danych...", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(RegistrationInfo.this, Home.class));
        } else {
            Toast.makeText(RegistrationInfo.this, "Pola nie mogą być puste!", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveGrades() {
        if (study.equals("Informatyka Stosowana")) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Grades").child(study);

            databaseReference.child("Students_Info").child(userID).child("Name").setValue(editTextName.getText().toString());
            databaseReference.child("Students_Info").child(userID).child("Surname").setValue(editTextSurname.getText().toString());
            databaseReference.child("Students_Info").child(userID).child("ID").setValue(userID);

            databaseReference.child("Semestr_1").child("Lektorat języka angielskiego I").child(userID).child("Lektorat").setValue("Brak");
            databaseReference.child("Semestr_1").child("Matematyka I").child(userID).child("Konwersatorium").setValue("Brak");
            databaseReference.child("Semestr_1").child("Ochrona własności intelektualnej").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_1").child("Podstawy użytkowania systemów komputerowych").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_1").child("Wstęp do informatyki").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_1").child("Wstęp do informatyki").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_1").child("Wstęp do informatyki").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_1").child("Wstęp do pomiarów i automatyki").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_1").child("Wstęp do programowania").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_1").child("Wstęp do programowania").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_1").child("Wstęp do programowania").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_1").child("ZALICZENIE SEMESTRU").child(userID).child("Semestr zaliczony?").setValue("NIE");

            databaseReference.child("Semestr_2").child("Algorytmy i programowanie").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_2").child("Algorytmy i programowanie").child(userID).child("Konwersatorium").setValue("Brak");
            databaseReference.child("Semestr_2").child("Algorytmy i programowanie").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_2").child("Algorytmy i programowanie").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_2").child("Architektura komputerów").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_2").child("Architektura komputerów").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_2").child("Architektura komputerów").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_2").child("Fizyka").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_2").child("Fizyka").child(userID).child("Konwersatorium").setValue("Brak");
            databaseReference.child("Semestr_2").child("Fizyka").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_2").child("Fizyka").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_2").child("Lektorat języka angielskiego II").child(userID).child("Lektorat").setValue("Brak");
            databaseReference.child("Semestr_2").child("Matematyka II").child(userID).child("Konwersatorium").setValue("Brak");
            databaseReference.child("Semestr_2").child("Podstawy elektrotechniki i elektroniki").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_2").child("Podstawy elektrotechniki i elektroniki").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_2").child("Podstawy elektrotechniki i elektroniki").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_2").child("ZALICZENIE SEMESTRU").child(userID).child("Semestr zaliczony?").setValue("NIE");


            databaseReference.child("Semestr_3").child("Algorytmy i struktury danych").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_3").child("Algorytmy i struktury danych").child(userID).child("Konwersatorium").setValue("Brak");
            databaseReference.child("Semestr_3").child("Algorytmy i struktury danych").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_3").child("Algorytmy i struktury danych").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_3").child("Bazy danych").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_3").child("Bazy danych").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_3").child("Bazy danych").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_3").child("Lektorat języka angielskiego III").child(userID).child("Lektorat").setValue("Brak");
            databaseReference.child("Semestr_3").child("Matematyka dyskretna").child(userID).child("Konwersatorium").setValue("Brak");
            databaseReference.child("Semestr_3").child("Matematyka dyskretna").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_3").child("Matematyka dyskretna").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_3").child("Podstawy techniki mikroprocesorowej").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_3").child("Prawo informatyczne").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_3").child("WF").child(userID).child("Ćwiczenia").setValue("Brak");
            databaseReference.child("Semestr_3").child("Sieci komputerowe").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_3").child("Sieci komputerowe").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_3").child("Sieci komputerowe").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_3").child("ZALICZENIE SEMESTRU").child(userID).child("Semestr zaliczony?").setValue("NIE");

            databaseReference.child("Semestr_4").child("Lektorat języka angielskiego IV").child(userID).child("Lektorat").setValue("Brak");
            databaseReference.child("Semestr_4").child("Lektorat języka angielskiego IV").child(userID).child("Egzamin").setValue("Brak");
            databaseReference.child("Semestr_4").child("Lektorat języka angielskiego IV").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_4").child("Podstawy automatyki i robotyki").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_4").child("Podstawy automatyki i robotyki").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_4").child("Podstawy automatyki i robotyki").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_4").child("Podstawy inżynierii oprogramowania").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_4").child("Podstawy inżynierii oprogramowania").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_4").child("Podstawy inżynierii oprogramowania").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_4").child("Podstawy metod probabilistycznych i statystki").child(userID).child("Konwersatorium").setValue("Brak");
            databaseReference.child("Semestr_4").child("Podstawy metod probabilistycznych i statystki").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_4").child("Podstawy metod probabilistycznych i statystki").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_4").child("Podstawy sztucznej inteligencji").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_4").child("Podstawy sztucznej inteligencji").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_4").child("Podstawy sztucznej inteligencji").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_4").child("Etyka biznesu i etyki zawodowe").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_4").child("Systemy wbudowane").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_4").child("Systemy wbudowane").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_4").child("Systemy wbudowane").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_4").child("Użytkowanie oprogramowania inżynierskiego").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_4").child("Użytkowanie oprogramowania inżynierskiego").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_4").child("Użytkowanie oprogramowania inżynierskiego").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_4").child("ZALICZENIE SEMESTRU").child(userID).child("Semestr zaliczony?").setValue("NIE");

            databaseReference.child("Semestr_5").child("Elementy grafiki komputerowej i przetwarzania obrazu").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_5").child("Elementy administracji baz danych").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_5").child("Elementy administracji baz danych").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_5").child("Elementy administracji baz danych").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_5").child("Kurs C").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_5").child("Kurs C").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_5").child("Kurs C").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_5").child("Kurs Java").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_5").child("Kurs Java").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_5").child("Kurs Java").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_5").child("Systemy operacyjne i programowanie systemowe").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_5").child("Systemy operacyjne i programowanie systemowe").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_5").child("Systemy operacyjne i programowanie systemowe").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_5").child("Środowiska i narzędzia wytwarzania oprogramowania").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_5").child("Wstęp do przedsiębiorczości").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_5").child("ZALICZENIE SEMESTRU").child(userID).child("Semestr zaliczony?").setValue("NIE");

            databaseReference.child("Semestr_6").child("Kurs C++").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_6").child("Kurs C++").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_6").child("Kurs C++").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_6").child("Kurs JavaScript").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_6").child("Kurs JavaScript").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_6").child("Kurs JavaScript").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_6").child("Kurs programowania gier komputerowych z wykorzystaniem silnika Unity").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_6").child("Kurs programowania gier komputerowych z wykorzystaniem silnika Unity").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_6").child("Kurs programowania gier komputerowych z wykorzystaniem silnika Unity").child(userID).child("OKM").setValue("Brak");
            databaseReference.child("Semestr_6").child("Pracownia inżynierskia I").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_6").child("Pracownia programowania zespołowego I").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_6").child("Praktyka inżynierska").child(userID).child("Praktyka").setValue("Brak");
            databaseReference.child("Semestr_6").child("Proseminarium inżynierskie").child(userID).child("Seminarium").setValue("Brak");
            databaseReference.child("Semestr_6").child("ZALICZENIE SEMESTRU").child(userID).child("Semestr zaliczony?").setValue("NIE");

            databaseReference.child("Semestr_7").child("Praca dyplomowa").child(userID).child("Seminarium").setValue("Brak");
            databaseReference.child("Semestr_7").child("Pracownia inżynierskia II").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_7").child("Pracownia programowania zespołowego II").child(userID).child("Laboratorium").setValue("Brak");
            databaseReference.child("Semestr_7").child("Człowiek w społeczeństwie, społeczeństwo w człowieku").child(userID).child("Wykład").setValue("Brak");
            databaseReference.child("Semestr_7").child("Seminarium inżynierskie").child(userID).child("Seminarium").setValue("Brak");
            databaseReference.child("Semestr_7").child("ZALICZENIE SEMESTRU").child(userID).child("Semestr zaliczony?").setValue("NIE");

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Grades").child("Informatyka Stosowana").child("Semestr_7").child("PLAN ZAJĘĆ");
            databaseReference.child("Wtorek").child("11:30").child("Subject").setValue("Seminarium inżynierskie");
            databaseReference.child("Wtorek").child("11:30").child("Time").setValue("11:30 - 13:00");
            databaseReference.child("Wtorek").child("11:30").child("Teacher").setValue("dr Jan Kowalski");
            databaseReference.child("Wtorek").child("11:30").child("Room").setValue("B/-1/09");

            databaseReference.child("Wtorek").child("13:45").child("Subject").setValue("Człowiek w społeczeństwie, społeczeństwo w człowieku");
            databaseReference.child("Wtorek").child("13:45").child("Time").setValue("13:45 - 15:15");
            databaseReference.child("Wtorek").child("13:45").child("Teacher").setValue("dr Jan Kowalski");
            databaseReference.child("Wtorek").child("13:45").child("Room").setValue("P/0/01");

            databaseReference.child("Środa").child("8:00").child("Subject").setValue("Pracownia programowania inżynierskiego cz. 2");
            databaseReference.child("Środa").child("8:00").child("Time").setValue("8:00 - 11:00");
            databaseReference.child("Środa").child("8:00").child("Teacher").setValue("dr Jan Kowalski");
            databaseReference.child("Środa").child("8:00").child("Room").setValue("S/0/05");

            databaseReference.child("Środa").child("11:30").child("Subject").setValue("Pracownia inżynierska cz. 2");
            databaseReference.child("Środa").child("11:30").child("Time").setValue("11:30 - 13:00");
            databaseReference.child("Środa").child("11:30").child("Teacher").setValue("dr Jan Kowalski");
            databaseReference.child("Środa").child("11:30").child("Room").setValue("P/0/03");
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
