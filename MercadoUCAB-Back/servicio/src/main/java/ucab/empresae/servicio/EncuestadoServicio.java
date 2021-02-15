package ucab.empresae.servicio;

import ucab.empresae.daos.*;
import ucab.empresae.dtos.*;
import ucab.empresae.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * API service encargada de realizar transacciones sobre la entidad Encuestado
 */

@Path("/encuestado")
public class EncuestadoServicio {

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/encuestado
     * Metodo con anotacion POST que recibe un DtoEncuestado y crea el objeto Encuestado con los atributos en el DTO
     * @param dtoEncuestado objeto que posee todos los atributos necesarios para crear un Encuestado
     * @return Response con status ok al crear el Encuestado con la informacion suministrada
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEncuestado(DtoEncuestado dtoEncuestado) throws Exception{
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

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(dtoEncuestado.getFechaNacimiento());

            encuestadoEntity.setFechaNacimiento(fechaNacimiento);
            encuestadoEntity.setEstado("a");

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

            DaoTelefono daoTelefono = new DaoTelefono();
            TelefonoEntity telefonoEntity = new TelefonoEntity();
            telefonoEntity.setNumero(dtoEncuestado.getTelefono().getNumero());
            telefonoEntity.setEstado("a");
            telefonoEntity.setEncuestado(encuestadoEntity);
            daoTelefono.insert(telefonoEntity);


            DirectorioActivo ldap = new DirectorioActivo();
            ldap.addEntryToLdap( dtoEncuestado.getUsuario(), rol );

            List<EstudioEntity> estudios;
            DaoEstudio daoEstudio = new DaoEstudio();
            estudios = daoEstudio.getEstudios(lugarEntity, nivelSocioeconomicoEntity);
            DaoEstudioEncuestado daoEstudioEncuestado = new DaoEstudioEncuestado();
            for(EstudioEntity estudio : estudios){
                EstudioEncuestadoEntity estudioEncuestadoEntity = new EstudioEncuestadoEntity();
                estudioEncuestadoEntity.setEstado("Pendiente");
                estudioEncuestadoEntity.setEstudio(estudio);
                estudioEncuestadoEntity.setEncuestado(encuestadoEntity);

                daoEstudioEncuestado.insert(estudioEncuestadoEntity);
            }
            return Response.ok(encuestadoEntity).build();
/*
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }*/
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/encuestado
     * Metodo con anotacion GET que devuelve todos los Encuestados registrados
     * @return Response con status ok con la lista de Encuestados.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getEncuestados() {
        List<EncuestadoEntity> encuestados;
        try {
            DaoEncuestado dao = new DaoEncuestado();
                encuestados = dao.findAll(EncuestadoEntity.class);
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
        return Response.ok(encuestados).build();
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/encuestado/id
     * Metodo con anotacion GET que devuelve todos los datos de un Encuestado a partir de su id
     * @param id identificador del Encuestado del que se quieren obtener todos sus datos
     * @return Response con status ok junto a los datos del Encuestado consultado
     */
    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getEncuestado(@PathParam("id") long id){

        DaoEncuestado dao = new DaoEncuestado();
        try{
            EncuestadoEntity encuestadoEntity = dao.find(id, EncuestadoEntity.class);
            if(encuestadoEntity != null) {

                DtoEncuestado dtoEncuestado = new DtoEncuestado();
                dtoEncuestado.set_id(encuestadoEntity.get_id());
                dtoEncuestado.setEstado(encuestadoEntity.getEstado());
                dtoEncuestado.setPrimerNombre(encuestadoEntity.getPrimerNombre());
                dtoEncuestado.setSegundoNombre(encuestadoEntity.getSegundoNombre());
                dtoEncuestado.setPrimerApellido(encuestadoEntity.getPrimerApellido());
                dtoEncuestado.setSegundoApellido(encuestadoEntity.getSegundoApellido());

                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fechaNacimiento = sdf.format(encuestadoEntity.getFechaNacimiento());
                dtoEncuestado.setFechaNacimiento(fechaNacimiento);

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
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/encuestado/id
     * Metodo con anotacion DELETE que recibe un id y se encarga de eliminar de la base de datos el Encuestado con ese id
     * @param id identificador del Encuestado a ser eliminado
     * @return Retorna un Response ok en caso de que el Encuestado se haya eliminado de manera correcta
     */
    @DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteEncuestado(@PathParam("id") long id){

        try{
            DaoEncuestado daoEncuestado = new DaoEncuestado();
            EncuestadoEntity encuestadoEntity = daoEncuestado.find(id, EncuestadoEntity.class);
            if (encuestadoEntity != null){
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
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }

    }

    /**
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/usuario/encuestado/updateEstado/id
     * Metodo con anotacion PUT que se encarga de actualizar el estado de un Encuestado
     * @param id identificador del Encuestado a ser actualizado
     * @param dtoEncuestado objeto que contiene el estado que sera actualizado
     * @return Response con status ok y el Encuestado actualizado en caso de que la transacción haya sido exitosa
     */
    @PUT
    @Consumes(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("/updateEstado/{id}")
    public Response updateEstadoEncuestado(@PathParam("id") long id, DtoEncuestado dtoEncuestado) {
        DaoEncuestado daoEncuestado = new DaoEncuestado();

        try{
            EncuestadoEntity encuestadoEntity = daoEncuestado.find(id, EncuestadoEntity.class);

            if (encuestadoEntity != null){
                encuestadoEntity.setEstado(dtoEncuestado.getEstado());
                daoEncuestado.update(encuestadoEntity);
                return Response.ok().entity(encuestadoEntity).build();
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
     * http://localhost:8080/servicio-1.0-SNAPSHOT/api/usuario/encuestado/id
     * Metodo con anotacion PUT que se encarga de actualizar los datos de un Encuestado
     * @param id identificador del Encuestado a ser actualizado
     * @param dtoEncuestado objeto que contiene los datos que seran actualizados
     * @return Response con status ok y el Encuestado actualizado en caso de que la transacción haya sido exitosa
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateEncuestado(@PathParam("id") long id, DtoEncuestado dtoEncuestado) {

        String rol = "Encuestado";

        DaoEncuestado dao = new DaoEncuestado();
        DaoEstadoCivil daoEstadoCivil = new DaoEstadoCivil();
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        DaoMedioConexion daoMedioConexion = new DaoMedioConexion();
        DaoGenero daoGenero = new DaoGenero();
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        DaoNivelSocioeconomico daoNivelSocioeconomico = new DaoNivelSocioeconomico();
        DaoLugar daoLugar = new DaoLugar();

        try{
            EncuestadoEntity encuestadoEntity = dao.find(id, EncuestadoEntity.class);

            if(encuestadoEntity != null){
                encuestadoEntity.setPrimerNombre(dtoEncuestado.getPrimerNombre());
                encuestadoEntity.setSegundoNombre(dtoEncuestado.getSegundoNombre());
                encuestadoEntity.setPrimerApellido(dtoEncuestado.getPrimerApellido());
                encuestadoEntity.setSegundoApellido(dtoEncuestado.getSegundoApellido());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = sdf.parse(dtoEncuestado.getFechaNacimiento());
                encuestadoEntity.setFechaNacimiento(fechaNacimiento);

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

                DaoTelefono daoTelefono = new DaoTelefono();
                TelefonoEntity telefonoEntity = daoTelefono.getTelefonoByEncuestado(encuestadoEntity);
                telefonoEntity.setNumero(dtoEncuestado.getTelefono().getNumero());
                telefonoEntity.setEstado(dtoEncuestado.getEstado());
                telefonoEntity.setEncuestado(encuestadoEntity);
                TelefonoEntity resulTlf = daoTelefono.update(telefonoEntity);

                DtoUsuario dtoUsuario = new DtoUsuario();
                dtoUsuario.setEstado(dtoEncuestado.getEstado());
                dtoUsuario.setUsername(dtoEncuestado.getUsuario().getUsername());
                dtoUsuario.setCorreoelectronico(dtoEncuestado.getUsuario().getCorreoelectronico());
                DirectorioActivo ldap = new DirectorioActivo();
                ldap.updateEntry(dtoUsuario, rol);

                EncuestadoEntity resul = dao.update(encuestadoEntity);

                return Response.ok(encuestadoEntity).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        }catch (Exception ex) {
            String problema = ex.getMessage();
            return  Response.status(Response.Status.NOT_ACCEPTABLE).entity(problema).build();
        }
    }

}
