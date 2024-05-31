package com.software2.ex.apiRest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software2.ex.apiRest.model.Pelicula;
import com.software2.ex.apiRest.repository.PeliculaRepository;

@Service
public class PeliculaServicelmpl implements PeliculaService{


    
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> getAll() {
        return peliculaRepository.findAll();
    }

    @Override
    public Pelicula getByPeliculaId(Long peliculaId){
        return peliculaRepository.findById(peliculaId).orElse(null);
    }

    @Override
    public Pelicula addPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
    public Pelicula updatePelicula(Long peliculaId, Pelicula newpelicula){
        Pelicula existentPelicula = getByPeliculaId(peliculaId);
        if (existentPelicula != null) {
            newpelicula.setPeliculaId(peliculaId);
            return peliculaRepository.save(newpelicula);
        }
        return null;
    }

    @Override
    public void deletePelicula(Long peliculaId) {
        peliculaRepository.deleteById(peliculaId);
    }

}
}
