package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Teachers extends AppCompatActivity {

    private ListView listView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private String AcademicTitle;
    private ClipboardManager clipboardManager;
    TeachersInformation tI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("PROWADZĄCY");

        tI = new TeachersInformation();
        listView = findViewById(R.id.listViewTeachers);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Teachers").child("Teachers_Info");
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(Teachers.this, R.layout.teach_info, R.id.teachInfo, arrayList);

        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    tI = ds.getValue(TeachersInformation.class);
                    if (tI.getAcademicTitle().equals("Doktor")) {
                        AcademicTitle = "dr"; }
                    if (tI.getAcademicTitle().equals("Inżynier")) {
                        AcademicTitle = "inż."; }
                    if (tI.getAcademicTitle().equals("Magister")) {
                        AcademicTitle = "mgr"; }
                    if (tI.getAcademicTitle().equals("Magister Inżynier")) {
                        AcademicTitle = "mgr inż."; }

                    arrayList.add(AcademicTitle + " " + tI.getName() + " " + tI.getSurname() + "\n" + tI.getRoom() + " - " + tI.getEmail());
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
