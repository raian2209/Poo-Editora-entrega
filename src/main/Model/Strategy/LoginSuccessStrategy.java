package main.Model.Strategy;

public interface LoginSuccessStrategy<T> {
    void  execute(T conta);
}
