package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Grades extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView break_1, break_2;
    private TextView textView2_1;
    private TextView textView2_2;
    private TextView textView3_1;
    private TextView textView3_2;
    private TextView textView4_1;
    private TextView textView4_2;
    private TextView textView5_1;
    private TextView textView5_2;
    private TextView textView6_1;
    private TextView textView6_2;
    private TextView textView7_1;
    private TextView textView7_2;
    private TextView textView8_1;
    private TextView textView8_2;
    private TextView textView9_1;
    private TextView textView9_2;
    private TextView textView10_1;
    private TextView textView10_2;
    private TextView textView11_1;
    private TextView textView11_2;
    private TextView textView12_1;
    private TextView textView12_2;
    private TextView textView13_1;
    private TextView textView13_2;
    private TextView textView14_1;
    private TextView textView14_2;
    private TextView textView15_1;
    private TextView textView15_2;
    private TextView textView16_1;
    private TextView textView16_2;
    private TextView textView17_1;
    private TextView textView17_2;
    private TextView textView18_1;
    private TextView textView18_2;
    private TextView textView21_1;
    private TextView textView21_2;
    private TextView textView22_1;
    private TextView textView22_2;
    private TextView textView23_1;
    private TextView textView23_2;
    private TextView textView24_1;
    private TextView textView24_2;
    private TextView textView25_1;
    private TextView textView25_2;
    private TextView textView26_1;
    private TextView textView26_2;
    private TextView textView27_1;
    private TextView textView27_2;
    private TextView textView28_1;
    private TextView textView28_2;



    private String study, userID;

    private DatabaseReference databaseReference;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("OCENY");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Students").child(userID).child("User_Information");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                study = dataSnapshot.child("Study").getValue().toString();

                if (study.equals("Informatyka Stosowana")) {
                    informatykaStosowana();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void informatykaStosowana() {
        textView = (TextView) findViewById(R.id.textViewGrades);
        textView.setText("Informatyka Stosowana");

        textView2_1 = (TextView) findViewById(R.id.textViewRow2_1);
        textView2_2 = (TextView) findViewById(R.id.textViewRow2_2);
        textView3_1 = (TextView) findViewById(R.id.textViewRow3_1);
        textView3_2 = (TextView) findViewById(R.id.textViewRow3_2);
        textView4_1 = (TextView) findViewById(R.id.textViewRow4_1);
        textView4_2 = (TextView) findViewById(R.id.textViewRow4_2);
        textView5_1 = (TextView) findViewById(R.id.textViewRow5_1);
        textView5_2 = (TextView) findViewById(R.id.textViewRow5_2);
        textView6_1 = (TextView) findViewById(R.id.textViewRow6_1);
        textView6_2 = (TextView) findViewById(R.id.textViewRow6_2);
        textView7_1 = (TextView) findViewById(R.id.textViewRow7_1);
        textView7_2 = (TextView) findViewById(R.id.textViewRow7_2);
        textView8_1 = (TextView) findViewById(R.id.textViewRow8_1);
        textView8_2 = (TextView) findViewById(R.id.textViewRow8_2);
        textView9_1 = (TextView) findViewById(R.id.textViewRow9_1);
        textView9_2 = (TextView) findViewById(R.id.textViewRow9_2);
        textView10_1 = (TextView) findViewById(R.id.textViewRow10_1);
        textView10_2 = (TextView) findViewById(R.id.textViewRow10_2);

        break_1 = (TextView) findViewById(R.id.textViewBreak_1);
        textView2 = (TextView) findViewById(R.id.textViewRow2);
        textView11_1 = (TextView) findViewById(R.id.textViewRow11_1);
        textView11_2 = (TextView) findViewById(R.id.textViewRow11_2);
        textView12_1 = (TextView) findViewById(R.id.textViewRow12_1);
        textView12_2 = (TextView) findViewById(R.id.textViewRow12_2);
        textView13_1 = (TextView) findViewById(R.id.textViewRow13_1);
        textView13_2 = (TextView) findViewById(R.id.textViewRow13_2);
        textView14_1 = (TextView) findViewById(R.id.textViewRow14_1);
        textView14_2 = (TextView) findViewById(R.id.textViewRow14_2);
        textView15_1 = (TextView) findViewById(R.id.textViewRow15_1);
        textView15_2 = (TextView) findViewById(R.id.textViewRow15_2);
        textView16_1 = (TextView) findViewById(R.id.textViewRow16_1);
        textView16_2 = (TextView) findViewById(R.id.textViewRow16_2);
        textView17_1 = (TextView) findViewById(R.id.textViewRow17_1);
        textView17_2 = (TextView) findViewById(R.id.textViewRow17_2);
        textView18_1 = (TextView) findViewById(R.id.textViewRow18_1);
        textView18_2 = (TextView) findViewById(R.id.textViewRow18_2);

        break_2 = (TextView) findViewById(R.id.textViewBreak_2);
        textView3 = (TextView) findViewById(R.id.textViewRow3);
        textView21_1 = (TextView) findViewById(R.id.textViewRow21_1);
        textView21_2 = (TextView) findViewById(R.id.textViewRow21_2);
        textView22_1 = (TextView) findViewById(R.id.textViewRow22_1);
        textView22_2 = (TextView) findViewById(R.id.textViewRow22_2);
        textView23_1 = (TextView) findViewById(R.id.textViewRow23_1);
        textView23_2 = (TextView) findViewById(R.id.textViewRow23_2);
        textView24_1 = (TextView) findViewById(R.id.textViewRow24_1);
        textView24_2 = (TextView) findViewById(R.id.textViewRow24_2);
        textView25_1 = (TextView) findViewById(R.id.textViewRow25_1);
        textView25_2 = (TextView) findViewById(R.id.textViewRow25_2);
        textView26_1 = (TextView) findViewById(R.id.textViewRow26_1);
        textView26_2 = (TextView) findViewById(R.id.textViewRow26_2);
        textView27_1 = (TextView) findViewById(R.id.textViewRow27_1);
        textView27_2 = (TextView) findViewById(R.id.textViewRow27_2);
        textView28_1 = (TextView) findViewById(R.id.textViewRow28_1);
        textView28_2 = (TextView) findViewById(R.id.textViewRow28_2);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Students").child(userID).child("Grades").child("InformatykaStosowana");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String lektorat = dataSnapshot.child("Semestr_1").child("Lektorat języka angielskiego I").getValue().toString();
                String matematyka = dataSnapshot.child("Semestr_1").child("Matematyka I").getValue().toString();
                String owi = dataSnapshot.child("Semestr_1").child("Ochrona własności intelektualnej").getValue().toString();
                String pusk = dataSnapshot.child("Semestr_1").child("Podstawy użytkowania systemów komputerowych").getValue().toString();
                String wf = dataSnapshot.child("Semestr_1").child("WF").getValue().toString();
                String wdi = dataSnapshot.child("Semestr_1").child("Wstęp do informatyki").getValue().toString();
                String wdp = dataSnapshot.child("Semestr_1").child("Wstęp do programowania").getValue().toString();
                String wdpia = dataSnapshot.child("Semestr_1").child("Wstęp do pomiarów i automatyki").getValue().toString();
                String semestr_1 = dataSnapshot.child("Semestr_1").child("_SEMESTR_1_ZALICZONY_?").getValue().toString();

                textView2_1.setText("Lektorat języka angielskiego I");
                textView2_2.setText(lektorat);
                textView3_1.setText("Matematyka I");
                textView3_2.setText(matematyka);
                textView4_1.setText("Ochrona własności intelektualnej");
                textView4_2.setText(owi);
                textView5_1.setText("Podstawy użytkowania systemów komputerowych");
                textView5_2.setText(pusk);
                textView6_1.setText("WF");
                textView6_2.setText(wf);
                textView7_1.setText("Wstęp do informatyki");
                textView7_2.setText(wdi);
                textView8_1.setText("Wstęp do programowania");
                textView8_2.setText(wdp);
                textView9_1.setText("Wstęp do pomiarów i automatyki");
                textView9_2.setText(wdpia);
                textView10_1.setText("SEMESTR 1 ZALICZONY?");
                textView10_2.setText(semestr_1);

                if (!lektorat.equals("Empty")) {
                    addNotifications();
                }

                if (semestr_1.equals("TAK")) {
                    String aip = dataSnapshot.child("Semestr_2").child("Algorytmy i programowanie").getValue().toString();
                    String ak =  dataSnapshot.child("Semestr_2").child("Architektura komputerów").getValue().toString();
                    String fizyka =  dataSnapshot.child("Semestr_2").child("Fizyka").getValue().toString();
                    String lektorat2 =  dataSnapshot.child("Semestr_2").child("Lektorat języka angielskiego II").getValue().toString();
                    String matematyka2 =  dataSnapshot.child("Semestr_2").child("Matematyka II").getValue().toString();
                    String peie =  dataSnapshot.child("Semestr_2").child("Podstawy elektrotechniki i elektroniki").getValue().toString();
                    String wf2 =  dataSnapshot.child("Semestr_2").child("WF").getValue().toString();
                    String semestr_2 =  dataSnapshot.child("Semestr_2").child("_SEMESTR_2_ZALICZONY_?").getValue().toString();

                    break_1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView11_1.setText("Algorytmy i programowanie");
                    textView11_1.setVisibility(View.VISIBLE);
                    textView11_2.setText(aip);
                    textView11_2.setVisibility(View.VISIBLE);
                    textView12_1.setText("Architektura komputerów");
                    textView12_1.setVisibility(View.VISIBLE);
                    textView12_2.setText(ak);
                    textView12_2.setVisibility(View.VISIBLE);
                    textView13_1.setText("Fizyka");
                    textView13_1.setVisibility(View.VISIBLE);
                    textView13_2.setText(fizyka);
                    textView13_2.setVisibility(View.VISIBLE);
                    textView14_1.setText("Lektorat języka angielskiego II");
                    textView14_1.setVisibility(View.VISIBLE);
                    textView14_2.setText(lektorat2);
                    textView14_2.setVisibility(View.VISIBLE);
                    textView15_1.setText("Matematyka II");
                    textView15_1.setVisibility(View.VISIBLE);
                    textView15_2.setText(matematyka2);
                    textView15_2.setVisibility(View.VISIBLE);
                    textView16_1.setText("Podstawy elektrotechniki i elektroniki");
                    textView16_1.setVisibility(View.VISIBLE);
                    textView16_2.setText(peie);
                    textView16_2.setVisibility(View.VISIBLE);
                    textView17_1.setText("WF");
                    textView17_1.setVisibility(View.VISIBLE);
                    textView17_2.setText(wf2);
                    textView17_2.setVisibility(View.VISIBLE);
                    textView18_1.setText("SEMESTR 2 ZALICZONY?");
                    textView18_1.setVisibility(View.VISIBLE);
                    textView18_2.setText(semestr_2);
                    textView18_2.setVisibility(View.VISIBLE);

                    if (semestr_2.equals("TAK")) {
                        String aisd = dataSnapshot.child("Semestr_3").child("Algorytmy i struktury danych").getValue().toString();
                        String bd =  dataSnapshot.child("Semestr_3").child("Bazy danych").getValue().toString();
                        String lektorat3 =  dataSnapshot.child("Semestr_3").child("Lektorat języka angielskiego III").getValue().toString();
                        String md =  dataSnapshot.child("Semestr_3").child("Matematyka dyskretna").getValue().toString();
                        String ptm =  dataSnapshot.child("Semestr_3").child("Podstawy techniki mikroprocesorowej").getValue().toString();
                        String pi =  dataSnapshot.child("Semestr_3").child("Prawo informatyczne").getValue().toString();
                        String sk =  dataSnapshot.child("Semestr_3").child("Sieci komputerowe").getValue().toString();
                        String semestr_3 =  dataSnapshot.child("Semestr_3").child("_SEMESTR_3_ZALICZONY_?").getValue().toString();

                        break_2.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                        textView21_1.setText("Algorytmy i struktury danych");
                        textView21_1.setVisibility(View.VISIBLE);
                        textView21_2.setText(aisd);
                        textView21_2.setVisibility(View.VISIBLE);
                        textView22_1.setText("Bazy danych");
                        textView22_1.setVisibility(View.VISIBLE);
                        textView22_2.setText(bd);
                        textView22_2.setVisibility(View.VISIBLE);
                        textView23_1.setText("Lektorat języka angielskiego III");
                        textView23_1.setVisibility(View.VISIBLE);
                        textView23_2.setText(lektorat3);
                        textView23_2.setVisibility(View.VISIBLE);
                        textView24_1.setText("Matematyka dyskretna");
                        textView24_1.setVisibility(View.VISIBLE);
                        textView24_2.setText(md);
                        textView24_2.setVisibility(View.VISIBLE);
                        textView25_1.setText("Podstawy techniki mikroprocesorowej");
                        textView25_1.setVisibility(View.VISIBLE);
                        textView25_2.setText(ptm);
                        textView25_2.setVisibility(View.VISIBLE);
                        textView26_1.setText("Prawo informatyczne");
                        textView26_1.setVisibility(View.VISIBLE);
                        textView26_2.setText(pi);
                        textView26_2.setVisibility(View.VISIBLE);
                        textView27_1.setText("Sieci komputerowe");
                        textView27_1.setVisibility(View.VISIBLE);
                        textView27_2.setText(sk);
                        textView27_2.setVisibility(View.VISIBLE);
                        textView28_1.setText("SEMESTR 3 ZALICZONY?");
                        textView28_1.setVisibility(View.VISIBLE);
                        textView28_2.setText(semestr_3);
                        textView28_2.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addNotifications() {}

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
