package com.example.sonidosdepeliculas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }//FIN ONCREATE


    public void clickbtnpeliculas (View view){

        Intent reserva=new Intent(getApplicationContext(),ListapeliculasActivity.class);
        startActivity(reserva);

    }


}//FIN MAINACTIVITY
