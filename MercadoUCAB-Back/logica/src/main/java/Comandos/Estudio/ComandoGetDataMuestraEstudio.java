package Comandos.Estudio;

import Comandos.ComandoBase;
import ucab.empresae.daos.DaoEncuestado;
import ucab.empresae.daos.DaoEstudio;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.EncuestadoEntity;
import ucab.empresae.entidades.EstudioEntity;

import java.util.List;

public class ComandoGetDataMuestraEstudio extends ComandoBase<DtoResponse> {

    private final long id;
    List<EncuestadoEntity> dataMuestraEncuestados = null;

    public ComandoGetDataMuestraEstudio(long id) {

        this.id = id;

    }

    @Override
    public void execute() throws Exception {

        DaoEstudio daoEstudio = DaoFactory.DaoEstudioInstancia();
        EstudioEntity estudio = daoEstudio.find(this.id, EstudioEntity.class);

        DaoEncuestado daoEncuestado = DaoFactory.DaoEncuestadoInstancia();
        this.dataMuestraEncuestados = daoEncuestado.getDataMuestraEstudio(estudio.getLugar(), estudio.getNivelSocioEconomico());
    }

    @Override
    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando estudios");
        dtoResponse.setObjeto(this.dataMuestraEncuestados);

        return dtoResponse;

    }
}
