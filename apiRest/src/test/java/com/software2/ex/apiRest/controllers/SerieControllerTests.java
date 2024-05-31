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

import com.software2.ex.apiRest.controller.SerieController;
import com.software2.ex.apiRest.model.Serie;
import com.software2.ex.apiRest.repository.SerieRepository;

@WebMvcTest(SerieController.class)
public class SerieControllerTests {
    
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private SerieRepository serieRepository;
    
    @BeforeEach
    public void setup() {
        this.mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAll() throws Exception{
        when(serieRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/series"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));
        verify(serieRepository,times(1)).findAll();
    }

    @Test
    public void testGetById() throws Exception{
        Serie serie=new Serie();
        serie.setId(1L);
        when(serieRepository.findById(1L)).thenReturn(Optional.of(serie));
        mockMvc.perform(get("/api/series/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1));
        verify(serieRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreatSerie() throws Exception{
        Serie serie=new Serie();
        serie.setId(1L);
        when(serieRepository.save(any(Serie.class))).thenReturn(serie);
        mockMvc.perform(post("/api/serie")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\":null, \"nombre\": null, \"temporadas\": null}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1));
        verify(serieRepository,times(1)).save(any(Serie.class));
    }

    @Test
    public void testUpdateSerie() throws Exception{
        Serie serie =new Serie();
        serie.setId(1L);
        when(serieRepository.findById(1L)).thenReturn(Optional.of(serie));
        mockMvc.perform(put("/api/series")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\":null, \"nombre\": null, \"temporadas\": null}"))
        .andExpect(jsonPath("$.id").value(1));
        verify(serieRepository,times(1)).findById(1L);
    }

    @Test
    public void testDeleteSerie() throws Exception{
        doNothing().when(serieRepository).deleteById(1L);
        mockMvc.perform(delete("/api/series/1"))
            .andExpect(status().isOk());
        verify(serieRepository, times(1)).deleteById(1L);
    }

}