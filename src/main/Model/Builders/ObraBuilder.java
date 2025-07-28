package main.Model.Builders;

import main.Entities.Escritor;
import main.Entities.Obra;

public interface ObraBuilder {
    ObraBuilder buildTitulo(String titulo);
    ObraBuilder buildGenero(String genero);
    ObraBuilder buildAno(int ano);
    ObraBuilder buildAutor(Escritor autor);
    Obra getResult();
}

