package ucab.empresae.servicio;

import Comandos.ComandoBase;
import Comandos.ComandoFactory;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.excepciones.CategoriaException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Api service encargada de las transacciones que tiene que ver con categorias
 * @author José Prieto
 */
@Path("/categoria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaServicio extends AplicacionBase {

    private ComandoBase comando;

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/categoria
     * @apiNote Api del tipo @GET para obtener todas las categorias existentes en la base de datos
     * @since 11/01/2021
     * @return retorna una lista de objetos del tipo DtoCategoria
     */
    @GET
    public Response getCategorias() {
        try {
            this.comando = ComandoFactory.comandoGetCategoriasInstancia();
            return Response.ok(this.comando.getResult()).build();
        } catch (Exception e) {
            JsonObject excepcion = Json.createObjectBuilder()
                    .add("mensaje", e.getMessage()).build();
            return  Response.status(500).entity(excepcion).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/categoria/id
     * @apiNote  Api del tipo @GET para obtener una categoria en particular
     * @since 11/01/2021
     * @param id el id es del tipo long
     * @return retorna una categoria del tipo CategoriaEntity
     */
    @GET
    @Path("/{id}")
    public Response getCategoria(@PathParam("id") long id) {
        try {
            this.comando = ComandoFactory.comandoGetCategoriaInstancia(id);
            return Response.ok(this.comando.getResult()).build();
        } catch (Exception ex) {
            JsonObject excepcion = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage()).build();
            return  Response.status(500).entity(excepcion).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/categoria
     * @apiNote Api del tipo @POST que se encarga de guardar en base de datos una Categoria nueva
     * @since 11/01/2021
     * @param dtoCategoria obtiene un objeto del tipo DtoCategoria
     * @return retorna a la CategoriaEntity agregada en caso de que la transacción haya sido exitosa
     */
    @POST
    public Response addCategoria(DtoCategoria dtoCategoria) {
        try {
            this.comando = ComandoFactory.comandoPostCategoriaInstancia(dtoCategoria);
            return Response.ok(this.comando.getResult()).build();
        } catch (Exception e) {
            JsonObject excepcion = Json.createObjectBuilder()
                    .add("mensaje", e.getMessage()).build();
            return  Response.status(500).entity(excepcion).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/categoria/id
     * Api del tipo @PUT que se encarga de actualizar una Categoria en particular
     * @param dtoCategoria el método obtiene un DtoCategoria
     * @return retorna a la entidad persistente CategoriaEntity en caso de que la transacción se haya realizado de manera correcta
     */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategoria(DtoCategoria dtoCategoria) {
        try {
            this.comando = ComandoFactory.comandoUpdateCategoriaInctancia(dtoCategoria);
            return Response.ok(this.comando.getResult()).build();
        } catch (Exception e) {
            JsonObject excepcion = Json.createObjectBuilder()
                    .add("mensaje", e.getMessage()).build();
            return  Response.status(500).entity(excepcion).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/categoria/id
     * Api del tipo @DELETE que se encarga de eliminar de la base de datos a la entidad persistente
     * @param id obtiene un objeto del tipo DtoCategoria
     * @return Retorna un Response ok en caso de que la categoria se haya eliminado de manera correcta
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCategoria(@PathParam("id") long id) {
        try {
            this.comando = ComandoFactory.comandoDeleteCategoriaInstancia(id);
            return Response.ok(this.comando.getResult()).build();
        } catch (Exception e) {
            JsonObject excepcion = Json.createObjectBuilder()
                    .add("mensaje", e.getMessage()).build();
            return  Response.status(500).entity(excepcion).build();
        }
    }

}
