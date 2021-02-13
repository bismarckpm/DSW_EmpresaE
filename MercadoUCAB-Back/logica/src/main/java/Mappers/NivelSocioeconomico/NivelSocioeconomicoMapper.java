package Mappers.NivelSocioeconomico;

import Mappers.GenericMapper;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoNivelSocioEconomico;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.EntidadesFactory;
import ucab.empresae.entidades.NivelSocioeconomicoEntity;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class NivelSocioeconomicoMapper extends GenericMapper<DtoNivelSocioEconomico> {

    @Override
    public DtoNivelSocioEconomico CreateDto(BaseEntity entity) throws CustomException {
        NivelSocioeconomicoEntity nivelSocioeconomico = (NivelSocioeconomicoEntity) entity;
        if(nivelSocioeconomico.get_id() == 0) {
            throw new CustomException("MAPNIV001","El id debe ser mayor a 0.");
        } else {
            DtoNivelSocioEconomico dtoNivelSocioEconomico = DtoFactory.DtoNivelSocioEconomicoInstance();
            dtoNivelSocioEconomico.set_id(nivelSocioeconomico.get_id());
            dtoNivelSocioEconomico.setEstado(nivelSocioeconomico.getEstado());
            dtoNivelSocioEconomico.setNombre(nivelSocioeconomico.getNombre());
            dtoNivelSocioEconomico.setDescripcion(nivelSocioeconomico.getDescripcion());
            return dtoNivelSocioEconomico;
        }
    }

    @Override
    public BaseEntity CreateEntity(DtoNivelSocioEconomico dtoNivelSocioEconomico) throws CustomException {
        if(dtoNivelSocioEconomico.getNombre() == null) {
            throw new CustomException("MAPNIV002","El nivel socioeconomico debe tener un nombre.");
        }else if(dtoNivelSocioEconomico.getDescripcion() == null) {
            throw new CustomException("MAPNIV002","El nivel socioeconomico debe tener una descripción.");
        }else if(dtoNivelSocioEconomico.getEstado() == null) {
            throw new CustomException("MAPNIV002","El nivel socioeconomico debe tener un estatus.");
        }else if(!dtoNivelSocioEconomico.getEstado().equals('a') && !dtoNivelSocioEconomico.getEstado().equals('i')){
            throw new CustomException("MAPNIV002","Estatus de nivel socioeconomico no válido.");
        }else {
            NivelSocioeconomicoEntity nivelSocioeconomico = EntidadesFactory.NivelSocioeconomicoInstance();
            if(dtoNivelSocioEconomico.get_id() != 0) {
                nivelSocioeconomico.set_id(dtoNivelSocioEconomico.get_id());
            }
            nivelSocioeconomico.setEstado(dtoNivelSocioEconomico.getEstado());
            nivelSocioeconomico.setNombre(dtoNivelSocioEconomico.getNombre());
            nivelSocioeconomico.setDescripcion(dtoNivelSocioEconomico.getDescripcion());
            return nivelSocioeconomico;
        }
    }

    @Override
    public List<DtoNivelSocioEconomico> CreateDtoList(List<BaseEntity> entities) throws CustomException {
        ArrayList<DtoNivelSocioEconomico> dtos = new ArrayList<>();

        for (BaseEntity obj: entities) {
            dtos.add(CreateDto(obj));
        }

        return dtos;
    }

    @Override
    public List<BaseEntity> CreateEntityList(List<DtoNivelSocioEconomico> dtos) throws CustomException {
        ArrayList<BaseEntity> entidades = new ArrayList<>();

        for (DtoNivelSocioEconomico obj: dtos) {
            entidades.add (CreateEntity(obj));
        }

        return entidades;
    }
}
