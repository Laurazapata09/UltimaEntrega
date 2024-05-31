package com.software2.ex.apiRest.model;

public class Serie {
    private Long id;
    private String nombre;
    private String temporadas;
    
    public Serie() {
    }
    
    public Serie(Long id, String nombre, String temporadas) {
        this.id = id;
        this.nombre = nombre;
        this.temporadas = temporadas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(String temporadas) {
        this.temporadas = temporadas;
    }

    
    
    
}
