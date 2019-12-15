package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Chat extends AppCompatActivity {

    private FirebaseListAdapter<ChatMessage> adapter;

    private EditText editTextSendMessage;
    private EditText editTextTopic;
    private Button buttonSendMessage;

    private TextView messageText;
    private TextView messageUser;
    private TextView messageTime;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("CHAT");


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.add_topic, null);

        editTextTopic = view.findViewById(R.id.editTextTopic);

        builder.setView(view)
                .setTitle("Wpisz temat rozmowy..")
                .setNegativeButton("ANULUJ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(new Intent(Chat.this, Home.class));
                    }
                })
                .setPositiveButton("DALEJ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        createMessage();
                    }
                });

        builder.create().show();

    }

    private void createMessage() {
        editTextSendMessage = findViewById(R.id.editTextSendMessage);
        buttonSendMessage = findViewById(R.id.buttonSendMessage);

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Message").child(editTextTopic.getText().toString()).push().setValue(new ChatMessage(editTextSendMessage.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                editTextSendMessage.setText("");
                editTextSendMessage.requestFocus();
            }
        });

        showMessage();
    }

    private void showMessage() {
        listView = findViewById(R.id.list_of_message);
        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.message_info, FirebaseDatabase.getInstance().getReference().child("Message").child(editTextTopic.getText().toString())) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                messageText = v.findViewById(R.id.textViewMessageText);
                messageUser = v.findViewById(R.id.textViewMessageUser);
                messageTime = v.findViewById(R.id.textViewMessageTime);

                messageText.setText(model.getText());
                messageUser.setText(model.getUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getTime()));
            }
        };
        listView.setAdapter(adapter);
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
