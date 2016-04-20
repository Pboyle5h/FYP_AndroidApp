package com.pauric.fyp_gym;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;


public class Register extends AppCompatActivity implements View.OnClickListener{
    EditText etName, etAge, etUsername, etPassword;
    ImageButton bRegister;
    public static final String REGISTER_URL = "http://pboyle5h.hol.es/Project/Register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (ImageButton) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v == bRegister){
            registerUser();
        }
    }

    private void registerUser() {
        String name = etName.getText().toString().trim().toLowerCase();
        String age = etAge.getText().toString().trim().toLowerCase();
        String username = etUsername.getText().toString().trim().toLowerCase();
        String password = etPassword.getText().toString().trim().toLowerCase();

        register(name, age, username, password);
    }

    private void register(String name, String username, String password, String age) {
        String urlSuffix = "?name="+name+"&email="+age+"&username="+username+"&password="+password;
        class RegisterUser extends AsyncTask<String, Void, String> {
            ServerRequests SR = new ServerRequests();
            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    Intent loginIntent = new Intent(Register.this, login.class);
                    startActivity(loginIntent);

            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("name",params[0]);
                data.put("email",params[1]);
                data.put("username",params[2]);
                data.put("password", params[3]);

                String result = SR.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,username,password,age);
    }

}
