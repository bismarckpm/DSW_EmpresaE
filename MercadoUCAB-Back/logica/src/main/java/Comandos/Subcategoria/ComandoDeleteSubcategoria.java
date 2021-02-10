package Comandos.Subcategoria;

import Comandos.ComandoBase;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoSubcategoria;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.SubcategoriaEntity;

public class ComandoDeleteSubcategoria extends ComandoBase<DtoResponse> {

    private final long id;

    public ComandoDeleteSubcategoria(long id) {

        this.id = id;

    }

    @Override
    public void execute() throws Exception {

        DaoSubcategoria daoSubcategoria = DaoFactory.DaoSubcategoriaInstancia();
        daoSubcategoria.delete(daoSubcategoria.find(this.id, SubcategoriaEntity.class));

    }

    @Override
    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Subcategoria eliminada");

        return dtoResponse;

    }
}
