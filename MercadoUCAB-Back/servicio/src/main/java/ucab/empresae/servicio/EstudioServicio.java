package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.dtos.DtoSubcategoria;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.EstudioException;
import ucab.empresae.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Api encargada de las transacciones que tienen que ver con estudios
 */
@Path("/estudio")
public class EstudioServicio extends AplicacionBase {

    private DaoEstudio dao = DaoFactory.DaoEstudioInstancia();
    private EstudioEntity estudio = EntidadesFactory.EstudioInstance();

    /**
     * Método encargado de armar el estudio en si.
     * @param dtoEstudio Data transfer object utilizado para recibir los datos del estudio.
     * @see DtoEstudio Objeto usado para recibir los datos.
     */
    private void estudioAtributos(DtoEstudio dtoEstudio) {

        try {
            this.estudio.setEstado("Solicitado");
            this.estudio.setNombre(dtoEstudio.getNombre());
            this.estudio.setComentarioAnalista(dtoEstudio.getComentarioAnalista());
            this.estudio.setEdadMinima(dtoEstudio.getEdadMinima());
            this.estudio.setEdadMaxima(dtoEstudio.getEdadMaxima());

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = dateFormat.parse(dtoEstudio.getFechaInicio());
            this.estudio.setFechaInicio(fecha);

            this.estudio.setFechaFin(null);
        }catch (Exception ex) {
            System.out.println(ex);
        }

        if(dtoEstudio.getSubcategoria() != null) {
            DaoSubcategoria daoSubcategoria = DaoFactory.DaoSubcategoriaInstancia();
            this.estudio.setSubcategoria(daoSubcategoria.find(dtoEstudio.getSubcategoria().get_id(), SubcategoriaEntity.class));
        }

        if(dtoEstudio.getNivelSocioEconomico() != null) {
            DaoNivelSocioeconomico daoNivelSocioeconomico = DaoFactory.DaoNivelSocioeconomicoInstancia();
            this.estudio.setNivelSocioEconomico(daoNivelSocioeconomico.find(dtoEstudio.getNivelSocioEconomico().get_id(), NivelSocioeconomicoEntity.class));
        }

        if(dtoEstudio.getLugar() != null) {
            DaoLugar daoLugar = new DaoLugar();
            this.estudio.setLugar(daoLugar.find(dtoEstudio.getLugar().get_id(), LugarEntity.class));
        }

        if(dtoEstudio.getAnalista() != null) {
            DaoUsuario daoAnalista = DaoFactory.DaoUsuarioInstancia();
            this.estudio.setAnalista(daoAnalista.find(dtoEstudio.getAnalista().get_id(), UsuarioEntity.class));
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio
     * Api del tipo get encargada retornar todos los estudios existentes en el sistema.
     * @return Lista de objetos de tipo EstudioEntity
     * @see EstudioEntity Entidad persistente utilizada para retornar los datos requeridos.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEstudios() {
        try {
            return Response.ok(this.dao.findAll(EstudioEntity.class)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/estudioSinEncuesta
     * Api del tipo get encargada de retornar Estudios que no están asociadas a encuestas.
     * @return Lista de objetos de tipo EstudioEntity
     * @see EstudioEntity Entidad persistente utilizada para retornar los datos requeridos.
     */
    @GET
    @Path("/estudiosSinEncuesta")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEstudiosSinEncuesta() {
        try {
            DaoEstudio daoEstudio = new DaoEstudio();
            List<EstudioEntity> estudios = daoEstudio.getEstudiosSinEncuesta();
            return Response.ok(estudios).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/{id}
     * Api del tipo get utilizada para retornar un estudio en específico.
     * @param id Objeto de tipo long que representa el id del estudio a consultar
     * @return Objeto de tipo EstudioEntity con el que se retornan los datos.
     * @see EstudioEntity Entidad persistente utilizada para retornar los datos requeridos.
     */
    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEstudios(@PathParam("id") long id) {
        try {
            return Response.ok(this.dao.find(id, EstudioEntity.class)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/cliente/{id}
     * Api del tipo get utilizada para retornae todos los estudios hechos para un cliente.
     * @param id objeto de tipo long que representa el id del cliente.
     * @return Lista de objetos de tipo EstudioEntity encargado de retornar los estudios de un cliente en especifico.
     */
    @GET
    @Path("/cliente/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEstudiosCliente(@PathParam("id") long id) {
        try {
            return Response.ok(this.dao.estudiosCliente(id)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/analista/{id}
     * Api del tipo get utilizada para retornar todos los estudios asignados a un analista
     * @param id Objeto de tipo long que representa el id del analista.
     * @return Lista de objetos de tipo Estudio que contiene los estudios del analista.
     */
    @GET
    @Path("/analista/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEstudiosAnalista(@PathParam("id") long id) {
        try {
            return Response.ok(this.dao.estudiosAnalista(id)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio
     * Api del tipo post encargado de añadir un estudio nuevo al sistema.
     * @param dtoEstudio Objeto de tipo DtoEstudio donde se reciben los datos a ingresar al sistema.
     * @return retorna objeto de tipo EstudioEntity.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEstudio(DtoEstudio dtoEstudio) {
        try {
            estudioAtributos(dtoEstudio);
            return Response.ok(this.dao.insert(this.estudio)).build();
        } catch(Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(ex).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/cliente/{id}
     * Api encargada de permitirle a un cliente solicitar un estudio.
     * @param id Objeto de tipo long con el id del cliente
     * @param dtoEstudio Objeto de tipo DtoEstudio utilizado para obtener los datos del estudio.
     * @return Response del tipo ok en caso de quetodo se haya hecho de manera correcta.
     */
    @POST
    @Path("/cliente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response solicitarEstudio(@PathParam("id") long id, DtoEstudio dtoEstudio) {
        try {
            estudioAtributos(dtoEstudio);
            DaoUsuario daoUsuario1 = new DaoUsuario();
            List<UsuarioEntity> listaAnalista = daoUsuario1.getAnalistas();
            int analistaAleatorio =  ThreadLocalRandom.current().nextInt(0, listaAnalista.size()-1);
            this.estudio.setAnalista(listaAnalista.get(analistaAleatorio));
            this.estudio = this.dao.insert(this.estudio);

            DaoClienteEstudio daoClienteEstudio = new DaoClienteEstudio();
            DaoCliente daoCliente = new DaoCliente();
            DaoUsuario daoUsuario = new DaoUsuario();
            this.estudio = this.dao.find(estudio.get_id(), EstudioEntity.class);

            if(daoCliente.getClienteByUsuario(daoUsuario.find(id, UsuarioEntity.class)) == null){
                throw new EstudioException("No existe ningun cliente registrado con ese Username");
            }

            ClienteEntity clienteEntity = daoCliente.getClienteByUsuario(daoUsuario.find(id, UsuarioEntity.class));

            ClienteEstudioEntity clienteEstudioEntity = new ClienteEstudioEntity();
            clienteEstudioEntity.setEstudio(this.estudio);
            clienteEstudioEntity.setCliente(clienteEntity);
            clienteEstudioEntity.setEstado("a");
            daoClienteEstudio.insert(clienteEstudioEntity);

            DaoEncuestado daoEncuestado = new DaoEncuestado();
            List<EncuestadoEntity> dataMuestraEncuestados = null;
            dataMuestraEncuestados = daoEncuestado.getDataMuestraEstudio(estudio.getLugar(), estudio.getNivelSocioEconomico());
            DaoEstudioEncuestado daoEstudioEncuestado = new DaoEstudioEncuestado();
            for(EncuestadoEntity encuestado : dataMuestraEncuestados){
                EstudioEncuestadoEntity estudioEncuestadoEntity = new EstudioEncuestadoEntity();
                estudioEncuestadoEntity.setEstado("Pendiente");
                estudioEncuestadoEntity.setEstudio(this.estudio);
                estudioEncuestadoEntity.setEncuestado(encuestado);

                daoEstudioEncuestado.insert(estudioEncuestadoEntity);
            }

            return Response.ok().build();

        }catch (EstudioException ex) {
            JsonObject excepcion = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage()).build();
            return  Response.status(500).entity(excepcion).build();
        }
        catch(Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(ex).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/{id}
     * Api encargada de modificar un estudio.
     * @param id Objeto de tipo long que representa el id del estudio a modificar.
     * @param dtoEstudio Objeto de tipo DtoEstudio utilizado para obtener los datos de la modificacion
     * @return Objeto de tipo EntudioEntity
     */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEstudio(@PathParam("id") long id, DtoEstudio dtoEstudio) {
        try {
            DaoEstudio daoEstudio = new DaoEstudio();
            EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);

            estudio.setComentarioAnalista(dtoEstudio.getComentarioAnalista());
            estudio.setEstado(dtoEstudio.getEstado());

            if(dtoEstudio.getFechaFin() != null){
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaFin = dateFormat.parse(dtoEstudio.getFechaFin());
                estudio.setFechaFin(fechaFin);
            }else{
                estudio.setFechaFin(null);
            }

            daoEstudio.update(estudio);

            return Response.ok(estudio).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/updateAdmin/{id}
     * @param id Objeto de tipo long que representa el id del estudio
     * @param dtoEstudio Objeto de tipo Dto estudio que sirve para obtener los datos del Estudio.
     * @return EstudioEntity
     */
    @PUT
    @Path("/updateAdmin/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEstudioAdmin(@PathParam("id") long id, DtoEstudio dtoEstudio) {
        try {
            DaoEstudio daoEstudio = new DaoEstudio();
            EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);

            estudio.setEstado(dtoEstudio.getEstado());
            estudio.setNombre(dtoEstudio.getNombre());
            estudio.setEdadMinima(dtoEstudio.getEdadMinima());
            estudio.setEdadMaxima(dtoEstudio.getEdadMaxima());

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = dateFormat.parse(dtoEstudio.getFechaInicio());
            estudio.setFechaInicio(fechaInicio);

            DaoLugar daoLugar = new DaoLugar();
            LugarEntity lugarEntity = daoLugar.find(dtoEstudio.getLugar().get_id(), LugarEntity.class);
            estudio.setLugar(lugarEntity);

            DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
            NivelSocioeconomicoEntity nivelSocioeconomicoEntity = daoNivelSocioeconomico.find(dtoEstudio.getNivelSocioEconomico().get_id(), NivelSocioeconomicoEntity.class);
            estudio.setNivelSocioEconomico(nivelSocioeconomicoEntity);

            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
            SubcategoriaEntity subcategoriaEntity = daoSubcategoria.find(dtoEstudio.getSubcategoria().get_id(), SubcategoriaEntity.class);
            estudio.setSubcategoria(subcategoriaEntity);

            DaoUsuario daoUsuario = new DaoUsuario();
            estudio.setAnalista(daoUsuario.find(dtoEstudio.getAnalista().get_id(), UsuarioEntity.class));

            daoEstudio.update(estudio);

            return Response.ok(estudio).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/updateCliente/{id}
     * Api del tipo PUT utilizada para actualizar los datos del estudio de un cliente.
     * @param id Objeto de tipob long que representa el id del estudio a actualizar.
     * @param dtoEstudio Objeto de tipo DtoEstudio con el cual se obtienen los datos del estudio.
     * @return Objeto de tipo EstudioEntity
     */
    @PUT
    @Path("/updateCliente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEstudioCliente(@PathParam("id") long id, DtoEstudio dtoEstudio) {
        try {
            DaoEstudio daoEstudio = new DaoEstudio();
            EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);

            estudio.setEstado(dtoEstudio.getEstado());
            estudio.setNombre(dtoEstudio.getNombre());
            estudio.setEdadMinima(dtoEstudio.getEdadMinima());
            estudio.setEdadMaxima(dtoEstudio.getEdadMaxima());

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = dateFormat.parse(dtoEstudio.getFechaInicio());
            estudio.setFechaInicio(fechaInicio);

            DaoLugar daoLugar = new DaoLugar();
            LugarEntity lugarEntity = daoLugar.find(dtoEstudio.getLugar().get_id(), LugarEntity.class);
            estudio.setLugar(lugarEntity);

            DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
            NivelSocioeconomicoEntity nivelSocioeconomicoEntity = daoNivelSocioeconomico.find(dtoEstudio.getNivelSocioEconomico().get_id(), NivelSocioeconomicoEntity.class);
            estudio.setNivelSocioEconomico(nivelSocioeconomicoEntity);

            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
            SubcategoriaEntity subcategoriaEntity = daoSubcategoria.find(dtoEstudio.getSubcategoria().get_id(), SubcategoriaEntity.class);
            estudio.setSubcategoria(subcategoriaEntity);

            daoEstudio.update(estudio);

            return Response.ok(estudio).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/{id}
     * Api del tipo DELETE utilizada para borrar un estudio existente en base de datos.
     * @param id Objeto de tipo long con el id del estudio a eliminar.
     * @return Objeto de tipo EstudioEntity
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEstudio(@PathParam("id") long id) {
        try {
            this.estudio = this.dao.find(id, EstudioEntity.class);
            return Response.ok(this.dao.delete(this.estudio)).build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/encuestado/{id}
     * Permite filtrar los estudios que puede ver el encuestado según las características del estudio.
     * @param id Objeto de tipo long con el id del usuario encuestado.
     * @return Lista de objetos de tipo EstudioAux
     */
    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/encuestado/{id}")                   //RECIBO EL ID DEL USUARIO
    public Response getEstudiosbyEncuestado(@PathParam("id") long id){

        /*PERMITE FILTRAR LOS ESTUDIOS QUE PUEDE VER EL ENCUESTADO
        SEGUN LAS CARACTERISTICAS DEL ESTUDIO
         */

        List<EstudioEntity> estudios = null;
        List<EstudioAux> estudioAuxList = new ArrayList<EstudioAux>(); //entidad que incluye el estado de la n-n estudioEncuestado
        try {
            DaoUsuario daoUsuario = new DaoUsuario();
            DaoEncuestado daoEncuestado = new DaoEncuestado();
            DaoEstudioEncuestado daoEstudioEncuestado = new DaoEstudioEncuestado();
            UsuarioEntity usuarioEntity = daoUsuario.find(id, UsuarioEntity.class);

            EncuestadoEntity encuestadoEntity = daoEncuestado.getEncuestadoByUsuario(usuarioEntity);

            DaoEstudio daoEstudio = new DaoEstudio();
            estudios = daoEstudio.getEstudiosEncuestado(encuestadoEntity);

            if(estudios == null){
                throw new EstudioException("No existen estudios relacionados con el Encuestado");
            }

            //llenado del aux para que tenga el estado de la n a n
            for(EstudioEntity estudio : estudios){
                EstudioAux estudioAux = new EstudioAux(estudio.get_id());

                estudioAux.setNombre(estudio.getNombre());
                estudioAux.setAnalista(estudio.getAnalista());
                estudioAux.setComentarioAnalista(estudio.getComentarioAnalista());
                estudioAux.setEdadMinima(estudio.getEdadMinima());
                estudioAux.setEdadMaxima(estudio.getEdadMaxima());
                estudioAux.setNivelSocioEconomico(estudio.getNivelSocioEconomico());
                estudioAux.setSubcategoria(estudio.getSubcategoria());
                estudioAux.setLugar(estudio.getLugar());
                estudioAux.setFechaInicio(estudio.getFechaInicio());
                estudioAux.setFechaFin(estudio.getFechaFin());


                EstudioEncuestadoEntity estudioEncuestado = daoEstudioEncuestado.getEstudioEncuestado(encuestadoEntity, estudio);
                estudioAux.setEstadoEstudioEncuestado(estudioEncuestado.getEstado());

                estudioAuxList.add(estudioAux);
            }

        }catch (EstudioException ex) {
            JsonObject excepcion = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage()).build();
            return  Response.status(500).entity(excepcion).build();
        }
        catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(estudioAuxList).build();
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/dataMuestra/{id}
     * Api que permite retornar la data muestra del estudio en cuestión.
     * @param id Objeto de tipo long con el id del estudio.
     * @return Lista de objetos del tipo EscuestadoEntity
     */
    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/dataMuestra/{id}")                   //RECIBO EL ID DEL ESTUDIO
    public Response getDataMuestraEstudio(@PathParam("id") long id) throws PruebaExcepcion {

        /*PERMITE DEVOLVER LA DATA MUESTRA ESTUDIO
         */

        List<EncuestadoEntity> dataMuestraEncuestados = null;
        try {
            DaoUsuario daoUsuario = new DaoUsuario();

            DaoEstudio daoEstudio = new DaoEstudio();
            EstudioEntity estudio = daoEstudio.find(id, EstudioEntity.class);

            DaoEncuestado daoEncuestado = new DaoEncuestado();
            dataMuestraEncuestados = daoEncuestado.getDataMuestraEstudio(estudio.getLugar(), estudio.getNivelSocioEconomico());

        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(dataMuestraEncuestados).build();
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/resultadoEstudio/id
     * Metodo con anotacion GET que permite obtener todas las preguntas con sus opciones (encuesta) de un estudio
     * @param id identificador del estudio para obtener las preguntas con opciones (encuesta)
     * @return Response con status ok con la lista de preguntas con opciones (encuesta) asignadas a un estudio.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/resultadoEstudio/{id}")
    public List<PreguntaAux> getResultadoEstudio(@PathParam("id") long id) {

        List<PreguntaEntity> preguntas = null;
        List<PreguntaAux> preguntaAuxList = new ArrayList<PreguntaAux>();
        DaoOpcion daoOpcion = new DaoOpcion();
        long contadorRespuestas;

        try {
            DaoPregunta dao = new DaoPregunta();
            DaoRespuesta daoRespuesta = new DaoRespuesta();
            preguntas = dao.getPreguntasbyEstudio(id);

            for(PreguntaEntity pregunta : preguntas){
                PreguntaAux preguntaAux = new PreguntaAux(pregunta.get_id());
                List<OpcionEntity> opciones = daoOpcion.getOpciones(pregunta);
                List<OpcionAux> opcionAuxList = new ArrayList<OpcionAux>();

                preguntaAux.setDescripcion(pregunta.getDescripcion());
                preguntaAux.setTipo(pregunta.getTipo());

                for(OpcionEntity opcion : opciones){
                    OpcionAux opcionAux = new OpcionAux(opcion.get_id());
                    opcionAux.setDescripcion(opcion.getDescripcion());
                    opcionAux.setEstado(opcion.getEstado());
                    contadorRespuestas = daoRespuesta.getCantidadRespuestas(id, opcion);
                    opcionAux.setValor(contadorRespuestas);

                    opcionAuxList.add(opcionAux);
                }

                preguntaAux.setOpcionesResultado(opcionAuxList);

                preguntaAuxList.add(preguntaAux);
            }
            return preguntaAuxList;
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return null;
    }

}
