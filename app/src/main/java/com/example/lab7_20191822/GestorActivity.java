package com.example.lab7_20191822;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.lab7_20191822.CitasAdapter;
import com.example.lab7_20191822.Citas;
import com.example.lab7_20191822.GestorActivity;
import com.example.lab7_20191822.databinding.ActivityGestorBinding;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

public class GestorActivity extends AppCompatActivity {
    ActivityGestorBinding binding;
    FirebaseFirestore db;
    List<Citas> citasListaa;
    private CitasAdapter citasAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGestorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = FirebaseFirestore.getInstance();
        binding.cerrar.setOnClickListener(view -> {
            cerrarSesion();
        });
        db.collection("citas")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        List<Citas> citasList = new ArrayList<>();
                        for (QueryDocumentSnapshot cita : task.getResult()) {
                            Citas cit = cita.toObject(Citas.class);
                            citasList.add(cit);
                        }
                        citasListaa = citasList;
                        CitasAdapter citasAdapter = new CitasAdapter();
                        citasAdapter.setCitaLista(citasList);
                        citasAdapter.setContext(GestorActivity.this);
                        binding.reclyerCitas.setAdapter(citasAdapter);
                        binding.reclyerCitas.setLayoutManager(new LinearLayoutManager(GestorActivity.this));
                    }

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

