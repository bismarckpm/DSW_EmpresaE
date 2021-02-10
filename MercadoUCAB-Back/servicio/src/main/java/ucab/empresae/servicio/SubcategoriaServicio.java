package ucab.empresae.servicio;

import Comandos.ComandoBase;
import Comandos.ComandoFactory;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.dtos.DtoSubcategoria;
import ucab.empresae.excepciones.CustomException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author José Prieto
 * Api encargada de las transacciones con respecto a las subcategorias de la base de datos
 */
@Path("/subcategoria")
public class SubcategoriaServicio extends AplicacionBase {

    private ComandoBase comando;
    private final DtoResponse response = DtoFactory.DtoResponseInstance();

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/subcategoria
     * Api del tipo @GET que se encarga de retornas todas las subcategorias existentes en la base de datos del sistema.
     * @return retorna un Response .json con una lista de objetos del tipo SubcategoriaEntity
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getSubCategorias() {
        try {
            this.comando = ComandoFactory.comandoGetSubcategoriasInstancia();
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
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/subcategoria/id
     * Api del tipo @GET que se encarga de retornar una subcategoria en particular
     * @param id recibe el id de la subcategoria en tipo long
     * @return retorna un Response .json con un objeto del tipo SubcategoriaEntity
     */
    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getSubCategoria(@PathParam("id") long id) {
        try {
            this.comando = ComandoFactory.comandoGetSubcategoriaInstancia(id);
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
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/subcategoria
     * Api del tipo @POST que se encarga de guardar en la base de datos una Subcategoria nueva
     * @param dtoSubcategoria recube un objeto del tipo DtoSubcategoria
     * @return retorna Response con el objeto del tipo SubcategoriaEntity ya agregado al sistema en caso
     * de que la transaccion haya sido exitosa
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSubcategoria(DtoSubcategoria dtoSubcategoria) {
        try {
            this.comando = ComandoFactory.comandoAddSubcategoriaInstancia(dtoSubcategoria);
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
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/subcategoria/id
     * Api del tipo @PUT que se encarga de actualizar la información de un objeto del tipo SubcategoriaEntity
     * @param dtoSubcategoria recibe un objeto del tipo DtoSubcategoria con los datos nuevos
     * @return retorna Response .json con objeto del tipo SubcategoriaEntity con los datos ya modificados
     */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSubcategoria(DtoSubcategoria dtoSubcategoria) {
        try {
            this.comando = ComandoFactory.comandoUpdateSubcategoria(dtoSubcategoria);
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
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/subcategoria/id
     * Api que se encarga de eliminar un registro en la base de datos en la tabla Subcategoria
     * @param id recibe un parámetro del tipo long con el id de la subcategoria a eliminar
     * @return retorna un Reponse .json con un objeto del tipo Subcategoria eliminado
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteSubcategoria(@PathParam("id") long id) {
        try {
            this.comando = ComandoFactory.comandoDeleteSubcategoriaInstancia(id);
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
