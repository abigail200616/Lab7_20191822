package com.example.lab7_20191822;

import android.content.Intent;
import android.os.Bundle;
import com.example.lab7_20191822.databinding.ActivityClienteBinding;
import com.example.lab7_20191822.databinding.ActivityMainBinding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ClienteActivity extends AppCompatActivity {

    ActivityClienteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cerrar.setOnClickListener(view -> {
            cerrarSesion();
        });
    }

    public void cerrarSesion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Estás seguro de que deseas cerrar sesión?")
                .setTitle("Aviso")
                .setPositiveButton("Cerrar Sesión", (dialog, which) -> {
                    Intent intent1 = new Intent(this, MainActivity.class);
                    startActivity(intent1);
                })
                .setNegativeButton("Cancelar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
