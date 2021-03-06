package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarEvent extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView textView, textViewDate;
    private EditText description;
    private Button buttonEvent;

    private String date, passDate, userID;

    private NumberPicker numberPickerHour, numberPickerMinutes;

    private FirebaseUser user;
    private DatabaseReference databaseReference;

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    CalendarEventInformation calendarEventInformation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("KALENDARZ");

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Events").child(userID);

        calendarView = findViewById(R.id.calendarView);
        textView = findViewById(R.id.textViewCalendar);
        buttonEvent = findViewById(R.id.buttonSetEvent);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date = dayOfMonth + "." + month + "." + year;
                textView.setText(date);
                passDate = date;
            }
        });

        buttonEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });
        showEvent();
    }

    private void addEvent() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_event, null);

        description = view.findViewById(R.id.editTextEventDescription);
        textViewDate = view.findViewById(R.id.textViewDate);
        textViewDate.setText(passDate);

        numberPickerHour = view.findViewById(R.id.numberPickerHour);
        numberPickerHour.setMinValue(0);
        numberPickerHour.setMaxValue(23);

        numberPickerMinutes = view.findViewById(R.id.numberPickerMinutes);
        numberPickerMinutes.setMinValue(0);
        numberPickerMinutes.setMaxValue(59);

        builder.setView(view)
                .setTitle("WYDARZENIE")
                .setNegativeButton("ANULUJ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("DODAJ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (description.getText().toString().equals("")) {
                            Toast.makeText(CalendarEvent.this, "Pola nie mogą być puste!", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child(description.getText().toString()).child("Date").setValue(passDate);
                            databaseReference.child(description.getText().toString()).child("Description").setValue(description.getText().toString());

                            if (numberPickerMinutes.getValue() < 10) {
                                databaseReference.child(description.getText().toString()).child("Time").setValue(numberPickerHour.getValue() + ":0" + numberPickerMinutes.getValue());
                            } else {
                                databaseReference.child(description.getText().toString()).child("Time").setValue(numberPickerHour.getValue() + ":" + numberPickerMinutes.getValue());
                            }
                            finish();
                            startActivity(new Intent(CalendarEvent.this, Home.class));
                            Toast.makeText(getApplicationContext(), "Wydarzenie zostało zapisane!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        builder.create().show();
    }

    private void showEvent() {
        calendarEventInformation = new CalendarEventInformation();
        listView = findViewById(R.id.listViewEvents);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(CalendarEvent.this, R.layout.event_info, R.id.eventInfo, arrayList);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    calendarEventInformation = ds.getValue(CalendarEventInformation.class);
                    arrayList.add(calendarEventInformation.getDescription() + "\n" + calendarEventInformation.getDate() + " - " + calendarEventInformation.getTime());
                }
                listView.setAdapter(arrayAdapter);
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
