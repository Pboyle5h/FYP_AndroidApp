package com.pauric.fyp_gym;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class contactPage extends Activity implements View.OnClickListener {
    EditText etMessage;
    String eMessage;
    String rec="warehousegymgalway16@gmail.com";
    Session session = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);

        etMessage = (EditText) findViewById(R.id.name);
        ImageButton Map = (ImageButton) findViewById(R.id.viewMap);
        Map.setOnClickListener(this);
        ImageButton sendMessage = (ImageButton) findViewById(R.id.btnSendMessage);
        sendMessage.setOnClickListener(this);
        etMessage.setOnClickListener(this);
    }

    public void onClick(View v) {
    //switch statment to determine which button was clicked
        switch (v.getId()) {
            case R.id.viewMap:
                Intent map = new Intent(this, Location.class);
                startActivity(map);
                break;
            case R.id.btnSendMessage:
                sendEmail();
                break;
            case R.id.name:
                etMessage.setHint("");
                break;

        }
    }


    public void sendEmail() {
        eMessage = etMessage.getText().toString();
        // setup of the java mail apis properties.
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //signs into the email account so that the email can be sent
        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("warehousegymgalway16@gmail.com", "finalyearproject16");
            }
        });
        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();

    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {
        ProgressDialog loading;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(contactPage.this, "Sending Message", null, true, true);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                //sets up the message recipient and and subject line
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("warehousegymgalway16@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject("Query from " + Util.uName);
                message.setContent(eMessage, "text/html; charset=utf-8");
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            //dialog box closed
            loading.dismiss();
            //message box returned to blank
            etMessage.setText("");
            //toast displayed to the user to say message sent
            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
        }

    }
}
