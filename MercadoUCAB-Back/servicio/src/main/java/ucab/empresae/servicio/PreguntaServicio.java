package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoOpcion;
import ucab.empresae.dtos.DtoPregunta;
import ucab.empresae.entidades.*;

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

    /**
     * Metodo que recibe un objeto pregunta para borrar las opciones asociadas a esa pregunta
     * @param pregunta objeto pregunta que se utiliza para obtener las opciones asociadas
     */
    public void borrarOpciones(PreguntaEntity pregunta) {
        DaoOpcion daoOpcion = new DaoOpcion();

        List<OpcionEntity> opciones = daoOpcion.getOpciones(pregunta);

        for (OpcionEntity opcion : opciones) {
            OpcionEntity opcionEliminar = daoOpcion.find(opcion.get_id(), OpcionEntity.class);
            daoOpcion.delete(opcionEliminar);
        }

    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/pregunta
     * Metodo con anotacion GET que devuelve todas las preguntas registradas en el sistema
     * @return Response con status ok al encontrar la informacion solicitada y la lista de preguntas
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getPreguntas() {
        List<PreguntaEntity> preguntas = null;
        try {
            DaoPregunta dao = new DaoPregunta();
            preguntas = dao.findAll(PreguntaEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(preguntas).build();
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

        DaoPregunta dao = new DaoPregunta();
        DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        DaoOpcion daoOpcion = new DaoOpcion();
        DaoPreguntaOpcion daoPreguntaOpcion = new DaoPreguntaOpcion();

        PreguntaEntity pregunta = new PreguntaEntity();

        if (pregunta != null) {
            pregunta.setDescripcion(dtoPregunta.getDescripcion());
            pregunta.setEstado(dtoPregunta.getEstado());

            SubcategoriaEntity subcategoria = daoSubcategoria.find(dtoPregunta.getSubcategoria().get_id(), SubcategoriaEntity.class);
            pregunta.setSubcategoria(subcategoria);
            TipoPreguntaEntity tipoPregunta = daoTipoPregunta.find(dtoPregunta.getTipo().get_id(), TipoPreguntaEntity.class);
            pregunta.setTipo(tipoPregunta);


            PreguntaEntity preguntaInsert = dao.insert(pregunta);

            // Tipo de Pregunta de Seleccion Simple o Seleccion multiple
            if (dtoPregunta.getTipo().get_id() == 3 || dtoPregunta.getTipo().get_id() == 4) {

                String[] opciones = dtoPregunta.getOpciones();
                int contador = 0;
                while (contador < opciones.length) {
                    OpcionEntity opcion_entidad = new OpcionEntity();
                    opcion_entidad.setDescripcion(opciones[contador]);
                    opcion_entidad.setEstado("a");
                    OpcionEntity resultadoOpcion = daoOpcion.insert(opcion_entidad);


                    PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                    pregunta_opcion_nn.setEstado("a");
                    pregunta_opcion_nn.setOpcion(resultadoOpcion);
                    pregunta_opcion_nn.setPregunta(preguntaInsert);
                    daoPreguntaOpcion.insert(pregunta_opcion_nn);

                    contador = contador + 1;
                }
            }

            // Tipo de pregunta de Verdadero y Falso
            if (dtoPregunta.getTipo().get_id() == 2) {   //Asigna en la n a n el verdadero o falso
                List<OpcionEntity> opciones = new ArrayList<>();
                opciones.add(new OpcionEntity("Verdadero", "a"));
                opciones.add(new OpcionEntity("Falso", "a"));

                for (OpcionEntity opcion : opciones) {
                    daoOpcion.insert(opcion);

                    PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                    pregunta_opcion_nn.setEstado("a");
                    pregunta_opcion_nn.setOpcion(opcion);
                    pregunta_opcion_nn.setPregunta(preguntaInsert);
                    daoPreguntaOpcion.insert(pregunta_opcion_nn);
                }
            }

            // Tipo de pregunta de Rango
            if (dtoPregunta.getTipo().get_id() == 5) {   //Asigna en la n a n EL rango
                List<OpcionEntity> opciones = new ArrayList<>();
                opciones.add(new OpcionEntity("1", "a"));
                opciones.add(new OpcionEntity("2", "a"));
                opciones.add(new OpcionEntity("3", "a"));
                opciones.add(new OpcionEntity("4", "a"));
                opciones.add(new OpcionEntity("5", "a"));

                for (OpcionEntity opcion : opciones) {
                    daoOpcion.insert(opcion);

                    PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                    pregunta_opcion_nn.setEstado("a");
                    pregunta_opcion_nn.setOpcion(opcion);
                    pregunta_opcion_nn.setPregunta(preguntaInsert);
                    daoPreguntaOpcion.insert(pregunta_opcion_nn);
                }
            }

            return Response.ok().entity(preguntaInsert).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
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

        DaoPregunta dao = new DaoPregunta();
        PreguntaEntity pregunta = dao.find(id, PreguntaEntity.class);
        if (pregunta != null) {
            PreguntaEntity resul = dao.delete(pregunta);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
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

        DaoPregunta dao = new DaoPregunta();
        PreguntaEntity pregunta = dao.find(id, PreguntaEntity.class);
        DaoPreguntaOpcion daoPreguntaOpcion = new DaoPreguntaOpcion();
        DaoOpcion daoOpcion = new DaoOpcion();

        if (pregunta != null) {

            // SI LA PREGUNTA EXISTENTE TIENE OPCIONES ASOCIADAS, SE ELIMINAN.

            if (pregunta.getTipo().get_id() == 2 || pregunta.getTipo().get_id() == 3 || pregunta.getTipo().get_id() == 4 || pregunta.getTipo().get_id() == 5) {
                this.borrarOpciones(pregunta);
            }


            pregunta.setEstado(dtoPregunta.getEstado());
            pregunta.setDescripcion(dtoPregunta.getDescripcion());

            SubcategoriaEntity subcategoria = new SubcategoriaEntity(dtoPregunta.getSubcategoria().get_id());
            pregunta.setSubcategoria(subcategoria);
            TipoPreguntaEntity tipoPregunta = new TipoPreguntaEntity(dtoPregunta.getTipo().get_id());
            pregunta.setTipo(tipoPregunta);

            PreguntaEntity preguntaUpdate = dao.update(pregunta);

            // Tipo de Pregunta de Seleccion
            if (dtoPregunta.getTipo().get_id() == 3 || dtoPregunta.getTipo().get_id() == 4) {

                String[] opciones = dtoPregunta.getOpciones();
                int contador = 0;
                while (contador < opciones.length) {
                    OpcionEntity opcion_entidad = new OpcionEntity();
                    opcion_entidad.setDescripcion(opciones[contador]);
                    opcion_entidad.setEstado("a");
                    OpcionEntity resultadoOpcion = daoOpcion.insert(opcion_entidad);


                    PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                    pregunta_opcion_nn.setEstado("a");
                    pregunta_opcion_nn.setOpcion(resultadoOpcion);
                    pregunta_opcion_nn.setPregunta(preguntaUpdate);
                    daoPreguntaOpcion.insert(pregunta_opcion_nn);

                    contador = contador + 1;
                }
            }

            // Tipo de pregunta de Verdadero y Falso
            if (dtoPregunta.getTipo().get_id() == 2) {   //Asigna en la n a n el verdadero o falso
                List<OpcionEntity> opciones = new ArrayList<>();
                opciones.add(new OpcionEntity("Verdadero", "a"));
                opciones.add(new OpcionEntity("Falso", "a"));

                for (OpcionEntity opcion : opciones) {
                    daoOpcion.insert(opcion);

                    PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                    pregunta_opcion_nn.setEstado("a");
                    pregunta_opcion_nn.setOpcion(opcion);
                    pregunta_opcion_nn.setPregunta(preguntaUpdate);
                    daoPreguntaOpcion.insert(pregunta_opcion_nn);
                }
            }

            if (dtoPregunta.getTipo().get_id() == 5) {   //Asigna en la n a n EL rango
                List<OpcionEntity> opciones = new ArrayList<>();
                opciones.add(new OpcionEntity("1", "a"));
                opciones.add(new OpcionEntity("2", "a"));
                opciones.add(new OpcionEntity("3", "a"));
                opciones.add(new OpcionEntity("4", "a"));
                opciones.add(new OpcionEntity("5", "a"));

                for (OpcionEntity opcion : opciones) {
                    daoOpcion.insert(opcion);

                    PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                    pregunta_opcion_nn.setEstado("a");
                    pregunta_opcion_nn.setOpcion(opcion);
                    pregunta_opcion_nn.setPregunta(preguntaUpdate);
                    daoPreguntaOpcion.insert(pregunta_opcion_nn);
                }
            }

            return Response.ok(pregunta).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
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

        List<PreguntaEntity> preguntas = null;
        try {
            DaoEstudio daoEstudio = new DaoEstudio();
            DaoPregunta dao = new DaoPregunta();

            //Busco el estudio con el id de la url, y a su vez hago la busqueda de las preguntas que tienen esa subcategoria
            preguntas = dao.getPreguntasbySubcategoria(daoEstudio.find(id, EstudioEntity.class).getSubcategoria().get_id());

        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(preguntas).build();
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/pregunta/preguntasEstudio/id
     * Metodo con anotacion GET que devuelve todas las preguntas que fueron asignadas a un estudio
     * @param id identificador del estudio al que se le asignaron preguntas
     * @return Response con status ok con la lista de Preguntas asignadas a un estudio.
     */
    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/preguntasEstudio/{id}")
    public Response getPreguntasbyEstudio(@PathParam("id") long id){

        List<PreguntaEntity> preguntas = null;
        try {
            DaoEstudio daoEstudio = new DaoEstudio();
            DaoPregunta dao = new DaoPregunta();

            //Busco el estudio con el id de la url, y a su vez hago la busqueda de las preguntas que tienen ese estudio
            preguntas = dao.getPreguntasbyEstudio(id);

        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(preguntas).build();
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/pregunta/encuestaEstudio/id
     * Metodo con anotacion GET que permite obtener todas las preguntas con sus opciones (encuesta) de un estudio
     * @param id identificador del estudio para obtener las preguntas con opciones (encuesta)
     * @return Response con status ok con la lista de preguntas con opciones (encuesta) asignadas a un estudio.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/encuestaEstudio/{id}")
    public List<PreguntaAux> getPreguntasyOpciones(@PathParam("id") long id) {

        List<PreguntaEntity> preguntas = null;
        List<PreguntaAux> preguntaAuxList = new ArrayList<PreguntaAux>();
        DaoOpcion daoOpcion = new DaoOpcion();

        try {
            DaoPregunta dao = new DaoPregunta();
            preguntas = dao.getPreguntasbyEstudio(id);

            for(PreguntaEntity pregunta : preguntas){
                PreguntaAux preguntaAux = new PreguntaAux(pregunta.get_id());
                List<OpcionEntity> opciones = daoOpcion.getOpciones(pregunta);


                preguntaAux.setDescripcion(pregunta.getDescripcion());
                preguntaAux.setTipo(pregunta.getTipo());
                preguntaAux.setOpcionesPregunta(opciones);

                preguntaAuxList.add(preguntaAux);
            }

            return preguntaAuxList;

        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return null;
    }

    /*
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/opcionesdePregunta/{id}")
    public List<OpcionEntity> getOpcionesdePregunta(@PathParam("id") long id) {

        try {
            DaoPregunta dao = new DaoPregunta();
            DaoOpcion daoOpcion = new DaoOpcion();
            PreguntaEntity pregunta = dao.find(id, PreguntaEntity.class);


            List<OpcionEntity> opciones = daoOpcion.getOpciones(pregunta);
            return opciones;

        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return null;
    }*/

}
