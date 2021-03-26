package com.example.numero_magico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Spinner dificultad;
Button aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dificultad = (Spinner)findViewById(R.id.spinner_dificultad);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.Spinner_dificultad, android.R.layout.simple_spinner_item);
        dificultad.setAdapter(adapter);
        aceptar = findViewById(R.id.btn_aceptar);
        aceptar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_aceptar:
                String itemSelect = dificultad.getSelectedItem().toString();
                Intent i = new Intent(getApplicationContext(),activity_juego.class);
                i.putExtra("dificultad", itemSelect.charAt(0));
                startActivity(i);
                break;
        }
    }
}