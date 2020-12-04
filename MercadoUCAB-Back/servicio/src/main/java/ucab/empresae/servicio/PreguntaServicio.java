package ucab.empresae.servicio;

import ucab.empresae.daos.DaoPregunta;
import ucab.empresae.dtos.DtoPregunta;
import ucab.empresae.entidades.PreguntaEntity;
import ucab.empresae.entidades.SubcategoriaEntity;
import ucab.empresae.entidades.TipoPreguntaEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pregunta")
public class PreguntaServicio {

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getPreguntas() {
        List<PreguntaEntity> preguntas = null;
        try {
            DaoPregunta dao = new DaoPregunta();
            preguntas = dao.findAll(PreguntaEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(preguntas).build();
    }
/*
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addPregunta")
    public Response addUsuario(DtoPregunta dtoPregunta) {

        DaoPregunta dao = new DaoPregunta();
        PreguntaEntity pregunta = new PreguntaEntity();

        if(pregunta != null) {
            pregunta.setDescripcion(dtoPregunta.getDescripcion());
            pregunta.setEstado(dtoPregunta.getEstado());

            SubcategoriaEntity subcategoria = new SubcategoriaEntity(dtoPregunta.getDtoSubcategoria().getId());
            pregunta.setSubcategoria(subcategoria);
            TipoPreguntaEntity tipoPregunta = new TipoPreguntaEntity(dtoPregunta.getDtoTipoPregunta().getId());
            pregunta.setTipo(tipoPregunta);

            PreguntaEntity resul = dao.insert(pregunta);
            return Response.ok(pregunta).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Path( "/consulta" )
    public String consulta()
    {
        return "Epa";
    }*/

}
