package ucab.empresae.daos;

import ucab.empresae.entidades.MedioConexionEntity;
import javax.persistence.EntityManager;

public class DaoMedioConexion extends Dao<MedioConexionEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoMedioConexion( )
    {
        super( _handler );
    }
}
