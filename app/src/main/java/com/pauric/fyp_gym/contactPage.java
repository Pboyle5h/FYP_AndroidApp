package com.pauric.fyp_gym;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class contactPage extends ActionBarActivity implements View.OnClickListener {
    EditText etMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);
        etMessage = (EditText) findViewById(R.id.etMessage);
        ImageButton Map = (ImageButton) findViewById(R.id.viewMap);
        Map.setOnClickListener( this);
        ImageButton sendMessage = (ImageButton) findViewById(R.id.btnSendMessage);
        sendMessage.setOnClickListener( this);
    }
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.viewMap:
                Intent map = new Intent(this, Location.class);
                startActivity(map);
                break;
            case R.id.btnSendMessage:
                sendEmail();
                break;

        }
    }


    public void sendEmail(){
        Intent intent = null, chooser=null;
        String[] email={"pauric18@hotmail.com"};
        String message=etMessage.getText().toString();


            intent=new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, email);
            intent.putExtra(Intent.EXTRA_SUBJECT, "hi");
            intent.putExtra(Intent.EXTRA_TEXT, message);
            intent.setType("message/rfc822");
            chooser=Intent.createChooser(intent, "Send Email");
            startActivity(chooser);

    }

}
