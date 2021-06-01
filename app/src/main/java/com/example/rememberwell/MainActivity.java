 package com.example.rememberwell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.rememberwell.alarm.AlarmReceiver;
import com.example.rememberwell.alarm.Utils;
import com.example.rememberwell.ui.LoginActivity;
import com.example.rememberwell.ui.NuevoUsuarioActivity;
import com.example.rememberwell.ui.adapter.DynamicRVAdapter;
import com.example.rememberwell.ui.adapter.StaticRvAdapter;
import com.example.rememberwell.ui.fragment.AgregarFragment;
import com.example.rememberwell.ui.fragment.registroFragment;
import com.example.rememberwell.ui.interfaces.ActualizarMain;
import com.example.rememberwell.ui.interfaces.LoadMore;
import com.example.rememberwell.ui.model.DynamicRVModel;
import com.example.rememberwell.ui.model.StaticRvModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static android.content.ContentValues.TAG;

 public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;
    public static String id_item;


    static List<DynamicRVModel> items = new ArrayList();
    DynamicRVAdapter dynamicRVAdapter;
    boolean isLargeLayout;
    FloatingActionButton fba,fba2;
    TextView name;
    EditText busqueda;
    ImageView btnBuscar;



     @SuppressLint("SetTextI18n")
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name= (TextView) findViewById(R.id.txtName);
        name.setText(""+LoginActivity.idName);
        busqueda= (EditText) findViewById(R.id.txt_buscar);
        btnBuscar=findViewById(R.id.btn_buscar);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (busqueda.getText().toString().equals("")){
                    ingresarItems();
                }else{
                    ingresarBuscados(""+busqueda.getText());
                }
            }
        });

        isLargeLayout = getResources().getBoolean(R.bool.large_layout);

        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.alarm,"Alarma"));
        item.add(new StaticRvModel(R.drawable.exit,"Salir"));

        recyclerView = findViewById(R.id.rv);
        staticRvAdapter = new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdapter);

        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_Diario",null,1);

        consultarRegistros(LoginActivity.idUser);

         RecyclerView drv = findViewById(R.id.rv_2);
         drv.setItemAnimator(null);
         drv.setLayoutManager(new LinearLayoutManager(this));
         dynamicRVAdapter = new DynamicRVAdapter(drv,this,items);
         drv.setAdapter(dynamicRVAdapter);
         if(items.size()<=0){
             Toast.makeText(getApplicationContext(),"Aún no existen registros",Toast.LENGTH_SHORT).show();
         }

         dynamicRVAdapter.setLoadMore(new LoadMore() {
             @Override
             public void onLoadMore() {

             }
         });
        fba = findViewById(R.id.btn_agregar);
        fba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                showAgregar();
            } catch (Exception e) {
                Log.e(TAG, "onCreateView", e);
                throw e;
            }
            }
        });
         fba2 = findViewById(R.id.btn_actualizar);
         fba2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ingresarItems();
                 Toast.makeText(getApplicationContext(),"Registro actualizado",Toast.LENGTH_SHORT).show();
             }
         });

        drv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fba.show();
                    fba2.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fba.isShown()) {
                    fba.hide();
                    fba2.hide();
                }
            }
        });

    }

     public void ingresarItems() {
         items.clear();
         consultarRegistros(LoginActivity.idUser);
         RecyclerView drv = findViewById(R.id.rv_2);
         drv.removeAllViewsInLayout();
         drv.setItemAnimator(null);
         drv.setLayoutManager(new LinearLayoutManager(this));
         dynamicRVAdapter = new DynamicRVAdapter(drv,this,items);
         drv.setAdapter(dynamicRVAdapter);
         if(items.size()<=0){
             Toast.makeText(getApplicationContext(),"Aún no existen registros",Toast.LENGTH_SHORT).show();
         }
         dynamicRVAdapter.setLoadMore(new LoadMore() {
             @Override
             public void onLoadMore() {

             }
         });
     }
     public void ingresarBuscados(String titleBuscar){
         items.clear();
         buscarRegistros(LoginActivity.idUser,titleBuscar);
         RecyclerView drv = findViewById(R.id.rv_2);
         drv.removeAllViewsInLayout();
         drv.setItemAnimator(null);
         drv.setLayoutManager(new LinearLayoutManager(this));
         dynamicRVAdapter = new DynamicRVAdapter(drv,this,items);
         drv.setAdapter(dynamicRVAdapter);
         if(items.size()<=0){
             Toast.makeText(getApplicationContext(),"Aún no existen registros",Toast.LENGTH_SHORT).show();
         }
     }

     private void buscarRegistros(String idUser,String titulo) {
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
         String selection = Utilidades.CAMPO_ID_USUARIO + " LIKE ? and "+Utilidades.CAMPO_TITULO+ " LIKE ?";
         String[] selectionArgs = { ""+idUser,""+titulo };

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
             String id2=cursor.getString(0);
             String titulo2=cursor.getString(1);
             String hora2=cursor.getString(2);
             String fecha2=cursor.getString(3);
             String mensaje2=cursor.getString(4);
             items.add(new DynamicRVModel(titulo2,mensaje2,fecha2,hora2,id2));
         }
         cursor.close();

     }

     private void consultarRegistros(String idUser) {
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
         String selection = Utilidades.CAMPO_ID_USUARIO + " = ?";
         String[] selectionArgs = { ""+idUser };

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

     public void showAgregar() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AgregarFragment newFragment = new AgregarFragment();
            // The device is smaller, so show the fragment fullscreen
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            // For a little polish, specify a transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity
            transaction.add(android.R.id.content, newFragment)
                    .addToBackStack(null).commit();
    }

     @Override
     protected void onResume() {
         super.onResume();
         ingresarItems();
     }
 }