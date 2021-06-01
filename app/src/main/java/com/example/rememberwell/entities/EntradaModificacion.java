package com.example.rememberwell.entities;

public class EntradaModificacion {

    private int id_entradaModificacion;
    private int id_entradaUsuario;
    private String horaModificacion;
    private String fechaModificacion;
    private String mesajeModificado;

    public EntradaModificacion(int id_entradaModificacion, int id_entradaUsuario, String horaModificacion, String fechaModificacion, String mesajeModificado) {
        this.id_entradaModificacion = id_entradaModificacion;
        this.id_entradaUsuario = id_entradaUsuario;
        this.horaModificacion = horaModificacion;
        this.fechaModificacion = fechaModificacion;
        this.mesajeModificado = mesajeModificado;
    }

    public int getId_entradaModificacion() {
        return id_entradaModificacion;
    }

    public void setId_entradaModificacion(int id_entradaModificacion) {
        this.id_entradaModificacion = id_entradaModificacion;
    }

    public int getId_entradaUsuario() {
        return id_entradaUsuario;
    }

    public void setId_entradaUsuario(int id_entradaUsuario) {
        this.id_entradaUsuario = id_entradaUsuario;
    }

    public String getHoraModificacion() {
        return horaModificacion;
    }

    public void setHoraModificacion(String horaModificacion) {
        this.horaModificacion = horaModificacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getMesajeModificado() {
        return mesajeModificado;
    }

    public void setMesajeModificado(String mesajeModificado) {
        this.mesajeModificado = mesajeModificado;
    }
}
