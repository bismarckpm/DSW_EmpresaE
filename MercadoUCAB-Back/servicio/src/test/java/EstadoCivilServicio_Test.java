import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.EstadoCivilServicio;

import javax.ws.rs.core.Response;

/**
 * Prueba unitaria que testea a EstadoCivilServicio.
 * @see EstadoCivilServicio Servicio a probar.
 */
public class EstadoCivilServicio_Test {

    /**
     * Prueba unitaria que testea que se puedan listar todas las marcar listadas en sistema.
     */
    @Test
    //Prueba Unitaria de la lista de Estados Civiles
    public void getMarcasTest(){
        EstadoCivilServicio servicio = new EstadoCivilServicio();
        Response resultado = servicio.getEstadosCiviles();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }
}
