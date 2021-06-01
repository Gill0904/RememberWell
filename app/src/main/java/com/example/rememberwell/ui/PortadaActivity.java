package com.example.rememberwell.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rememberwell.MainActivity;
import com.example.rememberwell.R;

public class PortadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        Animation animacion1= AnimationUtils.loadAnimation(this,R.anim.right_in);
        Animation animacion2= AnimationUtils.loadAnimation(this,R.anim.down_in);
        Animation animacion3= AnimationUtils.loadAnimation(this,R.anim.left_in);
        Animation animacion4= AnimationUtils.loadAnimation(this,R.anim.up_in);

        ImageView imageView= findViewById(R.id.imageDiario);
        imageView.setAnimation(animacion1);
        View separador= findViewById(R.id.separador);
        separador.setAnimation(animacion2);
        TextView rwShort= findViewById(R.id.textRWShort);
        rwShort.setAnimation(animacion3);
        TextView RW= findViewById(R.id.textRW);
        RW.setAnimation(animacion4);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PortadaActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}