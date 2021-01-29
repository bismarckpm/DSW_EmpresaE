import org.junit.Assert;
import org.junit.Test;
import ucab.empresae.dtos.*;
import ucab.empresae.servicio.EncuestaServicio;
import ucab.empresae.servicio.PreguntaServicio;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class EncuestaServicio_Test {
    @Test
    public void getPreguntasTest() throws Exception
    {
        EncuestaServicio servicio = new EncuestaServicio();
        Response respuesta = servicio.getEncuestas();
        Assert.assertEquals( respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void addEncuestaTest() throws Exception {
        EncuestaServicio servicio = new EncuestaServicio();
        DtoEncuesta dtoEncuesta = new DtoEncuesta();
        List<DtoPregunta> preguntas = new ArrayList<DtoPregunta>();

        DtoPregunta pregunta = new DtoPregunta(27);
        preguntas.add(pregunta);
        DtoPregunta pregunta2 = new DtoPregunta(28);
        preguntas.add(pregunta2);
        DtoPregunta pregunta3 = new DtoPregunta(29);
        preguntas.add(pregunta3);
        dtoEncuesta.setPreguntas(preguntas);

        dtoEncuesta.setFechaInicio("2020-05-05");
        dtoEncuesta.setFechaFin("2020-05-08");
        dtoEncuesta.setEstado("a");
        DtoEstudio dtoEstudio = new DtoEstudio(14);
        dtoEncuesta.setEstudio(dtoEstudio);

        Response respuesta = servicio.addEncuesta(dtoEncuesta);
        Assert.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void deleteEncuestaTest() throws Exception
    {
        EncuestaServicio servicio = new EncuestaServicio();
        Response respuesta = servicio.deletePreguntasEncuesta(14);
        Assert.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }
}
