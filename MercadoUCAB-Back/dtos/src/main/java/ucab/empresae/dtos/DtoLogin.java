package ucab.empresae.dtos;

public class DtoLogin extends DtoBase{

    //Atributos
    private String username;
    private String rol;

    //Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
