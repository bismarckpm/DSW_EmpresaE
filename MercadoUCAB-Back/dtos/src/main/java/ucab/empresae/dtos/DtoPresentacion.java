package ucab.empresae.dtos;

public class DtoPresentacion extends DtoBase {

    //Atributos
    private String estado;
    private String descripcion;

    //Constructores
    public DtoPresentacion() {
        super();
    }

    public DtoPresentacion(long id)  {
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
