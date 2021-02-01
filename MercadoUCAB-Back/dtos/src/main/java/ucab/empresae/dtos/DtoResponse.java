package ucab.empresae.dtos;

public class DtoResponse extends DtoBase{

    public String mensaje;
    public String mensaje_soporte;
    public String codError;
    public String estado;
    public Object objeto;

    public DtoResponse() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje_soporte() {
        return mensaje_soporte;
    }

    public void setMensaje_soporte(String mensaje_soporte) {
        this.mensaje_soporte = mensaje_soporte;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    public String getCodError() {
        return codError;
    }

    public void setCodError(String codError) {
        this.codError = codError;
    }
}
