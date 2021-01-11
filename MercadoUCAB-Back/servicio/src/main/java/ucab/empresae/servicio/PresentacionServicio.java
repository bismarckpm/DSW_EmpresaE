package ucab.empresae.servicio;

import ucab.empresae.daos.DaoPresentacion;
import ucab.empresae.dtos.DtoPresentacion;
import ucab.empresae.entidades.PresentacionEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Servicio de apis destinada a las transacciones correspondientes a las presentaciones de productos.
 */
@Path("/presentacion")
public class PresentacionServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/presentacion
     * @apiNote Api de tipo get hecha para retornar todas las presentaciones guardadas en sistema.
     * @return Lista de objetos de tipo PresentacionEntity
     * @see PresentacionEntity Entidad persistente utilizada para retornar las presentaciones en sistema.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getPresentaciones() {
        List<PresentacionEntity> presentaciones = null;
        try {
            DaoPresentacion dao = new DaoPresentacion();
            presentaciones = dao.findAll(PresentacionEntity.class);
            return Response.ok(presentaciones).build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/presentacion
     * @apiNote Api del tipo POST encargada de añadir una presentación nueva.
     * @param dtoPresentacion Objeto de tipo DtoPresentacion
     * @see DtoPresentacion Data transfer object utilizado para obtener los datos de la presentación a ingresar.
     * @return Objeto de tipo PresentacionEntity
     * @see PresentacionEntity Entidad persistente utilizada para insertar la presentacion nueva.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPresentacion(DtoPresentacion dtoPresentacion) {

        DaoPresentacion dao = new DaoPresentacion();
        PresentacionEntity presentacion = new PresentacionEntity();

        try{
            presentacion.setDescripcion(dtoPresentacion.getDescripcion());
            presentacion.setEstado(dtoPresentacion.getEstado());

            PresentacionEntity resul = dao.insert(presentacion);
            return Response.ok(presentacion).build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }

    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/presentacion
     * @apiNote Api de tipo @DELETE encargada de eliminar una presentación de producto del sistema.
     * @param id Objeto de tipo long que representa el id de la presentación a eliminar.
     * @return ok cuando el objeto fue eliminado con exito.
     */
    @DELETE
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response deletePresentacion(@PathParam("id") long id) {

        DaoPresentacion dao = new DaoPresentacion();
        PresentacionEntity presentacion = null;

        try{
            presentacion = dao.find(id, PresentacionEntity.class);
            PresentacionEntity resul = dao.delete(presentacion);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }

    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/presentacion
     * @apiNote Api de tipo PUT hecho para actualizar los datos de la presentación.
     * @param id Objeto de tipo long que representa al id de la presentación a actualizar.
     * @param dtoPresentacion Objeto de tipo DtoPresentación utilizado para obtener los datos de la presentacion
     * @return Objeto de tipo PresentacionEntity
     */
    @PUT
    @Path("/{id}")
    public Response updatePresentacion(@PathParam("id") long id, DtoPresentacion dtoPresentacion) {

        DaoPresentacion dao = new DaoPresentacion();
        PresentacionEntity presentacion = null;

        try {
            presentacion = dao.find(id, PresentacionEntity.class);
            presentacion.setEstado(dtoPresentacion.getEstado());
            presentacion.setDescripcion(dtoPresentacion.getDescripcion());

            PresentacionEntity resul = dao.update(presentacion);
            return Response.ok(presentacion).build();

        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }
}
