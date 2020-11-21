package com.example.personasmaterial;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Datos {
    
    private static String db = "Personas";
    private static DatabaseReference databaseReference =
            FirebaseDatabase.getInstance().getReference();
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
}
