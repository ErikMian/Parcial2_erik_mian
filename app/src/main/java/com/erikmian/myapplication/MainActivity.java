package com.erikmian.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    Button abrirdialogo,guardarinfo;
    EditText nombre, edad, genero;
    String guardarnombre,guardaredad,guardargenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abrirdialogo =  (Button)findViewById(R.id.buttoniralmenu);

        guardarnombre="Nombre";
        guardaredad="Edad";
        guardargenero="Genero";

        abrirdialogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogo();
            }
        });
    }
    private void mostrarDialogo() {
        AlertDialog.Builder alert = new AlertDialog.Builder( MainActivity.this);
        final View customlayout= getLayoutInflater().inflate(R.layout.datosdialogo, null);
        alert.setView(customlayout);
        nombre= customlayout.findViewById(R.id.editnombre);
        edad= customlayout.findViewById(R.id.editedad);
        genero= customlayout.findViewById(R.id.editgenero);
        guardarinfo= customlayout.findViewById(R.id.buttonguardar);



        guardarinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = new String(nombre.getText().toString());
                String eda = new String(edad.getText().toString());
                String gene = new String(genero.getText().toString());

                String nombres = "";
                String edades = "";
                String generos = "";

                if(!nom.equals(nombres)&&!eda.equals(edades)&&!gene.equals(generos)){

                    GuardarNombre();
                    GuardarEdad();
                    GuardarGenero();

                    Toast.makeText(getApplicationContext(), "los datos fueron guardados ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), menucategorias.class);
                    startActivityForResult(intent, 0);
                }else{
                    Toast.makeText(getApplicationContext(), "llene todos los campos", Toast.LENGTH_SHORT).show();
                }



            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    public void GuardarNombre(){

        try {
            OutputStreamWriter datonombre = new OutputStreamWriter(openFileOutput(guardarnombre, Context.MODE_PRIVATE));
            datonombre.write(nombre.getText().toString());


            datonombre.close();




        } catch (Exception e) {
            Log.e("Nombre", "Error al escribir archivo en la memoria interna");
        }


    }


    public void GuardarEdad(){

        try {
            OutputStreamWriter datoedad = new OutputStreamWriter(openFileOutput(guardaredad, Context.MODE_PRIVATE));
            datoedad.write(edad.getText().toString());

            datoedad.close();




        } catch (Exception e) {
            Log.e("Edad", "Error al escribir archivo en la memoria interna");
        }


    }


    public void GuardarGenero(){

        try {
            OutputStreamWriter datogenero = new OutputStreamWriter(openFileOutput(guardargenero, Context.MODE_PRIVATE));
            datogenero.write(genero.getText().toString());

            datogenero.close();




        } catch (Exception e) {
            Log.e("Genero", "Error al escribir archivo en la memoria interna");
        }


    }



}