package ucab.empresae.servicio;

import ucab.empresae.daos.DaoEncuesta;
import ucab.empresae.daos.DaoEstudio;
import ucab.empresae.daos.DaoPregunta;
import ucab.empresae.dtos.DtoEncuesta;
import ucab.empresae.entidades.EncuestaEntity;
import ucab.empresae.entidades.EstudioEntity;
import ucab.empresae.entidades.PreguntaEntity;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/encuesta")
public class EncuestaServicio extends AplicacionBase{

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEncuesta(DtoEncuesta dtoEncuesta) {

        DaoEncuesta dao = new DaoEncuesta();
        EncuestaEntity encuesta = new EncuestaEntity();

        DaoEstudio daoEstudio = new DaoEstudio();
        DaoPregunta daoPregunta = new DaoPregunta();

        if(encuesta != null) {

            encuesta.setEstado("a");
            encuesta.setFechaInicio(dtoEncuesta.getFechaInicio());
            encuesta.setFechaFin(dtoEncuesta.getFechaFin());

            EstudioEntity estudio = daoEstudio.find(dtoEncuesta.getEstudio().getId(),EstudioEntity.class);
            encuesta.setEstudio(estudio);

            PreguntaEntity pregunta = daoPregunta.find(dtoEncuesta.getPregunta().getId(),PreguntaEntity.class);
            encuesta.setPregunta(pregunta);

            /*
            presentacion.setDescripcion(dtoPresentacion.getDescripcion());
            presentacion.setEstado(dtoPresentacion.getEstado());

            PresentacionEntity resul = dao.insert(presentacion);
            return Response.ok(presentacion).build();*/

            EncuestaEntity resultado = dao.insert(encuesta);
            return Response.ok().entity(resultado).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
}
