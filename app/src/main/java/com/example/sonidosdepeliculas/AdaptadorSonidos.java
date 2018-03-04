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
 * Created by Vicente FN on 04/03/2018.
 */

public class AdaptadorSonidos extends ArrayAdapter<CSonidos> {


    ArrayList<CSonidos> sonidos;
    Context c;

    public AdaptadorSonidos(Context c, ArrayList<CSonidos> sonidos){
        super(c,R.layout.item_sonidospelis,sonidos);
        this.c = c;
        this.sonidos = sonidos;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.item_sonidospelis,null);

        //TextView titulo
        TextView tv_titulo=(TextView)item.findViewById(R.id.tvitmsndtitulo);
        tv_titulo.setText(sonidos.get(position).getTitulo());

        //TextView duracion
        TextView tv_duracion=(TextView)item.findViewById(R.id.tvitmsndduracion);
        tv_duracion.setText(sonidos.get(position).getDuracion());


        return item;



    }
}