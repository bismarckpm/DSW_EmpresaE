package ucab.empresae.daos;

import ucab.empresae.entidades.TipoUsuarioEntity;
import javax.persistence.EntityManager;

public class DaoTipoUsuario extends Dao<TipoUsuarioEntity>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoTipoUsuario( )
    {
        super( _handler );
    }
}
