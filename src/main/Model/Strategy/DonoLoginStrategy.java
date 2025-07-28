package main.Model.Strategy;

import main.Entities.Dono;
import main.view.HelloApplication;

public class DonoLoginStrategy implements LoginSuccessStrategy<Dono>{
    @Override
    public void execute(Dono conta) {
        // Por enquanto, apenas imprime no console. Poderia navegar para um painel admin.
        System.out.println("Login de Dono realizado. Acesso administrativo concedido.");
        HelloApplication.telaDono();
    }
}
