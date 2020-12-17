package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoEncuesta;
import ucab.empresae.dtos.DtoPregunta;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.PruebaExcepcion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/encuesta")
public class EncuestaServicio extends AplicacionBase{


    // BORRA LAS RELACIONES N A N ENTRE ESTUDIO Y PREGUNTA = ENCUESTA
    public void borrarPreguntasEncuesta(long id) {
        DaoEncuesta daoEncuesta = new DaoEncuesta();

        List<EncuestaEntity> preguntasEncuesta = daoEncuesta.getPreguntasEncuesta(id);

        for(EncuestaEntity relacionPreguntaEstudio : preguntasEncuesta){
            EncuestaEntity relacionEliminar = daoEncuesta.find(relacionPreguntaEstudio.get_id(), EncuestaEntity.class);
            daoEncuesta.delete(relacionEliminar);
        }

    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEncuestas() {
        List<EncuestaEntity> encuestas = null;
        try {
            DaoEncuesta dao = new DaoEncuesta();
            encuestas = dao.getEncuestas();

        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(encuestas).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEncuesta(DtoEncuesta dtoEncuesta) {

        DaoEncuesta dao = new DaoEncuesta();
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoPregunta daoPregunta = new DaoPregunta();

        EstudioEntity estudio = daoEstudio.find(dtoEncuesta.getEstudio().get_id(),EstudioEntity.class);

        if(estudio != null) {
            /*EstudioEntity estudio = daoEstudio.find(dtoEncuesta.getEstudio().get_id(),EstudioEntity.class);
            encuesta.setEstudio(estudio);
            PreguntaEntity pregunta = daoPregunta.find(dtoEncuesta.getPregunta().get_id(),PreguntaEntity.class);
            encuesta.setPregunta(pregunta);*/


            List<DtoPregunta> preguntas = dtoEncuesta.getPreguntas();

            for (DtoPregunta preguntaCiclo: preguntas) {
                //Se inserta en la n a n tantas veces como preguntas relacionadas al estudio
                EncuestaEntity encuesta = new EncuestaEntity();
                encuesta.setEstado("a");
                encuesta.setFechaInicio(dtoEncuesta.getFechaInicio());
                encuesta.setFechaFin(dtoEncuesta.getFechaFin());
                encuesta.setEstudio(estudio);

                PreguntaEntity pregunta = daoPregunta.find(preguntaCiclo.get_id(), PreguntaEntity.class);
                encuesta.setPregunta(pregunta);
                dao.insert(encuesta);
            }

            return Response.ok().entity(preguntas).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Path("/preguntasEncuesta/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response deletePreguntasEncuesta(@PathParam("id") long id) {

        DaoEstudio daoEstudio = new DaoEstudio();
        EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);

        if (estudio != null) {
            this.borrarPreguntasEncuesta(id);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
