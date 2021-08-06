package com.example.bibliotecaWeb.DTO;

public class RecursosDTO {

    private String idRecursos;
    private String titulo;
    private String tematica;
    private boolean estado;
    private String tipoRecurso;

    public RecursosDTO(){

    }

    public String getIdRecursos() {
        return idRecursos;
    }

    public void setIdRecursos(String idRecursos) {
        this.idRecursos = idRecursos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
}
