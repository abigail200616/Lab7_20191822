package com.example.lab7_20191822;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab7_20191822.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    FirebaseFirestore db;
    FirebaseAuth auth;
    Usuario usuario;
    Boolean registrado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        binding.button.setOnClickListener(view -> {
            String usuarioIngresado = ((TextInputEditText) binding.inputEmail.getEditText()).getText().toString();
            String contrasenaIngresada = ((TextInputEditText) binding.inputPasswd.getEditText()).getText().toString();
            db.collection("credenciales")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot u : task.getResult()){
                                Usuario user = u.toObject(Usuario.class);
                                if(user.getEmail().equals(usuarioIngresado) && user.getPassword().equals(contrasenaIngresada)){
                                    Intent intentGestor = new Intent(this, GestorActivity.class);
                                    startActivity(intentGestor);
                                    registrado = true;
                                    break;
                                }
                            }
                            if (!registrado) {
                                Usuario nuevo = new Usuario();
                                nuevo.setPassword(contrasenaIngresada);
                                nuevo.setEmail(usuarioIngresado);
                                nuevo.setRol("cliente");

                                db.collection("credenciales2")
                                        .add(nuevo)
                                        .addOnSuccessListener(documentReference -> {
                                            Intent intentCliente = new Intent(this, ClienteActivity.class);
                                            startActivity(intentCliente);
                                            Log.d("MainActivity", "Usuario agregado");
                                        })
                                        .addOnFailureListener(e -> {
                                            Log.w("MainActivity", "Error al agregar usuario");
                                        });
                            }
                        } else {
                            Log.w("MainActivity", "Inicio de sesión fallido", task.getException());
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("Correo o contraseña incorrecta. Vuelva a ingresar sus datos.")
                                    .setTitle("Aviso")
                                    .setPositiveButton("Aceptar", (dialog, which) -> {
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });

        });
    }

    private void redirigirSegunRol(String correo) {
        db.collection("credenciales")
                .whereEqualTo("email", correo)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String rol = document.getString("rol");

                            if (rol != null) {
                                switch (rol) {
                                    case "gestor":
                                        Intent intentGestor = new Intent(this, GestorActivity.class);
                                        startActivity(intentGestor);
                                        break;
                                    case "cliente":
                                        Intent intentCliente = new Intent(this, ClienteActivity.class);
                                        startActivity(intentCliente);
                                        break;
                                }
                                finish();
                            }
                        }
                    } else {
                        Log.w("MainActivity", "Error al obtener el rol", task.getException());
                    }
                });
    }
}