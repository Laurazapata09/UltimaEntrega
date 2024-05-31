package com.software2.ex.apiRest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software2.ex.apiRest.model.Pelicula;
import com.software2.ex.apiRest.model.Serie;
import com.software2.ex.apiRest.repository.PeliculaRepository;
import com.software2.ex.apiRest.repository.SerieRepository;

@Service
public class SerieServicelmpl {

@Autowired
    private SerieRepository serieRepository;

    @Override
    public List<Serie> getAll() {
        return serieRepository.findAll();
    }

    @Override
    public Pelicula getBySerieId(Long serieId){
        return serieRepository.findById(serieId).orElse(null);
    }

    @Override
    public Pelicula addSerie(Serie serie) {
        return serieRepository.save(serie);
    }

    @Override
    public Pelicula updateSerie(Long serieId, Serie newserie){
        Serie existentSerie = getByPeliculaId(serieId);
        if (existentSerie != null) {
            newserie.setSerieId(serieId);
            return serieRepository.save(newserie);
        }
        return null;
    }

    @Override
    public void deleteSerie(Long serieId) {
        serieRepository.deleteById(serieId);
    }

}
}

}
