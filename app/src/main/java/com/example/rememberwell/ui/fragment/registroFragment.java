package com.example.rememberwell.ui.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azure.core.annotation.Post;
import com.example.rememberwell.ConexionSQLiteHelper;
import com.example.rememberwell.MainActivity;
import com.example.rememberwell.R;
import com.example.rememberwell.Utilidades;
import com.example.rememberwell.azure.ApiUtils;
import com.example.rememberwell.ui.LoginActivity;
import com.example.rememberwell.ui.ModificarEntradaActivity;
import com.example.rememberwell.ui.interfaces.SentimientoApi;
import com.example.rememberwell.ui.model.Document;
import com.example.rememberwell.ui.model.Document__1;
import com.example.rememberwell.ui.model.DynamicRVModel;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class registroFragment extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private SentimientoApi mAPIService;
    TextToSpeech tts;
    public static String texto ="Un buen dia";
    String sentimiento ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrada_item_layout);
        tts= new TextToSpeech(this,this,"com.google.android.tts");

        buscarEntrada();
        DynamicRVModel item = items.get(0);
        TextView fecha= (TextView) findViewById(R.id.fecha_item);
        TextView titulo= (TextView) findViewById(R.id.txt_TituloView);
        TextView contenido= (TextView) findViewById(R.id.txt_ContenidoView);
        String fechaHora=item.getFecha()+"   |   "+item.getHora();
        fecha.setText(fechaHora);
        titulo.setText(item.getTitulo());
        contenido.setText(item.getContenido());
        texto=item.getContenido();

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbarRegistro);

        setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) this).getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        }

        FloatingActionMenu fabMenu = (FloatingActionMenu) findViewById(R.id.fabMenu);
        FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
        FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fabDelete);
        FloatingActionButton fabAnalizar =(FloatingActionButton) findViewById(R.id.fabAnalizar);

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), ModificarEntradaActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }
        });
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        eliminarRegistro();
                    }
                }, 500);
            }
        });
        mAPIService= ApiUtils.getAPIService();

        fabAnalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> documents = new HashMap<String,Object>();

                Map<String,String> documentsContent = new HashMap<String,String>();
                documentsContent.put("id","1");
                documentsContent.put("language","es");
                documentsContent.put("text",""+texto+"");
                List<Map<String,String>> datos=new ArrayList<>();
                datos.add(documentsContent);

                documents.put("documents",datos);

                HashMap<String,Object> body = new HashMap<String, Object>();
                body.put("documents",datos);
                sendPost(body);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (sentimiento!=null)
                            speak();
                    }
                }, 1000);
            }
        });

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
        String[] selectionArgs = { ""+MainActivity.id_item };

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

    private void eliminarRegistro() {
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_Diario",null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        // Define 'where' part of query.
        String selection = Utilidades.CAMPO_ID_ENTRADAUSUARIO + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {MainActivity.id_item};
        // Issue SQL statement.
        int deletedRows = db.delete(Utilidades.TABLA_ENTRADAUSUARIO, selection, selectionArgs);
        if (deletedRows>0){
            Toast.makeText(getApplicationContext(),"Eliminación exitosa",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"Error al eliminar",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onInit(int i) {
        if(i == TextToSpeech.SUCCESS){
            Set<String> a=new HashSet<>();
            a.add("MALE");//here you can give male if you want to select male voice.
            Voice v=new Voice("es-ES-Wavenet-B",new Locale("es","ES"),400,200,true,a);
            int result = tts.setVoice(v);
            //Locale locSpanish = new Locale("es", "US");
            //int result = tts.setLanguage(locSpanish);

            tts.setSpeechRate(0.9f);
            tts.setPitch(1f);
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","Lenguaje no soportado");
            }
        }else{
            Log.e("TextToSpeech","Inicialización fallida");
        }
    }

    private void speak() {
        String message="";
        String messageNeu="Parece que fue un día bastante equilibrado, solo Escribe en tu corazón que cada " +
                "día es el mejor día del año como lo dijó Ralph Waldo Emerson";
        String messagePos="Hoy parece haber sido un buen día, como dijó Steve Jobs Las cosas no tienen " +
                "por qué cambiar el mundo para ser importantes, ¡sigue así!";
        String messageNeg = "No diré nada de lo que no estoy totalmente enterada, pero, como dijó Stephen King. " +
                "Puedes hacerlo, deberías hacerlo, y si eres lo suficientemente valiente como para empezar, lo harás";

            if (sentimiento.equals("positive")){
                message=messagePos;
            }else if (sentimiento.equals("neutral")){
                message=messageNeu;
            }else{
                message=messageNeg;
            }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            tts.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
        }
    }
    private void sendPost(HashMap body) {
        mAPIService.savePost(body).enqueue(new Callback<Document>() {
            @Override
            public void onResponse(Call<Document> call, Response<Document> response) {
                try {
                    if(response.isSuccessful()) {
                        Document document= response.body();
                        System.out.println(document.getDocuments());
                        sentimiento=(document.getDocuments()).get(document.getDocuments().size()-1).getSentiment().toString();
                        System.out.println("Error: "+document.getErrors());
                    }
                }catch (Exception e){
                    Toast.makeText(registroFragment.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Document> call, Throwable t) {
                Toast.makeText(registroFragment.this, "Error de conexión.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
