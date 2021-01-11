package Comandos;

import Comandos.Categoria.ComandoGetCategoria;
import Comandos.Categoria.ComandoGetCategorias;
import ucab.empresae.excepciones.CategoriaException;

public class ComandoFactory <TComando> {

    public static ComandoGetCategoria comandoGetCategoriaInstancia(long id) throws CategoriaException {
        return new ComandoGetCategoria(id);
    }

    public static ComandoGetCategorias comandoGetCategoriasInstancia() {
        return new ComandoGetCategorias();
    }

}
