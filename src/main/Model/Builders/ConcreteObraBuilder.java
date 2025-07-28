package main.Model.Builders;

import main.Entities.Escritor;
import main.Entities.Obra;

public class ConcreteObraBuilder implements ObraBuilder {

    private Obra obra;

    public ConcreteObraBuilder() {
        this.obra = new Obra();
    }

    @Override
    public ObraBuilder buildTitulo(String titulo) {
        obra.setTitulo(titulo);
        return this;
    }

    @Override
    public ObraBuilder buildGenero(String genero) {
        obra.setGenero(genero);
        return this;
    }

    @Override
    public ObraBuilder buildAno(int ano) {
        obra.setAno(ano);
        return this;
    }

    @Override
    public ObraBuilder buildAutor(Escritor autor) {
        obra.setAutor(autor);
        return this;
    }

    @Override
    public Obra getResult() {
        return this.obra;
    }
}