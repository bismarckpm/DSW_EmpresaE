package ucab.empresae.dtos;

public class DtoPreguntaOpcion extends DtoBase {

    //Atributos
    private String estado;

    //Relaciones
    private DtoPregunta dtoPregunta;
    private DtoOpcion dtoOpcion;

    //Constructores
    public DtoPreguntaOpcion() {
    }

    public DtoPreguntaOpcion(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DtoPregunta getDtoPregunta() {
        return dtoPregunta;
    }

    public void setDtoPregunta(DtoPregunta dtoPregunta) {
        this.dtoPregunta = dtoPregunta;
    }

    public DtoOpcion getDtoOpcion() {
        return dtoOpcion;
    }

    public void setDtoOpcion(DtoOpcion dtoOpcion) {
        this.dtoOpcion = dtoOpcion;
    }

}
