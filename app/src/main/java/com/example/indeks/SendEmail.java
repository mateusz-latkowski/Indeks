package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail extends AppCompatActivity implements View.OnClickListener {

    private EditText enterMail;
    private EditText enterPass;

    private EditText editTextRecipient;
    private EditText editTextSubject;
    private EditText editTextMess;
    private Button buttonSend;

    String mail;
    String password;
    String recipient;
    String subject;
    String message;

    Context context = null;
    Session session = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;

        enterMail = (EditText) findViewById(R.id.editTextEnterEmail);
        enterPass = (EditText) findViewById(R.id.editTextEnterPassword);

        editTextRecipient = (EditText) findViewById(R.id.editTextRecipient);
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextMess = (EditText) findViewById(R.id.editTextMessage);
        buttonSend = (Button) findViewById(R.id.buttonSendEmail);

        buttonSend.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        mail = enterMail.getText().toString();
        password = enterPass.getText().toString();
        recipient = editTextRecipient.getText().toString();
        subject = editTextSubject.getText().toString();
        message = editTextMess.getText().toString();

        Properties prop = new Properties();

        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(mail));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                message.setSubject(subject);
                message.setContent(message, "text/html");
                Transport.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //finish();
            Toast.makeText(getApplicationContext(), "Wiadomość została wysłana!", Toast.LENGTH_SHORT).show();

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
