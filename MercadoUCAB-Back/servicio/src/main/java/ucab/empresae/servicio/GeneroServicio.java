package ucab.empresae.servicio;

import ucab.empresae.daos.DaoGenero;
import ucab.empresae.entidades.GeneroEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad Genero
 */

@Path("/genero")
public class GeneroServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/genero
     * Metodo con anotacion GET que devuelve todos los Generos registrados
     * @return Response con status ok con la lista de Generos.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getGeneros() {
        List<GeneroEntity> generos;
        try {
            DaoGenero dao = new DaoGenero();
            generos = dao.findAll(GeneroEntity.class);
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(generos).build();
    }
}
