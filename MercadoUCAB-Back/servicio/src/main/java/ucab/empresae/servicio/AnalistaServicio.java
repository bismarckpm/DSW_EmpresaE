package ucab.empresae.servicio;

import Comandos.ComandoBase;
import Comandos.ComandoFactory;
import ucab.empresae.daos.DaoEstudio;
import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.EstudioEntity;
import ucab.empresae.excepciones.CustomException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones relacionadas a los Analistas
 */

@Path("/analista")
public class AnalistaServicio extends AplicacionBase {

    private ComandoBase comando;
    private DtoResponse response = DtoFactory.DtoResponseInstance();

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/analista
     * Metodo con anotacion GET que devuelve todos los Analistas registrados
     * @return Response con status ok con la lista de Analistas.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAnalistas() {

        try {
            this.comando = ComandoFactory.comandoGetAnalistas();
            return Response.ok(this.comando.getResult()).build();
        } catch (CustomException cex){
            this.response.setEstado("ERROR");
            this.response.setMensaje(cex.getMensaje());
            this.response.setCodError(cex.getCodError());
            return Response.status(500).entity(this.response).build();
        } catch (Exception ex) {
            this.response.setEstado("ERROR");
            this.response.setMensaje(ex.getMessage());
            this.response.setCodError("SERPRE004");
            return Response.status(500).entity(this.response).build();
        }

    }

}
