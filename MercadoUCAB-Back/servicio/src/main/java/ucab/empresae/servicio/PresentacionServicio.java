package ucab.empresae.servicio;

import Comandos.ComandoBase;
import Comandos.ComandoFactory;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoPresentacion;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.PresentacionEntity;
import ucab.empresae.excepciones.CustomException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Servicio de apis destinada a las transacciones correspondientes a las presentaciones de productos.
 */
@Path("/presentacion")
public class PresentacionServicio {

    private ComandoBase comando;

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/presentacion
     * @apiNote Api de tipo get hecha para retornar todas las presentaciones guardadas en sistema.
     * @return Lista de objetos de tipo PresentacionEntity
     * @see PresentacionEntity Entidad persistente utilizada para retornar las presentaciones en sistema.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getPresentaciones() {
        DtoResponse response = DtoFactory.DtoResponseInstance();
        try {
            this.comando = ComandoFactory.comandoGetPresentacionesInstancia();
            return Response.ok(this.comando.getResult()).build();
        }catch (CustomException cex){
            response.setEstado("ERROR");
            response.setMensaje(cex.getMensaje());
            response.setCodError(cex.getCodError());
            return Response.status(500).entity(response).build();
        }
        catch (Exception ex) {
            response.setEstado("ERROR");
            response.setMensaje(ex.getMessage());
            response.setCodError("SERPRE001");
            return Response.status(500).entity(response).build();
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
        DtoResponse response = DtoFactory.DtoResponseInstance();
        try{
            this.comando = ComandoFactory.comandoPostPresentacionInstancia(dtoPresentacion);
            return Response.ok(this.comando.getResult()).build();
        } catch (CustomException cex){
            response.setEstado("ERROR");
            response.setMensaje(cex.getMensaje());
            response.setCodError(cex.getCodError());
            return Response.status(500).entity(response).build();
        }
        catch (Exception ex) {
            response.setEstado("ERROR");
            response.setMensaje(ex.getMessage());
            response.setCodError("SERPRE002");
            return Response.status(500).entity(response).build();
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
        DtoResponse response = DtoFactory.DtoResponseInstance();
        try {
            this.comando = ComandoFactory.comandoDeletePresentacionInstancia(id);
            return Response.ok(this.comando.getResult()).build();
        } catch (CustomException cex){
            response.setEstado("ERROR");
            response.setMensaje(cex.getMensaje());
            response.setCodError(cex.getCodError());
            return Response.status(500).entity(response).build();
        }
        catch (Exception ex) {
            response.setEstado("ERROR");
            response.setMensaje(ex.getMessage());
            response.setCodError("SERPRE002");
            return Response.status(500).entity(response).build();
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
        DtoResponse response = DtoFactory.DtoResponseInstance();
        try {
            this.comando = ComandoFactory.comandoUpdatePresentacionInstancia(dtoPresentacion);
            return Response.ok(this.comando.getResult()).build();
        } catch (CustomException cex){
            response.setEstado("ERROR");
            response.setMensaje(cex.getMensaje());
            response.setCodError(cex.getCodError());
            return Response.status(500).entity(response).build();
        }
        catch (Exception ex) {
            response.setEstado("ERROR");
            response.setMensaje(ex.getMessage());
            response.setCodError("SERPRE003");
            return Response.status(500).entity(response).build();
        }
    }
}
