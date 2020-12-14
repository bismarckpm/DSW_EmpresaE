package ucab.empresae.dtos;

public class DtoCliente extends DtoBase{

    //Atributos
    private String estado;
    private String razonSocial;
    private int rif;

    //Relaciones
    private DtoLugar lugar;
    private DtoUsuario usuario;

    //Constructores
    public DtoCliente() {
        super();
    }

    public DtoCliente(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getRif() {
        return rif;
    }

    public void setRif(int rif) {
        this.rif = rif;
    }

    public DtoLugar getLugar() {
        return lugar;
    }

    public void setLugar(DtoLugar lugar) {
        this.lugar = lugar;
    }

    public DtoUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(DtoUsuario usuario) {
        this.usuario = usuario;
    }

}
