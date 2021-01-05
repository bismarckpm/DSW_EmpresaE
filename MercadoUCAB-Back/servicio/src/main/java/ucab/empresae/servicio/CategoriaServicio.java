package ucab.empresae.servicio;
/**
 * Api Services necesarias para las categorias
 */

import ucab.empresae.daos.DaoCategoria;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.entidades.EntidadesFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Api service encargada de las transacciones que tiene que ver con categorias
 * @author José Prieto
 */
@Path("/categoria")
public class CategoriaServicio extends AplicacionBase {



    private DaoCategoria dao = DaoFactory.DaoCategoriaInstancia();
    private CategoriaEntity categoria = EntidadesFactory.CategoriaInstance();

    /**
     * Método que convierte los atributos del .json a los correspondientes de la clase persistente del tipo Categoria
     * @param dtoCategoria del tipo Dto categoria dentro del empaquetado de dtos
     */
    private void categoriAtributos(DtoCategoria dtoCategoria) {
        this.categoria.setNombre(dtoCategoria.getNombre());
        this.categoria.setEstado(dtoCategoria.getEstado());
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/categoria
     * Api del tipo @GET para obtener todas las categorias existentes en la base de datos
     * @return retorna una lista de Categorias del tipo CategoriaEntity
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getCategorias() {
        try {
            return Response.ok(this.dao.findAll(CategoriaEntity.class)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/categoria/id
     * Api del tipo @GET para obtener una categoria en particular
     * @param id el id es del tipo long
     * @return retorna una categoria del tipo CategoriaEntity
     */
    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getCategoria(@PathParam("id") long id) {
        try {
            return Response.ok(this.dao.find(id, CategoriaEntity.class)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/categoria
     * Api del tipo @POST que se encarga de guardar en base de datos una Categoria nueva
     * @param dtoCategoria obtiene un objeto del tipo DtoCategoria
     * @return retorna a la CategoriaEntity agregada en caso de que la transacción haya sido exitosa
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategoria(DtoCategoria dtoCategoria) {
        try {
            categoriAtributos(dtoCategoria);
            return Response.ok(this.dao.insert(this.categoria)).build();
        } catch(Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
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
            this.categoria = this.dao.find(dtoCategoria.get_id(), CategoriaEntity.class);
            categoriAtributos(dtoCategoria);
            return Response.ok(this.dao.update(this.categoria)).build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
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
            this.categoria = this.dao.find(id, CategoriaEntity.class);
            return Response.ok(this.dao.delete(this.categoria)).build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(ex).build();
        }
    }

}
