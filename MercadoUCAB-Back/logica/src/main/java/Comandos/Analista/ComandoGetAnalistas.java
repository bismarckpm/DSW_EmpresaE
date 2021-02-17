package Comandos.Analista;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.dtos.DtoFactory;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.dtos.DtoUsuario;

import java.util.ArrayList;
import java.util.List;

public class ComandoGetAnalistas extends ComandoBase<DtoResponse> {

    private List<DtoUsuario> analistas = new ArrayList<>();

    public ComandoGetAnalistas() {
    }

    @Override
    public void execute() throws Exception {

        DaoUsuario daoUsuario = DaoFactory.DaoUsuarioInstancia();
        GenericMapper analistasMapper = MapperFactory.usuarioMapperInstancia();

        this.analistas = analistasMapper.CreateDtoList(daoUsuario.getAnalistas());

    }

    @Override
    public DtoResponse getResult() throws Exception {

        execute();
        DtoResponse dtoResponse = DtoFactory.DtoResponseInstance();
        dtoResponse.setEstado("Exitoso");
        dtoResponse.setMensaje("Cargando analistas");
        dtoResponse.setObjeto(this.analistas);

        return dtoResponse;

    }
}
