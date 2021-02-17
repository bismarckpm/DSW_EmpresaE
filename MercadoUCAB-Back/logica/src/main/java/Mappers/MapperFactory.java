package Mappers;

import Mappers.Categoria.CategoriaMapper;
import Mappers.Estudio.EstudioMapper;
import Mappers.Genero.GeneroMapper;
import Mappers.Lugar.LugarMapper;
import Mappers.NivelSocioeconomico.NivelSocioeconomicoMapper;
import Mappers.Pregunta.PreguntaMapper;
import Mappers.Presentacion.PresentacionMapper;
import Mappers.Subcategoria.SubcategoriaMapper;
import Mappers.TipoPregunta.TipoPreguntaMapper;
import Mappers.TipoUsuario.TipoUsuarioMapper;
import Mappers.Usuario.UsuarioMapper;

public class MapperFactory {

    //CategoriaMapper
    public static CategoriaMapper categoriaMapperInstancia() {
        return new CategoriaMapper();
    }

    //PresentacionMapper
    public static PresentacionMapper presentacionMapperInstancia() {
        return new PresentacionMapper();
    }

    //PreguntaMapper
    public static PreguntaMapper preguntaMapperInstancia() {
        return new PreguntaMapper();
    }

    //subcategoriaMapper
    public static SubcategoriaMapper subcategoriaMapperInstancia() {
        return new SubcategoriaMapper();
    }

    //TipoPregunta
    public static TipoPreguntaMapper tipoPreguntaMapperInstancia() {
        return new TipoPreguntaMapper();
    }

    //NivelSocioeconomico
    public static NivelSocioeconomicoMapper nivelSocioeconomicoMapperInstancia() {
        return new NivelSocioeconomicoMapper();
    }

    //lugar
    public static LugarMapper lugarMapperInstancia() {
        return new LugarMapper();
    }

    //tipoUsuario
    public static TipoUsuarioMapper tipoUsuarioMapperInstancia() {
        return new TipoUsuarioMapper();
    }

    //Usuario
    public static UsuarioMapper usuarioMapperInstancia() {
        return new UsuarioMapper();
    }

    //genero
    public static GeneroMapper generoMapperInstancia() {
        return new GeneroMapper();
    }

    //Estudio
    public static EstudioMapper estudioMapperInstancia() {
        return new EstudioMapper();
    }
}
