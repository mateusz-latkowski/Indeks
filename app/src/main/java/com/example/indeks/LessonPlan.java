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

    private ListView listView1;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<String> arrayList1;
    private ArrayAdapter<String> arrayAdapter1;
    private TextView textView1, textView2;

    private ListView listView2;
    private ArrayList<String> arrayList2;
    private ArrayAdapter<String> arrayAdapter2;

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

        textView1 = (TextView) findViewById(R.id.textViewLesson1);
        listView1 = (ListView) findViewById(R.id.listViewLesson1);
        arrayList1 = new ArrayList<>();
        arrayAdapter1 = new ArrayAdapter<String>(LessonPlan.this, R.layout.lesson_list, R.id.lessonInfo, arrayList1);

        textView2 = (TextView) findViewById(R.id.textViewLesson2);
        listView2 = (ListView) findViewById(R.id.listViewLesson2);
        arrayList2 = new ArrayList<>();
        arrayAdapter2 = new ArrayAdapter<String>(LessonPlan.this, R.layout.lesson_list, R.id.lessonInfo, arrayList2);

        downloadLessonPlan();
    }

    private void downloadLessonPlan() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Students").child(userID).child("Schedule").child("Wtorek");
        lessonPlanInformation = new LessonPlanInformation();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    lessonPlanInformation = ds.getValue(LessonPlanInformation.class);
                    arrayList1.add(lessonPlanInformation.getSubject() + "\n" + lessonPlanInformation.getTime() + " - " + lessonPlanInformation.getRoom() + "\n" + lessonPlanInformation.getTeacher());
                }
                listView1.setAdapter(arrayAdapter1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Students").child(userID).child("Schedule").child("Środa");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    lessonPlanInformation = ds.getValue(LessonPlanInformation.class);
                    arrayList2.add(lessonPlanInformation.getSubject() + "\n" + lessonPlanInformation.getTime() + " - " + lessonPlanInformation.getRoom() + "\n" + lessonPlanInformation.getTeacher());
                }
                listView2.setAdapter(arrayAdapter2);
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
