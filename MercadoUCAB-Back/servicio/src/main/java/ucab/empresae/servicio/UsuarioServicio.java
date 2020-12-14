package ucab.empresae.servicio;

import ucab.empresae.daos.DaoCliente;
import ucab.empresae.daos.DaoEncuestado;
import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.entidades.ClienteEntity;
import ucab.empresae.entidades.EncuestadoEntity;
import ucab.empresae.entidades.UsuarioEntity;
import ucab.empresae.excepciones.PruebaExcepcion;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuario")
public class UsuarioServicio {

    @GET
    @Produces(value= MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getUsuario(@PathParam("id") long id) throws PruebaExcepcion {

        DaoUsuario dao = new DaoUsuario();
        UsuarioEntity usuarioEntity = dao.find(id, UsuarioEntity.class);
        Response usuario = null;

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

            usuario = Response.ok(usuarioEntity).build();

        }

        return usuario;
    }
}
