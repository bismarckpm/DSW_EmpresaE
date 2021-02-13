package ucab.empresae.excepciones;

public class CustomException extends Exception{
    private String mensajeInterno;

    private String mensaje;
    private String codError;

    public CustomException(Exception e) {
        super(e);
    }

    public CustomException(String mensaje, Exception e) {
        super(mensaje, e);
    }

    public CustomException(String mensaje) {
        super(mensaje);
    }

    public CustomException(String codError, String mensaje) {
        this.codError = codError;
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCodError() {
        return codError;
    }

    public void setCodError(String codError) {
        this.codError = codError;
    }
}
