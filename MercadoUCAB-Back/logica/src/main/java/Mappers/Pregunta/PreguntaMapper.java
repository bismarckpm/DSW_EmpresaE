package Mappers.Pregunta;

import Mappers.Categoria.CategoriaMapper;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import Mappers.Subcategoria.SubcategoriaMapper;
import Mappers.TipoPregunta.TipoPreguntaMapper;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoPregunta;
import ucab.empresae.daos.DaoSubcategoria;
import ucab.empresae.daos.DaoTipoPregunta;
import ucab.empresae.dtos.*;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.CategoriaException;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;


/**
 * Clase utilizada para mapear la clase persistente categoríaEntity con DtoCategoria y viceversa.
 */
public class PreguntaMapper {
    /**
     * Método encargado de convertir una entidad a un DtoPregunta
     * @param entity entidad base posteriormente convertida a PreguntaEntity
     * @return Objeto de tipo DtoPregunta
     * @throws CustomException En caso de que algún dato sea inválido
     */
    public DtoPregunta CreateDto(BaseEntity entity) throws CustomException {
        PreguntaEntity pregunta = (PreguntaEntity) entity;
        if(pregunta.getDescripcion() == null) {
            throw new CustomException("MAPPREG001","Descripcion no debe ser null.");
        }else {
            DtoPregunta dtoPregunta = DtoFactory.DtoPreguntaInstance(pregunta.get_id());
            dtoPregunta.setDescripcion(pregunta.getDescripcion());
            dtoPregunta.setEstado(pregunta.getEstado());

            SubcategoriaMapper subcategoriaMapper = MapperFactory.subcategoriaMapperInstancia();
            DtoSubcategoria dtoSubcategoria =  subcategoriaMapper.CreateDto(pregunta.getSubcategoria());
            dtoPregunta.setSubcategoria(dtoSubcategoria);

            TipoPreguntaMapper tipoPreguntaMapper = MapperFactory.tipoPreguntaMapperInstancia();
            DtoTipoPregunta dtoTipoPregunta =  tipoPreguntaMapper.CreateDto(pregunta.getTipo());
            dtoPregunta.setTipo(dtoTipoPregunta);

            return dtoPregunta;
        }
    }

    /**
     * Método encargado de convertir un DtoPregunta en Entidad persistente
     * @param dto Objeto de tipo DtoPregunta
     * @return Objeto de tipo PreguntaEntity
     * @throws CustomException En caso de que alguno de los datos no sea válido
     */
    public PreguntaEntity CreateEntity(DtoPregunta dto) throws CustomException {
        if(dto.getDescripcion() == null) {
            throw new CustomException("Nombre no debe ser null.");
        } else {
            PreguntaEntity pregunta = EntidadesFactory.PreguntaInstance(dto.get_id());
            if(pregunta == null) {
                throw new CustomException("Entidad correspondiente al dto no encontrada.");
            } else {
                pregunta.setDescripcion(dto.getDescripcion());
                pregunta.setEstado(dto.getEstado());

                DaoTipoPregunta daoTipoPregunta = DaoFactory.DaoTipoPreguntaInstancia();
                DaoSubcategoria daoSubcategoria = DaoFactory.DaoSubcategoriaInstancia();

                SubcategoriaEntity subcategoria = daoSubcategoria.find(dto.getSubcategoria().get_id(), SubcategoriaEntity.class);
                TipoPreguntaEntity tipoPregunta = daoTipoPregunta.find(dto.getTipo().get_id(), TipoPreguntaEntity.class);
                
                pregunta.setSubcategoria(subcategoria);
                pregunta.setTipo(tipoPregunta);

                return pregunta;
            }
        }
    }


    /**
     * Método encargado de convertir un DtoPregunta en Entidad persistente
     * @param dto Objeto de tipo DtoPregunta
     * @return Objeto de tipo PreguntaEntity
     * @throws CustomException En caso de que alguno de los datos no sea válido
     */
    public PreguntaEntity CreateEntityUpdate(long id, DtoPregunta dto) throws CustomException {
        if(dto.getDescripcion() == null) {
            throw new CustomException("Nombre no debe ser null.");
        } else {
            DaoPregunta daoPregunta = DaoFactory.DaoPreguntaInstancia();
            PreguntaEntity pregunta = daoPregunta.find(id, PreguntaEntity.class);
            if(pregunta == null) {
                throw new CustomException("Entidad correspondiente al dto no encontrada.");
            } else {
                pregunta.setDescripcion(dto.getDescripcion());
                pregunta.setEstado(dto.getEstado());

                DaoTipoPregunta daoTipoPregunta = DaoFactory.DaoTipoPreguntaInstancia();
                DaoSubcategoria daoSubcategoria = DaoFactory.DaoSubcategoriaInstancia();

                SubcategoriaEntity subcategoria = daoSubcategoria.find(dto.getSubcategoria().get_id(), SubcategoriaEntity.class);
                TipoPreguntaEntity tipoPregunta = daoTipoPregunta.find(dto.getTipo().get_id(), TipoPreguntaEntity.class);

                pregunta.setSubcategoria(subcategoria);
                pregunta.setTipo(tipoPregunta);

                return pregunta;
            }
        }
    }

    /**
     * Método encargado de convertir una lista de objetos de tipo PreguntaEntity en DtoPregunta
     * @param entities Lista de objetos de tipo PreguntaEntity
     * @return Lista de objetos de tipo DtoPregunta
     * @throws CustomException En caso de que falle a la hora de llamar a CreateDto
     */
    public List<DtoPregunta> CreateDtoList(List<BaseEntity> entities) throws CustomException {
        ArrayList<DtoPregunta> dtos = new ArrayList<>();

        for (BaseEntity obj : entities) {
            dtos.add(CreateDto(obj));
        }
        return dtos;
    }

    /**
     * Método encargado de convertir una lista de objetos de tipo DtoPregunta en PreguntaEntity
     * @param dtos Lista de objetos de tipo DtoPregunta
     * @return Lista de objetos de tipo PreguntaEntity
     * @throws CustomException En caso de que alguna de las llamadas a createEntity falle
     */
    public List<PreguntaEntity> CreateEntityList(List<DtoPregunta> dtos) throws CustomException {
        ArrayList<PreguntaEntity> preguntas = new ArrayList<>();

        for (DtoPregunta obj : dtos) {
            preguntas.add ( CreateEntity ( obj ) );
        }
        return preguntas ;
    }
}
