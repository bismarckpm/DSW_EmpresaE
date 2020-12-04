package ucab.empresae.dtos;

public class DtoPresentacion extends DtoBase {

    //Atributos
    private String estado;
    private String descripcion;

    //Constructores
    public DtoPresentacion() {
    }

    public DtoPresentacion(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
