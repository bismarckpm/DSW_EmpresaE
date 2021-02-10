package ucab.empresae.entidades;

public class OpcionAux extends BaseEntity{
    private String estado;
    private String descripcion;
    private long valor;


    public OpcionAux(long id){
        super(id);
    }

    public OpcionAux() {
    }

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

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

}
