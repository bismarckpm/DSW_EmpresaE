import org.junit.Test;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.servicio.DirectorioActivo;

/**
 * Pruebas unitarias utilizadas para probar el directorio activo de la base de datos.
 * @see DirectorioActivo Clase a probar.
 */
public class DirectorioActivo_Test {

    public String rol;

    /**
     * Prueba unitaria utilizada para crear un usuario a nivel del LDAP.
     */
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

    /**
     * Prueba unitaria utilizada para obtener un usuario del LDAP.
     */
    @Test
    public void getUserLDAP() {
        DtoUsuario user = new DtoUsuario();
        user.setUsername( "prueba15" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.getCorreo( user );
    }

    /**
     * Prueba unitaria utilizada para probar el cambio de la contraseña de algún usuario.
     */
    @Test
    public void changePassword() {
        DtoUsuario user = new DtoUsuario();
        user.setUsername( "prueba15" );
        user.setClave( "123456Prueba" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.changePassword( user );
    }

    /**
     * Prueba unitatia utilizada para actualizar los datos de un usuario a nivel del LDAP.
     */
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

    /**
     * Prueba unitaria utilizada para autenticar un usuario a nivel del LDAP.
     */
    @Test
    public void userAuthentication() {
        DtoUsuario user = new DtoUsuario();
        user.setUsername( "prueba15" );
        user.setClave( "123456Prueba" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.userAuthentication( user );
    }

    /**
     * Prueba unitaria utilizada para borrar un usuario a nivel del LDAP.
     */
    @Test
    public void deleteUserLDAP() {
        DtoUsuario user = new DtoUsuario();
        user.setUsername( "prueba15" );
        DirectorioActivo ldap = new DirectorioActivo();
        ldap.deleteEntry( user );
    }
}
