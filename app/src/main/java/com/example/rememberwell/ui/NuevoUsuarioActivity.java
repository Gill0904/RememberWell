package com.example.rememberwell.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rememberwell.ConexionSQLiteHelper;
import com.example.rememberwell.R;
import com.example.rememberwell.Utilidades;

public class NuevoUsuarioActivity extends AppCompatActivity {

    EditText campoNombre, campoContrasenia,campoContraseniaConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        campoNombre=(EditText) findViewById(R.id.txt_NombreNew);
        campoContrasenia=(EditText) findViewById(R.id.txt_contraseniaNew);
        campoContraseniaConf=(EditText) findViewById(R.id.txt_ConfirmarContrasenia);

        Animation animacion1= AnimationUtils.loadAnimation(this,R.anim.fade_in);

        LinearLayout layout= findViewById(R.id.layoutLogin2);
        layout.setAnimation(animacion1);

        Button btnRegistrar= findViewById(R.id.btn_registrarse);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });

        TextView button2 = findViewById(R.id.btn_IniciarSesion);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(NuevoUsuarioActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }
        });
    }

    public void registrarUsuario(){
        if((campoContrasenia.getText().toString().equals("")||campoContraseniaConf.getText().toString().equals("")
            ||campoNombre.getText().toString().equals(""))){
            Toast.makeText(this,"Verifique sus campos",Toast.LENGTH_SHORT).show();
        }else if(!(campoContrasenia.getText().toString().equals(campoContraseniaConf.getText().toString()))){
            Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
        }else{
            ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_Diario",null,1);
            SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues values= new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE_USUARIO,campoNombre.getText().toString());
            values.put(Utilidades.CAMPO_CONTRASENIA,campoContrasenia.getText().toString());
            Long idResultante=db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID_USUARIO,values);
            conn.close();
            if (idResultante==null){
                Toast.makeText(this,"Error al crear el usuario",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Usuario creado exitosamente",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(NuevoUsuarioActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }
        }
    }
}