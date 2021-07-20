package com.erikmian.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import android.content.SharedPreferences;

public class menucategorias extends AppCompatActivity {

    TextView textnombre,textedad,textcaricatura,textterror,textcomedia;
    ImageView imacaricatura,imaterror,imacomedia;
    Button aceptarfoto,cancelarfoto;
    String guardarnombre,guardaredad,guardargenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menucategorias);
        textnombre = (TextView) findViewById(R.id.textViewnombre);
        textedad = (TextView) findViewById(R.id.textViewedad);
        textcaricatura = (TextView) findViewById(R.id.textcaricaturas);
        textterror = (TextView) findViewById(R.id.textterror);
        textcomedia = (TextView) findViewById(R.id.texttextcomedia);

        imacaricatura=(ImageView) findViewById(R.id.imageViewcaricaturas);
        imaterror=(ImageView) findViewById(R.id.imageViewterror);
        imacomedia=(ImageView) findViewById(R.id.imageViewcomedia);

        guardarnombre="Nombre";
        guardaredad="Edad";
        guardargenero="Genero";

        LeerNombre();
        LeerEdad();

        String edad = textedad.getText().toString();
        int num = Integer.parseInt(edad);

        if(num<12){

            imaterror.setVisibility(View.INVISIBLE);
            textterror.setVisibility(View.INVISIBLE);
            imacomedia.setVisibility(View.INVISIBLE);
            textcomedia.setVisibility(View.INVISIBLE);

            Toast.makeText(getApplicationContext(), "eres menorr de 12 años solo puedes acceder a caricaturas", Toast.LENGTH_LONG).show();


        }

        if(num>=12&&num<18){

            imacomedia.setVisibility(View.INVISIBLE);
            textcomedia.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "eres mayor de 12 y meno de 18 años, solo puedes acceder a caricaturas y terror", Toast.LENGTH_LONG).show();


        }
        if(num>=18){

            Toast.makeText(getApplicationContext(), "eres mayor de 18 años, tienes acceso a las categorias de caricatura, terror y comedia", Toast.LENGTH_LONG).show();


        }

        imacaricatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dato =textcaricatura.getText().toString();
                //se crea el archivo
                SharedPreferences preferences = getSharedPreferences("Todaslascategorias",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= preferences.edit();
                editor.putString("dato",dato);
                editor.commit();



                mostrarDialogoTomarFoto();
            }
        });

        imaterror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dato =textterror.getText().toString();
                //se crea el archivo
                SharedPreferences preferences = getSharedPreferences("Todaslascategorias",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= preferences.edit();
                editor.putString("dato",dato);
                editor.commit();


                mostrarDialogoTomarFoto();
            }
        });

        imacomedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dato =textcomedia.getText().toString();
                //se crea el archivo
                SharedPreferences preferences = getSharedPreferences("Todaslascategorias",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= preferences.edit();
                editor.putString("dato",dato);
                editor.commit();


                mostrarDialogoTomarFoto();
            }
        });

    }

    //Leer los nombres
    private void LeerNombre() {
        String textoffilenombre = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(guardarnombre)));
            textoffilenombre = bufferedReader.readLine();
            bufferedReader.close();
            if (textoffilenombre != null) {
                textnombre.setText(textoffilenombre);

            } else {
                Toast.makeText(getApplicationContext(), "El Archivo Esta Vacio", Toast.LENGTH_SHORT).show();


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    //Leer las edades
    private void LeerEdad() {
        String textoffileedad = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(guardaredad)));
            textoffileedad = bufferedReader.readLine();
            bufferedReader.close();
            if (textoffileedad != null) {
                textedad.setText(textoffileedad);

            } else {
                Toast.makeText(getApplicationContext(), "El Archivo Esta Vacio", Toast.LENGTH_SHORT).show();


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    private void mostrarDialogoTomarFoto() {
        AlertDialog.Builder alert = new AlertDialog.Builder( menucategorias.this);
        final View customlayout= getLayoutInflater().inflate(R.layout.tomarfoto, null);
        alert.setView(customlayout);

        aceptarfoto= customlayout.findViewById(R.id.buttonaceptar);
        cancelarfoto= customlayout.findViewById(R.id.buttoncancelar);




        aceptarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), verpelicula.class);
                startActivityForResult(intent, 0);





            }
        });

        cancelarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Toast.makeText(getApplicationContext(), "USTED A CANCELADO ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), menucategorias.class);
                startActivityForResult(intent, 0);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }




}