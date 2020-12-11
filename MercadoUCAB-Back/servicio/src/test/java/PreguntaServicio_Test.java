import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.DtoPregunta;
import ucab.empresae.dtos.DtoSubcategoria;
import ucab.empresae.dtos.DtoTipoPregunta;
import ucab.empresae.servicio.PreguntaServicio;

import javax.ws.rs.core.Response;

public class PreguntaServicio_Test {

    @Test
    public void getPreguntasTest() throws Exception
    {
        PreguntaServicio servicio = new PreguntaServicio();
        Response respuesta = servicio.getPreguntas();
        Assert.assertEquals( respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void addPreguntaTest() throws Exception {
        PreguntaServicio servicio = new PreguntaServicio();
        DtoPregunta dtoPregunta = new DtoPregunta();

        dtoPregunta.setDescripcion("TestPregunta");
        dtoPregunta.setEstado("a");

        DtoTipoPregunta tipoPregunta = new DtoTipoPregunta(1);
        dtoPregunta.setTipo(tipoPregunta);
        DtoSubcategoria subcategoria = new DtoSubcategoria(1);
        dtoPregunta.setSubcategoria(subcategoria);

        Response respuesta = servicio.addPregunta(dtoPregunta);
        Assert.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void deletePreguntaTest() throws Exception
    {
        PreguntaServicio servicio = new PreguntaServicio();
        Response respuesta = servicio.deletePregunta(5);
        Assert.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void updatePreguntaTest() throws Exception {
        PreguntaServicio servicio = new PreguntaServicio();
        DtoPregunta dtoPregunta = new DtoPregunta();

        dtoPregunta.setDescripcion("TestPreguntaUPDATE");
        dtoPregunta.setEstado("a");

        DtoTipoPregunta tipoPregunta = new DtoTipoPregunta(2);
        dtoPregunta.setTipo(tipoPregunta);
        DtoSubcategoria subcategoria = new DtoSubcategoria(2);
        dtoPregunta.setSubcategoria(subcategoria);

        Response respuesta = servicio.updatePregunta(3,dtoPregunta);
        Assert.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }


}
