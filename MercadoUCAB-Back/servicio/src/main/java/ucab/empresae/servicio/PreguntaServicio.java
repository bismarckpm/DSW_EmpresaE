package ucab.empresae.servicio;

import ucab.empresae.daos.DaoPregunta;
import ucab.empresae.daos.DaoSubcategoria;
import ucab.empresae.daos.DaoTipoPregunta;
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPregunta(DtoPregunta dtoPregunta) {

        DaoPregunta dao = new DaoPregunta();
        DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();

        PreguntaEntity pregunta = new PreguntaEntity();

        if(pregunta != null) {
            pregunta.setDescripcion(dtoPregunta.getDescripcion());
            pregunta.setEstado(dtoPregunta.getEstado());

            SubcategoriaEntity subcategoria = daoSubcategoria.find(dtoPregunta.getSubcategoria().getId(), SubcategoriaEntity.class);
            pregunta.setSubcategoria(subcategoria);
            TipoPreguntaEntity tipoPregunta = daoTipoPregunta.find(dtoPregunta.getTipo().getId(), TipoPreguntaEntity.class);
            pregunta.setTipo(tipoPregunta);

            PreguntaEntity resul = dao.insert(pregunta);
            return Response.ok().entity(resul).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response deletePregunta(@PathParam("id") long id) {

        DaoPregunta dao = new DaoPregunta();
        PreguntaEntity pregunta = dao.find(id, PreguntaEntity.class);
        if (pregunta != null) {
            PreguntaEntity resul = dao.delete(pregunta);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePregunta(@PathParam("id") long id, DtoPregunta dtoPregunta) {

        DaoPregunta dao = new DaoPregunta();
        PreguntaEntity pregunta = dao.find(id, PreguntaEntity.class);

        if(pregunta != null) {
            pregunta.setEstado(dtoPregunta.getEstado());
            pregunta.setDescripcion(dtoPregunta.getDescripcion());

            SubcategoriaEntity subcategoria = new SubcategoriaEntity(dtoPregunta.getSubcategoria().getId());
            pregunta.setSubcategoria(subcategoria);
            TipoPreguntaEntity tipoPregunta = new TipoPreguntaEntity(dtoPregunta.getTipo().getId());
            pregunta.setTipo(tipoPregunta);

            PreguntaEntity resul = dao.update(pregunta);
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
    }

}
