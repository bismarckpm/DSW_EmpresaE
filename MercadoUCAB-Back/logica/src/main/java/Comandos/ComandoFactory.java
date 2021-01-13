package Comandos;

import Comandos.Categoria.*;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.excepciones.CategoriaException;

public class ComandoFactory {

    //Fábricas de los comandos para categoria
    public static ComandoGetCategoria comandoGetCategoriaInstancia(long id) throws CategoriaException {
        return new ComandoGetCategoria(id);
    }

    public static ComandoGetCategorias comandoGetCategoriasInstancia() {
        return new ComandoGetCategorias();
    }

    public static ComandoPostCategoria comandoPostCategoriaInstancia(DtoCategoria dtoCategoria) {
        return new ComandoPostCategoria(dtoCategoria);
    }

    public static ComandoUpdateCategoria comandoUpdateCategoriaInctancia(DtoCategoria dtoCategoria) {
        return new ComandoUpdateCategoria(dtoCategoria);
    }

    public static ComandoDeleteCategoria comandoDeleteCategoriaInstancia(long id) {
        return new ComandoDeleteCategoria(id);
    }

}
