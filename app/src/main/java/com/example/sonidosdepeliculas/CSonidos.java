package com.example.sonidosdepeliculas;

/**
 * Created by Vicente FN on 04/03/2018.
 */

public class CSonidos {

    String titulo;
    String duracion;
    String nombre;
    String pelicula;

    public CSonidos(String titulo, String duracion, String nombre,String pelicula) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.nombre = nombre;
        this.pelicula = pelicula;

    }

    public CSonidos() {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }
}
