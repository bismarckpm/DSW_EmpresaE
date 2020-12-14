package ucab.empresae.dtos;

public class DtoTelefono extends DtoBase{

    private String estado;
    private String numero;

    private DtoEncuestado encuestado;
    private DtoCliente cliente;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String  numero) {
        this.numero = numero;
    }

    public DtoEncuestado getEncuestado() {
        return encuestado;
    }

    public void setEncuestado(DtoEncuestado encuestado) {
        this.encuestado = encuestado;
    }

    public DtoCliente getCliente() {
        return cliente;
    }

    public void setCliente(DtoCliente cliente) {
        this.cliente = cliente;
    }
}
