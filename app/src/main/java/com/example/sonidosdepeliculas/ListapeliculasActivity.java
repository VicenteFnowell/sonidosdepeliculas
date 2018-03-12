package com.example.sonidosdepeliculas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ListapeliculasActivity extends AppCompatActivity {

    static final String EXTRA_PELICULAS = "PELICULA";

    ListView lvPeliculas;
    ArrayList<CPelicula> lista_peliculas = new ArrayList<CPelicula>();
    DatabaseReference dbRef;
    ValueEventListener valueEventListener;
    StorageReference storageRf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listapeliculas);

        cargarDatosFirebase();

        lvPeliculas = (ListView)findViewById(R.id.lv_listapelis);
        AdaptadorPeliculas adaptadorpelis = new AdaptadorPeliculas(this,lista_peliculas);
        lvPeliculas.setAdapter(adaptadorpelis);



    }//FIN ONCREATE

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

    private void  cargarDatosFirebase(){
        dbRef = FirebaseDatabase.getInstance().getReference().child("peliculas");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista_peliculas.clear();
                for (DataSnapshot peliculasDataSnapshot: dataSnapshot.getChildren()){
                    cargarListView(peliculasDataSnapshot);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ListapeliculasActivity","DATABASE ERROR");

            }
        };
        dbRef.addValueEventListener(valueEventListener);

    }


    }







