package com.example.sonidosdepeliculas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SonidospeliculaActivity extends AppCompatActivity {

    ImageView ivPosterSonidos;
    TextView tvTitulo,tvAno,tvDuracion;
    ListView lvSonidos;
    ArrayList<CSonidos> lista_sonidos = new ArrayList<CSonidos>();
    DatabaseReference dbRef;
    ValueEventListener valueEventListener;
    StorageReference storageRf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonidospelicula);

        lvSonidos = (ListView)findViewById(R.id.lvsonidospelis);
        ivPosterSonidos = (ImageView)findViewById(R.id.imgsonidospeli);
        tvTitulo = (TextView)findViewById(R.id.tvsonidostitulo);
        tvAno = (TextView)findViewById(R.id.tvsonidosano);
        tvDuracion = (TextView) findViewById(R.id.tvsonidosduracion);
        storageRf = FirebaseStorage.getInstance().getReference();


        Bundle b=getIntent().getExtras();

        if (b!=null) {
            CPelicula pelicula = b.getParcelable(ListapeliculasActivity.EXTRA_PELICULAS);

            String poster = pelicula.getImagen();
            cargarImagen(poster);


            tvTitulo.setText(pelicula.getTitulo());
            tvAno.setText(pelicula.getAno());
            tvDuracion.setText(pelicula.getDuracion());
            cargarDatosFirebase();


        }

    }//Fin OnCreate

    private void cargarListView (DataSnapshot dataSnapshot) {


        lista_sonidos.add(dataSnapshot.getValue(CSonidos.class));
        AdaptadorSonidos adaptadorsonidos = new AdaptadorSonidos(this, lista_sonidos);
        lvSonidos.setAdapter(adaptadorsonidos);



        lvSonidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CSonidos c = ((CSonidos) parent.getItemAtPosition(position));
                String duracion = c.getDuracion();

                Toast.makeText(getApplicationContext(),"La duración del sonido es: "+duracion, Toast.LENGTH_LONG).show();

            }
        });

    }



    private void  cargarDatosFirebase(){
        String titulo = tvTitulo.getText().toString();
        dbRef = FirebaseDatabase.getInstance().getReference().child("sonidos/"+titulo);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista_sonidos.clear();
                for (DataSnapshot sonidosDataSnapshot: dataSnapshot.getChildren()){
                    cargarListView(sonidosDataSnapshot);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("SonidospeliculaActivity","DATABASE ERROR");

            }
        };
        dbRef.addValueEventListener(valueEventListener);

    }


    private void cargarImagen (String nombre){

        storageRf.child("imágenes/"+nombre).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri.toString()).into(ivPosterSonidos);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });


    }






}
