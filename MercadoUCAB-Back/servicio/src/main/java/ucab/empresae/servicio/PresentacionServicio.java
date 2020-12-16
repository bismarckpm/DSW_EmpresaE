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
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(presentaciones).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addPresentacion")
    public Response addPresentacion(DtoPresentacion dtoPresentacion) {

        DaoPresentacion dao = new DaoPresentacion();
        PresentacionEntity presentacion = new PresentacionEntity();

        if(presentacion != null) {
            presentacion.setDescripcion(dtoPresentacion.getDescripcion());
            presentacion.setEstado(dtoPresentacion.getEstado());

            PresentacionEntity resul = dao.insert(presentacion);
            return Response.ok(presentacion).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response deletePresentacion(@PathParam("id") long id) {

        DaoPresentacion dao = new DaoPresentacion();
        PresentacionEntity presentacion = dao.find(id, PresentacionEntity.class);
        if (presentacion != null) {
            PresentacionEntity resul = dao.delete(presentacion);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePresentacion(@PathParam("id") long id, DtoPresentacion dtoPresentacion) {

        DaoPresentacion dao = new DaoPresentacion();
        PresentacionEntity presentacion = dao.find(id, PresentacionEntity.class);

        if(presentacion != null) {
            presentacion.setEstado(dtoPresentacion.getEstado());
            presentacion.setDescripcion(dtoPresentacion.getDescripcion());

            PresentacionEntity resul = dao.update(presentacion);
            return Response.ok(presentacion).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
}
