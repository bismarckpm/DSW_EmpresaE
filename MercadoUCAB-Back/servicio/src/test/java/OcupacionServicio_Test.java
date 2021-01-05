import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.OcupacionServicio;
import javax.ws.rs.core.Response;

/**
 * Pruebas unitarias para testear el funcionamiento del servicio OcupacionServicio
 * @see OcupacionServicio Servicio a probar.
 */
public class OcupacionServicio_Test {

    /**
     * Prueba unitaria que testea que se puedan listar todas las ocupaciones registradas en el sistema.
     */
    @Test
    //Prueba Unitaria de la lista de Ocupaciones
    public void getOcupacionesTest(){
        OcupacionServicio servicio = new OcupacionServicio();
        Response resultado = servicio.getOcupaciones();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }
}
