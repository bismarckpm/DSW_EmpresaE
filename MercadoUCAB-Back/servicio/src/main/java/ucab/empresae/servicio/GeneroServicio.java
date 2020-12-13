package ucab.empresae.servicio;

import ucab.empresae.daos.DaoGenero;
import ucab.empresae.entidades.GeneroEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/genero")
public class GeneroServicio {

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getGeneros() {
        List<GeneroEntity> generos = null;
        try {
            DaoGenero dao = new DaoGenero();
            generos = dao.findAll(GeneroEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(generos).build();
    }
}
