import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.MedioConexionServicio;
import javax.ws.rs.core.Response;

public class MedioConexionServicio_Test {

    @Test
    //Prueba Unitaria de la lista de Medios de Conexion
    public void getMediosDeConexionTest(){
        MedioConexionServicio servicio = new MedioConexionServicio();
        Response resultado = servicio.getMediosConexion();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }
}
