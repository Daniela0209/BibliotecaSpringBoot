package com.example.bibliotecaWeb.DTO;

public class PrestamoDTO {

    private String idPrestamo;
    private String idUsuario;
    private String idRecursos;
    private String fechaPrestamo;

    public PrestamoDTO(){
    }

    public String getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(String idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdRecursos() {
        return idRecursos;
    }

    public void setIdRecursos(String idRecursos) {
        this.idRecursos = idRecursos;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
}
