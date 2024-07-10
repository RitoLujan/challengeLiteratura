package com.riot.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autor,
        @JsonAlias("bookshelves") List<String> librerias,
        @JsonAlias("languages") List<String> lenguajes,
        @JsonAlias("download_count") Integer noDescargas) {
}
