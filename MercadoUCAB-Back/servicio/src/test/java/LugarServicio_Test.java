import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.LugarServicio;

import javax.ws.rs.core.Response;

/**
 * Pruebas unitarias utilizadas para pribar el funcionamiento de LugarServicio
 * @see LugarServicio Servicio a testear.
 */
public class LugarServicio_Test {

    /**
     * Prueba unitaria utilizada para testear que se puedan listar todos los lugares registrados en el sistema.
     */
    @Test
    //Prueba Unitaria de la lista de Lugares de Tipo Estado
    public void getLugaresTest(){
        LugarServicio servicio = new LugarServicio();
        Response resultado = servicio.getLugares();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria utilizada para obtener un lugar en específico registrado en la base de datos.
     */
    @Test
    //Prueba Unitaria consulta de un Lugar
    public void getLugarTest(){
        LugarServicio servicio = new LugarServicio();
        Response resultado = servicio.getLugar(323);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }
}
