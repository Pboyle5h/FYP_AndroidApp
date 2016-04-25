package com.pauric.fyp_gym;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;


public class Account extends Activity {
    String nameText, emailText,usernameText;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_account);
            //creates an instanse of the DownloadTask class
            DownloadTask task = new DownloadTask();
            //excutes the url followed by the users username so that it can return the users details
            task.execute("http://pboyle5h.hol.es/Project/fetchData.php?username=" + Util.uName);

        }
        //download task which is async so it is a threa that runs at the same time as the main thread
        public class DownloadTask extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //A please wait dialog which starts before the task
                loading = ProgressDialog.show(Account.this, "Please Wait", null, true, true);
            }

            @Override
            protected String doInBackground(String... urls) {

                String result = "";
                URL url;
                HttpURLConnection urlConnection = null;
                // setting up a new http connection so that the application can execute the state url
                try {
                    url = new URL(urls[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    int data = reader.read();

                    while (data != -1) {

                        char current = (char) data;

                        result += current;

                        data = reader.read();

                    }

                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                try {

                    JSONObject jsonObject = new JSONObject(result);

                    String userInfo = jsonObject.getString("result");



                    JSONArray arr = new JSONArray(userInfo);

                    for (int i = 0; i < arr.length(); i++) {

                        JSONObject jsonPart = arr.getJSONObject(i);

                        emailText = jsonPart.getString("email");
                        nameText = jsonPart.getString("name");
                        usernameText = jsonPart.getString("username");
                        TextView email =(TextView) findViewById(R.id.email);
                        email.setText("Email: "+emailText);
                        TextView name =(TextView) findViewById(R.id.name);
                        name.setText("Name: "+nameText);
                        TextView username =(TextView) findViewById(R.id.username);
                        username.setText("Username: "+usernameText);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                loading.dismiss();
            }
        }

    }

