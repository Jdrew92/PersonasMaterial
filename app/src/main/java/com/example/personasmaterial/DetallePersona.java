package com.example.personasmaterial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetallePersona extends AppCompatActivity {

    private ImageView foto;
    private TextView cedula, nombre, apellido;
    private Bundle bundle;
    private Intent intent;
    private StorageReference storageReference;
    private Persona p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);

        String  ced, nom, ape, id;

        foto = findViewById(R.id.imgFotoDetalle);
        cedula = findViewById(R.id.lblDetalleCedula);
        nombre = findViewById(R.id.lblDetalleNombre);
        apellido = findViewById(R.id.lblDetalleApellido);

        storageReference = FirebaseStorage.getInstance().getReference();

        //Bundle es un encapsulamiento, como un zip
        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        ced = bundle.getString("cedula");
        nom = bundle.getString("nombre");
        ape = bundle.getString("apellido");
        id = bundle.getString("id");
        storageReference.child(id).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });

        p = new Persona(ced, nom, ape, id);
        cedula.setText(ced);
        nombre.setText(nom);
        apellido.setText(ape);

    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_eliminar);
        builder.setMessage(R.string.msg_eliminar);
        positivo = getString(R.string.msg_si);
        negativo = getString(R.string.msg_no);
        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                p.eliminar();
                onBackPressed();
            }
        });
        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetallePersona.this, MainActivity.class);
        startActivity(intent);
    }
}