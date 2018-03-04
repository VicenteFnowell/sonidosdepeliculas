package com.example.sonidosdepeliculas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vicente FN on 03/03/2018.
 */

public class AdaptadorPeliculas extends ArrayAdapter<CPelicula> {


    ArrayList<CPelicula> peliculas;
    Context c;

    public AdaptadorPeliculas(Context c, ArrayList<CPelicula> peliculas){
        super(c,R.layout.item_listapeliculas,peliculas);
        this.c = c;
        this.peliculas = peliculas;

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

        //ImageView
        String logo = peliculas.get(position).getImagen();

        int idImagen = c.getResources().getIdentifier(logo, "drawable", c.getPackageName());
        ImageView iv_poster = (ImageView)item.findViewById(R.id.imgpelicula);
        iv_poster.setImageResource(idImagen);


        return item;



    }
}

