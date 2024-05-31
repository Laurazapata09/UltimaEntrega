package com.software2.ex.apiRest.service;

import java.util.List;

import com.software2.ex.apiRest.model.Pelicula;

public interface PeliculaService {

    List<Pelicula> getAll();
    Pelicula getByPeliculaId(Long peliculaId);
    Pelicula addPelicula(Pelicula pelicula);
    Pelicula updatePelicula(Long peliculaId, Pelicula newpelicula);
    void deletePelicula(Long peliculaId);

}
