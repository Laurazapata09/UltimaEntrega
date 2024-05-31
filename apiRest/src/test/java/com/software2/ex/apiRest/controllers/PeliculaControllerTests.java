package com.software2.ex.apiRest.controllers;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.software2.ex.apiRest.model.Pelicula;
import com.software2.ex.apiRest.controller.PeliculaController;
import com.software2.ex.apiRest.repository.PeliculaRepository;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTests {
    
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private PeliculaRepository peliculaRepository;
    
    @BeforeEach
    public void setup() {
        this.mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAll() throws Exception{
        when(peliculaRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/peliculas"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));
        verify(peliculaRepository,times(1)).findAll();
    }

    @Test
    public void testGetById() throws Exception{
        Pelicula pelicula=new Pelicula();
        pelicula.setId(1L);
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));
        mockMvc.perform(get("/api/peliculas/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1));
        verify(peliculaRepository,times(1)).findById(1L);
    }

    @Test
    public void testCreatPelicula() throws Exception{
        Pelicula pelicula=new Pelicula();
        pelicula.setId(1L);
        when(peliculaRepository.save(any(Pelicula.class))).thenReturn(pelicula);
        mockMvc.perform(post("/api/peliculas")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\":null, \"nombre\": null, \"genero\": null}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1));
        verify(peliculaRepository,times(1)).save(any(Pelicula.class));
    }

    @Test
    public void testUpdatePelicula() throws Exception{
        Pelicula pelicula =new Pelicula();
        pelicula.setId(1L);
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));
        mockMvc.perform(put("/api/peliculas")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\":null, \"nombre\": null, \"genero\": null}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1));
        verify(peliculaRepository,times(1)).findById(1L);
    }

    @Test
    public void testDeletePelicula() throws Exception{
        doNothing().when(peliculaRepository).deleteById(1L);
        mockMvc.perform(delete("/api/peliculas/1"))
            .andExpect(status().isOk());
        verify(peliculaRepository, times(1)).deleteById(1L);
    }

}