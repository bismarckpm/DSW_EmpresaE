import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.DtoTipoUsuario;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.excepciones.PruebaExcepcion;
import ucab.empresae.servicio.UsuarioServicio;
import javax.ws.rs.core.Response;
import java.text.ParseException;


public class UsuarioServicio_Test {

    @Test
    //Prueba Unitaria del registro de empleados
    public void addUsuarioEmpleadoTest(){
        UsuarioServicio servicio = new UsuarioServicio();

        DtoTipoUsuario dtoTipoUsuario = new DtoTipoUsuario();
        dtoTipoUsuario.setEstado("a");
        dtoTipoUsuario.setDescripcion("Administrador"); //Solo permite Administardor o Analista

        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setEstado("a");
        dtoUsuario.setUsername("pablitoprueba");
        dtoUsuario.setCorreoelectronico("pablito@gmail.com");
        dtoUsuario.setClave("pablitoprueba");
        dtoUsuario.setTipoUsuario(dtoTipoUsuario);

        Response resultado = servicio.addUsuarioEmpleado(dtoUsuario);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    //Prueba unitaria del getUsuario (Retorna todos los datos de un usuario sea cual sea su tipo de usuario)
    public void getUsuarioTest() throws ParseException, PruebaExcepcion {
        UsuarioServicio servicio = new UsuarioServicio();
        Response resultado = servicio.getUsuario(66);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    //Prueba unitaria de la lista de empleados registrados
    public void getEmpleadosTest(){
        UsuarioServicio servicio = new UsuarioServicio();
        Response resultado = servicio.getEmpleados();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    //Prueba Unitaria de la modificacion de usuarios, se necesita pasar el id y el username
    public void updateUsuarioVistaAdminTest(){
        UsuarioServicio servicio = new UsuarioServicio();

        DtoTipoUsuario dtoTipoUsuario = new DtoTipoUsuario();
        dtoTipoUsuario.setDescripcion("Analista"); //Solo permite Administrador o Analista

        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setEstado("a");
        dtoUsuario.setUsername("pablitoprueba"); //No se modifica, solo se necesita para buscarlo en el ldap
        dtoUsuario.setTipoUsuario(dtoTipoUsuario);

        Response resultado = servicio.updateUsuarioVistaAdmin(66, dtoUsuario);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void updateUsuarioVistaPerfilTest(){

    }

    @Test
    //Prueba Unitaria de la eliminacion de usuarios
    public void deleteUsuarioTest(){
        UsuarioServicio servicio = new UsuarioServicio();
        Response resultado = servicio.deleteUsuario(66);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

}