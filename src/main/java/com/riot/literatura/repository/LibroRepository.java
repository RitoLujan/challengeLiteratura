package com.riot.literatura.repository;

import com.riot.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String tituloLibro);

    List<Libro> findTop5ByOrderByTotalDescargasDesc();
}
