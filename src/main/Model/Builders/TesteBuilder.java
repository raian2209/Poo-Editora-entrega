package main.Model.Builders;

import main.Entities.Escritor;
import main.Entities.Obra;

public class TesteBuilder {
    public static void main(String[] args) {
        Escritor autor = new Escritor("Alisson", "123.123.123-56", "Rua dois");
        //titulo,genero,ano,autor
        Obra obra = new ConcreteObraBuilder()
                .buildTitulo("Branca de neve")
                .buildGenero("Aventura")
                .buildAno(1990)
                .buildAutor(autor)
                .getResult();

        System.out.println(obra);

        ObraBuilder obraBuilder = new ConcreteObraBuilder();
        ObraDirector obraDirector = new ObraDirector();

        obraDirector.constructBiografia(obraBuilder, "Minha história", autor, 1025);

        obraDirector.constructTerror(obraBuilder, "O iluminado", autor, 1025);


        System.out.println("====================");
        System.out.println(obraBuilder.getResult());
    }
}
