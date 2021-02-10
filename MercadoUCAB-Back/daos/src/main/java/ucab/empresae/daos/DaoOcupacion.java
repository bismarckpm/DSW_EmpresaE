package ucab.empresae.daos;

import ucab.empresae.entidades.OcupacionEntity;

import javax.persistence.EntityManager;

public class DaoOcupacion extends Dao<OcupacionEntity>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoOcupacion( )
    {
        super( _handler );
    }
}
