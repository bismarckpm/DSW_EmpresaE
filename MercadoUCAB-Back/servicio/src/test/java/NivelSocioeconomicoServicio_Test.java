import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.servicio.NivelSocioeconomicoServicio;

import javax.ws.rs.core.Response;

public class NivelSocioeconomicoServicio_Test {

    @Test
    //Prueba Unitaria de la lista de Niveles Socioeconomicos
    public void getNivelesSocioeconomicosTest(){
        NivelSocioeconomicoServicio servicio = new NivelSocioeconomicoServicio();
        Response resultado = servicio.getNivelesSocioeconomicos();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());

    }
}
