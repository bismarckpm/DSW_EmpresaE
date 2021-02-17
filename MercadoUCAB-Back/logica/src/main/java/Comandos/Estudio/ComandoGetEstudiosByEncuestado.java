package Comandos.Estudio;

import Comandos.ComandoBase;
import ucab.empresae.daos.*;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.*;
import ucab.empresae.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class ComandoGetEstudiosByEncuestado extends ComandoBase<DtoResponse> {


    private List<EstudioAux> estudioAuxList = new ArrayList<EstudioAux>(); //entidad que incluye el estado de la n-n estudioEncuestado
    private final long id;

    public ComandoGetEstudiosByEncuestado(long id) {

        this.id = id;

    }

    @Override
    public void execute() throws Exception {

        DaoUsuario daoUsuario = DaoFactory.DaoUsuarioInstancia();
        DaoEncuestado daoEncuestado = DaoFactory.DaoEncuestadoInstancia();
        DaoEstudioEncuestado daoEstudioEncuestado =DaoFactory.DaoEstudioEncuestadoInstancia();
        UsuarioEntity usuarioEntity = daoUsuario.find(this.id, UsuarioEntity.class);

        EncuestadoEntity encuestadoEntity = daoEncuestado.getEncuestadoByUsuario(usuarioEntity);

        DaoEstudio daoEstudio = DaoFactory.DaoEstudioInstancia();
        List<EstudioEntity> estudios = daoEstudio.getEstudiosEncuestado(encuestadoEntity);

        if(estudios == null){
            throw new CustomException("COMGETEST001","No existen estudios relacionados con el Encuestado");
        }

        //llenado del aux para que tenga el estado de la n a n
        for(EstudioEntity estudio : estudios){
            EstudioAux estudioAux = new EstudioAux(estudio.get_id());

            estudioAux.setNombre(estudio.getNombre());
            estudioAux.setAnalista(estudio.getAnalista());
            estudioAux.setComentarioAnalista(estudio.getComentarioAnalista());
            estudioAux.setEdadMinima(estudio.getEdadMinima());
            estudioAux.setEdadMaxima(estudio.getEdadMaxima());
            estudioAux.setNivelSocioEconomico(estudio.getNivelSocioEconomico());
            estudioAux.setSubcategoria(estudio.getSubcategoria());
            estudioAux.setLugar(estudio.getLugar());
            estudioAux.setFechaInicio(estudio.getFechaInicio());
            estudioAux.setFechaFin(estudio.getFechaFin());


            EstudioEncuestadoEntity estudioEncuestado = daoEstudioEncuestado.getEstudioEncuestado(encuestadoEntity, estudio);
            estudioAux.setEstadoEstudioEncuestado(estudioEncuestado.getEstado());

            this.estudioAuxList.add(estudioAux);
        }

    }

    @Override

    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando estudios");
        dtoResponse.setObjeto(this.estudioAuxList);

        return dtoResponse;

    }
}
