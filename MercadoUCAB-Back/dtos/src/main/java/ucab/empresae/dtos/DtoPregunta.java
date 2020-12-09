package ucab.empresae.dtos;

public class DtoPregunta extends DtoBase {

    //Atributps
    private String estado;
    private String descripcion;

    //Relaciones
    private DtoTipoPregunta tipo;
    private DtoSubcategoria subcategoria;

    //Constructores
    public DtoPregunta() {
    }

    public DtoPregunta(long id) throws Exception {
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

    public DtoTipoPregunta getTipo() {
        return tipo;
    }

    public void setTipo(DtoTipoPregunta tipo) {
        this.tipo = tipo;
    }

    public DtoSubcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(DtoSubcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

}
