package com.example.lab7_20191822;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab7_20191822.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    FirebaseFirestore db;
    //FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //auth = FirebaseAuth.getInstance();
        //db = FirebaseFirestore.getInstance();

       /* binding.button.setOnClickListener(view -> {
            String usuarioIngresado = ((TextInputEditText) binding.inputEmail.getEditText()).getText().toString();
            String contrasenaIngresada = ((TextInputEditText) binding.inputPasswd.getEditText()).getText().toString();
            validar(usuarioIngresado, contrasenaIngresada);
        });*/
    }
    /* private void validar(String usuario, String password) {
        auth.signInWithEmailAndPassword(usuario, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("MainActivity", "Inicio de sesión exitoso");
                        if (auth.getCurrentUser() != null) {
                            redirigirSegunRol(auth.getCurrentUser().getEmail());
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
                                        Intent intentGestor = new Intent(MainActivity.this, GestorActivity.class);
                                        intentGestor.putExtra("correoAlumno", correo);
                                        startActivity(intentGestor);
                                        break;
                                    case "cliente":
                                        Intent intentCliente = new Intent(MainActivity.this, ClienteActivity.class);
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
    }*/
}