package Mappers.Categoria;

import Mappers.GenericMapper;
import ucab.empresae.daos.DaoCategoria;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.excepciones.CategoriaException;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilizada para mapear la clase persistente categoríaEntity con DtoCategoria y viceversa.
 * @author José Prieto
 */
public class CategoriaMapper extends GenericMapper<DtoCategoria> {

    /**
     * Método encargado de convertir una entidad a un DtoCategoria
     * @since 11/01/2020
     * @param entity entidad base posteriormente convertidad a CategoriaEntity
     * @see CategoriaEntity Entidad a convertir
     * @return Objeto de tipo DtoCategoria
     * @see DtoCategoria Clase destino de conversión
     * @throws Exception En caso de que algún dato sea inválido
     */
    @Override
    public DtoCategoria CreateDto(BaseEntity entity) throws Exception {
        CategoriaEntity categoria = (CategoriaEntity) entity;
        if(entity.get_id() <= 0) {
            throw new CategoriaException("Id debe ser mayor a 0.");
        }else if(categoria.getNombre() == null) {
            throw new CategoriaException("Nombre no debe ser null.");
        }else {
            DtoCategoria dtoCategoria = DtoFactory.DtoCategoriaInstance(categoria.get_id());
            dtoCategoria.setNombre(categoria.getNombre());
            dtoCategoria.setEstado(categoria.getEstado());
            return dtoCategoria;
        }
    }

    /**
     * Método encargado de convertir un DtoCategoria en Entidad persistente
     * @since 11/01/2021
     * @param dto Objeto de tipo DtoCategoria
     * @return Objeto de tipo CategoríaEntity
     * @throws CategoriaException En caso de que alguno de los datos no sea válido
     */
    @Override
    public BaseEntity CreateEntity(DtoCategoria dto) throws CategoriaException {
        if(dto.get_id() <= 0) {
            throw new CategoriaException("Id debe ser mayor a 0.");
        }else if(dto.getNombre() == null) {
            throw new CategoriaException("Nombre no debe ser null.");
        } else {
            DaoCategoria daoCategoria = DaoFactory.DaoCategoriaInstancia();
            CategoriaEntity categoria = daoCategoria.find(dto.get_id(), CategoriaEntity.class);
            if(categoria == null) {
                throw new CategoriaException("Entidad correspondiente al dto no encontrada.");
            } else {
                categoria.setNombre(dto.getNombre());
                categoria.setEstado("a");
                return categoria;
            }
        }
    }

    /**
     * Método encargado de convertir una lista de objetos de tipo CategoriaEntity en DtoCategoria
     * @since 11/01/2021
     * @param entities Lista de bjetos de tipo CategoriaEntity
     * @return Lista de objetos de tipo DtoCategoria
     * @throws Exception En caso de que falle a la hora de llamar a CreateDto
     */
    @Override
    public List<DtoCategoria> CreateDtoList(List<BaseEntity> entities) throws Exception {
        ArrayList<DtoCategoria> dtos = new ArrayList<>();

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
     * @throws CategoriaException En caso de que alguna de las llamadas a createEntity falle
     */
    @Override
    public List<BaseEntity> CreateEntityList(List<DtoCategoria> dtos) throws CategoriaException {
        ArrayList<BaseEntity> categorias = new ArrayList<>();

        for (DtoCategoria obj : dtos) {
            categorias.add ( CreateEntity ( obj ) );
        }
        return categorias ;
    }

}
