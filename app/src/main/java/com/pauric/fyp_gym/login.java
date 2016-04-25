package com.pauric.fyp_gym;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class login extends Activity implements View.OnClickListener  {


    ImageButton bLogin;
    TextView registerLink;
    EditText etUsername, etPassword;


        public static final String USER_NAME = "username";

        public static final String PASSWORD = "password";

        private static final String LOGIN_URL = "http://pboyle5h.hol.es/Project/login.php";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            etUsername = (EditText) findViewById(R.id.etUsername);
            etPassword = (EditText) findViewById(R.id.etPassword);
            registerLink = (TextView) findViewById(R.id.tvRegisterLink);
            bLogin = (ImageButton) findViewById(R.id.bLogin);
            registerLink.setOnClickListener(this);
            bLogin.setOnClickListener(this);
            etPassword.setOnClickListener(this);
            etUsername.setOnClickListener(this);
        }


        private void login(){
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            Util.uName=username;
            userLogin(username, password);
        }

        private void userLogin(final String username, final String password){
            class UserLoginClass extends AsyncTask<String,Void,String> {
                ProgressDialog loading;
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(login.this,"Please Wait",null,true,true);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    if(s.equalsIgnoreCase("success")){
                        Intent intent = new Intent(login.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(login.this, s, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                protected String doInBackground(String... params) {
                    // the user inputted data entered passed to a hash map and passed to server requests
                    HashMap<String,String> data = new HashMap<>();
                    data.put("username",params[0]);
                    data.put("password",params[1]);

                    ServerRequests SR = new ServerRequests();

                    String result = SR.sendPostRequest(LOGIN_URL,data);

                    return result;
                }
            }
            UserLoginClass ulc = new UserLoginClass();
            ulc.execute(username,password);
        }


    @Override
    public void onClick(View view) {
        //once theres a button click this method is called
        //If there is more then one button on the view we use a switch statement to distinguise between the different buttons
        //To do this we switch on the id of the button and each case will have its own jobs.
        switch (view.getId()) {
            case R.id.bLogin:
               login();
                break;
            case R.id.tvRegisterLink:
                Intent registerIntent = new Intent(login.this, Register.class);
                startActivity(registerIntent);
                break;
            case R.id.etUsername:
                etUsername.setHint("");
                break;
            case R.id.etPassword:
                etPassword.setHint("");
                break;

        }
    }



}
