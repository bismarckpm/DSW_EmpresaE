package ucab.empresae.dtos;

public class DtoPregunta extends DtoBase {

    //Atributps
    private String estado;
    private String descripcion;

    //Relaciones
    private DtoTipoPregunta dtoTipoPregunta;
    private DtoSubcategoria dtoSubcategoria;

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

    public DtoTipoPregunta getDtoTipoPregunta() {
        return dtoTipoPregunta;
    }

    public void setDtoTipoPregunta(DtoTipoPregunta dtoTipoPregunta) {
        this.dtoTipoPregunta = dtoTipoPregunta;
    }

    public DtoSubcategoria getDtoSubcategoria() {
        return dtoSubcategoria;
    }

    public void setDtoSubcategoria(DtoSubcategoria dtoSubcategoria) {
        this.dtoSubcategoria = dtoSubcategoria;
    }

}
