package ucab.empresae.dtos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class DtoClienteEstudio extends DtoBase{

    //Atributos
    private String estado;
    private String comentarioCliente;

    //Relaciones
    private DtoCliente dtoCliente;
    private DtoEstudio dtoEstudio;

    //Constructores
    public DtoClienteEstudio() {
        super();
    }

    public DtoClienteEstudio(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentarioCliente() {
        return comentarioCliente;
    }

    public void setComentarioCliente(String comentarioCliente) {
        this.comentarioCliente = comentarioCliente;
    }

    public DtoCliente getCliente() {
        return dtoCliente;
    }

    public void setCliente(DtoCliente cliente) {
        this.dtoCliente = cliente;
    }

    public DtoEstudio getEstudio() {
        return dtoEstudio;
    }

    public void setEstudio(DtoEstudio estudio) {
        this.dtoEstudio = estudio;
    }

}
