package com.example.lab7_20191822;
import java.io.Serializable;
import java.util.List;
public class Citas implements Serializable {

    public String hora;
    public String servicio;
    public String email;

    public Citas() {
    }

    public Citas(String hora, String servicio, String email) {
        this.hora = hora;
        this.servicio = servicio;
        this.email = email;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
