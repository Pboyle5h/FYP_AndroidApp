package com.pauric.fyp_gym;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton contactBtn = (ImageButton) findViewById(R.id.contactBtn);
        contactBtn.setOnClickListener(this);
        ImageButton aboutBtn = (ImageButton) findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(this);
        ImageButton storeBtn = (ImageButton) findViewById(R.id.storeBtn);
        storeBtn.setOnClickListener(this);



    }


    @Override
    //if user is not logged in sign in page will be loaded
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.contactBtn:
                Intent contact = new Intent(this, contactPage.class);
                startActivity(contact);
                break;
            case R.id.aboutBtn:
                Intent aboutIntent = new Intent(MainActivity.this, AboutPage.class);
                startActivity(aboutIntent);
                break;
            case R.id.storeBtn:
                Intent storeIntent = new Intent(MainActivity.this, Store.class);
                startActivity(storeIntent);
                break;

        }
    }


}
