package ucab.empresae.dtos;

import java.util.List;

public class DtoRespuesta extends DtoBase {

    //Atributos
    private String estado;
    private String texto;

    //Relaciones
    private DtoPregunta dtoPregunta;
    private List<DtoOpcion> opciones;
    private DtoPreguntaOpcion dtoPreguntaOpcion;
    private DtoEncuestado dtoEncuestado;

    //Constructores
    public DtoRespuesta() {
    }

    public DtoRespuesta(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public DtoPreguntaOpcion getDtoPreguntaOpcion() {
        return dtoPreguntaOpcion;
    }

    public void setDtoPreguntaOpcion(DtoPreguntaOpcion dtoPreguntaOpcion) {
        this.dtoPreguntaOpcion = dtoPreguntaOpcion;
    }

    public DtoEncuestado getDtoEncuestado() {
        return dtoEncuestado;
    }

    public void setDtoEncuestado(DtoEncuestado dtoEncuestado) {
        this.dtoEncuestado = dtoEncuestado;
    }

    public List<DtoOpcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<DtoOpcion> opciones) {
        this.opciones = opciones;
    }

}
