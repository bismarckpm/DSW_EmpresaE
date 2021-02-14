package Comandos.Subcategoria;

import Comandos.ComandoBase;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoSubcategoria;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.SubcategoriaEntity;

import java.util.ArrayList;
import java.util.List;

public class ComandoGetSubcategoriasByCategoria extends ComandoBase<DtoResponse> {

    private final long id;
    private List<SubcategoriaEntity> subcategorias = new ArrayList<>();

    public ComandoGetSubcategoriasByCategoria(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {
        DaoSubcategoria daoSubcategoria = DaoFactory.DaoSubcategoriaInstancia();
        this.subcategorias = daoSubcategoria.getSubcategorias(this.id);
    }

    @Override
    public DtoResponse getResult() throws Exception {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando subcategorias");
        dtoResponse.setObjeto(this.subcategorias);

        return dtoResponse;
    }
}
