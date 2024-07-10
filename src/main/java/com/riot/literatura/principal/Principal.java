package com.riot.literatura.principal;

import com.riot.literatura.model.Datos;
import com.riot.literatura.model.DatosLibro;
import com.riot.literatura.service.ConsumoAPI;
import com.riot.literatura.service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private String json;

    public void muestraMenu(){
        json = consumoAPI.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);
        var opcion = -1;
        while (opcion != 0){
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar libros por idioma
                    4 - Listar autores registrados
                    5 - Listar autores vivos en determinado año

                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarTitulo();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    private void buscarTitulo(){
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        String librerias = libroBuscado.get().librerias().isEmpty() ? "N/A" : String.join(", ", libroBuscado.get().librerias());
        String lenguajes = libroBuscado.get().lenguajes().isEmpty() ? "N/A" : String.join(", ", libroBuscado.get().lenguajes());
        String autores = libroBuscado.get().autor().stream()
                .map(autor -> String.format("%s %s - %s", autor.nombre(), autor.fechaNacimiento(), autor.fechafuncion()))
                .collect(Collectors.joining(", "));
        if(libroBuscado.isPresent()){
            var res = """
                    -------------------- Libro --------------------
                    Título:         %s
                    Autor:          %s
                    Librerías:      %s
                    Lenguajes:      %s
                    No Descargas:   %s
                    -----------------------------------------------
                    """.formatted(libroBuscado.get().titulo(), autores, librerias,
                    lenguajes, libroBuscado.get().noDescargas());
            System.out.println("Libro Encontrado ");
            System.out.println(res);
        }else {
            System.out.println("Libro no encontrado");
        }
    }
}
