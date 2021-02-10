package ucab.empresae.servicio;

import ucab.empresae.daos.DaoCliente;
import ucab.empresae.daos.DaoEncuestado;
import ucab.empresae.daos.DaoTipoUsuario;
import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.dtos.DtoTipoUsuario;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.entidades.ClienteEntity;
import ucab.empresae.entidades.EncuestadoEntity;
import ucab.empresae.entidades.TipoUsuarioEntity;
import ucab.empresae.entidades.UsuarioEntity;
import ucab.empresae.excepciones.UsuarioException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad Usuario y LDAP
 */

@Path("/usuario")
public class UsuarioServicio {


    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/usuario
     * Metodo con anotacion POST que recibe un DtoUsuario y crea el objeto usuario con los atributos en el DTO
     * @param dtoUsuario objeto que posee todos los atributos necesarios para crear un usuario empleado (Administrador/Analista)
     * @return Response con status ok al crear el usuario con la informacion suministrada
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuarioEmpleado(DtoUsuario dtoUsuario){

            DaoUsuario daoUsuario = new DaoUsuario();
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            DaoTipoUsuario daoTipoUsuario = new DaoTipoUsuario();

        try{
            TipoUsuarioEntity tipoUsuarioEntity = daoTipoUsuario.getTipoUsuarioByDescripcion(dtoUsuario.getTipoUsuario().getDescripcion());

            if(daoUsuario.getUsuarioByUsername(dtoUsuario.getUsername()) != null){
                throw new UsuarioException("Ya existe un usuario con ese Username");
            }

            usuarioEntity.setEstado(dtoUsuario.getEstado());
            usuarioEntity.setUsername(dtoUsuario.getUsername());
            usuarioEntity.setClave(dtoUsuario.getClave());
            usuarioEntity.setTipousuario(tipoUsuarioEntity);
            daoUsuario.insert(usuarioEntity);

            DirectorioActivo ldap = new DirectorioActivo();
            ldap.addEntryToLdap(dtoUsuario, tipoUsuarioEntity.getDescripcion());

        }catch (UsuarioException ex) {
            JsonObject excepcion = Json.createObjectBuilder()
                    .add("mensaje", ex.getMessage()).build();
            return  Response.status(500).entity(excepcion).build();
        }
        catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(usuarioEntity).build();
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/usuario/id
     * Metodo con anotacion GET que devuelve todos los datos de un usuario a partir de su id
     * @param id identificador del usuario del que se quieren obtener todos sus datos
     * @return Response con status ok junto a los datos del usuario consultado
     */
    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getUsuario(@PathParam("id") long id) {

        DaoUsuario dao = new DaoUsuario();
        DtoUsuario dtoUsuario = new DtoUsuario();
        Response usuario = null;

        try{
            UsuarioEntity usuarioEntity = dao.find(id, UsuarioEntity.class);

            if(usuarioEntity.getTipousuario().getDescripcion().equals("Encuestado")){

                DaoEncuestado daoEncuestado = new DaoEncuestado();
                EncuestadoEntity encuestadoEntity = daoEncuestado.getEncuestadoByUsuario(usuarioEntity);
                EncuestadoServicio encuestadoServicio = new EncuestadoServicio();
                usuario = encuestadoServicio.getEncuestado(encuestadoEntity.get_id());

            }else if(usuarioEntity.getTipousuario().getDescripcion().equals("Cliente")){

                DaoCliente daoCliente = new DaoCliente();
                ClienteEntity clienteEntity = daoCliente.getClienteByUsuario(usuarioEntity);
                ClienteServicio clienteServicio = new ClienteServicio();
                usuario = clienteServicio.getCliente(clienteEntity.get_id());

            }else if(usuarioEntity.getTipousuario().getDescripcion().equals("Administrador") ||
                    usuarioEntity.getTipousuario().getDescripcion().equals("Analista")){

                dtoUsuario.set_id(usuarioEntity.get_id());
                dtoUsuario.setUsername(usuarioEntity.getUsername());

                DirectorioActivo ldap = new DirectorioActivo();
                String correo = ldap.getCorreo(dtoUsuario);
                dtoUsuario.setCorreoelectronico(correo);

                DtoTipoUsuario dtoTipoUsuario = new DtoTipoUsuario();
                dtoTipoUsuario.set_id(usuarioEntity.getTipousuario().get_id());
                dtoTipoUsuario.setDescripcion(usuarioEntity.getTipousuario().getDescripcion());
                dtoUsuario.setTipoUsuario(dtoTipoUsuario);

                usuario = Response.ok(dtoUsuario).build();
            }

            return usuario;

        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/usuario/empleados
     * Metodo con anotacion GET que devuelve todos los usuarios Analistas y Administradores
     * @return Response con status ok con la lista de Usuarios.
     */
    @GET
    @Path("/empleados")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEmpleados() {
        List<UsuarioEntity> empleados;
        try {
            DaoUsuario daoUsuario = new DaoUsuario();
            empleados = daoUsuario.getUsuariosEmpleados();

            if(empleados == null){
                throw new UsuarioException("Fallo obtener la lista de empleados");
            }

        }catch (UsuarioException ex){
            return Response.status(500).entity(ex.getMessage()).build();
        }
        catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(empleados).build();
    }


    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/usuario/updateAdmin/id
     * Metodo con anotacion PUT que se encarga de actualizar usuarios desde la vista de un administrador (Solo modifica el estado y el rol)
     * @param id identificador del usuario a ser actualizado
     * @param dtoUsuario objeto que contiene los atributos que seran actualizados
     * @return Response con status ok y el usuario actualizado en caso de que la transacción haya sido exitosa
     */
    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/updateAdmin/{id}")
    public Response updateUsuarioVistaAdmin(@PathParam("id") long id, DtoUsuario dtoUsuario) {
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoTipoUsuario daoTipoUsuario = new DaoTipoUsuario();
        UsuarioEntity usuarioEntity;

        try{
            usuarioEntity = daoUsuario.find(id, UsuarioEntity.class);

            if (usuarioEntity != null){
                usuarioEntity.setEstado(dtoUsuario.getEstado());
                usuarioEntity.setTipousuario(daoTipoUsuario.getTipoUsuarioByDescripcion(dtoUsuario.getTipoUsuario().getDescripcion()));
                UsuarioEntity resul = daoUsuario.update(usuarioEntity);

                DirectorioActivo ldap = new DirectorioActivo();
                ldap.updateRol(dtoUsuario, usuarioEntity.getTipousuario().getDescripcion());
            }
        }catch (Exception e){
            String problema = e.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok().entity(usuarioEntity).build();
    }


    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/usuario/updatePerfil/id
     * Metodo con anotacion PUT que se encarga de actualizar usuarios desde la vista de su perfil
     * @param id identificador del usuario a ser actualizado
     * @param dtoUsuario objeto que contiene los atributos que seran actualizados
     * @return Response con status ok y el usuario actualizado en caso de que la transacción haya sido exitosa
     */
    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/updatePerfil/{id}")
    public Response updateUsuarioVistaPerfil(@PathParam("id") long id, DtoUsuario dtoUsuario) {
        DaoUsuario daoUsuario = new DaoUsuario();

        try{
            UsuarioEntity usuarioEntity = daoUsuario.find(id, UsuarioEntity.class);

            if (usuarioEntity != null){

                DirectorioActivo ldap = new DirectorioActivo();
                ldap.updateEntry(dtoUsuario, usuarioEntity.getTipousuario().getDescripcion());

                return Response.ok().entity(usuarioEntity).build();
            }
            else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        }catch (Exception e){
            String problema = e.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }


    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/usuario/id
     * Metodo con anotacion DELETE que recibe un id y se encarga de eliminar de la base de datos y del LDAP al usuario con ese id
     * @param id identificador del usuario a ser eliminado
     * @return Retorna un Response ok en caso de que el usuario se haya eliminado de manera correcta
     */
    @DELETE
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteUsuario(@PathParam("id") long id) {

        try {
            DaoUsuario daoUsuario = new DaoUsuario();
            DtoUsuario dtoUsuario = new DtoUsuario();
            DirectorioActivo ldap = new DirectorioActivo();
            UsuarioEntity usuarioEntity = daoUsuario.find(id, UsuarioEntity.class);
            if(usuarioEntity != null) {

                dtoUsuario.setUsername(usuarioEntity.getUsername());
                ldap.deleteEntry(dtoUsuario);
                UsuarioEntity resul = daoUsuario.delete(usuarioEntity);

                return Response.ok().build();
            }
        }catch (Exception e){
            String problema = e.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
