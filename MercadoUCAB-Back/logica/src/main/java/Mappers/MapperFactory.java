package Mappers;

import Mappers.Categoria.CategoriaMapper;

public class MapperFactory {

    public static CategoriaMapper categoriaMapperInstancia() {
        return new CategoriaMapper();
    }

}
