package com.software2.ex.apiRest.repository;
import com.software2.ex.apiRest.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SerieRepository extends JpaRepository<Serie, Long>{
    List<Serie> findByCodigo(String codigo);
} 
