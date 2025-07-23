package main.Model.Strategy;

import main.view.HelloApplication;

public class EscritorLoginStrategy implements LoginSuccessStrategy{
    @Override
    public void execute(){
        HelloApplication.telaRegistrarObra();
    }
}
