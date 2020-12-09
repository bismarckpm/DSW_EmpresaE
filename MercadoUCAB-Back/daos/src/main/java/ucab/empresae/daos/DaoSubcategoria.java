package ucab.empresae.daos;

import ucab.empresae.entidades.SubcategoriaEntity;
import javax.persistence.EntityManager;

public class DaoSubcategoria extends Dao<SubcategoriaEntity>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoSubcategoria( )
    {
        super( _handler );
    }
}
