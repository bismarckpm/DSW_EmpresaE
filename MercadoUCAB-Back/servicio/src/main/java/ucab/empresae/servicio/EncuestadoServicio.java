package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoEncuestado;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoTipoUsuario;
import ucab.empresae.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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

        EstadoCivilEntity estadoCivilEntity = daoEstadoCivil.find(dtoEncuestado.getEstadoCivil().getId(), EstadoCivilEntity.class);
        encuestadoEntity.setEdocivil(estadoCivilEntity);

        NivelAcademicoEntity nivelAcademicoEntity = daoNivelAcademico.find(dtoEncuestado.getNivelAcademico().getId(), NivelAcademicoEntity.class);
        encuestadoEntity.setNivelacademico(nivelAcademicoEntity);

        MedioConexionEntity medioConexionEntity = daoMedioConexion.find(dtoEncuestado.getMedioConexion().getId(), MedioConexionEntity.class);
        encuestadoEntity.setMedioconexion(medioConexionEntity);

        GeneroEntity generoEntity = daoGenero.find(dtoEncuestado.getGenero().getId(), GeneroEntity.class);
        encuestadoEntity.setGenero(generoEntity);

        OcupacionEntity ocupacionEntity = daoOcupacion.find(dtoEncuestado.getOcupacion().getId(), OcupacionEntity.class);
        encuestadoEntity.setOcupacion(ocupacionEntity);

        NivelSocioeconomicoEntity nivelSocioeconomicoEntity = daoNivelSocioeconomico.find(dtoEncuestado.getNivelSocioeco().getId(), NivelSocioeconomicoEntity.class);
        encuestadoEntity.setNivelsocioeco(nivelSocioeconomicoEntity);

        LugarEntity lugarEntity = daoLugar.find(dtoEncuestado.getLugar().getId(), LugarEntity.class);
        encuestadoEntity.setLugar(lugarEntity);

        usuarioEntity.setUsername(dtoEncuestado.getUsuario().getUsername());
        usuarioEntity.setClave(dtoEncuestado.getUsuario().getClave());
        usuarioEntity.setEstado(dtoEncuestado.getUsuario().getEstado());
        usuarioEntity.setTipousuario(daoTipoUsuario.find(tipoUsuario, TipoUsuarioEntity.class));
        daoUsuario.insert(usuarioEntity);

        encuestadoEntity.setUsuario(usuarioEntity);

        EncuestadoEntity resul = dao.insert(encuestadoEntity);

        DirectorioActivo ldap = new DirectorioActivo();
        ldap.addEntryToLdap( dtoEncuestado.getUsuario(), rol );

        return Response.ok(encuestadoEntity).build();
    }

}
