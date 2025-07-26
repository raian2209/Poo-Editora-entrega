package main.Model.Strategy;

import main.view.HelloApplication;

public class DonoLoginStrategy implements LoginSuccessStrategy{
    @Override
    public void execute() {
        // Por enquanto, apenas imprime no console. Poderia navegar para um painel admin.
        System.out.println("Login de Dono realizado. Acesso administrativo concedido.");
        HelloApplication.telaDono();
    }
}
