package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.*;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.EncuestaException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad Encuesta
 */
@Path("/encuesta")
public class EncuestaServicio extends AplicacionBase{

    /**
     * Metodo que recibe un id de estudio para borrar las encuestas(n a n) asociadas a ese estudio
     * @param id identificador de estudio que se utiliza para obtener las encuestas asociadas a ese estudio
     */
    public void borrarPreguntasEncuesta(long id) throws EncuestaException {
        DaoEncuesta daoEncuesta = new DaoEncuesta();

        List<EncuestaEntity> preguntasEncuesta = daoEncuesta.getPreguntasEncuesta(id);

        if (preguntasEncuesta == null){
            throw new EncuestaException("El estudio seleccionado no tiene encuestas asociadas");
        }

        for(EncuestaEntity relacionPreguntaEstudio : preguntasEncuesta){
            EncuestaEntity relacionEliminar = daoEncuesta.find(relacionPreguntaEstudio.get_id(), EncuestaEntity.class);
            daoEncuesta.delete(relacionEliminar);
        }

    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/encuesta
     * Metodo con anotacion GET que devuelve todas las encuestas para ser manipuladas por el administrador
     * @return Response con status ok al encontrar la informacion solicitada y la lista de encuestas
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEncuestas() {
        List<EncuestaEntity> encuestas = null;
        try {
            DaoEncuesta dao = new DaoEncuesta();
            encuestas = dao.getEncuestas();
            return Response.ok(encuestas).build();

        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }


    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/encuesta
     * Metodo con anotacion POST que recibe un DtoEncuesta e inserta en la n a n encuesta con los atributos en el DTO
     * @param dtoEncuesta posee todos los atributos necesarios para crear la encuesta y asociarla al estudio
     * @return Response con status ok al crear la encuesta con la informacion suministrada
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEncuesta(DtoEncuesta dtoEncuesta){

        DaoEncuesta dao = new DaoEncuesta();
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoPregunta daoPregunta = new DaoPregunta();

        EstudioEntity estudio = daoEstudio.find(dtoEncuesta.getEstudio().get_id(),EstudioEntity.class);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<DtoPregunta> preguntas = dtoEncuesta.getPreguntas();

            for (DtoPregunta preguntaCiclo: preguntas) {
                //Se inserta en la n a n tantas veces como preguntas relacionadas al estudio
                EncuestaEntity encuesta = new EncuestaEntity();
                encuesta.setEstado("a");
                encuesta.setFechaInicio(new Date());

                encuesta.setEstudio(estudio);

                PreguntaEntity pregunta = daoPregunta.find(preguntaCiclo.get_id(), PreguntaEntity.class);
                encuesta.setPregunta(pregunta);
                dao.insert(encuesta);
            }


            List<DtoEncuestado> encuestados = dtoEncuesta.getEncuestados();

            DaoEstudioEncuestado daoEstudioEncuestado = DaoFactory.DaoEstudioEncuestadoInstancia();
            DaoEncuestado daoEncuestado = DaoFactory.DaoEncuestadoInstancia();
            for(DtoEncuestado encuestadoCiclo: encuestados){
                EncuestadoEntity encuestado = daoEncuestado.find(encuestadoCiclo.get_id(), EncuestadoEntity.class);

                EstudioEncuestadoEntity estudioEncuestadoEntity = EntidadesFactory.EstudioEncuestadoInstance();
                estudioEncuestadoEntity.setEstado("Pendiente");
                estudioEncuestadoEntity.setEstudio(estudio);
                estudioEncuestadoEntity.setEncuestado(encuestado);

                daoEstudioEncuestado.insert(estudioEncuestadoEntity);
            }


            return Response.ok().entity(preguntas).build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }

    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/encuesta
     * Metodo con anotacion POST que recibe un DtoEncuesta e inserta en la n a n encuesta con los atributos en el DTO
     * @param dtoEncuesta posee todos los atributos necesarios para crear la encuesta y asociarla al estudio
     * @return Response con status ok al crear la encuesta con la informacion suministrada
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEncuesta(DtoEncuesta dtoEncuesta){

        DaoEncuesta dao = new DaoEncuesta();
        DaoEstudio daoEstudio = new DaoEstudio();
        DaoPregunta daoPregunta = new DaoPregunta();

        try {
            EstudioEntity estudio = daoEstudio.find(dtoEncuesta.getEstudio().get_id(),EstudioEntity.class);
            this.borrarPreguntasEncuesta(estudio.get_id());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<DtoPregunta> preguntas = dtoEncuesta.getPreguntas();

            for (DtoPregunta preguntaCiclo: preguntas) {
                //Se inserta en la n a n tantas veces como preguntas relacionadas al estudio
                EncuestaEntity encuesta = new EncuestaEntity();
                encuesta.setEstado("a");
                Date fechaInicio = sdf.parse(dtoEncuesta.getFechaInicio());
                Date fechaFin = sdf.parse(dtoEncuesta.getFechaFin());
                encuesta.setFechaInicio(fechaInicio);
                encuesta.setFechaFin(fechaFin);

                encuesta.setEstudio(estudio);

                PreguntaEntity pregunta = daoPregunta.find(preguntaCiclo.get_id(), PreguntaEntity.class);
                encuesta.setPregunta(pregunta);
                dao.insert(encuesta);
            }

            return Response.ok().entity(preguntas).build();
        }catch (EncuestaException ex) {
            JsonObject excepcion = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage()).build();
            return  Response.status(500).entity(excepcion).build();
        }
        catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }

    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/encuesta/id
     * Metodo con anotacion DELETE que recibe un id estudio y se encarga de eliminar de la base de datos las encuestas del estudio
     * @param id identificador del estudio para eliminar las encuestas de ese estudio
     * @return Retorna un Response ok en caso de que las encuesta se hayan eliminado de manera correcta
     */
    @DELETE
    @Path("/preguntasEncuesta/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response deletePreguntasEncuesta(@PathParam("id") long id) {

        DaoEstudio daoEstudio = new DaoEstudio();
        EstudioEntity estudio = null;

        try {
            estudio = daoEstudio.find(id, EstudioEntity.class);
            this.borrarPreguntasEncuesta(id);
            return Response.ok().build();
        }catch (EncuestaException ex) {
            JsonObject excepcion = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage()).build();
            return  Response.status(500).entity(excepcion).build();
        }
        catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }

    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/encuesta/respuesta
     * Metodo con anotacion POST que recibe una lista DtoRespuesta e inserta en la n a n la respuesta a cada pregunta
     * @param dtoRespuestaAux lista de respuestas a cada pregunta, el encuestado que la responde, y el estudio correspondiente
     * @return Response con status ok al crear las respuestas con la informacion suministrada
     */
    @POST
    @Path("/respuesta")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response respuestaEncuesta(DtoRespuestaAux dtoRespuestaAux) {
        //DtoRespuestaAux posee un array de dtoRespuesta, un id usuario, y un id estudio

        try {
            //SE BUSCA AL ENCUESTADO MEDIANTE EL ID DE USUARIO SUMINISTRADO
            DaoUsuario daoUsuario = new DaoUsuario();
            DaoEncuestado daoEncuestado = new DaoEncuestado();
            DaoEstudioEncuestado daoEstudioEncuestado = new DaoEstudioEncuestado();           //este dao cambia el edo de la n a n
            UsuarioEntity usuarioEntity = daoUsuario.find(dtoRespuestaAux.getUsuario(), UsuarioEntity.class);
            EncuestadoEntity encuestadoEntity = daoEncuestado.getEncuestadoByUsuario(usuarioEntity);

            //SE BUSCA AL ESTUDIO MEDIANTE EL ID DE ESTUDIO SUMINISTRADO
            DaoEstudio daoEstudio = new DaoEstudio();
            EstudioEntity estudioEntity = daoEstudio.find(dtoRespuestaAux.getEstudio(), EstudioEntity.class);

            DaoRespuesta daoRespuesta = new DaoRespuesta();
            DaoPreguntaOpcion daoPreguntaOpcion = new DaoPreguntaOpcion();

            //Por cada respuesta en la lista suministrada
            for (DtoRespuesta respuesta: dtoRespuestaAux.getRespuestas()) {

                //si la respuesta tiene opciones
                if(respuesta.getOpciones() != null) {
                    //RESPUESTA.GETID SEGUN EL FORMATO DEL JSON CORRESPONDE AL ID DE LA PREGUNTA
                    for (DtoOpcion opcion : respuesta.getOpciones()){
                        PreguntaOpcionEntity preguntaOpcion = daoPreguntaOpcion.getPreguntaOpcion(respuesta.get_id(), opcion.get_id());
                        RespuestaEntity respuestaEntity = new RespuestaEntity();

                        respuestaEntity.setEstado("a");
                        respuestaEntity.setPreguntaOpcion(preguntaOpcion);
                        respuestaEntity.setEncuestado(encuestadoEntity);
                        respuestaEntity.setEstudio(estudioEntity);
                        respuestaEntity.setId_pregunta(respuesta.get_id());
                        daoRespuesta.insert(respuestaEntity);
                    }

                }
                //si la respuesta no tiene opciones
                if (respuesta.getOpciones().size() == 0){
                    RespuestaEntity respuestaEntity = new RespuestaEntity();
                    respuestaEntity.setEstado("a");
                    respuestaEntity.setEncuestado(encuestadoEntity);
                    respuestaEntity.setEstudio(estudioEntity);
                    respuestaEntity.setTexto(respuesta.getTexto());
                    respuestaEntity.setId_pregunta(respuesta.get_id());
                    daoRespuesta.insert(respuestaEntity);
                }
            }

            DaoRespuesta daoRespuestaPrueba = new DaoRespuesta();
            DaoPregunta daoPregunta = new DaoPregunta();

            long preguntasRespondidas = daoRespuestaPrueba.getCantidadPreguntasRespondidas(estudioEntity.get_id(), encuestadoEntity);
            long preguntasxResponder = daoPregunta.getPreguntasbyEstudioConPregAb(estudioEntity.get_id()).size();

            if (preguntasRespondidas == preguntasxResponder) {
                EstudioEncuestadoEntity estudioEncuestadoEntity = daoEstudioEncuestado.getEstudioEncuestado(encuestadoEntity, estudioEntity);
                estudioEncuestadoEntity.setEstado("Finalizado");
                daoEstudioEncuestado.update(estudioEncuestadoEntity);
            }
            else {
                EstudioEncuestadoEntity estudioEncuestadoEntity = daoEstudioEncuestado.getEstudioEncuestado(encuestadoEntity, estudioEntity);
                estudioEncuestadoEntity.setEstado("En Proceso");
                daoEstudioEncuestado.update(estudioEncuestadoEntity);
            }


            //VALIDAR QUE TODOS LOS ENCUESTADOS HAN RESPONDIDO EL ESTUDIO

            long cantidadEncuestados = daoEstudioEncuestado.getCantidadEncuestados(estudioEntity.get_id());
            long cantidadEncuestadosFinalizado = daoEstudioEncuestado.getCantidadEncuestadosFinalizado(estudioEntity.get_id());

            if(cantidadEncuestados == cantidadEncuestadosFinalizado){
                estudioEntity.setEstado("por analizar");
                daoEstudio.update(estudioEntity);
            }


            return Response.ok().build();

        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/encuesta/respuestaxAnalista
     * Metodo con anotacion POST que recibe una lista DtoRespuesta e inserta en la n a n la respuesta a cada pregunta por parte del analista
     * @param dtoRespuestaAux lista de respuestas a cada pregunta, el encuestado que la responde, y el estudio correspondiente
     * @return Response con status ok al crear las respuestas con la informacion suministrada
     */
    @POST
    @Path("/respuestaxAnalista")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response respuestaEncuestaxAnalista(DtoRespuestaAux dtoRespuestaAux) {
        //DtoRespuestaAux posee un array de dtoRespuesta, un id usuario, y un id estudio

        try {

            DaoEncuestado daoEncuestado = new DaoEncuestado();
            DaoEstudioEncuestado daoEstudioEncuestado = new DaoEstudioEncuestado();           //este dao cambia el edo de la n a n
            EncuestadoEntity encuestadoEntity = daoEncuestado.find(dtoRespuestaAux.getUsuario(), EncuestadoEntity.class);

            //SE BUSCA AL ESTUDIO MEDIANTE EL ID DE ESTUDIO SUMINISTRADO
            DaoEstudio daoEstudio = new DaoEstudio();
            EstudioEntity estudioEntity = daoEstudio.find(dtoRespuestaAux.getEstudio(), EstudioEntity.class);

            DaoRespuesta daoRespuesta = new DaoRespuesta();
            DaoPreguntaOpcion daoPreguntaOpcion = new DaoPreguntaOpcion();

            //Por cada respuesta en la lista suministrada
            for (DtoRespuesta respuesta: dtoRespuestaAux.getRespuestas()) {

                //si la respuesta tiene opciones
                if(respuesta.getOpciones() != null) {
                    //RESPUESTA.GETID SEGUN EL FORMATO DEL JSON CORRESPONDE AL ID DE LA PREGUNTA
                    for (DtoOpcion opcion : respuesta.getOpciones()){
                        PreguntaOpcionEntity preguntaOpcion = daoPreguntaOpcion.getPreguntaOpcion(respuesta.get_id(), opcion.get_id());
                        RespuestaEntity respuestaEntity = new RespuestaEntity();

                        respuestaEntity.setEstado("a");
                        respuestaEntity.setPreguntaOpcion(preguntaOpcion);
                        respuestaEntity.setEncuestado(encuestadoEntity);
                        respuestaEntity.setEstudio(estudioEntity);
                        respuestaEntity.setId_pregunta(respuesta.get_id());
                        daoRespuesta.insert(respuestaEntity);
                    }

                }
                //si la respuesta no tiene opciones
                if (respuesta.getOpciones().size() == 0){
                    RespuestaEntity respuestaEntity = new RespuestaEntity();
                    respuestaEntity.setEstado("a");
                    respuestaEntity.setEncuestado(encuestadoEntity);
                    respuestaEntity.setEstudio(estudioEntity);
                    respuestaEntity.setTexto(respuesta.getTexto());
                    respuestaEntity.setId_pregunta(respuesta.get_id());
                    daoRespuesta.insert(respuestaEntity);
                }
            }

            DaoRespuesta daoRespuestaPrueba = new DaoRespuesta();
            DaoPregunta daoPregunta = new DaoPregunta();

            long preguntasRespondidas = daoRespuestaPrueba.getCantidadPreguntasRespondidas(estudioEntity.get_id(), encuestadoEntity);
            long preguntasxResponder = daoPregunta.getPreguntasbyEstudioConPregAb(estudioEntity.get_id()).size();

            if (preguntasRespondidas == preguntasxResponder) {
                EstudioEncuestadoEntity estudioEncuestadoEntity = daoEstudioEncuestado.getEstudioEncuestado(encuestadoEntity, estudioEntity);
                estudioEncuestadoEntity.setEstado("Finalizado");
                daoEstudioEncuestado.update(estudioEncuestadoEntity);
            }
            else {
                EstudioEncuestadoEntity estudioEncuestadoEntity = daoEstudioEncuestado.getEstudioEncuestado(encuestadoEntity, estudioEntity);
                estudioEncuestadoEntity.setEstado("En Proceso");
                daoEstudioEncuestado.update(estudioEncuestadoEntity);
            }



            //VALIDAR QUE TODOS LOS ENCUESTADOS HAN RESPONDIDO EL ESTUDIO

            long cantidadEncuestados = daoEstudioEncuestado.getCantidadEncuestados(estudioEntity.get_id());
            long cantidadEncuestadosFinalizado = daoEstudioEncuestado.getCantidadEncuestadosFinalizado(estudioEntity.get_id());

            if(cantidadEncuestados == cantidadEncuestadosFinalizado){
                estudioEntity.setEstado("por analizar");
                daoEstudio.update(estudioEntity);
            }

            return Response.ok().build();

        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }
}