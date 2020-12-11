package ucab.empresae.daos;

import ucab.empresae.entidades.EncuestadoEntity;
import javax.persistence.EntityManager;

public class DaoEncuestado extends Dao<EncuestadoEntity>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEncuestado( )
    {
        super( _handler );
    }
}