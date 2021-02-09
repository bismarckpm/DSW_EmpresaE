package Mappers.Subcategoria;

import Mappers.Categoria.CategoriaMapper;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoSubcategoria;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.entidades.EntidadesFactory;
import ucab.empresae.entidades.SubcategoriaEntity;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class SubcategoriaMapper extends GenericMapper<DtoSubcategoria> {
    /**
     * Método encargado de convertir una entidad a un DtoSubcategoria
     * @param entity entidad base posteriormente convertidad a SubategoriaEntity
     * @return Objeto de tipo DtoSubcategoria
     * @throws CustomException En caso de que algún dato sea inválido
     */
    @Override
    public DtoSubcategoria CreateDto(BaseEntity entity) throws CustomException {
        SubcategoriaEntity subcategoria = (SubcategoriaEntity) entity;
        if(subcategoria.getNombre() == null) {
            throw new CustomException("MAPSUB001","Nombre no debe ser null.");
        }else {
            DtoSubcategoria dtoSubcategoria = DtoFactory.DtoSubcategoriaInstance(subcategoria.get_id());
            dtoSubcategoria.setNombre(subcategoria.getNombre());
            dtoSubcategoria.setEstado(subcategoria.getEstado());

            CategoriaMapper categoriaMapper = MapperFactory.categoriaMapperInstancia();
            DtoCategoria dtoCategoria = categoriaMapper.CreateDto(subcategoria.getCategoria());

            dtoSubcategoria.setCategoria(dtoCategoria);
            return dtoSubcategoria;
        }
    }

    /**
     * Método encargado de convertir un DtoSubcategoria en Entidad persistente
     * @param dto Objeto de tipo DtoSubcategoria
     * @return Objeto de tipo SubcategoriaEntity
     * @throws CustomException En caso de que alguno de los datos no sea válido
     */
    @Override
    public BaseEntity CreateEntity(DtoSubcategoria dto) throws CustomException {
        if(dto.getNombre() == null) {
            throw new CustomException("MAPSUB002","Nombre no debe ser null.");
        } else {
            SubcategoriaEntity subcategoria = EntidadesFactory.SubcategoriaInstance(dto.get_id());
            if(subcategoria == null) {
                throw new CustomException("Entidad correspondiente al dto no encontrada.");
            } else {
                subcategoria.setNombre(dto.getNombre());
                subcategoria.setEstado(dto.getEstado());

                CategoriaMapper categoriaMapper = MapperFactory.categoriaMapperInstancia();
                CategoriaEntity categoria = (CategoriaEntity) categoriaMapper.CreateEntity(dto.getCategoria());

                subcategoria.setCategoria(categoria);
                return subcategoria;
            }
        }
    }

    /**
     * Método encargado de convertir una lista de objetos de tipo SubcategoriaEntity en DtoSubcategoria
     * @param entities Lista de objetos de tipo SubcategoriaEntity
     * @return Lista de objetos de tipo DtoSubcategoria
     * @throws CustomException En caso de que falle a la hora de llamar a CreateDto
     */
    @Override
    public List<DtoSubcategoria> CreateDtoList(List<BaseEntity> entities) throws CustomException {
        ArrayList<DtoSubcategoria> dtos = new ArrayList<>();

        for (BaseEntity obj : entities) {
            dtos.add(CreateDto(obj));
        }
        return dtos;
    }

    /**
     * Método encargado de convertir una lista de objetos de tipo DtoCategoria en CategoriaEntity
     * @param dtos Lista de objetos de tipo DtoSubcategoria
     * @return Lista de objetos de tipo SubcategoriaEntity
     * @throws CustomException En caso de que alguna de las llamadas a createEntity falle
     */
    @Override
    public List<BaseEntity> CreateEntityList(List<DtoSubcategoria> dtos) throws CustomException {
        ArrayList<BaseEntity> subcategorias = new ArrayList<>();

        for (DtoSubcategoria obj : dtos) {
            subcategorias.add ( CreateEntity ( obj ) );
        }
        return subcategorias ;
    }
}
