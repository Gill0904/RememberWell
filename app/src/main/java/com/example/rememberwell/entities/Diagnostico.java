package com.example.rememberwell.entities;

public class Diagnostico {

    private int id_diagnostico;
    private int id_usuario;
    private int id_entradaUsuario;
    private String horaDiagnostico;
    private String fechaDiagnostico;
    private String mensajeDiagnostico;

    public Diagnostico(int id_diagnostico, int id_usuario, int id_entradaUsuario, String horaDiagnostico, String fechaDiagnostico, String mensajeDiagnostico) {
        this.id_diagnostico = id_diagnostico;
        this.id_usuario = id_usuario;
        this.id_entradaUsuario = id_entradaUsuario;
        this.horaDiagnostico = horaDiagnostico;
        this.fechaDiagnostico = fechaDiagnostico;
        this.mensajeDiagnostico = mensajeDiagnostico;
    }

    public int getId_diagnostico() {
        return id_diagnostico;
    }

    public void setId_diagnostico(int id_diagnostico) {
        this.id_diagnostico = id_diagnostico;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_entradaUsuario() {
        return id_entradaUsuario;
    }

    public void setId_entradaUsuario(int id_entradaUsuario) {
        this.id_entradaUsuario = id_entradaUsuario;
    }

    public String getHoraDiagnostico() {
        return horaDiagnostico;
    }

    public void setHoraDiagnostico(String horaDiagnostico) {
        this.horaDiagnostico = horaDiagnostico;
    }

    public String getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(String fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public String getMensajeDiagnostico() {
        return mensajeDiagnostico;
    }

    public void setMensajeDiagnostico(String mensajeDiagnostico) {
        this.mensajeDiagnostico = mensajeDiagnostico;
    }
}
