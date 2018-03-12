package com.example.sonidosdepeliculas;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Vicente FN on 03/03/2018.
 */

public class AdaptadorPeliculas extends ArrayAdapter<CPelicula> {


    ArrayList<CPelicula> peliculas;
    Context c;
    StorageReference storageRf;

    public AdaptadorPeliculas(Context c, ArrayList<CPelicula> peliculas){
        super(c,R.layout.item_listapeliculas,peliculas);
        this.c = c;
        this.peliculas = peliculas;
        this.storageRf = FirebaseStorage.getInstance().getReference();

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.item_listapeliculas,null);

        //TextView titulo
        TextView tv_titulo=(TextView)item.findViewById(R.id.tvlistatitulo);
        tv_titulo.setText(peliculas.get(position).getTitulo());

        //TextView Año
        TextView tv_ano=(TextView)item.findViewById(R.id.tvlistaano);
        tv_ano.setText(peliculas.get(position).getAno());

        //TextView Duración
        TextView tv_duracion=(TextView)item.findViewById(R.id.tvlistaduracion);
        tv_duracion.setText(peliculas.get(position).getDuracion());

        //TextView Reparto
        TextView tv_reparto=(TextView)item.findViewById(R.id.tvlistareparto);
        tv_reparto.setText(peliculas.get(position).getReparto());

        //ImageView Poster
        String nombreimagen = peliculas.get(position).getImagen();

        ImageView iv_poster = (ImageView)item.findViewById(R.id.imgpelicula);
        cargarImagen(nombreimagen,item,iv_poster);



        return item;



    }

    private void cargarImagen (String nombre, final View item, final ImageView iv_poster){

        storageRf.child("imágenes/"+nombre).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(item).load(uri.toString()).into(iv_poster);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {


            }
        });


    }

}

