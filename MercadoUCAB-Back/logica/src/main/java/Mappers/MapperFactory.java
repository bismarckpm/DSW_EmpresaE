package Mappers;

import Mappers.Categoria.CategoriaMapper;
import Mappers.Presentacion.PresentacionMapper;
import Mappers.Pregunta.PreguntaMapper;
import Mappers.Subcategoria.SubcategoriaMapper;
import Mappers.TipoPregunta.TipoPreguntaMapper;

public class MapperFactory {

    public static CategoriaMapper categoriaMapperInstancia() {
        return new CategoriaMapper();
    }

    public static PresentacionMapper presentacionMapperInstancia() {
        return new PresentacionMapper();
    }

    public static PreguntaMapper preguntaMapperInstancia() {
        return new PreguntaMapper();
    }

    public static SubcategoriaMapper subcategoriaMapperInstancia() {
        return new SubcategoriaMapper();
    }

    public static TipoPreguntaMapper tipoPreguntaMapperInstancia() {
        return new TipoPreguntaMapper();
    }


}
