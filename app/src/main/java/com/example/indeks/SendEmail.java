package com.example.indeks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

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

    private Session session;

    String email;
    String password;
    String recipient;
    String subject;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        enterMail = (EditText) findViewById(R.id.editTextEnterEmail);
        enterPass = (EditText) findViewById(R.id.editTextEnterPassword);

        editTextRecipient = (EditText) findViewById(R.id.editTextRecipient);
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextMess = (EditText) findViewById(R.id.editTextMessage);
        buttonSend = (Button) findViewById(R.id.buttonSendEmail);

        buttonSend.setOnClickListener(this);
    }

    private void sendEmail() {
        email = enterMail.getText().toString().trim();
        password = enterPass.getText().toString().trim();

        recipient = editTextRecipient.getText().toString().trim();
        subject = editTextSubject.getText().toString().trim();
        message = editTextMess.getText().toString().trim();

        SendEmailConfiguration smc = new SendEmailConfiguration();
        smc.execute();
    }

    class SendEmailConfiguration extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Properties prop = new Properties();

            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.port", "465");

            session = Session.getDefaultInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(email, password);
                        }
                    });

            try {
                MimeMessage mess = new MimeMessage(session);
                mess.setFrom(new InternetAddress(email));
                mess.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                mess.setSubject(subject);
                mess.setText(message);

                Transport.send(mess);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            finish();
            Toast.makeText(getApplicationContext(), "Twoja wiadomość została wysłana!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View v) {
        sendEmail();
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
