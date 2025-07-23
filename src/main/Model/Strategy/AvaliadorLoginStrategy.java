package main.Model.Strategy;

import main.view.HelloApplication;

public class AvaliadorLoginStrategy  implements LoginSuccessStrategy{
    @Override
    public void execute(){
        HelloApplication.obrasAvaliadas();
    }
}
