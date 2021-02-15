package Comandos.Estudio;

import Comandos.ComandoBase;
import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.*;

import java.util.ArrayList;
import java.util.List;

public class ComandoGetDataMuestraEstudioxAnalista extends ComandoBase<DtoResponse> {
    private final long id;
    List<EncuestadoAux> dataMuestraEncuestado = new ArrayList<EncuestadoAux>();
    List<EncuestadoEntity> dataMuestra = null;

    public ComandoGetDataMuestraEstudioxAnalista(long id) {

        this.id = id;

    }

    @Override
    public void execute() throws Exception {

        DaoEstudio daoEstudio = DaoFactory.DaoEstudioInstancia();
        EstudioEntity estudio = daoEstudio.find(this.id, EstudioEntity.class);

        DaoTelefono daoTelefono = DaoFactory.DaoTelefonoInstancia();


        DaoEncuestado daoEncuestado = DaoFactory.DaoEncuestadoInstancia();
        this.dataMuestra = daoEncuestado.getDataMuestraEstudioxAnalista(this.id);

        DaoEstudioEncuestado daoEstudioEncuestado = DaoFactory.DaoEstudioEncuestadoInstancia();

        //llenado del aux para que tenga el estado de la n a n y el telefono
        for(EncuestadoEntity encuestado : this.dataMuestra){
            EncuestadoAux encuestadoAux = new EncuestadoAux(encuestado.get_id());

            encuestadoAux.setPrimerNombre(encuestado.getPrimerNombre());
            encuestadoAux.setPrimerApellido(encuestado.getPrimerApellido());
            encuestadoAux.setNivelacademico(encuestado.getNivelacademico());


            EstudioEncuestadoEntity estudioEncuestado = daoEstudioEncuestado.getEstudioEncuestado(encuestado, estudio);
            encuestadoAux.setEstadoEstudioEncuestado(estudioEncuestado.getEstado());

            TelefonoEntity telefono = EntidadesFactory.TelefonoInstance();
            telefono = daoTelefono.getTelefonoByEncuestado(encuestado);
            encuestadoAux.setTelefono(telefono.getNumero());

            this.dataMuestraEncuestado.add(encuestadoAux);
        }
    }

    @Override
    public DtoResponse getResult() throws Exception {
        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando Data Muestra x Analista jeje");
        dtoResponse.setObjeto(this.dataMuestraEncuestado);

        return dtoResponse;

    }
}
