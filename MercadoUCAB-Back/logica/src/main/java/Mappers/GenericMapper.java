package Mappers;

import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.excepciones.CustomException;

import java.text.ParseException;
import java.util.List;

/**
 * @author José Prieto
 * @since 11/01/2020
 * @param <TDto> Dto genérico especificado en las clases concretas
 */
public abstract class GenericMapper <TDto> {

    public abstract TDto CreateDto(BaseEntity entity) throws CustomException;

    public abstract BaseEntity CreateEntity(TDto dto) throws CustomException, ParseException;

    public abstract List<TDto> CreateDtoList(List<BaseEntity> entities) throws CustomException;

    public abstract List<BaseEntity> CreateEntityList(List<TDto> dtos) throws CustomException, ParseException;

}