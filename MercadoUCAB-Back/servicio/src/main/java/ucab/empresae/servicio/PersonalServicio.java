package ucab.empresae.servicio;

import ucab.empresae.daos.Dao;
import ucab.empresae.daos.DaoTipo;
import ucab.empresae.daos.DaoTipoUsuario;
import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.entidades.TipoUsuarioEntity;
import ucab.empresae.entidades.UsuarioEntity;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/personal")
public class PersonalServicio {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPersonal(DtoUsuario dtoUsuario){

        DaoUsuario daoUsuario = new DaoUsuario();
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        DaoTipoUsuario daoTipoUsuario = new DaoTipoUsuario();
        TipoUsuarioEntity tipoUsuarioEntity = daoTipoUsuario.getTipoUsuarioByDescripcion(dtoUsuario.getTipoUsuario().getDescripcion());

        usuarioEntity.setEstado("a");
        usuarioEntity.setUsername(dtoUsuario.getUsername());
        usuarioEntity.setClave(dtoUsuario.getClave());
        usuarioEntity.setTipousuario(tipoUsuarioEntity);
        daoUsuario.insert(usuarioEntity);

        DirectorioActivo ldap = new DirectorioActivo();
        ldap.addEntryToLdap(dtoUsuario, tipoUsuarioEntity.getDescripcion());

        return Response.ok(usuarioEntity).build();
    }

}
