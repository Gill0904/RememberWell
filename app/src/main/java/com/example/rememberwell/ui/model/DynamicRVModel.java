package com.example.rememberwell.ui.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rememberwell.ui.fragment.registroFragment;

public class DynamicRVModel extends AppCompatActivity {

    String titulo, contenido,fecha,hora,id;

    public DynamicRVModel(String titulo) {
        this.titulo = titulo;
    }

    public DynamicRVModel(String titulo, String contenido, String fecha, String hora, String id) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.hora = hora;
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
