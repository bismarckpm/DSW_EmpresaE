package ucab.empresae.servicio;

import Comandos.ComandoBase;
import Comandos.ComandoFactory;
import ucab.empresae.daos.DaoLugar;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.LugarEntity;
import ucab.empresae.excepciones.CustomException;

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

    private ComandoBase comando;
    private DtoResponse response;

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/lugar
     * Metodo con anotacion GET que devuelve todos los lugares de tipo Estado registrados
     * @return Response con status ok con la lista de Estados.
     */
    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    public Response getLugares(){
        try {
            this.comando = ComandoFactory.comandoGetLugaresInstancia();
            return Response.ok(this.comando.getResult()).build();
        } catch (CustomException cex){
            this.response.setEstado("ERROR");
            this.response.setMensaje(cex.getMensaje());
            this.response.setCodError(cex.getCodError());
            return Response.status(500).entity(this.response).build();
        } catch (Exception ex) {
            this.response.setEstado("ERROR");
            this.response.setMensaje(ex.getMessage());
            this.response.setCodError(ex.getClass().toString());
            return Response.status(500).entity(this.response).build();
        }
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
        try {
            this.comando = ComandoFactory.comandoGetLugarInstancia(id);
            return Response.ok(this.comando.getResult()).build();
        } catch (CustomException cex){
            this.response.setEstado("ERROR");
            this.response.setMensaje(cex.getMensaje());
            this.response.setCodError(cex.getCodError());
            return Response.status(500).entity(this.response).build();
        } catch (Exception ex) {
            this.response.setEstado("ERROR");
            this.response.setMensaje(ex.getMessage());
            this.response.setCodError(ex.getClass().toString());
            return Response.status(500).entity(this.response).build();
        }
    }

}
