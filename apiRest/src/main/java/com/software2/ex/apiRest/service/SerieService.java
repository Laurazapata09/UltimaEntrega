package com.software2.ex.apiRest.service;

import java.util.List;

import com.software2.ex.apiRest.model.Serie;

public interface SerieService {
    List<Serie> getAll();
    Serie getBySerieId(Long serieId);
    Serie addSerie(Serie serie);
    Serie updateSerie(Long serieId, Serie newserie);
    void deleteSerie(Long serieId);

}
