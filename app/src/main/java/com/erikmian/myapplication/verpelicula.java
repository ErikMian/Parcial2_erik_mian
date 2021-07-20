package com.erikmian.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.MediaController;
import android.widget.VideoView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class verpelicula extends AppCompatActivity {

    TextView textnombre,textedad,textgenero,textpelicula,textcategoria;
    ImageView imafotodeperfil;
    VideoView reproducirpelicula;
    Button btplay,btpausa,btregresar;
    String guardarnombre,guardaredad,guardargenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verpelicula);

        imafotodeperfil = (ImageView) findViewById(R.id.imageViewfotodeperfil);
        textnombre = (TextView) findViewById(R.id.textViewnombreperfil);
        textedad = (TextView) findViewById(R.id.textViewedadperfil);
        textgenero = (TextView) findViewById(R.id.textViewgeneroperfil);
        textcategoria = (TextView) findViewById(R.id.textcategoriaperfil);
        textpelicula = (TextView) findViewById(R.id.texttitulopeliperfil);

        btplay =(Button) findViewById(R.id.buttonplay);
        btpausa =(Button) findViewById(R.id.buttonpausa);
        btregresar =(Button) findViewById(R.id.buttonregresar);

        guardarnombre="Nombre";
        guardaredad="Edad";
        guardargenero="Genero";

        SharedPreferences preferences = getSharedPreferences("Todaslascategorias", Context.MODE_PRIVATE);
        textcategoria.setText(preferences.getString("dato","no guardaste nada"));


        TomarFoto();
        LeerNombre();
        LeerEdad();
        LeerGenero();

        String categorias = new String(textcategoria.getText().toString());
        String caricatura = "CARICATURA";
        String terror = "TERROR";
        String comedia = "COMEDIA";

        if(categorias.equals(caricatura)){

            Reproducircaricatura();
            String titulocaricatura = "BOB SPONJA AL RESCATE";
            textpelicula.setText(titulocaricatura);
        }

        if(categorias.equals(terror)){

            ReproducirTerror();
            String titulocaricatura = "ANABELLE 3 VIENE A CASA";
            textpelicula.setText(titulocaricatura);
        }
        if(categorias.equals(comedia)){

            ReproducirComedia();
            String titulocaricatura = "NO MANCHES FRIDA 2";
            textpelicula.setText(titulocaricatura);
        }





        btplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducirpelicula.start();;
            }
        });

        btpausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducirpelicula.pause();;
            }
        });
        btregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "usted a regresado al menu ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), menucategorias.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void TomarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 1);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imafotodeperfil.setImageBitmap(imgBitmap);
        }

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
                //Toast.makeText(getApplicationContext(), "Contenido Leido Con Exito", Toast.LENGTH_SHORT).show();

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

    //Leer las generos
    private void LeerGenero() {
        String textoffilegenero = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(guardargenero)));
            textoffilegenero = bufferedReader.readLine();
            bufferedReader.close();
            if (textoffilegenero != null) {
                textgenero.setText(textoffilegenero);
                //Toast.makeText(getApplicationContext(), "Contenido Leido Con Exito", Toast.LENGTH_SHORT).show();

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
    private void Reproducircaricatura(){
        Uri myuri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bobsponjaalrescate);
        reproducirpelicula =(VideoView) findViewById(R.id.videoverpelicula);
        reproducirpelicula.setVideoURI(myuri);
        reproducirpelicula.setMediaController(new MediaController(this));
        reproducirpelicula.requestFocus();

    }
    private void ReproducirTerror(){
        Uri myuri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.anabelle3vieneacasa);
        reproducirpelicula =(VideoView) findViewById(R.id.videoverpelicula);
        reproducirpelicula.setVideoURI(myuri);
        reproducirpelicula.setMediaController(new MediaController(this));
        reproducirpelicula.requestFocus();

    }
    private void ReproducirComedia(){
        Uri myuri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.nomanchesfrida2);
        reproducirpelicula =(VideoView) findViewById(R.id.videoverpelicula);
        reproducirpelicula.setVideoURI(myuri);
        reproducirpelicula.setMediaController(new MediaController(this));
        reproducirpelicula.requestFocus();
    }



}