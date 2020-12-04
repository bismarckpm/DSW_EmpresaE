package ucab.empresae.dtos;

public class DtoUsuario extends DtoBase {

    //Atributos
    private String estado;
    private String username;
    private String clave;
    
    //Relaciones
    private DtoTipoUsuario dtoTipoUsuario;
    private DtoCliente dtoCliente;
    private DtoEncuestado dtoEncuestado;

    //Constructores
    public DtoUsuario() {
    }

    public DtoUsuario(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public DtoTipoUsuario getDtoTipoUsuario() {
        return dtoTipoUsuario;
    }

    public void setDtoTipoUsuario(DtoTipoUsuario dtoTipoUsuario) {
        this.dtoTipoUsuario = dtoTipoUsuario;
    }

    public DtoCliente getDtoCliente() {
        return dtoCliente;
    }

    public void setDtoCliente(DtoCliente dtoCliente) {
        this.dtoCliente = dtoCliente;
    }

    public DtoEncuestado getDtoEncuestado() {
        return dtoEncuestado;
    }

    public void setDtoEncuestado(DtoEncuestado dtoEncuestado) {
        this.dtoEncuestado = dtoEncuestado;
    }

}
