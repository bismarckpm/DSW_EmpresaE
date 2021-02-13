package ucab.empresae.servicio;

import ucab.empresae.daos.DaoMarca;
import ucab.empresae.dtos.DtoMarca;
import ucab.empresae.entidades.MarcaEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad Marca
 */

@Path("/marca")
public class MarcaServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/marca
     * Metodo con anotacion POST que recibe un DtoMarca y crea el objeto Marca con los atributos en el DTO
     * @param dtoMarca objeto que posee todos los atributos necesarios para crear una Marca
     * @return Response con status ok al crear la Marca con la informacion suministrada
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMarca(DtoMarca dtoMarca) {

        DaoMarca dao = new DaoMarca();
        MarcaEntity marca = new MarcaEntity();

        try{
            if(dtoMarca != null) {
                marca.setNombre(dtoMarca.getNombre());
                marca.setEstado(dtoMarca.getEstado());
                MarcaEntity resul = dao.insert(marca);
                return Response.ok(marca).build();
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
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/marca
     * Metodo con anotacion GET que devuelve todas las Marcas
     * @return Response con status ok con la lista de Marcas.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getMarcas() {
        List<MarcaEntity> marcas;
        try {
            DaoMarca dao = new DaoMarca();
            marcas = dao.findAll(MarcaEntity.class);
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(marcas).build();
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/marca/id
     * Metodo con anotacion GET que devuelve todos los datos de una Marca a partir de su id
     * @param id identificador de la Marca de la cual se quieren obtener los datos
     * @return Response con status ok junto a los datos de la Marca consultada
     */
    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getMarca(@PathParam("id") long id) {
        DaoMarca dao = new DaoMarca();
        try{
            MarcaEntity marca = dao.find(id, MarcaEntity.class);
            if(marca != null) {
                return Response.ok(marca).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/usuario/marca/id
     * Metodo con anotacion PUT que se encarga de actualizar Marcas
     * @param id identificador de la Marca a ser actualizada
     * @param dtoMarca objeto que contiene los atributos que seran actualizados
     * @return Response con status ok y la Marca actualizada en caso de que la transacción haya sido exitosa
     */
    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateMarca(@PathParam("id") long id, DtoMarca dtoMarca){
        DaoMarca dao = new DaoMarca();
        try{
            MarcaEntity marca = dao.find(id, MarcaEntity.class);
            if (marca != null){
                marca.setNombre(dtoMarca.getNombre());
                marca.setEstado(dtoMarca.getEstado());
                MarcaEntity resul = dao.update(marca);
                return Response.ok().entity(marca).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/usuario/id
     * Metodo con anotacion DELETE que recibe un id y se encarga de eliminar de la base de datos la Marca con ese id
     * @param id identificador de la Marca a ser eliminada
     * @return Retorna un Response ok en caso de que la Marca se haya eliminado de manera correcta
     */
    @DELETE
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteMarca(@PathParam("id") long id) {
        try {
            DaoMarca dao = new DaoMarca();
            MarcaEntity marca = dao.find(id, MarcaEntity.class);
            if(marca != null) {
                MarcaEntity resul = dao.delete(marca);
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
