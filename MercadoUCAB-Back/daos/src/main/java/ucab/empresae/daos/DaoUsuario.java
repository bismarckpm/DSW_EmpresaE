package ucab.empresae.daos;

import ucab.empresae.entidades.UsuarioEntity;

import javax.persistence.EntityManager;

public class DaoUsuario extends Dao<UsuarioEntity>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoUsuario() {
        super(_handler);
    }

}
