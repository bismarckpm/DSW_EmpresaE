package ucab.empresae.dtos;

public class DtoClienteEstudio extends DtoBase{

    //Atributos
    private String estado;
    private String comentarioCliente;

    //Relaciones
    private DtoCliente cliente;
    private DtoEstudio estudio;

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
        return cliente;
    }

    public void setCliente(DtoCliente cliente) {
        this.cliente = cliente;
    }

    public DtoEstudio getEstudio() {
        return estudio;
    }

    public void setEstudio(DtoEstudio estudio) {
        this.estudio = estudio;
    }

}
