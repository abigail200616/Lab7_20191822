package com.example.lab7_20191822;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;

import org.checkerframework.checker.units.qual.C;

import java.util.List;

public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.ViewHolder>{
    private List<Citas> citaLista;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_gestor, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Citas cita = citaLista.get(position);
        holder.cita = cita;

        TextView fechaTextView = holder.itemView.findViewById(R.id.hora);
        TextView servicioTextView = holder.itemView.findViewById(R.id.servicio);
        TextView emailTextView = holder.itemView.findViewById(R.id.correo);

        fechaTextView.setText(cita.getHora());
        servicioTextView.setText(cita.getServicio());
        emailTextView.setText(cita.getEmail());
    }

    @Override
    public int getItemCount() {
        return citaLista.size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Citas cita;
        TextView fechaTextView;
        TextView servicioTextView;
        TextView emailTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fechaTextView = itemView.findViewById(R.id.hora);
            servicioTextView = itemView.findViewById(R.id.servicio);
            emailTextView =itemView.findViewById(R.id.correo);
        }
    }
    public List<Citas> getCitaLista(){
        return citaLista;
    }

    public void setCitaLista (List<Citas> citaLista){
        this.citaLista = citaLista;
        notifyDataSetChanged();
    }
}
