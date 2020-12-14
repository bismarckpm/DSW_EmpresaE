package ucab.empresae.servicio;

import ucab.empresae.daos.DaoEncuesta;
import ucab.empresae.daos.DaoPresentacion;
import ucab.empresae.dtos.DtoEncuesta;
import ucab.empresae.entidades.EncuestaEntity;

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
    public Response addPresentacion(DtoEncuesta dtoEncuesta) {

        DaoEncuesta dao = new DaoEncuesta();
        EncuestaEntity encuesta = new EncuestaEntity();

        if(encuesta != null) {/*
            presentacion.setDescripcion(dtoPresentacion.getDescripcion());
            presentacion.setEstado(dtoPresentacion.getEstado());

            PresentacionEntity resul = dao.insert(presentacion);
            return Response.ok(presentacion).build();*/
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
}
