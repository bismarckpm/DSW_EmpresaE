package Mappers.Genero;

import Mappers.GenericMapper;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoGenero;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.EntidadesFactory;
import ucab.empresae.entidades.GeneroEntity;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class GeneroMapper extends GenericMapper<DtoGenero> {

    @Override
    public DtoGenero CreateDto(BaseEntity entity) throws CustomException {
        GeneroEntity genero = (GeneroEntity) entity;
        if(genero.get_id() <= 0) {
            throw new CustomException("MAPGEN001","El id debe ser mayor a 0.");
        }else if(genero.getEstado() == null) {
            throw new CustomException("MAPGEN001","El genero debe tener un estado.");
        }else if(genero.getNombre() == null) {
            throw new CustomException("MAPGEN001","El genero debe estar identificado con un nombre.");
        }else if(!genero.getEstado().equals("a") && !genero.getEstado().equals("i")){
            throw new CustomException("MAPGEN001","El estado del genero no es válido.");
        }else {
            DtoGenero dtoGenero = DtoFactory.DtoGeneroInstance();
            dtoGenero.setEstado(genero.getEstado());
            dtoGenero.setNombre(genero.getNombre());
            dtoGenero.set_id(genero.get_id());

            return dtoGenero;
        }
    }

    @Override
    public BaseEntity CreateEntity(DtoGenero dtoGenero) throws CustomException {
        if(dtoGenero.getEstado() == null) {
            throw new CustomException("MAPGEN001","El genero debe tener un estado.");
        }else if(dtoGenero.getNombre() == null) {
            throw new CustomException("MAPGEN001","El genero debe estar identificado con un nombre.");
        }else if(!dtoGenero.getEstado().equals("a") && !dtoGenero.getEstado().equals("i")){
            throw new CustomException("MAPGEN001","El estado del genero no es válido.");
        }else {
            GeneroEntity genero = EntidadesFactory.GeneroInstance();
            genero.setEstado(dtoGenero.getEstado());
            genero.setNombre(dtoGenero.getNombre());
            if(dtoGenero.get_id() > 0) {
                genero.set_id(dtoGenero.get_id());
            }
            return genero;
        }
    }

    @Override
    public List<DtoGenero> CreateDtoList(List<BaseEntity> entities) throws CustomException {
        ArrayList<DtoGenero> dtos = new ArrayList<>();

        for (BaseEntity obj: entities) {
            dtos.add(CreateDto(obj));
        }
        return dtos;
    }

    @Override
    public List<BaseEntity> CreateEntityList(List<DtoGenero> dtos) throws CustomException {
        ArrayList<BaseEntity> entidades = new ArrayList<>();

        for (DtoGenero obj: dtos) {
            entidades.add(CreateEntity((obj)));
        }

        return entidades;
    }
}
