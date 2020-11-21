package com.example.personasmaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallePersona extends AppCompatActivity {

    private ImageView foto;
    private TextView cedula, nombre, apellido;
    Bundle bundle;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);

        String  ced, nom, ape;

        foto = findViewById(R.id.imgFotoDetalle);
        cedula = findViewById(R.id.lblDetalleCedula);
        nombre = findViewById(R.id.lblDetalleNombre);
        apellido = findViewById(R.id.lblDetalleApellido);

        //Bundle es un encapsulamiento, como un zip
        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        ced = bundle.getString("cedula");
        nom = bundle.getString("nombre");
        ape = bundle.getString("apellido");

        cedula.setText(ced);
        nombre.setText(nom);
        apellido.setText(ape);


    }
}