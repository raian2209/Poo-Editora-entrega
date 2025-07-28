// Crie um novo arquivo: ObraDirector.java
package main.Model.Builders;

import main.Entities.Escritor;

public class ObraDirector {
    public void constructFantasia(ObraBuilder builder, String titulo, Escritor autor, int ano) {
        builder.buildTitulo(titulo)
                .buildGenero("Fantasia")
                .buildAno(ano)
                .buildAutor(autor);
    }
    public void constructTerror(ObraBuilder builder, String titulo, Escritor autor, int ano) {
        builder.buildTitulo(titulo)
                .buildGenero("Terror")
                .buildAno(ano)
                .buildAutor(autor);
    }
    public void constructRomance(ObraBuilder builder, String titulo, Escritor autor, int ano) {
        builder.buildTitulo(titulo)
                .buildGenero("Romance")
                .buildAno(ano)
                .buildAutor(autor);
    }
    public void constructSuspense(ObraBuilder builder, String titulo, Escritor autor, int ano) {
        builder.buildTitulo(titulo)
                .buildGenero("Suspense")
                .buildAno(ano)
                .buildAutor(autor);
    }
}