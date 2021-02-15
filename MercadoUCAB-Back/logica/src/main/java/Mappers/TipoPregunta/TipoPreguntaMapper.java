package Mappers.TipoPregunta;

import Mappers.Categoria.CategoriaMapper;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.dtos.*;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class TipoPreguntaMapper extends GenericMapper<DtoTipoPregunta> {
    /**
     * Método encargado de convertir una entidad a un DtoTipoPregunta
     * @param entity entidad base posteriormente convertidad a TipoPreguntaEntity
     * @return Objeto de tipo DtoTipoPregunta
     * @throws CustomException En caso de que algún dato sea inválido
     */
    @Override
    public DtoTipoPregunta CreateDto(BaseEntity entity) throws CustomException {
        TipoPreguntaEntity tipopregunta = (TipoPreguntaEntity) entity;
        if(tipopregunta.getTipo() == null) {
            throw new CustomException("MAPTIPP001","Tipo no debe ser null.");
        }else {
            DtoTipoPregunta dtoTipoPregunta = DtoFactory.DtoTipoPreguntaInstance(tipopregunta.get_id());
            dtoTipoPregunta.setTipo(tipopregunta.getTipo());
            dtoTipoPregunta.setEstado(tipopregunta.getEstado());

            return dtoTipoPregunta;
        }
    }

    /**
     * Método encargado de convertir un DtoTipoPregunta en Entidad persistente
     * @param dto Objeto de tipo DtoTipoPregunta
     * @return Objeto de tipo TipoPreguntaEntity
     * @throws CustomException En caso de que alguno de los datos no sea válido
     */
    @Override
    public BaseEntity CreateEntity(DtoTipoPregunta dto) throws CustomException {
        if(dto.getTipo() == null) {
            throw new CustomException("MAPTIPP002","Tipo no debe ser null.");
        } else {
            TipoPreguntaEntity tipopregunta = EntidadesFactory.TipoPreguntaInstance(dto.get_id());
            if(tipopregunta == null) {
                throw new CustomException("Entidad correspondiente al dto no encontrada.");
            } else {
                tipopregunta.setTipo(dto.getTipo());
                tipopregunta.setEstado(dto.getEstado());
                return tipopregunta;
            }
        }
    }

    /**
     * Método encargado de convertir una lista de objetos de tipo TipoPreguntaEntity en DtoTipoPregunta
     * @param entities Lista de objetos de tipo TipoPreguntaEntity
     * @return Lista de objetos de tipo DtoTipoPregunta
     * @throws CustomException En caso de que falle a la hora de llamar a CreateDto
     */
    @Override
    public List<DtoTipoPregunta> CreateDtoList(List<BaseEntity> entities) throws CustomException {
        ArrayList<DtoTipoPregunta> dtos = new ArrayList<>();

        for (BaseEntity obj : entities) {
            dtos.add(CreateDto(obj));
        }
        return dtos;
    }

    /**
     * Método encargado de convertir una lista de objetos de tipo DtoTipoPregunta en TipoPreguntaEntity
     * @param dtos Lista de objetos de tipo DtoTipoPregunta
     * @return Lista de objetos de tipo TipoPreguntaEntity
     * @throws CustomException En caso de que alguna de las llamadas a createEntity falle
     */
    @Override
    public List<BaseEntity> CreateEntityList(List<DtoTipoPregunta> dtos) throws CustomException {
        ArrayList<BaseEntity> tipopreguntas = new ArrayList<>();

        for (DtoTipoPregunta obj : dtos) {
            tipopreguntas.add ( CreateEntity ( obj ) );
        }
        return tipopreguntas ;
    }
}
