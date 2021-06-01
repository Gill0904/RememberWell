package com.example.rememberwell.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
import com.example.rememberwell.MainActivity;
import com.example.rememberwell.R;
import com.example.rememberwell.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static String idUser="";
    public static String idName="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Animation animacion1= AnimationUtils.loadAnimation(this,R.anim.fade_in);

        LinearLayout layout= findViewById(R.id.layoutLogin);
        layout.setAnimation(animacion1);

        EditText nombre = (EditText) findViewById(R.id.txt_Nombre);
        EditText contrasenia = (EditText) findViewById(R.id.txt_Contrasenia);
        Button button = findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((nombre.getText().toString().equals("")||contrasenia.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "Verifique sus campos", Toast.LENGTH_SHORT).show();
                } else if(!verificarLogin(nombre.getText().toString(),contrasenia.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Las credenciales no coinciden",Toast.LENGTH_SHORT).show();
                }else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 500);
                }
            }
        });

        TextView button2 = findViewById(R.id.btn_CrearCuenta);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoginActivity.this, NuevoUsuarioActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }
        });
    }
    public boolean verificarLogin(String nombre,String contrasenia){
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_Diario",null,1);
        SQLiteDatabase db=conn.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                Utilidades.CAMPO_ID_USUARIO,
                Utilidades.CAMPO_NOMBRE_USUARIO,
                Utilidades.CAMPO_CONTRASENIA
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = Utilidades.CAMPO_NOMBRE_USUARIO + " = ? AND " +Utilidades.CAMPO_CONTRASENIA+ " = ?";
        String[] selectionArgs = { ""+nombre,""+contrasenia };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Utilidades.CAMPO_ID_USUARIO + " DESC";

        Cursor cursor = db.query(
                Utilidades.TABLA_USUARIO,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List itemIds = new ArrayList<>();
        int var=0;
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(Utilidades.CAMPO_ID_USUARIO));
            itemIds.add(itemId);
            idUser=cursor.getString(0);
            idName=cursor.getString(1);
            var++;
        }
        cursor.close();
        if(var!=0)
            return true;
        return false;
    }
}