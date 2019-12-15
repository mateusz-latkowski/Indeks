package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LessonPlan extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private ListView listViewTuesday;
    private ArrayList<String> arrayListTuesday;
    private ArrayAdapter<String> arrayAdapterTuesday;
    private TextView textViewTuesday;

    private ListView listViewWednesday;
    private ArrayList<String> arrayListWednesday;
    private ArrayAdapter<String> arrayAdapterWednesday;
    private TextView textViewWednesday;

    private String userID;

    LessonPlanInformation lessonPlanInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_plan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("PLAN ZAJĘĆ");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        textViewTuesday = findViewById(R.id.textViewTuesday);
        listViewTuesday = findViewById(R.id.listViewTuesday);
        arrayListTuesday = new ArrayList<>();
        arrayAdapterTuesday = new ArrayAdapter<String>(LessonPlan.this, R.layout.lesson_list, R.id.lessonInfo, arrayListTuesday);

        textViewWednesday = findViewById(R.id.textViewWednesday);
        listViewWednesday = findViewById(R.id.listViewWednesday);
        arrayListWednesday = new ArrayList<>();
        arrayAdapterWednesday = new ArrayAdapter<String>(LessonPlan.this, R.layout.lesson_list, R.id.lessonInfo, arrayListWednesday);

        downloadLessonPlan();
    }

    private void downloadLessonPlan() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        lessonPlanInformation = new LessonPlanInformation();
        databaseReference = firebaseDatabase.getReference().child("Grades").child("Informatyka Stosowana").child("Semestr_7").child("PLAN ZAJĘĆ").child("Wtorek");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    lessonPlanInformation = ds.getValue(LessonPlanInformation.class);
                    arrayListTuesday.add(lessonPlanInformation.getSubject() + "\n" + lessonPlanInformation.getTime() + " - " + lessonPlanInformation.getRoom() + "\n" + lessonPlanInformation.getTeacher());
                }
                listViewTuesday.setAdapter(arrayAdapterTuesday);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference().child("Grades").child("Informatyka Stosowana").child("Semestr_7").child("PLAN ZAJĘĆ").child("Środa");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    lessonPlanInformation = ds.getValue(LessonPlanInformation.class);
                    arrayListWednesday.add(lessonPlanInformation.getSubject() + "\n" + lessonPlanInformation.getTime() + " - " + lessonPlanInformation.getRoom() + "\n" + lessonPlanInformation.getTeacher());
                }
                listViewWednesday.setAdapter(arrayAdapterWednesday);
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
