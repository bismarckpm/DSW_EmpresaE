package ucab.empresae.daos;

import ucab.empresae.entidades.EstadoCivilEntity;

import javax.persistence.EntityManager;

public class DaoEstadoCivil extends Dao<EstadoCivilEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstadoCivil( )
    {
        super( _handler );
    }
}
