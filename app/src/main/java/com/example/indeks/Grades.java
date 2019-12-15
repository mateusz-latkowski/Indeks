package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableRow;
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
    private TextView textView29_1;
    private TextView textView29_2;

    private final String CHANNEL_ID = "personal_notification";
    private final int NOTIFICATION_ID = 001;

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

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Students").child("Students_Info").child(userID);
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
        textView29_1 = (TextView) findViewById(R.id.textViewRow29_1);
        textView29_2 = (TextView) findViewById(R.id.textViewRow29_2);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Grades").child("Informatyka Stosowana");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String lektorat_1 = dataSnapshot.child("Semestr_1").child("Lektorat języka angielskiego I").child(userID).child("Lektorat").getValue().toString();
                String matematyka_1 = dataSnapshot.child("Semestr_1").child("Matematyka I").child(userID).child("Konwersatorium").getValue().toString();
                String owi = dataSnapshot.child("Semestr_1").child("Ochrona własności intelektualnej").child(userID).child("Wykład").getValue().toString();
                String pusk = dataSnapshot.child("Semestr_1").child("Podstawy użytkowania systemów komputerowych").child(userID).child("Laboratorium").getValue().toString();
                String wdi_l = dataSnapshot.child("Semestr_1").child("Wstęp do informatyki").child(userID).child("Laboratorium").getValue().toString();
                String wdi_w = dataSnapshot.child("Semestr_1").child("Wstęp do informatyki").child(userID).child("Wykład").getValue().toString();
                String wdi_okm = dataSnapshot.child("Semestr_1").child("Wstęp do informatyki").child(userID).child("OKM").getValue().toString();
                String wdpia = dataSnapshot.child("Semestr_1").child("Wstęp do pomiarów i automatyki").child(userID).child("Laboratorium").getValue().toString();
                String wdp_l = dataSnapshot.child("Semestr_1").child("Wstęp do programowania").child(userID).child("Laboratorium").getValue().toString();
                String wdp_w = dataSnapshot.child("Semestr_1").child("Wstęp do programowania").child(userID).child("Wykład").getValue().toString();
                String wdp_okm = dataSnapshot.child("Semestr_1").child("Wstęp do programowania").child(userID).child("OKM").getValue().toString();
                String semestr_1 = dataSnapshot.child("Semestr_1").child("ZALICZENIE SEMESTRU").child(userID).child("Semestr zaliczony?").getValue().toString();

                textView2_1.setText("Lektorat języka angielskiego I");
                textView2_2.setText("L: " + lektorat_1);
                textView3_1.setText("Matematyka I");
                textView3_2.setText("K: " + matematyka_1);
                textView4_1.setText("Ochrona własności intelektualnej");
                textView4_2.setText("W: " + owi);
                textView5_1.setText("Podstawy użytkowania systemów komputerowych");
                textView5_2.setText("L: " + pusk);
                textView6_1.setText("Wstęp do informatyki\n\n");
                textView6_2.setText("L: " + wdi_l + "\nW: " + wdi_w + "\nOKM: " + wdi_okm);
                textView7_1.setText("Wstęp do pomiarów i automatyki");
                textView7_2.setText("L: " + wdpia);
                textView8_1.setText("Wstęp do programowania\n\n");
                textView8_2.setText("L: " + wdp_l + "\nW: " + wdp_w + "\nOKM: " + wdp_okm);
                textView9_1.setText("SEMESTR 1 ZALICZONY?");
                textView9_2.setText("S: " + semestr_1);

                if (semestr_1.equals("TAK")) {
                    String aip_l = dataSnapshot.child("Semestr_2").child("Algorytmy i programowanie").child(userID).child("Laboratorium").getValue().toString();
                    String aip_k = dataSnapshot.child("Semestr_2").child("Algorytmy i programowanie").child(userID).child("Konwersatorium").getValue().toString();
                    String aip_w = dataSnapshot.child("Semestr_2").child("Algorytmy i programowanie").child(userID).child("Wykład").getValue().toString();
                    String aip_okm = dataSnapshot.child("Semestr_2").child("Algorytmy i programowanie").child(userID).child("OKM").getValue().toString();
                    String ak_l =  dataSnapshot.child("Semestr_2").child("Architektura komputerów").child(userID).child("Laboratorium").getValue().toString();
                    String ak_w =  dataSnapshot.child("Semestr_2").child("Architektura komputerów").child(userID).child("Wykład").getValue().toString();
                    String ak_okm =  dataSnapshot.child("Semestr_2").child("Architektura komputerów").child(userID).child("OKM").getValue().toString();
                    String fizyka_l = dataSnapshot.child("Semestr_2").child("Fizyka").child(userID).child("Laboratorium").getValue().toString();
                    String fizyka_k = dataSnapshot.child("Semestr_2").child("Fizyka").child(userID).child("Konwersatorium").getValue().toString();
                    String fizyka_w = dataSnapshot.child("Semestr_2").child("Fizyka").child(userID).child("Wykład").getValue().toString();
                    String fizyka_okm = dataSnapshot.child("Semestr_2").child("Fizyka").child(userID).child("OKM").getValue().toString();
                    String lektorat_2 =  dataSnapshot.child("Semestr_2").child("Lektorat języka angielskiego II").child(userID).child("Lektorat").getValue().toString();
                    String matematyka_2 =  dataSnapshot.child("Semestr_2").child("Matematyka II").child(userID).child("Konwersatorium").getValue().toString();
                    String peie_l =  dataSnapshot.child("Semestr_2").child("Podstawy elektrotechniki i elektroniki").child(userID).child("Laboratorium").getValue().toString();
                    String peie_w =  dataSnapshot.child("Semestr_2").child("Podstawy elektrotechniki i elektroniki").child(userID).child("Wykład").getValue().toString();
                    String peie_okm =  dataSnapshot.child("Semestr_2").child("Podstawy elektrotechniki i elektroniki").child(userID).child("OKM").getValue().toString();
                    String semestr_2 =  dataSnapshot.child("Semestr_2").child("ZALICZENIE SEMESTRU").child(userID).child("Semestr zaliczony?").getValue().toString();

                    TableRow row2_1 = findViewById(R.id.row2_1);
                    row2_1.setBackgroundResource(R.color.TableBorder);
                    TableRow row2_2 = findViewById(R.id.row2_2);
                    row2_2.setBackgroundResource(R.color.TableBorder);
                    TableRow row2_3 = findViewById(R.id.row2_3);
                    row2_3.setBackgroundResource(R.color.TableBorder);
                    TableRow row2_4 = findViewById(R.id.row2_4);
                    row2_4.setBackgroundResource(R.color.TableBorder);
                    TableRow row2_5 = findViewById(R.id.row2_5);
                    row2_5.setBackgroundResource(R.color.TableBorder);
                    TableRow row2_6 = findViewById(R.id.row2_6);
                    row2_6.setBackgroundResource(R.color.TableBorder);
                    TableRow row2_7 = findViewById(R.id.row2_7);
                    row2_7.setBackgroundResource(R.color.TableBorder);
                    TableRow row2_8 = findViewById(R.id.row2_8);
                    row2_8.setBackgroundResource(R.color.TableBorder);

                    break_1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView2.setBackgroundResource(R.color.titleBackground);
                    textView11_1.setText("Algorytmy i programowanie\n\n\n");
                    textView11_1.setVisibility(View.VISIBLE);
                    textView11_1.setBackgroundResource(R.color.rowsBackground);
                    textView11_2.setText("L: " + aip_l + "\nK: " + aip_k + "\nW: " + aip_w + "\nOKM: " + aip_okm);
                    textView11_2.setVisibility(View.VISIBLE);
                    textView11_2.setBackgroundResource(R.color.rowsBackground);
                    textView12_1.setText("Architektura komputerów\n\n");
                    textView12_1.setVisibility(View.VISIBLE);
                    textView12_1.setBackgroundResource(R.color.rowsBackground);
                    textView12_2.setText("L: " + ak_l + "\nW: " + ak_w  + "\nOKM: " + ak_okm);
                    textView12_2.setVisibility(View.VISIBLE);
                    textView12_2.setBackgroundResource(R.color.rowsBackground);
                    textView13_1.setText("Fizyka\n\n\n");
                    textView13_1.setVisibility(View.VISIBLE);
                    textView13_1.setBackgroundResource(R.color.rowsBackground);
                    textView13_2.setText("L: " + fizyka_l + "\nK: " + fizyka_k + "\nW: " + fizyka_w + "\nOKM: " + fizyka_okm);
                    textView13_2.setVisibility(View.VISIBLE);
                    textView13_2.setBackgroundResource(R.color.rowsBackground);
                    textView14_1.setText("Lektorat języka angielskiego II");
                    textView14_1.setVisibility(View.VISIBLE);
                    textView14_1.setBackgroundResource(R.color.rowsBackground);
                    textView14_2.setText("L: " + lektorat_2);
                    textView14_2.setVisibility(View.VISIBLE);
                    textView14_2.setBackgroundResource(R.color.rowsBackground);
                    textView15_1.setText("Matematyka II");
                    textView15_1.setVisibility(View.VISIBLE);
                    textView15_1.setBackgroundResource(R.color.rowsBackground);
                    textView15_2.setText("K: " + matematyka_2);
                    textView15_2.setVisibility(View.VISIBLE);
                    textView15_2.setBackgroundResource(R.color.rowsBackground);
                    textView16_1.setText("Podstawy elektrotechniki i elektroniki\n\n");
                    textView16_1.setVisibility(View.VISIBLE);
                    textView16_1.setBackgroundResource(R.color.rowsBackground);
                    textView16_2.setText("L: " + peie_l + "\nW:" + peie_w + "\nOKM: " + peie_okm);
                    textView16_2.setVisibility(View.VISIBLE);
                    textView16_2.setBackgroundResource(R.color.rowsBackground);
                    textView17_1.setText("SEMESTR 2 ZALICZONY?");
                    textView17_1.setVisibility(View.VISIBLE);
                    textView17_1.setBackgroundResource(R.color.titleBackground);
                    textView17_2.setText("S: " + semestr_2);
                    textView17_2.setVisibility(View.VISIBLE);
                    textView17_2.setBackgroundResource(R.color.titleBackground);

                    if (semestr_2.equals("TAK")) {
                        String aisd_l = dataSnapshot.child("Semestr_3").child("Algorytmy i struktury danych").child(userID).child("Laboratorium").getValue().toString();
                        String aisd_k = dataSnapshot.child("Semestr_3").child("Algorytmy i struktury danych").child(userID).child("Konwersatorium").getValue().toString();
                        String aisd_w = dataSnapshot.child("Semestr_3").child("Algorytmy i struktury danych").child(userID).child("Wykład").getValue().toString();
                        String aisd_okm = dataSnapshot.child("Semestr_3").child("Algorytmy i struktury danych").child(userID).child("OKM").getValue().toString();
                        String bd_l = dataSnapshot.child("Semestr_3").child("Bazy danych").child(userID).child("Laboratorium").getValue().toString();
                        String bd_w = dataSnapshot.child("Semestr_3").child("Bazy danych").child(userID).child("Wykład").getValue().toString();
                        String bd_okm = dataSnapshot.child("Semestr_3").child("Bazy danych").child(userID).child("OKM").getValue().toString();
                        String lektorat_3 =  dataSnapshot.child("Semestr_3").child("Lektorat języka angielskiego III").child(userID).child("Lektorat").getValue().toString();
                        String md_k = dataSnapshot.child("Semestr_3").child("Matematyka dyskretna").child(userID).child("Konwersatorium").getValue().toString();
                        String md_w = dataSnapshot.child("Semestr_3").child("Matematyka dyskretna").child(userID).child("Wykład").getValue().toString();
                        String md_okm = dataSnapshot.child("Semestr_3").child("Matematyka dyskretna").child(userID).child("OKM").getValue().toString();
                        String ptm =  dataSnapshot.child("Semestr_3").child("Podstawy techniki mikroprocesorowej").child(userID).child("Laboratorium").getValue().toString();
                        String pi =  dataSnapshot.child("Semestr_3").child("Prawo informatyczne").child(userID).child("Wykład").getValue().toString();
                        String sk_l =  dataSnapshot.child("Semestr_3").child("Sieci komputerowe").child(userID).child("Laboratorium").getValue().toString();
                        String sk_w =  dataSnapshot.child("Semestr_3").child("Sieci komputerowe").child(userID).child("Wykład").getValue().toString();
                        String sk_okm =  dataSnapshot.child("Semestr_3").child("Sieci komputerowe").child(userID).child("OKM").getValue().toString();
                        String wf = dataSnapshot.child("Semestr_3").child("WF").child(userID).child("Ćwiczenia").getValue().toString();
                        String semestr_3 =  dataSnapshot.child("Semestr_3").child("ZALICZENIE SEMESTRU").child(userID).child("Semestr zaliczony?").getValue().toString();

                        TableRow row3_1 = findViewById(R.id.row3_1);
                        row3_1.setBackgroundResource(R.color.TableBorder);
                        TableRow row3_2 = findViewById(R.id.row3_2);
                        row3_2.setBackgroundResource(R.color.TableBorder);
                        TableRow row3_3 = findViewById(R.id.row3_3);
                        row3_3.setBackgroundResource(R.color.TableBorder);
                        TableRow row3_4 = findViewById(R.id.row3_4);
                        row3_4.setBackgroundResource(R.color.TableBorder);
                        TableRow row3_5 = findViewById(R.id.row3_5);
                        row3_5.setBackgroundResource(R.color.TableBorder);
                        TableRow row3_6 = findViewById(R.id.row3_6);
                        row3_6.setBackgroundResource(R.color.TableBorder);
                        TableRow row3_7 = findViewById(R.id.row3_7);
                        row3_7.setBackgroundResource(R.color.TableBorder);
                        TableRow row3_8 = findViewById(R.id.row3_8);
                        row3_8.setBackgroundResource(R.color.TableBorder);
                        TableRow row3_9 = findViewById(R.id.row3_9);
                        row3_9.setBackgroundResource(R.color.TableBorder);
                        TableRow row3_10 = findViewById(R.id.row3_10);
                        row3_10.setBackgroundResource(R.color.TableBorder);

                        break_2.setVisibility(View.VISIBLE);
                        textView3.setVisibility(View.VISIBLE);
                        textView3.setBackgroundResource(R.color.titleBackground);
                        textView21_1.setText("Algorytmy i struktury danych\n\n\n");
                        textView21_1.setVisibility(View.VISIBLE);
                        textView21_1.setBackgroundResource(R.color.rowsBackground);
                        textView21_2.setText("L: " + aisd_l + "\nK: " + aisd_k + "\nW: " + aisd_w + "\nOKM: " + aisd_okm);
                        textView21_2.setVisibility(View.VISIBLE);
                        textView21_2.setBackgroundResource(R.color.rowsBackground);
                        textView22_1.setText("Bazy danych\n\n");
                        textView22_1.setVisibility(View.VISIBLE);
                        textView22_1.setBackgroundResource(R.color.rowsBackground);
                        textView22_2.setText("L: " + bd_l + "\nW: " + bd_w + "\nOKM: " + bd_okm);
                        textView22_2.setVisibility(View.VISIBLE);
                        textView22_2.setBackgroundResource(R.color.rowsBackground);
                        textView23_1.setText("Lektorat języka angielskiego III");
                        textView23_1.setVisibility(View.VISIBLE);
                        textView23_1.setBackgroundResource(R.color.rowsBackground);
                        textView23_2.setText("L: " + lektorat_3);
                        textView23_2.setVisibility(View.VISIBLE);
                        textView23_2.setBackgroundResource(R.color.rowsBackground);
                        textView24_1.setText("Matematyka dyskretna\n\n");
                        textView24_1.setVisibility(View.VISIBLE);
                        textView24_1.setBackgroundResource(R.color.rowsBackground);
                        textView24_2.setText("K: " + md_k + "\nW: " + md_w + "\nOKM: " + md_okm);
                        textView24_2.setVisibility(View.VISIBLE);
                        textView24_2.setBackgroundResource(R.color.rowsBackground);
                        textView25_1.setText("Podstawy techniki mikroprocesorowej");
                        textView25_1.setVisibility(View.VISIBLE);
                        textView25_1.setBackgroundResource(R.color.rowsBackground);
                        textView25_2.setText("L: " + ptm);
                        textView25_2.setVisibility(View.VISIBLE);
                        textView25_2.setBackgroundResource(R.color.rowsBackground);
                        textView26_1.setText("Prawo informatyczne");
                        textView26_1.setVisibility(View.VISIBLE);
                        textView26_1.setBackgroundResource(R.color.rowsBackground);
                        textView26_2.setText("W: " + pi);
                        textView26_2.setVisibility(View.VISIBLE);
                        textView26_2.setBackgroundResource(R.color.rowsBackground);
                        textView27_1.setText("Sieci komputerowe\n\n");
                        textView27_1.setVisibility(View.VISIBLE);
                        textView27_1.setBackgroundResource(R.color.rowsBackground);
                        textView27_2.setText("L: " + sk_l + "\nW: " + sk_w + "\nOKM: " + sk_okm);
                        textView27_2.setVisibility(View.VISIBLE);
                        textView27_2.setBackgroundResource(R.color.rowsBackground);
                        textView28_1.setText("WF");
                        textView28_1.setVisibility(View.VISIBLE);
                        textView28_1.setBackgroundResource(R.color.rowsBackground);
                        textView28_2.setText("Ć: " + wf);
                        textView28_2.setVisibility(View.VISIBLE);
                        textView28_2.setBackgroundResource(R.color.rowsBackground);
                        textView29_1.setText("SEMESTR 3 ZALICZONY?");
                        textView29_1.setVisibility(View.VISIBLE);
                        textView29_1.setBackgroundResource(R.color.titleBackground);
                        textView29_2.setText("S: " + semestr_3);
                        textView29_2.setVisibility(View.VISIBLE);
                        textView29_2.setBackgroundResource(R.color.titleBackground);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void notification() {
        createNotificationChannel();
        Intent intent = new Intent(this, Grades.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_message);
        builder.setContentTitle("Indeks elektroniczny");
        builder.setContentText("Dodano nową ocenę!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Personal Notifications";
            String description = "Include all the personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
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
