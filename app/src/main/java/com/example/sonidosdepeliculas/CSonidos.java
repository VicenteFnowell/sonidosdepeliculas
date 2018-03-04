package com.example.sonidosdepeliculas;

/**
 * Created by Vicente FN on 04/03/2018.
 */

public class CSonidos {

    String titulo;
    String duracion;

    public CSonidos(String titulo, String duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
