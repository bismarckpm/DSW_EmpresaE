package Mappers.Usuario;

import Mappers.GenericMapper;
import Mappers.MapperFactory;
import Mappers.TipoUsuario.TipoUsuarioMapper;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoTipoUsuario;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.entidades.BaseEntity;
import ucab.empresae.entidades.EntidadesFactory;
import ucab.empresae.entidades.TipoUsuarioEntity;
import ucab.empresae.entidades.UsuarioEntity;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper extends GenericMapper<DtoUsuario> {

    @Override
    public DtoUsuario CreateDto(BaseEntity entity) throws CustomException {
        UsuarioEntity usuario = (UsuarioEntity) entity;
        if(usuario.get_id() <= 0) {
            throw new CustomException("MAPUSU001","El id del usuario debe ser mayor a 0.");
        }else if(usuario.getEstado() == null) {
            throw new CustomException("MAPUSU001","El usuario debe tener un id asignado.");
        }else if(!usuario.getEstado().equals("a") && !usuario.getEstado().equals("i")) {
            throw new CustomException("MAPUSU001","Estado del usuario inválido");
        }else if(usuario.getClave() == null) {
            throw new CustomException("MAPUSU001","El usuario debe tener una clave asignada.");
        }else if(usuario.getTipousuario() == null) {
            throw new CustomException("MAPUSU001","El usuario debe tener un tipo de usuario aisgnado.");
        }else if(usuario.getUsername() == null) {
            throw new CustomException("MAPUSU001","El usuario debe tener un username asignado.");
        }else {
            DtoUsuario dtoUsuario = DtoFactory.DtoUsuarioInstance();
            dtoUsuario.setEstado(usuario.getEstado());

            TipoUsuarioMapper tipoUsuarioMapper = MapperFactory.tipoUsuarioMapperInstancia();
            dtoUsuario.setTipoUsuario(tipoUsuarioMapper.CreateDto(usuario.getTipousuario()));

            dtoUsuario.set_id(usuario.get_id());
            dtoUsuario.setClave(usuario.getClave());

            return dtoUsuario;
        }
    }

    @Override
    public BaseEntity CreateEntity(DtoUsuario dtoUsuario) throws CustomException {
        if(dtoUsuario.getEstado() == null) {
            throw new CustomException("MAPUSU001","El usuario debe tener un id asignado.");
        }else if(!dtoUsuario.getEstado().equals("a") && !dtoUsuario.getEstado().equals("i")) {
            throw new CustomException("MAPUSU001","Estado del usuario inválido");
        }else if(dtoUsuario.getClave() == null) {
            throw new CustomException("MAPUSU001","El usuario debe tener una clave asignada.");
        }else if(dtoUsuario.getTipoUsuario() == null) {
            throw new CustomException("MAPUSU001","El usuario debe tener un tipo de usuario aisgnado.");
        }else if(dtoUsuario.getUsername() == null) {
            throw new CustomException("MAPUSU001","El usuario debe tener un username asignado.");
        }else {
            UsuarioEntity usuario = EntidadesFactory.UsuarioInstance();
            usuario.setEstado(dtoUsuario.getEstado());
            usuario.setUsername(dtoUsuario.getUsername());
            usuario.setClave(dtoUsuario.getClave());

            DaoTipoUsuario daoTipoUsuario = DaoFactory.DaoTipoUsuarioInstancia();
            usuario.setTipousuario(daoTipoUsuario.find(dtoUsuario.getTipoUsuario().get_id(), TipoUsuarioEntity.class));

            if(usuario.get_id() != 0) {
                usuario.set_id(dtoUsuario.get_id());
            }

            return usuario;
        }
    }

    @Override
    public List<DtoUsuario> CreateDtoList(List<BaseEntity> entities) throws CustomException {
        ArrayList<DtoUsuario> dtos = new ArrayList<>();

        for (BaseEntity obj : entities) {
            dtos.add(CreateDto(obj));
        }
        return dtos;
    }

    @Override
    public List<BaseEntity> CreateEntityList(List<DtoUsuario> dtos) throws CustomException {
        ArrayList<BaseEntity> usuarios = new ArrayList<>();

        for(DtoUsuario obj: dtos) {
            usuarios.add(CreateEntity(obj));
        }

        return usuarios;
    }
}
