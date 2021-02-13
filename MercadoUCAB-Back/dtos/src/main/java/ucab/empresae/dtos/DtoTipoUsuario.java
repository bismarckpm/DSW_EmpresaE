package ucab.empresae.dtos;

public class DtoTipoUsuario extends DtoBase {

    //Atributos
    private String descripcion;
    private String estado;

    //Constructores
    public DtoTipoUsuario() {
        super();
    }

    public DtoTipoUsuario(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
