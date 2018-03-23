package com.example.sonidosdepeliculas;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Vicente FN on 04/03/2018.
 */

public class AdaptadorSonidos extends ArrayAdapter<CSonidos> {


    ArrayList<CSonidos> sonidos;
    Context c;
    StorageReference stRef;
    StorageReference audioRef;

    public AdaptadorSonidos(Context c, ArrayList<CSonidos> sonidos){
        super(c,R.layout.item_sonidospelis,sonidos);
        this.c = c;
        this.sonidos = sonidos;
        this.stRef  = FirebaseStorage.getInstance().getReference();

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.item_sonidospelis,null);


        //TextView titulo
        TextView tv_titulo=(TextView)item.findViewById(R.id.tvitmsndtitulo);
        tv_titulo.setText(sonidos.get(position).getTitulo());

        //TextView duracion
        TextView tv_duracion=(TextView)item.findViewById(R.id.tvitmsndduracion);
        tv_duracion.setText(sonidos.get(position).getDuracion());

        //BTN Share
        Button bt_share =(Button)item.findViewById(R.id.btitmsnshare);

        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sonido = sonidos.get(position).getNombre();
                String pelicula = sonidos.get(position).getPelicula();

                   // compartirAudio(sonido,pelicula);
                    descargarArchivoLocalFirebase();



            }
        });

        //BTN PLAY
        final Button bt_playpause = (Button)item.findViewById(R.id.btitmplay);
        bt_playpause.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String sonido = sonidos.get(position).getNombre();
                String pelicula = sonidos.get(position).getPelicula();
                cargarAudio(sonido,pelicula);
                bt_playpause.setBackgroundResource(R.drawable.pauseselector);


            }
        });

        return item;



    }




    private void cargarAudio(String audio, String pelicula){


        stRef.child("audios/"+pelicula+("/")+audio).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                MediaPlayer mp = new MediaPlayer();
                try {

                    mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mp.setDataSource(uri.toString());
                    mp.prepare();
                    mp.start();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }



private void compartirAudio (String audio, String pelicula) throws IOException {
    StorageReference audiotemporal = stRef.child("audios/"+pelicula+("/")+audio);
    final File audiodescargado;
    audiodescargado = File.createTempFile("audios","mp3");
    audiotemporal.getFile(audiodescargado).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
            String audiotempdescargado = audiodescargado.toString();
            Uri uri = Uri.parse(audiotempdescargado);
            Intent share =  new Intent(Intent.ACTION_SEND);
            share.setType("audio/*");
            share.putExtra(Intent.EXTRA_STREAM,uri);
            c.startActivity(Intent.createChooser(share,"Compartir sonido en: "));
            //Local temp file has been created
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            //Handle any errors
        }
    });


}

private void descargarArchivoLocalFirebase(){
    this.stRef  = FirebaseStorage.getInstance().getReference().child("audios/Matrix/elegido.mp3");

    File localFile = null;
   String audiotempdescargado="";
    try {
            localFile = File.createTempFile("elegido", ".mp3");
            audiotempdescargado = localFile.getPath();
    } catch (IOException e) {
        e.printStackTrace();
    }

    stRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
            Toast.makeText(getContext(),"creado",Toast.LENGTH_LONG).show();


        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception exception) {
            Toast.makeText(getContext(),"aaa "+exception,Toast.LENGTH_LONG).show();
        }
    });

     Uri uri = Uri.parse(audiotempdescargado);
    Intent share =  new Intent(Intent.ACTION_SEND,uri);
    share.setType("audio/*");
    c.startActivity(Intent.createChooser(share,"Compartir sonido en: "));
}

/*
    private void compartirAudio (String audio, String pelicula){

        String sonido = stRef.child("audios/"+pelicula+("/")+audio).getDownloadUrl().toString();
        Uri uri = Uri.parse(sonido);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("audio/mp3");
        share.putExtra(Intent.EXTRA_STREAM,uri);
        c.startActivity(Intent.createChooser(share, "Compartir sonido en: "));

    }
    */


}




