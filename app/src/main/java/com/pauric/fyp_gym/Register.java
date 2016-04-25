

package com.pauric.fyp_gym;
import android.app.Activity;
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

import java.util.HashMap;


public class Register extends Activity implements View.OnClickListener{
    EditText etName, etEmail, etUsername, etPassword, etAge;
    ImageButton bRegister;
    public static final String REGISTER_URL = "http://pboyle5h.hol.es/Project/Register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (ImageButton) findViewById(R.id.bRegister);
        //on click listener which is always listening for click event
        bRegister.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v == bRegister){
            registerUser();
        }
    }

    private void registerUser() {
        //get the data entered by the user
        String name = etName.getText().toString().trim().toLowerCase();
        String email = etEmail.getText().toString().trim().toLowerCase();
        String username = etUsername.getText().toString().trim().toLowerCase();
        String password = etPassword.getText().toString().trim().toLowerCase();
        //passes them to the register method
        register(name, email, username, password);
    }

    private void register(String name, String username, String password, String email) {
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
               //passes the data into a hash map
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("name",params[0]);
                data.put("email",params[1]);
                data.put("username",params[2]);
                data.put("password", params[3]);
                // the hash map is then passed to the server requests along with the url
                String result = SR.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,username,password,email);
    }

}
