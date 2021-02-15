package ucab.empresae.servicio;

import Comandos.ComandoBase;
import Comandos.ComandoFactory;
import Comandos.Pregunta.ComandoGetEncuesta;
import Comandos.Pregunta.ComandoGetEncuestaAnalista;
import Comandos.Pregunta.ComandoGetPreguntasBySubcategoria;
import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoEstudioEncuestado;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoPregunta;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.CustomException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad Pregunta
 */

@Path("/pregunta")
public class PreguntaServicio {

    private ComandoBase comando;


    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/pregunta
     * Metodo con anotacion GET que devuelve todas las preguntas registradas en el sistema
     * @return Response con status ok al encontrar la informacion solicitada y la lista de preguntas
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getPreguntas() {
        DtoResponse response = DtoFactory.DtoResponseInstance();
        try {
            this.comando = ComandoFactory.comandoGetPreguntasInstancia();
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
            response.setCodError("SERPREG001");
            return Response.status(500).entity(response).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/pregunta
     * Metodo con anotacion POST que recibe un DtoPregunta y crea el objeto pregunta con los atributos en el DTO
     * @param dtoPregunta posee todos los atributos necesarios para crear la pregunta y sus opciones
     * @return Response con status ok al crear la pregunta con la informacion suministrada
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPregunta(DtoPregunta dtoPregunta) {
        DtoResponse response = DtoFactory.DtoResponseInstance();
        try{
            this.comando = ComandoFactory.comandoPostPreguntaInstancia(dtoPregunta);
            return Response.ok(this.comando.getResult()).build();
        }catch (CustomException cex){
            response.setEstado("000");
            response.setMensaje(cex.getMensaje());
            response.setCodError(cex.getCodError());
            return Response.status(500).entity(response).build();
        }
        catch (Exception ex) {
            response.setEstado("000");
            response.setMensaje(ex.getMessage());
            response.setCodError("SERPREG002");
            return Response.status(500).entity(response).build();
        }

    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/pregunta/id
     * Metodo con anotacion DELETE que recibe un id y se encarga de eliminar de la base de datos a la entidad con ese id
     * @param id identificador de la pregunta a ser eliminada
     * @return Retorna un Response ok en caso de que la pregunta se haya eliminado de manera correcta
     */
    @DELETE
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response deletePregunta(@PathParam("id") long id) {
        /*
        DaoPregunta dao = new DaoPregunta();
        PreguntaEntity pregunta = null;
        try {
            pregunta = dao.find(id, PreguntaEntity.class);
            PreguntaEntity resul = dao.delete(pregunta);
            return Response.ok().build();

        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }*/

        DtoResponse response = DtoFactory.DtoResponseInstance();
        try {
            this.comando = ComandoFactory.comandoDeletePreguntaInstancia(id);
            return Response.ok(this.comando.getResult()).build();
        } catch (CustomException cex){
            response.setEstado("000");
            response.setMensaje(cex.getMensaje());
            response.setCodError(cex.getCodError());
            return Response.status(500).entity(response).build();
        }
        catch (Exception ex) {
            response.setEstado("000");
            response.setMensaje(ex.getMessage());
            response.setCodError("SERPREG003");
            return Response.status(500).entity(response).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/pregunta/id
     * Metodo con anotacion PUT que se encarga de actualizar una Pregunta en particular
     * @param id identificador de la pregunta a ser actualizada
     * @param dtoPregunta objeto que contiene los atributos que seran actualizados
     * @return retorna a la Pregunta actualizada en caso de que la transacción haya sido exitosa
     */
    @PUT
    @Path("/{id}")
    public Response updatePregunta(@PathParam("id") long id, DtoPregunta dtoPregunta) {
        DtoResponse response = DtoFactory.DtoResponseInstance();
        try{
            this.comando = ComandoFactory.comandoUpdatePreguntaInstancia(id, dtoPregunta);
            return Response.ok(this.comando.getResult()).build();
        }catch (CustomException cex){
            response.setEstado("000");
            response.setMensaje(cex.getMensaje());
            response.setCodError(cex.getCodError());
            return Response.status(500).entity(response).build();
        }
        catch (Exception ex) {
            response.setEstado("000");
            response.setMensaje(ex.getMessage());
            response.setCodError("SERPREG004");
            return Response.status(500).entity(response).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/pregunta/preguntasSubcategoria/id
     * Metodo con anotacion GET que devuelve todas las preguntas que pertenecen a la misma subcategoria de un estudio
     * @param id identificador del estudio al que se le asignaran preguntas
     * @return Response con status ok con la lista de Preguntas al encontrar la informacion solicitada.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/preguntasSubcategoria/{id}")
    public Response getPreguntasbySubcategoria(@PathParam("id") long id) {
        DtoResponse response = DtoFactory.DtoResponseInstance();
        try {
            this.comando = ComandoFactory.comandoGetPreguntasBySubcategoriaInstancia(id);
            //ComandoGetPreguntasBySubcategoria comandoP = new ComandoGetPreguntasBySubcategoria(id);
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
            response.setCodError("SERPREG005");
            return Response.status(500).entity(response).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/pregunta/encuestaEstudioEncuestado/id_estudio/id_encuestado
     * Metodo con anotacion GET que permite obtener todas las preguntas con sus opciones (encuesta) de un estudio respecto a un encuestado
     * @return Response con status ok con la lista de preguntas con opciones (encuesta) asignadas a un estudio.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/encuestaEstudioEncuestado/{id_estudio}/{id_encuestado}")
    public Response getPreguntasyOpcionesxEncuestado(@PathParam("id_estudio") long id_estudio, @PathParam("id_encuestado") long id_encuestado) {
        DtoResponse response = DtoFactory.DtoResponseInstance();
        try {
            //this.comando = ComandoFactory.comandoGetEncuestaInstancia(id_estudio, id_encuestado);//id de encuestado es el id del usuario
            ComandoGetEncuesta comandoGetEncuesta = new ComandoGetEncuesta(id_estudio, id_encuestado);
            return Response.ok(comandoGetEncuesta.getResult()).build();
        }catch (CustomException cex){
            response.setEstado("ERROR");
            response.setMensaje(cex.getMensaje());
            response.setCodError(cex.getCodError());
            return Response.status(500).entity(response).build();
        }
        catch (Exception ex) {
            response.setEstado("ERROR");
            response.setMensaje(ex.getMessage());
            response.setCodError("SERPREG006");
            return Response.status(500).entity(response).build();
        }
    }


    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/pregunta/encuestaEstudioAnalistaEncuestado/id_estudio/id_encuestado
     * Metodo con anotacion GET que permite obtener todas las preguntas con sus opciones (encuesta) de un estudio respecto a un encuestado QUE RESPONDERA EL ANALISTA
     * @return Response con status ok con la lista de preguntas con opciones (encuesta) asignadas a un estudio.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/encuestaEstudioAnalistaEncuestado/{id_estudio}/{id_encuestado}")
    public Response getPreguntasyOpcionesxAnalistaEncuestado(@PathParam("id_estudio") long id_estudio, @PathParam("id_encuestado") long id_encuestado) {
        DtoResponse response = DtoFactory.DtoResponseInstance();
        try {
            //this.comando = ComandoFactory.comandoGetEncuestaAnalistaInstancia(id_estudio, id_encuestado);//id de encuestado es el id del usuario
            ComandoGetEncuestaAnalista comandoGetEncuestaAnalista = new ComandoGetEncuestaAnalista(id_estudio, id_encuestado);
            return Response.ok(comandoGetEncuestaAnalista.getResult()).build();
        }catch (CustomException cex){
            response.setEstado("ERROR");
            response.setMensaje(cex.getMensaje());
            response.setCodError(cex.getCodError());
            return Response.status(500).entity(response).build();
        }
        catch (Exception ex) {
            response.setEstado("ERROR");
            response.setMensaje(ex.getMessage());
            response.setCodError("SERPREG00");
            return Response.status(500).entity(response).build();
        }
    }
}
