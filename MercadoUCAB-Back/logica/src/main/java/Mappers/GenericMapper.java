package Mappers;

import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.excepciones.CategoriaException;

import java.util.List;

/**
 * @author José Prieto
 * @since 11/01/2020
 * @param <TDto> Dto genérico especificado en las clases concretas
 */
public abstract class GenericMapper <TDto> {

    public abstract TDto CreateDto(BaseEntity entity) throws Exception;

    public abstract BaseEntity CreateEntity(TDto dto) throws Exception;

    public abstract List<TDto> CreateDtoList(List<BaseEntity> entities) throws Exception;

    public abstract List<BaseEntity> CreateEntityList(List<TDto> dtos) throws CategoriaException;

}