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

public class ComandoGetSubcategoria extends ComandoBase<DtoResponse> {

    private final long id;
    private DtoSubcategoria dtoSubcategoria = DtoFactory.DtoSubcategoriaInstance();

    public ComandoGetSubcategoria(long id) {

        this.id = id;

    }

    @Override
    public void execute() throws Exception {

        DaoSubcategoria daoSubcategoria = DaoFactory.DaoSubcategoriaInstancia();
        GenericMapper subcategoriaMapper = MapperFactory.subcategoriaMapperInstancia();

        this.dtoSubcategoria = (DtoSubcategoria) subcategoriaMapper.CreateDto(daoSubcategoria.find(this.id, SubcategoriaEntity.class));

    }

    @Override
    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando subcategoria");
        dtoResponse.setObjeto(this.dtoSubcategoria);

        return dtoResponse;

    }
}
