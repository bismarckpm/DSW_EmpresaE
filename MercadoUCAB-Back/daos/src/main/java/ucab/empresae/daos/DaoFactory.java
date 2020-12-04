package ucab.empresae.daos;

public class DaoFactory {

    public static DaoCategoria DaoCategoriaInstancia() {
        return new DaoCategoria();
    }

    public static DaoCliente DaoClienteInstancia() {
        return new DaoCliente();
    }

    public static DaoClienteEstudio DaoClienteEstudioInstancia() {
        return new DaoClienteEstudio();
    }

    public static DaoEncuesta DaoEncuestaInstancia() {
        return new DaoEncuesta();
    }

    public static DaoEncuestado DaoEncuestadoInstancia() {
        return new DaoEncuestado();
    }

    public static DaoEstadoCivil DaoEstadoCivilInstancia() {
        return new DaoEstadoCivil();
    }

    public static DaoEstudio DaoEstudioInstancia() {
        return new DaoEstudio();
    }

    public static DaoEstudioEncuestado DaoEstudioEncuestadoInstancia() {
        return new DaoEstudioEncuestado();
    }

    public static DaoEstudioGenero DaoEstudioGeneroInstancia() {
        return new DaoEstudioGenero();
    }

    public static DaoGenero DaoGeneroInstancia() {
        return new DaoGenero();
    }

    public static DaoHijo DaoHijoInstancia() {
        return new DaoHijo();
    }

    public static DaoLugar DaoLugarInstancia() {
        return new DaoLugar();
    }

    public static DaoMarca DaoMarcaInstancia() {
        return new DaoMarca();
    }

    public static DaoMarcaTipo DaoMarcaTipoInstancia() {
        return new DaoMarcaTipo();
    }

    public static DaoMedioConexion DaoMedioConexionInstancia() {
        return new DaoMedioConexion();
    }

    public static DaoNivelAcademico DaoNivelAcademicoInstancia() {
        return new DaoNivelAcademico();
    }

    public static DaoNivelSocioeconomico DaoNivelSocioeconomicoInstancia() {
        return new DaoNivelSocioeconomico();
    }

    public static DaoOcupacion DaoOcupacionInstancia() {
        return new DaoOcupacion();
    }

    public static DaoOpcion DaoOpcionInstancia() {
        return new DaoOpcion();
    }

    public static DaoPregunta DaoPreguntaInstancia() {
        return new DaoPregunta();
    }

    public static DaoPreguntaOpcion DaoPreguntaOpcionInstancia() {
        return new DaoPreguntaOpcion();
    }

    public static DaoPresentacion DaoPresentacionInstancia() {
        return new DaoPresentacion();
    }

    public static DaoRespuesta DaoRespuestaInstancia() {
        return new DaoRespuesta();
    }

    public static DaoSubcategoria DaoSubcategoriaInstancia() {
        return new DaoSubcategoria();
    }

    public static DaoSubcategoriaMarca DaoSubcategoriaMarcaInstancia() {
        return new DaoSubcategoriaMarca();
    }

    public static DaoSubcategoriaPresentacion DaoSubcategoriaPresentacionInstancia() {
        return new DaoSubcategoriaPresentacion();
    }

    public static DaoSubcategoriaTipo DaoSubcategoriaTipoInstancia() {
        return new DaoSubcategoriaTipo();
    }

    public static DaoTelefono DaoTelefonoInstancia() {
        return new DaoTelefono();
    }

    public static DaoTipo DaoTipoInstancia() {
        return new DaoTipo();
    }

    public static DaoTipoPregunta DaoTipoPreguntaInstancia() {
        return new DaoTipoPregunta();
    }

    public static DaoTipoUsuario DaoTipoUsuarioInstancia() {
        return new DaoTipoUsuario();
    }

    public static DaoUsuario DaoUsuarioInstancia() {
        return new DaoUsuario();
    }

}
