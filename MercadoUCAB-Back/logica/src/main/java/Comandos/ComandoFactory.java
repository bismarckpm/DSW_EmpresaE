package Comandos;

import Comandos.Categoria.*;
import Comandos.Presentacion.*;
import Comandos.Pregunta.*;
import ucab.empresae.dtos.DtoCategoria;
import ucab.empresae.dtos.DtoPresentacion;
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

    //Fábricas de los comandos para Presentacion
    public static ComandoGetPresentaciones comandoGetPresentacionesInstancia() {
        return new ComandoGetPresentaciones();
    }

    public static ComandoPostPresentacion comandoPostPresentacionInstancia(DtoPresentacion dtoPresentacion) {
        return new ComandoPostPresentacion(dtoPresentacion);
    }

    public static ComandoUpdatePresentacion comandoUpdatePresentacionInstancia(DtoPresentacion dtoPresentacion) {
        return new ComandoUpdatePresentacion(dtoPresentacion);
    }

    public static ComandoDeletePresentacion comandoDeletePresentacionInstancia(long id) {
        return new ComandoDeletePresentacion(id);
    }

    ////Fábricas de los comandos para Pregunta

    public static ComandoGetPreguntas comandoGetPreguntasInstancia() {
        return new ComandoGetPreguntas();
    }
}
