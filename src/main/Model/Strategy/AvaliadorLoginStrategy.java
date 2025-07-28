package main.Model.Strategy;

import main.Entities.Avaliador;
import main.view.HelloApplication;

public class AvaliadorLoginStrategy  implements LoginSuccessStrategy<Avaliador>{
    @Override
    public void execute(Avaliador conta){
        HelloApplication.telaAvaliadorLogin(conta);
    }
}
