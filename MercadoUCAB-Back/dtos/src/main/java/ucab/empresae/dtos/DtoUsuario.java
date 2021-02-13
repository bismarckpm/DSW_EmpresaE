package ucab.empresae.dtos;

public class DtoUsuario extends DtoBase {

    //Atributos
    private String estado;
    private String username;
    private String clave;
    private String correoelectronico;
    private String nuevaClave;
    
    //Relaciones
    private DtoTipoUsuario tipoUsuario;
    private DtoCliente cliente;
    private DtoEncuestado encuestado;

    //Constructores
    public DtoUsuario() {
        super();
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

    public String getCorreoelectronico(){return correoelectronico;}

    public void setCorreoelectronico(String correoelectronico){this.correoelectronico = correoelectronico;}

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public DtoTipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(DtoTipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public DtoCliente getCliente() {
        return cliente;
    }

    public void setCliente(DtoCliente cliente) {
        this.cliente = cliente;
    }

    public DtoEncuestado getEncuestado() {
        return encuestado;
    }

    public void setEncuestado(DtoEncuestado encuestado) {
        this.encuestado = encuestado;
    }
}
