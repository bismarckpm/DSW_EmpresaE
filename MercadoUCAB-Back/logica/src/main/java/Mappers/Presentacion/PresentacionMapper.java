package Mappers.Presentacion;

import Mappers.GenericMapper;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoPresentacion;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.EntidadesFactory;
import ucab.empresae.entidades.PresentacionEntity;
import ucab.empresae.excepciones.CategoriaException;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilizada para mapear la clase persistente categoríaEntity con DtoCategoria y viceversa.
 */
public class PresentacionMapper extends GenericMapper<DtoPresentacion> {
    /**
     * Método encargado de convertir una entidad a un DtoPresentacion
     * @param entity entidad base posteriormente convertida a PresentacionEntity
     * @return Objeto de tipo DtoPresentacion
     * @throws CustomException En caso de que algún dato sea inválido
     */
    @Override
    public DtoPresentacion CreateDto(BaseEntity entity) throws CustomException{
        PresentacionEntity presentacion = (PresentacionEntity) entity;
        if(presentacion.getDescripcion() == null) {
            throw new CustomException("MAPPRE001","Nombre no debe ser null.");
        }else {
            DtoPresentacion dtoPresentacion = DtoFactory.DtoPresentacionInstance(presentacion.get_id());
            dtoPresentacion.setDescripcion(presentacion.getDescripcion());
            dtoPresentacion.setEstado(presentacion.getEstado());

            return dtoPresentacion;
        }
    }

    /**
     * Método encargado de convertir un DtoPresentacion en Entidad persistente
     * @param dto Objeto de tipo DtoPresentacion
     * @return Objeto de tipo PresentacionEntity
     * @throws CategoriaException En caso de que alguno de los datos no sea válido
     */
    @Override
    public BaseEntity CreateEntity(DtoPresentacion dto) throws CustomException {
        if(dto.getDescripcion() == null) {
            throw new CustomException("Nombre no debe ser null.");
        } else {
            PresentacionEntity presentacion = EntidadesFactory.PresentacionInstance(dto.get_id());
            if(presentacion == null) {
                throw new CustomException("Entidad correspondiente al dto no encontrada.");
            } else {
                presentacion.setDescripcion(dto.getDescripcion());
                presentacion.setEstado("a");

                return presentacion;
            }
        }
    }

    /**
     * Método encargado de convertir una lista de objetos de tipo PresentacionEntity en DtoPresentacion
     * @param entities Lista de bjetos de tipo PresentacionEntity
     * @return Lista de objetos de tipo DtoPresentacion
     * @throws Exception En caso de que falle a la hora de llamar a CreateDto
     */
    @Override
    public List<DtoPresentacion> CreateDtoList(List<BaseEntity> entities) throws CustomException {
        ArrayList<DtoPresentacion> dtos = new ArrayList<>();

        for (BaseEntity obj : entities) {
            dtos.add(CreateDto(obj));
        }
        return dtos;
    }

    /**
     * Método encargado de convertir una lista de objetos de tipo DtoPresentacion en PresentacionEntity
     * @param dtos Lista de objetos de tipo DtoPresentacion
     * @return Lista de objetos de tipo PresentacionEntity
     * @throws CategoriaException En caso de que alguna de las llamadas a createEntity falle
     */
    @Override
    public List<BaseEntity> CreateEntityList(List<DtoPresentacion> dtos) throws CustomException {
        ArrayList<BaseEntity> presentaciones = new ArrayList<>();

        for (DtoPresentacion obj : dtos) {
            presentaciones.add ( CreateEntity ( obj ) );
        }
        return presentaciones ;
    }
}
