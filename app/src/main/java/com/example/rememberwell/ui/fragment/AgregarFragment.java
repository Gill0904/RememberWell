package com.example.rememberwell.ui.fragment;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.rememberwell.ConexionSQLiteHelper;
import com.example.rememberwell.MainActivity;
import com.example.rememberwell.R;
import com.example.rememberwell.Utilidades;
import com.example.rememberwell.ui.LoginActivity;
import com.example.rememberwell.ui.NuevoUsuarioActivity;
import com.example.rememberwell.ui.interfaces.ActualizarMain;

import org.jetbrains.annotations.NotNull;


public class AgregarFragment extends DialogFragment  {


    EditText campoTitulo ;
    EditText campoContenido ;
    /**
     * The system calls this to get the DialogFragment's layout, regardless
     * of whether it's being displayed as a dialog or an embedded fragment.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment

        View view= inflater.inflate(R.layout.agregar_entrada, container, false);

        campoTitulo =(EditText) view.findViewById(R.id.txt_addTitulo);
        campoContenido =(EditText) view.findViewById(R.id.txt_addContenido);

        Toolbar toolbar= (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Crear nueva entrada");

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.save){
            if((campoTitulo.getText().toString().equals("")||campoContenido.getText().toString().equals(""))){
                Toast.makeText(getContext(),"Verifique sus campos",Toast.LENGTH_SHORT).show();
            }else{
                String fecha=Utilidades.obtenerFechaActual("America/Mexico_City");
                String hora=Utilidades.obtenerHoraActual("America/Mexico_City");
                ConexionSQLiteHelper conn= new ConexionSQLiteHelper(getContext(),"bd_Diario",null,1);
                SQLiteDatabase db = conn.getWritableDatabase();
                ContentValues values= new ContentValues();
                values.put(Utilidades.CAMPO_ID_USUARIO,LoginActivity.idUser);
                values.put(Utilidades.CAMPO_TITULO,campoTitulo.getText().toString());
                values.put(Utilidades.CAMPO_HORAENTRADA,hora);
                values.put(Utilidades.CAMPO_FECHAENTRADA,fecha);
                values.put(Utilidades.CAMPO_MENSAJEENTRANTE,campoContenido.getText().toString());
                Long idResultante=db.insert(Utilidades.TABLA_ENTRADAUSUARIO,Utilidades.CAMPO_ID_ENTRADAUSUARIO,values);
                conn.close();
                if (idResultante==null){
                    Toast.makeText(getContext(),"Error al crear el usuario",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"Registro guardado",Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dismiss();
                        }
                    }, 500);
                }
            }
            return true;
        }else if(id==android.R.id.home){
            dismiss();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * The system calls this only when creating the layout in a dialog.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
    }
}