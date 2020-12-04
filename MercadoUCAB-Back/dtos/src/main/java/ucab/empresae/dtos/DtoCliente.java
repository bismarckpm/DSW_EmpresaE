package ucab.empresae.dtos;

public class DtoCliente extends DtoBase{

    //Atributos
    private String estado;
    private String razonSocial;
    private int rif;

    //Relaciones
    private DtoLugar dtoLugar;
    private DtoUsuario dtoUsuario;

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
        return dtoLugar;
    }

    public void setLugar(DtoLugar lugar) {
        this.dtoLugar = lugar;
    }

    public DtoUsuario getUsuario() {
        return dtoUsuario;
    }

    public void setUsuario(DtoUsuario usuario) {
        this.dtoUsuario = usuario;
    }

}
