package ucab.empresae.dtos;

public class DtoTipoPregunta extends DtoBase {

    //Atributos
    private String estado;
    private String tipo;

    //Constructores
    public DtoTipoPregunta(long id){
        super(id);
    }

    public DtoTipoPregunta() {
        super();
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
