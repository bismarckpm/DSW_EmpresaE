package Comandos.Lugar;

import Comandos.ComandoBase;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoLugar;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.LugarEntity;

import java.util.List;

public class ComandoGetLugar extends ComandoBase<DtoResponse> {

    private List<LugarEntity> lugares;
    private final long id;

    public ComandoGetLugar(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {
        DaoLugar dao = DaoFactory.DaoLugarInstancia();
        this.lugares = dao.getLugaresById(this.id);
    }

    @Override
    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Caragando lugares");
        dtoResponse.setObjeto(this.lugares);

        return dtoResponse;

    }
}
