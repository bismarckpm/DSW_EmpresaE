package ucab.empresae.daos;

import ucab.empresae.entidades.GeneroEntity;

import javax.persistence.EntityManager;

public class DaoGenero extends Dao<GeneroEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoGenero( )
    {
        super( _handler );
    }
}
