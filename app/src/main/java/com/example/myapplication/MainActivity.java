package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String name="https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    String kurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Usddownload usd=new Usddownload();
        usd.execute(name);
    }

    public void start(View view) {
        Intent intent=new Intent(this,Table.class);
        intent.putExtra("kurs",kurs);
        startActivity(intent);

}
class Usddownload extends AsyncTask<String,Void,String> {
    URL url = null;
    HttpURLConnection httpURLConnection = null;
    StringBuilder result = new StringBuilder();

    @Override
    protected String doInBackground(String... strings) {
        try {
            url = new URL(strings[0]);
            httpURLConnection = (HttpURLConnection) (url.openConnection());
            InputStream stream = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null) {
                result.append(line);
                line = bufferedReader.readLine();

            }
            return result.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("Myresult", s);
        try {

            JSONArray array = new JSONArray(s);
            JSONObject USD = array.getJSONObject(0);
            kurs = USD.getString("buy");
            Log.d("Myresult", kurs);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
}