package Comandos;

import Comandos.Analista.ComandoGetAnalistas;
import Comandos.Categoria.*;
import Comandos.Estudio.*;
import Comandos.Pregunta.ComandoGetPreguntas;
import Comandos.Presentacion.ComandoDeletePresentacion;
import Comandos.Presentacion.ComandoGetPresentaciones;
import Comandos.Presentacion.ComandoPostPresentacion;
import Comandos.Presentacion.ComandoUpdatePresentacion;
import Comandos.Subcategoria.*;
import ucab.empresae.dtos.*;
import ucab.empresae.excepciones.CategoriaException;

public class ComandoFactory {

    //Fabrica de subcategorias
    public static ComandoGetSubcategorias comandoGetSubcategoriasInstancia() {
        return new ComandoGetSubcategorias();
    }

    public static ComandoGetSubcategoria comandoGetSubcategoriaInstancia(long id) {
        return new ComandoGetSubcategoria(id);
    }

    public static ComandoAddSubcategoria comandoAddSubcategoriaInstancia(DtoSubcategoria dtoSubcategoria) {
        return new ComandoAddSubcategoria(dtoSubcategoria);
    }

    public static ComandoUpdateSubcategoria comandoUpdateSubcategoria(DtoSubcategoria dtoSubcategoria) {
        return new ComandoUpdateSubcategoria(dtoSubcategoria);
    }

    public static ComandoDeleteSubcategoria comandoDeleteSubcategoriaInstancia(long id) {
        return new ComandoDeleteSubcategoria(id);
    }

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

    //Fábrica de los comandos para Estudio

    public static ComandoGetEstudios comandoGetEstudiosInstancia() {
        return new ComandoGetEstudios();
    }

    public static ComandoGetEstudio comandoGetEstudioInstancia(long id) {
        return new ComandoGetEstudio(id);
    }

    public static ComandoGetEstudioSinEncuesta comandoGetEstudioSinEncuesta() {
        return new ComandoGetEstudioSinEncuesta();
    }

    public static ComandoGetEstudiosCliente comandoGetEstudiosClienteInstancia(long id) {
        return new ComandoGetEstudiosCliente(id);
    }

    public static ComandoGetEstudiosAnalista comandoGetEstudiosAnalistaInstancia(long id) {
        return new ComandoGetEstudiosAnalista(id);
    }

    public static ComandoAddEstudio comandoAddEstudioInstancia(DtoEstudio dtoEstudio) {
        return new ComandoAddEstudio(dtoEstudio);
    }

    public static ComandoSolicitarEstudio comandoSolicitarEstudioInstancia(long id, DtoEstudio dtoEstudio) {
        return new ComandoSolicitarEstudio(id, dtoEstudio);
    }

    public static ComandoUpdateEstudio comandoUpdateEstudioInstancia(DtoEstudio dtoEstudio) {
        return new ComandoUpdateEstudio(dtoEstudio);
    }

    public static ComandoRandomAnalista comandoRandomAnalistaInstancia() {
        return new ComandoRandomAnalista();
    }

    //analistas
    public static ComandoGetAnalistas comandoGetAnalistas() {
        return new ComandoGetAnalistas();
    }
}
