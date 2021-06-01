package com.example.rememberwell.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rememberwell.ConexionSQLiteHelper;
import com.example.rememberwell.MainActivity;
import com.example.rememberwell.R;
import com.example.rememberwell.Utilidades;
import com.example.rememberwell.ui.model.DynamicRVModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ModificarEntradaActivity extends AppCompatActivity {
    EditText titulo,contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_entrada);
        iniciarMain();
    }

    private void iniciarMain() {
        buscarEntrada();
        DynamicRVModel item = items.get(0);
        titulo= (EditText) findViewById(R.id.txt_modTitulo);
        contenido= (EditText) findViewById(R.id.txt_modContenido);
        titulo.setText(item.getTitulo());
        contenido.setText(item.getContenido());

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbarMod);
        toolbar.setTitle("Actualizar entrada");

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.save){
            actualizarRegistros(MainActivity.id_item);
            super.recreate();
            finish();
            return true;
        }else if(id==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    List<DynamicRVModel> items = new ArrayList();
    private void buscarEntrada() {
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_Diario",null,1);
        SQLiteDatabase db=conn.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                Utilidades.CAMPO_ID_ENTRADAUSUARIO,
                Utilidades.CAMPO_TITULO,
                Utilidades.CAMPO_HORAENTRADA,
                Utilidades.CAMPO_FECHAENTRADA,
                Utilidades.CAMPO_MENSAJEENTRANTE

        };

        // Filter results WHERE "title" = 'My Title'
        String selection = Utilidades.CAMPO_ID_ENTRADAUSUARIO + " = ?";
        String[] selectionArgs = { ""+ MainActivity.id_item };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Utilidades.CAMPO_FECHAENTRADA + " ASC";

        Cursor cursor = db.query(
                Utilidades.TABLA_ENTRADAUSUARIO,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        while(cursor.moveToNext()) {
            String id=cursor.getString(0);
            String titulo=cursor.getString(1);
            String hora=cursor.getString(2);
            String fecha=cursor.getString(3);
            String mensaje=cursor.getString(4);
            items.add(new DynamicRVModel(titulo,mensaje,fecha,hora,id));
        }
        cursor.close();
    }

    private void actualizarRegistros(String idUser) {
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_Diario",null,1);
        String title= titulo.getText().toString();
        String content =contenido.getText().toString();
        Boolean isUpdated= conn.update(MainActivity.id_item,title,content);
        if (isUpdated){
            Toast.makeText(getApplicationContext(),"Actualización exitosa",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Actualización fallida",Toast.LENGTH_SHORT).show();
        }
    }


}