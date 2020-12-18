package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/estudio")
public class EstudioServicio extends AplicacionBase {

    private DaoEstudio dao = DaoFactory.DaoEstudioInstancia();
    private EstudioEntity estudio = EntidadesFactory.EstudioInstance();

    /**
     * Método encargado de generar el objeto EstudioEntity a partir de un objeto del tipo DtoEstudio
     * @param dtoEstudio recibe un objeto del tipo DtoEstudio
     */
    private void estudioAtributos(DtoEstudio dtoEstudio) {
        this.estudio.setEstado(dtoEstudio.getEstado());
        this.estudio.setNombre(dtoEstudio.getNombre());
        this.estudio.setComentarioAnalista(dtoEstudio.getComentarioAnalista());
        this.estudio.setEdadMinima(dtoEstudio.getEdadMinima());
        this.estudio.setEdadMaxima(dtoEstudio.getEdadMaxima());

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            Date fecha = dateFormat.parse(dtoEstudio.getFechaInicio());
            this.estudio.setFechaInicio(fecha);

            fecha = dateFormat.parse(dtoEstudio.getFechaFin());
            this.estudio.setFechaFin(fecha);
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
     * Api del tipo @GET que se encarga de pedir la lista de todos los estudios de la base de datos
     * @return retorna Response .json con una lista de objetos del tipo EstudioEntity en caso de que la transaccion
     * sea exitosa
     * sea exitosa
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
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio
     * Api de tipo @GET que se encarga de pedir a la base de datos un estudio especifico
     * @param id recibe un un parámetro del tipo long con el id del estudio pedido
     * @return retorna Response .json con objeto del tipo EstudioEntity en caso de que la transaccion
     * sea exitosa
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
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/cliente/id
     * Api de tipo @GET que encargada de pedir todos los estudios solicitados por un cliente en específico
     * @param id recibe un objeto de tipo long con el id del cliente en cuestión
     * @return retorna un Response .json con una lista de objetos del tipo EstudioEntity en caso de que la transaccion
     * sea exitosa
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
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio/analista/id
     * Api de tipo @GET encargada de pedir todos los estudios de los cuales un analista en específico está como encargado
     * @param id recibe un objeto de tipo long con el id del analista en cuestión
     * @return retorna Response .json con una lista de objetos del tipo EstudioEntity en caso de que la transaccion
     * sea exitosa
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
     * Api del tipo @POST encargada de insertar un nuevo estudio a la base de datos
     * @param dtoEstudio recibe un parámetro del tipo DtoEstudio con el estudio a insertar
     * @return retorna Response .json con objeto del tipo EstudioEntity con el estudio insertado  en caso de que la transaccion
     * sea exitosa
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
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio
     * Api del tipo @POST encargada de insertar tanto un nuevo estudio con la solicitud del mismo por parte del cliente
     * @param id recibe objeto del tipo con con el id del cliente
     * @param dtoEstudio recibe objeto de tipo DtoEstudio con el estudio a insertar
     * @return retorna Response .json con el objeto del tipo ClienteEstudioEntity insertado en caso de que la transaccion
     * sea exitosa
     */
    @POST
    @Path("/cliente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response solicitarEstudio(@PathParam("id") long id, DtoEstudio dtoEstudio) {
        try {
            estudioAtributos(dtoEstudio);
            this.estudio = this.dao.insert(this.estudio);

            DaoClienteEstudio daoClienteEstudio = new DaoClienteEstudio();
            DaoCliente daoCliente = new DaoCliente();
            DaoUsuario daoUsuario = new DaoUsuario();
            this.estudio = this.dao.find(estudio.get_id(), EstudioEntity.class);

            ClienteEntity clienteEntity = daoCliente.getClienteByUsuario(daoUsuario.find(id, UsuarioEntity.class));

            ClienteEstudioEntity clienteEstudioEntity = new ClienteEstudioEntity();
            clienteEstudioEntity.setEstudio(this.estudio);
            clienteEstudioEntity.setCliente(clienteEntity);
            clienteEstudioEntity.setEstado("a");
            return Response.ok(daoClienteEstudio.insert(clienteEstudioEntity)).build();

        } catch(Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(ex).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio
     * Api del tipo @PUT encargada de actualizar un Estudio
     * @param dtoEstudio recibe objeto del tipo DtoEstudio con los datos ya modificados
     * @return retorna Response .json con el objeto del tipo EstudioEntity actualizado en caso de que la transaccion
     * sea exitosa
     */
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEstudio(DtoEstudio dtoEstudio) {
        try {
            if(dtoEstudio.get_id() == 0) {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
            this.estudio = this.dao.find(dtoEstudio.get_id(), EstudioEntity.class);
            estudioAtributos(dtoEstudio);
            return Response.ok(this.dao.update(this.estudio)).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/estudio
     * Api del tipo @DELETE encargada de eliminar de la base de datos el estudio seleccionado
     * @param dtoEstudio recibe un objeto del tipo DtoEstudio
     * @return retorna Response .json con objeto del tipo Estudio
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEstudio(DtoEstudio dtoEstudio) {
        try {
            this.estudio = this.dao.find(dtoEstudio.get_id(), EstudioEntity.class);
            return Response.ok(this.dao.delete(this.estudio)).build();
        } catch(Exception ex){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

}
