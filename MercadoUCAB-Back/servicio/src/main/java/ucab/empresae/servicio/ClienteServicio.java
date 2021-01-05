package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.*;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.PruebaExcepcion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad Cliente
 */

@Path("/cliente")
public class ClienteServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/cliente
     * Metodo con anotacion POST que recibe un DtoCliente y crea el objeto Cliente con los atributos en el DTO
     * @param dtoCliente objeto que posee todos los atributos necesarios para crear un Cliente
     * @return Response con status ok al crear el Cliente con la informacion suministrada
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCliente(DtoCliente dtoCliente){

        String rol = "Cliente";
        long tipoUsuario = 2;

        DaoCliente daoCliente = new DaoCliente();
        ClienteEntity clienteEntity = new ClienteEntity();
        try{
            clienteEntity.setEstado(dtoCliente.getEstado());
            clienteEntity.setRazonSocial(dtoCliente.getRazonSocial());
            clienteEntity.setRif(dtoCliente.getRif());

            DaoLugar daoLugar = new DaoLugar();
            LugarEntity lugarEntity = daoLugar.find(dtoCliente.getLugar().get_id(), LugarEntity.class);
            clienteEntity.setLugar(lugarEntity);

            DaoTipoUsuario daoTipoUsuario = new DaoTipoUsuario();
            DaoUsuario daoUsuario = new DaoUsuario();
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity.setUsername(dtoCliente.getUsuario().getUsername());
            usuarioEntity.setClave(dtoCliente.getUsuario().getClave());
            usuarioEntity.setEstado(dtoCliente.getEstado());
            usuarioEntity.setTipousuario(daoTipoUsuario.find(tipoUsuario, TipoUsuarioEntity.class));
            daoUsuario.insert(usuarioEntity);

            clienteEntity.setUsuario(usuarioEntity);
            daoCliente.insert(clienteEntity);

            DaoTelefono daoTelefono = new DaoTelefono();
            TelefonoEntity telefonoEntity = new TelefonoEntity();
            telefonoEntity.setNumero(dtoCliente.getTelefono().getNumero());
            telefonoEntity.setEstado(dtoCliente.getEstado());
            telefonoEntity.setCliente(clienteEntity);
            daoTelefono.insert(telefonoEntity);

            DirectorioActivo ldap = new DirectorioActivo();
            ldap.addEntryToLdap( dtoCliente.getUsuario(), rol );

            return Response.ok(clienteEntity).build();

        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/cliente
     * Metodo con anotacion GET que devuelve todos los Clientes registrados
     * @return Response con status ok con la lista de Clientes.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getClientes() {
        List<ClienteEntity> clientes;
        try {
            DaoCliente dao = new DaoCliente();
            clientes = dao.findAll(ClienteEntity.class);
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(clientes).build();
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/cliente/id
     * Metodo con anotacion GET que devuelve todos los datos de un Cliente a partir de su id
     * @param id identificador del Cliente del que se quieren obtener todos sus datos
     * @return Response con status ok junto a los datos del Cliente consultado
     */
    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getCliente(@PathParam("id") long id){

        DaoCliente dao = new DaoCliente();
        try{
            ClienteEntity clienteEntity = dao.find(id, ClienteEntity.class);
            if(clienteEntity != null) {
                DtoCliente dtoCliente = new DtoCliente();
                dtoCliente.set_id(clienteEntity.get_id());
                dtoCliente.setEstado(clienteEntity.getEstado());
                dtoCliente.setRazonSocial(clienteEntity.getRazonSocial());
                dtoCliente.setRif(clienteEntity.getRif());

                DtoLugar dtoParroquia = new DtoLugar();
                dtoParroquia.set_id(clienteEntity.getLugar().get_id());
                dtoParroquia.setEstado(clienteEntity.getLugar().getEstado());
                dtoParroquia.setNombre(clienteEntity.getLugar().getNombre());
                dtoParroquia.setTipo(clienteEntity.getLugar().getTipo());

                DtoLugar dtoMunicipio = new DtoLugar();
                dtoMunicipio.set_id(clienteEntity.getLugar().getLugar().get_id());
                dtoMunicipio.setEstado(clienteEntity.getLugar().getLugar().getEstado());
                dtoMunicipio.setNombre(clienteEntity.getLugar().getLugar().getNombre());
                dtoMunicipio.setTipo(clienteEntity.getLugar().getLugar().getTipo());
                dtoMunicipio.setLugar(dtoParroquia);

                DtoLugar dtoPais = new DtoLugar();
                dtoPais.set_id(clienteEntity.getLugar().getLugar().getLugar().get_id());
                dtoPais.setEstado(clienteEntity.getLugar().getLugar().getLugar().getEstado());
                dtoPais.setNombre(clienteEntity.getLugar().getLugar().getLugar().getNombre());
                dtoPais.setTipo(clienteEntity.getLugar().getLugar().getLugar().getTipo());
                dtoPais.setLugar(dtoMunicipio);

                dtoCliente.setLugar(dtoPais);

                DtoUsuario dtoUsuario = new DtoUsuario();
                dtoUsuario.set_id(clienteEntity.getUsuario().get_id());
                dtoUsuario.setEstado(clienteEntity.getUsuario().getEstado());
                dtoUsuario.setUsername(clienteEntity.getUsuario().getUsername());

                DirectorioActivo ldap = new DirectorioActivo();
                String correo = ldap.getCorreo(dtoUsuario);
                dtoUsuario.setCorreoelectronico(correo);

                dtoCliente.setUsuario(dtoUsuario);

                DaoTelefono daoTelefono = new DaoTelefono();
                TelefonoEntity telefonoEntity = daoTelefono.getTelefonoByCliente(clienteEntity);
                DtoTelefono dtoTelefono = new DtoTelefono();
                dtoTelefono.set_id(telefonoEntity.get_id());
                dtoTelefono.setNumero(telefonoEntity.getNumero());

                dtoCliente.setTelefono(dtoTelefono);

                return Response.ok(dtoCliente).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/cliente/id
     * Metodo con anotacion DELETE que recibe un id y se encarga de eliminar de la base de datos y del LDAP el Cliente con ese id
     * @param id identificador del Cliente a ser eliminado
     * @return Retorna un Response ok en caso de que el Cliente se haya eliminado de manera correcta
     */
    @DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteCliente(@PathParam("id") long id){

        try{
            DaoCliente daoCliente = new DaoCliente();
            ClienteEntity clienteEntity = daoCliente.find(id, ClienteEntity.class);

            if(clienteEntity != null) {
                DaoUsuario daoUsuario = new DaoUsuario();
                UsuarioEntity usuarioEntity = daoUsuario.find(clienteEntity.getUsuario().get_id(), UsuarioEntity.class);
                DtoUsuario dtoUsuario = new DtoUsuario();
                dtoUsuario.setUsername(clienteEntity.getUsuario().getUsername());

                DaoTelefono daoTelefono = new DaoTelefono();
                TelefonoEntity telefonoEntity = daoTelefono.getTelefonoByCliente(clienteEntity);
                daoTelefono.delete(telefonoEntity);

                DirectorioActivo ldap = new DirectorioActivo();
                ldap.deleteEntry(dtoUsuario);

                ClienteEntity clienteResul = daoCliente.delete(clienteEntity);
                UsuarioEntity usuarioResul = daoUsuario.delete(usuarioEntity);

                return Response.ok().build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/cliente/id
     * Metodo con anotacion PUT que se encarga de actualizar Clientes
     * @param id identificador del Cliente a ser actualizado
     * @param dtoCliente objeto que contiene los atributos que seran actualizados
     * @return Response con status ok y el Cliente actualizado en caso de que la transacción haya sido exitosa
     */
    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateCliente(@PathParam("id") long id, DtoCliente dtoCliente) {
        DaoCliente dao = new DaoCliente();
        try{
            ClienteEntity clienteEntity = dao.find(id, ClienteEntity.class);

            if (clienteEntity != null){

                clienteEntity.setEstado(dtoCliente.getEstado());
                clienteEntity.setRazonSocial(dtoCliente.getRazonSocial());

                DaoLugar daoLugar = new DaoLugar();
                LugarEntity lugarEntity = daoLugar.find(dtoCliente.getLugar().get_id(), LugarEntity.class);
                clienteEntity.setLugar(lugarEntity);

                DaoTelefono daoTelefono = new DaoTelefono();
                TelefonoEntity telefonoEntity = daoTelefono.getTelefonoByCliente(clienteEntity);
                telefonoEntity.setNumero(dtoCliente.getTelefono().getNumero());
                telefonoEntity.setEstado(dtoCliente.getEstado());
                telefonoEntity.setCliente(clienteEntity);
                TelefonoEntity resulTlf = daoTelefono.update(telefonoEntity);

                DtoUsuario dtoUsuario = new DtoUsuario();
                dtoUsuario.setEstado(dtoCliente.getEstado());
                dtoUsuario.setUsername(dtoCliente.getUsuario().getUsername());
                dtoUsuario.setCorreoelectronico(dtoCliente.getUsuario().getCorreoelectronico());
                DirectorioActivo ldap = new DirectorioActivo();
                ldap.updateEntry(dtoUsuario, "Cliente");

                ClienteEntity resul = dao.update(clienteEntity);
                return Response.ok().entity(clienteEntity).build();
            }
            else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/cliente/update/id
     * Metodo con anotacion PUT que se encarga de actualizar el estado de un Cliente
     * @param id identificador del Cliente a ser actualizado
     * @param dtoCliente objeto que contiene el estado qeu sera actualizado
     * @return Response con status ok y el Cliente actualizado en caso de que la transacción haya sido exitosa
     */
    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateEstadoCliente(@PathParam("id") long id, DtoCliente dtoCliente) {
        DaoCliente dao = new DaoCliente();
        try{
            ClienteEntity clienteEntity = dao.find(id, ClienteEntity.class);
            if (clienteEntity != null){
                clienteEntity.setEstado(dtoCliente.getEstado());
                ClienteEntity resul = dao.update(clienteEntity);
                return Response.ok().entity(clienteEntity).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

}
