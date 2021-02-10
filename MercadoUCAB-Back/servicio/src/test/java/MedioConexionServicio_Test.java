import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.MedioConexionServicio;

import javax.ws.rs.core.Response;

/**
 * Prueba unitaria utilizada para testar el funcionamiento MedioConexioServicio
 * @see MedioConexionServicio Servicip a probar
 */
public class MedioConexionServicio_Test {

    /**
     * Prueba unitaria utilizada para probar que se puedan listar todos los medios de conexión registrados en la base de datos.
     */
    @Test
    //Prueba Unitaria de la lista de Medios de Conexion
    public void getMediosDeConexionTest(){
        MedioConexionServicio servicio = new MedioConexionServicio();
        Response resultado = servicio.getMediosConexion();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }
}
