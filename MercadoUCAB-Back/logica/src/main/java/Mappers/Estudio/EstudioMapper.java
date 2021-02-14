package Mappers.Estudio;

import Mappers.GenericMapper;
import Mappers.Genero.GeneroMapper;
import Mappers.Lugar.LugarMapper;
import Mappers.MapperFactory;
import Mappers.NivelSocioeconomico.NivelSocioeconomicoMapper;
import Mappers.Subcategoria.SubcategoriaMapper;
import Mappers.Usuario.UsuarioMapper;
import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.dtos.DtoEstudio;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.CustomException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EstudioMapper extends GenericMapper<DtoEstudio> {

    /**
     * Método encargado de convertir una entidad a un DtoEstudio
     * @since 06/02/2021
     * @param entity entidad base posteriormente convertidad a DtoEstudio
     * @see EstudioEntity Entidad a convertir
     * @return Objeto de tipo DtoCategoria
     * @see DtoCategoria Clase destino de conversión
     * @throws CustomException En caso de que algún dato sea inválido
     */
    @Override
    public DtoEstudio CreateDto(BaseEntity entity) throws CustomException {
        EstudioEntity estudio = (EstudioEntity) entity;
        if(estudio.get_id() <= 0) {
            throw new CustomException("MAPEST001","El id debe ser mayor a 0.");
        }else if(estudio.getEstado() == null) {
            throw new CustomException("MAPEST001","El estudio debe tener un estado asignado.");
        }else if(!estudio.getEstado().equals("solicitado") && !estudio.getEstado().equals("procesado") && !estudio.getEstado().equals("en ejecucion") && !estudio.getEstado().equals("culminado")) {
            throw new CustomException("MAPEST001","El estado del estudio no es válido");
        }else if(estudio.getNombre() == null) {
            throw new CustomException("MAPEST001","El estudio debe tener un nombre asignado.");
        }else if(estudio.getFechaInicio() == null) {
            throw new CustomException("MAPEST001","El estudio debe tener una fecha de inicio.");
        }else if(estudio.getVia() == null) {
            throw new CustomException("MAPEST001","El estudio debe tener una vía de respuesta.");
        }else if(!estudio.getVia().equals("telefono") && !estudio.getVia().equals("plataforma")) {

            throw new CustomException("MAPEST001","La vía de respuestas del estudio no es válida.");
        }else {

            DtoEstudio dtoEstudio = DtoFactory.DtoEstudioInstance();
            dtoEstudio.set_id(estudio.get_id());
            dtoEstudio.setEstado(estudio.getEstado());
            dtoEstudio.setNombre(estudio.getNombre());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            dtoEstudio.setFechaInicio(dateFormat.format(estudio.getFechaInicio()));

            dtoEstudio.setVia(estudio.getVia());

            SubcategoriaMapper subcategoriaMapper = MapperFactory.subcategoriaMapperInstancia();
            dtoEstudio.setSubcategoria(subcategoriaMapper.CreateDto(estudio.getSubcategoria()));

            NivelSocioeconomicoMapper nivelSocioeconomicoMapper = MapperFactory.nivelSocioeconomicoMapperInstancia();
            dtoEstudio.setNivelSocioEconomico(nivelSocioeconomicoMapper.CreateDto(estudio.getNivelSocioEconomico()));

            LugarMapper lugarMapper = MapperFactory.lugarMapperInstancia();
            dtoEstudio.setLugar(lugarMapper.CreateDto(estudio.getLugar()));

            if(estudio.getAnalista() != null) {
                UsuarioMapper usuarioMapper = MapperFactory.usuarioMapperInstancia();
                dtoEstudio.setAnalista(usuarioMapper.CreateDto(estudio.getAnalista()));
            }

            if(estudio.getGenero() != null) {
                GeneroMapper generoMapper = MapperFactory.generoMapperInstancia();
                dtoEstudio.setGenero(generoMapper.CreateDto(estudio.getGenero()));
            }

            if(estudio.getComentarioAnalista() != null) {
                dtoEstudio.setComentarioAnalista(estudio.getComentarioAnalista());
            }
            if(estudio.getEdadMinima() != null) {
                dtoEstudio.setEdadMinima(estudio.getEdadMinima());
            }
            if(estudio.getEdadMaxima() != null) {
                dtoEstudio.setEdadMaxima(estudio.getEdadMaxima());
            }
            if(estudio.getFechaFin() != null) {
                dtoEstudio.setFechaFin(dateFormat.format(estudio.getFechaFin()));
            }

            return dtoEstudio;
        }
    }

    /**
     * Método encargado de convertir un DtoCategoria en Entidad persistente
     * @since 11/01/2021
     * @param dtoEstudio Objeto de tipo DtoCategoria
     * @return Objeto de tipo CategoríaEntity
     * @throws CustomException En caso de que alguno de los datos no sea válido
     */
    @Override
    public BaseEntity CreateEntity(DtoEstudio dtoEstudio) throws CustomException, ParseException {
        if(dtoEstudio.getNombre() == null) {
            throw new CustomException("MAPEST002","El estudio debe tener un nombre asignado.");
        }else if(dtoEstudio.getVia() == null) {
            throw new CustomException("MAPEST002","El estudio debe tener una vía de respuesta.");
        }else if(!dtoEstudio.getVia().equals("telefono") && !dtoEstudio.getVia().equals("plataforma")) {
            throw new CustomException("MAPEST002","La vía de respuestas del estudio no es válida.");
        }else {
            EstudioEntity estudio = EntidadesFactory.EstudioInstance();
            estudio.setNombre(dtoEstudio.getNombre());
            estudio.setVia(dtoEstudio.getVia());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");

            if(dtoEstudio.getFechaInicio() == null) {
                estudio.setFechaInicio(new Date());
            }else {
                estudio.setFechaInicio(dateFormat.parse(dtoEstudio.getFechaInicio()));
            }

            //SubcategoriaMapper subcategoriaMapper = MapperFactory.subcategoriaMapperInstancia();
            DaoSubcategoria daoSubcategoria = DaoFactory.DaoSubcategoriaInstancia();
            estudio.setSubcategoria(daoSubcategoria.find(dtoEstudio.getSubcategoria().get_id(), SubcategoriaEntity.class));

            //NivelSocioeconomicoMapper nivelSocioeconomicoMapper = MapperFactory.nivelSocioeconomicoMapperInstancia();
            DaoNivelSocioeconomico daoNivelSocioeconomico = DaoFactory.DaoNivelSocioeconomicoInstancia();
            estudio.setNivelSocioEconomico(daoNivelSocioeconomico.find(dtoEstudio.getNivelSocioEconomico().get_id(), NivelSocioeconomicoEntity.class));

            //LugarMapper lugarMapper = MapperFactory.lugarMapperInstancia();
            DaoLugar daoLugar = DaoFactory.DaoLugarInstancia();
            estudio.setLugar(daoLugar.find(dtoEstudio.getLugar().get_id(), LugarEntity.class));

            if(dtoEstudio.get_id() != 0) {
                estudio.set_id(dtoEstudio.get_id());
            }

            if(dtoEstudio.getAnalista() != null) {
                //UsuarioMapper usuarioMapper = MapperFactory.usuarioMapperInstancia();
                DaoUsuario daoUsuario = DaoFactory.DaoUsuarioInstancia();
                estudio.setAnalista(daoUsuario.find(dtoEstudio.getAnalista().get_id(), UsuarioEntity.class));
            }

            if(dtoEstudio.getGenero() != null) {
                //GeneroMapper generoMapper = MapperFactory.generoMapperInstancia();
                DaoGenero daoGenero = DaoFactory.DaoGeneroInstancia();
                estudio.setGenero(daoGenero.find(dtoEstudio.getGenero().get_id(), GeneroEntity.class));
            }

            if(dtoEstudio.getComentarioAnalista() != null) {
                estudio.setComentarioAnalista(dtoEstudio.getComentarioAnalista());
                if(dtoEstudio.getEstado() == null) {
                    estudio.setEstado("culminado");
                    estudio.setFechaFin(new Date());
                }
            }
            if(dtoEstudio.getEstado() == null && estudio.getEstado() == null) {
                estudio.setEstado("solicitado");
            }else if (dtoEstudio.getEstado() != null && estudio.getEstado() == null){
                estudio.setEstado(dtoEstudio.getEstado());
            }

            if(dtoEstudio.getEdadMinima() != null) {
                estudio.setEdadMinima(dtoEstudio.getEdadMinima());
            }
            if(dtoEstudio.getEdadMaxima() != null) {
                estudio.setEdadMaxima(dtoEstudio.getEdadMaxima());
            }

            return estudio;
        }
    }

    /**
     * Método encargado de convertir una lista de objetos de tipo CategoriaEntity en DtoCategoria
     * @since 11/01/2021
     * @param entities Lista de bjetos de tipo CategoriaEntity
     * @return Lista de objetos de tipo DtoCategoria
     * @throws CustomException En caso de que falle a la hora de llamar a CreateDto
     */
    @Override
    public List<DtoEstudio> CreateDtoList(List<BaseEntity> entities) throws CustomException {
        ArrayList<DtoEstudio> dtos = new ArrayList<>();

        for (BaseEntity obj : entities) {
            dtos.add(CreateDto(obj));
        }
        return dtos;
    }

    /**
     * Método encargado de convertir una lista de objetos de tipo DtoCategoria en CategoriaEntity
     * @since 11/01/2021
     * @param dtos Lista de objetos de tipo DtoCategoria
     * @return Lista de objetos de tipo CategoriaEntity
     * @throws CustomException En caso de que alguna de las llamadas a createEntity falle
     */
    @Override
    public List<BaseEntity> CreateEntityList(List<DtoEstudio> dtos) throws CustomException, ParseException {
        ArrayList<BaseEntity> categorias = new ArrayList<>();

        for (DtoEstudio obj : dtos) {
            categorias.add ( CreateEntity ( obj ) );
        }
        return categorias ;
    }

}
