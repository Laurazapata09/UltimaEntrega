package com.software2.ex.apiRest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.software2.ex.apiRest.model.Pelicula;

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
@RequestMapping("/api/peliculas")
public class PeliculaController {

    private List<Pelicula> peliculas;

    public PeliculaController() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Pelicula[] peliculasArray = objectMapper.readValue(new ClassPathResource("peliculas.json").getFile(),
                    Pelicula[].class);
            peliculas = new ArrayList<>(Arrays.asList(peliculasArray));
        } catch (IOException e) {
            e.printStackTrace();
            peliculas = new ArrayList<>();
        }
    }

    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        return peliculas;
    }

    @GetMapping("/{id}")
    public Pelicula getPeliculaById(@PathVariable Long id) {
        return peliculas.stream().filter(pelicula -> pelicula.getId().equals(id)).findFirst().orElse(null);

    }

    @PostMapping
    public Pelicula createPelicula(@RequestBody Pelicula pelicula) {
        peliculas.add(pelicula);
        return pelicula;
    }

    @PutMapping("/{id}")
    public Pelicula updatePelicula(@PathVariable Long id, @RequestBody Pelicula updatedPelicula) {
        Pelicula pelicula = getPeliculaById(id);
        if (pelicula != null) {
            pelicula.setNombre(updatedPelicula.getNombre());
            pelicula.setGenero(updatedPelicula.getGenero());
            return pelicula;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Long id) {
        peliculas.removeIf(pelicula -> pelicula.getId().equals(id));
    }

}
