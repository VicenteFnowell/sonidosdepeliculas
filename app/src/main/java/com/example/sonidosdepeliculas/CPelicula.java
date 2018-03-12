package com.example.sonidosdepeliculas;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vicente FN on 03/03/2018.
 */

public class CPelicula implements Parcelable {

    String titulo;
    String duracion;
    String ano;
    String premios;
    String categoria;
    String imagen;
    String reparto;



    public static final Parcelable.Creator<CPelicula> CREATOR = new Parcelable.Creator<CPelicula>() {
        public CPelicula createFromParcel(Parcel in) {
            return new CPelicula(in);
        }

        public CPelicula[] newArray(int size) {
            return new CPelicula[size];
        }
    };

    public CPelicula(String nombre, String duracion, String ano, String premios, String categoria, String imagen, String reparto) {
        this.titulo = nombre;
        this.duracion = duracion;
        this.ano = ano;
        this.premios = premios;
        this.categoria = categoria;
        this.imagen = imagen;
        this.reparto= reparto;
    }

    public CPelicula(Parcel p) {
        readFromParcel(p);
    }

    public CPelicula() {
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

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPremios() {
        return premios;
    }

    public void setPremios(String premios) {
        this.premios = premios;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.titulo);
            dest.writeString(this.ano);
            dest.writeString(this.duracion);
            dest.writeString(this.imagen);
            dest.writeString(this.premios);
            dest.writeString(this.categoria);
            dest.writeString(this.reparto);


        }

        private void readFromParcel(Parcel p) {

            this.titulo= p.readString();
            this.ano= p.readString();
            this.duracion= p.readString();
            this.imagen= p.readString();
            this.premios= p.readString();
            this.categoria= p.readString();
            this.reparto= p.readString();


        }

}
