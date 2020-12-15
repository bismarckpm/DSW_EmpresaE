package ucab.empresae.dtos;

public class DtoCliente extends DtoBase{

    //Atributos
    private String estado;
    private String razonSocial;
    private String rif;

    //Relaciones
    private DtoLugar lugar;
    private DtoUsuario usuario;
    private DtoTelefono telefono;


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

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
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

    public DtoTelefono getTelefono() {
        return telefono;
    }

    public void setTelefono(DtoTelefono telefono) {
        this.telefono = telefono;
    }
}
