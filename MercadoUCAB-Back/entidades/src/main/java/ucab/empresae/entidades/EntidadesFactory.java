package ucab.empresae.entidades;

public class EntidadesFactory {

    public static CategoriaEntity CategoriaInstance() {
        return new CategoriaEntity();
    }

    public static CategoriaEntity CategoriaInstance(long id) {
        return new CategoriaEntity(id);
    }

    public static ClienteEntity ClienteInstance() {
        return new ClienteEntity();
    }

    public static ClienteEstudioEntity ClienteEstudiosInstance() {
        return new ClienteEstudioEntity();
    }

    public static EncuestadoEntity EncuestadoInstance() {
        return new EncuestadoEntity();
    }

    public static EncuestaEntity EncuestaInstance() {
        return new EncuestaEntity();
    }

    public static EstadoCivilEntity EstadoCivilInstance() {
        return new EstadoCivilEntity();
    }

    public static EstudioEncuestadoEntity EstudioEncuestadoInstance() {
        return new EstudioEncuestadoEntity();
    }

    public static EstudioEntity EstudioInstance() {
        return new EstudioEntity();
    }

    public static GeneroEntity GeneroInstance() {
        return new GeneroEntity();
    }

    public static HijoEntity HijoInstance() {
        return new HijoEntity();
    }

    public static LugarEntity LugarInstance() {
        return new LugarEntity();
    }

    public static MarcaEntity MarcaInstance() {
        return new MarcaEntity();
    }

    public static MedioConexionEntity MedioConexionInstance() {
        return new MedioConexionEntity();
    }

    public static NivelAcademicoEntity NivelAcademicoInstance() {
        return new NivelAcademicoEntity();
    }

    public static NivelSocioeconomicoEntity NivelSocioeconomicoInstance() {
        return new NivelSocioeconomicoEntity();
    }

    public static OcupacionEntity OcupacionInstance() {
        return new OcupacionEntity();
    }

    public static OpcionEntity OpcionInstance() {
        return new OpcionEntity();
    }

    public static PreguntaEntity PreguntaInstance() {
        return new PreguntaEntity();
    }
    public static PreguntaEntity PreguntaInstance(long id) {
        return new PreguntaEntity(id);
    }

    public static PreguntaOpcionEntity PreguntaOpcionInstance() {
        return new PreguntaOpcionEntity();
    }

    public static PresentacionEntity PresentacionInstance() {
        return new PresentacionEntity();
    }

    public static PresentacionEntity PresentacionInstance(long id) {
        return new PresentacionEntity(id);
    }

    public static RespuestaEntity RespuestaInstance() {
        return new RespuestaEntity();
    }

    public static SubcategoriaEntity SubcategoriaInstance() {
        return new SubcategoriaEntity();
    }
    public static SubcategoriaEntity SubcategoriaInstance(long id) {
        return new SubcategoriaEntity(id);
    }

    public static TelefonoEntity TelefonoInstance() {
        return new TelefonoEntity();
    }

    public static TipoEntity TipoInstance() {
        return new TipoEntity();
    }

    public static TipoPreguntaEntity TipoPreguntaInstance() {
        return new TipoPreguntaEntity();
    }
    public static TipoPreguntaEntity TipoPreguntaInstance(long id) {
        return new TipoPreguntaEntity(id);
    }


    public static TipoUsuarioEntity TipoUsuarioInstance() {
        return new TipoUsuarioEntity();
    }

    public static UsuarioEntity UsuarioInstance() {
        return new UsuarioEntity();
    }

}