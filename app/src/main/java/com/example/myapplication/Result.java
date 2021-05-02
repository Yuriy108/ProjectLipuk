package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        text=findViewById(R.id.textViewResult);
        Intent intent3=getIntent();
        String result=intent3.getStringExtra("res");
        text.setText(result);

    }

    public void back(View view) {
        Intent intent1=new Intent(this,Table.class);
        startActivity(intent1);
    }
}