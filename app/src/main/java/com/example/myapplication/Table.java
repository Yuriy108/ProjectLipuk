
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Table extends AppCompatActivity {
    TextView textViewKursUSD;
    TextView TextViewKursEUR;
    Spinner spinnerImporter;
    Spinner spinnerYear;
    Spinner spinnerGas;
    Spinner money;
    EditText price;
    EditText engine;
    Integer acchiz;
    Double mainTax;
    Double nds;
    Double result;
    Boolean electro=true;
    String textKurs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        textViewKursUSD=findViewById(R.id.textView2);
        TextViewKursEUR=findViewById(R.id.textView11);
        spinnerImporter=findViewById(R.id.spinner2);
        spinnerYear=findViewById(R.id.spinner3);
        spinnerGas=findViewById(R.id.spinner4);
        money=findViewById(R.id.spinner5);
        price=findViewById(R.id.editTexMoney);
        engine=findViewById(R.id.editTextEngine);
        Intent intent=getIntent();
        textKurs=intent.getStringExtra("kurs");
        textViewKursUSD.setText(textKurs);
        Double euro=Double.parseDouble(textKurs)*1.19;
        TextViewKursEUR.setText(euro.toString());
    }

    public void count(View view) {
        Integer year=Integer.parseInt(spinnerYear.getSelectedItem().toString());
        Integer age=2021-year;
        Integer eng=Integer.parseInt(engine.getText().toString());
        Integer autoPrice=Integer.parseInt(price.getText().toString());
        String mon=money.getSelectedItem().toString();
        String fuel=spinnerGas.getSelectedItem().toString();
        switch (fuel){
            case "Дизель": if(eng<3.5){
                acchiz=75*age*eng;
            } else{
                acchiz=150*age*eng;
            } break;
            case "Бензин": if(eng<=3){
                acchiz=50*age*eng;
            }else{
                acchiz=100*age*eng;
            } break;
            case "Электро":
                acchiz = 0;
                electro=false;
        }

        mainTax=autoPrice*0.1;
        nds=(autoPrice+acchiz+mainTax)*0.2;
        //Toast.makeText(this," "+nds+"   "+mainTax+"   "+acchiz,Toast.LENGTH_LONG).show();
        if(electro){
            result=(acchiz+mainTax+nds);
        }else {result=0.0;}
        if(mon.equals("USD")){
            result=result*28;
        }else {result=result*28*1.19;}
        Intent intent2=new Intent(this,Result.class);
        intent2.putExtra("res",result.toString());
       startActivity(intent2);
        //Toast.makeText(this," "+result,Toast.LENGTH_LONG).show();


    }

    public void clear(View view) {
        price.setText(" ");
        engine.setText(" ");

    }
}