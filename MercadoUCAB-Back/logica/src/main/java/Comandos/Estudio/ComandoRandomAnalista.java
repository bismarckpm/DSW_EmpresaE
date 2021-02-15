package Comandos.Estudio;

import Comandos.ComandoBase;
import Mappers.GenericMapper;
import Mappers.MapperFactory;
import ucab.empresae.daos.DaoFactory;
import ucab.empresae.daos.DaoUsuario;
import ucab.empresae.dtos.DtoUsuario;
import ucab.empresae.entidades.UsuarioEntity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ComandoRandomAnalista extends ComandoBase<DtoUsuario> {

    private DtoUsuario dtoAnalista;

    public ComandoRandomAnalista() {
    }

    @Override
    public void execute() throws Exception {

        DaoUsuario daoUsuario1 = DaoFactory.DaoUsuarioInstancia();
        List<UsuarioEntity> listaAnalista = daoUsuario1.getAnalistas();
        int analistaAleatorio =  ThreadLocalRandom.current().nextInt(0, listaAnalista.size()-1);

        GenericMapper analistaMapper = MapperFactory.usuarioMapperInstancia();
        this.dtoAnalista = (DtoUsuario) analistaMapper.CreateDto(listaAnalista.get(analistaAleatorio));

    }

    @Override
    public DtoUsuario getResult() throws Exception {
        execute();
        return this.dtoAnalista;
    }
}
