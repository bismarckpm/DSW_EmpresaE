import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.GeneroServicio;

import javax.ws.rs.core.Response;

/**
 * Pruebas unitarias utilizadas para testear al servicio GeneroServicio.
 * @see GeneroServicio Servicio a probar.
 */
public class GeneroServicio_Test {

    /**
     * Prueba unitaria utilizada para testear que se puedan listar todos los generos registrados en la base de datos.
     */
    @Test
    //Prueba Unitaria de la lista de Generos
    public void getGenerosTest(){
        GeneroServicio servicio = new GeneroServicio();
        Response resultado = servicio.getGeneros();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }
}
