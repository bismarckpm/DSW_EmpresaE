package ucab.empresae.servicio;

import ucab.empresae.daos.DaoPresentacion;
import ucab.empresae.dtos.DtoPresentacion;
import ucab.empresae.entidades.PresentacionEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/presentacion")
public class PresentacionServicio {
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getPresentaciones() {
        List<PresentacionEntity> presentaciones = null;
        try {
            DaoPresentacion dao = new DaoPresentacion();
            presentaciones = dao.findAll(PresentacionEntity.class);
            return Response.ok(presentaciones).build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPresentacion(DtoPresentacion dtoPresentacion) {

        DaoPresentacion dao = new DaoPresentacion();
        PresentacionEntity presentacion = new PresentacionEntity();

        try{
            presentacion.setDescripcion(dtoPresentacion.getDescripcion());
            presentacion.setEstado(dtoPresentacion.getEstado());

            PresentacionEntity resul = dao.insert(presentacion);
            return Response.ok(presentacion).build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }

    }

    @DELETE
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response deletePresentacion(@PathParam("id") long id) {

        DaoPresentacion dao = new DaoPresentacion();
        PresentacionEntity presentacion = null;

        try{
            presentacion = dao.find(id, PresentacionEntity.class);
            PresentacionEntity resul = dao.delete(presentacion);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }

    }

    @PUT
    @Path("/{id}")
    public Response updatePresentacion(@PathParam("id") long id, DtoPresentacion dtoPresentacion) {

        DaoPresentacion dao = new DaoPresentacion();
        PresentacionEntity presentacion = null;

        try {
            presentacion = dao.find(id, PresentacionEntity.class);
            presentacion.setEstado(dtoPresentacion.getEstado());
            presentacion.setDescripcion(dtoPresentacion.getDescripcion());

            PresentacionEntity resul = dao.update(presentacion);
            return Response.ok(presentacion).build();

        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }
}
