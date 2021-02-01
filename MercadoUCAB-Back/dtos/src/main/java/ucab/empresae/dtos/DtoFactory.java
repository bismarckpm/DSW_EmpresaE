package ucab.empresae.dtos;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DtoFactory {

    public static DtoCategoria DtoCategoriaInstance() {
        return new DtoCategoria();
    }

    public static DtoCategoria DtoCategoriaInstance(long id){
        return new DtoCategoria(id);
    }

    public static ArrayList<DtoCategoria> DtoCategoriasInstancia() {
        return new ArrayList<>();
    }

    public static DtoCliente DtoClienteInstance() {
        return new DtoCliente();
    }

    public static DtoClienteEstudio DtoClienteEstudioInstance() {
        return new DtoClienteEstudio();
    }

    public static DtoEncuesta DtoEncuestaInstance() {
        return new DtoEncuesta();
    }

    public static DtoEncuestado DtoEncuestadoInstance() {
        return new DtoEncuestado();
    }

    public static DtoEstadoCivil DtoEstadoCivilInstance() {
        return new DtoEstadoCivil();
    }

    public static DtoEstudio DtoEstudioInstance() {
        return new DtoEstudio();
    }

    public static DtoEstudioEncuestado DtoEstudioEncuestadoInstance() {
        return new DtoEstudioEncuestado();
    }

    public static DtoGenero DtoGeneroInstance() {
        return new DtoGenero();
    }

    public static DtoHijo DtoHijoInstance() {
        return new DtoHijo();
    }

    public static DtoLugar DtoLugarInstance() {
        return new DtoLugar();
    }

    public static DtoMarca DtoMarcaInstance() {
        return new DtoMarca();
    }

    public static DtoMedioConexion DtoMedioConexionInstance() {
        return new DtoMedioConexion();
    }

    public static DtoNivelAcademico DtoNivelAcademicoInstance() {
        return new DtoNivelAcademico();
    }

    public static DtoNivelSocioEconomico DtoNivelSocioEconomicoInstance() {
        return new DtoNivelSocioEconomico();
    }

    public static DtoOcupacion DtoOcupacionInstance() {
        return new DtoOcupacion();
    }

    public static DtoOpcion DtoOpcionInstance() {
        return new DtoOpcion();
    }

    public static DtoPregunta DtoPreguntaInstance() {
        return new DtoPregunta();
    }
    public static DtoPregunta DtoPreguntaInstance(long id) {
        return new DtoPregunta(id);
    }

    public static DtoPreguntaOpcion DtoPreguntaOpcionInstance() {
        return new DtoPreguntaOpcion();
    }

    public static DtoPresentacion DtoPresentacionInstance() {
        return new DtoPresentacion();
    }
    public static DtoPresentacion DtoPresentacionInstance(long id){
        return new DtoPresentacion(id);
    }

    public static DtoRespuesta DtoRespuestaInstance() {
        return new DtoRespuesta();
    }

    public static DtoResponse DtoResponseInstance() {
        return new DtoResponse();
    }

    public static DtoSubcategoria DtoSubcategoriaInstance() {
        return new DtoSubcategoria();
    }
    public static DtoSubcategoria DtoSubcategoriaInstance(long id) {
        return new DtoSubcategoria(id);
    }

    public static DtoTipo DtoTipoInstance() {
        return new DtoTipo();
    }

    public static DtoTipoPregunta DtoTipoPreguntaInstance() {
        return new DtoTipoPregunta();
    }
    public static DtoTipoPregunta DtoTipoPreguntaInstance(long id) {
        return new DtoTipoPregunta(id);
    }

    public static DtoTipoUsuario DtoTipoUsuarioInstance() {
        return new DtoTipoUsuario();
    }

    public static DtoUsuario DtoUsuarioInstance() {
        return new DtoUsuario();
    }
}
