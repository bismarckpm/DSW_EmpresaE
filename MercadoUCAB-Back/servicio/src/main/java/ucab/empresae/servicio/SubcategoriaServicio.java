package ucab.empresae.servicio;
/**
 * Api encargada de las transacciones con respecto a las subcategorias de la base de datos
 */

import ucab.empresae.daos.DaoCategoria;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoSubcategoria;
import ucab.empresae.dtos.DtoSubcategoria;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.entidades.EntidadesFactory;
import ucab.empresae.entidades.SubcategoriaEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/subcategoria")
public class SubcategoriaServicio extends AplicacionBase {

    private DaoSubcategoria dao = DaoFactory.DaoSubcategoriaInstancia();
    private SubcategoriaEntity subcategoria = EntidadesFactory.SubcategoriaInstance();

    /**
     * Metodo con la funcion de convertir los atributos del objeto DtoSubcategoria recibido a los atributos de la
     * entidad persistente SubcategoriaEntity
     * @param dtoSubcategoria recibe un objeto del tipo DtoSubcategoria
     */
    private void subcategoriaAtributos(DtoSubcategoria dtoSubcategoria) {
        this.subcategoria.setNombre(dtoSubcategoria.getNombre());
        this.subcategoria.setEstado(dtoSubcategoria.getEstado());

        if(dtoSubcategoria.getCategoria() != null) {
            DaoCategoria daoCategoria = DaoFactory.DaoCategoriaInstancia();
            this.subcategoria.setCategoria(daoCategoria.find(dtoSubcategoria.getCategoria().get_id(), CategoriaEntity.class));
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/subcategoria
     * Api del tipo @GET que se encarga de retornas todas las subcategorias existentes en la base de datos del sistema.
     * @return retorna un Response .json con una lista de objetos del tipo SubcategoriaEntity
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getSubCategorias() {
        try {
            return Response.ok(this.dao.findAll(SubcategoriaEntity.class)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
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
            return Response.ok(this.dao.find(id, SubcategoriaEntity.class)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
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
            subcategoriaAtributos(dtoSubcategoria);
            return Response.ok(this.dao.insert(subcategoria)).build();
        } catch(Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
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
            this.subcategoria = this.dao.find(dtoSubcategoria.get_id(), SubcategoriaEntity.class);
            subcategoriaAtributos(dtoSubcategoria);
            return Response.ok(this.dao.update(this.subcategoria)).build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
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
            this.subcategoria = this.dao.find(id, SubcategoriaEntity.class);
            return Response.ok(this.dao.delete(this.subcategoria)).build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

}
