package com.example.personasmaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class CrearPersonas extends AppCompatActivity {

    private EditText cedula, nombre, apellido;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_personas);

        cedula = findViewById(R.id.txtCedula);
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
    }

    public void guardar(View v){
        String nom, ced, ape, id;
        Persona p;

        ced = cedula.getText().toString();
        nom = nombre.getText().toString();
        ape = apellido.getText().toString();

        p = new Persona(ced, nom, ape, "");
        p.guardar();
        limpiar();
        Snackbar.make(v, "Persona Guardada Exitosamente!!", Snackbar.LENGTH_LONG).show();
    }

    public void limpiar(View v){
        limpiar();
    }

    public void limpiar(){
        cedula.setText("");
        nombre.setText("");
        apellido.setText("");
        cedula.requestFocus();
    }
}