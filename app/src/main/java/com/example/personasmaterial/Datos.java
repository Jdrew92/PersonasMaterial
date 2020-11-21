package com.example.personasmaterial;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Datos {
    
    private static String db = "Personas";
    private static DatabaseReference databaseReference =
            FirebaseDatabase.getInstance().getReference();
    private static StorageReference storageReference =
            FirebaseStorage.getInstance().getReference();
    public static ArrayList<Persona> personas = new ArrayList();
    
    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void guardar(Persona p){
        databaseReference.child(db).child(p.getId()).setValue(p);
        //personas.add(p);
    }

    public static void setPersonas(ArrayList<Persona> personas){
        personas = personas;
    }

    public static void eliminar(Persona p){
        databaseReference.child(db).child(p.getId()).removeValue();
        storageReference.child(db).child(p.getId()).delete();
    }
}
