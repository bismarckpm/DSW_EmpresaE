package Comandos.Estudio;

import Comandos.ComandoBase;
import ucab.empresae.daos.DaoEstudio;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.EstudioEntity;

public class ComandoDeleteEstudio extends ComandoBase<DtoResponse> {

    private final long id;

    public ComandoDeleteEstudio(long id) {

        this.id = id;

    }

    @Override
    public void execute() throws Exception {

        DaoEstudio daoEstudio = DaoFactory.DaoEstudioInstancia();
        daoEstudio.delete(daoEstudio.find(this.id, EstudioEntity.class));

    }

    @Override
    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Estudio eliminado");

        return dtoResponse;

    }
}
