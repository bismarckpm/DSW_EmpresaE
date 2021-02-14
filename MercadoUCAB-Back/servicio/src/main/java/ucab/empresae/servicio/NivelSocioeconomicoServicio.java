package ucab.empresae.servicio;

import Comandos.ComandoBase;
import Comandos.ComandoFactory;
import ucab.empresae.daos.DaoNivelSocioeconomico;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.NivelSocioeconomicoEntity;
import ucab.empresae.excepciones.CustomException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad NivelSocioeconomico
 */

@Path("/nivelsocioeconomico")
public class NivelSocioeconomicoServicio {

    private ComandoBase comando;
    private DtoResponse response;

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/nivelsocioeconomico
     * Metodo con anotacion GET que devuelve todos los Niveles Socioeconomicos registrados
     * @return Response con status ok con la lista de Niveles Socioeconomicos.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getNivelesSocioeconomicos() {
        try {
            this.comando = ComandoFactory.comandoGetNivelesSocioeconomicoInstancia();
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

