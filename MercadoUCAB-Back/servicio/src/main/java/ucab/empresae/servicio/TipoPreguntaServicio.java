package ucab.empresae.servicio;


import ucab.empresae.daos.DaoTipoPregunta;
import ucab.empresae.entidades.TipoPreguntaEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tipopregunta")
public class TipoPreguntaServicio {
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getPresentaciones() {
        List<TipoPreguntaEntity> tipospreguntas = null;
        try {
            DaoTipoPregunta dao = new DaoTipoPregunta();
            tipospreguntas = dao.findAll(TipoPreguntaEntity.class);
            return Response.ok(tipospreguntas).build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }
}
