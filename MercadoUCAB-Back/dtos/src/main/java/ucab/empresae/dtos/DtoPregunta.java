package ucab.empresae.dtos;

import java.util.List;

public class DtoPregunta extends DtoBase {

    //Atributps
    private String estado;
    private String descripcion;

    //Relaciones
    private DtoTipoPregunta tipo;
    private DtoSubcategoria subcategoria;

    private List<DtoOpcion> opciones;

    private String[] opcionesString;

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

    public List<DtoOpcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<DtoOpcion> opciones) {
        this.opciones = opciones;
    }

    public String[] getOpcionesString() {
        return opcionesString;
    }

    public void setOpcionesString(String[] opcionesString) {
        this.opcionesString = opcionesString;
    }

}
