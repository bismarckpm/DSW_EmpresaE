package Mappers.TipoUsuario;

import Mappers.GenericMapper;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoTipoUsuario;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.EntidadesFactory;
import ucab.empresae.entidades.TipoUsuarioEntity;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class TipoUsuarioMapper extends GenericMapper<DtoTipoUsuario> {

    @Override
    public DtoTipoUsuario CreateDto(BaseEntity entity) throws CustomException {
        TipoUsuarioEntity tipoUsuario = (TipoUsuarioEntity) entity;
        if(tipoUsuario.get_id() <= 0) {
            throw new CustomException("MAPTIP001","El id debe ser mayor que 0");
        }else if(tipoUsuario.getEstado() == null) {
            throw new CustomException("MAPTIP001","El tipo de usuario debe tener un estado definido.");
        }else if(!tipoUsuario.getEstado().equals("a") && !tipoUsuario.getEstado().equals("i")){
            throw new CustomException("MAPTIP001","Estado no valido.");
        }else {
            DtoTipoUsuario dtoTipoUsuario = DtoFactory.DtoTipoUsuarioInstance();
            dtoTipoUsuario.set_id(tipoUsuario.get_id());
            if(tipoUsuario.getDescripcion() != null) {
                dtoTipoUsuario.setDescripcion(tipoUsuario.getDescripcion());
            }
            dtoTipoUsuario.setEstado(tipoUsuario.getEstado());
            return dtoTipoUsuario;
        }
    }

    @Override
    public BaseEntity CreateEntity(DtoTipoUsuario dtoTipoUsuario) throws CustomException {
        if(dtoTipoUsuario.getEstado() == null) {
            throw new CustomException("MAPTIP002","El tipo usuario deber tener un estatus válido.");
        }else if(!dtoTipoUsuario.getEstado().equals("a") && !dtoTipoUsuario.getEstado().equals("i")) {
            throw new CustomException("MAPTIP002","Estatus del tipo de usuario no válido.");
        }
        TipoUsuarioEntity tipoUsuario = EntidadesFactory.TipoUsuarioInstance();
        if(dtoTipoUsuario.getDescripcion() != null) {
            tipoUsuario.setDescripcion(dtoTipoUsuario.getDescripcion());
        }
        tipoUsuario.setEstado(dtoTipoUsuario.getEstado());
        if(dtoTipoUsuario.get_id() > 0) {
            tipoUsuario.set_id(dtoTipoUsuario.get_id());
        }
        return tipoUsuario;
    }

    @Override
    public List<DtoTipoUsuario> CreateDtoList(List<BaseEntity> entities) throws CustomException {
        ArrayList<DtoTipoUsuario> dtos = new ArrayList<>();

        for (BaseEntity obj: entities) {
            dtos.add(CreateDto(obj));
        }

        return dtos;
    }

    @Override
    public List<BaseEntity> CreateEntityList(List<DtoTipoUsuario> dtos) throws CustomException {
        ArrayList<BaseEntity> tiposUsuarios = new ArrayList<>();

        for(DtoTipoUsuario obj: dtos) {
            tiposUsuarios.add(CreateEntity(obj));
        }

        return tiposUsuarios;
    }
}
