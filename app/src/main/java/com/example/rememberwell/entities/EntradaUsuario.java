package com.example.rememberwell.entities;

public class EntradaUsuario {

    private int id_entradaUsuario;
    private int id_usuario;
    private String horaEntrada;
    private String fechaEntrada;
    private String mesajeEntrante;

    public EntradaUsuario(int id_entradaUsuario, int id_usuario, String horaEntrada, String fechaEntrada, String mesajeEntrante) {
        this.id_entradaUsuario = id_entradaUsuario;
        this.id_usuario = id_usuario;
        this.horaEntrada = horaEntrada;
        this.fechaEntrada = fechaEntrada;
        this.mesajeEntrante = mesajeEntrante;
    }

    public int getId_entradaUsuario() {
        return id_entradaUsuario;
    }

    public void setId_entradaUsuario(int id_entradaUsuario) {
        this.id_entradaUsuario = id_entradaUsuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getMesajeEntrante() {
        return mesajeEntrante;
    }

    public void setMesajeEntrante(String mesajeEntrante) {
        this.mesajeEntrante = mesajeEntrante;
    }
}
