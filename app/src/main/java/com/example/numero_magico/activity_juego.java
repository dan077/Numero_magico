package com.example.numero_magico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class activity_juego extends AppCompatActivity implements View.OnClickListener {
    ListView Registro_intentos;
    ArrayList<String> intento;
    Button verificar,reset;
    TextView txt_intentos;
    TextView edt_numero;
    int intentos;
    ArrayAdapter<String> adapter;
    int num_aleatorio = (int) Math.floor(Math.random()*100+1);
    char dificultad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        Intent I = getIntent();
        dificultad = I.getCharExtra("dificultad",' ');

        Registro_intentos = (ListView)findViewById(R.id.registro_Intentos);
        verificar = findViewById(R.id.verificar);
        reset = findViewById(R.id.btn_reset);
        txt_intentos = findViewById(R.id.txt_intentos);
        edt_numero = findViewById(R.id.edt_numero);
        verificar.setOnClickListener(this);
        reset.setOnClickListener(this);
        calcularIntentos();
        intento = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, intento);
        txt_intentos.setText("Intentos: " + intentos);

    }

    public void calcularIntentos(){


        switch (dificultad){
            case '1':
                intentos = 10;
                break;
            case '2':
                intentos = 7;
                break;
            case '3':
                intentos = 5;
                break;
            default:
                intentos = 0;
                Toast.makeText(getApplicationContext(),"Algo ha salido mal. dificultad: " + dificultad ,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.verificar:
                if(!edt_numero.getText().toString().equalsIgnoreCase(""))
                {
                    int numero = Integer.parseInt(edt_numero.getText().toString());

                    if (numero >= 0 && numero <= 100) {
                        intentos--;
                        txt_intentos.setText("Intentos: " + intentos);

                        String estado = numero == num_aleatorio ? "el de la maquina" : numero > num_aleatorio ? "menor" : "mayor";
                        intento.add("Numero ingresado: " + edt_numero.getText().toString() + ". El numero de la maquina es " + estado);
                        Registro_intentos.setAdapter(adapter);


                        if (num_aleatorio == numero) {
                            intento.add("¡Ganó! ");
                            Registro_intentos.setAdapter(adapter);
                            Toast.makeText(getApplicationContext(), "¡¡¡Ganooooo!!!!", Toast.LENGTH_LONG).show();
                            verificar.setEnabled(false);
                        } else if (intentos == 0) {
                            intento.add("¡Perdió! el numero era: " + num_aleatorio);
                            Registro_intentos.setAdapter(adapter);
                            Toast.makeText(getApplicationContext(), "Perdiste, el numero era: " + num_aleatorio, Toast.LENGTH_LONG).show();
                            verificar.setEnabled(false);
                        } else {
                            edt_numero.setText("");
                        }
                        //hace que haga scroll hasta el ultimo elemento.
                        Registro_intentos.setSelection(Registro_intentos.getCount() - 1);
                    } else {
                        edt_numero.setError("Valor fuera de rango.Ingrese un numero comprendido entre 0 y 100");

                    }
                }
                else{
                    edt_numero.setError("Caja de texto vacia");

                }
                break;
            case R.id.btn_reset:
                calcularIntentos();
                intento.clear();
                Registro_intentos.setAdapter(adapter);
                txt_intentos.setText("Intentos: " + intentos);
                verificar.setEnabled(true); //por si el botón estaba desactivado.
                num_aleatorio = (int) Math.floor(Math.random()*100+1);
                Toast.makeText(getApplicationContext(), "Los datos han sido reseteados. un nuevo numero aleatorio ha surgido", Toast.LENGTH_LONG).show();
        }
    }
}