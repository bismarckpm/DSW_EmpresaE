package ucab.empresae.excepciones;

public class CategoriaException extends Exception {

    private String mensajeInterno;

    public CategoriaException(Exception e) {
        super(e);
    }

    public CategoriaException(String mensaje, Exception e) {
        super(mensaje, e);
    }

    public CategoriaException(String mensaje) {
        super(mensaje);
    }
}
