package ucab.empresae.servicio;

import ucab.empresae.daos.DaoLugar;
import ucab.empresae.entidades.LugarEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad Lugar
 */

@Path("/lugar")
public class LugarServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/lugar
     * Metodo con anotacion GET que devuelve todos los lugares de tipo Estado registrados
     * @return Response con status ok con la lista de Estados.
     */
    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    public Response getLugares(){

        List<LugarEntity> lugares;
        try {
            DaoLugar dao = new DaoLugar();
            lugares = dao.getLugaresByTipo("estado");
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(lugares).build();
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/usuario/id
     * Metodo con anotacion GET que devuelve todos los lugares relacionados al lugar recibido a partir de su id
     * @param id identificador del lugar del que se quieren obtener todos sus lugares asociados
     * @return Response con status ok junto a la lista de lugares relacionados al recibido por parametro
     */
    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getLugar(@PathParam("id") long id) {

        List<LugarEntity> resultado;
        DaoLugar dao = new DaoLugar();

        try{
            LugarEntity lugar = dao.find(id, LugarEntity.class);
            if(lugar != null) {
                resultado = dao.getLugaresById(lugar);
                return Response.ok(resultado).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

}
