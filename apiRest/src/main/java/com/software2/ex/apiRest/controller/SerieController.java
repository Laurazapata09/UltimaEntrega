package com.software2.ex.apiRest.controller;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.software2.ex.apiRest.model.Serie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/series")

public class SerieController {
    
    private List<Serie> series;

    public SerieController() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Serie[] seriesArray = objectMapper.readValue(new ClassPathResource("series.json").getFile(),
                    Serie[].class);
            series = new ArrayList<>(Arrays.asList(seriesArray));
        } catch (IOException e) {
            e.printStackTrace();
            series = new ArrayList<>();
        }
    }

    @GetMapping
    public List<Serie> getAllSeries() {
        return series;
    }

    @GetMapping("/{id}")
    public Serie getSerieById(@PathVariable Long id) {
        return series.stream().filter(serie -> serie.getId().equals(id)).findFirst().orElse(null);

    }

    @PostMapping
    public Serie createSerie(@RequestBody Serie serie) {
        series.add(serie);
        return serie;
    }

    @PutMapping("/{id}")
    public Serie updateSerie (@PathVariable Long id, @RequestBody Serie updatedSerie) {
        Serie serie = getSerieById(id);
        if (serie != null) {
            serie.setNombre(updatedSerie.getNombre());
            serie.setTemporadas(updatedSerie.getTemporadas());
            return serie;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteSerie(@PathVariable Long id) {
        series.removeIf(serie -> serie.getId().equals(id));
    }

}

    

