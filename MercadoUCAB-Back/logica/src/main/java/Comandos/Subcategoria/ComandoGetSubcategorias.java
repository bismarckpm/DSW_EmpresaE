package Comandos.Subcategoria;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoSubcategoria;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.dtos.DtoSubcategoria;
import ucab.empresae.entidades.SubcategoriaEntity;

import java.util.ArrayList;
import java.util.List;

public class ComandoGetSubcategorias extends ComandoBase<DtoResponse> {

    private List<DtoSubcategoria> dtoSubcategorias = new ArrayList<>();

    public ComandoGetSubcategorias() {
    }

    @Override
    public void execute() throws Exception {
        DaoSubcategoria daoSubcategoria = DaoFactory.DaoSubcategoriaInstancia();

        GenericMapper subcategoriaMapper = MapperFactory.subcategoriaMapperInstancia();
        this.dtoSubcategorias = subcategoriaMapper.CreateDtoList(daoSubcategoria.findAll(SubcategoriaEntity.class));
    }

    @Override
    public DtoResponse getResult() throws Exception {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando subcategorias");
        dtoResponse.setObjeto(this.dtoSubcategorias);

        return dtoResponse;
    }
}
