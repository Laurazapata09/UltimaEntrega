package com.software2.ex.apiRest.repository;
import com.software2.ex.apiRest.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
    List<Pelicula> findByCodigo(String codigo);
} 



