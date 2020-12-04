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
}
