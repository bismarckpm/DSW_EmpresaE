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

public class ComandoAddSubcategoria extends ComandoBase<DtoResponse> {

    private DtoSubcategoria dtoSubcategoria;

    public ComandoAddSubcategoria(DtoSubcategoria dtoSubcategoria) {

        this.dtoSubcategoria = dtoSubcategoria;

    }

    @Override
    public void execute() throws Exception {

        DaoSubcategoria daoSubcategoria = DaoFactory.DaoSubcategoriaInstancia();
        GenericMapper subcategoriaMapper = MapperFactory.subcategoriaMapperInstancia();

        this.dtoSubcategoria = (DtoSubcategoria) subcategoriaMapper.CreateDto(daoSubcategoria.insert((SubcategoriaEntity) subcategoriaMapper.CreateEntity(this.dtoSubcategoria)));

    }

    @Override
    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Subcategoria insertada");
        dtoResponse.setObjeto(this.dtoSubcategoria);

        return dtoResponse;

    }
}
