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

@Path("/pregunta")
public class PreguntaServicio {

    public void borrarOpciones(PreguntaEntity pregunta) {
        DaoOpcion daoOpcion = new DaoOpcion();

        List<OpcionEntity> opciones = daoOpcion.getOpciones(pregunta);

        for(OpcionEntity opcion : opciones){
            OpcionEntity opcionEliminar = daoOpcion.find(opcion.get_id(), OpcionEntity.class);
            daoOpcion.delete(opcionEliminar);
        }

    }

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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPregunta(DtoPregunta dtoPregunta) {

        DaoPregunta dao = new DaoPregunta();
        DaoTipoPregunta daoTipoPregunta = new DaoTipoPregunta();
        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        DaoOpcion daoOpcion = new DaoOpcion();
        DaoPreguntaOpcion daoPreguntaOpcion= new DaoPreguntaOpcion();

        PreguntaEntity pregunta = new PreguntaEntity();

        if(pregunta != null) {
            pregunta.setDescripcion(dtoPregunta.getDescripcion());
            pregunta.setEstado(dtoPregunta.getEstado());

            SubcategoriaEntity subcategoria = daoSubcategoria.find(dtoPregunta.getSubcategoria().getId(), SubcategoriaEntity.class);
            pregunta.setSubcategoria(subcategoria);
            TipoPreguntaEntity tipoPregunta = daoTipoPregunta.find(dtoPregunta.getTipo().getId(), TipoPreguntaEntity.class);
            pregunta.setTipo(tipoPregunta);


            PreguntaEntity preguntaInsert = dao.insert(pregunta);

            // Tipo de Pregunta de Seleccion
            if(dtoPregunta.getTipo().getId() == 3 || dtoPregunta.getTipo().getId() == 4) {

                String[] opciones = dtoPregunta.getOpciones();
                int contador = 0;
                while (contador<opciones.length) {
                    OpcionEntity opcion_entidad = new OpcionEntity();
                    opcion_entidad.setDescripcion(opciones[contador]);
                    opcion_entidad.setEstado("a");
                    OpcionEntity resultadoOpcion = daoOpcion.insert(opcion_entidad);


                    PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                    pregunta_opcion_nn.setEstado("a");
                    pregunta_opcion_nn.setOpcion(resultadoOpcion);
                    pregunta_opcion_nn.setPregunta(preguntaInsert);
                    daoPreguntaOpcion.insert(pregunta_opcion_nn);

                    contador = contador+1;
                }
            }

            // Tipo de pregunta de Verdadero y Falso
            if(dtoPregunta.getTipo().getId() == 2) {   //Asigna en la n a n el verdadero o falso
                List<OpcionEntity> opciones = new ArrayList<>();
                opciones.add(new OpcionEntity("Verdadero", "a"));
                opciones.add(new OpcionEntity("Falso", "a"));

                for (OpcionEntity opcion: opciones) {
                    daoOpcion.insert(opcion);

                    PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                    pregunta_opcion_nn.setEstado("a");
                    pregunta_opcion_nn.setOpcion(opcion);
                    pregunta_opcion_nn.setPregunta(preguntaInsert);
                    daoPreguntaOpcion.insert(pregunta_opcion_nn);
                }
            }

            if(dtoPregunta.getTipo().getId() == 5) {   //Asigna en la n a n EL rango
                List<OpcionEntity> opciones = new ArrayList<>();
                opciones.add(new OpcionEntity("1", "a"));
                opciones.add(new OpcionEntity("2", "a"));
                opciones.add(new OpcionEntity("3", "a"));
                opciones.add(new OpcionEntity("4", "a"));
                opciones.add(new OpcionEntity("5", "a"));

                for (OpcionEntity opcion: opciones) {
                    daoOpcion.insert(opcion);

                    PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                    pregunta_opcion_nn.setEstado("a");
                    pregunta_opcion_nn.setOpcion(opcion);
                    pregunta_opcion_nn.setPregunta(preguntaInsert);
                    daoPreguntaOpcion.insert(pregunta_opcion_nn);
                }
            }

            return Response.ok().entity(preguntaInsert).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

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

    @PUT
    @Path("/{id}")
    public Response updatePregunta(@PathParam("id") long id, DtoPregunta dtoPregunta) {

        DaoPregunta dao = new DaoPregunta();
        PreguntaEntity pregunta = dao.find(id, PreguntaEntity.class);
        DaoPreguntaOpcion daoPreguntaOpcion= new DaoPreguntaOpcion();
        DaoOpcion daoOpcion = new DaoOpcion();

        if(pregunta != null) {

            // SI LA PREGUNTA EXISTENTE TIENE OPCIONES ASOCIADAS, SE ELIMINAN.

            if(pregunta.getTipo().get_id() == 2 || pregunta.getTipo().get_id() == 3 || pregunta.getTipo().get_id() == 4 || pregunta.getTipo().get_id() == 5){
                this.borrarOpciones(pregunta);
            }


            pregunta.setEstado(dtoPregunta.getEstado());
            pregunta.setDescripcion(dtoPregunta.getDescripcion());

            SubcategoriaEntity subcategoria = new SubcategoriaEntity(dtoPregunta.getSubcategoria().getId());
            pregunta.setSubcategoria(subcategoria);
            TipoPreguntaEntity tipoPregunta = new TipoPreguntaEntity(dtoPregunta.getTipo().getId());
            pregunta.setTipo(tipoPregunta);

            PreguntaEntity preguntaUpdate = dao.update(pregunta);

            // Tipo de Pregunta de Seleccion
            if(dtoPregunta.getTipo().getId() == 3 || dtoPregunta.getTipo().getId() == 4) {

                String[] opciones = dtoPregunta.getOpciones();
                int contador = 0;
                while (contador<opciones.length) {
                    OpcionEntity opcion_entidad = new OpcionEntity();
                    opcion_entidad.setDescripcion(opciones[contador]);
                    opcion_entidad.setEstado("a");
                    OpcionEntity resultadoOpcion = daoOpcion.insert(opcion_entidad);


                    PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                    pregunta_opcion_nn.setEstado("a");
                    pregunta_opcion_nn.setOpcion(resultadoOpcion);
                    pregunta_opcion_nn.setPregunta(preguntaUpdate);
                    daoPreguntaOpcion.insert(pregunta_opcion_nn);

                    contador = contador+1;
                }
            }

            // Tipo de pregunta de Verdadero y Falso
            if(dtoPregunta.getTipo().getId() == 2) {   //Asigna en la n a n el verdadero o falso
                List<OpcionEntity> opciones = new ArrayList<>();
                opciones.add(new OpcionEntity("Verdadero", "a"));
                opciones.add(new OpcionEntity("Falso", "a"));

                for (OpcionEntity opcion: opciones) {
                    daoOpcion.insert(opcion);

                    PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                    pregunta_opcion_nn.setEstado("a");
                    pregunta_opcion_nn.setOpcion(opcion);
                    pregunta_opcion_nn.setPregunta(preguntaUpdate);
                    daoPreguntaOpcion.insert(pregunta_opcion_nn);
                }
            }

            if(dtoPregunta.getTipo().getId() == 5) {   //Asigna en la n a n EL rango
                List<OpcionEntity> opciones = new ArrayList<>();
                opciones.add(new OpcionEntity("1", "a"));
                opciones.add(new OpcionEntity("2", "a"));
                opciones.add(new OpcionEntity("3", "a"));
                opciones.add(new OpcionEntity("4", "a"));
                opciones.add(new OpcionEntity("5", "a"));

                for (OpcionEntity opcion: opciones) {
                    daoOpcion.insert(opcion);

                    PreguntaOpcionEntity pregunta_opcion_nn = new PreguntaOpcionEntity();
                    pregunta_opcion_nn.setEstado("a");
                    pregunta_opcion_nn.setOpcion(opcion);
                    pregunta_opcion_nn.setPregunta(preguntaUpdate);
                    daoPreguntaOpcion.insert(pregunta_opcion_nn);
                }
            }

            return Response.ok(pregunta).build();
        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/preguntasSubcategoria/{id}")
    public Response getPreguntasbySubcategoria(@PathParam("id") long id){

        List<PreguntaEntity> preguntas = null;
        try {
            DaoEstudio daoEstudio = new DaoEstudio();
            DaoPregunta dao = new DaoPregunta();

            //Busco el estudio con el id de la url, y a su vez hago la busqueda de las preguntas que tienen esa subcategoria
            preguntas = dao.getPreguntasbySubcategoria(daoEstudio.find(id,EstudioEntity.class).getSubcategoria().get_id());

        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(preguntas).build();
    }

}
