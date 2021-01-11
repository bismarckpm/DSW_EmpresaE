package Comandos;

public abstract class ComandoBase<T> {

    public abstract void execute() throws Exception;

    public abstract T getResult() throws Exception;

}