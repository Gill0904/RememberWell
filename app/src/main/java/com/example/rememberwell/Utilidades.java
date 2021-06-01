package com.example.rememberwell;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utilidades {

    //Tabla Usuarios
    public static final String TABLA_USUARIO="Usuario";
    public static final String CAMPO_ID_USUARIO="id_usuario";
    public static final String CAMPO_NOMBRE_USUARIO="nombre_usuario";
    public static final String CAMPO_CONTRASENIA="contrasenia";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIO+"( "+
            CAMPO_ID_USUARIO+" INTEGER CONSTRAINT pk_usuario PRIMARY KEY AUTOINCREMENT, \n" +
            CAMPO_NOMBRE_USUARIO+" VARCHAR(25) NOT NULL, \n" +
            CAMPO_CONTRASENIA+" VARCHAR(25) NOT NULL \n" +
            ");";

    //Tabla Entrada Usuario
    public static final String TABLA_ENTRADAUSUARIO="EntradaUsuario";
    public static final String CAMPO_ID_ENTRADAUSUARIO="id_entradaUsuario";
    public static final String CAMPO_TITULO="titulo";
    public static final String CAMPO_HORAENTRADA="horaEntrada";
    public static final String CAMPO_FECHAENTRADA="fechaEntrada";
    public static final String CAMPO_MENSAJEENTRANTE="mesajeEntrante";

    public static final String CREAR_TABLA_ENTRADAUSUARIO="CREATE TABLE "+TABLA_ENTRADAUSUARIO+"("+
            CAMPO_ID_ENTRADAUSUARIO+" INTEGER CONSTRAINT pk_entradaUsuario PRIMARY KEY AUTOINCREMENT,\n"+
            CAMPO_ID_USUARIO+" INTEGER REFERENCES "+TABLA_USUARIO+"("+CAMPO_ID_USUARIO+"),\n"+
            CAMPO_TITULO+" VARCHAR(25) NOT NULL,\n"+
            CAMPO_HORAENTRADA+" VARCHAR(7) NOT NULL,\n"+
            CAMPO_FECHAENTRADA+" VARCHAR(10) NOT NULL,\n"+
            CAMPO_MENSAJEENTRANTE+" VARCHAR(500) NOT NULL\n "+
    ");";

    //Fecha y hora
    public static String obtenerHoraActual(String zonaHoraria) {
        String formato = "HH:mm";
        return obtenerFechaConFormato(formato, zonaHoraria);
    }

    public static String obtenerFechaActual(String zonaHoraria) {
        String formato = "yyyy-MM-dd";
        return obtenerFechaConFormato(formato, zonaHoraria);
    }

    @SuppressLint("SimpleDateFormat")
    public static String obtenerFechaConFormato(String formato, String zonaHoraria) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(formato);
        sdf.setTimeZone(TimeZone.getTimeZone(zonaHoraria));
        return sdf.format(date);
    }
}
