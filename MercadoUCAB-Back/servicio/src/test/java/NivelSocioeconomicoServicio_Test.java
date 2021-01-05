import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.NivelSocioeconomicoServicio;

import javax.ws.rs.core.Response;

/**
 * Pruebas unitarias utilizadas para testear el servicio NivelSocioeconomicoServicio
 * @see NivelSocioeconomicoServicio Servicio a probar.
 */
public class NivelSocioeconomicoServicio_Test {

    /**
     * Prueba unitaria que testea que se puedan listar todos los niveles socioeconómicos registrados en el sistema.
     */
    @Test
    //Prueba Unitaria de la lista de Niveles Socioeconomicos
    public void getNivelesSocioeconomicosTest(){
        NivelSocioeconomicoServicio servicio = new NivelSocioeconomicoServicio();
        Response resultado = servicio.getNivelesSocioeconomicos();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }
}
