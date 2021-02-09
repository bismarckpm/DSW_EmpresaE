package Mappers.Encuestado;

import Mappers.GenericMapper;
import ucab.empresae.dtos.DtoEncuestado;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.excepciones.CustomException;

import java.util.List;

public class EncuestadoMapper extends GenericMapper<DtoEncuestado> {

    @Override
    public DtoEncuestado CreateDto(BaseEntity entity) throws CustomException {
        return null;
    }

    @Override
    public BaseEntity CreateEntity(DtoEncuestado dtoEncuestado) throws CustomException {
        return null;
    }

    @Override
    public List<DtoEncuestado> CreateDtoList(List<BaseEntity> entities) throws CustomException {
        return null;
    }

    @Override
    public List<BaseEntity> CreateEntityList(List<DtoEncuestado> dtoEncuestados) throws CustomException {
        return null;
    }

}
