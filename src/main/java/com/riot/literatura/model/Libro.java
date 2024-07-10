package com.riot.literatura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private List<String> libreria;
    private List<String> lenguejes;
    private Integer totalDescargas;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.libreria = datosLibro.librerias();
        this.lenguejes = datosLibro.lenguajes();
        this.totalDescargas = datosLibro.noDescargas();
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", libreria=" + libreria +
                ", lenguejes=" + lenguejes +
                ", totalDescargas=" + totalDescargas +
                ", autor=" + autor +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getLibreria() {
        return libreria;
    }

    public void setLibreria(List<String> libreria) {
        this.libreria = libreria;
    }

    public List<String> getLenguejes() {
        return lenguejes;
    }

    public void setLenguejes(List<String> lenguejes) {

        this.lenguejes = lenguejes;
    }

    public Integer getTotalDescargas() {
        return totalDescargas;
    }

    public void setTotalDescargas(Integer totalDescargas) {
        this.totalDescargas = totalDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
