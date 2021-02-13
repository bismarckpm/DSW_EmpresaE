package Mappers.Lugar;

import Mappers.GenericMapper;
import Mappers.MapperFactory;
import Mappers.NivelSocioeconomico.NivelSocioeconomicoMapper;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoLugar;
import ucab.empresae.daos.DaoNivelSocioeconomico;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoLugar;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.EntidadesFactory;
import ucab.empresae.entidades.LugarEntity;
import ucab.empresae.entidades.NivelSocioeconomicoEntity;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class LugarMapper extends GenericMapper<DtoLugar> {

    @Override
    public DtoLugar CreateDto(BaseEntity entity) throws CustomException {
        LugarEntity lugar = (LugarEntity) entity;
        if(lugar.getEstado() == null) {
            throw new CustomException("MAPPAI001","Estado no debe ser null.");
        }else if(lugar.getNombre() == null) {
            throw new CustomException("MAPPAI001","Nombre no debe ser null");
        }else if(lugar.get_id() == 0) {
            throw new CustomException("MAPPAI001","Id debe ser mayor a 0.");
        }else if(lugar.getTipo() == null) {
            throw new CustomException("MAPPAI001","El tipo del lugar debe estar especificado.");
        }else if(!lugar.getTipo().equals("pais") && !lugar.getTipo().equals("estado") &&! lugar.getTipo().equals("municipio") && !lugar.getTipo().equals("parroquia")) {
            throw new CustomException("MAPPAI001","Tipo de lugar no válido.");
        }else {
            DtoLugar dtoLugar = DtoFactory.DtoLugarInstance();
            dtoLugar.setEstado(lugar.getEstado());
            dtoLugar.setNombre(lugar.getNombre());
            dtoLugar.setTipo(lugar.getTipo());
            dtoLugar.set_id(lugar.get_id());
            if(lugar.getNivelsocioeco() != null) {
                NivelSocioeconomicoMapper nivelSocioeconomicoMapper = MapperFactory.nivelSocioeconomicoMapperInstancia();
                dtoLugar.setNivelSocioEconomico(nivelSocioeconomicoMapper.CreateDto(lugar.getNivelsocioeco()));
            }
            if(lugar.getLugar() != null) {
                dtoLugar.setLugar(CreateDto(lugar.getLugar()));
            }
            return dtoLugar;
        }
    }

    @Override
    public BaseEntity CreateEntity(DtoLugar dtoLugar) throws CustomException {
        if(dtoLugar.getLugar() != null){
            throw new CustomException("MAPPAI002","Los paises no tienen FK.");
        } else if(dtoLugar.getNombre() == null) {
            throw new CustomException("MAPPAI002","El pais debe tener un nombre.");
        } else if(dtoLugar.getEstado() == null) {
            throw new CustomException("MAPPAI002","El pais debe tener un estatus.");
        } else if(!dtoLugar.getEstado().equals("a") && !dtoLugar.getEstado().equals("i")) {
            throw new CustomException("MAPPAI002","Estatus del pais no valido.");
        } else if(!dtoLugar.getTipo().equals("pais")) {
            throw new CustomException("MAPPAI002","El tipo debe ser pais.");
        } else{
            LugarEntity lugar = EntidadesFactory.LugarInstance();
            lugar.setEstado(dtoLugar.getEstado());
            lugar.setNombre(dtoLugar.getNombre());
            lugar.setTipo(dtoLugar.getTipo());

            DaoLugar daoLugar = DaoFactory.DaoLugarInstancia();
            lugar.setLugar(daoLugar.find(dtoLugar.getLugar().get_id(), LugarEntity.class));

            if(dtoLugar.getNivelSocioEconomico() != null) {
                DaoNivelSocioeconomico daoNivelSocioeconomico = DaoFactory.DaoNivelSocioeconomicoInstancia();
                lugar.setNivelsocioeco(daoNivelSocioeconomico.find(dtoLugar.getNivelSocioEconomico().get_id(), NivelSocioeconomicoEntity.class));
            }

            if(dtoLugar.get_id() != 0) {
                lugar.set_id(dtoLugar.get_id());
            }

            return lugar;
        }
    }

    @Override
    public List<DtoLugar> CreateDtoList(List<BaseEntity> entities) throws CustomException {
        ArrayList<DtoLugar> dtos = new ArrayList<>();

        for (BaseEntity obj: entities) {
            dtos.add(CreateDto(obj));
        }
        return dtos;
    }

    @Override
    public List<BaseEntity> CreateEntityList(List<DtoLugar> dtos) throws CustomException {
        ArrayList<BaseEntity> entidades = new ArrayList<>();

        for (DtoLugar obj: dtos) {
            entidades.add(CreateEntity((obj)));
        }

        return entidades;
    }
}
