package com.example.personasmaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CrearPersonas extends AppCompatActivity {

    private EditText cedula, nombre, apellido;
    private ImageView foto;
    private InputMethodManager im;
    private Uri uri;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_personas);

        cedula = findViewById(R.id.txtCedula);
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        foto = findViewById(R.id.imgFotoSelected);
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void guardar(View v){
        String nom, ced, ape, id;
        Persona p;

        ced = cedula.getText().toString();
        nom = nombre.getText().toString();
        ape = apellido.getText().toString();
        id = Datos.getId();
        im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        p = new Persona(ced, nom, ape, id);
        p.guardar();
        im.hideSoftInputFromWindow(cedula.getWindowToken(), 0);
        limpiar();
        subirFoto(id);
        Snackbar.make(v, "Persona Guardada Exitosamente!!", Snackbar.LENGTH_LONG).show();

    }

    public void subirFoto(String id){
        StorageReference child = storageReference.child(id);
        UploadTask uploadTask = child.putFile(uri);
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

    public void onBackPressed(){
        finish();
        Intent i = new Intent(CrearPersonas.this, MainActivity.class);
        startActivity(i);
    }

    public void seleccionarFoto(View v){
        Intent in = new Intent();
        in.setType("image/*");
        in.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(in,
                "Seleccione la foto de la persona"), 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            uri = data.getData();
            if (uri != null){
                foto.setImageURI(uri);
            }
        }
    }

}