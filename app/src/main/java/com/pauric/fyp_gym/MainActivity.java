package com.pauric.fyp_gym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;


public class MainActivity extends Activity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton contactBtn = (ImageButton) findViewById(R.id.contactBtn);
        contactBtn.setOnClickListener(this);
        ImageButton aboutBtn = (ImageButton) findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(this);
        ImageButton storeBtn = (ImageButton) findViewById(R.id.storeBtn);
        storeBtn.setOnClickListener(this);

        ImageButton btnAccount = (ImageButton) findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        // switch statement to determine which button has been clicked
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
            case R.id.btnAccount:
                Intent account = new Intent(this, Account.class);
                startActivity(account);
                break;


        }
    }


}
