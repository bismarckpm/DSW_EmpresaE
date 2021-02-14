package Comandos.NivelSocioeconomico;

import Comandos.ComandoBase;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoNivelSocioeconomico;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.NivelSocioeconomicoEntity;

import java.util.List;

public class ComandoGetNivelesSocioeconomico extends ComandoBase<DtoResponse> {

    private List<NivelSocioeconomicoEntity> niveles;

    public ComandoGetNivelesSocioeconomico() {
    }

    @Override
    public void execute() throws Exception {
        DaoNivelSocioeconomico dao = DaoFactory.DaoNivelSocioeconomicoInstancia();
        this.niveles = dao.findAll(NivelSocioeconomicoEntity.class);
    }

    @Override
    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Caragando niveles Socioeconomicos");
        dtoResponse.setObjeto(this.niveles);

        return dtoResponse;

    }
}
