import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.DtoMarca;
import ucab.empresae.dtos.DtoPresentacion;
import ucab.empresae.servicio.MarcaServicio;
import ucab.empresae.servicio.PresentacionServicio;

import javax.ws.rs.core.Response;

public class PresentacionServicio_Test {
    @Test
    public void getPresentacionTest() throws Exception{
        PresentacionServicio servicio = new PresentacionServicio();
        Response resultado = servicio.getPresentaciones();
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void addPresentacionTest() throws Exception{
        PresentacionServicio servicio = new PresentacionServicio();
        DtoPresentacion dtoPresentacion = new DtoPresentacion();
        dtoPresentacion.setDescripcion("Espuma");
        dtoPresentacion.setEstado("a");
        Response resultado = servicio.addPresentacion(dtoPresentacion);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void updatePresentacionTest() throws Exception{
        PresentacionServicio servicio = new PresentacionServicio();
        DtoPresentacion dtoPresentacion = new DtoPresentacion();
        dtoPresentacion.setDescripcion("EspumaUpdate");
        dtoPresentacion.setEstado("a");
        Response resultado = servicio.updatePresentacion(22, dtoPresentacion);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void deletePresentacionTest() throws Exception{
        PresentacionServicio servicio = new PresentacionServicio();
        Response resultado = servicio.deletePresentacion(22);
        Assert.assertEquals(resultado.getStatus(), Response.Status.OK.getStatusCode());
    }
}
