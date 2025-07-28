package main.Model.Strategy;

import main.Entities.Escritor;
import main.view.HelloApplication;

public class EscritorLoginStrategy implements LoginSuccessStrategy<Escritor>{
    @Override
    public void execute(Escritor conta){
        HelloApplication.telaEscritorLogin(conta);
    }
}
