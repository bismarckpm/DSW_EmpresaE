package Comandos;

/**
 * Clase padre de todos los comandos.
 * @author José Prieto
 * @param <T> Clase general
 */
public abstract class ComandoBase<T> {

    public abstract void execute() throws Exception;

    public abstract T getResult() throws Exception;

}