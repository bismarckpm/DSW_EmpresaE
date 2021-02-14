package Comandos.Genero;

import Comandos.ComandoBase;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoGenero;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.GeneroEntity;

import java.util.List;

public class ComandoGetGeneros extends ComandoBase<DtoResponse> {

    private List<GeneroEntity> generos;

    public ComandoGetGeneros() {
    }

    @Override
    public void execute() throws Exception {

        DaoGenero dao = DaoFactory.DaoGeneroInstancia();
        this.generos = dao.findAll(GeneroEntity.class);

    }

    @Override
    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Caragando generos");
        dtoResponse.setObjeto(this.generos);

        return dtoResponse;

    }
}
