package com.example.sonidosdepeliculas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SonidospeliculaActivity extends AppCompatActivity {

    ImageView ivPelicula;
    TextView tvTitulo,tvAno,tvDuracion;
    ListView lvSonidos;
    ArrayList<CSonidos> lista_sonidos = new ArrayList<CSonidos>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonidospelicula);

        ivPelicula = (ImageView)findViewById(R.id.imgsonidospeli);
        tvTitulo = (TextView)findViewById(R.id.tvsonidostitulo);
        tvAno = (TextView)findViewById(R.id.tvsonidosano);
        tvDuracion = (TextView)findViewById(R.id.tvsonidosduracion);

        Bundle b=getIntent().getExtras();



        if (b!=null){
            CPelicula pelicula=b.getParcelable(ListapeliculasActivity.EXTRA_PELICULAS);
            //ivPelicula.setImageDrawable(pelicula.getImagen());
            tvTitulo.setText(pelicula.getTitulo());
            tvAno.setText(pelicula.getAno());
            tvDuracion.setText(pelicula.getDuracion());
        }


        cargarDatos();

        lvSonidos = (ListView)findViewById(R.id.lvsonidospelis);
        AdaptadorSonidos adaptadorsonidos = new AdaptadorSonidos(this,lista_sonidos);
        lvSonidos.setAdapter(adaptadorsonidos);


    }//Fin OnCreate



    private void cargarDatos(){

        lista_sonidos.add(new CSonidos("¿A quién se lo dices?", "00.30 "));
        lista_sonidos.add(new CSonidos("Hola hola carambola", "00.12 "));
        lista_sonidos.add(new CSonidos("¿Me estás tomando el pelo?", "00.30 "));
        lista_sonidos.add(new CSonidos("Yo soy tu padre", "00.10 "));
        lista_sonidos.add(new CSonidos("Que dices tio", "00.20"));
        lista_sonidos.add(new CSonidos("Hacer algunas apuestas", "00.15 "));
        lista_sonidos.add(new CSonidos("Dios mio...", "00.07 "));



    }





}
