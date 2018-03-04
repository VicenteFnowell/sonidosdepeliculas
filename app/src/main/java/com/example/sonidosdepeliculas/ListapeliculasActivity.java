package com.example.sonidosdepeliculas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListapeliculasActivity extends AppCompatActivity {

    static final String EXTRA_PELICULAS = "PELICULA";

    ListView lvPeliculas;
    ArrayList<CPelicula> lista_peliculas = new ArrayList<CPelicula>();
    //DatabaseReference dbRef;
    //ValueEventListener valueEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listapeliculas);

        cargarDatos();

        lvPeliculas = (ListView)findViewById(R.id.lv_listapelis);
        AdaptadorPeliculas adaptadorpelis = new AdaptadorPeliculas(this,lista_peliculas);
        lvPeliculas.setAdapter(adaptadorpelis);

        lvPeliculas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CPelicula c = ((CPelicula)parent.getItemAtPosition(position));

                Intent empleadolista = new Intent(getApplicationContext(), SonidospeliculaActivity.class);

                empleadolista.putExtra(EXTRA_PELICULAS,c);

                startActivity(empleadolista);
            }


        });


    }//FIN ONCREATE
/*
    private void cargarListView (DataSnapshot dataSnapshot) {

        lista_peliculas.add(dataSnapshot.getValue(CPelicula.class));
        AdaptadorPeliculas adaptadorpelis = new AdaptadorPeliculas(this, lista_peliculas);
        lvPeliculas.setAdapter(adaptadorpelis);


        lvPeliculas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CPelicula c = ((CPelicula) parent.getItemAtPosition(position));


                Intent sonidospelicula = new Intent(getApplicationContext(), SonidospeliculaActivity.class);

                sonidospelicula.putExtra(EXTRA_PELICULAS, c);

                startActivity(sonidospelicula);
            }


        });


    }
*/

        private void cargarDatos(){

        lista_peliculas.add(new CPelicula("Matrix", "120 minutos","1999","Dos Oscars","Ciencia-ficcion","matrix"));
        lista_peliculas.add(new CPelicula("Los Goonies", "141 minutos","1991","Un globo de oro","Aventuras","goonies"));
        lista_peliculas.add(new CPelicula("El último Samurái", "144 minutos","2002","Dos Oscars","Aventuras","samurai"));
        lista_peliculas.add(new CPelicula("Regreso al Futuro", "115 minutos","1985","Un Oscar","Ciencia-ficcion","futuro"));


    }


    }







