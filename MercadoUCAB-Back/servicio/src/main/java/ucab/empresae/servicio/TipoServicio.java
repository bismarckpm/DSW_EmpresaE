package ucab.empresae.servicio;

import ucab.empresae.daos.DaoTipo;
import ucab.empresae.dtos.DtoTipo;
import ucab.empresae.entidades.TipoEntity;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad Tipo
 */

@Path("/tipo")
public class TipoServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/tipo
     * Metodo con anotacion POST que recibe un DtoTipo y crea el objeto tipo con los atributos en el DTO
     * @param dtoTipo objeto que posee todos los atributos necesarios para crear un tipo
     * @return Response con status ok al crear el tipo con la informacion suministrada
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTipo(DtoTipo dtoTipo) {

        DaoTipo dao = new DaoTipo();
        TipoEntity tipo = new TipoEntity();

        try{
            if(dtoTipo != null) {
                tipo.setNombre(dtoTipo.getNombre());
                tipo.setEstado(dtoTipo.getEstado());
                tipo.setDescripcion(dtoTipo.getDescripcion());
                TipoEntity resul = dao.insert(tipo);
                return Response.ok(tipo).build();
            }
            else {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/usuario/tipo
     * Metodo con anotacion GET que devuelve todos los tipos
     * @return Response con status ok con la lista de Tipos.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response geTipos() {
        List<TipoEntity> tipos;
        try {
            DaoTipo dao = new DaoTipo();
            tipos = dao.findAll(TipoEntity.class);
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(tipos).build();
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/tipo/id
     * Metodo con anotacion GET que devuelve todos los datos de un tipo a partir de su id
     * @param id identificador del tipo del que se quieren obtener todos sus datos
     * @return Response con status ok junto a los datos del tipo consultado
     */
    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getTipo(@PathParam("id") long id)
    {
        DaoTipo dao = new DaoTipo();
        try{
            TipoEntity tipo = dao.find(id, TipoEntity.class);
            if(tipo != null) {
                return Response.ok(tipo).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/tipo/id
     * Metodo con anotacion PUT que se encarga de actualizar tipos
     * @param id identificador del tipo a ser actualizado
     * @param dtoTipo objeto que contiene los atributos que seran actualizados
     * @return Response con status ok y el tipo actualizado en caso de que la transacción haya sido exitosa
     */
    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateTipo(@PathParam("id") long id, DtoTipo dtoTipo) {
        DaoTipo dao = new DaoTipo();

        try{
            TipoEntity tipo = dao.find(id, TipoEntity.class);
            if (tipo != null){
                tipo.setNombre(dtoTipo.getNombre());
                tipo.setEstado(dtoTipo.getEstado());
                tipo.setDescripcion(dtoTipo.getDescripcion());
                TipoEntity resul = dao.update(tipo);
                return Response.ok().entity(tipo).build();
            }
            else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/tipo/id
     * Metodo con anotacion DELETE que recibe un id y se encarga de eliminar de la base de datos el Tipo con ese id
     * @param id identificador del Tipo a ser eliminado
     * @return Retorna un Response ok en caso de que el Tipo se haya eliminado de manera correcta
     */
    @DELETE
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteTipo(@PathParam("id") long id) {
        DaoTipo dao = new DaoTipo();
        try {
            TipoEntity tipo = dao.find(id, TipoEntity.class);
            if(tipo != null) {
                TipoEntity resul = dao.delete(tipo);
                return Response.ok().build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

}
