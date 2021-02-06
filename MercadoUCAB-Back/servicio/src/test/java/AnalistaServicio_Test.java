import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.AnalistaServicio;
import javax.ws.rs.core.Response;

/**
 * Prueba unitaria utilizada para probar el servicio de analista.
 * @see AnalistaServicio Servicio a probar.
 */
public class AnalistaServicio_Test {

    /**
     * Prueba unitaria que prueba listar todos los analistas guardados en la base de datos.
     */
    @Test
    //Prueba Unitaria de la lista de Analistas
    public void getAnalistasTest(){
        AnalistaServicio servicio = new AnalistaServicio();
        Response resultado = servicio.getAnalistas();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria que prueba que se puede obtener un analita por medio de su id.
     */
    @Test
    //Prueba Unitaria de la lista de Estudios de un Analista
    public void getAnalistaTest(){
        AnalistaServicio servicio = new AnalistaServicio();
        Response resultado = servicio.getAnalista(3);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

}
