package ucab.empresae.dtos;

import javax.persistence.*;
import java.util.List;

public class DtoPregunta extends DtoBase
{
    private String estado;
    private String descripcion;
    private DtoTipoPregunta dtoTipoPregunta;
    private DtoSubcategoria dtoSubcategoria;

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

    public void setTipo(DtoTipoPregunta dtoTipoPregunta) {
        this.dtoTipoPregunta = dtoTipoPregunta;
    }

    public DtoSubcategoria getDtoSubcategoria() {
        return dtoSubcategoria;
    }

    public void setSubcategoria(DtoSubcategoria dtoSubcategoria) {
        this.dtoSubcategoria = dtoSubcategoria;
    }

}
