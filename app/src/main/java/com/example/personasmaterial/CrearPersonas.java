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
import android.widget.Toast;

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

        im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(cedula.getWindowToken(), 0);

        if (validar()) {
            ced = cedula.getText().toString();
            nom = nombre.getText().toString();
            ape = apellido.getText().toString();
            id = Datos.getId();

            p = new Persona(ced, nom, ape, id);
            p.guardar();

            limpiar();
            subirFoto(id);
            Snackbar.make(v, R.string.success_msg, Snackbar.LENGTH_LONG).show();
            uri = null;
        }
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
        foto.setImageResource(android.R.drawable.ic_menu_gallery);
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
                getString(R.string.select_foto)), 1);
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

    public boolean validar(){
        if (cedula.getText().toString().isEmpty()){
            cedula.setError(getText(R.string.ced_empty));
            cedula.requestFocus();
            return false;
        }
        if (nombre.getText().toString().isEmpty()){
            nombre.setError(getText(R.string.nom_empty));
            nombre.requestFocus();
            return false;
        }
        if (apellido.getText().toString().isEmpty()){
            apellido.setError(getText(R.string.ape_empty));
            apellido.requestFocus();
            return false;
        }
        if (uri == null){
            Snackbar.make((View) cedula, getText(R.string.select_foto), Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}