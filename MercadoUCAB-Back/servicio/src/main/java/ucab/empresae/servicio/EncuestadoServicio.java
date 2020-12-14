package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoEncuestado;
import ucab.empresae.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Path("/encuestado")
public class EncuestadoServicio {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEncuestado(DtoEncuestado dtoEncuestado) throws ParseException {

        String rol = "Encuestado";
        long tipoUsuario = 1;

        DaoEncuestado dao = new DaoEncuestado();
        DaoEstadoCivil daoEstadoCivil = new DaoEstadoCivil();
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        DaoMedioConexion daoMedioConexion = new DaoMedioConexion();
        DaoGenero daoGenero = new DaoGenero();
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
        DaoLugar daoLugar = new DaoLugar();
        DaoTipoUsuario daoTipoUsuario = new DaoTipoUsuario();

        DaoUsuario daoUsuario = new DaoUsuario();
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        EncuestadoEntity encuestadoEntity = new EncuestadoEntity();

        encuestadoEntity.setPrimerNombre(dtoEncuestado.getPrimerNombre());
        encuestadoEntity.setSegundoNombre(dtoEncuestado.getSegundoNombre());
        encuestadoEntity.setPrimerApellido(dtoEncuestado.getPrimerApellido());
        encuestadoEntity.setSegundoApellido(dtoEncuestado.getSegundoApellido());
        SimpleDateFormat fecha = new SimpleDateFormat("dd/mm/yyyy");
        Date fechaNacimiento = fecha.parse(dtoEncuestado.getFechaNacimiento());
        encuestadoEntity.setFechaNacimiento(fechaNacimiento);
        encuestadoEntity.setEstado(dtoEncuestado.getEstado());

        EstadoCivilEntity estadoCivilEntity = daoEstadoCivil.find(dtoEncuestado.getEstadoCivil().get_id(), EstadoCivilEntity.class);
        encuestadoEntity.setEdocivil(estadoCivilEntity);

        NivelAcademicoEntity nivelAcademicoEntity = daoNivelAcademico.find(dtoEncuestado.getNivelAcademico().get_id(), NivelAcademicoEntity.class);
        encuestadoEntity.setNivelacademico(nivelAcademicoEntity);

        MedioConexionEntity medioConexionEntity = daoMedioConexion.find(dtoEncuestado.getMedioConexion().get_id(), MedioConexionEntity.class);
        encuestadoEntity.setMedioconexion(medioConexionEntity);

        GeneroEntity generoEntity = daoGenero.find(dtoEncuestado.getGenero().get_id(), GeneroEntity.class);
        encuestadoEntity.setGenero(generoEntity);

        OcupacionEntity ocupacionEntity = daoOcupacion.find(dtoEncuestado.getOcupacion().get_id(), OcupacionEntity.class);
        encuestadoEntity.setOcupacion(ocupacionEntity);

        NivelSocioeconomicoEntity nivelSocioeconomicoEntity = daoNivelSocioeconomico.find(dtoEncuestado.getNivelSocioEconomico().get_id(), NivelSocioeconomicoEntity.class);
        encuestadoEntity.setNivelsocioeco(nivelSocioeconomicoEntity);

        LugarEntity lugarEntity = daoLugar.find(dtoEncuestado.getLugar().get_id(), LugarEntity.class);
        encuestadoEntity.setLugar(lugarEntity);

        usuarioEntity.setUsername(dtoEncuestado.getUsuario().getUsername());
        usuarioEntity.setClave(dtoEncuestado.getUsuario().getClave());
        usuarioEntity.setEstado("a");
        usuarioEntity.setTipousuario(daoTipoUsuario.find(tipoUsuario, TipoUsuarioEntity.class));
        daoUsuario.insert(usuarioEntity);

        encuestadoEntity.setUsuario(usuarioEntity);

        EncuestadoEntity resul = dao.insert(encuestadoEntity);

        DirectorioActivo ldap = new DirectorioActivo();
        ldap.addEntryToLdap( dtoEncuestado.getUsuario(), rol );

        return Response.ok(encuestadoEntity).build();
    }


    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEncuestados() {
        List<EncuestadoEntity> encuestados = null;
        try {
            DaoEncuestado dao = new DaoEncuestado();
                encuestados = dao.findAll(EncuestadoEntity.class);
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return Response.ok(encuestados).build();
    }

    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getEncuestado(@PathParam("id") long id)
    {
        DaoEncuestado dao = new DaoEncuestado();
        EncuestadoEntity encuestadoEntity = dao.find(id, EncuestadoEntity.class);

        if(encuestadoEntity != null) {
            return Response.ok(encuestadoEntity).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteEncuestado(@PathParam("id") long id){

        try{
            DaoEncuestado daoEncuestado = new DaoEncuestado();
            EncuestadoEntity encuestadoEntity = daoEncuestado.find(id, EncuestadoEntity.class);

            DaoUsuario daoUsuario = new DaoUsuario();
            UsuarioEntity usuarioEntity = daoUsuario.find(encuestadoEntity.getUsuario().get_id(), UsuarioEntity.class);

            EncuestadoEntity escuestadoResul = daoEncuestado.delete(encuestadoEntity);
            UsuarioEntity usuarioResul = daoUsuario.delete(usuarioEntity);

            return Response.ok().build();

        }catch (Exception ex){
            String problema = ex.getMessage();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
