package main.Model.Singleton;

import main.Entities.Dono;
import main.view.HelloApplication;

public class DonoSingleton {

    private static DonoSingleton instance;

    private DonoSingleton(){

    }

    public static DonoSingleton getInstance(){
        if(instance == null){
            synchronized(DonoSingleton.class) {
                if(instance == null) {
                    instance = new DonoSingleton();
                }
            }
        }
        return instance;
    }

}
