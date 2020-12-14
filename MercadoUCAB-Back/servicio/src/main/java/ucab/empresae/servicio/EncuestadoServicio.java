package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.*;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.PruebaExcepcion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
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

        DaoTelefono daoTelefono = new DaoTelefono();
        TelefonoEntity telefonoEntity = new TelefonoEntity();
        telefonoEntity.setNumero(dtoEncuestado.getTelefono().getNumero());
        telefonoEntity.setEstado("a");
        telefonoEntity.setEncuestado(encuestadoEntity);
        daoTelefono.insert(telefonoEntity);

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
    public Response getEncuestado(@PathParam("id") long id) throws PruebaExcepcion {
        DaoEncuestado dao = new DaoEncuestado();
        EncuestadoEntity encuestadoEntity = dao.find(id, EncuestadoEntity.class);

        if(encuestadoEntity != null) {

            DtoEncuestado dtoEncuestado = new DtoEncuestado();
            dtoEncuestado.setEstado(encuestadoEntity.getEstado());
            dtoEncuestado.setPrimerNombre(encuestadoEntity.getPrimerNombre());
            dtoEncuestado.setSegundoNombre(encuestadoEntity.getSegundoNombre());
            dtoEncuestado.setPrimerApellido(encuestadoEntity.getPrimerApellido());
            dtoEncuestado.setSegundoApellido(encuestadoEntity.getSegundoApellido());
            DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            dtoEncuestado.setFechaNacimiento(dateFormat.format(encuestadoEntity.getFechaNacimiento()));

            DtoEstadoCivil dtoEstadoCivil = new DtoEstadoCivil();
            dtoEstadoCivil.set_id(encuestadoEntity.getEstadoCivil().get_id());
            dtoEstadoCivil.setEstado(encuestadoEntity.getEstadoCivil().getEstado());
            dtoEstadoCivil.setNombre(encuestadoEntity.getEstadoCivil().getNombre());
            dtoEncuestado.setEstadoCivil(dtoEstadoCivil);

            DtoNivelAcademico dtoNivelAcademico = new DtoNivelAcademico();
            dtoNivelAcademico.set_id(encuestadoEntity.getNivelacademico().get_id());
            dtoNivelAcademico.setEstado(encuestadoEntity.getNivelacademico().getEstado());
            dtoNivelAcademico.setNombre(encuestadoEntity.getNivelacademico().getNombre());
            dtoEncuestado.setNivelAcademico(dtoNivelAcademico);

            DtoMedioConexion dtoMedioConexion = new DtoMedioConexion();
            dtoMedioConexion.set_id(encuestadoEntity.getMedioconexion().get_id());
            dtoMedioConexion.setEstado(encuestadoEntity.getMedioconexion().getEstado());
            dtoMedioConexion.setNombre(encuestadoEntity.getMedioconexion().getNombre());
            dtoEncuestado.setMedioConexion(dtoMedioConexion);

            DtoGenero dtoGenero = new DtoGenero();
            dtoGenero.set_id(encuestadoEntity.getGenero().get_id());
            dtoGenero.setEstado(encuestadoEntity.getGenero().getEstado());
            dtoGenero.setNombre(encuestadoEntity.getGenero().getNombre());
            dtoEncuestado.setGenero(dtoGenero);

            DtoOcupacion dtoOcupacion = new DtoOcupacion();
            dtoOcupacion.set_id(encuestadoEntity.getOcupacion().get_id());
            dtoOcupacion.setEstado(encuestadoEntity.getOcupacion().getEstado());
            dtoOcupacion.setNombre(encuestadoEntity.getOcupacion().getNombre());
            dtoEncuestado.setOcupacion(dtoOcupacion);

            DtoNivelSocioEconomico dtoNivelSocioEconomico = new DtoNivelSocioEconomico();
            dtoNivelSocioEconomico.set_id(encuestadoEntity.getNivelsocioeco().get_id());
            dtoNivelSocioEconomico.setEstado(encuestadoEntity.getNivelsocioeco().getEstado());
            dtoNivelSocioEconomico.setDescripcion(encuestadoEntity.getNivelsocioeco().getDescripcion());
            dtoNivelSocioEconomico.setNombre(encuestadoEntity.getNivelsocioeco().getNombre());
            dtoEncuestado.setNivelSocioEconomico(dtoNivelSocioEconomico);

            DtoLugar dtoParroquia = new DtoLugar();
            dtoParroquia.set_id(encuestadoEntity.getLugar().get_id());
            dtoParroquia.setEstado(encuestadoEntity.getLugar().getEstado());
            dtoParroquia.setNombre(encuestadoEntity.getLugar().getNombre());
            dtoParroquia.setTipo(encuestadoEntity.getLugar().getTipo());

            DtoLugar dtoMunicipio = new DtoLugar();
            dtoMunicipio.set_id(encuestadoEntity.getLugar().getLugar().get_id());
            dtoMunicipio.setEstado(encuestadoEntity.getLugar().getLugar().getEstado());
            dtoMunicipio.setNombre(encuestadoEntity.getLugar().getLugar().getNombre());
            dtoMunicipio.setTipo(encuestadoEntity.getLugar().getLugar().getTipo());
            dtoMunicipio.setLugar(dtoParroquia);

            DtoLugar dtoPais = new DtoLugar();
            dtoPais.set_id(encuestadoEntity.getLugar().getLugar().getLugar().get_id());
            dtoPais.setEstado(encuestadoEntity.getLugar().getLugar().getLugar().getEstado());
            dtoPais.setNombre(encuestadoEntity.getLugar().getLugar().getLugar().getNombre());
            dtoPais.setTipo(encuestadoEntity.getLugar().getLugar().getLugar().getTipo());
            dtoPais.setLugar(dtoMunicipio);
            dtoEncuestado.setLugar(dtoPais);

            DtoUsuario dtoUsuario = new DtoUsuario();
            dtoUsuario.set_id(encuestadoEntity.getUsuario().get_id());
            dtoUsuario.setEstado(encuestadoEntity.getUsuario().getEstado());
            dtoUsuario.setUsername(encuestadoEntity.getUsuario().getUsername());

            DirectorioActivo ldap = new DirectorioActivo();
            String correo = ldap.getCorreo(dtoUsuario);
            dtoUsuario.setCorreoelectronico(correo);

            dtoEncuestado.setUsuario(dtoUsuario);

            DaoTelefono daoTelefono = new DaoTelefono();
            TelefonoEntity telefonoEntity = daoTelefono.getTelefonoByEncuestado(encuestadoEntity);
            DtoTelefono dtoTelefono = new DtoTelefono();
            dtoTelefono.set_id(telefonoEntity.get_id());
            dtoTelefono.setNumero(telefonoEntity.getNumero());

            dtoEncuestado.setTelefono(dtoTelefono);

            return Response.ok(dtoEncuestado).build();
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
            DtoUsuario dtoUsuario = new DtoUsuario();
            dtoUsuario.setUsername(encuestadoEntity.getUsuario().getUsername());

            DaoTelefono daoTelefono = new DaoTelefono();
            TelefonoEntity telefonoEntity = daoTelefono.getTelefonoByEncuestado(encuestadoEntity);
            daoTelefono.delete(telefonoEntity);

            DirectorioActivo ldap = new DirectorioActivo();
            ldap.deleteEntry(dtoUsuario);

            EncuestadoEntity escuestadoResul = daoEncuestado.delete(encuestadoEntity);
            UsuarioEntity usuarioResul = daoUsuario.delete(usuarioEntity);

            return Response.ok().build();

        }catch (Exception ex){
            String problema = ex.getMessage();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
