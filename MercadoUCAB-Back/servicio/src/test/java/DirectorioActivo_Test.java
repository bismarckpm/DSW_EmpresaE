import org.junit.Test;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.servicio.DirectorioActivo;


public class DirectorioActivo_Test {

    public String rol;

    @Test
    public void createUserLDAP() {
        DtoUsuario user = new DtoUsuario();
        rol = "Analista";
        user.setUsername( "prueba15" );
        user.setClave( "Prueba654321" );
        user.setCorreoelectronico("prueba@gmail.com");
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.addEntryToLdap( user, rol );
    }

    @Test
    public void getUserLDAP() {
        DtoUsuario user = new DtoUsuario();
        user.setUsername( "prueba15" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.getCorreo( user );
    }

    @Test
    public void changePassword() {
        DtoUsuario user = new DtoUsuario();
        user.setUsername( "prueba15" );
        user.setClave( "123456Prueba" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.changePassword( user );
    }

    @Test
    public void updateUserLDAP(){
        DtoUsuario user = new DtoUsuario();
        rol = "Encuestado";
        user.setUsername( "prueba15" );
        user.setClave( "Prueba654321" );
        user.setCorreoelectronico("updatePrueba222@gmail.com");
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.updateEntry(user, rol);
    }

    @Test
    public void userAuthentication() {
        DtoUsuario user = new DtoUsuario();
        user.setUsername( "prueba15" );
        user.setClave( "123456Prueba" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.userAuthentication( user );
    }

    @Test
    public void deleteUserLDAP() {
        DtoUsuario user = new DtoUsuario();
        user.setUsername( "prueba15" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.deleteEntry( user );
    }
}
