package com.example.rememberwell;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {



    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        db.execSQL(Utilidades.CREAR_TABLA_ENTRADAUSUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("drop table if EXISTS "+Utilidades.TABLA_USUARIO);
        db.execSQL("drop table if EXISTS "+Utilidades.TABLA_ENTRADAUSUARIO);
        onCreate(db);
    }

    public boolean update(String id,String titulo,String contenido){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("titulo",titulo);
        values.put("mesajeEntrante ",contenido);
        Cursor cursor = db.rawQuery("select * from EntradaUsuario where id_entradaUsuario = ? ",new String[]{id});
        if (cursor.getCount()>0){
            long result = db.update("EntradaUsuario",values,"id_entradaUsuario = ?",new String[]{id});
            if(result != -1)
                return true;
            return false;
        }
        return false;
    }
}
